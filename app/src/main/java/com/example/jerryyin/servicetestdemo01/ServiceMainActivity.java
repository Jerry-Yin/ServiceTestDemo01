package com.example.jerryyin.servicetestdemo01;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.jerryyin.servicetestdemo01.service.ForegroundService;
import com.example.jerryyin.servicetestdemo01.service.TestService;


public class ServiceMainActivity extends ActionBarActivity implements View.OnClickListener {


    private Button mbtnStartService, mbtnStopService, mbtnBindService, mbtnUnBindService, mbtnStartFogService, mbtnStopFogService;

    private MyServiceConnection connection;
    private TestService.MyBinder mBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main);
        setupViews();

    }

    public void setupViews(){
        connection = new MyServiceConnection();

        mbtnStartService = (Button) findViewById(R.id.btn_start_service);
        mbtnStopService = (Button) findViewById(R.id.btn_stop_service);
        mbtnBindService = (Button) findViewById(R.id.btn_bind_servce);
        mbtnUnBindService = (Button) findViewById(R.id.btn_unbind_serice);
        mbtnStartFogService = (Button) findViewById(R.id.btn_start_foreground_service);
        mbtnStopFogService = (Button) findViewById(R.id.btn_stop_foreground_service);

        mbtnStartService.setOnClickListener(this);
        mbtnStopService.setOnClickListener(this);
        mbtnBindService.setOnClickListener(this);
        mbtnUnBindService.setOnClickListener(this);
        mbtnStartFogService.setOnClickListener(this);
        mbtnStopFogService.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_service:
                Intent startIntent = new Intent(this, TestService .class);
                startService(startIntent);
                break;

            case R.id.btn_stop_service:
                Intent stopIntent = new Intent(this, TestService.class);
                stopService(stopIntent);
                break;

            case R.id.btn_bind_servce:
                Intent bindIntent = new Intent(this, TestService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;

            case R.id.btn_unbind_serice:
                unbindService(connection);
                break;

            case R.id.btn_start_foreground_service:
                Intent startFogIntent = new Intent(this, ForegroundService.class);
                startService(startFogIntent);
                break;

            case R.id.btn_stop_foreground_service:
                Intent stopFogIntent = new Intent(this, ForegroundService.class);
                stopService(stopFogIntent);
                break;

            default:
                break;


        }
    }



    /**
     * 用于Activity 与 Service之间的关联
     */
    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //execute when the Activity start connection with the service
            mBinder = (TestService.MyBinder) service;
            mBinder.startDownload();    //  执行服务操作任务
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //execute when the Activity stop connection with the service

        }
    }
}
