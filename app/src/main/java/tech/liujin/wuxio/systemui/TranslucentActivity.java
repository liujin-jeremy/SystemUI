package tech.liujin.wuxio.systemui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import tech.liujin.systemui.SystemUi;

/**
 * @author liujin
 */
public class TranslucentActivity extends MainActivity {

      public static void start ( Context context ) {

            Intent starter = new Intent( context, TranslucentActivity.class );
            context.startActivity( starter );
      }

      @Override
      protected void onCreate ( Bundle savedInstanceState ) {

            super.onCreate( savedInstanceState );

            SystemUi.translucentStatus( this );
      }
}
