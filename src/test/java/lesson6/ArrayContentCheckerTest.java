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
                {new int[]{4,0,1}, new Boolean(true)},
                {new int[]{1,4,2}, new Boolean(true)},
                {new int[]{2,2,4}, new Boolean(false)},
                {new int[]{4,4,6}, new Boolean(false)},
                {new int[]{0,1,5}, new Boolean(false)},
        });
    }

    private int[] numbers;
    private boolean answer;

    public ArrayContentCheckerTest(int[] numbers, boolean answer) {
        this.numbers    = numbers   ;
        this.answer = answer;
    }

    @Test
    public void checkContainsAtLeastOne1or4() {
        Assert.assertEquals(answer, ArrayContentChecker.checkContainsAtLeastOne1or4(numbers));
    }
}