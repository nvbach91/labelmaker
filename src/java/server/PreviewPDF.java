/**
 *
 *
 */
package server;

import com.itextpdf.text.DocumentException;
import data.FileHandler;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pdf.PDFDesign;
import pdf.PDFMaker;

/**
 *
 * @author Nguyen Viet Bach
 */
@WebServlet(name = "PreviewPDF", urlPatterns = {"/LabelMaker/PreviewPDF"})
public class PreviewPDF extends HttpServlet {

    private SimpleDateFormat sdf;

    @Override
    public void init() {
        sdf = new SimpleDateFormat("dd.MM.YYYY HH:mm:ss");
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String styleName = request.getParameter("STYLE");
        String size = request.getParameter("SIZE");
        String color = request.getParameter("COLOR");
        String list = request.getParameter("TS");

        /*boolean received = true;
        if (styleName == null || size == null || color == null || list == null) {
            received = false;
        }*/

        System.out.println("[*] Using: " + styleName + " " + size + " " + color);

        try (PrintWriter pw = new PrintWriter("itemList.txt")) {
            pw.print(list + "\n");
        }

        FileHandler fh = new FileHandler("itemList.txt");
        boolean parsedOK = fh.isSuccess() ;

        if (parsedOK) {
            System.out.println("[*] " + sdf.format(Calendar.getInstance().getTime()) + " Success Parsing file: " + fh.getAbsoluteFilePath());

            Map<String, String> fontMap = new HashMap<>();

            fontMap.put("gothic", getServletContext().getRealPath("/fonts/gothic.TTF"));
            fontMap.put("bookman", getServletContext().getRealPath("/fonts/bookman.TTF"));
            fontMap.put("droidsans", getServletContext().getRealPath("/fonts/droidsans.ttf"));
            fontMap.put("digital7", getServletContext().getRealPath("/fonts/digital7.ttf"));
            fontMap.put("courier", getServletContext().getRealPath("/fonts/courier.ttf"));
            fontMap.put("impact", getServletContext().getRealPath("/fonts/impact.ttf"));
            fontMap.put("modern", getServletContext().getRealPath("/fonts/modern.TTF"));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            PDFMaker pdfMaker = new PDFMaker(fh.getList(), baos, new PDFDesign(size, color, styleName, fontMap));
            try {
                pdfMaker.makePDF();
            } catch (DocumentException ex) {
                Logger.getLogger(PreviewPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0, no-cache, no-store");
            response.setHeader("Pragma", "public");
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());

            try (OutputStream os = response.getOutputStream()) {
                baos.writeTo(os);
                os.flush();
            }
        } else {
            System.out.println("[*] " + sdf.format(Calendar.getInstance().getTime()) + " Failed Parsing file: " + fh.getAbsoluteFilePath());
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head><meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\"/>");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/EnterpriseApps/styles/style_7_10_2014.css\">");
                out.println("<link href=\"http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic\" rel=\"stylesheet\" type=\"text/css\">");
                out.println("<script src=\"//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script>");
                out.println("<script src=\"/EnterpriseApps/scripts/script_7_10_2014.js\"></script>");
                out.println("<title>LabelMaker PreviewPDF</title> </head>");
                out.println("<body style=\"background:none;\" onload=\"document.body.classList.add('loaded')\">");
                //if (received) {
                    out.println("<div class=\"erroratframe\" id=\"idErrorDiv\" onclick=\"removeDiv(document.getElementById('idErrorDiv'))\">"
                            + "Lỗi ở dòng : " + fh.getErrorLineNumber()
                            + "</div>"
                            + "<div class=\"erroratframe\" id=\"idBadInputDiv\" onclick=\"removeDiv(document.getElementById('idBadInputDiv'))\">\""
                            + fh.getLineStoppedAt()
                            + "\"</div>"
                            + "<div class=\"erroratframe\" id=\"idExampleDiv\" onclick=\"removeDiv(document.getElementById('idExampleDiv'))\">"
                            + "-;Cocacola;27.00;Kc;1;L;</div>"
                            + "<div class=\"erroratframe\" id=\"idRefreshDiv\" onclick=\"removeDiv(document.getElementById('idRefreshDiv'))\">"
                            + "Refresh: Ctrl+F5</div>"
                    );
                /*} else {
                    System.out.println("[*] User informed about null");
                    out.println("<div class=\"erroratframe\" id=\"idErrorDiv\" onclick=\"removeDiv(document.getElementById('idErrorDiv'))\">"
                            + "Lỗi null pointer: Bạn hãy refresh trang web (Ctrl+F5) và lặp lại"
                            + "</div>");

                }*/
                out.println("</body></html>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
