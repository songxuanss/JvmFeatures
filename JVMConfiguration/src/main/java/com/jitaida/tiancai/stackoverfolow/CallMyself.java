package com.jitaida.tiancai.stackoverfolow;

public class CallMyself {

    public void callMyself(int a)
    {
        /*
         Keep calling myself result in stackoverflow
         */
        callMyself(a);
    }

    public static void main(String [] args) {
        CallMyself a = new CallMyself();
        a.callMyself(1);
    }
}
