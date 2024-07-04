/*
 * 您可以更改此项目但请不要删除作者署名谢谢，否则根据中华人民共和国版权法进行处理.
 * You may change this item but please do not remove the author's signature,
 * otherwise it will be dealt with according to the Copyright Law of the People's Republic of China.
 *
 * yangbuyi Copyright (c) https://yby6.com 2024.
 */

package com.yby6.coze.sdk.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

/**
 * 腾讯元器构建请求
 *
 * @author Yang Shuai
 * Create By 2024/05/29
 */
@Data
@Builder
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class CoZeCompletionRequest implements Serializable {
    
    /**
     * 智能体ID
     */
    @JsonProperty("bot_id")
    private String botId;
    
    /**
     * 用户id
     */
    @JsonProperty("user")
    private String user;
    
    /**
     * 用户id
     */
    @JsonProperty("conversation_id")
    private String conversationId;
    
    /**
     * 问 - 内容
     */
    private String query;
    
    /**
     * 历史消息 上下文用
     */
    @JsonProperty("chat_history")
    private List<Message> chatHistory;
    
    /**
     * 流动
     */
    @JsonProperty("stream")
    private Boolean stream;
    
    
}
