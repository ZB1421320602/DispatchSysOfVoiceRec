package com.telecom.ccs.entities.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject_search {
    private String  value;
    private List<Channel_search> channel_searchList;

    @Override
    public String toString() {
        return "Subject_search{" +
                "value='" + value + '\'' +
                ", channel_searchList=" + channel_searchList +
                '}';
    }
}
