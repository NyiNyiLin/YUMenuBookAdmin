package com.nyi.yumenuadmin.views.holders;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
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

public class MenuViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.rv_item_view_menu)
    RecyclerView rvItemViewMenu;


    public MenuViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindMenu(String menuType){

    }


}
