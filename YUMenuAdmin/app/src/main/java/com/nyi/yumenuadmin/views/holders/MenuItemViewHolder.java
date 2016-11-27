package com.nyi.yumenuadmin.views.holders;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyi.yumenuadmin.R;
import com.nyi.yumenuadmin.data.VOs.MenuItem;
import com.nyi.yumenuadmin.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 22-Nov-16.
 */

public class MenuItemViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.tv_item_menu_name)
    TextView tvItemMenuName;

    @BindView(R.id.tv_item_menu_price)
    TextView tvItemMenuPrice;

    @BindView(R.id.btn_isavailable)
    CheckBox btnIsAvailable;

    @BindView(R.id.iv_item_menu_edit)
    ImageView ivItemMenuEdit;

    private ControllerMenuItem mControllerMenuItem;
    private MenuItem menuItem;

    public MenuItemViewHolder(View itemView, ControllerMenuItem controllerMenuItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mControllerMenuItem = controllerMenuItem;

        btnIsAvailable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) menuItem.setAvailable(Constants.AVAILABLE);
                else menuItem.setAvailable(Constants.NOT_AVAILABLE);
                mControllerMenuItem.onTapAvailable(menuItem);
            }
        });

        ivItemMenuEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mControllerMenuItem.onTapEdit(menuItem);
            }
        });
    }

    public void bindMenu(MenuItem menuItem){
        this.menuItem = menuItem;
        tvItemMenuName.setText(menuItem.getName());
        tvItemMenuPrice.setText(menuItem.getPrice() + "");

        if(menuItem.getAvailable() == Constants.AVAILABLE) btnIsAvailable.setChecked(true);
        else if(menuItem.getAvailable() == Constants.NOT_AVAILABLE) btnIsAvailable.setChecked(false);
    }

    public interface ControllerMenuItem{
        void onTapAvailable(MenuItem menuItem);
        void onTapEdit(MenuItem menuItem);
    }
}
