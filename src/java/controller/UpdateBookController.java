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
public class UpdateBookController extends HttpServlet {

    private static final String ERROR = "view.jsp";
    private static final String SUCCESS = "view.jsp";
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
        try {
            String id = request.getParameter("txtBookID");
            String quantity = request.getParameter("txtQuantity");
            HttpSession session = request.getSession();
            cartDTO cart = (cartDTO) session.getAttribute("CART");
            BookDTO book = null;
            if (Integer.parseInt(quantity) > 0) {
                for (BookDTO dto : cart.getItems().values()) {
                    if (dto.getBookID().equals(id)) {
                        book = new BookDTO(dto.getBookID(), dto.getTitle(), dto.getPrice(), Integer.parseInt(quantity.trim()
                        ), dto.getAuthor(), dto.getStatus());
                    }
                }
                cart.update(id, book);
                session.setAttribute("CART", cart);
                url = SUCCESS;
            }else{
                String error = "error :quantity must > 0 ";
                request.setAttribute("MESSAGE", error);
            }
        } catch (Exception e) {
            log("error at Update Book Controller" + e.toString());
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
