package com.example.niujun.njbindservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.nio.file.FileAlreadyExistsException;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    String TAG = "Nj";

    LocalService mService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button_bind_service);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "bindService ~");
                Intent intent = new Intent(MainActivity.this, LocalService.class);
                /**
                 * BIND_AUTO_CREATE : 创建尚未激活的服务
                 */
                bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
                if (mService != null) {
                    mService.print();
                }
            }
        });
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "-----onServiceConnected-------");
            Log.d(TAG, "name.getClassName(): " + name.getClassName());
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            mService = binder.getService();
            mService.print();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
            Log.d(TAG, "****onServiceDisconnected****");
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        unbindService(mServiceConnection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
