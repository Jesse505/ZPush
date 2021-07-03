package com.android.jesse.zpush_core.platform.api;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.jesse.zpush_core.api.IPushClient;
import com.android.jesse.zpush_core.open.api.IPushInitListener;

/**
 * 选择推送平台
 */
public interface IPlatformChoose {

    IPushClient choosePushPlatform(@NonNull Context context, @NonNull IPushInitListener initListener);
}
