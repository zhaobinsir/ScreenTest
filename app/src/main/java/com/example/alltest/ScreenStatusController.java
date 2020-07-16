package com.example.alltest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by zhaobinsir
 * on 2020/7/16.
 */
public class ScreenStatusController {

    private Context mContext;
    private IntentFilter mScreenStatusFilter = null;
    private ScreenStatusListener mScreenStatusListener = null;

    public ScreenStatusController(Context context) {
        mContext = context;
        mScreenStatusFilter = new IntentFilter();
        mScreenStatusFilter.addAction(Intent.ACTION_SCREEN_ON);
        mScreenStatusFilter.addAction(Intent.ACTION_SCREEN_OFF);
        mScreenStatusFilter.addAction(Intent.ACTION_USER_PRESENT);
    }

    private BroadcastReceiver mScreenStatusReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Intent.ACTION_SCREEN_ON.equals(action)) { // 开屏
                if (mScreenStatusListener != null) {
                    mScreenStatusListener.onScreenOn();
                }
            } else if (Intent.ACTION_SCREEN_OFF.equals(action)) { // 锁屏
                if (mScreenStatusListener != null) {
                    mScreenStatusListener.onScreenOff();
                }
            } else if (Intent.ACTION_USER_PRESENT.equals(action)) { //解锁
                if (mScreenStatusListener != null) {
                    mScreenStatusListener.userPresent();
                }
            }
        }
    };

    //设置监听
    public void setScreenStatusListener(ScreenStatusListener l) {
        mScreenStatusListener = l;
    }


    //开始监听
    public void startListen() {
        if (mContext != null) {
            mContext.registerReceiver(mScreenStatusReceiver, mScreenStatusFilter);
        }
    }

    //结束监听
    public void stopListen() {
        if (mContext != null) {
            mContext.unregisterReceiver(mScreenStatusReceiver);
        }
    }
}

