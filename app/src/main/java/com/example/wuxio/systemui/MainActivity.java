package com.example.wuxio.systemui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.system_ui.SystemUI;

public class MainActivity extends AppCompatActivity {

    protected ImageView      mIvLogo;
    protected ImageView      mIvNavigation;
    protected FrameLayout    mContent;
    protected NavigationView mNavigationView;
    protected DrawerLayout   mDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {

        mIvLogo = findViewById(R.id.iv_logo);
        mContent = findViewById(R.id.content);
        mNavigationView = findViewById(R.id.navigation_view);
        mDrawer = findViewById(R.id.drawer);
        mIvNavigation = mNavigationView.getHeaderView(0).findViewById(R.id.main_navigation_header_iv);

        Glide.with(this).load(R.drawable.i28731106).centerCrop().into(mIvLogo);
        Glide.with(this).load(R.drawable.i30033106).centerCrop().into(mIvNavigation);

        SystemUI.layoutFullScreen(this);

        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.color_gold:

                                SystemUI.setStatusColor(MainActivity.this, getColor("#ffd700"));
                                break;
                            case R.id.color_blue:
                                break;
                            case R.id.color_transparent:
                                break;

                            default:
                                break;
                        }

                        return true;
                    }
                });
    }


    @ColorInt
    int getColor(String color) {

        return Color.parseColor(color);
    }

}
