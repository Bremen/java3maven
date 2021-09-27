package lesson6;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ArrayAfterLastNumber4Test {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{4,0}, new int[]{0}},
                {new int[]{1,4,2}, new int[]{2}},
                {new int[]{2,2,4}, new int[]{}},
                {new int[]{4,4,6}, new int[]{6}},
                {new int[]{0,4,4}, new int[]{}},
        });
    }

    private int[] numbers;
    private int[] subNumbers;

    public ArrayAfterLastNumber4Test(int[] numbers, int[] subNumbers) {
        this.numbers    = numbers   ;
        this.subNumbers = subNumbers;
    }

    @Test
    public void getSubArrayAfterLastNumberFour() {
        Assert.assertArrayEquals(ArrayAfterLastNumber4.getSubArrayAfterLastNumberFour(numbers), subNumbers);
    }

    @Test (expected = RuntimeException.class, timeout = 10)
    public void getSubArrayAfterLastNumberFourGenExceptionIfNoNumberFour() {
        Assert.assertArrayEquals(ArrayAfterLastNumber4.getSubArrayAfterLastNumberFour(new int[]{5, 3}), new int[]{3});
    }
}