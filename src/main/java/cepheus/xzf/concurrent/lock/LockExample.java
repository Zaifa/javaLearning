package cepheus.xzf.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by cepheus
 * 25/07/2017.
 */
public class InterruptLockExample {
    private ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        InterruptLockExample interruptLockExample = new InterruptLockExample();
        ReentrantLock lock = interruptLockExample.getLock();
        Thread thread1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + " trying to get the lock and is ready to be interrupt");
                lock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + " get the lock and interrupt failed!");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " is interrupted");
            }finally {
                if(lock.isLocked()){
                    System.out.println(Thread.currentThread().getName() + " unlock the lock");
                    lock.unlock();
                }
            }
        }, "thread1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get the lock and sleep for 5 seconds");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " is going to interrupt " + thread1.getName());
                thread1.interrupt();
                System.out.println(thread1.getName() + " is interrupted by " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if(lock.isLocked()){
                    System.out.println(Thread.currentThread().getName() + " unlock the lock");
                    lock.unlock();
                }
            }

        }, "thread2");
        thread2.start();
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public void setLock(ReentrantLock lock) {
        this.lock = lock;
    }
}
