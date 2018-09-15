package com.telecom.ccs.entities.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Function_high_energy {
    private Items_search items;

    @Override
    public String toString() {
        return "Function_high_energy{" +
                "items=" + items +
                '}';
    }
}
