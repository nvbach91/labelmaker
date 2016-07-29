/**
 *
 *
 */
package data;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Nguyen Viet Bach
 */
public class Item {

    private final String barCode;
    private final String title;
    private final BigDecimal price;
    private final String currency;
    private final BigDecimal amount;
    private final String unit;
    private final BigDecimal unitPrice;

    private final BigDecimal secondPrice;
    private final String secondCurrency;
    private BigDecimal secondUnitPrice;

    public Item(String b, String t, BigDecimal p, String c, BigDecimal a, String u, BigDecimal sp, String sc) {
        barCode = b;
        title = t;
        price = p;//.divide(new BigDecimal("100"), 2, RoundingMode.CEILING);
        currency = c;
        amount = a;
        unit = u;
        if (unit.equalsIgnoreCase("ml")) {
            a = a.divide(new BigDecimal(1000));
        } else if (unit.equalsIgnoreCase("cl")) {
            a = a.divide(new BigDecimal(100));
        } else if (unit.equalsIgnoreCase("g")) {
            if (a.compareTo(new BigDecimal("100")) == 1) {
                a = a.divide(new BigDecimal(1000));
            } else {
                a = a.divide(new BigDecimal(100));
            }
        }
        unitPrice = price.divide(a, 2, RoundingMode.CEILING);

        secondPrice = sp;
        secondCurrency = sc;
        secondUnitPrice = null;
        if (secondPrice != null) {
            secondUnitPrice = secondPrice.divide(a, 2, RoundingMode.CEILING);
        }

    }

    public String getTitle() {
        return title;
    }

    public String getBarCode() {
        String finalBarCode = barCode;
        for (int i = 0; i < 13 - barCode.length(); i++) {
            finalBarCode = "0" + finalBarCode;
        }
        return finalBarCode;
    }

    public String getAmount() {
        return amount.toString();
    }

    public String getPrice() {
        return price.toString()/*.replace('.', ',')*/;
    }

    public String getUnitPrice() {
        return unitPrice.toString();
    }

    public String getUnit() {
        return unit;
    }

    public String getXUnit() {
        if (unit.equalsIgnoreCase("g")) {
            if (amount.compareTo(new BigDecimal("100")) == 1) {
                return "kg";
            } else {
                return "g";
            }
        } else if (unit.equalsIgnoreCase("l")) {
            return "L";
        } else if (unit.equalsIgnoreCase("cl")) {
            return "L";
        } else if (unit.equalsIgnoreCase("ml")) {
            return "L";
        }
        return unit;
    }

    public String getXAmount() {
        if (!unit.equalsIgnoreCase("g")) {
            return "1";
        }

        if (unit.equalsIgnoreCase("g")) {
            if (amount.compareTo(new BigDecimal("100")) == 1) {
                return "1";
            }
        }
        return "100";
    }

    public String getCurrency() {
        if (currency.equalsIgnoreCase("Kc")) {
            return "Kč";
        } else if (currency.equalsIgnoreCase("Eur")) {
            return "\u20AC"; //€ euro sign
        }
        return currency;
    }

    public String getSecondPrice() {
        if (secondPrice == null) {
            return "";
        }
        return secondPrice.toString();
    }

    public String getSecondCurrency() {
        if (secondCurrency.equalsIgnoreCase("Kc")) {
            return "Kč";
        } else if (secondCurrency.equalsIgnoreCase("Eur")) {
            return "\u20AC"; //€ euro sign
        }
        return secondCurrency;
    }

    public String getSecondUnitPrice() {
        if (secondUnitPrice == null) {
            return "";
        }
        return secondUnitPrice.toString();
    }

}
