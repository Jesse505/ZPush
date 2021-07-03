package com.android.jesse.zpush_getui;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.jesse.zpush_core.api.IPushClient;
import com.igexin.sdk.IUserLoggerInterface;
import com.igexin.sdk.PushManager;

/**
 * 个推 推送客户端
 */
public class GeTuiPushClient implements IPushClient {

    public static final String GETUI_PUSH_PLATFORM_NAME = "GeTuiPush";
    public static final int GETUI_PUSH_PLATFORM_CODE = 1000;

    private Context mContext;

    @Override
    public void init(Context context) {
        mContext = context.getApplicationContext();
        PushManager.getInstance().initialize(context);

        //TODO: debug 专用，release需要删掉
        PushManager.getInstance().setDebugLogger(context, new IUserLoggerInterface() {
            @Override
            public void log(String s) {
                Log.i("GeTuiPushClient", "个推日志：" + s);
            }
        });
    }

    @Override
    public void turnOnPush() {
        PushManager.getInstance().turnOnPush(mContext);
    }

    @Override
    public void turnOffPush() {
        PushManager.getInstance().turnOffPush(mContext);
    }

    @Override
    public void bindAlias(String alias, String sn) {
        if (TextUtils.isEmpty(sn)) {
            PushManager.getInstance().bindAlias(mContext, alias);
        } else {
            PushManager.getInstance().bindAlias(mContext, alias, sn);
        }
    }

    @Override
    public void unBindAlias(String alias, boolean isSelf, String sn) {
        if (TextUtils.isEmpty(sn)) {
            PushManager.getInstance().unBindAlias(mContext, alias, isSelf);
        } else {
            PushManager.getInstance().unBindAlias(mContext, alias, isSelf, sn);
        }
    }

    @Override
    public void getAlias() {

    }

    @Override
    public void addTags(String... tag) {

    }

    @Override
    public void deleteTags(String... tag) {

    }

    @Override
    public void getTags() {

    }

    @Override
    public String getPushToken() {
        return null;
    }

    @Override
    public int getPlatformCode() {
        return GETUI_PUSH_PLATFORM_CODE;
    }

    @Override
    public String getPlatformName() {
        return GETUI_PUSH_PLATFORM_NAME;
    }
}
