package com.nyi.yumenuadmin.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.nyi.yumenuadmin.R;
import com.nyi.yumenuadmin.YUMenuBookAdminApp;
import com.nyi.yumenuadmin.data.VOs.AdminVO;
import com.nyi.yumenuadmin.data.VOs.ShopVO;
import com.nyi.yumenuadmin.data.models.ShopModel;
import com.nyi.yumenuadmin.fragments.MenuFragment;
import com.nyi.yumenuadmin.utils.Constants;
import com.nyi.yumenuadmin.utils.DialogUtil;
import com.nyi.yumenuadmin.utils.FirebaseUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInActivity extends AppCompatActivity {

    private List<AdminVO> adminVOList = new ArrayList<>();

    ProgressDialog progressDialog;

    @BindView(R.id.et_login_usernamr)
    EditText etUserName;

    @BindView(R.id.et_login_password)
    EditText etPassword;

    public static Intent newIntent(){
        Intent intent = new Intent(YUMenuBookAdminApp.getContext(), LogInActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this, this);

        progressDialog = DialogUtil.createProgressDialoge(this, "Loading...");
        progressDialog.show();

        getAdminDataFromFirebase();



    }

    public void onClickLogIn(View view){
        String username = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        for(AdminVO adminVO : adminVOList){
            if(username.compareTo(adminVO.getUserName()) == 0){
                if(password.compareTo(adminVO.getPassword()) == 0){

                    SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.PREF, MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString(MainActivity.PARAM_shopID, adminVO.getShopID());
                    editor.commit();
                    this.finish();
                }
            }
        }

    }

    private void getAdminDataFromFirebase(){
        DatabaseReference ref = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.ADMIN);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                AdminVO adminVO = dataSnapshot.getValue(AdminVO.class);

                adminVOList.add(adminVO);

                if(progressDialog.isShowing()) progressDialog.dismiss();
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
