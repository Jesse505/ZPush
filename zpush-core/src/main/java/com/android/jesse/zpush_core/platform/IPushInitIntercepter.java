/*
 * Copyright (C) 2021 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.android.jesse.zpush_core.platform;

/**
 * 推送初始化拦截器
 *
 */
public interface IPushInitIntercepter {

    /**
     * 推送初始化拦截
     *
     * @param platformCode 平台码
     * @param platformName 平台名
     */
    boolean intercept(int platformCode, String platformName);

}
