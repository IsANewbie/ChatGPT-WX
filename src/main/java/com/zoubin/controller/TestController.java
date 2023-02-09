package com.zoubin.controller;

import com.zoubin.openai.service.OpenAI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * jsapi 演示接口的 controller.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-04-25
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/openai")
public class TestController {

    @Autowired
    private OpenAI openAI;

    @GetMapping("/chat")
    public String getJsapiTicket(String q) throws WxErrorException, IOException {
        log.info("测试请求,收到问题：{}", q);
        return openAI.doChatGPT(q);
    }
}
