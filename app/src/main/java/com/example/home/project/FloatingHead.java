package com.example.home.project;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import jp.co.recruit_lifestyle.android.floatingview.FloatingViewListener;

/**
 * Created by Home on 4/29/16.
 */
public class FloatingHead extends Service implements FloatingViewListener {

    public FrameLayout mFL;
    public WindowManager mWM;
    public FrameLayout.LayoutParams mFLParams;
    public WindowManager.LayoutParams mWMParams;
    public WindowManager.LayoutParams mUpdatedWMParams;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mWM = (WindowManager) getSystemService(WINDOW_SERVICE);
        mFL = new FrameLayout(this);

        mFLParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        mFL.setBackgroundResource(R.drawable.dinosaur);
        mFL.setLayoutParams(mFLParams);

      mWMParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
        mWMParams.x=0;
        mWMParams.y=0;
        mWMParams.gravity = Gravity.CENTER|Gravity.CENTER;

        mWM.addView(mFL, mWMParams);

        mFL.setOnTouchListener(new View.OnTouchListener() {

            WindowManager.LayoutParams mUpdatedWMParams = mWMParams;
            int x, y;
            float touchedX, touchedY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = mUpdatedWMParams.x;
                        y = mUpdatedWMParams.y;

                        touchedX = event.getRawX();
                        touchedY = event.getRawY();

                        break;

                    case MotionEvent.ACTION_MOVE:
                        mUpdatedWMParams.x = (int) (x + (event.getRawX() - touchedX));
                        mUpdatedWMParams.y = (int) (y + (event.getRawY() - touchedY));

                        mWM.updateViewLayout(mFL, mUpdatedWMParams);

                    default:
                        break;

                }
                return false;
            }
        });

        mFL.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getBaseContext(), "long clicked", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        return super.onStartCommand(intent, flags, startId);
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        mWM.removeView(mFL);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onFinishFloatingView() {
        stopSelf();
    }
}
