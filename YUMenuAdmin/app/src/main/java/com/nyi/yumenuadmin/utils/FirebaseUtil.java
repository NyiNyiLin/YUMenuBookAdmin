package com.nyi.yumenuadmin.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nyi.yumenuadmin.data.VOs.AdminVO;
import com.nyi.yumenuadmin.data.VOs.MenuItem;
import com.nyi.yumenuadmin.data.VOs.ShopVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 20-Nov-16.
 */

public class FirebaseUtil {
    private static FirebaseUtil objInstance;
    private DatabaseReference mDatabase;

    private FirebaseUtil(){
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public static FirebaseUtil getObjInstance(){
        if(objInstance == null) objInstance = new FirebaseUtil();
        return objInstance;
    }

    public DatabaseReference getDatabaseReference() {
        return mDatabase;
    }

    public void uploadTestShop(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        List<String> type = new ArrayList<>();
        type.add("Breakfast");
        type.add("Lunch");
        type.add("Drink");
        ShopVO shopVO1 = new ShopVO("Shan Ma Lay", "Shan Ma Lay", Constants.DUMMY_LINK, Constants.TAUNGNOO_CANTEEN, type);
        ShopVO shopVO2 = new ShopVO("Shwe Pha Lar", "Shwe Pha Lar", Constants.DUMMY_LINK, Constants.TAUNGNOO_CANTEEN, type);
        ShopVO shopVO3 = new ShopVO("Inn Wa", "Inn Wa", Constants.DUMMY_LINK, Constants.SCIENCE_CANTEEN, type);

        mDatabase.child(Constants.SHOP).child("Shan Ma Lay").setValue(shopVO1);
        mDatabase.child(Constants.SHOP).child("Shwe Pha Lar").setValue(shopVO2);
        mDatabase.child(Constants.SHOP).child("Inn Wa").setValue(shopVO3);
    }

    public void uploadTestMenu(){
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child(Constants.DETAIL).child("Shan Ma Lay").child(Constants.MENUITEM).child("Breakfast").push().setValue(new MenuItem("Fried Rice 1", 1300, 1));
        mDatabase.child(Constants.DETAIL).child("Shan Ma Lay").child(Constants.MENUITEM).child("Breakfast").push().setValue(new MenuItem("Mone Hin Khar 1", 300, 1));
        mDatabase.child(Constants.DETAIL).child("Shan Ma Lay").child(Constants.MENUITEM).child("Breakfast").push().setValue(new MenuItem("Pa Lar Tar 1", 100, 1));

        mDatabase.child(Constants.DETAIL).child("Shan Ma Lay").child(Constants.MENUITEM).child("Lunch").push().setValue(new MenuItem("Soe Myint Kyaw 1", 1500, 1));
        mDatabase.child(Constants.DETAIL).child("Shan Ma Lay").child(Constants.MENUITEM).child("Lunch").push().setValue(new MenuItem("Chinese Fried Rice 1", 1500, 1));
        mDatabase.child(Constants.DETAIL).child("Shan Ma Lay").child(Constants.MENUITEM).child("Lunch").push().setValue(new MenuItem("Thai Fried Rice 1", 1500, 1));

        mDatabase.child(Constants.DETAIL).child("Shan Ma Lay").child(Constants.MENUITEM).child("Drink").push().setValue(new MenuItem("Cola 1", 300, 1));
        mDatabase.child(Constants.DETAIL).child("Shan Ma Lay").child(Constants.MENUITEM).child("Drink").push().setValue(new MenuItem("Pa Pa Ya 1", 500, 1));
        mDatabase.child(Constants.DETAIL).child("Shan Ma Lay").child(Constants.MENUITEM).child("Drink").push().setValue(new MenuItem("Juice 1", 500, 1));

        mDatabase.child(Constants.DETAIL).child("Shwe Pha Lar").child(Constants.MENUITEM).child("Breakfast").push().setValue(new MenuItem("Fried Rice 2", 1300, 1));
        mDatabase.child(Constants.DETAIL).child("Shwe Pha Lar").child(Constants.MENUITEM).child("Breakfast").push().setValue(new MenuItem("Mone Hin Khar 2", 300, 1));
        mDatabase.child(Constants.DETAIL).child("Shwe Pha Lar").child(Constants.MENUITEM).child("Breakfast").push().setValue(new MenuItem("Pa Lar Tar 2", 100, 1));

        mDatabase.child(Constants.DETAIL).child("Shwe Pha Lar").child(Constants.MENUITEM).child("Lunch").push().setValue(new MenuItem("Soe Myint Kyaw 2", 1500, 1));
        mDatabase.child(Constants.DETAIL).child("Shwe Pha Lar").child(Constants.MENUITEM).child("Lunch").push().setValue(new MenuItem("Chinese Fried Rice 2", 1500, 1));
        mDatabase.child(Constants.DETAIL).child("Shwe Pha Lar").child(Constants.MENUITEM).child("Lunch").push().setValue(new MenuItem("Thai Fried Rice 2", 1500, 1));

        mDatabase.child(Constants.DETAIL).child("Shwe Pha Lar").child(Constants.MENUITEM).child("Drink").push().setValue(new MenuItem("Cola 2", 300, 1));
        mDatabase.child(Constants.DETAIL).child("Shwe Pha Lar").child(Constants.MENUITEM).child("Drink").push().setValue(new MenuItem("Pa Pa Ya 2", 500, 1));
        mDatabase.child(Constants.DETAIL).child("Shwe Pha Lar").child(Constants.MENUITEM).child("Drink").push().setValue(new MenuItem("Juice 2", 500, 1));

    }

    public void uploadBerryHouseShop(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        List<String> type = new ArrayList<>();
        type.add("Food");
        type.add("Drink");
        ShopVO shopVO1 = new ShopVO("Berry House", "Berry House", Constants.DUMMY_LINK, Constants.SCIENCE_CANTEEN, type);

        mDatabase.child(Constants.SHOP).child("Berry House").setValue(shopVO1);

        mDatabase.child(Constants.DETAIL).child("Berry House").child(Constants.MENUITEM).child("Food").push().setValue(new MenuItem("Food", 1300, 1));
        mDatabase.child(Constants.DETAIL).child("Berry House").child(Constants.MENUITEM).child("Drink").push().setValue(new MenuItem("Drink", 100, 1));

    }

    public void uploadSagingRoadShop(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        List<String> type = new ArrayList<>();
        type.add("Breakfast");
        type.add("Lunch");
        type.add("Drink");
        ShopVO shopVO1 = new ShopVO("Sagaing Lan", "Sagaing Lan", Constants.DUMMY_LINK, Constants.SCIENCE_CANTEEN, type);

        mDatabase.child(Constants.SHOP).child("Sagaing Lan").setValue(shopVO1);

        mDatabase.child(Constants.DETAIL).child("Sagaing Lan").child(Constants.MENUITEM).child("Breakfast").push().setValue(new MenuItem("Breakfast", 1300, 1));
        mDatabase.child(Constants.DETAIL).child("Sagaing Lan").child(Constants.MENUITEM).child("Lunch").push().setValue(new MenuItem("Lunch", 300, 1));
        mDatabase.child(Constants.DETAIL).child("Sagaing Lan").child(Constants.MENUITEM).child("Drink").push().setValue(new MenuItem("Drink", 100, 1));

    }

    public void uploadShanMaLayShop(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        List<String> type = new ArrayList<>();
        type.add("Breakfast");
        type.add("Lunch");
        type.add("ဟင္းပြဲ");
        type.add("ျမန္မာအစားအစာ");
        type.add("ပံုစား");
        type.add("ဟင္းခ်ိဳ");
        type.add("Drink");
        ShopVO shopVO1 = new ShopVO("Shan Ma Lay", "Shan Ma Lay", Constants.DUMMY_LINK, Constants.TAUNGNOO_CANTEEN, type);

        mDatabase.child(Constants.SHOP).child("Shan Ma Lay").setValue(shopVO1);

        mDatabase.child(Constants.DETAIL).child("Shan Ma Lay").child(Constants.MENUITEM).child("ဟင္းပြဲ").push().setValue(new MenuItem("ဟင္းပြဲ", 1300, 1));

    }

    public void uploadShwePhaLarShop(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        List<String> type = new ArrayList<>();
        type.add("Breakfast");
        type.add("ျမန္မာအစားအစာ");
        type.add("တရုတ္အစားအစာ");
        type.add("ဟင္းပြဲ");
        type.add("ဟင္းခ်ိဳ");
        type.add("ရွမ္းရိုးရာ");
        type.add("အစာေျပ");
        type.add("Drink");
        type.add("Icecream");
        ShopVO shopVO1 = new ShopVO("Shwe Pha Lar", "Shwe Pha Lar", Constants.DUMMY_LINK, Constants.TAUNGNOO_CANTEEN, type);

        mDatabase.child(Constants.SHOP).child("Shwe Pha Lar").setValue(shopVO1);

        mDatabase.child(Constants.DETAIL).child("Shwe Pha Lar").child(Constants.MENUITEM).child("Breakfast").push().setValue(new MenuItem("ဟင္းပြဲ", 1300, 1));
        mDatabase.child(Constants.DETAIL).child("Shwe Pha Lar").child(Constants.MENUITEM).child("ျမန္မာအစားအစာ").push().setValue(new MenuItem("ဟင္းပြဲ", 1300, 1));
        mDatabase.child(Constants.DETAIL).child("Shwe Pha Lar").child(Constants.MENUITEM).child("တရုတ္အစားအစာ").push().setValue(new MenuItem("ဟင္းပြဲ", 1300, 1));
        mDatabase.child(Constants.DETAIL).child("Shwe Pha Lar").child(Constants.MENUITEM).child("ဟင္းပြဲ").push().setValue(new MenuItem("ဟင္းပြဲ", 1300, 1));
        mDatabase.child(Constants.DETAIL).child("Shwe Pha Lar").child(Constants.MENUITEM).child("ဟင္းခ်ိဳ").push().setValue(new MenuItem("ဟင္းပြဲ", 1300, 1));
        mDatabase.child(Constants.DETAIL).child("Shwe Pha Lar").child(Constants.MENUITEM).child("ရွမ္းရိုးရာ").push().setValue(new MenuItem("ဟင္းပြဲ", 1300, 1));
        mDatabase.child(Constants.DETAIL).child("Shwe Pha Lar").child(Constants.MENUITEM).child("အစာေျပ").push().setValue(new MenuItem("ဟင္းပြဲ", 1300, 1));
        mDatabase.child(Constants.DETAIL).child("Shwe Pha Lar").child(Constants.MENUITEM).child("Drink").push().setValue(new MenuItem("ဟင္းပြဲ", 1300, 1));
        mDatabase.child(Constants.DETAIL).child("Shwe Pha Lar").child(Constants.MENUITEM).child("Icecream").push().setValue(new MenuItem("ဟင္းပြဲ", 1300, 1));

    }

    public void uploadUserNameAndPassword(){
        mDatabase = FirebaseDatabase.getInstance().getReference();

        AdminVO adminVO1 = new AdminVO("shanmalay", "abcde", "Shan Ma Lay");
        AdminVO adminVO2 = new AdminVO("shwephalar", "12345", "Shwe Pha Lar");
        AdminVO adminVO3 = new AdminVO("berryhouse", "qwert", "Berry House");
        AdminVO adminVO4 = new AdminVO("sagainglan", "asdfg", "Sagaing Lan");


        mDatabase.child(Constants.ADMIN).push().setValue(adminVO1);
        mDatabase.child(Constants.ADMIN).push().setValue(adminVO2);
        mDatabase.child(Constants.ADMIN).push().setValue(adminVO3);
        mDatabase.child(Constants.ADMIN).push().setValue(adminVO4);
    }

}
