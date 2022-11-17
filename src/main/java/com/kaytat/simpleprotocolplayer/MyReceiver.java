package com.kaytat.simpleprotocolplayer;

import static android.content.Context.MODE_PRIVATE;
import static com.kaytat.simpleprotocolplayer.MainActivity.BUFFER_MS_PREF;
import static com.kaytat.simpleprotocolplayer.MainActivity.IP_JSON_PREF;
import static com.kaytat.simpleprotocolplayer.MainActivity.IP_PREF;
import static com.kaytat.simpleprotocolplayer.MainActivity.PORT_JSON_PREF;
import static com.kaytat.simpleprotocolplayer.MainActivity.PORT_PREF;
import static com.kaytat.simpleprotocolplayer.MainActivity.RATE_PREF;
import static com.kaytat.simpleprotocolplayer.MainActivity.RETRY_PREF;
import static com.kaytat.simpleprotocolplayer.MainActivity.STEREO_PREF;
import static com.kaytat.simpleprotocolplayer.MainActivity.USE_MIN_BUFFER_PREF;
import static com.kaytat.simpleprotocolplayer.MainActivity.USE_PERFORMANCE_MODE_PREF;
import static com.kaytat.simpleprotocolplayer.MusicService.ACTION_PLAY;

import android.content.BroadcastReceiver;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import java.util.List;

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

//            i.setComponent(new ComponentName("com.kaytat.simpleprotocolplayer","com.kaytat.simpleprotocolplayer.MainActivity"));
            SharedPreferences myPrefs = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
            List<String> ipAddrList = Util.getListFromPrefs(myPrefs, IP_JSON_PREF, IP_PREF);
            List<String> audioPortList = Util.getListFromPrefs(myPrefs, PORT_JSON_PREF, PORT_PREF);
            int sampleRate = myPrefs.getInt(RATE_PREF, MusicService.DEFAULT_SAMPLE_RATE);
            boolean stereo = myPrefs.getBoolean(STEREO_PREF, MusicService.DEFAULT_STEREO);
            int bufferMs = myPrefs.getInt(BUFFER_MS_PREF, MusicService.DEFAULT_BUFFER_MS);
            boolean retry = myPrefs.getBoolean(RETRY_PREF, MusicService.DEFAULT_RETRY);
            boolean usePerformanceMode = myPrefs.getBoolean(USE_PERFORMANCE_MODE_PREF, MusicService.DEFAULT_USE_PERFORMANCE_MODE);
            boolean useMinBuffer = myPrefs.getBoolean(USE_MIN_BUFFER_PREF, MusicService.DEFAULT_USE_MIN_BUFFER);
            Toast.makeText(context, "接收到广播！22222"+intent.getAction(), Toast.LENGTH_LONG).show();
            Intent i = new Intent(context, MusicService.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setAction(ACTION_PLAY);
            i.putExtra(MusicService.DATA_IP_ADDRESS, ipAddrList.get(0));
            i.putExtra(MusicService.DATA_AUDIO_PORT, audioPortList.get(0));
            i.putExtra(MusicService.DATA_SAMPLE_RATE, sampleRate);
            i.putExtra(MusicService.DATA_STEREO, stereo);
            i.putExtra(MusicService.DATA_BUFFER_MS, bufferMs);
            i.putExtra(MusicService.DATA_RETRY, retry);
            i.putExtra(MusicService.DATA_USE_PERFORMANCE_MODE, usePerformanceMode);
            i.putExtra(MusicService.DATA_USE_MIN_BUFFER, useMinBuffer);
            i.setPackage("com.kaytat.simpleprotocolplayer");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(i);
            }else{
                context.startService(i);
            }
        }
    }
}
