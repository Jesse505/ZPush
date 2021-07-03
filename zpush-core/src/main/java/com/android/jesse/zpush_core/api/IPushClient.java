/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
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

package com.android.jesse.zpush_core.api;

import android.content.Context;

/**
 * 推送客户端【一定要有无参构造方法】
 */
public interface IPushClient {

    /**
     * 初始化【必须】
     *
     * @param context
     */
    void init(Context context);

    /**
     * 开启推送【必须】
     */
    void turnOnPush();

    /**
     * 关闭推送【必须】
     */
    void turnOffPush();

    /**
     * 绑定别名【别名是唯一的】
     *
     * @param alias 别名
     * @param sn    用户自定义的序列号，用来唯一标识该动作
     */
    void bindAlias(String alias, String sn);

    /**
     * 解绑别名
     *
     * @param alias  别名
     * @param isSelf 是否只对当前 cid 有效，如果是 true，只对当前 cid 做解绑；
     *               如果是 false，对所有绑定该别名的 cid 列表做解绑
     * @param sn     用户自定义的序列号，用来唯一标识该动作
     */
    void unBindAlias(String alias, boolean isSelf, String sn);

    /**
     * 获取别名
     */
    void getAlias();

    /**
     * 增加标签
     *
     * @param tag 标签
     */
    void addTags(String... tag);

    /**
     * 删除标签
     *
     * @param tag 标签
     */
    void deleteTags(String... tag);

    /**
     * 获取标签
     */
    void getTags();

    /**
     * @return 获取推送令牌
     */
    String getPushToken();

    /**
     * 注意千万不要重复【必须】
     *
     * @return 获取平台码
     */
    int getPlatformCode();

    /**
     * 注意千万不要重复【必须】
     *
     * @return 获取平台名
     */
    String getPlatformName();

}
