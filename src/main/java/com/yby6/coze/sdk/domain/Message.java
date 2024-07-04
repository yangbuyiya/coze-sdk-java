package com.yby6.coze.sdk.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yby6.coze.sdk.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 消息内容
 *
 * @author yangs
 * Create By 2024/05/29
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {
    
    /**
     * 角色 assistant
     */
    private String role;
    
    /**
     * 消息类型  verbose、answer、follow_up
     */
    private String type;
    
    /**
     * 会话内容
     */
    private String content;
    
    /**
     * 内容类型
     */
    @JsonProperty("content_type")
    private String contentType;
    
    private Message(Builder builder) {
        this.role = builder.role;
        this.content = builder.content;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    /**
     * 建造者模式
     */
    public static final class Builder {
        
        private String role;
        private String content;
        
        public Builder() {
        }
        
        public Builder role(Constants.Role role) {
            this.role = role.getCode();
            return this;
        }
        
        public Builder content(String content) {
            this.content = content;
            return this;
        }
        
        public Message build() {
            return new Message(this);
        }
    }
    
}
