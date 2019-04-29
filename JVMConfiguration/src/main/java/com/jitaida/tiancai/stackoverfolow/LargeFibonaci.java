package com.jitaida.tiancai.stackoverfolow;

public class LargeFibonaci {

    public long getFibonaci(int n, String text){
        String longString = "As a developer, everyone will get a question about enabling the GC log on production servers. Is it advisable to enable GC log on production server? Yes, it is recommended to enable the GC log on production servers. The overhead by enabling the GC log on the JVM is minimal. As per Standard Performance Evaluation Corporation (SPEC), the world record high performance Java Enterprise production servers are running with GC log enabled. First, have to pass the JVM arguments to enable the GC log. Below are the options given for JDK8 Oracle HotSpot JVM.\n" +
                "\n" +
                "Note: Set the heap size as low to get the GC log for this exercise:\n" +
                "\n" +
                "-XX:+DisableExplicitGC\n" +
                "-XX:+PrintGCDetails\n" +
                "-XX:+PrintGCApplicationStoppedTime\n" +
                "-XX:+PrintGCApplicationConcurrentTime\n" +
                "-XX:+PrintGCDateStamps\n" +
                "-Xloggc:gclog.log\n" +
                "-XX:+UseGCLogFileRotation";
        String resultString = longString + text;

        if (n == 1){
            return 1L;
        }

        if (n == 2) {
            return 2L;
        }

        return getFibonaci(n-2, resultString) + getFibonaci(n-1,resultString);
    }

    public static void main(String args[]) {
        LargeFibonaci l = new LargeFibonaci();

        /*
            As interned-Stirng move to heap, the JAVA OutOfMemoryError will be throwed here
         */
        System.out.println("result: " + l.getFibonaci(10000, "test"));
    }
}
