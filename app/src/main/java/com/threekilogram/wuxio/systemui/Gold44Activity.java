package com.threekilogram.wuxio.systemui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import tech.liujin.systemui.SystemUi;

/**
 * @author liujin
 */
public class Gold44Activity extends MainActivity {

      public static void start ( Context context ) {

            Intent starter = new Intent( context, Gold44Activity.class );
            context.startActivity( starter );
      }

      @Override
      protected void onCreate ( Bundle savedInstanceState ) {

            super.onCreate( savedInstanceState );

            SystemUi.setKitkatStatusColor(
                Gold44Activity.this,
                getColorFromRes( R.color.gold )
            );
      }
}
