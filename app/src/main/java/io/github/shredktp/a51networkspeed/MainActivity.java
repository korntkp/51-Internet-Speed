package io.github.shredktp.a51networkspeed;

import android.content.Intent;
import android.net.TrafficStats;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    static TextView tvHello;
    TextView tvSpeed;
    Button btnGetUsage;
    Button btnStartSpeedService, btnStopSpeedService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHello = (TextView) findViewById(R.id.tv_hello);
        tvSpeed = (TextView) findViewById(R.id.tv_speed);
        btnGetUsage = (Button) findViewById(R.id.btn_get_usage);
        btnStartSpeedService = (Button) findViewById(R.id.btn_enable_speed_status_bar);
        btnStopSpeedService = (Button) findViewById(R.id.btn_disable_speed_status_bar);
        btnGetUsage.setOnClickListener(this);
        btnStartSpeedService.setOnClickListener(this);
        btnStopSpeedService.setOnClickListener(this);

        updateUpDownload();
        showSpeed();
//        handleMessage();
    }

    public static void handleMessage(CheckSpeedService checkSpeedService) {
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Speed speed = (Speed) msg.obj;
                tvHello.setText(speed.getDownloadPerSec() + "\n" + speed.getUploadPerSec());
            }
        };
    }

    private void showSpeed() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                long startDownload = TrafficStats.getTotalRxBytes();
                long startUpload = TrafficStats.getTotalTxBytes();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                long endDownload = TrafficStats.getTotalRxBytes();
                long endUpload = TrafficStats.getTotalTxBytes();

                long downloadPerSec = endDownload - startDownload;
                long uploadPerSec = endUpload - startUpload;

                final String result = "DateTime: " + MyUtility.getCurrentDateTimeInFormat() + "\n"
                        + "Download: " + downloadPerSec + " Bytes/Sec" + "\n"
                        + "Upload: " + uploadPerSec + " Bytes/Sec";

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvSpeed.setText(result);
                    }
                });
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 1000);
    }

    private void updateUpDownload() {
        long download = TrafficStats.getTotalRxBytes();
        long upload = TrafficStats.getTotalTxBytes();

        String result = "Download: " + download + " Bytes\n"
                + "Upload: " + upload + " Bytes";

        tvHello.setText(result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_usage: {
                updateUpDownload();
                break;
            }
            case R.id.btn_enable_speed_status_bar: {
                // TODO: 02-Mar-17 Start Intent Service
//                SpeedIntentService.startActionSpeed(getApplicationContext());
                Intent intent = new Intent(MainActivity.this, CheckSpeedService.class);
                startService(intent);

//                Log.d(TAG, "onClick: Noti");
//
//                Notification notificationCompat = new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setContentTitle("Speed")
//                        .setContentText("in Bytes")
//                        .build();
//
//                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                notificationManager.notify(741, notificationCompat);
                break;
            }
            case R.id.btn_disable_speed_status_bar: {
                // TODO: 02-Mar-17 Stop Intent Service
//                SpeedIntentService.stopActionSpeed(getApplicationContext());
                Intent intent = new Intent(MainActivity.this, CheckSpeedService.class);
                stopService(intent);
                break;
            }
        }
    }
}
