package com.nyi.yumenuadmin.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.nyi.yumenuadmin.R;
import com.nyi.yumenuadmin.YUMenuBookAdminApp;
import com.nyi.yumenuadmin.data.VOs.OrderItemVO;
import com.nyi.yumenuadmin.data.VOs.OrderVO;
import com.nyi.yumenuadmin.views.holders.OrderItemViewHolder;

import java.util.List;

/**
 * Created by IN-3442 on 08-Dec-16.
 */

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemViewHolder> {
    private List<OrderItemVO> orderItemVOList;
    private LayoutInflater inflater;

    public OrderItemAdapter(List<OrderItemVO> orderItemVOList) {
        this.orderItemVOList = orderItemVOList;

        inflater = LayoutInflater.from(YUMenuBookAdminApp.getContext());
    }

    @Override
    public OrderItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view_order_item, parent, false);

        OrderItemViewHolder orderItemViewHolder = new OrderItemViewHolder(view);

        return orderItemViewHolder;
    }

    @Override
    public void onBindViewHolder(OrderItemViewHolder holder, int position) {
        holder.bindData(orderItemVOList.get(position));
    }


    @Override
    public int getItemCount() {
        return orderItemVOList.size();
    }

    public void setOrderItemVOList(List orderItemVOList){
        this.orderItemVOList = orderItemVOList;

        notifyDataSetChanged();
    }

    public void addNewOrderItem(OrderItemVO orderItemVO){
        orderItemVOList.add(orderItemVO);
        notifyItemInserted(orderItemVOList.size());
    }
}
