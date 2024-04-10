package ro.pub.cs.systems.eim.Colocviu1_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Colocviu1_2BroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action != null) {
                if(action.equals(Constants.SUM_ACTION)) {
                    String dataReceived = intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA);
                    Log.d("Colocviu1_2BroadcastReceiver", "Received message: " + dataReceived);
                }
            }
        }

}

