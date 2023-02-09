package com.zoubin.openai.service;

import com.alibaba.fastjson.JSON;
import com.zoubin.openai.config.OpenAIProperties;
import com.zoubin.openai.model.aggregates.AIAnswer;
import com.zoubin.openai.model.vo.Choices;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Slf4j
@Service
@EnableConfigurationProperties(OpenAIProperties.class)
public class OpenAI implements IOpenAI {

    @Autowired
    private OpenAIProperties openAIProperties;

    @Override
    public String doChatGPT(String question) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer " + openAIProperties.getKey());

        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"" + question + "\", \"temperature\": 0, \"max_tokens\": 1024}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        log.info("等待回答中......");
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            AIAnswer aiAnswer = JSON.parseObject(jsonStr, AIAnswer.class);
            StringBuilder answers = new StringBuilder();
            List<Choices> choices = aiAnswer.getChoices();
            for (Choices choice : choices) {
                answers.append(choice.getText());
            }
            log.info("收到回复：{}", answers.toString());
            return answers.toString();
        } else {
            return "发生了一些错误，请重试";
            //throw new RuntimeException("api.openai.com Err Code is " + response.getStatusLine().getStatusCode());
        }
    }

}
