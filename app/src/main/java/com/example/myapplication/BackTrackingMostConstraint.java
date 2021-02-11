package com.example.myapplication;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.util.*;
// Backtracking approach Tiles are being iterated according to the amount of their possible assignment given starting clues
public class BackTrackingMostConstraint implements IStrategySolve{
    List<Tile> tiles= new ArrayList<>();
    Observer observer;
    TileManager manager;
    int[][] grid;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean solve(int[][] grid) {
        this.grid=grid;
        manager = new TileManager(grid);
        observer= new Observer(manager);
        manager.setCandidates();
        tiles=manager.getFilteredList(Comparator.comparingInt((Tile t) -> t.getCandidates().size()));
        observer.start();
        return solve(tiles,0);
    }
    public void printAnalytics() {
        observer.printResults();
    }
    public void printSolution() {
        manager.printSolution();
    }
    private boolean solve(List<Tile> list,int k){
        for(int c=k;c<list.size();c++){
            Tile t=list.get(c);
            if(t.value==0) {
                t.setCandidates();
                for (int i : t.candidates) {
                    t.value = i;
                    observer.assignment(t.x,t.y,i);
                    if (solve(list,c)) {
                        return true;
                    }
                    t.value = 0;
                    observer.deadEnd(t.x,t.y);
                }
                return false;
            }
        }
        observer.end();
        return true;
    }
}
