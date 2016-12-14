package com.nyi.yumenuadmin.fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.nyi.yumenuadmin.R;
import com.nyi.yumenuadmin.YUMenuBookAdminApp;
import com.nyi.yumenuadmin.adapters.MenuItemAdapter;
import com.nyi.yumenuadmin.data.VOs.MenuItem;
import com.nyi.yumenuadmin.utils.Constants;
import com.nyi.yumenuadmin.utils.FirebaseUtil;
import com.nyi.yumenuadmin.views.holders.MenuItemViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuItemFragment extends Fragment implements MenuItemViewHolder.ControllerMenuItem{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOP_ID = "shopID";
    private static final String ARG_TYPE = "type";

    private String mShopID;
    private String mShopType;

    @BindView(R.id.rv_menuItem)
    RecyclerView rvMenuItem;

    @BindView(R.id.btn_menu_item_add)
    Button btnItemAdd;

    private List<MenuItem> mMenuItemList;
    private MenuItemAdapter menuItemAdapter;

    public MenuItemFragment() {
        // Required empty public constructor
    }

    public static MenuItemFragment newInstance(String shopID, String type) {
        MenuItemFragment fragment = new MenuItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOP_ID, shopID);
        args.putString(ARG_TYPE, type);
        fragment.setArguments(args);

        Log.d(Constants.TAG, "Menu Item Created");
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mShopID = getArguments().getString(ARG_SHOP_ID);
            mShopType = getArguments().getString(ARG_TYPE);
        }

        if(mMenuItemList == null){
            mMenuItemList = new ArrayList<>();
        }
        Log.d(Constants.TAG, "MenuItemFragment onCreate" + mShopID + " + " + mShopType);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_item, container, false);
        ButterKnife.bind(this, view);

        Log.d(Constants.TAG, "MenuItemFragment onCreate View" + mShopID + " + " + mShopType);

        menuItemAdapter = new MenuItemAdapter(mMenuItemList, this);
        rvMenuItem.setAdapter(menuItemAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(YUMenuBookAdminApp.getContext(), LinearLayoutManager.VERTICAL, false);
        rvMenuItem.setLayoutManager(layoutManager);

        getMenuItemFromFirebase();

        btnItemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialog = EditDialogFragment.newInstance(mShopID, mShopType);
                dialog.show(getChildFragmentManager(), "Add");
            }
        });

        return view;
    }


    @Override
    public void onTapAvailable(MenuItem menuItem) {
        updateMenuItem(menuItem);
    }

    @Override
    public void onTapEdit(MenuItem menuItem) {
        DialogFragment dialog = EditDialogFragment.newInstance(menuItem, mShopID, mShopType);
        dialog.show(getChildFragmentManager(), "Update");
    }

    private void getDummyData(){
        for(int a= 0; a<10; a++){
            MenuItem menuItem = new MenuItem("name " + a, 500, Constants.AVAILABLE);
            menuItem.setMenuItemID("ID " + a);
            mMenuItemList.add(menuItem);
        }
    }


    private void getMenuItemFromFirebase(){
        DatabaseReference ref = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.DETAIL).child(mShopID).child(Constants.MENUITEM).child(mShopType);

        Log.d(Constants.TAG, "MenuItemFragment getMenuItemChild" + mShopID + " + " + mShopType);

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(Constants.TAG, "onMenuItemChildAdded:" + dataSnapshot.getKey());

                MenuItem menuItem = dataSnapshot.getValue(MenuItem.class);
                menuItem.setMenuItemID(dataSnapshot.getKey());
                menuItemAdapter.addNewMenu(menuItem);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(Constants.TAG, "onMenuItemChildChildren:" + dataSnapshot.getKey());

                MenuItem menuItem = dataSnapshot.getValue(MenuItem.class);
                menuItem.setMenuItemID(dataSnapshot.getKey());
                menuItemAdapter.changeMenu(menuItem);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(Constants.TAG, "onMenuItemChildMoved:" + dataSnapshot.getKey());

                String removeMenuID = dataSnapshot.getKey();
                menuItemAdapter.removeMenu(removeMenuID);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(Constants.TAG, "onChildMoved:" + dataSnapshot.getKey());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        ref.addChildEventListener(childEventListener);
    }

    private void updateMenuItem(MenuItem menuItem){
        DatabaseReference ref = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.DETAIL).child(mShopID).child(Constants.MENUITEM).child(mShopType).child(menuItem.getMenuItemID());
        ref.setValue(menuItem);
    }
}
