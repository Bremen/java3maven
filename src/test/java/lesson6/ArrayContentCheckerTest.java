package lesson6;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ArrayContentCheckerTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 1, 4, 4}, true},
                {new int[]{1, 1, 1, 1}, false},
                {new int[]{4, 4, 4, 4}, false},
                {new int[]{5, 4, 1, 0}, false},
        });
    }

    private int[] numbers;
    private boolean answer;

    public ArrayContentCheckerTest(int[] numbers, boolean answer) {
        this.numbers    = numbers   ;
        this.answer = answer;
    }

    @Test
    public void checkContains1and4() {
        Assert.assertEquals(answer, ArrayContentChecker.checkContains1and4(numbers));
    }
}