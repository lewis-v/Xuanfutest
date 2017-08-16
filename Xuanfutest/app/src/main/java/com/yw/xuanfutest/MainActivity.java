package com.yw.xuanfutest;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MainPresenter presenter;
    private Button buttonOpen,buttonClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW},1);
        presenter =  new MainPresenter(this);
        buttonOpen = (Button)findViewById(R.id.bt_open);
        buttonClose = (Button)findViewById(R.id.bt_close);
        presenter.askForPermission();


        buttonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter.askForPermission()) {
                    Intent intent = new Intent(MainActivity.this, MyService.class);
                    startService(intent);
                }
            }
        });

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyService.class);
                stopService(intent);
            }
        });
    }
}
