package com.jitaida.tiancai.dynamicProxy;

public class PrintProcess implements BaseProcess {
    @Override
    public void before() {
        System.out.println("ssss");
    }

    @Override
    public void after() {
        System.out.println("aaaaa");
    }

    @Override
    public void doProcess() {
        System.out.println("do something here");
    }
}
