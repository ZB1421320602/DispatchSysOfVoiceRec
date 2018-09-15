package com.telecom.ccs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyWordsClustering {
    private String  keyword;
    private int exists  ;

    private int count;
    private Date inputTime;
}
