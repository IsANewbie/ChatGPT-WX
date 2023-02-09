package com.zoubin.openai.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Choices {

    private String text;

    private int index;

    private String logprobs;

    private String finish_reason;

}
