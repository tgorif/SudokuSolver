package com.example.myapplication;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        int[][] testcase = new int[9][9];
        {
            testcase[0][0] = 8;
            testcase[0][1] = 6;
            testcase[0][4] = 2;
            testcase[1][3] = 7;
            testcase[1][7] = 5;
            testcase[1][8] = 9;
            testcase[3][4] = 6;
            testcase[3][6] = 8;
            testcase[4][1] = 4;
            testcase[5][2] = 5;
            testcase[5][3] = 3;
            testcase[5][8] = 7;
            testcase[7][1] = 2;
            testcase[7][6] = 6;
            testcase[8][2] = 7;
            testcase[8][3] = 5;
            testcase[8][5] = 9;
        }

    }
}