/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf;

import data.FontPaths;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import data.Item;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nguyen Viet Bach
 */
public class PDFDesign {

    private int nColumns;
    private String styleName;
    private float cellHeight;
    private float titleLineFixedHeight;
    private float bigPriceNumberLeading;
    private BaseColor color;
    private int priceFontInc;
    private int textFontInc;
    private String size;

    private Font titleLineFont;
    private Font amountAndPriceLabelLineFont;
    private Font bigPriceNumberFont;
    private Font decimalPriceNumberFont;
    private Font unitPriceLineFont;
    private BaseFont droidsans;

    private final FontPaths fontPaths;

    public PDFDesign(String s, String c, String sn, Map<String, String> fontMap) {
        size = s;
        titleLineFixedHeight = 15.0f;
        priceFontInc = 0;
        textFontInc = 0;
        styleName = sn;
        fontPaths = new FontPaths(fontMap);
        nColumns = 6;
        switch (c) {
            case "black":
                color = BaseColor.BLACK;
                break;
            case "gray":
                color = BaseColor.DARK_GRAY;
                break;
            case "red":
                color = BaseColor.RED.darker();
                break;
            case "green":
                color = BaseColor.GREEN.darker().darker().darker();
                break;
            case "blue":
                color = BaseColor.BLUE.darker().darker().darker();
                break;
            default:
                this.color = BaseColor.BLACK;
        }
        switch (size) {
            case "3x5":
                cellHeight = 83.8f;
                break;
            case "3.5x5":
                cellHeight = 97.8f;
                break;
            case "3.5x6":
                priceFontInc = 8;
                cellHeight = 97.8f;
                nColumns = 5;
                break;
            case "3.5x7":
                cellHeight = 97.8f;
                nColumns = 4;
                break;
            case "4x6":
                titleLineFixedHeight = 26.0f;
                priceFontInc = 8;
                cellHeight = 117.0f;
                nColumns = 5;
                break;
            case "4x7":
                titleLineFixedHeight = 26.0f;
                priceFontInc = 8;
                cellHeight = 117.0f;
                nColumns = 4;
                break;
            case "5x6":
                titleLineFixedHeight = 30.0f;
                priceFontInc = 13;
                textFontInc = 2;
                cellHeight = 145.0f;
                nColumns = 5;
                break;
            case "5x7":
                titleLineFixedHeight = 30.0f;
                priceFontInc = 18;
                textFontInc = 3;
                cellHeight = 145.0f;
                nColumns = 4;
                break;
            case "6x7":
                titleLineFixedHeight = 34.0f;
                priceFontInc = 26;
                textFontInc = 5;
                cellHeight = 175.0f;
                nColumns = 4;
                break;
        }
        try {
            int normal = Font.NORMAL;
            int bold = Font.BOLD;

            droidsans = BaseFont.createFont(fontPaths.getPath("droidsans"), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            BaseFont impact = BaseFont.createFont(fontPaths.getPath("impact"), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            switch (styleName) {
                case "style1":
                    BaseFont courier = BaseFont.createFont(fontPaths.getPath("courier"), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                    initValues(42f,
                            new Font(courier, 12 + textFontInc, normal, color),
                            new Font(courier, 9 + textFontInc, normal, color),
                            new Font(impact, 43 + priceFontInc + textFontInc, normal, color),
                            new Font(courier, 20 + textFontInc, normal, color),
                            new Font(courier, 8 + textFontInc, normal, color));
                    break;
                case "style2":
                    BaseFont digital7 = BaseFont.createFont(fontPaths.getPath("digital7"), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                    initValues(42f,
                            new Font(droidsans, 10 + textFontInc, normal, color),
                            new Font(droidsans, 7 + textFontInc, normal, color),
                            new Font(digital7, 55 + priceFontInc + textFontInc, normal, color),
                            new Font(digital7, 30, normal, color),
                            new Font(droidsans, 7 + textFontInc, normal, color));
                    break;
                case "style3":
                    BaseFont modern = BaseFont.createFont(fontPaths.getPath("modern"), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                    initValues(40f,
                            new Font(droidsans, 10 + textFontInc, normal, color),
                            new Font(droidsans, 7 + textFontInc, normal, color),
                            new Font(modern, 55 + priceFontInc + textFontInc, normal, color),
                            new Font(modern, 30, normal, color),
                            new Font(droidsans, 7 + textFontInc, normal, color));
                    break;
                case "style4":
                    BaseFont gothic = BaseFont.createFont(fontPaths.getPath("gothic"), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                    initValues(40f,
                            new Font(droidsans, 10 + textFontInc, normal, color),
                            new Font(droidsans, 7 + textFontInc, normal, color),
                            new Font(gothic, 48 + priceFontInc + textFontInc, bold, color),
                            new Font(gothic, 30, bold, color),
                            new Font(droidsans, 7 + textFontInc, normal, color));
                    break;
                case "style5":
                    BaseFont bookman = BaseFont.createFont(fontPaths.getPath("bookman"), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                    initValues(35f,
                            new Font(droidsans, 10 + textFontInc, normal, color),
                            new Font(droidsans, 7 + textFontInc, normal, color),
                            new Font(bookman, 45 + priceFontInc + textFontInc, bold, color),
                            new Font(bookman, 25, normal, color),
                            new Font(droidsans, 7 + textFontInc, normal, color));
                    break;

                case "style6":
                    initValues(45f,
                            new Font(droidsans, 10 + textFontInc, normal, color),
                            new Font(droidsans, 7 + textFontInc, normal, color),
                            new Font(impact, 45 + priceFontInc + textFontInc, normal, color),
                            new Font(droidsans, 20 + textFontInc, normal, color),
                            new Font(droidsans, 7 + textFontInc, normal, color));
                    break;

                default:
                    System.out.println("[*] Unknown Style");
                    break;
            }

        } catch (DocumentException | IOException ex) {
            System.out.println("[E] " + ex.getMessage());
        }
    }

    private void initValues(float l, Font ltf, Font aapllf, Font bpnf, Font dpnf, Font unpf) {
        if (styleName.equals("style1") && size.equals("3x5")) {
            bigPriceNumberLeading = l - 7;
        } else {
            bigPriceNumberLeading = l + priceFontInc + textFontInc;
        }
        titleLineFont = ltf;

        amountAndPriceLabelLineFont = aapllf;
        bigPriceNumberFont = bpnf;
        decimalPriceNumberFont = dpnf;
        unitPriceLineFont = unpf;
    }

    public PdfPTable createTable(List<Item> l) throws DocumentException, IOException {
        boolean usingDoublePriceMode = false;
        if (!l.get(0).getSecondPrice().equals("")) {
            usingDoublePriceMode = true;
        }
        if (usingDoublePriceMode && size.equals("6x7")) {
            return createEuro_6_Table(l);
        }
        if (usingDoublePriceMode) {
            return createEuroTable(l);
        }
        if ((size.equals("4x7") || size.equals("5x6") || size.equals("5x7") || size.equals("6x7")) && styleName.equals("style6")) {
            return createStyle6_4x7Table(l);
        }
        return createPriceTable(l);
    }

    public PdfPTable createPriceTable(List<Item> itemList) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(nColumns);
        table.setWidthPercentage(100.0f);
        //PdfContentByte cb = writer.getDirectContent();

        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            //cell.setBorder(0);
            //BarcodeEAN code = new BarcodeEAN();
            //code.setCode(item.getBarCode());
            //code.setGuardBars(false);
            //Image im = code.createImageWithBarcode(cb, BaseColor.BLACK, BaseColor.BLACK);
            //p.add(im);//new Chunk(im,0.5f,0.5f));

            PdfPTable innerTable = new PdfPTable(1);

            Paragraph p1 = new Paragraph();
            p1.setFont(titleLineFont);
            String t = item.getTitle();
            String[] split = t.split("//");
            //System.out.println(t);

            for (String split1 : split) {
                p1.add(split1 + "\n");
            }
            PdfPCell innercCell1 = new PdfPCell(p1);
            innercCell1.setLeading(11f, 0f);
            innercCell1.setBorder(0);
            if (t.contains("//") && (size.equals("4x6") || size.equals("4x7") || size.equals("5x6") || size.equals("5x7"))) {
                innercCell1.setFixedHeight(titleLineFixedHeight);
            } else {
                innercCell1.setFixedHeight(15.0f);
            }
            //innercCell1.setFixedHeight(titleLineFixedHeight);
            innercCell1.setHorizontalAlignment(Element.ALIGN_CENTER);

            Paragraph p2 = new Paragraph();
            p2.setFont(amountAndPriceLabelLineFont);
            p2.add("Množství: " + item.getAmount() + item.getUnit() + "\n");
            PdfPCell innercCell2 = new PdfPCell(p2);
            innercCell2.setLeading(5f + textFontInc, 0f);
            innercCell2.setBorder(0);
            innercCell2.setHorizontalAlignment(Element.ALIGN_CENTER);

            Paragraph p3 = new Paragraph();
            p3.setFont(bigPriceNumberFont);
            p3.add(item.getPrice().substring(0, item.getPrice().length() - 3));
            p3.setFont(decimalPriceNumberFont);
            p3.add(item.getPrice().substring(item.getPrice().length() - 3, item.getPrice().length()));
            p3.setFont(unitPriceLineFont);
            p3.add(item.getCurrency());
            PdfPCell innercCell3 = new PdfPCell(p3);
            if (styleName.equals("style6") /*&& (size.equals("4x7") || size.equals("4x6"))*/) {
                innercCell3 = new PdfPCell(createLine3Table(item, 45 + priceFontInc, 20, 15));
            }
            innercCell3.setBorder(0);
            innercCell3.setLeading(bigPriceNumberLeading, 0f);
            innercCell3.setHorizontalAlignment(Element.ALIGN_CENTER);

            Paragraph p4 = new Paragraph();
            p4.setFont(unitPriceLineFont);
            p4.add("Cena za "+item.getXAmount() + item.getXUnit() + ": " + item.getUnitPrice() + " " + item.getCurrency());
            PdfPCell innercCell4 = new PdfPCell(p4);
            innercCell4.setBorder(0);
            innercCell4.setLeading(5f + textFontInc, 0f);
            innercCell4.setHorizontalAlignment(Element.ALIGN_CENTER);

            innerTable.addCell(innercCell1);
            innerTable.addCell(innercCell2);
            innerTable.addCell(innercCell3);
            innerTable.addCell(innercCell4);

            PdfPCell cell = new PdfPCell(innerTable);
            cell.setFixedHeight(cellHeight);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderColor(BaseColor.GRAY);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
        }

        int remainder = itemList.size() % nColumns;
        if (remainder != 0) {
            remainder = nColumns - remainder;
        }
        for (int i = 0; i < remainder; i++) {
            PdfPCell c = new PdfPCell(new Phrase(" "));
            c.setBorderColor(BaseColor.GRAY);
            table.addCell(c);
        }

        return table;
    }

    private PdfPTable createStyle6_4x7Table(List<Item> list) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(nColumns);
        table.setWidthPercentage(100.0f);

        for (int i = 0; i < list.size(); i++) {
            Item item = list.get(i);
            PdfPTable innerTable = new PdfPTable(1);
            Paragraph p1 = new Paragraph();
            p1.setFont(titleLineFont);
            String t = item.getTitle();
            String[] split = t.split("//");
            for (String split1 : split) {
                p1.add(split1 + "\n");
            }
            PdfPCell innercCell1 = new PdfPCell(p1);
            innercCell1.setBorder(0);
            if (t.contains("//")) {
                innercCell1.setFixedHeight(titleLineFixedHeight);
            } else {
                innercCell1.setFixedHeight(15.0f + textFontInc);
            }
            innercCell1.setHorizontalAlignment(Element.ALIGN_CENTER);

            Paragraph p2 = new Paragraph();
            p2.setFont(amountAndPriceLabelLineFont);
            p2.add("Množství: " + item.getAmount() + item.getUnit() + "\n");
            PdfPCell innercCell2 = new PdfPCell(p2);
            innercCell2.setLeading(5f, 0f);
            innercCell2.setBorder(0);
            innercCell2.setFixedHeight(12.0f);
            innercCell2.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell innercCell3 = new PdfPCell(createLine3Table(item, 0, 0, 0));
            innercCell3.setBorder(0);
            innercCell3.setLeading(2.0f, 0f);
            innercCell3.setFixedHeight(67.0f);
            innercCell3.setHorizontalAlignment(Element.ALIGN_CENTER);

            Paragraph p4 = new Paragraph();
            p4.setFont(unitPriceLineFont);
            p4.add("Cena za 1" + item.getXUnit() + ": " + item.getUnitPrice() + " " + item.getCurrency());
            PdfPCell innercCell4 = new PdfPCell(p4);
            innercCell4.setBorder(0);
            innercCell4.setLeading(1f, 0f);
            innercCell4.setHorizontalAlignment(Element.ALIGN_CENTER);

            innerTable.addCell(innercCell1);
            innerTable.addCell(innercCell2);
            innerTable.addCell(innercCell3);
            innerTable.addCell(innercCell4);

            PdfPCell cell = new PdfPCell(innerTable);
            cell.setFixedHeight(cellHeight);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderColor(BaseColor.GRAY);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
        }

        int remainder = list.size() % nColumns;
        if (remainder != 0) {
            remainder = nColumns - remainder;
        }
        for (int i = 0; i < remainder; i++) {
            PdfPCell c = new PdfPCell(new Phrase(" "));
            c.setBorderColor(BaseColor.GRAY);
            table.addCell(c);
        }
        return table;
    }

    private PdfPTable createLine3Table(Item item, int s1, int s2, int s3) throws DocumentException {
        if (s1 != 0 && s1 != 0 && s3 != 0) {
            bigPriceNumberFont.setSize(s1);
            decimalPriceNumberFont.setSize(s2);
            decimalPriceNumberFont.setSize(s3);
        } else {
            bigPriceNumberFont.setSize(70);
        }
        Paragraph p3 = new Paragraph();
        p3.setFont(bigPriceNumberFont);
        p3.add(item.getPrice().substring(0, item.getPrice().length() - 3));

        Paragraph p3_2 = new Paragraph();
        p3_2.setFont(decimalPriceNumberFont);
        p3_2.add(item.getPrice().substring(item.getPrice().length() - 2, item.getPrice().length()));

        Paragraph p3_3 = new Paragraph();
        p3_3.setFont(decimalPriceNumberFont);
        p3_3.add(item.getCurrency());

        PdfPTable table = new PdfPTable(2);
        if (item.getPrice().length() == 6) {
            table.setWidths(new float[]{2.8f, 1});
        } else if (item.getPrice().length() == 5) {
            table.setWidths(new float[]{1.8f, 1});
        } else {
            table.setWidths(new float[]{1.5f, 1});
        }
        PdfPCell cell = new PdfPCell(p3);
        cell.setRowspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

        if (s1 != 0 && s1 != 0 && s3 != 0) {
            cell.setLeading(42.0f, 0f);
        } else {
            cell.setLeading(55.0f, 0f);
        }
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(p3_2);
        cell.setRowspan(1);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(p3_3);
        cell.setRowspan(1);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);

        return table;
    }

    private PdfPTable createEuroTable(List<Item> items) throws DocumentException, IOException {
        int nColumnsEuro = 4;
        PdfPTable table = new PdfPTable(nColumnsEuro);
        table.setWidthPercentage(100.0f);

        for (int i = 0; i < items.size(); i++) {
            table.addCell(createEuroCell(items.get(i)));
        }

        int remainder = items.size() % nColumnsEuro;
        if (remainder != 0) {
            remainder = nColumnsEuro - remainder;
        }
        for (int i = 0; i < remainder; i++) {
            table.addCell("");
        }
        return table;
    }

    private PdfPCell createEuroCell(Item item) throws DocumentException, IOException {
        Font descFont = new Font(droidsans, 12, Font.NORMAL, color);
        Font unitPriceFont = new Font(droidsans, 10, Font.NORMAL, color);

        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100f);
        PdfPCell cell;
        Paragraph p;

        cell = new PdfPCell();
        p = new Paragraph();
        p.setFont(descFont);
        p.add(item.getTitle());
        cell.setFixedHeight(28f);
        cell.setPhrase(p);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell();
        p = new Paragraph();
        p.setFont(descFont);
        p.add(item.getAmount() + item.getUnit());
        cell.setFixedHeight(16f);
        cell.setPhrase(p);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(0);
        table.addCell(cell);

        table.addCell(createEuroInnerPrice(item));

        cell = new PdfPCell();
        p = new Paragraph();
        p.setFont(unitPriceFont);
        p.add("Cena za 1" + item.getXUnit() + ": " + item.getUnitPrice() + item.getCurrency() + "/" + item.getSecondUnitPrice() + item.getSecondCurrency());
        cell.setPhrase(p);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(0);
        cell.setLeading(2f, 0f);
        table.addCell(cell);

        PdfPCell finalCell = new PdfPCell(table);
        finalCell.setFixedHeight(117.4f);
        finalCell.setBorderColor(BaseColor.GRAY);
        return finalCell;
    }

    private PdfPCell createEuroInnerPrice(Item item) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1f, 1f});

