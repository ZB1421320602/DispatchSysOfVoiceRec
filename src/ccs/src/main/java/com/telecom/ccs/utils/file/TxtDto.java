package com.telecom.ccs.utils.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TxtDto {
    private String path;
    private String[] recordedInfo;

    @Override
    public String toString() {
        return "TxtDto{" +
                "path='" + path + '\'' +
                ", recordedInfo=" + Arrays.toString(recordedInfo) +
                '}';
    }
}
