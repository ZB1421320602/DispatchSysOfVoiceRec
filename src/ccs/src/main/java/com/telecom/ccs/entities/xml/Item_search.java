package com.telecom.ccs.entities.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item_search {
    private String  start;
    private String  end;
    private String  duration;

    @Override
    public String toString() {
        return "Item_search{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
