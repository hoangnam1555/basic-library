/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class OrderDTO {
    private String orderID;
    private String userID;
    private Date Date;
    private Date returnDate;
    private int total;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getDate() {
        return Date;
    }

    public void setGetDate(Date getDate) {
        this.Date = getDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public OrderDTO() {
    }

    public OrderDTO(String orderID, String userID, Date getDate, Date returnDate, int total) {
        this.orderID = orderID;
        this.userID = userID;
        this.Date = getDate;
        this.returnDate = returnDate;
        this.total = total;
    }

}
