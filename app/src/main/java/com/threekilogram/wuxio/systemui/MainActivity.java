package com.threekilogram.wuxio.systemui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.threekilogram.systemui.SystemUi;

/**
 * @author liujin
 */
public class MainActivity extends AppCompatActivity {

      private static final String TAG = MainActivity.class.getSimpleName();

      protected ImageView      mIvLogo;
      protected FrameLayout    mContent;
      protected NavigationView mNavigationView;
      protected DrawerLayout   mDrawer;

      @Override
      protected void onCreate ( Bundle savedInstanceState ) {

            super.onCreate( savedInstanceState );
            super.setContentView( R.layout.activity_main );
            initView();
            Log.e( TAG, "onCreate : " );
      }

      private void initView ( ) {

            mIvLogo = findViewById( R.id.iv_logo );
            mContent = findViewById( R.id.content );
            mNavigationView = findViewById( R.id.navigation_view );
            mDrawer = findViewById( R.id.drawer );

            Glide.with( this ).load( R.drawable.i28731106 ).centerCrop().into( mIvLogo );

            final Menu menu = mNavigationView.getMenu();
            mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                      @Override
                      public boolean onNavigationItemSelected ( @NonNull MenuItem item ) {

                            switch( item.getItemId() ) {

                                  case R.id.kitkat_translucent:
                                        SystemUi.translucentStatus( MainActivity.this );
                                        break;

                                  case R.id.kitkat_un_translucent:
                                        SystemUi.clearTranslucentStatus( MainActivity.this );
                                        break;

                                  case R.id.transparent:
                                        SystemUi.transparentStatus( MainActivity.this );
                                        break;

                                  case R.id.clearTranslucent:
                                        SystemUi.clearTransparentStatus( MainActivity.this );
                                        break;

                                  case R.id.gold44:
                                        SystemUi.setKitkatStatusColor(
                                            MainActivity.this,
                                            getColorFromRes( R.color.gold )
                                        );
                                        break;

                                  case R.id.blue44:
                                        SystemUi.setKitkatStatusColor(
                                            MainActivity.this,
                                            getColorFromRes( R.color.colorPrimaryDark )
                                        );
                                        break;

                                  case R.id.clear44:
                                        SystemUi.clearKitkatStatusColor(
                                            MainActivity.this
                                        );
                                        break;

                                  case R.id.gold50:
                                        SystemUi.setLollipopStatusColor(
                                            MainActivity.this,
                                            getColorFromRes( R.color.gold )
                                        );
                                        break;

                                  case R.id.blue50:
                                        SystemUi.setLollipopStatusColor(
                                            MainActivity.this,
                                            getColorFromRes( R.color.colorPrimaryDark )
                                        );
                                        break;

                                  case R.id.clear50:
                                        SystemUi.clearLollipopStatusColor(
                                            MainActivity.this
                                        );
                                        break;

                                  case R.id.fullScreenTemporary:
                                        SystemUi.fullScreenTemporary(
                                            MainActivity.this
                                        );
                                        break;

                                  case R.id.hideNavigation:
                                        SystemUi.hideNavigationBarTemporary(
                                            MainActivity.this
                                        );
                                        break;

                                  case R.id.immersiveRelayout:
                                        SystemUi.fullScreenTemporaryStable(
                                            MainActivity.this
                                        );
                                        break;

                                  case R.id.immersiveSticky:
                                        SystemUi.immersive(
                                            MainActivity.this
                                        );
                                        break;

                                  case R.id.clear:
                                        SystemUi.clearHide(
                                            MainActivity.this
                                        );
                                        break;

                                  case R.id.drawerFit:
                                        SystemUi.fitStatusBarHeight( mDrawer );
                                        break;

                                  case R.id.drawerUnFit:
                                        SystemUi.unFitStatusBarHeight( mDrawer );
                                        break;

                                  case R.id.contentFit:
                                        SystemUi.fitStatusBarHeight( mContent );
                                        break;

                                  case R.id.contentUnFit:
                                        SystemUi.unFitStatusBarHeight( mContent );
                                        break;

                                  default:
                                        break;
                            }

                            closeDrawer();
                            return true;
                      }
                }
            );
      }

      @ColorInt
      int getColorFromRes ( int color ) {

            return getResources().getColor( color );
      }

      void closeDrawer ( ) {

            mDrawer.closeDrawer( Gravity.START );
      }

      @Override
      public void onConfigurationChanged ( Configuration newConfig ) {

            super.onConfigurationChanged( newConfig );
            Log.e( TAG, "onConfigurationChanged : " );
      }

      @Override
      protected void onStart ( ) {

            super.onStart();
            Log.e( TAG, "onStart : " );
      }

      @Override
      protected void onStop ( ) {

            super.onStop();
            Log.e( TAG, "onStop : " );
      }

      @Override
      protected void onDestroy ( ) {

            super.onDestroy();
            Log.e( TAG, "onDestroy : " );
      }

      @Override
      protected void onPause ( ) {

            super.onPause();
            Log.e( TAG, "onPause : " );
      }

      @Override
      protected void onResume ( ) {

            super.onResume();
            Log.e( TAG, "onResume : " );
      }
}
