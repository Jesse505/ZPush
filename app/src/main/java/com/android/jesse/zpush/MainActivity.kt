package com.android.jesse.zpush

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.jesse.zpush_core.ZPush
import com.android.jesse.zpush_core.open.api.IMessageProcessor
import com.android.jesse.zpush_core.open.api.IPushInitStrategy
import com.android.jesse.zpush_getui.GeTuiPushClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ZPush.init(this,
            object : IPushInitStrategy {
                override fun priorPushInit(platformCode: Int, platformName: String?): Boolean {
                    return GeTuiPushClient.GETUI_PUSH_PLATFORM_CODE == platformCode
                            && GeTuiPushClient.GETUI_PUSH_PLATFORM_NAME == platformName
                }

                override fun backupPushInit(platformCode: Int, platformName: String?): Boolean {
                    return GeTuiPushClient.GETUI_PUSH_PLATFORM_CODE == platformCode
                            && GeTuiPushClient.GETUI_PUSH_PLATFORM_NAME == platformName
                }
            },
            object : IMessageProcessor {
                override fun onReceiveClientId(context: Context?, cid: String?) {
                    Log.i("PUSH_LOG", "onReceiveClientId > $cid")
                }

                override fun onReceivePassThroughMessage(
                    context: Context?,
                    messageData: String?,
                    platformCode: Int
                ) {
                    Log.i("PUSH_LOG", "onReceivePassThroughMessage > $messageData")
                }

                override fun onNotificationMessageArrived(
                    context: Context?,
                    messageData: String?,
                    platformCode: Int
                ) {
                    Log.i("PUSH_LOG", "onNotificationMessageArrived > $messageData")
                }

                override fun onNotificationMessageClicked(
                    context: Context?,
                    messageData: String?,
                    platformCode: Int
                ) {
                    Log.i("PUSH_LOG", "onNotificationMessageClicked > $messageData")
                }

            })
    }
}
