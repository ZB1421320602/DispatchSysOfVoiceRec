package com.telecom.ccs.entities.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String  start;
    private String  end;
    private String  energy;
    private String  speed;

    private String emotion_type;
    private String emotion_score;

    @Override
    public String toString() {
        return "Item{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", energy='" + energy + '\'' +
                ", speed='" + speed + '\'' +
                ", emotion_type='" + emotion_type + '\'' +
                ", emotion_score='" + emotion_score + '\'' +
                '}';
    }
}
