package com.nyi.yumenuadmin.data.VOs;

/**
 * Created by IN-3442 on 19-Nov-16.
 */

public class MenuItem {
    private String menuItemID;
    private String name;
    private int price;
    private int available;

    public MenuItem() {
    }

    public MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public MenuItem(String name, int price, int available) {
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getAvailable() {
        return available;
    }

    public String getMenuItemID() {
        return menuItemID;
    }

    public void setMenuItemID(String menuItemID) {
        this.menuItemID = menuItemID;
    }
}
