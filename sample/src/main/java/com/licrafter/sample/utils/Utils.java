package com.licrafter.sample.utils;

import android.app.Activity;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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

    public static void hideSystemUI(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public static void showSystemUI(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}
