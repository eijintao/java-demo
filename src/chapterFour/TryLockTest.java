package chapterFour;

import java.lang.Iterable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * asus 梅锦涛
 * 2022/4/3
 *
 * @author mjt
 */

/**
 * public boolean tryLock(long timeout, TimeUnit unit)方法的作用
 *      是嗅探拿锁，如果在指定的timeout内持有了锁，则返回true，
 *      如果超过时间则返回false。timeout参数代表当前线程抢锁的时间。
 */
public class TryLockTest {

    public ReentrantLock lock = new ReentrantLock();

    public void waitMethod () throws InterruptedException {
        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                System.out.println("     " + Thread.currentThread().getName() + "获得锁的时间：" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(10);
            } else {
                System.out.println("     " + Thread.currentThread().getName() + "没有获得锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // todo:  public boolean isHeldByCurrentThread()方法的作用是查询当前线程是否持有此锁。
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

}


class RunTryLock {

    public static void main(String[] args) {

        final TryLockTest lockTest = new TryLockTest();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "调用waitMethod时间：" + System.currentTimeMillis());
                try {
                    lockTest.waitMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        thread1.setName("A");
        thread1.start();

        Thread thread2 = new Thread(runnable);
        thread2.setName("B");
        thread2.start();

    }
}