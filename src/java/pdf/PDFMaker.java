/**
 *
 *
 */
package pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import data.Item;
import java.util.List;
import java.io.*;

/**
 *
 * @author Nguyen Viet Bach
 */
public class PDFMaker {

    private final List<Item> itemList;
    private final Document doc;
    private final ByteArrayOutputStream baos;
    private final PDFDesign design;

    public PDFMaker(List<Item> l, ByteArrayOutputStream b, PDFDesign d) {
        itemList = l;
        doc = new Document();
        baos = b;
        design = d;

    }

    public void makePDF() throws DocumentException, IOException {

        PdfWriter.getInstance(doc, baos);
        doc.setPageSize(PageSize.A4.rotate());
        doc.setMargins(3.0f, 10.0f, 2.0f, 6.0f); //left right top bottom
        doc.open();
        doc.add(design.createTable(itemList));

        doc.close();
    }

}
