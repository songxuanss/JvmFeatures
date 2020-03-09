package utils;

import java.io.Closeable;

public class IOUtil {

    public static void gracefullyClose(Closeable c){
        if (c != null) {
            try{
                c.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
