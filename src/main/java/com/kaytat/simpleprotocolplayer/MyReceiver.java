package com.kaytat.simpleprotocolplayer;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    public MyReceiver()
    {
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "接收到广播！"+intent.getAction(), Toast.LENGTH_LONG).show();
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
            Toast.makeText(context, "接收到广播！1111"+intent.getAction(), Toast.LENGTH_LONG).show();
            Intent i = new Intent(context, MainActivity.class);
//            i.setComponent(new ComponentName("com.kaytat.simpleprotocolplayer","com.kaytat.simpleprotocolplayer.MainActivity"));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Toast.makeText(context, "接收到广播！22222"+intent.getAction(), Toast.LENGTH_LONG).show();
            i.setAction("android.intent.action.MAIN");
            i.addCategory("android.intent.category.LAUNCHER");
            i.setClassName("com.kaytat.simpleprotocolplayer","com.kaytat.simpleprotocolplayer.MainActivity");
            context.startActivity(i);
        }
    }
}
