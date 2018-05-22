package com.jingdu.toolbar.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/8/2 002.
 */

public class SystemUtils {
    public static int getDeviceWidth(Activity activity){
        WindowManager wm = activity.getWindowManager();
        return wm.getDefaultDisplay().getWidth();
    }

    public static int getDeviceHeight(Activity activity){
        WindowManager wm = activity.getWindowManager();
        return wm.getDefaultDisplay().getHeight();
    }

    public static int getDeviceWidthByDM(Activity activity){
        final DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getDeviceHeightByDM(Activity activity){
        final DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static float density(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int dp2px(Context context,float dipValue){
        float scale = density(context);
        return (int)(dipValue*scale + 0.5f);
    }

    public static int px2dp(Context context,float pxValue){
        float scale = density(context);
        return (int)(pxValue/scale+0.5f);
    }

    public static int px2sp(Context context,float pxValue){
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue/scale+0.5f);
    }
    public static int sp2px(Context context,float pxValue){
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue*scale+0.5f);
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        Activity activity;
        if (!(context instanceof Activity) && context instanceof ContextWrapper) {
            activity = (Activity) ((ContextWrapper) context).getBaseContext();
        } else {
            activity = (Activity) context;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    /**
     * 获取屏幕大小
     *
     * @param context
     * @return
     */
    public static int[] getScreenPixelSize(Context context) {
        DisplayMetrics metrics = getDisplayMetrics(context);
        return new int[]{metrics.widthPixels, metrics.heightPixels};
    }

    public static void hideSoftInputKeyBoard(Context context, View focusView) {
        if (focusView != null) {
            IBinder binder = focusView.getWindowToken();
            if (binder != null) {
                InputMethodManager imd = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imd.hideSoftInputFromWindow(binder, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        }
    }

    public static void showSoftInputKeyBoard(Context context, View focusView) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(focusView, InputMethodManager.SHOW_FORCED);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    public static int getAppInScreenheight(Context context) {
        return getScreenHeight(context) - getStatusBarHeight(context);
    }

    public static int getImageSourceIdByName(Context context,String name){
        name = name.toLowerCase();
        return context.getResources().getIdentifier(name,"mipmap",context.getPackageName());
    }

    public static String getStringByResource(Context context,int resourceId){
        if(context==null){
            return "";
        }
        return context.getResources().getString(resourceId);
    }

    public static Integer getIntegerByResource(Context context,int resourceId){
        if(context==null){
            return null;
        }
        return context.getResources().getInteger(resourceId);
    }
}
