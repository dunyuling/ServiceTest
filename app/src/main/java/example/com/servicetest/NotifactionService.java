package example.com.servicetest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

public class NotifactionService extends Service {
    public NotifactionService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        test();
        Log.i("aaa","notification:  pid: " +android.os.Process.myPid() + "\t thread: " + Thread.currentThread().getId() + "\t uid: " + android.os.Process.myUid() + "\t tid: " + android.os.Process.myTid() );
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    void test() {
        //简版
        RemoteViews remoteViews = new RemoteViews(getPackageName(),
                R.layout.notification);

        View mRoot = LayoutInflater.from(this).inflate(R.layout.notification,null);
        final Context context = this;
        mRoot.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"abcde",Toast.LENGTH_SHORT).show();
            }
        });

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setContent(remoteViews)
                        .setNumber(1)
                        .setContentInfo("1s")
//                        .setAutoCancel(true)
                ;


        Intent resultIntent = new Intent(this,NotificationResultActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,111,resultIntent,PendingIntent.FLAG_NO_CREATE);
        mBuilder.setContentIntent(resultPendingIntent);

        int mNotificationId = 001;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify("abc",mNotificationId, mBuilder.build());

        //Regular Activity PendingIntent
        /*int id = 1;
        Intent resultIntent = new Intent(this, NotificationResultActivity.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(NotificationResultActivity.class);
        taskStackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = taskStackBuilder.getPendingIntent(111, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)        .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("aa").setContentText("bb").setAutoCancel(true);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify(id, mBuilder.build());*/

        /*
        Special Activity PendingIntent
        // Instantiate a Builder object.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setContentTitle("cc").setContentText("dd");
        // Creates an Intent for the Activity
        Intent notifyIntent =
                new Intent(new ComponentName(this, NotificationResultActivity.class).toString());
        // Sets the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // Creates the PendingIntent
        PendingIntent notifyIntent1 =
                PendingIntent.getActivity(
                        this,
                        0,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        // Puts the PendingIntent into the notification builder
        builder.setContentIntent(notifyIntent1);
        // Notifications are issued by sending them to the
        // NotificationManager system service.
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Builds an anonymous Notification object from the builder, and
        // passes it to the NotificationManager
        mNotificationManager.notify(0, builder.build());*/



    }
}
