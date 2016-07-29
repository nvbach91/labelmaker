/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package languages;

/**
 *
 * @author Nguyen Viet Bach
 */
public class Labels {

    private String PlainTextModeButton;
    private String DownloadText;
    private String RemoveLastRow;
    private String PreviewInPDF;
    private String AddToList;
    private String ChooseStyle;

    //private String Style;
    //private String Color;
    //private String Size;
    private String Black;
    private String Gray;
    private String Red;
    private String Green;
    private String Blue;

    private String EAN;
    private String Title;
    private String Price;
    private String Currency;
    private String Amount;
    private String Unit;
    private String DragDrop;

    public Labels(String lang) {
        PlainTextModeButton = "Plain Text Mode";
        DownloadText = "Download Text";
        RemoveLastRow = "Remove Last Row";
        PreviewInPDF = "Preview in PDF";
        AddToList = "Add to List";
        ChooseStyle = "Choose Style";

        //Style = "Style";
        //Color = "Color";
        //Size = "Size";
        Black = "Black";
        Gray = "Gray";
        Red = "Red";
        Green = "Green";
        Blue = "Blue";

        EAN = "EAN (*)";
        Title = "TITLE";
        Price = "PRICE";
        Currency = "CURRENCY";
        Amount = "AMOUNT";
        Unit = "UNIT";
        DragDrop = "Drop File Here";

        switch (lang) {
            case "vietnamese":
                PlainTextModeButton = "Chế Độ Văn Bản";
                DownloadText = "Tải Dữ Liệu";
                RemoveLastRow = "Xoá Dòng Cuối Cùng";
                PreviewInPDF = "Xem Bản In";
                AddToList = "Thêm Mặt Hàng";
                ChooseStyle = "Tuỳ Chọn Mẫu";

                //Style = "Kiểu";// Mẫu";
                //Color = "Màu";
                //Size = "Kích Cỡ";
                Black = "Đen";
                Gray = "Xám";
                Red = "Đỏ";
                Green = "XanhLC";
                Blue = "XanhNB";

                EAN = "EAN (*)";
                Title = "Tên Mặt Hàng";
                Price = "Giá Tiền";
                Currency = "Tiền Tệ";
                Amount = "Số Lượng";
                Unit = "Đơn Vị";
                DragDrop = "Thả tập tin tại đây";//dữ liệu hoặc sao chép và dán;
                break;
            case "czech":
                PlainTextModeButton = "Textový Režim";
                DownloadText = "Stáhnout dat. soubor";
                RemoveLastRow = "Smazat řádek";
                PreviewInPDF = "Náhled v PDF";
                AddToList = "Přidat zboží";
                ChooseStyle = "Vybrat Styl";

                //Style = "Styl";
                //Color = "Barva";
                //Size = "Velikost";
                Black = "Černá";
                Gray = "Šedá";
                Red = "Červená";
                Green = "Zelená";
                Blue = "Modrá";

                EAN = "EAN (*)";
                Title = "Název";
                Price = "Cena";
                Currency = "Měna";
                Amount = "Množství";
                Unit = "Jednotka";
                DragDrop = "Nahrajte soubor zde";
                break;
        }

    }

    public String getPlainTextModeButton() {
        return PlainTextModeButton;
    }

    public String getDownloadText() {
        return DownloadText;
    }

    public String getRemoveLastRow() {
        return RemoveLastRow;
    }

    public String getPreviewInPDF() {
        return PreviewInPDF;
    }

    public String getAddToList() {
        return AddToList;
    }

    public String getEAN() {
        return EAN;
    }

    public String getTitle() {
        return Title;
    }

    public String getPrice() {
        return Price;
    }

    public String getCurrency() {
        return Currency;
    }

    public String getAmount() {
        return Amount;
    }

    public String getUnit() {
        return Unit;
    }

    public String getDragDrop() {
        return DragDrop;
    }

    public String getChooseStyle() {
        return ChooseStyle;
    }

    /*public String getStyle() {
        return Style;
    }*/

    public String getBlue() {
        return Blue;
    }

    public String getGreen() {
        return Green;
    }

    public String getRed() {
        return Red;
    }

    public String getGray() {
        return Gray;
    }

    public String getBlack() {
        return Black;
    }

    /*public String getColor() {
        return Color;
    }*/

   /*public String getSize() {
        return Size;
    }*/

}
