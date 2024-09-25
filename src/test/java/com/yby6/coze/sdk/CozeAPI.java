/*
 * 您可以更改此项目但请不要删除作者署名谢谢，否则根据中华人民共和国版权法进行处理.
 * You may change this item but please do not remove the author's signature,
 * otherwise it will be dealt with according to the Copyright Law of the People's Republic of China.
 *
 * yangbuyi Copyright (c) https://yby6.com 2024.
 */

package com.yby6.coze.sdk;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.yby6.coze.sdk.domain.*;
import com.yby6.coze.sdk.session.CoZeConfiguration;
import com.yby6.coze.sdk.session.CoZeSession;
import com.yby6.coze.sdk.session.defaults.DefaultCoZeSessionFactory;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * coze api
 *
 * @author yangs
 * Create By 2024/07/04
 */
@Slf4j
public class CozeAPI {
    
    private CoZeSession coZeSession;
    
    /**
     * 测试会话工厂
     */
    @Before
    public void test_session_Factory() {
        // 1. 配置文件
        CoZeConfiguration yuanQiConfiguration = new CoZeConfiguration();
        yuanQiConfiguration.setApiHost("https://api.coze.cn/open_api/");
        yuanQiConfiguration.setApiKey("Bearer 你的APIKEY");
        yuanQiConfiguration.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        // 2. 会话工厂
        DefaultCoZeSessionFactory factory = new DefaultCoZeSessionFactory(yuanQiConfiguration);
        // 3. 开启会话
        this.coZeSession = factory.openSession();
        log.info("openAiSession:{}", coZeSession);
    }
    
    /**
     * 测试聊天完成
     */
    @Test
    public void test_chat_completions() {
        // 1. 创建参数
        CoZeCompletionRequest chatCompletion = CoZeCompletionRequest
                .builder()
                .stream(false)
                .conversationId(IdUtil.nanoId())
                .user("29032201862555")
                .botId("7387634479231434790")
                .query("你是谁呀?")
                .build();
        // 2. 发起请求
        CoZeCompletionResponse yuanQiCompletionResponse = coZeSession.completions(chatCompletion);
        // 3. 解析结果
        for (Message message : yuanQiCompletionResponse.getMessages()) {
            // answer 是机器人回答的
            if (message.getType().equals("answer")) {
                log.info("返回内容: {}", message.getContent());
            }
        }
    }
    
    
    /**
     * 流试返回参数:
     * {
     * "id": "82956d810a0ff9b413c8bf924c2190c3",
     * "created": 1717177832,
     * "choices": [
     * {
     * "delta": {
     * "role": "assistant",
     * "content": "我是",
     * "time_cost": 0
     * }
     * }
     * ],
     * "assistant_id": "mmbnqMnLdYz0",
     * "usage": {
     * "prompt_tokens": 0,
     * "completion_tokens": 0,
     * "total_tokens": 0
     * }
     * }
     */
    @Test
    public void test_chat_completions_stream() throws JsonProcessingException, InterruptedException {
        // 1. 创建参数
        CoZeCompletionRequest chatCompletion = CoZeCompletionRequest
                .builder()
                .stream(true)
                .conversationId(IdUtil.nanoId())
                .user("29032201862555")
                .botId("7387634479231434790")
                .query("你是谁呀?")
                .build();
        // 2. 发起请求
        EventSource eventSource = coZeSession.chatCompletions(chatCompletion, new EventSourceListener() {
            @Override
            public void onEvent(@NotNull EventSource eventSource, String id, String type, @NotNull String data) {
                try {
                    CoZeCompletionEventResponse response = JSONUtil.toBean(data, CoZeCompletionEventResponse.class);
                    
                    if (response.getEvent().equals("done")) {
                        return;
                    }
                    
                    Message message = response.getMessage();
                    if (message.getType().equals("answer")) {
                        log.info("返回内容: {}", message.getContent());
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            
            @Override
            public void onFailure(EventSource eventSource, Throwable t, Response response) {
                log.error("错误: {}, {}", response.code(), t.getMessage());
            }
        });
        // 等待
        new CountDownLatch(1).await();
    }


    /**
     * 测试工作流远程调用
     */
    @Test
    public void test_workFlow_run() throws JsonProcessingException {

        // 1. 配置文件
        CoZeConfiguration cozeConfiguration = new CoZeConfiguration();
        cozeConfiguration.setApiHost("https://api.coze.cn/");
        cozeConfiguration.setApiKey("Bearer pat_QuWfmGwBQA8BsMG7cXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        cozeConfiguration.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        // 2. 会话工厂
        DefaultCoZeSessionFactory factory = new DefaultCoZeSessionFactory(cozeConfiguration);

        // 3. 开启会话
        coZeSession = factory.openSession();

        // 4. 设置入参
        String prompt = "a cat";
        JSONObject jsonObj = new JSONObject();
        jsonObj.append("prompt", prompt);

        // 5. 构建请求
        CoZeWorkFlowRequest coZeWorkFlowRequest = new CoZeWorkFlowRequest();
        // 设置工作流ID
        coZeWorkFlowRequest.setWorkflowId("7417847815457701923");
        // 设置参数
        coZeWorkFlowRequest.setParameters(jsonObj);

        // 6. 发起请求
        CoZeWorkFlowResponse coZeWorkFlowResponse = coZeSession.workflowCompletions(coZeWorkFlowRequest);

        // 7. 解析结果
        log.info("Code:{}", coZeWorkFlowResponse.getCode());
        log.info("Msg:{}", coZeWorkFlowResponse.getMsg());
        log.info("Data:{}", coZeWorkFlowResponse.getData());
        log.info("Debug:{}", coZeWorkFlowResponse.getDebug_url());

    }
    
    
}
