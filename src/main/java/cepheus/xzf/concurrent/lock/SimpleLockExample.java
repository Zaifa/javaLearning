package cepheus.xzf.concurrent.lock;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by cepheus
 * 26/07/2017.
 */
public class SimpleLockExample {
    private static int value=0;

    public static void main(String[] args) {
        MyCasLock myCasLock = new MyCasLock();
        for(int i=0;i<10;i++){
            new Thread(() -> {
                try {
                    myCasLock.lock();
                    value++;
                    System.out.println(Thread.currentThread().getName() + " has finished adding the value. And the value now is " + value);
                }finally {
                    myCasLock.unlock();
                }

            }, "thread"+i).start();
        }
    }

}
