package com.nyi.yumenuadmin.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.nyi.yumenuadmin.adapters.ReviewAdapter;
import com.nyi.yumenuadmin.data.VOs.ReviewVO;
import com.nyi.yumenuadmin.data.VOs.ShopVO;
import com.nyi.yumenuadmin.data.models.ShopModel;
import com.nyi.yumenuadmin.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewFragment extends Fragment {

    private ReviewAdapter reviewAdapter;
    private List<ReviewVO> mReviewVOList = new ArrayList<>();

    @BindView(R.id.rv_review_item)
    RecyclerView rvReviewItem;

    public ReviewFragment() {
        // Required empty public constructor
    }

    public static ReviewFragment newInstance() {
        ReviewFragment fragment = new ReviewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        ButterKnife.bind(this, view);

        reviewAdapter = new ReviewAdapter(mReviewVOList);
        rvReviewItem.setAdapter(reviewAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(YUMenuBookAdminApp.getContext(), LinearLayoutManager.VERTICAL, false);
        rvReviewItem.setLayoutManager(layoutManager);

        getReviewFromFirebase();

        return view;
    }

    private void getReviewFromFirebase(){
        ShopVO shopVO = ShopModel.getobjInstance().getShopVO();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constants.DETAIL).child(shopVO.getShopID()).child(Constants.REVIEW);

        ChildEventListener listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(Constants.TAG, "Review Activity onChildAdded:" + dataSnapshot.getKey());

                ReviewVO reviewVO = dataSnapshot.getValue(ReviewVO.class);

                reviewAdapter.addNewReview(reviewVO);
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
        };
        databaseReference.addChildEventListener(listener);


    }

}
