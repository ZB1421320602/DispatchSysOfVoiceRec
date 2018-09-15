package com.telecom.ccs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessTag {

    private long businessId;
    private String business;
    private  int proType;
    private int proValue;
    private int inputTime;
}
