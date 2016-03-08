package example.com.servicetest;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by liuhg on 16-3-8.
 */
public class MyService extends Service {

    public static final String TAG = "MyService";
    private MyBinder myBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
//        Log.d(TAG,Thread.currentThread().hashCode() + " \t " + Thread.currentThread().getId());
        Log.d(TAG, "onCreate() executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    class MyBinder extends Binder {
        public void startDownload(String str) {
            Log.d(TAG, "startDownload() executed" + " \t " + str);
        }

        private boolean unBind = false;

        public boolean isUnBind() {
            return unBind;
        }

        public void setUnBind(boolean unBind) {
            this.unBind = unBind;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
