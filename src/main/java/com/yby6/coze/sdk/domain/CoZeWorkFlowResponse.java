/*
 * 您可以更改此项目但请不要删除作者署名谢谢，否则根据中华人民共和国版权法进行处理.
 * You may change this item but please do not remove the author's signature,
 * otherwise it will be dealt with according to the Copyright Law of the People's Republic of China.
 *
 * yangbuyi Copyright (c) https://yby6.com 2024.
 */

package com.yby6.coze.sdk.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 对话请求结果信息
 *
 * @author XF4n
 * Create By 2024/09/24
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoZeWorkFlowResponse implements Serializable {
    
    /**
     * 返回码
     */
    private Integer code;
    
    /**
     * 消息
     */
    private String msg;
    
    /**
     * data 工作流执行结果
     */
    private String data;

    /**
     * debug_url 工作流试运行调试页面
     */
    private String debug_url;
}
