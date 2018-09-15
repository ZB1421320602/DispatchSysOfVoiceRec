package com.telecom.ccs.entities.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Channel_search {
    private String no;
    private Functicn_1_best functicn_1_best;
    private Function_long_silence function_long_silence;
    private Function_interrupted  function_interrupted;
    private Function_high_energy  function_high_energy;

    @Override
    public String toString() {
        return "Channel_search{" +
                "no='" + no + '\'' +
                ", functicn_1_best=" + functicn_1_best +
                ", function_long_silence=" + function_long_silence +
                ", function_interrupted=" + function_interrupted +
                ", function_high_energy=" + function_high_energy +
                '}';
    }
}
