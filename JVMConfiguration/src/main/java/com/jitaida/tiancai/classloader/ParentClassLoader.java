package com.jitaida.tiancai.classloader;

public class ParentClassLoader {
    public static void main(String[] args){
        System.out.println("parent: " + ParentClassLoader.class.getClassLoader().getParent());
        System.out.println("ext parent: " + ParentClassLoader.class.getClassLoader().getParent().getParent());
    }
}
