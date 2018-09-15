package com.telecom.ccs.entities.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Functicn_1_best {
    private String value;
    private String count;
    private String speed;
    private Text text;
    private Time  time;

    @Override
    public String toString() {
        return "Functicn_1_best{" +
                "value='" + value + '\'' +
                ", count='" + count + '\'' +
                ", speed='" + speed + '\'' +
                ", text=" + text +
                ", time=" + time +
                '}';
    }
}
