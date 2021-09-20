package lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private static int CARS_COUNT;
    public static CountDownLatch startFlag;
    public static CountDownLatch finishFlag;


    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;

        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            startFlag.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            startFlag.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        System.out.println(this.name + " финишировал");

        if (race.winnerFlag.incrementAndGet() == 1) {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> " + this.name + " ВЫЙГРАЛ ГОНКУ!!!");
        }

        finishFlag.countDown();
        try {
            finishFlag.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
