package com.telecom.ccs.entities.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Items_search {
    private String count;
    private String duration;
    private List<Item_search> items;

    @Override
    public String toString() {
        return "Items_search{" +
                "count='" + count + '\'' +
                ", duration='" + duration + '\'' +
                ", items=" + items +
                '}';
    }
}
