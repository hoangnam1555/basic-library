/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dtos.UserErrorDTO;
import daos.UserDAO;
import dtos.UserDTO;
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
public class CreateUserController extends HttpServlet {

    private static final String SUCCESS = "login.html";
    private static final String ERROR = "createNewUser.jsp";

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
                    String userID = request.getParameter("txtUserID");
                    String fullName = request.getParameter("txtFullName");
                    String roleID = "user";
                    String password = request.getParameter("txtPassword");
                    String rePassword = request.getParameter("txtRePassword");
                    boolean check = true;
                    UserErrorDTO errorDTO = new UserErrorDTO();
                    if (userID.isEmpty()) {
                        errorDTO.setUserIDError("User ID is not empty");
                        check = false;
                    }
                    if (fullName.isEmpty() || fullName.length() < 2 || fullName.length() > 8) {
                        errorDTO.setFullNameError("8 > Full Name > 2 !");
                        check = false;
                    }
                    if (roleID.isEmpty()) {
                        errorDTO.setRoleIDError("Role ID is not empty");
                        check = false;
                    } if (roleID.equals("ad") || roleID.equals("admin")) {
                        errorDTO.setRoleIDError("no permission to create role Admin");
                        check = false;
                    }
                    if (!password.equals(rePassword)) {
                        errorDTO.setRePasswordError("password is not matched");
                        check = false;
                    }
                    UserDAO dao = new UserDAO();

                    if (dao.checkID(userID)) {
                        errorDTO.setUserIDError("User ID da ton tai");
                        check = false;
                    }
                    if (check == true) {
                        UserDTO dto = new UserDTO(userID, fullName, password, roleID);
                        dao.insert(dto);
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR", errorDTO);
                    }
                } catch (Exception e) {
                    log("error at Create User Controller" + e.toString());

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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
        
            () {
        return "Short description";
        }// </editor-fold>

    }
