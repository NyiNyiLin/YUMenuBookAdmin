package com.nyi.yumenuadmin.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nyi.yumenuadmin.R;
import com.nyi.yumenuadmin.YUMenuBookAdminApp;
import com.nyi.yumenuadmin.adapters.OrderAdapter;
import com.nyi.yumenuadmin.data.VOs.OrderItemVO;
import com.nyi.yumenuadmin.data.VOs.OrderVO;
import com.nyi.yumenuadmin.data.models.ShopModel;
import com.nyi.yumenuadmin.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 11-Dec-16.
 */

public class OrderFragment extends Fragment {

    @BindView(R.id.rv_order)
    RecyclerView rvOrder;

    private List<OrderVO> mOrderVOList = new ArrayList<>();
    private OrderAdapter orderAdapter;

    public OrderFragment(){

    }

    public static OrderFragment newInstance(){
        OrderFragment orderFragment = new OrderFragment();

        return orderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, view);

        orderAdapter = new OrderAdapter(mOrderVOList);
        rvOrder.setAdapter(orderAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(YUMenuBookAdminApp.getContext(), LinearLayoutManager.VERTICAL, false);
        rvOrder.setLayoutManager(layoutManager);

        getOrderDataFromFirebase();

        return view;
    }

    private void getOrderDataFromFirebase(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constants.DETAIL).child(ShopModel.getobjInstance().getShopVO().getShopID()).child(Constants.ORDER);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                OrderVO orderVO = dataSnapshot.getValue(OrderVO.class);

                orderAdapter.addNewOrder(orderVO);
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
