package example.com.servicetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class FrontActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        Intent intent = new Intent(this,NotifactionService.class);
        startService(intent);
        Log.i("aaa","activity:  pid: " +android.os.Process.myPid() + "\t thread: " + Thread.currentThread().getId() + "\t uid: " + android.os.Process.myUid() + "\t tid: " + android.os.Process.myTid() + "\t ppid: "
                );
    }
}
