package com.jitaida.tiancai.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        BaseProcess proc = new PrintProcess();

        InvocationHandler ih = new DynamicProxy(proc);

        // 关键是getProxyClass0方法中调用getCachedProxy->ProxyFactory.apply(key, parameter));
        BaseProcess proc2 = (BaseProcess) Proxy.newProxyInstance(ih.getClass().getClassLoader(), proc.getClass().getInterfaces(), ih);
        proc2.doProcess();
    }
}
