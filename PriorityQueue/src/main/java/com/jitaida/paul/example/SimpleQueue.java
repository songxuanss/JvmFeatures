package com.jitaida.paul.example;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class SimpleQueue {

    public static void main(String[] args){
        List<Integer> a = new ArrayList<Integer>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(20);

        a.add(1);
        a.add(12);
        a.add(24);
        a.add(23);
        a.add(190002);
        queue.addAll(a);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

}
