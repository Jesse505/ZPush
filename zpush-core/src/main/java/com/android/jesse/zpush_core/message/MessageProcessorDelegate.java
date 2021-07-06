package com.android.jesse.zpush_core.message;

import android.content.Context;

import com.android.jesse.zpush_core.open.api.IMessageProcessor;

/**
 * 处理通知消息的代理类
 */
public class MessageProcessorDelegate implements IMessageProcessor {

    private IMessageProcessor messageProcessor;

    public MessageProcessorDelegate(IMessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }


    @Override
    public void onReceiveClientId(Context context, String cid) {
        if (messageProcessor != null) {
            messageProcessor.onReceiveClientId(context, cid);
        }
    }

    @Override
    public void onReceivePassThroughMessage(Context context, String messageData, int platformCode) {
        if (messageProcessor != null) {
            messageProcessor.onReceivePassThroughMessage(context, messageData, platformCode);
        }
    }

    @Override
    public void onNotificationMessageArrived(Context context, String messageData, int platformCode) {
        if (messageProcessor != null) {
            messageProcessor.onNotificationMessageArrived(context, messageData, platformCode);
        }
    }

    @Override
    public void onNotificationMessageClicked(Context context, String messageData, int platformCode) {
        if (messageProcessor != null) {
            messageProcessor.onNotificationMessageClicked(context, messageData, platformCode);
        }
    }
}
