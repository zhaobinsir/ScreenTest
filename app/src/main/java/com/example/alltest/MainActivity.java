package com.example.alltest;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import hugo.weaving.DebugLog;

public class MainActivity extends AppCompatActivity {

    private VideoGestureLayout mVideoGestureLayout;
    private SeekBar mSeekBar;
    String TAG="MainActivity";
    int videoLength=60*1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoGestureLayout=findViewById(R.id.video_gesture);
        mSeekBar=findViewById(R.id.seek_bar);
        mSeekBar.setMax(videoLength);
        mSeekBar.getThumb().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);//设置滑块样式
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged: "+progress+"_fromUser "+fromUser);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStartTrackingTouch: "+seekBar.getProgress());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onProgressChanged: "+seekBar.getProgress());
            }
        });
        mVideoGestureLayout.setGenseePlayerListener(new GenseePlayerListener() {
            @Override
            public void onBrightnessGesture(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            }

            @Override
            public void onVolumeGesture(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            }

            @Override
            public void onVideoSpeedGesture(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.d(TAG, "onVideoSpeedGesture: alllength "+
                        (e1.getX()-e2.getX())+"e1 startx："+e1.getX()+" e2 startX："+e2.getX());
                float startx = e1.getX();
                float endx = e2.getX();
                int rewind = (int) (Math.abs(startx - endx) / ((float) mVideoGestureLayout.getWidth() / (float) videoLength));//获取滑动视频进度
                Log.d(TAG, "onVideoSpeedGesture: rewind "+rewind);
                mSeekBar.setProgress( rewind);

            }

            @Override
            public void onSingleTapGesture(MotionEvent e) {

            }

            @Override
            public void onDoubleTapGesture(MotionEvent e) {

            }

            @Override
            public void onDown(MotionEvent e) {
                Log.d(TAG, "onDown: seekbarLength "+mSeekBar.getMax());
            }

            @Override
            public void onVideoSpeedEnd(MotionEvent e) {

            }
        });
        fun1();
    }

    @DebugLog
    private String fun1(){
        String str="zbb";
        return str;
    }
}
