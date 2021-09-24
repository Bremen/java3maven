package lesson6;

public class ArrayContentChecker {
    public static void main(String[] args) {
        System.out.println(checkContains1and4(new int[]{1, 1, 4, 4}));
        System.out.println(checkContains1and4(new int[]{1, 1, 1, 1}));
        System.out.println(checkContains1and4(new int[]{4, 4, 4, 4}));
        System.out.println(checkContains1and4(new int[]{5, 4, 1, 0}));
    }

    public static boolean checkContains1and4(int[] numbers) {
        if (numbers == null) return false;

        boolean hasFour = false;
        boolean hasOne = false;

        for (int number : numbers) {
            if (number != 4 && number != 1)
                return false;

            if (!hasOne && number == 1)
                hasOne = true;

            if (!hasFour && number == 4)
                hasFour = true;
        }

        return hasOne && hasFour;
    }
}
