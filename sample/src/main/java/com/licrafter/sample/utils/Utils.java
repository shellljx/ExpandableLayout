package com.licrafter.sample.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * author: shell
 * date 2017/1/16 下午5:04
 **/
public class Utils {

    public static int getDisplayWidth(AppCompatActivity context) {
        Point point = new Point();
        context.getWindowManager().getDefaultDisplay().getSize(point);
        return point.x;
    }

    public static int getStatusBarHeight(Context context) {
        try {
            int result = 0;
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void setTranslucentMode(Activity activity) {
        Window window = activity.getWindow();
        View decorView = window.getDecorView();
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
