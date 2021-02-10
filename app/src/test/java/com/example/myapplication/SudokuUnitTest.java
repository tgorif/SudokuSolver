package com.example.myapplication;

import org.junit.Assert;
import org.junit.Test;

public class SudokuUnitTest {
    @Test
    public void testcase0(){
        int[][] board = {
                { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
                { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
                { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
                { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
                { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
                { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
        };
        Sudoku s = new Sudoku(board,new BackTracking());
        Assert.assertTrue(s.solve());
    }
    public  void SudokuCreation(){
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
        Sudoku s = new Sudoku(testcase,new BackTracking());
        boolean tmp=s.solve();
        Assert.assertTrue(tmp);

    }
}
