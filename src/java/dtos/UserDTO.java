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
public class UserDTO {
    private String userID,fullName,password,RoleID;

    public UserDTO(String userID, String fullName, String password, String RoleID) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.RoleID = RoleID;
    }

    public UserDTO() {
    }

    public String getUserId() {
        return userID;
    }

    public void setUserId(String id) {
        this.userID = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return RoleID;
    }

    public void setRoleID(String RoleID) {
        this.RoleID = RoleID;
    }

}

