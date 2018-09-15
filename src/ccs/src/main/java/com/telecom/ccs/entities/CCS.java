package com.telecom.ccs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CCS {

    private  String serialNumber;
    private String audioPath;
    private VoiceInfo voiceInfo;
    private SttInfo sttInfo;
    private List<BusinessTag> businessTags;   // 业务标签
    private List<RuleInfo> ruleInfos;  // 系统规则匹配信息（是不是只保留正确的？）
    private List<WorkScore> workScores;  // 质检得分
    private List<KeyWordsClustering>  keywords;


    @Override
    public String toString() {
        return "CCS{" +
                "serialNumber='" + serialNumber + '\'' +
                ", audioPath='" + audioPath + '\'' +
                ", voiceInfo=" + voiceInfo +
                ", sttInfo=" + sttInfo +
                ", businessTags=" + businessTags +
                ", ruleInfos=" + ruleInfos +
                ", workScores=" + workScores +
                ", keywords=" + keywords +
                '}';
    }
}
