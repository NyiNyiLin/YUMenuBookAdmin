package com.nyi.yumenuadmin.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.nyi.yumenuadmin.R;
import com.nyi.yumenuadmin.data.VOs.MenuItem;
import com.nyi.yumenuadmin.utils.Constants;
import com.nyi.yumenuadmin.utils.FirebaseUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 27-Nov-16.
 */

public class EditDialogFragment extends DialogFragment {

    @BindView(R.id.et_dialoge_name)
    EditText etDialogeName;

    @BindView(R.id.et_dialoge_price)
    EditText etDialogePrice;

    private static final String ARG_MENUITEMID ="menuItemID";
    private static final String ARG_NAME = "name";
    private static final String ARG_PRICE = "price";
    private static final String ARG_AVAIL = "available";
    private static final String ARG_SHOP_ID = "shopID";
    private static final String ARG_SHOP_TYPE = "shopType";
    private static final String ARG_IS_NEW = "isNew";

    private String btnText;
    private boolean isNew;

    private MenuItem menuItem;
    private String shopID;
    private String shopType;


    public static EditDialogFragment newInstance(MenuItem menuItem, String shopID, String shopType){
        EditDialogFragment editDialogFragment = new EditDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MENUITEMID, menuItem.getMenuItemID());
        args.putString(ARG_NAME, menuItem.getName());
        args.putInt(ARG_PRICE, menuItem.getPrice());
        args.putInt(ARG_AVAIL, menuItem.getAvailable());
        args.putString(ARG_SHOP_ID, shopID);
        args.putString(ARG_SHOP_TYPE, shopType);

        args.putBoolean(ARG_IS_NEW, false);

        editDialogFragment.setArguments(args);

        return editDialogFragment;
    }

    public static EditDialogFragment newInstance(String shopID, String shopType){
        EditDialogFragment editDialogFragment = new EditDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOP_ID, shopID);
        args.putString(ARG_SHOP_TYPE, shopType);

        args.putBoolean(ARG_IS_NEW, true);

        editDialogFragment.setArguments(args);

        return editDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuItem = new MenuItem();
        if (getArguments() != null) {

            if(getArguments().getBoolean(ARG_IS_NEW)){
                btnText = "Add";
                isNew = true;

            }else {
                menuItem.setMenuItemID(getArguments().getString(ARG_MENUITEMID));
                menuItem.setName(getArguments().getString(ARG_NAME));
                menuItem.setPrice(getArguments().getInt(ARG_PRICE));
                menuItem.setAvailable(getArguments().getInt(ARG_AVAIL));

                btnText = "Update";
                isNew = false;
            }

            shopID = getArguments().getString(ARG_SHOP_ID);
            shopType = getArguments().getString(ARG_SHOP_TYPE);
        }


    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.fragment_edit_menu_item, null);
        ButterKnife.bind(this, view);

        etDialogeName.setText(menuItem.getName());
        etDialogePrice.setText(menuItem.getPrice() + "");

        builder.setView(view);

        // Add action buttons
        builder.setPositiveButton(btnText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        menuItem.setName(etDialogeName.getText().toString());
                        menuItem.setPrice(Integer.parseInt(etDialogePrice.getText().toString()));

                        if(isNew) onTapNew();
                        else onTapUpdate();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditDialogFragment.this.getDialog().cancel();
                    }
                });

        if(!isNew)builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseReference ref = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.DETAIL).child(shopID).child(Constants.MENUITEM).child(shopType).child(menuItem.getMenuItemID());
                ref.removeValue();
            }
        });

        return builder.create();
    }

    private void onTapNew() {
        menuItem.setAvailable(Constants.AVAILABLE);
        DatabaseReference ref = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.DETAIL).child(shopID).child(Constants.MENUITEM).child(shopType);
        ref.push().setValue(menuItem);
    }

    private void onTapUpdate(){
        DatabaseReference ref = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.DETAIL).child(shopID).child(Constants.MENUITEM).child(shopType).child(menuItem.getMenuItemID());
        ref.setValue(menuItem);
    }
}
