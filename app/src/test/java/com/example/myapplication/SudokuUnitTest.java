package com.example.myapplication;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SudokuUnitTest {
    List<int[][]> testcases= new ArrayList<>();

    private void setup(){
        int[][] case1={
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
        int[][] case2={
                { 0, 0, 0, 0, 0, 0, 5, 0, 0 },
                { 0, 0, 0, 0, 4, 0, 2, 6, 0 },
                { 3, 7, 0, 0, 1, 0, 0, 0, 0 },
                { 6, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 2, 8, 9, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 2, 0, 1, 6, 3, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 9, 0, 0, 0, 7, 0, 0, 0 },
                { 0, 6, 0, 5, 2, 0, 0, 7, 8 }
        };

        testcases.add(case1);
        //testcases.add(testcase);
        //testcases.add(case2);

    }

    @Test
    public void testMostConstraint(){
        setup();
        for(int[][] c : testcases){
            Sudoku s = new Sudoku(c,new BackTrackingMostConstraint());
            Assert.assertTrue(s.solve());
            s.printAnalytics();
        }
    }
    @Test
    public void testLeastConstraint(){
        setup();
        for(int[][] c : testcases){
            Sudoku s = new Sudoku(c,new BacktrackingLeastConstraint());
            Assert.assertTrue(s.solve());
            s.printAnalytics();
        }
    }
    @Test
    public void testLeftRightTopBot(){
        setup();
        for(int[][] c : testcases){
            Sudoku s = new Sudoku(c,new BackTrackingLeftRightTopBot());
            Assert.assertTrue(s.solve());
            s.printAnalytics();
        }
    }
    @Test
    public void testProximity(){
        setup();
        for(int[][] c : testcases){
            Sudoku s = new Sudoku(c,new BackTrackingProximity());
            Assert.assertTrue(s.solve());
            s.printAnalytics();
            s.printSolution();
        }
    }
    @Test
    public void testStartingClues(){
        setup();
        for(int[][] c : testcases){
            Sudoku s = new Sudoku(c,new BackTrackingStaringClues());
            Assert.assertTrue(s.solve());
            s.printAnalytics();
        }
    }
}
