/*
 * 您可以更改此项目但请不要删除作者署名谢谢，否则根据中华人民共和国版权法进行处理.
 * You may change this item but please do not remove the author's signature,
 * otherwise it will be dealt with according to the Copyright Law of the People's Republic of China.
 *
 * yangbuyi Copyright (c) https://yby6.com 2024.
 */

package com.yby6.coze.sdk.session.defaults;


import com.yby6.coze.sdk.ICoZeApi;
import com.yby6.coze.sdk.domain.CoZeCompletionRequest;
import com.yby6.coze.sdk.domain.CoZeCompletionResponse;
import com.yby6.coze.sdk.session.CoZeConfiguration;
import com.yby6.coze.sdk.session.CoZeSession;
import okhttp3.sse.EventSource;

/**
 * 默认的 CoZe 会话实现CoZeSession
 *
 * @author Yang Shuai
 * Create By 2024/05/29
 */
public class DefaultCoZeSession implements CoZeSession {


    /**
     * 配置信息
     */
    private final CoZeConfiguration cozeConfiguration;

    /**
     * CoZe 接口
     */
    private final ICoZeApi cozeApi;
    /**
     * 工厂事件
     */
    private final EventSource.Factory factory;

    public DefaultCoZeSession(CoZeConfiguration cozeConfiguration) {
        this.cozeConfiguration = cozeConfiguration;
        this.cozeApi = cozeConfiguration.getCoZeApi();
        this.factory = cozeConfiguration.createRequestFactory();
    }

    /**
     * 问答模型扣子智能体AI
     *
     * @param cozeCompletionRequest 请求信息
     * @return 应答结果
     */
    @Override
    public CoZeCompletionResponse completions(CoZeCompletionRequest cozeCompletionRequest) {
        return this.cozeApi.completions(cozeCompletionRequest).blockingGet();
    }

}
