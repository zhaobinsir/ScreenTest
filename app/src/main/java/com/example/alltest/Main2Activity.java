package com.example.alltest;

import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    PowerManager.WakeLock newWakeLock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         //用于锁屏显示
           getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|               //这个在锁屏状态下， 限制程序锁屏前在前台
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        以当前手机背景作为activity显示区域
  /*     Drawable wallPaper = WallpaperManager.getInstance( this).getDrawable();
        getWindow().setBackgroundDrawable(wallPaper);*/
        //解锁下显示Activity
       /* getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON			//这个是点亮屏幕
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD		//这个是透过锁屏界面，相当与解锁，但实质没有
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);		//这个是保持屏幕常亮。*/

        /*PowerManager  manager = (PowerManager) getSystemService(Context.POWER_SERVICE);//华为测试
        newWakeLock = manager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP |
                PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
        newWakeLock.acquire();//点亮屏幕(常亮)*/
        setContentView(R.layout.activity_main2);

//        锁屏下无法展示
       /* View inflater=LayoutInflater.from(this).inflate(R.layout.activity_main2,null);
        ViewGroup view=inflater.findViewById(R.id.con);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addWindows(inflater);*/

       /* ScheduledThreadPoolExecutor testThread  =new ScheduledThreadPoolExecutor(1);
        testThread.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.d("Main2Activity", light()+"");
            }
        },1, 1,TimeUnit.SECONDS);*/
    }

  /*  @Override
    protected void onResume() {
        super.onResume();
        this.startLockTask();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_HOME)
            return true;
        return super.onKeyDown(keyCode, event);
    }*/
   //添加窗口

    private boolean light(){
        KeyguardManager km =
                (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        return km.inKeyguardRestrictedInputMode();
    }


    @Override
    public void onBackPressed() {
       moveTaskToBack(true);
    }

    private void addWindows(View view){
//      WindowManager wm = (WindowManager)getApplicationContext().getSystemService(WINDOW_SERVICE);
      WindowManager wm = getWindow().getWindowManager();
      WindowManager.LayoutParams params = getWindow().getAttributes();

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
          params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
      }else {
          params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT |
                  WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
      }

      params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

      params.width = WindowManager.LayoutParams.FILL_PARENT;
      params.height = WindowManager.LayoutParams.FILL_PARENT;
      params.format = PixelFormat.TRANSPARENT;

      params.gravity= Gravity.LEFT|Gravity.TOP;
      params.x = 0;
      params.y = 0;
      wm.addView(view, params);
  }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (newWakeLock != null) {
            newWakeLock.release();//熄灭屏幕
        }
    }
}
