package lesson5.mfumodel;

import java.util.concurrent.locks.ReentrantLock;

public class MFU {
    private static final int SCAN_TIME = 1000;
    private static final int PRINT_TIME = 1500;
    private static final int COPY_TIME = SCAN_TIME + PRINT_TIME;
    private static final int EMAIL_TIME = 100;

    private final ReentrantLock emailLock = new ReentrantLock();
    private final ReentrantLock scanLock = new ReentrantLock();
    private final ReentrantLock printLock = new ReentrantLock();

    private static String message = "a";

    public static void main(String[] args) throws InterruptedException{
        MFU mfu = new MFU();
        new Thread(mfu.new ScanAction()).start();
        new Thread(mfu.new CopyAction()).start();
        new Thread(mfu.new PrintAction()).start();


        new Thread(mfu.new EmailAction()).start();
        new Thread(mfu.new EmailAction()).start();
    }

    class ScanAction implements Runnable {
        public void run() {
            System.out.println("МФУ получило задание на сканирование.");
            scanLock.lock();
            System.out.println("МФУ сканирует..");
            try {
                Thread.sleep(SCAN_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("МФУ закончило сканирование.");
            scanLock.unlock();
        }
    }
    class PrintAction implements Runnable {
        public void run() {
            System.out.println("МФУ получило задание на печать.");
            printLock.lock();
            System.out.println("МФУ печатает..");
            try {
                Thread.sleep(PRINT_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("МФУ закончило печать.");
            printLock.unlock();
        }
    }

    class CopyAction implements Runnable {
        public void run() {
            System.out.println("МФУ получило задание на копирование.");

            while (printLock.isLocked() || scanLock.isLocked()) {}

            printLock.lock();
            scanLock.lock();
            System.out.println("МФУ копирует..");
            try {
                Thread.sleep(PRINT_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("МФУ закончило копировать.");
            printLock.unlock();
            scanLock.unlock();
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
