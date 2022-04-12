package chapterFour;

import java.util.concurrent.locks.ReentrantLock;

/**
 * asus 梅锦涛
 * 2022/4/3
 *
 * @author mjt
 */

/**
 * isLocked()方法的作用是查询此锁定是否由任意线程持有，且没有释放。
 *
 *
 */
public class IsLockTest {

    private ReentrantLock lock = new ReentrantLock();

    public void IsLockTestMethod () {

        try {
            System.out.println(lock.isLocked());// false
            lock.lock();
            System.out.println(lock.isLocked());// true
        } finally {
            lock.unlock();
        }
    }

}

class RunTest {

    public static void main(String[] args) {

        final IsLockTest isLockTest = new IsLockTest();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                isLockTest.IsLockTestMethod();
            }
        };

        new Thread(runnable).start();

    }
}