        table.addCell(createEuroInnerInnerPrice(item.getPrice(), item.getCurrency(), true));
        table.addCell(createEuroInnerInnerPrice(item.getSecondPrice(), item.getSecondCurrency(), false));

        PdfPCell finalCell = new PdfPCell(table);
        finalCell.setFixedHeight(57f);
        finalCell.setLeading(20f, 0f);
        finalCell.setBorder(0);
        return finalCell;
    }

    private PdfPCell createEuroInnerInnerPrice(String price, String curr, boolean isFirst) throws DocumentException {

        Font bigPriceFont = new Font(droidsans, 40, Font.BOLD, color);
        Font smallPriceFont = new Font(droidsans, 18, Font.NORMAL, color);
        Font unitFont = new Font(droidsans, 12, Font.NORMAL, color);

        PdfPTable table = new PdfPTable(2);
        table.setWidths(new float[]{3f, 1f});
        PdfPCell cell;
        Paragraph para;

        para = new Paragraph("");
        para.setFont(bigPriceFont);
        if (price.length() >= 4) {
            para.add(price.substring(0, price.length() - 3));
        }
        cell = new PdfPCell(para);
        cell.setRowspan(2);
        cell.setFixedHeight(57f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(0);
        table.addCell(cell);

        para = new Paragraph("");
        para.setFont(smallPriceFont);
        if (price.length() >= 4) {
            para.add(price.substring(price.length() - 2, price.length()));
        }
        cell = new PdfPCell(para);
        cell.setRowspan(1);
        cell.setFixedHeight(35f);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(0);
        table.addCell(cell);

        para = new Paragraph();
        para.setFont(unitFont);
        para.add(curr);
        cell = new PdfPCell(para);
        cell.setRowspan(1);
        cell.setFixedHeight(22f);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(0);
        table.addCell(cell);

        PdfPCell finalCell = new PdfPCell(table);
        finalCell.setBorder(0);
        /*if (isFirst) {
         finalCell.setBorder(Rectangle.RIGHT);
         }*/
        return finalCell;
    }

    private PdfPTable createEuro_6_Table(List<Item> list) throws DocumentException {
        //int nColumns = 4;
        PdfPTable table = new PdfPTable(nColumns);
        table.setWidthPercentage(100.0f);

        for (int i = 0; i < list.size(); i++) {
            table.addCell(createEuro_6_Cell(list.get(i)));
        }
        int remainder = list.size() % nColumns;
        if (remainder != 0) {
            remainder = nColumns - remainder;
        }
        for (int i = 0; i < remainder; i++) {
            PdfPCell c = new PdfPCell(new Phrase(" "));
            c.setBorderColor(BaseColor.GRAY);
            table.addCell(c);
        }
        return table;
    }

    private PdfPCell createEuro_6_Cell(Item item) throws DocumentException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100f);

        PdfPCell cell;
        Paragraph p;

        cell = new PdfPCell();
        p = new Paragraph();
        p.setFont(new Font(droidsans, 13, Font.NORMAL, color));
        String t = item.getTitle();
        String[] split = t.split("//");
        for (String split1 : split) {
            p.add(split1 + "\n");
        }
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPhrase(p);
        cell.setColspan(2);
        cell.setFixedHeight(30f);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell();
        p = new Paragraph();
        p.setFont(new Font(droidsans, 11, Font.NORMAL, color));
        p.add(item.getAmount() + "" + item.getUnit());
        cell.setPhrase(p);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);

        table.addCell(createEuro_6_InnerPrice(item));

        cell = new PdfPCell();
        p = new Paragraph();
        p.setFont(new Font(droidsans, 11, Font.NORMAL, color));
        p.add("Cena za 1" + item.getXUnit() + ": " + item.getUnitPrice() + item.getCurrency() + "/" + item.getSecondUnitPrice() + item.getSecondCurrency());
        cell.setPhrase(p);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(0);
        table.addCell(cell);

        PdfPCell finalCell = new PdfPCell(table);
        finalCell.setFixedHeight(cellHeight);//175f
        finalCell.setBorderColor(BaseColor.GRAY);
        return finalCell;
    }

    private PdfPCell createEuro_6_InnerPrice(Item item) throws DocumentException {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100f);

        table.addCell(createEuro_6_InnerInnerPrice(item.getPrice(), item.getCurrency(), true));
        table.addCell(createEuro_6_InnerInnerPrice(item.getSecondPrice(), item.getSecondCurrency(), false));

        PdfPCell finalCell = new PdfPCell(table);
        finalCell.setBorder(0);
        return finalCell;
    }

    private PdfPCell createEuro_6_InnerInnerPrice(String price, String curr, boolean isFirst) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidths(new float[]{3f, 1.2f});
        PdfPCell cell;
        Paragraph para;

        para = new Paragraph("");
        para.setFont(new Font(droidsans, 60, Font.BOLD, color));
        if (price.length() >= 4) {
            para.add(price.substring(0, price.length() - 3));
        }
        cell = new PdfPCell(para);
        cell.setRowspan(2);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setLeading(50f, 0f);
        cell.setBorder(0);
        table.addCell(cell);

        para = new Paragraph("");
        para.setFont(new Font(droidsans, 28, Font.NORMAL, color));
        if (price.length() >= 4) {
            para.add(price.substring(price.length() - 2, price.length()));
        }
        cell = new PdfPCell(para);
        cell.setRowspan(1);
        cell.setFixedHeight(32f);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(0);
        table.addCell(cell);

        para = new Paragraph();
        para.setFont(new Font(droidsans, 16, Font.NORMAL, color));
        para.add(curr);
        cell = new PdfPCell(para);
        cell.setRowspan(1);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(0);
        table.addCell(cell);

        PdfPCell finalCell = new PdfPCell(table);
        finalCell.setBorder(0);
        /*if (isFirst) {
            finalCell.setBorder(Rectangle.RIGHT);
        }*/
        return finalCell;
    }
}
