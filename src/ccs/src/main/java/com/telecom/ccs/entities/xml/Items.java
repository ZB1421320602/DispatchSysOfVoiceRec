package com.telecom.ccs.entities.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Items {
    private String count;
    private String duration;
    private List<Item> itemList;

    @Override
    public String toString() {
        return "Items{" +
                "count='" + count + '\'' +
                ", duration='" + duration + '\'' +
                ", itemList=" + itemList +
                '}';
    }
}
