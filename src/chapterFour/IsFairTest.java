package chapterFour;

import java.util.concurrent.locks.ReentrantLock;

/**
 * asus 梅锦涛
 * 2022/4/3
 *
 * @author mjt
 */
public class IsFairTest {

    /**
     * isFair(): 判断是不是公平锁
     *    公平锁： 公平锁采用先到先得的策略，每次获取锁之前都会检查队列里面有没有排队等待的线程，
     *              没有才会尝试获取锁，如果有就将当前线程追加到队列中。
     *
     *    非公平锁：采用“有机会插队”的策略，一个线程获取锁之前，
     *              要先去尝试获取锁，而不是在队列中等待，如果真的获取锁成功，
     *              说明线程虽然是后启动的，但先获得了锁，这就是“作弊插队”的效果，
     *              如果获取锁没有成功，那么将自身追加到队列中进行等待。
     * @param args
     */
    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock(true);
        System.out.println(reentrantLock.isFair());// true

        ReentrantLock reentrantLock1 = new ReentrantLock(false);
        System.out.println(reentrantLock1.isFair());// false

        ReentrantLock reentrantLock2 = new ReentrantLock();
        System.out.println(reentrantLock2.isFair());// false

    }

}
