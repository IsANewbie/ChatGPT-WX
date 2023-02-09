package com.zoubin.openai.service;

import java.io.IOException;


public interface IOpenAI {

    String doChatGPT(String question) throws IOException;

}
