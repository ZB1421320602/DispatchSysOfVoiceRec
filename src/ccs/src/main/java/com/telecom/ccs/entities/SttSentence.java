package com.telecom.ccs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SttSentence {

    private int channel; // 声道 0 座席  1 客户
    private double start;
    private double end;

    private String centent;  //句子内容
    private List<KeyWord> keywords;  //分词列表
    private  int emotion; // 情绪  高兴、生气、普通
    private double speed;  //语速  字/秒
    private  int energy;    //音量
    private Date inputTime;  //入库时间
}
