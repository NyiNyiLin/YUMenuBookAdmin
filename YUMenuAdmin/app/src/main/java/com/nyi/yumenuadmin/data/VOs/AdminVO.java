package com.nyi.yumenuadmin.data.VOs;

/**
 * Created by IN-3442 on 14-Dec-16.
 */

public class AdminVO {
    public String userName;
    public String password;
    public String userID;
    public String shopID;

    public AdminVO() {
    }

    public AdminVO(String userName, String password, String shopID) {
        this.userName = userName;
        this.password = password;
        this.shopID = shopID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserID() {
        return userID;
    }

    public String getShopID() {
        return shopID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }
}
