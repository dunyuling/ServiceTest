package example.com.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private Button startService;
    private Button bindService;
    private Button stopService;
    private Button unbindService;

    private MyService.MyBinder myBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownload("b");
            Log.d(MyService.TAG, myBinder.hashCode() + " \t " + "second");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("----");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        startService = (Button) findViewById(R.id.start_service);
        bindService = (Button) findViewById(R.id.bind_service);
        stopService = (Button) findViewById(R.id.stop_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        bindService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.unbind_service:
                if (myBinder != null && !myBinder.isUnBind()) {
                    unbindService(connection);
                    myBinder.setUnBind(true);
                }
                break;
            default:
                break;
        }
    }
}
