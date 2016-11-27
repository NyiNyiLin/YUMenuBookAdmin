package com.nyi.yumenuadmin.activities;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nyi.yumenuadmin.R;
import com.nyi.yumenuadmin.YUMenuBookAdminApp;
import com.nyi.yumenuadmin.adapters.MenuFragmentPagerAdapter;
import com.nyi.yumenuadmin.fragments.MenuFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private float x1,x2;
    static final int MIN_DISTANCE = 150;

    @BindView(R.id.main_frame)
    FrameLayout frameLayout;

    @BindView(R.id.left_menu)
    LinearLayout leftMenu;

    @BindView(R.id.cv_main)
    CardView cardViewMain;

    @BindView(R.id.iv_open_left_menu)
    ImageView ivOpenLeftMenu;

    private ObjectAnimator leftAnimation;
    private Animation animSlideRight;
    private Animation animSlideLeft;
    private boolean isLeftMenuOpen = false;
    public MenuFragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        leftAnimation = ObjectAnimator.ofFloat(
                cardViewMain,
                "x",
                300);
        leftAnimation.setDuration(500);

        cardViewMain.setClickable(false);

        ivOpenLeftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerOnOff();
            }
        });

        cardViewMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerOnOff();
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, MenuFragment.newInstance()).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void drawerOnOff(){
        if(isLeftMenuOpen == false){
            leftAnimation.start();

            isLeftMenuOpen = true;
            cardViewMain.setClickable(true);
        }else if(isLeftMenuOpen == true) {
            leftAnimation.reverse();

            isLeftMenuOpen = false;
            cardViewMain.setClickable(false);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1)
                    {
                        drawerOnOff();
                    }

                    // Right to left swipe action
                    else
                    {
                        drawerOnOff();
                    }

                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
