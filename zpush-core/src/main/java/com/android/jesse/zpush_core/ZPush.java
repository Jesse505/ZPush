package com.android.jesse.zpush_core;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.jesse.zpush_core.open.ZPushManager;
import com.android.jesse.zpush_core.open.api.IMessageProcessor;
import com.android.jesse.zpush_core.open.api.IPushInitListener;

public final class ZPush {

    private ZPush() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化
     *
     * @param context
     * @param initListener
     */
    public static void init(@NonNull Context context, @NonNull IPushInitListener initListener) {
        ZPushManager.getInstance().init(context, initListener);
    }

    /**
     * 开启推送
     */
    public static void turnOnPush() {
        ZPushManager.getInstance().turnOnPush();
    }

    /**
     * 关闭推送
     */
    public static void turnOffPush() {
        ZPushManager.getInstance().turnOffPush();
    }

    /**
     * 绑定别名
     *
     * @param alias 别名
     */
    public static void bindAlias(String alias, String sn) {
        ZPushManager.getInstance().bindAlias(alias, sn);
    }

    /**
     * 解绑别名
     *
     * @param alias 别名
     */
    public static void unBindAlias(String alias, boolean isSelf, String sn) {
        ZPushManager.getInstance().unBindAlias(alias, isSelf, sn);
    }

    /**
     * 获取别名
     */
    public static void getAlias() {
        ZPushManager.getInstance().getAlias();
    }

    /**
     * 添加标签
     *
     * @param tag 标签
     */
    public static void addTags(String... tag) {
        ZPushManager.getInstance().addTags(tag);
    }

    /**
     * 删除标签
     *
     * @param tag 标签
     */
    public static void deleteTags(String... tag) {
        ZPushManager.getInstance().deleteTags(tag);
    }

    /**
     * 获取标签
     */
    public static void getTags() {
        ZPushManager.getInstance().getTags();
    }

    /**
     * 获取推送令牌
     */
    public static String getPushToken() {
        return ZPushManager.getInstance().getPushToken();
    }

    /**
     * @return 推送平台码
     */
    public static int getPlatformCode() {
        return ZPushManager.getInstance().getPlatformCode();
    }

    /**
     * @return 推送平台的名称
     */
    public static String getPlatformName() {
        return ZPushManager.getInstance().getPlatformName();
    }

    /**
     * 设置推送消息处理器
     *
     * @param processor
     */
    public static void setMessageProcessor(IMessageProcessor processor) {
        ZPushManager.getInstance().setIMessageProcessor(processor);
    }

    /**
     * 获取推送消息处理器
     *
     * @return
     */
    public static IMessageProcessor getMessageProcessor() {
        return ZPushManager.getInstance().getIMessageProcessor();
    }

}
