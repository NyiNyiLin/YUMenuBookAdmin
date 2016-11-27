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

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemViewHolder> {
    private LayoutInflater inflater;
    private List<MenuItem> mMenuItemList;
    MenuItemViewHolder.ControllerMenuItem mControllerMenuItem;

    public MenuItemAdapter(List<MenuItem> menuItemList, MenuItemViewHolder.ControllerMenuItem controllerMenuItem) {
        this.mMenuItemList = menuItemList;
        this.mControllerMenuItem = controllerMenuItem;
        inflater = LayoutInflater.from(YUMenuBookAdminApp.getContext());
    }


    @Override
    public MenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view_menu_item, parent, false);
        MenuItemViewHolder menuItemViewHolder = new MenuItemViewHolder(view, mControllerMenuItem);
        return menuItemViewHolder;
    }

    @Override
    public void onBindViewHolder(MenuItemViewHolder holder, int position) {
        holder.bindMenu(mMenuItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMenuItemList.size();
    }

    public void addNewMenu(MenuItem menuItem){
        mMenuItemList.add(menuItem);
        notifyItemInserted(mMenuItemList.size());
    }

    public void changeMenu(MenuItem newMenuItem){
        int position = 0;
        for(int a=0; a<getItemCount(); a++ ){
            MenuItem menuItem = mMenuItemList.get(a);
            if(menuItem.getMenuItemID().equals(newMenuItem.getMenuItemID())){
                position = a;
            }
        }
        mMenuItemList.remove(position);
        mMenuItemList.add(position, newMenuItem);
        notifyItemChanged(position);
    }

    public void removeMenu(String removedMenuID){
        int position = 0;
        for(int a=0; a<getItemCount(); a++ ){
            MenuItem menuItem = mMenuItemList.get(a);
            if(menuItem.getMenuItemID().equals(removedMenuID)){
                position = a;
            }
        }
        mMenuItemList.remove(position);
        notifyItemRemoved(position);
    }
}
