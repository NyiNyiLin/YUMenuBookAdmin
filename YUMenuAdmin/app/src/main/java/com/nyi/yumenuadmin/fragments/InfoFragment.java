package com.nyi.yumenuadmin.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nyi.yumenuadmin.R;
import com.nyi.yumenuadmin.activities.LogInActivity;
import com.nyi.yumenuadmin.activities.MainActivity;
import com.nyi.yumenuadmin.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 12-Dec-16.
 */

public class InfoFragment extends Fragment {

    @BindView(R.id.btn_info_logout)
    Button btnLogOut;

    public InfoFragment(){

    }

    public static InfoFragment newInstance(){
        InfoFragment fragment =new InfoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getContext().getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString(MainActivity.PARAM_shopID, null);
                editor.commit();

                Intent intent = LogInActivity.newIntent();
                startActivity(intent);
            }
        });

        return view;
    }
}
