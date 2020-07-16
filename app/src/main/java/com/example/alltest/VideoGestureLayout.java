package com.example.alltest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

//用于处理视频手势的处理
public class VideoGestureLayout extends ConstraintLayout implements View.OnTouchListener {
    
    public static final String TAG="TESTLog";
    private static final int NONE = 0, VOLUME = 1, BRIGHTNESS = 2, FF_REW = 3;
    private int mScrollMode = NONE;
    private GenseePlayOnGestureListener mGenseePlayOnGestureListener;//手势控制实现类
    private GestureDetector mGestureDetector;//手势控制
    private GenseePlayerListener mGenseePlayerListener;//用于播放回调
    //横向偏移检测，让快进快退不那么敏感
    private int offsetX = 1;
    private boolean videoSpeedEnd = false;


    public VideoGestureLayout(Context context) {
        super(context);
        init(context);
    }

    public VideoGestureLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VideoGestureLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        super.setOnTouchListener(this);
        mGenseePlayOnGestureListener = new GenseePlayOnGestureListener();
        mGestureDetector = new GestureDetector(context, mGenseePlayOnGestureListener);
    }

    public void setGenseePlayerListener(GenseePlayerListener mGenseePlayerListener) {
        this.mGenseePlayerListener = mGenseePlayerListener;
    }

  /**
   * 拦截事件，禁止子View消费事件
   */
    /*@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
*/

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (videoSpeedEnd) {
                if (mGenseePlayerListener != null) {
                    mGenseePlayerListener.onVideoSpeedEnd(event);
                }
                videoSpeedEnd = false;
            }
        }
        //监听触摸事件
        return mGestureDetector.onTouchEvent(event);
    }

    public class GenseePlayOnGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {
            videoSpeedEnd = false;
            //每次按下都重置为NONE
            mScrollMode = NONE;
            if (mGenseePlayerListener != null) {
                mGenseePlayerListener.onDown(e);
            }
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            switch (mScrollMode) {
                case NONE:
                    Log.i(TAG, "NONE: ");
                    //offset是让快进快退不要那么敏感的值
                    if (Math.abs(distanceX) - Math.abs(distanceY) > offsetX) {
                        mScrollMode = FF_REW;
                    } else {
                        if (e1.getX() < getWidth() / 2) {
                            mScrollMode = BRIGHTNESS;
                        } else {
                            mScrollMode = VOLUME;
                        }
                    }
                    break;
                case VOLUME:
                    if (mGenseePlayerListener != null) {
                        mGenseePlayerListener.onVolumeGesture(e1, e2, distanceX, distanceY);
                    }
                    Log.d(TAG, "VOLUME:");
                    break;
                case BRIGHTNESS:
                    if (mGenseePlayerListener != null) {
                        mGenseePlayerListener.onBrightnessGesture(e1, e2, distanceX, distanceY);
                    }
                    Log.d(TAG, "BRIGHTNESS: ");
                    break;
                case FF_REW:
                    if (mGenseePlayerListener != null) {
                        mGenseePlayerListener.onVideoSpeedGesture(e1, e2, distanceX, distanceY);
                    }
                    videoSpeedEnd = true;
                    Log.d(TAG, "FF_REW: ");
                    break;
            }
            return true;
        }

        @Override
        public boolean onContextClick(MotionEvent e) {
            return super.onContextClick(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (mGenseePlayerListener != null) {
                mGenseePlayerListener.onDoubleTapGesture(e);
            }
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            super.onShowPress(e);
        }


        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (mGenseePlayerListener != null) {
                mGenseePlayerListener.onSingleTapGesture(e);
            }
            return super.onSingleTapConfirmed(e);
        }
    }
}
