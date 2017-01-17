package com.licrafter.sample.utils;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;

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
}
