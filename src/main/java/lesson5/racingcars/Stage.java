package lesson5.racingcars;

import lesson5.racingcars.Car;

public abstract class Stage {
    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}