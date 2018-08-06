package com.threekilogram.wuxio.systemui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.threekilogram.systemui.SystemUi;

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

        SystemUi.layoutFullScreen(this);

        final Menu menu = mNavigationView.getMenu();
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    private boolean flag = false;


                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.color_gold:
                                SystemUi.setStatusColor(MainActivity.this, getColor("#ffd700"));
                                break;

                            case R.id.color_blue:
                                SystemUi.setStatusColor(
                                        MainActivity.this,
                                        getResources().getColor(R.color.colorPrimaryDark)
                                );
                                break;

                            case R.id.color_transparent:
                                SystemUi.setStatusColor(
                                        MainActivity.this,
                                        Color.TRANSPARENT
                                );
                                break;

                            case R.id.fitSystemBar:
                                if (!flag) {
                                    SystemUi.fitStatusBarHeight(MainActivity.this, mIvLogo);
                                    flag = true;
                                }
                                break;

                            case R.id.notFitSystemBar:
                                if (flag) {
                                    SystemUi.doNotFitStatusBarHeight(MainActivity.this, mIvLogo);
                                    flag = false;
                                }
                                break;

                            case R.id.translucent:
                                SystemUi.translucentStatus(MainActivity.this);
                                break;

                            case R.id.immersive:
                                SystemUi.immersiveSticky(MainActivity.this);
                                break;

                            case R.id.normal:
                                SystemUi.normal(MainActivity.this);
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


    int getCheckedColor(Menu menu) {

        if (menu.findItem(R.id.color_gold).isChecked()) {
            return getColor("#ffd700");
        }
        if (menu.findItem(R.id.color_blue).isChecked()) {
            return getResources().getColor(R.color.colorPrimaryDark);
        }
        if (menu.findItem(R.id.color_gold).isChecked()) {
            return Color.TRANSPARENT;
        }

        return getResources().getColor(R.color.colorPrimaryDark);
    }

}
