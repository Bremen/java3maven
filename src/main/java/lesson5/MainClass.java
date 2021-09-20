package lesson5;

import java.util.concurrent.CountDownLatch;

public class MainClass {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        Race race = initRace();
        Car[] cars = initCars(CARS_COUNT, race);

        prepareCars(cars);
        goAhead();

        endRace();
    }

    private static void endRace() {
        try {
            Car.finishFlag.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

    private static void goAhead() {
        try {
            Car.startFlag.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
    }

    private static void prepareCars(Car[] cars) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
    }

    private static Car[]  initCars(int carsCount, Race race) {
        Car[] cars = new Car[carsCount];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        Car.startFlag = new CountDownLatch(CARS_COUNT);
        Car.finishFlag = new CountDownLatch(CARS_COUNT);

        return cars;
    }

    private static Race initRace() {
        return  new Race(new Road(60), new Tunnel(CARS_COUNT / 2), new Road(40));
    }
}