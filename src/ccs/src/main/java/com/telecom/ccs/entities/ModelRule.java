package com.telecom.ccs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelRule {

    private int modelId  ;
    private String  modelName;
    private int ruleId  ;
    private String  preQuality;
    private String  ruleName;
    private double startTime  ;
    private double endTime  ;
    private int ruleScore  ;
    private int matchStatus  ;
    private int proStatus  ;
    private Date inputTime  ;
    private String  appealNote;
    private Date appealTime  ;
    private long appealUserId  ;
    private String  proNote;
    private Date proTime  ;
    private long proUserId  ;




}
