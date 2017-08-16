package com.yw.xuanfutest;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MyService extends Service implements IMyServicePersenter{
    private static final String TAG = "MyService";

    private LinearLayout layout;
    private WindowManager.LayoutParams layoutParams;
    private WindowManager windowManager;
    private MyServicePresenter myServicePresenter;
    private RecyclerView recyclerView;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myServicePresenter = new MyServicePresenter(this,this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myServicePresenter.setSensorListener();
        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (layout != null){
            windowManager.removeView(layout);
        }
        if (myServicePresenter != null){
            myServicePresenter.stop();
        }
    }

    public void createView(MyHomeAdapter adapter){
        layoutParams = new WindowManager.LayoutParams();
        windowManager = (WindowManager)getApplication()
                .getSystemService(getApplication().WINDOW_SERVICE);
        layoutParams.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;
        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        LayoutInflater inflater = LayoutInflater.from(getApplication());
        layout = (LinearLayout) inflater.inflate(R.layout.test_layout,null);
        recyclerView = (RecyclerView)layout.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setEnabled(false);
        recyclerView.setClickable(false);
        recyclerView.setFocusable(false);
        windowManager.addView(layout,layoutParams);

        layout.measure(View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED)
                ,View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED));

    }

    @Override
    public void setRecyclerViewAdapter(MyHomeAdapter adapter) {
        createView(adapter);
    }
}
