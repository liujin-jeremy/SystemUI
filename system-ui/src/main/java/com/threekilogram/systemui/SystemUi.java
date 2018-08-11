package com.threekilogram.systemui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

/**
 * 状态栏颜色设置工具
 *
 * @author wuxio 2018-04-22:17:51
 */
public class SystemUi {

      private static final String TAG = SystemUi.class.getSimpleName();

      /**
       * 设置状态栏半透明,并且activity布局延伸到状态栏下面
       *
       * @param activity activity
       */
      public static void translucentStatus ( Activity activity ) {

            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {

                  activity.getWindow()
                          .addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
            }
      }

      /**
       * 设置状态栏半透明,并且activity布局延伸到状态栏下面
       *
       * @param activity activity
       */
      public static void clearTranslucentStatus ( Activity activity ) {

            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {

                  activity.getWindow()
                          .clearFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
            }
      }

      /**
       * 状态栏透明
       *
       * @param activity activity
       */
      public static void transparentStatus ( Activity activity ) {

            if( Build.VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP ) {

                  activity.getWindow()
                          .addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
            }
      }

      /**
       * 取消状态栏透明
       *
       * @param activity activity
       */
      public static void clearTransparentStatus ( Activity activity ) {

            if( Build.VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP ) {

                  activity.getWindow()
                          .clearFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
            }
      }

      /**
       * 设置状态栏颜色
       *
       * @param activity 需要设置状态栏颜色的activity
       * @param color 状态栏颜色
       */
      public static void setStatusColor ( Activity activity, @ColorInt int color ) {

            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
                  setLollipopStatusColor( activity, color );
                  return;
            }

            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
                  setKitkatStatusColor( activity, color );
            }
      }

      /**
       * 使用{@link Window#setStatusBarColor(int)}API设置状态栏颜色
       *
       * @param activity 需要设置状态栏颜色的activity
       * @param color 状态栏颜色
       */
      public static void setLollipopStatusColor ( Activity activity, @ColorInt int color ) {

            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
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
      public static void setKitkatStatusColor ( Activity activity, @ColorInt int color ) {

            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {

                  Window window = activity.getWindow();

                  //添加一个view,并设置颜色
                  ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();

                  View statusBarView = decorViewGroup
                      .findViewById( R.id.system_ui_status_bar_view );

                  /* 如果没有该view添加一个 */
                  if( statusBarView == null ) {

                        //状态栏半透明,才能显示颜色
                        window.addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );

                        statusBarView = new View( window.getContext() );
                        statusBarView.setId( R.id.system_ui_status_bar_view );
                        int statusBarHeight = getStatusBarHeight( window.getContext() );
                        LayoutParams params = new LayoutParams(
                            LayoutParams.MATCH_PARENT,
                            statusBarHeight
                        );

                        decorViewGroup.getChildAt( 0 ).setFitsSystemWindows( false );
                        decorViewGroup.addView( statusBarView, 0, params );
                  }

                  statusBarView.setBackgroundColor( color );
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

            int statusBarHeight = 0;
            Resources res = context.getResources();
            int resourceId = res.getIdentifier(
                "status_bar_height",
                "dimen",
                "android"
            );
            if( resourceId > 0 ) {
                  statusBarHeight = res.getDimensionPixelSize( resourceId );
            }
            return statusBarHeight;
      }

      /**
       * 清除设置的颜色
       *
       * @param activity activity
       */
      public static void clearKitkatStatusColor ( Activity activity ) {

            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
                  Window window = activity.getWindow();

                  //移除添加的view
                  ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
                  View statusBarView = decorViewGroup
                      .findViewById( R.id.system_ui_status_bar_view );
                  if( statusBarView != null ) {

                        decorViewGroup.removeView( statusBarView );
                        decorViewGroup.getChildAt( 0 ).setFitsSystemWindows( true );
                        /*清除状态栏半透明*/
                        window.clearFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
                  }
            }
      }

      /**
       * 清除状态栏设置的颜色
       *
       * @param activity activity
       */
      public static void clearLollipopStatusColor ( Activity activity ) {

            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
                  Window window = activity.getWindow();
                  //设置状态栏颜色必须清除WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS标记
                  window.clearFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
                  //设置状态栏颜色必须设置WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS标记
                  window.clearFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS );
            }
      }

      /**
       * 竖直方向偏移状态栏距离
       *
       * @param views 需要偏移的views
       */
      public static void fitStatusBarHeight ( View... views ) {

      }

      /**
       * 竖直负方向偏移状态栏距离,需要自己保证view之前偏移过状态栏高度,不要多次调用给同一个view
       */
      public static void unFitStatusBarHeight ( View... views ) {

      }

      /**
       * 导航栏状态栏临时隐藏,activity布局占据他们位置,
       * 在状态栏位置下拉,状态栏出现不会引发activity布局向下移动,
       * 导航栏出现会向上挤压activity
       *
       * @param activity activity
       */
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
      public static void immersive ( Activity activity ) {

            activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY );
      }

      /**
       * 清除所有隐藏状态
       *
       * @param activity activity
       */
      public static void clearHide ( Activity activity ) {

            activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_VISIBLE
            );
      }
}
