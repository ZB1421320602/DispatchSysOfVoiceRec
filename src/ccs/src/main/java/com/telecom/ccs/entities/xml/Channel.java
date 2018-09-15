package com.telecom.ccs.entities.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Channel {
    private String no;
    private String channeluri;
    private Items items;

    @Override
    public String toString() {
        return "Channel{" +
                "no='" + no + '\'' +
                ", channeluri='" + channeluri + '\'' +
                ", items=" + items +
                '}';
    }
}
