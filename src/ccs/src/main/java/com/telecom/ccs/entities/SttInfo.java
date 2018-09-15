package com.telecom.ccs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SttInfo {

    private  List<SttSentence> sttSentences;   // 文本识别
    private  List<Silence> silences;   // 静音
    private  List<Interrupt> interrupted;  //叠音
    private  Date sttStartTime;
    private  Date sttEndTime;

}
