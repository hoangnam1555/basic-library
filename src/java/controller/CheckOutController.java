/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.BookDAO;
import daos.OrderDAO;
import dbUntils.Utils;
import dtos.BookDTO;
import dtos.OrderDTO;
import dtos.UserDTO;
import dtos.cartDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
public class CheckOutController extends HttpServlet {

    private static final String SUCCESS = "view.jsp";
    private static final String ERROR = "view.jsp";

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = ERROR;
            String total = request.getParameter("txtTotal");
            BookDAO bookDAO = new BookDAO();
            long getDate = System.currentTimeMillis();
            long returnDate = getDate + 1209600000;
            java.sql.Date date = new java.sql.Date(getDate);
            java.sql.Date date2 = new java.sql.Date(returnDate);
            String error = "";
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            cartDTO cart = (cartDTO) session.getAttribute("CART");
            Map<String, BookDTO> items = cart.getItems();
            String orderID = Utils.randomAlphaNumeric(10);
            OrderDTO orderDTO = new OrderDTO(orderID, user.getUserId(), date, date2, Integer.parseInt(total));
            boolean check3 = false;
            try {
                for (BookDTO book : items.values()) {
                    String bookID = book.getBookID();
                    int quantity = book.getQuantity();
                    if (bookDAO.getQuantity(bookID) >= quantity) {
                    } else {
                        error = "Quantity of book " + book.getTitle() + " is too much, please choose quantity lower than " + book.getQuantity();
                        request.setAttribute("MESSAGE", error);
                    }
                    boolean check = OrderDAO.checkQuantity(book, bookDAO.getQuantity(bookID));
                    if(!check){
                       int tmp = OrderDAO.getQuantityDetail(book, bookDAO.getQuantity(bookID));
                                int total2 = bookDAO.getQuantity(bookID) - tmp;
                                if (total2 > 0) {
                                    error = "pls choose quantity of " + book.getTitle() + " lower or equal " + total2;
                                } else {
                                    error = "Quantity of book " + book.getTitle() + " is empty";
                                }
                        request.setAttribute("MESSAGE", error);
                    }
                }
                String msg = (String) request.getAttribute("MESSAGE");
                if (msg == null || msg.isEmpty()) {
                    for (BookDTO dto : items.values()) {
                        String bookID = dto.getBookID();
                        int quantity = dto.getQuantity();
                        if (bookDAO.getQuantity(bookID) >= quantity) {
                        } else {
                            error = "Quantity of book " + dto.getTitle() + " is too much, please choose quantity lower than " + dto.getQuantity();
                            request.setAttribute("MESSAGE", error);
                        }

                        msg = (String) request.getAttribute("MESSAGE");
                        if (msg == null || msg.isEmpty()) {
                            boolean check = OrderDAO.checkQuantity(dto, bookDAO.getQuantity(bookID));
                            if (check) {
                                boolean check2 = OrderDAO.insertOrder(orderDTO);
                                if (check2) {
                                    check3 = OrderDAO.checkOut(orderID, items);
                                    if (check3) {
                                        url = SUCCESS;
                                        session.removeAttribute("CART");
                                    }
                                }
                            } else {
                                int tmp = OrderDAO.getQuantityDetail(dto, bookDAO.getQuantity(bookID));
                                int total2 = bookDAO.getQuantity(bookID) - tmp;
                                if (total2 > 0) {
                                    error = "pls choose lower or equal " + total2;
                                } else {
                                    error = "Quantity of book " + dto.getTitle() + " is empty";
                                }

                                request.setAttribute("MESSAGE", error);
                            }
                        }
                    }
                }
            } catch (NumberFormatException e) {
                log("Error at CheckoutController " + e.toString());
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
