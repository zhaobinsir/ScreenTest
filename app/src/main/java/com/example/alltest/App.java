package com.example.alltest;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;

/**
 * Created by zhaobinsir
 * on 2020/7/16.
 */
public class App extends Application {

    public static final String TAG="MYAPP";

    private ScreenStatusController mScreenStatusController = null;
    private Handler mHandlder;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        init();
    }

    private void init() {
        mHandlder=new Handler();
        mScreenStatusController=new ScreenStatusController(this);
        //注册广播监听锁屏状态
        mScreenStatusController.setScreenStatusListener(new ScreenStatusListener() {
            @Override
            public void onScreenOn() {
                Log.e(TAG, "onScreenOn: ");
            }

            @Override
            public void onScreenOff() {//锁屏时点亮屏幕
                Log.e(TAG, "onScreenOff: ");
                mHandlder.removeCallbacksAndMessages(null);//防止用户快速开关屏幕
                mHandlder.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "run: 唤醒");
                        PowerManager manager = (PowerManager) getSystemService(Context.POWER_SERVICE);
                        @SuppressLint("InvalidWakeLockTag")
                        PowerManager.WakeLock newWakeLock = manager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP |
                                PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
                        newWakeLock.acquire();//点亮屏幕(常亮)
                        Intent intent=new Intent(getApplicationContext(), Main2Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }, 2000);
            }

            @Override
            public void userPresent() {
                Log.e(TAG, "userPresent: ");
            }
        });
        mScreenStatusController.startListen();
    }

}
