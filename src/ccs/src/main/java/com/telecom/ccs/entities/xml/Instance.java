package com.telecom.ccs.entities.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instance {
    private String waveuri;
    private String fmt;
    private String sample_rate;   //xml 格式为 sample-rate
    private String bit_rate;      //xml 格式为 bit-rate
    private String channel;
    private String duration;
    private String file_comment;

    private Subject_speaker_separation subject_speaker_separation;
    private Subject_search subject_search;

    @Override
    public String toString() {
        return "Instance{" +
                "waveuri='" + waveuri + '\'' +
                ", fmt='" + fmt + '\'' +
                ", sample_rate='" + sample_rate + '\'' +
                ", bit_rate='" + bit_rate + '\'' +
                ", channel='" + channel + '\'' +
                ", duration='" + duration + '\'' +
                ", file_comment='" + file_comment + '\'' +
                ", subject_speaker_separation=" + subject_speaker_separation +
                ", subject_search=" + subject_search +
                '}';
    }
}
