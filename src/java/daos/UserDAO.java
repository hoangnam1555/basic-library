/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dbUntils.DBConnection;
import dtos.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class UserDAO {

    public static UserDTO checkLogin(String userID, String password) throws SQLException {
        String fullName = "";
        String roleID = "";
        UserDTO dto = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            if (con != null) {
                String sql = "SELECT s.FullName,s.RoleID\n"
                        + "FROM tblUsers s\n"
                        + "WHERE s.UserID = ? and s.Password = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, userID);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    fullName = rs.getString("Fullname");
                    roleID = rs.getString("RoleID");
                    dto = new UserDTO(userID, fullName, password, roleID);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }

    public static ArrayList<UserDTO> getList(String search) throws SQLException {
        ArrayList<UserDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            if (con != null) {
                String sql = "SELECT s.UserID,s.FullName,s.RoleID\n"
                        + "FROM tblUsers s\n"
                        + "WHERE s.FullName LIKE '%" + search + "%'";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("UserID");
                    String fullName = rs.getString("FullName");
                    String password = "***";
                    String roleID = rs.getString("RoleID");
                    list.add(new UserDTO(userID, fullName, password, roleID));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public void update(UserDTO dto) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblUsers\n"
                        + "                         SET FullName = ?\n"
                        + "                        WHERE UserID = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, dto.getFullName());
                pst.setString(2, dto.getRoleID());
                pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    public void delete(String userID) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = "DELETE tblUsers"
                        + " WHERE UserID = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, userID);
                pst.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean checkID(String userID)
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            if (con != null) {
                String sql = "SELECT FullName FROM tblUsers WHERE\n"
                        + "                        UserID = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, userID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public void insert(UserDTO dto) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblUSers( UserID,RoleID,FullName,Password )"
                        + " VALUES(?,?,?,?) ";

                pst = conn.prepareStatement(sql);
                pst.setString(1, dto.getUserId());
                pst.setString(2, dto.getRoleID());
                pst.setString(3, dto.getFullName());
                pst.setString(4, dto.getPassword());
                pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
