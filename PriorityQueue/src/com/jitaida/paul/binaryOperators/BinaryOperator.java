package com.jitaida.paul.binaryOperators;

public class BinaryOperator {

    public static void main(String args[]) {
        int i = Integer.MIN_VALUE;
        System.out.println(Integer.toBinaryString(i));

        i = i >> 2;
        System.out.println(Integer.toBinaryString(i));

        i = i >>> 2;
        System.out.println(Integer.toBinaryString(i));

        System.out.println("=================POSITIVE INT==================");

        i = 1;
        System.out.println(Integer.toBinaryString(i));

        i = i >> 2;
        System.out.println(Integer.toBinaryString(i));

        i = i >>> 2;
        System.out.println(Integer.toBinaryString(i));
    }
}
