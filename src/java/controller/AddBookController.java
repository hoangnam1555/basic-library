/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dtos.BookDTO;
import dtos.cartDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
public class AddBookController extends HttpServlet {

    private static final String SUCCESS = "library.jsp";
    private static final String ERROR = "error.html";

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

            String url = ERROR;
            try {
                String bookID = request.getParameter("txtBookID");
                String title = request.getParameter("txtTitle");
                String price = request.getParameter("txtPrice");
                String quantity = request.getParameter("txtQuantity");
                String author = request.getParameter("txtAuthor");
                String status = "1";
                BookDTO book = new BookDTO(bookID, title, Integer.parseInt(price.trim()), Integer.parseInt(quantity.trim()), 
                        author,Integer.parseInt(status.trim()));
                HttpSession session = request.getSession();
                cartDTO cart = (cartDTO) session.getAttribute("CART");
                if (cart == null) {
                    cart = new cartDTO(null);
                } 
                    cart.add(book);
                    session.setAttribute("CART", cart);
                    url = SUCCESS;
                    request.setAttribute("message", "ban da them thanh cong " + title + " vao gio hang");
   
            } catch (Exception e) {
                log("error at add book Controller" + e.toString());
 
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
