package com.example.jerryyin.servicetestdemo01.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.jerryyin.servicetestdemo01.R;
import com.example.jerryyin.servicetestdemo01.ServiceMainActivity;

/**
 * Created by JerryYin on 8/4/15.
 * 前台Service,
 */
public class ForegroundService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
        //设置为前台服务的设置部分
        Notification notification = new Notification(R.drawable.notification_template_icon_bg, "有通知到来", System.currentTimeMillis());
        Intent notificationIntent = new Intent(this, ServiceMainActivity.class);    // 点击通知就跳转进入的Activity
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        notification.setLatestEventInfo(this, "通知标题", "通知内容", pendingIntent);
        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
