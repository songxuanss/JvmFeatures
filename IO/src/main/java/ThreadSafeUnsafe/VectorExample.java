package ThreadSafeUnsafe;

import java.util.Vector;

/**
 * Vector is thread safe, but following code may got error
 */
public class VectorExample {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args){
        for (int i = 0; i< 1000; i++){
            vector.add(i);
        }

        Thread thread1 =  new Thread() {
            public void run(){
                for (int i = 0; i < vector.size(); i++){
                    vector.remove(i);
                }
            }
        };

        Thread thread2 =  new Thread() {
            public void run(){
                for (int i = 0; i < vector.size(); i++){
                    vector.get(i);
                }
            }
        };

        thread1.run();
        thread2.run();

        System.out.println("s");
    }
}
