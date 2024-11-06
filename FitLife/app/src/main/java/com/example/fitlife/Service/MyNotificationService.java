//package com.example.fitlife.Service;
//
//import android.app.Notification;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.Service;
//import android.content.Intent;
//import android.os.Build;
//import android.os.IBinder;
//
//import com.example.fitlife.R;
//
//public class MyNotificationService extends Service {
//    private static final String CHANNEL_ID = "default_channel";
//    private NotificationManager notificationManager;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        // Tạo kênh thông báo cho Android 8.0 trở lên
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = "Default Channel";
//            String description = "This is the default notification channel";
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);
//            notificationManager.createNotificationChannel(channel);
//        }
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        // Tạo thông báo
//        Notification notification = new Notification.Builder(this, CHANNEL_ID)
//                .setContentTitle("Service Notification")
//                .setContentText("This notification is from the background service.")
//                .setSmallIcon(R.drawable.ic_notification)
//                .setAutoCancel(true)
//                .build();
//
//        // Gửi thông báo
//        notificationManager.notify(1, notification);
//
//        // Dừng service sau khi gửi thông báo
//        stopSelf();
//
//        return START_NOT_STICKY;
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//}
