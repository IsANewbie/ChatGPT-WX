package com.zoubin.openai.model.aggregates;


import com.zoubin.openai.model.vo.Choices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AIAnswer {

    private String id;

    private String object;

    private int created;

    private String model;

    private List<Choices> choices;


}
