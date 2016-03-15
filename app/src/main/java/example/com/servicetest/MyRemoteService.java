package example.com.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyRemoteService extends Service {
    public MyRemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    MyAidlService.Stub mBinder = new MyAidlService.Stub() {

        @Override
        public int plus(int a, int b) throws RemoteException {
            Log.d("TAG","a: " + a + "\t b: " + b );
            return a + b;
        }

        @Override
        public String toUpperCase(String str) throws RemoteException {
            if (str != null) {
                return str.toUpperCase();
            }
            return null;
        }
    };
}
