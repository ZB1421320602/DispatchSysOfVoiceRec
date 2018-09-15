package com.telecom.ccs.entities.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 非 xml 实体类， 自定义类型，用于解析角色对话详细信息
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sentence {
    private String  role;   // 角色
    private String text;
    private String  start;
    private String  end;
    private String  energy;
    private String  speed;
    private String emotion_type;
    private String emotion_score;

    private List<String> keywordsList;   //每个句子的分词列表
    private List<String> keywordsListTime;   // 每个句子的分词对应的时间点列表

    @Override
    public String toString() {
        return "Sentence{" +
                "role='" + role + '\'' +
                ", text='" + text + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", energy='" + energy + '\'' +
                ", speed='" + speed + '\'' +
                ", emotion_type='" + emotion_type + '\'' +
                ", emotion_score='" + emotion_score + '\'' +
                ", keywordsList=" + keywordsList +
                ", keywordsListTime=" + keywordsListTime +
                '}';
    }
}
