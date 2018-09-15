package com.telecom.ccs.entities.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject_speaker_separation {

    private String  value;

    private List<Channel> channelList ;

    @Override
    public String toString() {
        return "Subject_speaker_separation{" +
                "value='" + value + '\'' +
                ", channelList=" + channelList +
                '}';
    }
}
