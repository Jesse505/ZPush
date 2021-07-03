package com.android.jesse.zpush_getui;

import android.content.Context;
import android.util.Log;

import com.igexin.sdk.GTIntentService;

public class GeTuiIntentService extends GTIntentService {
    @Override
    public void onReceiveClientId(Context context, String s) {
        Log.e("PUSH_LOG", "clientId > " + s);
    }
}
