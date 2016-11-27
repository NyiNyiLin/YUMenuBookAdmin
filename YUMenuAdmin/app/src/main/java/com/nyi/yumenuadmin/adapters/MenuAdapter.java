package com.nyi.yumenuadmin.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.yumenuadmin.R;
import com.nyi.yumenuadmin.YUMenuBookAdminApp;
import com.nyi.yumenuadmin.data.VOs.MenuItem;
import com.nyi.yumenuadmin.views.holders.MenuItemViewHolder;
import com.nyi.yumenuadmin.views.holders.MenuViewHolder;

import java.util.List;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {
    private LayoutInflater inflater;
    private List<String> mMenuTypeList;

    public MenuAdapter(List<String> menuTypeList) {
        this.mMenuTypeList = menuTypeList;
        inflater = LayoutInflater.from(YUMenuBookAdminApp.getContext());
    }


    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view_menu, parent, false);
        MenuViewHolder menuViewHolder = new MenuViewHolder(view);
        return menuViewHolder;
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        holder.bindMenu(mMenuTypeList.get(position));
    }


    @Override
    public int getItemCount() {
        return mMenuTypeList.size();
    }
}
