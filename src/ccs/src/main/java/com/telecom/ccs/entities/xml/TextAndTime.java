package com.telecom.ccs.entities.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextAndTime {
    private String  text_n0;
    private String  time_n0;
    private String  text_n1;
    private String  time_n1;


    @Override
    public String toString() {
        return "TextAndTime{" +
                "text_n0='" + text_n0 + '\'' +
                ", time_n0='" + time_n0 + '\'' +
                ", text_n1='" + text_n1 + '\'' +
                ", time_n1='" + time_n1 + '\'' +
                '}';
    }
}
