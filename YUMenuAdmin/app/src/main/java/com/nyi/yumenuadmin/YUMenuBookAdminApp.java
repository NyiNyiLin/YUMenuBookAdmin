package com.nyi.yumenuadmin;

import android.app.Application;
import android.content.Context;

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
    }

    public static Context getContext() {
        return context;
    }

}
