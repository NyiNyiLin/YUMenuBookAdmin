package com.nyi.yumenuadmin.views.holders;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

    /*@BindView(R.id.sc_available)
    SwitchCompat scAvailable;*/

    @BindView(R.id.btn_isavailable)
    CheckBox btnIsAvailable;

    @BindView(R.id.iv_item_menu_edit)
    ImageView ivItemMenuEdit;

    private ControllerMenuItem mControllerMenuItem;

    public MenuItemViewHolder(View itemView, ControllerMenuItem controllerMenuItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mControllerMenuItem = controllerMenuItem;
    }

    public void bindMenu(MenuItem menuItem){
        tvItemMenuName.setText(menuItem.getName());
        tvItemMenuPrice.setText(menuItem.getPrice() + "");
        /*if(menuItem.getAvailable() == Constants.AVAILABLE) scAvailable.setChecked(true);
        else if(menuItem.getAvailable() == Constants.NOT_AVAILABLE) scAvailable.setChecked(false);*/

        if(menuItem.getAvailable() == Constants.AVAILABLE) btnIsAvailable.setChecked(true);
        else if(menuItem.getAvailable() == Constants.NOT_AVAILABLE) btnIsAvailable.setChecked(false);
    }

    public interface ControllerMenuItem{
        void onTapAvailable();
        void onTapEdit();
    }
}
