package lesson6;

//1. Написать метод, которому в качестве аргумента передается
// непустой одномерный целочисленный массив. Метод должен вернуть новый массив,
// который получен путем вытаскивания из исходного массива элементов,
// идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку,
// иначе в методе необходимо выбросить RuntimeException.
// Написать набор тестов для этого метода (по 3-4 варианта входных данных).
// Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].

import java.util.Arrays;

public class ArrayAfterLastNumber4 {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        System.out.println(Arrays.toString(getSubArrayAfterLastNumberFour(numbers)));
    }

    public static int[] getSubArrayAfterLastNumberFour(int[] numbers) {
        return getSubArrayAfterLastNumber(numbers, 4);
    }

    private static int[] getSubArrayAfterLastNumber(int[] numbers, int number) throws RuntimeException{
        int lastIndex = numbers.length - 1;

        while (lastIndex >= 0 && numbers[lastIndex] != number) lastIndex--;

        if (lastIndex < 0) {
            throw new RuntimeException("В передаваемом массиве нет ни одной четверки!");
        }

        return Arrays.copyOfRange(numbers, ++lastIndex, numbers.length);
    }
}
