package com.nyi.yumenuadmin.views.holders;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nyi.yumenuadmin.R;
import com.nyi.yumenuadmin.YUMenuBookAdminApp;
import com.nyi.yumenuadmin.adapters.OrderItemAdapter;
import com.nyi.yumenuadmin.data.VOs.OrderItemVO;
import com.nyi.yumenuadmin.data.VOs.OrderVO;
import com.nyi.yumenuadmin.data.models.ShopModel;
import com.nyi.yumenuadmin.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 04-Dec-16.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tv_item_order_shop_name)
    TextView tvShopName;

    @BindView(R.id.tv_item_order_date)
    TextView tvDate;

    @BindView(R.id.tv_item_order_id)
    TextView tvOrderId;

    @BindView(R.id.tv_item_order_time)
    TextView tvOrderTime;

    @BindView(R.id.btn_item_order)
    Button btnOrder;

    @BindView(R.id.rv_item_order_item)
    RecyclerView rvOrderItem;

    private List<OrderItemVO> mOrderItemVOList = new ArrayList<>();
    private OrderItemAdapter mOrderItemAdapter;

    public OrderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        /*tvShopName.setTypeface(YUMenuBookApp.getTextTypeface());
        tvOrderId.setTypeface(YUMenuBookApp.getTextTypeface());
        tvDate.setTypeface(YUMenuBookApp.getTextTypeface());
        tvOrderTime.setTypeface(YUMenuBookApp.getTextTypeface());*/

        mOrderItemAdapter = new OrderItemAdapter(mOrderItemVOList);
        rvOrderItem.setAdapter(mOrderItemAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(YUMenuBookAdminApp.getContext(), LinearLayoutManager.VERTICAL, false);
        rvOrderItem.setLayoutManager(layoutManager);
    }

    public void bindData(OrderVO orderVO){
        tvShopName.setText(orderVO.getUserName());
        tvOrderId.setText("");
        tvDate.setText(orderVO.getUserPhone());
        tvOrderTime.setText(orderVO.getTime());

        //dummyDat();
        mOrderItemAdapter.setOrderItemVOList(mOrderItemVOList);

        getOrderItemFromFirebase(orderVO.getOrderID());
    }

    private void dummyDat(){
        mOrderItemVOList.clear();
        mOrderItemVOList.add(new OrderItemVO("Fried Rice 1 ", 2, 200));
        mOrderItemVOList.add(new OrderItemVO("Fried Rice 2 ", 2, 200));
        mOrderItemVOList.add(new OrderItemVO("Fried Rice 3 ", 2, 200));
        mOrderItemVOList.add(new OrderItemVO("Fried Rice 4 ", 2, 200));
        mOrderItemVOList.add(new OrderItemVO("Fried Rice 5 ", 2, 200));
    }

    private void getOrderItemFromFirebase(String id){
        mOrderItemVOList.clear();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constants.DETAIL).child(ShopModel.getobjInstance().getShopVO().getShopID()).child(Constants.ORDER).child(id).child(Constants.ORDER_ITEM);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                OrderItemVO orderItemVO = dataSnapshot.getValue(OrderItemVO.class);

                mOrderItemAdapter.addNewOrderItem(orderItemVO);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
