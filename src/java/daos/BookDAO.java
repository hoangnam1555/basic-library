/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dbUntils.DBConnection;
import dtos.BookDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class BookDAO {

    public List<BookDTO> getListBook() throws SQLException {
        List<BookDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = "SELECT bookID,title,price,quantity,author,status\n"
                        + "                        FROM tblBooks \n";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String bookID = rs.getString("bookID");
                    String title = rs.getString("title");
                    String price = rs.getString("price");
                    String quantity = rs.getString("quantity");
                    String author = rs.getString("author");
                    int status = rs.getInt("status");
                    if (status == 1) {
                        list.add(new BookDTO(bookID, title, Integer.parseInt(price), Integer.parseInt(quantity), author, status));
                    }
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
        return list;
    }

    public static ArrayList<BookDTO> getList(String search) throws SQLException {
        ArrayList<BookDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            if (con != null) {
                String sql = "SELECT b.bookID,b.title,b.price,b.quantity,b.author,b.status\n"
                        + "                        FROM tblBooks b\n"
                        + "                        WHERE b.bookID LIKE '%" + search + "%'";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String bookID = rs.getString("bookID");
                    String title = rs.getString("title");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String author = rs.getString("author");
                    int status = rs.getInt("status");

                    list.add(new BookDTO(bookID, title, price, quantity, author, status));
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

    public void update(BookDTO dto) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblBooks\n"
                        + "SET title = ?,quantity = ?,price = ?,author = ?,[status] = ?\n"
                        + "WHERE bookID = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, dto.getTitle());
                pst.setInt(2, dto.getQuantity());
                pst.setInt(3, dto.getPrice());
                pst.setString(4, dto.getAuthor());
                pst.setInt(5, dto.getStatus());
                pst.setString(6, dto.getBookID());
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

    public void delete(String bookID, int status) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblBooks\n"
                        + "SET [status] = ?\n"
                        + "WHERE bookID = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setString(2, bookID);
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

    public void insert(BookDTO dto) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblBooks(bookID,title,price,quantity,author,[status])\n"
                        + " VALUES(?,?,?,?,?,?)";

                pst = conn.prepareStatement(sql);
                pst.setString(1, dto.getBookID());
                pst.setString(2, dto.getTitle());
                pst.setInt(3, dto.getPrice());
                pst.setInt(4, dto.getQuantity());
                pst.setString(5, dto.getAuthor());
                pst.setInt(6, dto.getStatus());
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

    public boolean checkID(String bookID)
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            if (con != null) {
                String sql = "SELECT title FROM tblBooks WHERE\n"
                        + "                        bookID = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, bookID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
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

    public int getQuantity(String bookID) throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int quantity = -1;
        try {
            con = DBConnection.getConnection();
            if (con != null) {
                String sql = "SELECT quantity from tblBooks where BookID = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, bookID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("Quantity");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return quantity;
    }

    public BookDTO getBook(String seach) throws SQLException {
        BookDTO dto = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            if (con != null) {
                String sql = "SELECT bookID, title, quantity, Price, author from tblBooks where BookID = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, seach);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String bookID = rs.getString("BookID");
                    String bookname = rs.getString("Bookname");
                    int quantity = rs.getInt("Quantity");
                    int price = rs.getInt("Price");
                    String author = rs.getString("Author");
                    dto = new BookDTO(bookID, bookname, quantity, price, author,1);
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
}
