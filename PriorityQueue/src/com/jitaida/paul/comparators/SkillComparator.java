package com.jitaida.paul.comparators;

import com.jitaida.paul.ProgramEnginer;

import java.util.Comparator;


public class SkillComparator implements Comparator<ProgramEnginer> {
    public int compare(ProgramEnginer first, ProgramEnginer sec) {
        double ratingDiff = first.getSkillRating() - sec.getSkillRating();
        if(ratingDiff < 0) return -1;
        if(ratingDiff > 0) return 1;
        return 0;
    }
}
