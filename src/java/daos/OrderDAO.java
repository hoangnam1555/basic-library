/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dbUntils.DBConnection;
import dtos.BookDTO;
import dtos.OrderDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author USER
 */
public class OrderDAO {

    public static boolean insertOrder(OrderDTO dto) throws SQLException {

        boolean check = false;
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblOrder(orderID,userID,[getDate],returnDate,total) "
                        + "values (?,?,?,?,?)\n";

                pst = conn.prepareStatement(sql);
                pst.setString(1, dto.getOrderID());
                pst.setString(2, dto.getUserID());
                pst.setDate(3, dto.getDate());
                pst.setDate(4, dto.getReturnDate());
                pst.setInt(5, dto.getTotal());
                int r = pst.executeUpdate();
                if (r > 0) {
                    check = true;
                }
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
        return check;
    }

    public static boolean checkOut(String id, Map<String, BookDTO> cart) throws SQLException {
        boolean check = false;
        Connection conn = null;
        int row = 0;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblOrderDetail(bookID,orderID,quantity,price) values (?,?,?,?) ";

                pst = conn.prepareStatement(sql);
                for (String items : cart.keySet()) {
                    int n = cart.get(items).getPrice() * cart.get(items).getQuantity();
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, items);
                    pst.setString(2, id);
                    pst.setInt(3, cart.get(items).getQuantity());
                    pst.setInt(4, n);

                    row = pst.executeUpdate();
                    if (row > 0) {
                        check = true;
                    }
                }
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
        return check;
    }

    public static boolean checkQuantity(BookDTO bookDto, int bookQuantity) throws SQLException {
        boolean check = false;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        int quantity = 0;
        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = "SELECT SUM(quantity) quantity\n"
                        + "FROM tblOrderDetail\n"
                        + "WHERE bookID = ?";

                pst = conn.prepareStatement(sql);
                pst.setString(1, bookDto.getBookID());
                rs = pst.executeQuery();
                while (rs.next()) {
                    quantity += rs.getInt("quantity");
                }
                int total = quantity + bookDto.getQuantity();
                if (bookQuantity >= total) {
                    check = true;
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
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public static int getQuantityDetail(BookDTO bookDto, int bookQuantity) throws SQLException {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        int quantity = 0;
        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = "SELECT SUM(quantity) quantity\n"
                        + "FROM tblOrderDetail\n"
                        + "WHERE bookID = ?";

                pst = conn.prepareStatement(sql);
                pst.setString(1, bookDto.getBookID());
                rs = pst.executeQuery();
                while (rs.next()) {
                    quantity += rs.getInt("quantity");
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
            if (conn != null) {
                conn.close();
            }
        }
        return quantity;
    }
}
