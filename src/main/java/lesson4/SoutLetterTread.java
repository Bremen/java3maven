package lesson4;

//1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз
//(порядок – ABСABСABС).
//Используйте wait/notify/notifyAll.

public class SoutLetterTread extends Thread {
    static private final Object mon = new Object();
    static private volatile char currentLetter = 'A';

    private char letter;
    private char nextLetter;

    public SoutLetterTread(char letter, char nextLetter) {
        this.letter = letter;
        this.nextLetter = nextLetter;
    }

    public static void main(String[] args) {
        Thread threadA = new SoutLetterTread('A', 'B');
        Thread threadB = new SoutLetterTread('B', 'C');
        Thread threadC = new SoutLetterTread('C', 'A');

        threadA.start();
        threadB.start();
        threadC.start();
    }

    @Override
    public void run() {
        printLetter();
    }

    void printLetter() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != letter) {
                        mon.wait();
                    }
                    System.out.print(letter);
                    currentLetter = nextLetter;
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

