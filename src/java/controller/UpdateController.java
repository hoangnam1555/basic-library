/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.BookDAO;
import dtos.BookDTO;
import dtos.BookErrorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class UpdateController extends HttpServlet {

    private static final String ERROR = "update.jsp";
    private static final String SUCCESS = "SearchController";

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = ERROR;
            BookErrorDTO errorDTO = new BookErrorDTO();
            boolean check = true;
            try {
                String bookID = request.getParameter("txtBookID");
                String quantity = request.getParameter("txtQuantity");

                String title = request.getParameter("txtTitle");
                String price = request.getParameter("txtPrice");
                String author = request.getParameter("txtAuthor");
                 String status = request.getParameter("txtStatus");

                if (bookID.isEmpty()) {
                    errorDTO.setErrorBookID("Book ID is not empty");
                    check = false;
                }
                if (title.isEmpty() || title.length() < 2 || title.length() > 20) {
                    errorDTO.setErrorBookTitle("20 > Title Book > 2 !");
                    check = false;
                }

                if (author.isEmpty() || author.length() < 2) {
                    errorDTO.setErrorBookAuthor("Author Name > 2 !");
                    check = false;
                }
                if (status.isEmpty()) {
                    errorDTO.setErrorStatus("status can't empty");
                    check = false;
                }

                try {
                    Integer.parseInt(price.trim());
                } catch (Exception e) {
                    errorDTO.setErrorBookPrice("price must be a number");
                    request.setAttribute("ERROR_BOOK", errorDTO);
                }
                try {
                    Integer.parseInt(quantity.trim());
                } catch (Exception e) {
                    errorDTO.setErrorBookQuantity("quantity must be a number");
                    request.setAttribute("ERROR_BOOK", errorDTO);
                }
                try {
                    Integer.parseInt(status.trim());
                } catch (Exception e) {
                    errorDTO.setErrorStatus("status  must be 1(valid) or 0(invalid)");
                    request.setAttribute("ERROR_BOOK", errorDTO);
                }
                if (quantity.isEmpty() || Integer.parseInt(quantity.trim()) < 0) {
                    errorDTO.setErrorBookQuantity("quantity can't empty or lower than 0");
                    check = false;
                }
                if (price.isEmpty() || Integer.parseInt(price.trim()) < 0) {
                    errorDTO.setErrorBookPrice("price can't empty or lower than 0");
                    check = false;
                }
                if (!(Integer.parseInt(status.trim()) == 0 || Integer.parseInt(status.trim()) == 1)) {
                    errorDTO.setErrorStatus("status  must be 1(valid) or 0(invalid)");
                    check = false;
                }
                if (check) {
                    BookDTO book;
                    BookDAO dao = new BookDAO();
                    book = new BookDTO(bookID, title, Integer.parseInt(price.trim()),
                            Integer.parseInt(quantity.trim()), author, Integer.parseInt(status.trim()));
                    dao.update(book);
                    url = SUCCESS;
                }else {
                    request.setAttribute("ERROR_BOOK", errorDTO);
                    url = ERROR;
                }

            } catch (Exception e) {
                log("error at updateController" + e.toString());
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
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
