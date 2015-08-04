package com.example.jerryyin.servicetestdemo01.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by JerryYin on 8/3/15.
 * 后台service,   ,但注意，后台 ！＝ 线程 ，service是运行在主线程中运行的；
 */
public class TestService extends Service {

    public static final String TAG = "TestService";
    private MyBinder mBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() executed");
        mBinder = new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        //再这里开启线程，完成比较耗时的操作，是比较好的
        //开始执行后台任务
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    /**
     * 服务要执行的任务
     */
    public class MyBinder extends Binder{
        public void startDownload(){
            Log.d(TAG, "startDownload() executed");
            //执行服务中具体的下载操作
            new Thread(new Runnable() {
                @Override
                public void run() {

                }
            }).start();
        }
    }

}
