package com.android.jesse.zpush_getui;

import android.content.Context;
import android.util.Log;

import com.android.jesse.zpush_core.ZPush;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;

import java.nio.charset.Charset;

public class GeTuiIntentService extends GTIntentService {

    /**
     * 推送进程启动回调
     *
     * @param context
     * @param i
     */
    @Override
    public void onReceiveServicePid(Context context, int i) {
        super.onReceiveServicePid(context, i);
    }

    /**
     * 获取CID回调，个推初始化成功回调该函数并返回 cid
     *
     * @param context
     * @param s
     */
    @Override
    public void onReceiveClientId(Context context, String s) {
        ZPush.getMessageProcessor().onReceiveClientId(context, s);
    }

    /**
     * 透传消息回调，接收到透传消息后回调该函数。
     *
     * @param context
     * @param gtTransmitMessage
     */
    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage gtTransmitMessage) {
        super.onReceiveMessageData(context, gtTransmitMessage);
        ZPush.getMessageProcessor().onReceivePassThroughMessage(context,
                new String(gtTransmitMessage.getPayload(), Charset.forName("UTF-8")),
                GeTuiPushClient.GETUI_PUSH_PLATFORM_CODE);
    }

    /**
     * 监听在线状态，cid 在线状态变化时回调该函数
     *
     * @param context
     * @param b
     */
    @Override
    public void onReceiveOnlineState(Context context, boolean b) {
        super.onReceiveOnlineState(context, b);
    }

    /**
     * 命令回执，调用设置标签、绑定别名、解绑别名、自定义回执操作的结果返回
     *
     * @param context
     * @param gtCmdMessage
     */
    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage gtCmdMessage) {
        super.onReceiveCommandResult(context, gtCmdMessage);
    }

    /**
     * 通知到达时回调该接口（仅支持个推 SDK 通道下发的通知）
     *
     * @param context
     * @param gtNotificationMessage
     */
    @Override
    public void onNotificationMessageArrived(Context context, GTNotificationMessage gtNotificationMessage) {
        super.onNotificationMessageArrived(context, gtNotificationMessage);
        ZPush.getMessageProcessor().onNotificationMessageArrived(context,
                gtNotificationMessage.getContent(),
                GeTuiPushClient.GETUI_PUSH_PLATFORM_CODE);
    }

    /**
     * 通知点击回调接口（仅支持个推 SDK 通道下发的通知）
     *
     * @param context
     * @param gtNotificationMessage
     */
    @Override
    public void onNotificationMessageClicked(Context context, GTNotificationMessage gtNotificationMessage) {
        super.onNotificationMessageClicked(context, gtNotificationMessage);
        ZPush.getMessageProcessor().onNotificationMessageClicked(context,
                gtNotificationMessage.getContent(),
                GeTuiPushClient.GETUI_PUSH_PLATFORM_CODE);
    }
}
