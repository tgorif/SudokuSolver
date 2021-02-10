package com.example.myapplication;
import android.os.Build;
import androidx.annotation.RequiresApi;

import java.util.*;
// Backtracking approach Tiles are being iterated according to their Number of adjacent clues.
// Clues are considered adjacent if they share a row, a col or are int the same 3x3 field.
public class BackTrackingStaringClues implements IStrategySolve{
    List<Tile> tiles= new ArrayList<>();
    Observer observer=new Observer();
    TileManager manager;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean solve(int[][] grid) {
        manager=new TileManager(grid);
        manager.setAdjacency();
        manager.setStartingClues();
        tiles = manager.getFilteredList(Comparator.comparingInt((Tile t) -> t.staringClues));
        return solve(tiles);
    }
    public void printAnalytics(){
        observer.printResults();
    }
    public void printSolution() {
        manager.printSolution();
    }
    private boolean solve(List<Tile> list){
        observer.start();
        for(Tile t : list){
            if(t.value==0) {
                for (int i=1;i<10;i++) {
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
                for (int i=1;i<10;i++) {
                    t.value = i;
                    if (manager.isValid(t) && solve(list,c)) {
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
