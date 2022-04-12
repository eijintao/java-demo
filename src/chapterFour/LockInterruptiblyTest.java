package chapterFour;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;

import java.io.InputStream;
import java.util.concurrent.locks.ReentrantLock;

/**
 * asus 梅锦涛
 * 2022/4/3
 *
 * @author mjt
 */

/**
 * lockInterruptibly()方法的作用是当某个线程尝试获得锁并且阻塞在lock-Interruptibly()方法时，该线程可以被中断。
 */
public class LockInterruptiblyTest {

    private ReentrantLock lock = new ReentrantLock();

    public void testMethod () throws InterruptedException {

            lock.lockInterruptibly();
            System.out.println("begin " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            for (int i = 0; i < Integer.MAX_VALUE / 10; i++) {
                String newString = new String();
                Math.random();
                // 为了不让currentThread() 过高的占有CPU资源
                // 所以执行yield()方法
                Thread.currentThread().yield();
            }

            System.out.println("end " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            lock.unlock();


    }

}

class ThreadA extends  Thread {

    private LockInterruptiblyTest interruptiblyTest;

    public ThreadA (LockInterruptiblyTest lockInterruptiblyTest) {
        super();
        this.interruptiblyTest = lockInterruptiblyTest;
    }

    @Override
    public void run() {
        try {
            interruptiblyTest.testMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class RunTest1 {

    public static void main(String[] args) throws InterruptedException {
        LockInterruptiblyTest interruptiblyTest = new LockInterruptiblyTest();

        ThreadA threadA = new ThreadA(interruptiblyTest);
        threadA.setName("a");
        threadA.start();

        Thread.sleep(5000);

        ThreadA threadb = new ThreadA(interruptiblyTest);
        threadb.setName("b");
        threadb.start();

        Thread.sleep(5000);

        threadb.interrupt();

        System.out.println("main线程中断b, 但并没有成功！");


    }
}