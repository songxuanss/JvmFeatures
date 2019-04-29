package com.jitaida.paul;

import com.jitaida.paul.comparators.SkillComparator;

import java.util.PriorityQueue;

public class WorstQueue {

    public static void main(String [] args)
    {
        PriorityQueue<ProgramEnginer> worstKillQueue = new PriorityQueue<>(new SkillComparator());


        worstKillQueue.add(new ProgramEnginer("paul",180,160, 8.9));
        worstKillQueue.add(new ProgramEnginer("john",181,150,8.8));
        worstKillQueue.add(new ProgramEnginer("shortGuy",160,140,9.0));

        ProgramEnginer a = worstKillQueue.poll();

        System.out.println(a.getName());
    }

}
