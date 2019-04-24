package com.threekilogram.wuxio.systemui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import tech.liujin.systemui.SystemUi;

/**
 * @author liujin
 */
public class Gold50Activity extends MainActivity {

      public static void start ( Context context ) {

            Intent starter = new Intent( context, Gold50Activity.class );
            context.startActivity( starter );
      }

      @Override
      protected void onCreate ( Bundle savedInstanceState ) {

            super.onCreate( savedInstanceState );

            SystemUi.setLollipopStatusColor(
                Gold50Activity.this,
                getColorFromRes( R.color.gold )
            );
      }
}
