package com.android.jesse.zpush_core.open.api;

import android.content.Context;

public interface IMessageProcessor {

    /**
     * 透传消息回调
     *
     * @param context
     * @param messageData
     * @param platformCode
     */
    void onReceivePassThroughMessage(Context context, String messageData, int platformCode);

    /**
     * 通知到达回调
     *
     * @param context
     * @param messageData
     * @param platformCode
     */
    void onNotificationMessageArrived(Context context, String messageData, int platformCode);

    /**
     * 通知点击回调
     *
     * @param context
     * @param messageData
     * @param platformCode
     */
    void onNotificationMessageClicked(Context context, String messageData, int platformCode);
}
