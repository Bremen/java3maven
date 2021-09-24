package lesson6;

public class ArrayContentChecker {
    public static void main(String[] args) {
        System.out.println(checkContainsAtLeastOne1or4(new int[]{1, 2, 3, 4}));
        System.out.println(checkContainsAtLeastOne1or4(new int[]{0, 2, 3, 4}));
        System.out.println(checkContainsAtLeastOne1or4(new int[]{1, 2, 3, 0}));
        System.out.println(checkContainsAtLeastOne1or4(new int[]{5, 2, 3, 0}));
    }

    public static boolean checkContainsAtLeastOne1or4(int[] numbers) {
        boolean isThereOne = false;
        boolean isThereFour = false;

        for (int i = 0; i < numbers.length && (!isThereOne || !isThereFour); i++) {
            if (numbers[i] == 4)
                isThereFour = true;

            if (numbers[i] == 1)
                isThereOne = true;
        }

        return isThereOne && isThereFour;
    }
}
