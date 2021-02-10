package com.example.myapplication;
import java.util.HashSet;
import java.util.Set;

public class Sudoku {

    IStrategySolve strategy;
    int[][] grid;
    public Sudoku(int[][] grid,IStrategySolve strategy){
        this.strategy=strategy;
        this.grid=grid;
    }
    public void printAnalytics(){
        strategy.printAnalytics();
    }
    public void printSolution(){
        strategy.printSolution();
    }
    public boolean solve(){
        return strategy.solve(grid);
    }

}
