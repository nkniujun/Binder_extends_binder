package com.example.niujun.njbindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by niujun on 2018/10/17.
 */

public class LocalService extends Service {

    String TAG = "Nj";
    int i = 0;

    private final IBinder mBinder = new LocalBinder();  // Binder given to clients

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    /**
     * 客户端用来和服务器进行交互的接口
     * 得到IBinder常用的3种方式： 继承Binder类，使用Messenger类，使用AIDL
     * IBinder是一个在整个Android系统中都非常重要的东西，是为高性能而设计的轻量级远程调用机制的核心部分。
     * 不仅仅可以用于远程调用IPC，也可以用于进程内调用.
     */
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public class LocalBinder extends Binder {
        LocalService getService() {
            //得到目标service的对象，然后可以调用其内的共有方法，实现客户端与服务端交互的目的。
            return LocalService.this;
        }
    }

    public void print() {
        Log.d(TAG, "print: " + i++);
    }
}
