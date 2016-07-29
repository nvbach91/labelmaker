/**
 *
 *
 */
package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nguyen Viet Bach
 */
public class FileHandler {

    private final List<Item> list;
    private final BufferedReader br;
    private final String fp;
    private boolean success;
    private int errorLineNumber;
    private boolean continueReadingFile;
    private String lineStoppedAt;

    public FileHandler(String path) throws FileNotFoundException, IOException {
        lineStoppedAt = "";
        continueReadingFile = true;
        errorLineNumber = 0;
        success = true;
        File f = new File(path);
        FileReader fr = new FileReader(f);
        fp = f.getAbsolutePath();
        list = new ArrayList<>();
        br = new BufferedReader(fr);

        String line = br.readLine();
        while (line != null && continueReadingFile) {
            if (!line.equals("")) {
                if (!line.startsWith("//")) {
                    //if(line.length()){}
                    addItemToList(line);
                }
                errorLineNumber++;
            }
            line = br.readLine();
        }
        if (!continueReadingFile) {
            System.err.println("[*] Bad input line " + errorLineNumber + ": " + lineStoppedAt);

        }
        br.close();
    }

    private void addItemToList(String line) {
        try {

            lineStoppedAt = line;
            if (lineStoppedAt.length() > 80) {
                lineStoppedAt = lineStoppedAt.substring(0, 80);
            }
            if (line.length() < 16) {
                throw new Exception("Line length shorter than minimum(16)");
            }
            if (line.length() > 80) {
                throw new Exception("Line length longer than maximum(80)");
            }
            String ean = line.substring(0, line.indexOf(";"));
            line = line.substring(ean.length() + 1, line.length());

            String title = line.substring(0, line.indexOf(";"));
            line = line.substring(title.length() + 1, line.length());

            String price = line.substring(0, line.indexOf(";"));
            line = line.substring(price.length() + 1, line.length());

            String currency = line.substring(0, line.indexOf(";"));
            line = line.substring(currency.length() + 1, line.length());

            String amount = line.substring(0, line.indexOf(";"));
            line = line.substring(amount.length() + 1, line.length());

            String unit = line.substring(0, line.indexOf(";"));
            line = line.substring(unit.length() + 1, line.length());

            //System.out.println("[*] Remains "+line.length());
            String secondPrice = "";
            String secondCurrency = "";
            if (line.length() > 0) {
                secondPrice = line.substring(0, line.indexOf(";"));
                line = line.substring(secondPrice.length() + 1, line.length());

                secondCurrency = line.substring(0, line.indexOf(";"));
                //line = line.substring(unit.length() + 1, line.length());
                //System.out.println("[*] " + secondPrice + " " + secondCurrency);
            }

            if (!isValidCurrency(currency)) {
                success = false;
                continueReadingFile = false;
                throw new Exception("Invalid currency");
            }
            if (!isValidUnit(unit)) {
                success = false;
                continueReadingFile = false;
                throw new Exception("Invalid unit");
            }
            if (!isValidPrice(price)) {
                success = false;
                continueReadingFile = false;
                throw new Exception("Invalid price");
            }
            if (!secondPrice.isEmpty()) {
                if (!isValidPrice(secondPrice) /*|| !isValidPrice(secondPrice)*/) {
                    success = false;
                    continueReadingFile = false;
                    throw new Exception("Invalid second price");
                }
            }
            if (!isValidAmount(amount)) {
                success = false;
                continueReadingFile = false;
                throw new Exception("Invalid Amount");
            }

            BigDecimal bigPrice = new BigDecimal(price);
            BigDecimal bigAmount = new BigDecimal(amount);
            
            BigDecimal bigSecondPrice = null;
            if (!secondPrice.isEmpty()) {
                bigSecondPrice = new BigDecimal(secondPrice);
            }

            list.add(new Item(ean, title, bigPrice, currency, bigAmount, unit, bigSecondPrice, secondCurrency));
        } catch (Exception ee) {
            String msg = ee.getMessage();
            if (msg == null) {
                msg = "BigDecimal conversion failed";
            }
            System.err.println("[*] Caught Parsing Exception: " + msg);
            success = false;
            continueReadingFile = false;
        }
    }

    public List<Item> getList() {
        return list;
    }

    public String getAbsoluteFilePath() {
        return fp;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getErrorLineNumber() {
        return errorLineNumber;
    }

    private boolean isValidCurrency(String currency) {
        return currency.equalsIgnoreCase("Kc")
                || currency.equalsIgnoreCase("Kč")
                || currency.equalsIgnoreCase("Sk")
                || currency.equalsIgnoreCase("Eur");
    }

    private boolean isValidUnit(String unit) {
        return unit.equalsIgnoreCase("kg")
                || unit.equalsIgnoreCase("g")
                || unit.equalsIgnoreCase("l")
                || unit.equalsIgnoreCase("cl")
                || unit.equalsIgnoreCase("ml")
                || unit.equalsIgnoreCase("ks");
    }

    public String getLineStoppedAt() {
        return lineStoppedAt;
    }

    private boolean isValidPrice(String price) {
        int dotIndex = price.indexOf('.');
        if (dotIndex == -1 || price.length() < 4) {
            return false;
        }
        String decimalPart = price.substring(dotIndex + 1, price.length());
        return decimalPart.length() == 2;
    }

    private boolean isValidAmount(String amount) {
        return amount.length() >= 1;
    }
}
