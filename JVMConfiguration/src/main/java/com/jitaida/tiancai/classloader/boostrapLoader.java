package com.jitaida.tiancai.classloader;


public class boostrapLoader {
    public static void main(String[] args){
        // 代码显示bootstrap类加载器加载的类目录
        System.out.println(System.getProperty("sun.boot.class.path"));

        // extension 类加载器加载的目录 C:\Program Files\Java\jdk1.8.0_191\jre\lib\ext;C:\Windows\Sun\Java\lib\ext
        // 可以看见其主要是在lib\ext下面
        // 通过改变 java.ext.dirs环境变量改变ext类加载器加载的目录
        System.out.println(System.getProperty("java.ext.dirs"));

        // Application 类加载器加载的目录，其实就是 class path的所有路径
        System.out.println(System.getProperty("java.class.path"));
    }
}
