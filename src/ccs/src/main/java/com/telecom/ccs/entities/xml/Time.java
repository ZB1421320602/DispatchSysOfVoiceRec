package com.telecom.ccs.entities.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Time {
    private String time;

    @Override
    public String toString() {
        return "Time{" +
                "time='" + time + '\'' +
                '}';
    }
}
