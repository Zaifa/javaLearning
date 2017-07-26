package cepheus.xzf.concurrent.lock;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by cepheus
 * 26/07/2017.
 */
public class MyCasLock {
    private static AtomicBoolean flag = new AtomicBoolean(true);

    public void lock(){
        while (!flag.compareAndSet(true,false)){}
    }

    public void unlock(){
        flag.set(true);
    }
}
