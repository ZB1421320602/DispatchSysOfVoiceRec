package com.telecom.ccs.utils.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoiceDto {
    private String path;
    private long size;


    @Override
    public String toString() {
        return "VoiceDto{" +
                "path='" + path + '\'' +
                ", size=" + size +
                '}';
    }
}
