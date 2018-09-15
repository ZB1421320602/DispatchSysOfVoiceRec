package com.telecom.ccs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleInfo {
    private  long ruleId;
    private String ruleName;
    private double startTime;
    private double endTime;
    private Date inputTime;
}
