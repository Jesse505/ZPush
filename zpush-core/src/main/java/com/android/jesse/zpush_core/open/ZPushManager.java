package com.android.jesse.zpush_core.open;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.jesse.zpush_core.api.IPushClient;
import com.android.jesse.zpush_core.open.api.IPushInitListener;
import com.android.jesse.zpush_core.platform.DefaultPlatformChooseImpl;
import com.android.jesse.zpush_core.platform.api.IPlatformChoose;

public class ZPushManager implements IPushClient, IPlatformChoose {

    /**
     * 推送客户端
     */
    private IPushClient mIPushClient;
    private Context mContext;
    private IPlatformChoose mIPlatformChoose;

    private static volatile ZPushManager instance;

    private ZPushManager() {
        mIPlatformChoose = new DefaultPlatformChooseImpl();
    }

    public static ZPushManager getInstance() {
        if (instance == null) {
            synchronized (ZPushManager.class) {
                if (instance == null) {
                    instance = new ZPushManager();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化[自动注册]
     *
     * @param context
     * @param initListener 初始化回调
     */
    public void init(@NonNull Context context, @NonNull IPushInitListener initListener) {
        mContext = context;
        //choose custom push platform
        mIPushClient = choosePushPlatform(mContext, initListener);
        if (mIPushClient != null) {
            mIPushClient.init(mContext);
        } else {
            throw new IllegalStateException("onInitPush must at least one of them returns to true");
        }
    }

    @Override
    public void init(Context context) {

    }

    @Override
    public void turnOnPush() {
        checkInitialize();
        mIPushClient.turnOnPush();
    }

    @Override
    public void turnOffPush() {
        checkInitialize();
        mIPushClient.turnOffPush();
    }

    @Override
    public void bindAlias(String alias, String sn) {
        checkInitialize();
        mIPushClient.bindAlias(alias, sn);
    }

    @Override
    public void unBindAlias(String alias, boolean isSelf, String sn) {
        checkInitialize();
        mIPushClient.unBindAlias(alias, isSelf, sn);
    }


    @Override
    public void getAlias() {
        checkInitialize();
        mIPushClient.getAlias();
    }

    @Override
    public void addTags(String... tag) {
        checkInitialize();
        mIPushClient.addTags(tag);
    }

    @Override
    public void deleteTags(String... tag) {
        checkInitialize();
        mIPushClient.deleteTags(tag);
    }

    @Override
    public void getTags() {
        checkInitialize();
        mIPushClient.getTags();
    }

    @Override
    public String getPushToken() {
        checkInitialize();
        return mIPushClient.getPushToken();
    }

    @Override
    public int getPlatformCode() {
        checkInitialize();
        return mIPushClient.getPlatformCode();
    }

    @Override
    public String getPlatformName() {
        checkInitialize();
        return mIPushClient.getPlatformName();
    }


    @Override
    public IPushClient choosePushPlatform(@NonNull Context context, @NonNull IPushInitListener initListener) {
        return mIPlatformChoose.choosePushPlatform(context, initListener);
    }

    private void checkInitialize() {
        if (mIPushClient == null) {
            throw new ExceptionInInitializerError("请先调用 ZPush.init() 进行初始化！");
        }
    }
}
