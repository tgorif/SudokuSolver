package com.example.myapplication;
import java.util.*;
// Backtracking approach Tiles are being iterated according to their proximity to given x and y coordinates,
// prioritising tiles that share constrains with the staring tile
public class BackTrackingProximity implements IStrategySolve{
    List<Tile> tiles= new ArrayList<>();
    Observer observer;
    TileManager manager;
    int[][] grid;
    public boolean solve(int[][] grid) {
        this.grid=grid;
        manager= new TileManager(grid);
        observer = new Observer(manager);
        tiles=manager.getTileListByProximity(0,0);
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
                for (int i=1;i<10;i++) {
                    t.value = i;
                    if (manager.isValid(t)) {
                        observer.assignment(t.x,t.y,i);
                        if(solve(list,c)) {
                            return true;
                        }
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
