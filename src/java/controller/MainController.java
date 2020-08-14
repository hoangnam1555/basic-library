/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
public class MainController extends HttpServlet {

    private final String LOGIN = "LoginController";
    private final String ERROR = "login.html";
    private final String SEARCH = "SearchController";
    private final String LOGOUT = "LogoutController";
    private final String DELETE = "DeleteController";
    private final String UPDATE_PAGE = "update.jsp";
    private final String UPDATE = "UpdateController";
    private final String CREATE = "CreateUserController";
    private final String ADD_BOOK = "AddBookController";
    private final String VIEW = "view.jsp";
    private final String CHECKOUT = "CheckOutController";
    private final String UPDATE_BOOK = "UpdateBookController";
    private final String DELETE_BOOK = "DeleteBookController";
    private final String CREATE_BOOK = "CreateBookController";

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
                String action = request.getParameter("btnAction");
                if (action.equals("login")) {
                    url = LOGIN;
                } else if (action.equals("search")) {
                    url = SEARCH;
                } else if (action.equals("logout")) {
                    url = LOGOUT;
                } else if (action.equals("delete")) {
                    url = DELETE;
                } else if (action.equals("update page")) {
                    url = UPDATE_PAGE;
                } else if (action.equals("update")) {
                    url = UPDATE;
                } else if (action.equals("create")) {
                    url = CREATE;
                } else if (action.equals("add")) {
                    url = ADD_BOOK;
                } else if (action.equals("view")) {
                    url = VIEW;
                } else if (action.equals("CheckOut")) {
                    url = CHECKOUT;
                } else if (action.equals("deleteBook")) {
                    url = DELETE_BOOK;
                } else if (action.equals("updateBook")) {
                    url = UPDATE_BOOK;
                }else if (action.equals("createBook")) {
                    url = CREATE_BOOK;
                }

            } catch (Exception e) {
                log("ERROR at MainController: " + e.toString());
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
