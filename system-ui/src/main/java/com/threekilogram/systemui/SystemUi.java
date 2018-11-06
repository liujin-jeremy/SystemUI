package com.threekilogram.systemui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.WindowManager;

/**
 * 状态栏颜色设置工具
 *
 * @author wuxio 2018-04-22:17:51
 */
public class SystemUi {

      private static int sStatusViewID;

      /**
       * 设置状态栏半透明,并且activity布局延伸到状态栏下面
       *
       * @param activity activity
       */
      public static void translucentStatus ( Activity activity ) {

            if( Build.VERSION.SDK_INT >= VERSION_CODES.KITKAT ) {

                  Window window = activity.getWindow();
                  window.addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
            }
      }

      /**
       * 状态栏透明,并且activity布局延伸到状态栏下面
       *
       * @param activity activity
       */
      public static void transparentStatus ( Activity activity ) {

            if( Build.VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP ) {

                  setLollipopStatusColor( activity, Color.parseColor( "#00000000" ) );
                  activity.getWindow()
                          .getDecorView()
                          .setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN );
            }
      }

      /**
       * 设置状态栏颜色
       *
       * @param activity 需要设置状态栏颜色的activity
       * @param color 状态栏颜色
       */
      public static void setStatusColor ( Activity activity, @ColorInt int color ) {

            if( Build.VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP ) {
                  setLollipopStatusColor( activity, color );
                  return;
            }

            if( Build.VERSION.SDK_INT >= VERSION_CODES.KITKAT ) {
                  setKitkatStatusColor( activity, color );
            }
      }

      /**
       * 设置状态栏颜色
       *
       * @param activity 需要设置状态栏颜色的activity
       * @param color 状态栏颜色
       */
      public static void setLollipopStatusColor ( Activity activity, @ColorInt int color ) {

            if( Build.VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP ) {

                  Window window = activity.getWindow();

                  //设置状态栏颜色必须清除WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS标记
                  window.clearFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );

                  //设置状态栏颜色必须设置WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS标记
                  window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS );

                  //绘制透明状态栏
                  window.setStatusBarColor( color );
            }
      }

      /**
       * 使用{@link Window#addFlags(int)}{@link WindowManager.LayoutParams#FLAG_TRANSLUCENT_STATUS},
       * 配合添加一个设置为指定颜色的view到状态栏区域来间接设置状态栏颜色
       *
       * @param activity 需要设置状态栏颜色的activity
       * @param color 状态栏颜色
       */
      public static void setKitkatStatusColor (
          Activity activity, @ColorInt int color ) {

            if( Build.VERSION.SDK_INT >= VERSION_CODES.KITKAT ) {

                  Window window = activity.getWindow();

                  /* 状态栏半透明 */
                  window.addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );

                  ViewGroup systemContent = activity.findViewById( android.R.id.content );
                  if( sStatusViewID == 0 ) {
                        sStatusViewID = View.generateViewId();
                  }
                  View statusBarView = activity.findViewById( sStatusViewID );

                  if( statusBarView == null ) {

                        statusBarView = new View( activity );
                        statusBarView.setId( sStatusViewID );
                        int statusBarHeight = getStatusBarHeight( activity );
                        LayoutParams lp = new LayoutParams(
                            LayoutParams.MATCH_PARENT,
                            statusBarHeight
                        );

                        /* add a margin */

                        MarginLayoutParams layoutParams =
                            (MarginLayoutParams) systemContent.getChildAt( 0 ).getLayoutParams();
                        int topMargin = layoutParams.topMargin;
                        layoutParams.topMargin = topMargin + statusBarHeight;

                        /* add backGround */

                        systemContent.addView( statusBarView, 0, lp );
                  }

                  statusBarView.setBackgroundColor( color );
            }
      }

      /**
       * 清除kitkat status view
       *
       * @param activity activity
       */
      public static void clearKitkatStatusColor ( Activity activity ) {

            if( Build.VERSION.SDK_INT >= VERSION_CODES.KITKAT ) {

                  ViewGroup systemContent = activity.findViewById( android.R.id.content );
                  View statusBarView = activity.findViewById( sStatusViewID );

                  if( statusBarView != null ) {

                        systemContent.removeViewAt( 0 );

                        /* add a margin */

                        MarginLayoutParams layoutParams =
                            (MarginLayoutParams) systemContent.getChildAt( 0 ).getLayoutParams();
                        int topMargin = layoutParams.topMargin;
                        layoutParams.topMargin = topMargin - getStatusBarHeight( activity );
                  }
            }
      }

      /**
       * 获取状态栏高度
       *
       * @param context context
       *
       * @return 高度
       */
      public static int getStatusBarHeight ( Context context ) {

            Resources res = context.getResources();
            int resourceId = res.getIdentifier(
                "status_bar_height",
                "dimen",
                "android"
            );
            try {
                  return res.getDimensionPixelSize( resourceId );
            } catch(NotFoundException e) {
                  /* nothing */
            }

            DisplayMetrics displayMetrics = res.getDisplayMetrics();
            return (int) displayMetrics.density * 24;
      }

      /**
       * 导航栏状态栏临时隐藏,activity布局占据他们位置,
       * 在状态栏位置下拉,状态栏出现不会引发activity布局向下移动,
       * 导航栏出现会向上挤压activity
       *
       * @param activity activity
       */
      @RequiresApi(16)
      public static void fullScreenTemporary ( Activity activity ) {

            activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            );
      }

      /**
       * 临时隐藏状态栏导航条,下拉出现,之后不会消失,相对{@link #fullScreenTemporary(Activity)}不会挤压activity底部
       *
       * @param activity activity
       */
      @RequiresApi(19)
      public static void fullScreenTemporaryStable ( Activity activity ) {

            activity.getWindow().getDecorView().setSystemUiVisibility(

                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    | View.SYSTEM_UI_FLAG_IMMERSIVE );
      }

      /**
       * 临时隐藏导航条,activity布局占据导航条位置,点击屏幕出现,不会触发activity布局向上移动
       *
       * @param activity activity
       */
      public static void hideNavigationBarTemporary ( Activity activity ) {

            activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            );
      }

      /**
       * 同时隐藏状态栏导航条,下拉出现(状态栏导航条半透明),之后会消失
       *
       * @param activity activity
       */
      @RequiresApi(19)
      public static void immersive ( Activity activity ) {

            activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY );
      }
}
