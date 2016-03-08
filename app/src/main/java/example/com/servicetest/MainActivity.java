package example.com.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;
    private Button setting;

    private MyService.MyBinder myBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownload("a");
            Log.d(MyService.TAG, myBinder.hashCode() + " \t " + "main");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("----");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService = (Button) findViewById(R.id.start_service);
        stopService = (Button) findViewById(R.id.stop_service);
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        setting = (Button) findViewById(R.id.second);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        setting.setOnClickListener(this);
//        Log.d(MyService.TAG,Thread.currentThread().hashCode() + " \t " + Thread.currentThread().getId());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                if(myBinder != null) myBinder.setUnBind(false);
                break;
            case R.id.unbind_service:
                if (myBinder != null && !myBinder.isUnBind()) {
                    unbindService(connection);
                    myBinder.setUnBind(true);
                }
                break;
            case R.id.second:
                Intent intent = new Intent(this,SecondActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
