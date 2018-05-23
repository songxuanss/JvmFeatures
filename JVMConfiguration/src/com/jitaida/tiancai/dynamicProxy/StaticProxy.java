package com.jitaida.tiancai.dynamicProxy;

public class StaticProxy implements BaseProcess{
    @Override
    public void before() {

    }

    @Override
    public void after() {

    }

    @Override
    public void doProcess(){
        before();
        doProcess();
        after();
    }
}
