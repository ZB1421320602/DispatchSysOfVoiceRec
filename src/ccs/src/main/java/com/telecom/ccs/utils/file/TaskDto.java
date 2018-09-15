package com.telecom.ccs.utils.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private String voicePath;
    private long voieceSize;
    private String txtPath;
    private String[] recordedInfo;


    @Override
    public String toString() {
        return "TaskDto{" +
                "voicePath='" + voicePath + '\'' +
                ", voieceSize=" + voieceSize +
                ", txtPath='" + txtPath + '\'' +
                ", recordedInfo=" + Arrays.toString(recordedInfo) +
                '}';
    }
}
