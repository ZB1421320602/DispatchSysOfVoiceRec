package com.telecom.ccs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkScore {

    private long  workId;
    private String  workName;
    private int  preQuality;
    private int  proStatus;
    private String  workScore;
    private String  scoreNote;
    private List<ModelRule> modelRules  ;



}
