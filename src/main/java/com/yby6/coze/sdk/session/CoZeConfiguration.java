/*
 * 您可以更改此项目但请不要删除作者署名谢谢，否则根据中华人民共和国版权法进行处理.
 * You may change this item but please do not remove the author's signature,
 * otherwise it will be dealt with according to the Copyright Law of the People's Republic of China.
 *
 * yangbuyi Copyright (c) https://yby6.com 2024.
 */

package com.yby6.coze.sdk.session;

import com.yby6.coze.sdk.ICoZeApi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import org.jetbrains.annotations.NotNull;


/**
 * 配置信息
 *
 * @author Yang Shuai
 * Create By 2024/05/29
 */
@Getter
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoZeConfiguration {
    
    /**
     * 扣子api接口
     */
    private ICoZeApi coZeApi;
    
    /**
     * 请求客户端
     */
    private OkHttpClient okHttpClient;
    
    /**
     * 鉴权密钥
     */
    @NotNull
    private String apiKey;
    
    /**
     * 请求地址
     */
    private String apiHost;

    /**
     * 请求日志打印类型 Level
     * NONE,
     * BASIC,
     * HEADERS,
     * BODY;
     */
    private HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
    
    /**
     * 创建请求工厂
     *
     * @return {@link EventSource.Factory}
     */
    public EventSource.Factory createRequestFactory() {
        return EventSources.createFactory(okHttpClient);
    }

}
