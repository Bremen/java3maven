package lesson5.mfumodel;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MFU {
    private static final int SCAN_TIME = 5000;
    private static final int PRINT_TIME = 6500;
    private static final int COPY_TIME = SCAN_TIME + PRINT_TIME;
    private static final int EMAIL_TIME = 100;

    private final ReentrantReadWriteLock scanPrintLock = new ReentrantReadWriteLock(false);
    private final ReentrantLock emailLock = new ReentrantLock();

    private static String message = "a";

    public static void main(String[] args) throws InterruptedException{
        MFU mfu = new MFU();
        new Thread(mfu.new ScanAction()).start();
        new Thread(mfu.new PrintAction()).start();

//        new Thread(mfu.new EmailAction()).start();
//        new Thread(mfu.new EmailAction()).start();
//        new Thread(mfu.new EmailAction()).start();
//        new Thread(mfu.new EmailAction()).start();
    }

    class ScanAction implements Runnable {
        public void run() {
            System.out.println("МФУ получило задание на сканирование.");
            scanPrintLock.readLock().lock();
            System.out.println("МФУ сканирует..");
            try {
                Thread.sleep(SCAN_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("МФУ закончило сканирование.");
            scanPrintLock.readLock().unlock();
        }
    }
    class PrintAction implements Runnable {
        public void run() {
            System.out.println("МФУ получило задание на печать.");
            scanPrintLock.writeLock().lock();
            System.out.println("МФУ печатает..");
            try {
                Thread.sleep(PRINT_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("МФУ закончило печать.");
            scanPrintLock.writeLock().unlock();
        }
    }

    class CopyAction implements Runnable {
        public void run() {
            for(int i = 0; i<= 10; i ++) {
                try {
                    scanPrintLock.writeLock().lock();
                    message = message.concat("b");
                } finally {
                    scanPrintLock.writeLock().unlock();
                }
            }
        }
    }

    class EmailAction implements Runnable {
        public void run() {
            System.out.println("МФУ получило задание на отправку email.");
            emailLock.lock();
            System.out.println("МФУ отправляет email..");
            try {
                Thread.sleep(EMAIL_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("МФУ отправило email..");
            emailLock.unlock();
        }
    }
}
