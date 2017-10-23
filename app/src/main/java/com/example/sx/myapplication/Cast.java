package com.example.sx.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by sx on 2017/10/16.
 */

public class Cast extends BroadcastReceiver {
    public static String action = "android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.equals(action)) {
            Intent bootIntent = new Intent(context, MainActivity.class);
            bootIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(bootIntent);
        }
    }
}
