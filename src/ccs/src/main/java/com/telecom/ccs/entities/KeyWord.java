package com.telecom.ccs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyWord {

    private long keywordStart; // 开始位置
    private long keywordEnd;    // 结束位置
    private String keyword;     //词
}
