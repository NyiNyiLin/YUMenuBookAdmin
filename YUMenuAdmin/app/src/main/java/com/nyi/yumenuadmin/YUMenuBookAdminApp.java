package com.nyi.yumenuadmin;

import android.app.Application;
import android.content.Context;

import com.nyi.yumenuadmin.utils.FirebaseUtil;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class YUMenuBookAdminApp extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        //FirebaseUtil.getObjInstance().uploadTestMenu();
        //FirebaseUtil.getObjInstance().uploadTestShop();

        /*FirebaseUtil.getObjInstance().uploadShanMaLayShop();
        FirebaseUtil.getObjInstance().uploadBerryHouseShop();
        FirebaseUtil.getObjInstance().uploadSagingRoadShop();*/

        /*FirebaseUtil.getObjInstance().uploadShanMaLayShop();
        FirebaseUtil.getObjInstance().uploadShwePhaLarShop();*/

        //FirebaseUtil.getObjInstance().uploadUserNameAndPassword();
    }

    public static Context getContext() {
        return context;
    }

}
