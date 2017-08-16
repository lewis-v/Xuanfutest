package com.yw.xuanfutest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by LYW on 2017/6/28.
 */
public class MainPresenter {
    private Context context;

    public MainPresenter(Context context){
        this.context = context;
    }

    /**
     * 检测是否有悬浮框权限,没有则引导用户开启
     */
    public boolean askForPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(context)){
                Toast.makeText(context,"无悬浮窗权限,请开启权限",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION
                        , Uri.parse("package:"+context.getPackageName()));
                context.startActivity(intent);
                return false;
            }
        }
        return true;
    }
}
