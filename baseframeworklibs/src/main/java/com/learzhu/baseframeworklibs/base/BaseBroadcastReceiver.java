package com.learzhu.baseframeworklibs.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.learzhu.baseframeworklibs.utils.LogUtil;
import com.learzhu.baseframeworklibs.utils.StringUtil;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;


/**
 * BaseBroadcastReceiver.java是BaseFramework的基础广播接收器类。
 *
 * @author Learzhu
 * @version 1.0.0 2019-03-26 17:17
 * @use 自定义BroadcastReceiver - extends BaseBroadcastReceiver；其它 - 直接使用里面的静态方法
 * @must 调用register和unregister方法
 * @update UserName 2019-03-26 17:17
 * @updateDes
 */

public abstract class BaseBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "BaseBroadcastReceiver";

    /**
     * 接收信息监听回调
     */
    public interface OnReceiveListener {
        void onReceive(Context context, Intent intent);
    }

    protected OnReceiveListener onReceiveListener = null;

    /**
     * 注册接收信息监听
     *
     * @param onReceiveListener
     * @must 在register后，unregister前调用
     */
    public void setOnReceiveListener(OnReceiveListener onReceiveListener) {
        this.onReceiveListener = onReceiveListener;
    }


    protected Context context = null;

    public BaseBroadcastReceiver(Context context) {
        this.context = context;
    }

    /**
     * 接收信息监听回调方法
     */
    public void onReceive(Context context, Intent intent) {
        LogUtil.i(TAG, "onReceive intent = " + intent);
        if (onReceiveListener != null) {
            onReceiveListener.onReceive(context, intent);
        }
    }


    /**
     * 注册广播接收器
     *
     * @use 一般在Activity或Fragment的onCreate中调用
     */
    public abstract BaseBroadcastReceiver register();

    /**
     * 取消注册广播接收器
     *
     * @use 一般在Activity或Fragment的onDestroy中调用
     */
    public abstract void unregister();


    /**
     * 注册广播接收器
     *
     * @param context
     * @param receiver
     * @param action
     * @return
     */
    public static BroadcastReceiver register(Context context, @Nullable BroadcastReceiver receiver, String action) {
        return register(context, receiver, new String[]{action});
    }

    /**
     * 注册广播接收器
     *
     * @param context
     * @param receiver
     * @param actions
     * @return
     */
    public static BroadcastReceiver register(Context context, @Nullable BroadcastReceiver receiver, String[] actions) {
        return register(context, receiver, actions == null ? null : Arrays.asList(actions));
    }

    /**
     * 注册广播接收器
     *
     * @param context
     * @param receiver
     * @param actionList
     * @return
     */
    public static BroadcastReceiver register(Context context, @Nullable BroadcastReceiver receiver, List<String> actionList) {
        IntentFilter filter = new IntentFilter();
        for (String action : actionList) {
            if (StringUtil.isNotEmpty(action, true)) {
                filter.addAction(StringUtil.getTrimedString(action));
            }
        }
        return register(context, receiver, filter);
    }

    /**
     * 注册广播接收器
     *
     * @param context
     * @param receiver
     * @param filter
     * @return
     */
    public static BroadcastReceiver register(Context context, @Nullable BroadcastReceiver receiver, IntentFilter filter) {
        LogUtil.i(TAG, "register >>>");
        if (context == null || filter == null) {
            LogUtil.e(TAG, "register  context == null || filter == null >> return;");
            return receiver;
        }

        context.registerReceiver(receiver, filter);

        return receiver;
    }


    /**
     * 取消注册广播接收器
     *
     * @param context
     * @param receiver
     * @return
     */
    public static void unregister(Context context, BroadcastReceiver receiver) {
        LogUtil.i(TAG, "unregister >>>");
        if (context == null || receiver == null) {
            LogUtil.e(TAG, "unregister  context == null || receiver == null >> return;");
            return;
        }

        try {
            context.unregisterReceiver(receiver);
        } catch (Exception e) {
            LogUtil.e(TAG, "unregister  try { context.unregisterReceiver(receiver);" +
                    " } catch (Exception e) { \n" + e.getMessage());
        }
    }

}
