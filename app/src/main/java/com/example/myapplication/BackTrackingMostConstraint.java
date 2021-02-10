package com.example.myapplication;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.util.*;
// Backtracking approach Tiles are being iterated according to the amount of their possible assignment given starting clues
public class BackTrackingMostConstraint implements IStrategySolve{
    List<Tile> tiles= new ArrayList<>();
    Observer observer=new Observer();
    TileManager manager;
    int[][] grid;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean solve(int[][] grid) {
        this.grid=grid;
        manager = new TileManager(grid);
        manager.setAdjacency();
        manager.setCandidates();
        tiles=manager.getFilteredList(Comparator.comparingInt((Tile t) -> t.getCandidates().size()));
        return solve(tiles);
    }
    public void printAnalytics() {
        observer.printResults();
    }
    public void printSolution() {
        manager.printSolution();
    }
    private boolean solve(List<Tile> list){
        observer.start();
        for(Tile t : list){
            if(t.value==0) {
                for (int i : t.candidates) {
                    t.value = i;
                    if (manager.isValid(t) && solve(list,list.indexOf(t))) {
                        observer.end();
                        return true;
                    }
                    t.value = 0;
                    observer.deadEnd();
                }
                return false;
            }
        }
        return true;
    }
    private boolean solve(List<Tile> list,int k){
        for(int c=k;c<list.size();c++){
            Tile t=list.get(c);
            if(t.value==0) {
                t.setCandidates();
                for (int i : t.candidates) {
                    t.value = i;
                    if (solve(list,c)) {
                        return true;
                    }
                    t.value = 0;
                    observer.deadEnd();
                }
                return false;
            }
        }
        return true;
    }
}
