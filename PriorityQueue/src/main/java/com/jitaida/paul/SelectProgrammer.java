package com.jitaida.paul;

import com.jitaida.paul.comparators.SkillComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SelectProgrammer {
    public static void main(String[] args){
        List<ProgramEnginer> aList = new ArrayList<>();

        aList.add(new ProgramEnginer("paul",180,160, 8.9));
        aList.add(new ProgramEnginer("john",181,150,8.8));
        aList.add(new ProgramEnginer("shortGuy",160,140,9.0));

        Collections.sort(aList, new SkillComparator());

        for (ProgramEnginer each: aList){
            System.out.println(each.getName());
        }
    }
}
