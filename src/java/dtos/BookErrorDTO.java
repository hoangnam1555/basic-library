/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author USER
 */
public class BookErrorDTO {
    private String errorBookID,errorBookTitle,errorBookPrice,errorBookQuantity,errorBookAuthor,errorStatus;

    public BookErrorDTO(String errorBookID, String errorBookTitle, String errorBookPrice, String errorBookQuantity, String errorBookAuthor, String errorStatus) {
        this.errorBookID = errorBookID;
        this.errorBookTitle = errorBookTitle;
        this.errorBookPrice = errorBookPrice;
        this.errorBookQuantity = errorBookQuantity;
        this.errorBookAuthor = errorBookAuthor;
        this.errorStatus = errorStatus;
    }

    public BookErrorDTO() {
    }

    public String getErrorBookID() {
        return errorBookID;
    }

    public void setErrorBookID(String errorBookID) {
        this.errorBookID = errorBookID;
    }

    public String getErrorBookTitle() {
        return errorBookTitle;
    }

    public void setErrorBookTitle(String errorBookTitle) {
        this.errorBookTitle = errorBookTitle;
    }

    public String getErrorBookPrice() {
        return errorBookPrice;
    }

    public void setErrorBookPrice(String errorBookPrice) {
        this.errorBookPrice = errorBookPrice;
    }

    public String getErrorBookQuantity() {
        return errorBookQuantity;
    }

    public void setErrorBookQuantity(String errorBookQuantity) {
        this.errorBookQuantity = errorBookQuantity;
    }

    public String getErrorBookAuthor() {
        return errorBookAuthor;
    }

    public void setErrorBookAuthor(String errorBookAuthor) {
        this.errorBookAuthor = errorBookAuthor;
    }

    public String getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
    }
    
    
}
