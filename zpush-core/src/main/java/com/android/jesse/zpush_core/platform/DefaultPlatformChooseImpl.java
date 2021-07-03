package com.android.jesse.zpush_core.platform;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.android.jesse.zpush_core.api.IPushClient;
import com.android.jesse.zpush_core.open.api.IPushInitListener;
import com.android.jesse.zpush_core.platform.api.IPlatformChoose;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DefaultPlatformChooseImpl implements IPlatformChoose {

    /**
     * the meta-data header
     */
    private static final String META_DATA_PUSH_HEADER = "ZPush_";
    /**
     * the meta_data split symbol
     */
    private static final String METE_DATA_SPLIT_SYMBOL = "_";


    @Override
    public IPushClient choosePushPlatform(@NonNull Context context, @NonNull final IPushInitListener initListener) {
        LinkedHashMap<String, String> supportPushPlatformMap = findAllSupportPushPlatform(context);
        if (supportPushPlatformMap.isEmpty()) {
            throw new IllegalArgumentException("have none push platform,check AndroidManifest.xml is have meta-data name is start with " + META_DATA_PUSH_HEADER);
        }

        IPushClient client;
        return (client = _choosePushPlatform(supportPushPlatformMap, new IPushInitIntercepter() {
            @Override
            public boolean intercept(int platformCode, String platformName) {
                return !initListener.onInitPush(platformCode, platformName);
            }
        })) == null ? _choosePushPlatform(supportPushPlatformMap, new IPushInitIntercepter() {
            @Override
            public boolean intercept(int platformCode, String platformName) {
                return !initListener.onInitDefaultPush(platformCode, platformName);
            }
        }) : client;
    }

    /**
     * 在AndroidManifest.xml中查找所有支持的平台
     */
    private LinkedHashMap<String, String> findAllSupportPushPlatform(Context context) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        try {
            //find all support push platform
            Bundle metaData = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData;
            if (metaData != null) {
                Set<String> allKeys = metaData.keySet();
                if (allKeys != null && !allKeys.isEmpty()) {
                    for (String key : allKeys) {
                        if (key.startsWith(META_DATA_PUSH_HEADER)) {
                            map.put(key, metaData.getString(key));
                        }
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }

    private IPushClient _choosePushPlatform(@NonNull LinkedHashMap<String, String> supportPushPlatformMap, @NonNull IPushInitIntercepter intercepter) {
        for (Map.Entry<String, String> next : supportPushPlatformMap.entrySet()) {
            String metaPlatformName = next.getKey();
            String metaPlatformClassName = next.getValue();
            StringBuilder stringBuilder = new StringBuilder(metaPlatformName).delete(0, META_DATA_PUSH_HEADER.length());
            int len = stringBuilder.length();
            int lastIndexSymbol = stringBuilder.lastIndexOf(METE_DATA_SPLIT_SYMBOL);

            int platformCode = Integer.parseInt(stringBuilder.substring(lastIndexSymbol + 1, len));
            String platformName = stringBuilder.substring(0, lastIndexSymbol);

            if (intercepter.intercept(platformCode, platformName)) {
                continue;
            }

            try {
                Class<?> currentClz = Class.forName(metaPlatformClassName);
                Class<?>[] interfaces = currentClz.getInterfaces();
                List<Class<?>> allInterfaces = Arrays.asList(interfaces);
                if (allInterfaces.contains(IPushClient.class)) {
                    IPushClient iPushClient = (IPushClient) currentClz.newInstance();
                    return iPushClient;
                } else {
                    throw new IllegalArgumentException(metaPlatformClassName + "is not implements " + IPushClient.class.getName());
                }
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("can not find class " + metaPlatformClassName);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
