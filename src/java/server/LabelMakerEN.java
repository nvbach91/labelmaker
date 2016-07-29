/**
 *
 *
 */
package server;

import data.UserDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import languages.Labels;

/**
 *
 * @author Nguyen Viet Bach
 */
@WebServlet(name = "LabelMakerEN", urlPatterns
        = {
            "/en/LabelMaker"
        })
public class LabelMakerEN extends HttpServlet {

    private UserDatabase udb;

    @Override
    public void init() {
        udb = new UserDatabase();
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

        udb.addUser("[EN];" + IPDetector.getClientIpAddr(request));

        Labels labels = new Labels("english");

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");        
        
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
        
        
        try (PrintWriter out = response.getWriter()) {
            int currentUserAccessedWithinHisSession;
            if (request.getSession().getAttribute("count") == null) {
                currentUserAccessedWithinHisSession = 0;
            } else {
                currentUserAccessedWithinHisSession = (Integer) request.getSession().getAttribute("count");
            }
            request.getSession().setAttribute("count", ++currentUserAccessedWithinHisSession);

            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            //out.println("<meta http-equiv=\"Cache-Control\" content=\"no-cache, no-store, must-revalidate\" />"
            //        + "<meta http-equiv=\"Pragma\" content=\"no-cache\" />"
            //        + "<meta http-equiv=\"Expires\" content=\"0\" />");
            //out.println("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\"/>"
            //        + "<meta http-equiv=\"cache-control\" content=\"max-age=0\" />"
            //        + "<meta http-equiv=\"cache-control\" content=\"no-cache\" />"
            //        + "<meta http-equiv=\"expires\" content=\"0\" />"
            //        + "<meta http-equiv=\"expires\" content=\"Tue, 01 Jan 1980 1:00:00 GMT\" />"
            //        + "<meta http-equiv=\"pragma\" content=\"no-cache\" />");
            out.println("<title>LabelMaker</title>");
            out.println("<link rel=\"SHORTCUT ICON\" href=\"/EnterpriseApps/images/abstergo.png\">");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/EnterpriseApps/styles/style_7_10_2014.css\">");
            out.println("<link href=\"http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic\" rel=\"stylesheet\" type=\"text/css\">");
            out.println("<script src=\"//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script>");
            out.println("<script src=\"/EnterpriseApps/scripts/script_7_10_2014.js\"></script>");
            out.println("</head>");
            out.println("<body onload=\"document.body.classList.add('loaded')\">");
            out.println("<div class=\"slideout-widget\">"
                    + "<div class=\"slideout-widget widget-facebook\">"
                    + "<div class=\"slideout-widget-handler\">"
                    + "<img src=\"/EnterpriseApps/images/fb-modern.png\" alt=\"fb\" class=\"icon-facebook\"/>"
                    + "</div>"
                    + "<div class=\"slideout-widget-content\" onclick=\"document.getElementById('ID_FBLink').click()\">"
                    + "<div style=\"text-align:left;\"><a id=\"ID_FBLink\" href=\"https://www.facebook.com/EnterpriseAppsVN\" target=\"_blank\" style=\"position:absolute;\">EA @ Facebook &nbsp;</a>"
                    + "</div>"
                    + "<div class=\"fbLikeShareDiv\">"
                    + "<iframe src=\"//www.facebook.com/plugins/like.php?href=https%3A%2F%2Fwww.facebook.com%2FEnterpriseAppsVN&amp;width&amp;layout=button_count&amp;action=like&amp;show_faces=true&amp;share=true&amp;height=21px\" "
                    + "class=\"fbLikeShareFrame\">"
                    + "</iframe>"
                    + "</div>"
                    + "</div>"
                    + "</div>"
                    
                    + "<div class=\"slideout-widget widget-twitter\">"
                    + "<div class=\"slideout-widget-handler\">"
                    + "<img src=\"/EnterpriseApps/images/twitter-modern.png\" alt=\"fb\" class=\"icon-twitter\"/>"
                    + "</div>"
                    + "<div class=\"slideout-widget-content\" onclick=\"document.getElementById('ID_TWLink').click()\">"
                    + "<div style=\"text-align:left;\"><a id=\"ID_TWLink\" href=\"https://www.twitter.com/e_appsvn\" target=\"_blank\" style=\"position:absolute;\">EA @ Twitter &nbsp;</a>"
                    + "</div>"                    
                    + "</div>"
                    + "</div>"
                    
                    + "</div>"
                    + "");
            out.println("<div class=\"header\">Label Maker&#8480;"
                    + "<div id=\"clockbox\" class=\"clock\"></div>"
                    + "<div id=\"goback\" class=\"backbtndiv\" onclick=\"moveBack()\">"
                    + "<img src=\"/EnterpriseApps/images/leftarrow.png\" alt=\"Back to Welcome Page\" class=\"backbtn\"/>"
                    + "</div>"
                    + "<div id=\"ID_flag\" class=\"languageflagdiv\">"
                    + "<img src=\"/EnterpriseApps/images/english_flag.png\" alt=\"English\" class=\"langimg\"/>"
                    + "</div>"
                    + "</div>");
            out.println("<div class=\"headersmall\">E &nbsp;&nbsp; T &nbsp;&nbsp; P &nbsp;&nbsp; C &nbsp;&nbsp; Q &nbsp;&nbsp; U &nbsp;&nbsp;&nbsp;&nbsp; F E A T U R E S</div>");
            out.println("<form method=\"POST\" action=\"/EnterpriseApps/LabelMaker/PreviewPDF\" accept-charset=\"UTF-8\" name=\"FForm\" target=\"pdfFrame\">"
                    + "<div id=\"ID_formSection\" class=\"formsection\">"
                    + "<table id=\"ID_formTable\" class=\"tablemain\">"
                    + "<tr>"
                    + "<td>"
                    + "<div class=\"label\">"
                    + labels.getEAN()
                    + "</div>"
                    + "</td>"
                    + "<td>"
                    + "<input type=\"text\" name=\"EAN\" size=\"50\" class=\"textinputdisabled\" maxlength=\"13\" disabled/><br/>"
                    + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>"
                    + "<div class=\"label\" title=\"Enterproduct Name\">"
                    + labels.getTitle()
                    + "</div>"
                    + "</td>"
                    + "<td>"
                    + "<input type=\"text\" name=\"TITLE\" size=\"60\" id=\"ID_titleInput\" class=\"textinput\" onkeypress=\"clearError()\" "
                    + "maxlength=\""
                    //+ PDFDesign.calculateMaxInputLength()
                    + "22"
                    + "\"/><br/>"
                    + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>"
                    + "<div class=\"label\">"
                    + labels.getPrice()
                    + "</div>"
                    + "</td>"
                    + "<td>"
                    + "<input type=\"text\" name=\"PRICE\" id=\"ID_price\" class=\"textinput\" onkeypress=\"clearError()\" maxlength=\"5\" onblur=\"refillValueOf(document.getElementById('ID_price'))\" /><br/>"
                    + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>"
                    + "<div class=\"label\">"
                    + labels.getCurrency()
                    + "</div>"
                    + "</td>"
                    + "<td><table class =\"subtable\"><tr>"
                    + "<td class =\"subtd\">"
                    + "<input id=\"ID_radio1_1\" class =\"radiostyle\" type=\"radio\" name=\"CURR\" value=\"Kc\" checked>"
                    + "<label for=\"ID_radio1_1\" class=\"radiostyle-label radGroup1\">Kč &nbsp;</label>"
                    + "</td>"
                    + "<td class =\"subtd\">"
                    + "<input id=\"ID_radio1_2\" class =\"radiostyle\" type=\"radio\" name=\"CURR\" value=\"Sk\">"
                    + "<label for=\"ID_radio1_2\" class=\"radiostyle-label radGroup1\">Sk &nbsp;</label>"
                    + "</td>"
                    + "<td class =\"subtd\">"
                    + "<input id=\"ID_radio1_3\" class =\"radiostyle\" type=\"radio\" name=\"CURR\" value=\"Eur\">"
                    + "<label for=\"ID_radio1_3\" class=\"radiostyle-label radGroup1\">&euro; &nbsp;</label>"
                    + "</td>"
                    + "</tr></table>"
                    + "</td></tr>"
                    + "<tr>"
                    + "<td>"
                    + "<div class=\"label\">"
                    + labels.getAmount()
                    + "</div>"
                    + "</td>"
                    + "<td>"
                    + "<input type=\"text\" name=\"AMOUNT\" id=\"ID_amount\" class=\"textinput\" onkeypress=\"clearError()\" maxlength=\"5\"/>"
                    + "</td></tr>"
                    + "<tr>"
                    + "<td>"
                    + "<div class=\"label\">"
                    + labels.getUnit()
                    + "</div>"
                    + "</td>"
                    + "<td>"
                    + "<table class =\"subtable\"><tr>"
                    + "<td class =\"subtd\">"
                    + "<input id=\"ID_radio2_1\" class =\"radiostyle\" type=\"radio\" name=\"UNIT\" value=\"kg\" checked>"
                    + "<label for=\"ID_radio2_1\" class=\"radiostyle-label radGroup2\">kg &nbsp;</label>"
                    + "</td>"
                    + "<td class =\"subtd\">"
                    + "<input id=\"ID_radio2_2\" class =\"radiostyle\" type=\"radio\" name=\"UNIT\" value=\"g\">"
                    + "<label for=\"ID_radio2_2\" class=\"radiostyle-label radGroup2\">g &nbsp;</label>"
                    + "</td>"
                    + "<td class =\"subtd\">"
                    + "<input id=\"ID_radio2_3\" class =\"radiostyle\" type=\"radio\" name=\"UNIT\" value=\"l\">"
                    + "<label for=\"ID_radio2_3\" class=\"radiostyle-label radGroup2\">l &nbsp;</label>"
                    + "</td>"
                    + "<td class =\"subtd\">"
                    + "<input id=\"ID_radio2_4\" class =\"radiostyle\" type=\"radio\" name=\"UNIT\" value=\"cl\">"
                    + "<label for=\"ID_radio2_4\" class=\"radiostyle-label radGroup2\">cl &nbsp;</label>"
                    + "</td>"
                    + "<td class =\"subtd\">"
                    + "<input id=\"ID_radio2_5\" class =\"radiostyle\" type=\"radio\" name=\"UNIT\" value=\"ml\">"
                    + "<label for=\"ID_radio2_5\" class=\"radiostyle-label radGroup2\">ml &nbsp;</label>"
                    + "</td>"
                    + "<td class =\"subtd\">"
                    + "<input id=\"ID_radio2_6\" class =\"radiostyle\" type=\"radio\" name=\"UNIT\" value=\"ks\">"
                    + "<label for=\"ID_radio2_6\" class=\"radiostyle-label radGroup2\">ks &nbsp;</label>"
                    + "</td>"
                    + "</tr></table>"
                    + "</td></tr>"
                    + "</table></div>"
                    /* Start Size Chooser */
                    + "<div class=\"slideout\" id=\"ID_stylechoosersection\">"
                    + "<img src=\"/EnterpriseApps/images/options.png\" alt=\"opts\"/>"
                    + "<div class=\"slideout_inner\" id=\"ID_innerstylechooser\">"
                    + "<table>"
                    + "<tr>"
                    //+ "<td>"
                    //+ labels.getSize()
                    //+ "</td>"
                    + "<td>"
                    + "<input id=\"ID_radio4_1\" class =\"radiostyle\" type=\"radio\" name=\"SIZE\" value=\"3x5\" onclick=\"changeInputMaxLengthOf(22)\" checked>"
                    + "<label for=\"ID_radio4_1\" class=\"radiostyle-label radGroup4\">3x5cm &nbsp;</label>"
                    + "</td>"
                    + "<td>"
                    + "<input id=\"ID_radio4_2\" class =\"radiostyle\" type=\"radio\" name=\"SIZE\" value=\"3.5x5\" onclick=\"changeInputMaxLengthOf(22)\">"
                    + "<label for=\"ID_radio4_2\" class=\"radiostyle-label radGroup4\">3.5x5cm &nbsp;</label>"
                    + "</td>"
                    + "<td>"
                    + "<input id=\"ID_radio4_3\" class =\"radiostyle\" type=\"radio\" name=\"SIZE\" value=\"3.5x6\" onclick=\"changeInputMaxLengthOf(22)\">"
                    + "<label for=\"ID_radio4_3\" class=\"radiostyle-label radGroup4\">3.5x6cm &nbsp;</label>"
                    + "</td>"
                    + "<td>"
                    + "<input id=\"ID_radio4_9\" class =\"radiostyle\" type=\"radio\" name=\"SIZE\" value=\"3.5x7\" onclick=\"changeInputMaxLengthOf(40)\">"
                    + "<label for=\"ID_radio4_9\" class=\"radiostyle-label radGroup4\">3.5x7cm &nbsp;</label>"
                    + "</td>" 
                    + "<td>" 
                    + "<input id=\"ID_radio4_4\" class =\"radiostyle\" type=\"radio\" name=\"SIZE\" value=\"4x6\" onclick=\"changeInputMaxLengthOf(40)\">"
                    + "<label for=\"ID_radio4_4\" class=\"radiostyle-label radGroup4\">4x6cm &nbsp;</label>"
                    + "</td>"
                    + "<td>"
                    + "<input id=\"ID_radio4_5\" class =\"radiostyle\" type=\"radio\" name=\"SIZE\" value=\"4x7\" onclick=\"changeInputMaxLengthOf(40)\">"
                    + "<label for=\"ID_radio4_5\" class=\"radiostyle-label radGroup4\">4x7cm &nbsp;</label>"                    
                    + "</td>"
                    + "<td>"
                    + "<input id=\"ID_radio4_6\" class =\"radiostyle\" type=\"radio\" name=\"SIZE\" value=\"5x6\" onclick=\"changeInputMaxLengthOf(40)\">"
                    + "<label for=\"ID_radio4_6\" class=\"radiostyle-label radGroup4\">5x6cm &nbsp;</label>"                    
                    + "</td>"
                    + "<td>"
                    + "<input id=\"ID_radio4_7\" class =\"radiostyle\" type=\"radio\" name=\"SIZE\" value=\"5x7\" onclick=\"changeInputMaxLengthOf(40)\">"
                    + "<label for=\"ID_radio4_7\" class=\"radiostyle-label radGroup4\">5x7cm &nbsp;</label>"                    
                    + "</td>"
                    + "<td>"
                    + "<input id=\"ID_radio4_8\" class =\"radiostyle\" type=\"radio\" name=\"SIZE\" value=\"6x7\" onclick=\"changeInputMaxLengthOf(40)\">"
                    + "<label for=\"ID_radio4_8\" class=\"radiostyle-label radGroup4\">6x7cm &nbsp;</label>"                    
                    + "</td>"
                    + "</tr>"
                    + "<tr>"
                    //+ "<td>"
                    //+ labels.getColor()
                    //+ "</td>"
                    + "<td>"
                    + "<input id=\"ID_radio5_1\" class =\"radiostyle\" type=\"radio\" name=\"COLOR\" value=\"black\" checked>"
                    + "<label for=\"ID_radio5_1\" class=\"radiostyle-label radGroup5\">"
                    + labels.getBlack()
                    + " &nbsp;</label>"
                    + "</td>"
                    + "<td>"
                    + "<input id=\"ID_radio5_2\" class =\"radiostyle\" type=\"radio\" name=\"COLOR\" value=\"gray\">"
                    + "<label for=\"ID_radio5_2\" class=\"radiostyle-label radGroup5\">"
                    + labels.getGray()
                    + " &nbsp;</label>"
                    + "</td>"
                    + "<td>"
                    + "<input id=\"ID_radio5_3\" class =\"radiostyle\" type=\"radio\" name=\"COLOR\" value=\"red\">"
                    + "<label for=\"ID_radio5_3\" class=\"radiostyle-label radGroup5\">"
                    + labels.getRed()
                    + " &nbsp;</label>"
                    + "</td>"
                    + "<td>"
                    + "<input id=\"ID_radio5_4\" class =\"radiostyle\" type=\"radio\" name=\"COLOR\" value=\"green\">"
                    + "<label for=\"ID_radio5_4\" class=\"radiostyle-label radGroup5\">"
                    + labels.getGreen()
                    + " &nbsp;</label>"
                    + "</td>"
                    + "<td>"
                    + "<input id=\"ID_radio5_5\" class =\"radiostyle\" type=\"radio\" name=\"COLOR\" value=\"blue\">"
                    + "<label for=\"ID_radio5_5\" class=\"radiostyle-label radGroup5\">"
                    + labels.getBlue()
                    + " &nbsp;</label>"
                    + "</td><td></td><td></td><td></td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>"
                    + "<input id=\"ID_radio3_1\" class =\"radiostyle\" type=\"radio\" name=\"STYLE\" value=\"style1\" onclick=\"changeStyleImage(this.value)\" checked>"
                    + "<label for=\"ID_radio3_1\" class=\"radiostyle-label radGroup3\">#1 &nbsp;</label>"
                    + "</td>"
                    + "<td>"
                    + "<input id=\"ID_radio3_2\" class =\"radiostyle\" type=\"radio\" name=\"STYLE\" value=\"style2\" onclick=\"changeStyleImage(this.value)\">"
                    + "<label for=\"ID_radio3_2\" class=\"radiostyle-label radGroup3\">#2 &nbsp;</label>"
                    + "</td>"
                    + "<td>"
                    + "<input id=\"ID_radio3_3\" class =\"radiostyle\" type=\"radio\" name=\"STYLE\" value=\"style3\" onclick=\"changeStyleImage(this.value)\">"
                    + "<label for=\"ID_radio3_3\" class=\"radiostyle-label radGroup3\">#3 &nbsp;</label>"
                    + "</td>"
                    + "<td>"
                    + "<input id=\"ID_radio3_4\" class =\"radiostyle\" type=\"radio\" name=\"STYLE\" value=\"style4\" onclick=\"changeStyleImage(this.value)\">"
                    + "<label for=\"ID_radio3_4\" class=\"radiostyle-label radGroup3\">#4 &nbsp;</label>"
                    + "</td>"
                    + "<td>"
                    + "<input id=\"ID_radio3_5\" class =\"radiostyle\" type=\"radio\" name=\"STYLE\" value=\"style5\" onclick=\"changeStyleImage(this.value)\">"
                    + "<label for=\"ID_radio3_5\" class=\"radiostyle-label radGroup3\">#5 &nbsp;</label>"
                    + "</td>"
                    + "<td>"
                    + "<input id=\"ID_radio3_6\" class =\"radiostyle\" type=\"radio\" name=\"STYLE\" value=\"style6\" onclick=\"changeStyleImage(this.value)\">"
                    + "<label for=\"ID_radio3_6\" class=\"radiostyle-label radGroup3\">#6 &nbsp;</label>"
                    + "</td><td></td><td></td>"
                    + "</tr>"
                    /*+ "<tr>"
                    + "<td>"
                    + "<input id=\"ID_radio3_6\" class =\"radiostyle\" type=\"radio\" name=\"STYLE\" value=\"style6\" onclick=\"changeStyleImage(this.value)\">"
                    + "<label for=\"ID_radio3_6\" class=\"radiostyle-label radGroup3\">#6 &nbsp;</label>"
                    + "</td>"
                    + "<td>"
                    //+ "<input id=\"ID_radio3_2\" class =\"radiostyle\" type=\"radio\" name=\"STYLE\" value=\"style7\" onclick=\"changeStyleImage(this.value)\">"
                    //+ "<label for=\"ID_radio3_2\" class=\"radiostyle-label radGroup3\">#7 &nbsp;</label>"
                    + "</td>"
                    + "<td>"
                    //+ "<input id=\"ID_radio3_3\" class =\"radiostyle\" type=\"radio\" name=\"STYLE\" value=\"style8\" onclick=\"changeStyleImage(this.value)\">"
                    //+ "<label for=\"ID_radio3_3\" class=\"radiostyle-label radGroup3\">#8 &nbsp;</label>"
                    + "</td>"
                    + "<td>"
                    //+ "<input id=\"ID_radio3_4\" class =\"radiostyle\" type=\"radio\" name=\"STYLE\" value=\"style9\" onclick=\"changeStyleImage(this.value)\">"
                    //+ "<label for=\"ID_radio3_4\" class=\"radiostyle-label radGroup3\">#9 &nbsp;</label>"
                    + "</td>"
                    + "<td>"
                    //+ "<input id=\"ID_radio3_5\" class =\"radiostyle\" type=\"radio\" name=\"STYLE\" value=\"style10\" onclick=\"changeStyleImage(this.value)\">"
                    //+ "<label for=\"ID_radio3_5\" class=\"radiostyle-label radGroup3\">#10 &nbsp;</label>"
                    + "</td>"
                    + "</tr>"*/
                    + "</table>"
                    + "<div id=\"ID_styleImageDiv\" class=\"styleimagediv\">"
                    + "<img id=\"ID_styleImageFrame\" class=\"styleimageframe\" alt=\"styleimage\""
                    + " src=\"/EnterpriseApps/images/style1.png\"/>"
                    + "</div></div>"
                    + "</div>"
                    /* End Size Chooser*/
                    + "<div class=\"center\">"
                    + "<input id=\"ID_addToListButton\" type=\"button\" value=\""
                    + labels.getAddToList()
                    + "\" class=\"btn\" onclick=\"addToList()\" />"
                    // + "<input id=\"ID_chooseStyle\" type=\"button\" value=\""
                    //+ labels.getChooseStyle()
                    //+ "\" class=\"btn\" onclick=\"showStyleChooser()\" />"
                    + "<input id=\"ID_plainTextModeButton\" type=\"button\" value=\""
                    + labels.getPlainTextModeButton()
                    + "\" class=\"btn\" onclick=\"toggleTextEditMode()\" "
                    // + "disabled"
                    + "/>"
                    + "</div>"
                    + "<div class=\"txtareasection\" id=\"ID_textAreaTableSection\">"
                    + "<table id=\"ID_table\" class=\"tg\">"
                    + "<colgroup>"
                    + "<col style=\"width: 5%\">"
                    + "<col style=\"width: 10%\">"
                    + "<col style=\"width: 30%\">"
                    + "<col style=\"width: 15%\">"
                    + "<col style=\"width: 10%\">"
                    + "<col style=\"width: 15%\">"
                    + "<col style=\"width: 15%\">"
                    + "</colgroup>"
                    + "<tr>"
                    + "<th>#</th>"
                    + "<th>EAN</th>"
                    + "<th>" + labels.getTitle() + "</th>"
                    + "<th>" + labels.getPrice() + "</th>"
                    + "<th>" + labels.getCurrency() + "</th>"
                    + "<th>" + labels.getAmount() + "</th>"
                    + "<th>" + labels.getUnit() + "</th>"
                    + " </tr>"
                    + "</table>"
                    + "<div>"
                    + "<textarea class=\"txtarea\" id=\"ID_textArea\" onkeypress=\"clearError()\"></textarea>"
                    + "<div class=\"dropsection\" id=\"ID_dropsection\">"
                    + "<div class=\"dropzone\" id=\"ID_dropzone\">"
                    + labels.getDragDrop()
                    + "</div>"
                    + "</div>"
                    + "<script>"
                    + "initDragDrop();"
                    + "</script>"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"center\">"
                    + "<input id=\"ID_removeLastRowButton\" type=\"button\" value=\""
                    + labels.getRemoveLastRow()
                    + "\" class=\"btn\" onclick=\"deleteLastRow()\"/>"
                    + "<input type=\"button\" value=\""
                    + labels.getPreviewInPDF()
                    + "\" class=\"btn\" onclick=\"previewPDF()\" />"
                    + "<input type=\"button\" value=\""
                    + labels.getDownloadText()
                    + "\" class=\"btn\" onclick=\"downloadText()\" />"
                    + "</div>"
                    + "<a id=\"ID_scroll2PDFWindow\" style=\"display:none;\" href=#ID_pdfWindow>Scroll2PDFWindow</a>"
                    + "<a id=\"ID_scroll2AddButton\" style=\"display:none;\" href=#ID_titleInput>Scroll2AddButton</a>"
                    + "<input type=\"hidden\" name=\"TS\" id=\"ID_dataToSubmit\"/>"
                    + "</form>"
                    + "<iframe id=\"ID_pdfWindow\" name=\"pdfFrame\" class=\"pdfframe\">"
                    + "</iframe>"
                    + "");

            out.println("<div class=\"footerdiv\">"
                    + "<footer>"
                    + "<div class=\"leftfoot\">LabelMaker &copy; 2014 Nguyen Viet Bach | Powered by iText, GlassFish, Jelastic "
                    + "</div>"
                    + "<div class=\"rightfoot\">"
                    + currentUserAccessedWithinHisSession //+ " | " + totalTimesRequested +" | " + instances.size()
                    + "</div></footer>"
                    + "</div>");
            out.println("</body>");
            out.println("</html>");
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
