package chapterFour;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * asus 梅锦涛
 * 2022/4/3
 *
 * @author mjt
 */

/**
 * 读读共享
 *
 * todo 总结： 只要出现“写操作”，就是互斥的。 而“读读”是异步的，非互斥的。
 */
public class ReentrantReadWriteLockTest {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private String username = "abc";

    public void testMethod1 () throws InterruptedException {
        // 读读共享
//        lock.readLock().lock();
//        System.out.println("begin: " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
//        TimeUnit.SECONDS.sleep(4);
//        System.out.println("end: " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
//        lock.readLock().unlock();

        // 写写互斥
//        try {
//            lock.writeLock().lock();
//            System.out.println("获得写锁："  + Thread.currentThread().getName() + " " + System.currentTimeMillis());
//            Thread.sleep(10000);
//        } finally {
//            lock.writeLock().unlock();
//        }


        // 读写互斥
          // 读
        try {
            lock.readLock().lock();
            System.out.println("获得读锁：" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(10000);
        } finally {
            lock.readLock().unlock();
        }

    }
    // 写
    public void write () throws InterruptedException {
        try {
            lock.writeLock().lock();
            System.out.println("获得写锁：" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(10000);
        } finally {
            lock.writeLock().unlock();
        }
    }

}

class ThreadZ extends Thread {

    private ReentrantReadWriteLockTest lockTest = new ReentrantReadWriteLockTest();

    public ThreadZ (ReentrantReadWriteLockTest lockTest ) {
        super();
        this.lockTest = lockTest;
    }

    @Override
    public void run() {
        try {
            lockTest.testMethod1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
class ThreadX extends Thread {

    private ReentrantReadWriteLockTest lockTest = new ReentrantReadWriteLockTest();

    public ThreadX (ReentrantReadWriteLockTest lockTest ) {
        super();
        this.lockTest = lockTest;
    }

    @Override
    public void run() {
        try {
            lockTest.write();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
class Test {

    public static void main(String[] args) throws InterruptedException {

        ReentrantReadWriteLockTest lockTest = new ReentrantReadWriteLockTest();

//        ThreadZ threadZ = new ThreadZ(lockTest);
//        threadZ.setName("A");
//        threadZ.start();
//
//        Thread.sleep(1000);
//
//        ThreadZ threadB = new ThreadZ(lockTest);
//        threadB.setName("B");
//        threadB.start();

        // 读写互斥
        ThreadZ threadZ = new ThreadZ(lockTest);
        threadZ.setName("A");
        threadZ.start();

        Thread.sleep(1000);

        ThreadX threadB = new ThreadX(lockTest);
        threadB.setName("B");
        threadB.start();
    }
}