package lesson5;

import java.util.concurrent.CyclicBarrier;

public class MainClass {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        Race race = initRace();
        Car[] cars = initCars(CARS_COUNT, race);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

//        while (Car.cb.reset()) {
//
//        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

    private static Car[]  initCars(int carsCount, Race race) {
        Car[] cars = new Car[carsCount];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        Car.cb = new CyclicBarrier(CARS_COUNT);

        return cars;
    }

    private static Race initRace() {
        return  new Race(new Road(60), new Tunnel(), new Road(40));
    }
}