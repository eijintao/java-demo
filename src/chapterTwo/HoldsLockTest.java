package chapterTwo;

/**
 * asus 梅锦涛
 * 2022/4/3
 *
 * @author mjt
 */
public class HoldsLockTest {

    /**
     * holdsLock(Object obj)方法的作用是
     *          当currentThread在指定的对象上保持锁定时，返回true。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("A " + Thread.currentThread().holdsLock(HoldsLockTest.class)); // false

        synchronized (HoldsLockTest.class) {
            System.out.println("B " + Thread.currentThread().holdsLock(HoldsLockTest.class));// true
        }

        System.out.println("C " + Thread.currentThread().holdsLock(HoldsLockTest.class));// false
    }

}
