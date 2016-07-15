package com.example.jc.broadcastreceiversdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by c_bpras on 14/7/16.
 */
public class ReceiverExplicit extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(MainActivity.ACTION_BROADCAST)) {
            Log.v("Test", "ReceiverExplicit: onReceive");
        }
    }
}
