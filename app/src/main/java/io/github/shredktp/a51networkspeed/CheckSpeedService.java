package io.github.shredktp.a51networkspeed;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.net.TrafficStats;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

public class CheckSpeedService extends Service {

    private static final int NOTIFICATION_ID = 741;

    public CheckSpeedService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showSpeed();
        return START_STICKY;
    }

    private void showSpeed() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                long startDownload = TrafficStats.getMobileRxBytes();
                long startUpload = TrafficStats.getMobileTxBytes();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                long endDownload = TrafficStats.getTotalRxBytes();
                long endUpload = TrafficStats.getTotalTxBytes();

                long downloadPerSec = endDownload - startDownload;
                long uploadPerSec = endUpload - startUpload;

                final String result = "Download: " + downloadPerSec + " Bytes/Sec" + "\n"
                        + "Upload: " + uploadPerSec + " Bytes/Sec";

                Notification notificationCompat = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("DateTime: " + MyUtility.getCurrentDateTimeInFormat())
                        .setContentText(result)
                        .build();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(NOTIFICATION_ID, notificationCompat);

//                Speed speed = new Speed(downloadPerSec, uploadPerSec);
//                // MainActivity.getInstance().handleState(this, state)
//                MainActivity.handleMessage(speed);
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        tvSpeed.setText(result);
//                    }
//                });
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 1000);
    }
}
