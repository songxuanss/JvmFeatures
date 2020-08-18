package com.jitaida.tiancai.dynamicProxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
public class DynamicProxy implements InvocationHandler{

    private Object process;

    public DynamicProxy(Object process){
        this.process = process;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName() == "doProcess"){
            Method before = this.process.getClass().getMethod("before");
            Method after = this.process.getClass().getMethod("after");

            InvocationHandler ih = Proxy.getInvocationHandler(proxy);

            if (before != null) before.invoke(this.process);
            method.invoke(this.process);
            if(after != null ) after.invoke(this.process);
        }

        return null;
    }
}
