package com.nyi.yumenuadmin.data.models;


import com.nyi.yumenuadmin.data.VOs.ShopVO;

/**
 * Created by IN-3442 on 20-Nov-16.
 */

public class ShopModel {
    private ShopVO shopVO;

    private static ShopModel objInstance;

    private ShopModel(){

    }

    public static ShopModel getobjInstance(){
        if(objInstance == null) objInstance = new ShopModel();
        return objInstance;
    }

    public void addAdminShop(ShopVO shopVO){
        this.shopVO = shopVO;
    }

    public ShopVO getShopVO() {
        return shopVO;
    }
}
