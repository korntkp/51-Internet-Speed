package io.github.shredktp.a51networkspeed;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SpeedIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_START = "io.github.shredktp.a51networkspeed.action.START_SPEED";
    public static final String ACTION_STOP = "io.github.shredktp.a51networkspeed.action.STOP_SPEED";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "io.github.shredktp.a51networkspeed.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "io.github.shredktp.a51networkspeed.extra.PARAM2";

    public SpeedIntentService() {
        super("SpeedIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionSpeed(Context context/*, String param1, String param2*/) {
        Intent intent = new Intent(context, SpeedIntentService.class);
        intent.setAction(ACTION_START);
//        intent.putExtra(EXTRA_PARAM1, param1);
//        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void stopActionSpeed(Context context/*, String param1, String param2*/) {
        Intent intent = new Intent(context, SpeedIntentService.class);
        intent.setAction(ACTION_STOP);
//        intent.putExtra(EXTRA_PARAM1, param1);
//        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_START.equals(action)) {
//                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionStartSpeed(/*param1, param2*/);
            } else if (ACTION_STOP.equals(action)) {
//                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionStopSpeed(/*param1, param2*/);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionStartSpeed(/*String param1, String param2*/) {
        Log.d("StartSpeedSv", "handleActionStartSpeed: ");
        Toast.makeText(this, "adf", Toast.LENGTH_SHORT).show();

    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionStopSpeed(/*String param1, String param2*/) {
        Log.d("StopSpeedSv", "handleActionStopSpeed: ");
        stopSelf();
    }
}
