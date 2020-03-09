package com.jitaida.paul.example;

import java.util.PriorityQueue;

public class ObjectInQueue implements Comparable<ObjectInQueue>
{
    public String a;
    public String b;

    public ObjectInQueue(String a, String b){
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(ObjectInQueue simpleQueue) {
        if (this.a.length() > simpleQueue.a.length()){
            return 1;
        }
        else if (this.a.length() < simpleQueue.a.length()){
            return -1;
        }

        return 0;
    }

    public static void main(String[] args){
        ObjectInQueue obj1 = new ObjectInQueue("adfasdfasdfsafdasdfsaf","sf");
        ObjectInQueue obj2 = new ObjectInQueue("aaaaaaaaaaaaaaaaaaaaa", "sdfds");
        ObjectInQueue obj3 = new ObjectInQueue("aaadsf","s");
        ObjectInQueue obj4 = new ObjectInQueue("11111","");

        PriorityQueue<ObjectInQueue> queue = new PriorityQueue<>(3);
        queue.add(obj1);
        queue.add(obj2);
        queue.add(obj3);
        queue.add(obj4);

        System.out.println(queue.poll().a);
        System.out.println(queue.poll().a);
        System.out.println(queue.poll().a);
        System.out.println(queue.poll().a);

    }
}
