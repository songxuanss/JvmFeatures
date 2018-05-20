package com.jitaida.paul.comparators;

import com.jitaida.paul.ProgramEnginer;

import java.util.Comparator;

public class HeightComparator implements Comparator<ProgramEnginer> {
    public int compare(ProgramEnginer first, ProgramEnginer sec){
        return first.getHeight() - sec.getHeight();
    }
}
