/*
 * 您可以更改此项目但请不要删除作者署名谢谢，否则根据中华人民共和国版权法进行处理.
 * You may change this item but please do not remove the author's signature,
 * otherwise it will be dealt with according to the Copyright Law of the People's Republic of China.
 *
 * yangbuyi Copyright (c) https://yby6.com 2024.
 */

package com.yby6.coze.sdk.session;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.yby6.coze.sdk.domain.CoZeCompletionRequest;
import com.yby6.coze.sdk.domain.CoZeCompletionResponse;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.util.concurrent.CompletableFuture;

/**
 * 扣子会话
 * CoZe 会话接口
 *
 * @author Yang Shuai
 * Create By 2024/05/29
 */
public interface CoZeSession {
    
    
    /**
     * 简单问答
     *
     * @param cozeCompletionRequest 扣子完成请求
     * @return {@link CoZeCompletionResponse}
     */
    CoZeCompletionResponse completions(CoZeCompletionRequest cozeCompletionRequest);


}
