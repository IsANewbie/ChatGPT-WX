package com.zoubin.openai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * wechat mp properties
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@ConfigurationProperties(prefix = "openai")
public class OpenAIProperties {
    /**
     * 是否使用redis存储access token
     */
    private String key;

}
