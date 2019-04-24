package tech.liujin.wuxio.systemui;

import android.content.res.Configuration;
import android.os.Build.VERSION_CODES;
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
import com.liujin.wuxio.systemui.R;
import tech.liujin.systemui.SystemUi;

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

                      @android.support.annotation.RequiresApi(api = VERSION_CODES.KITKAT)
                      @Override
                      public boolean onNavigationItemSelected ( @NonNull MenuItem item ) {

                            switch( item.getItemId() ) {

                                  case R.id.kitkat_translucent:
                                        TranslucentActivity.start( MainActivity.this );
                                        break;

                                  case R.id.transparent:
                                        TransparentActivity.start( MainActivity.this );
                                        break;

                                  case R.id.gold44:
                                        Gold44Activity.start( MainActivity.this );
                                        break;

                                  case R.id.blue44:
                                        Blue44Activity.start( MainActivity.this );
                                        break;

                                  case R.id.clear44:
                                        SystemUi.clearKitkatStatusColor( MainActivity.this );
                                        break;

                                  case R.id.gold50:
                                        Gold50Activity.start( MainActivity.this );
                                        break;

                                  case R.id.blue50:
                                        Blue50Activity.start( MainActivity.this );
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

                                  case R.id.fullScreenTemporaryStable:
                                        SystemUi.fullScreenTemporaryStable(
                                            MainActivity.this
                                        );
                                        break;

                                  case R.id.immersive:
                                        SystemUi.immersive(
                                            MainActivity.this
                                        );
                                        break;

                                  case R.id.clear:
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
