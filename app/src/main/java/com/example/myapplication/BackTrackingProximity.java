package com.example.myapplication;
import java.util.*;
// Backtracking approach Tiles are being iterated according to their proximity to given x and y coordinates,
// prioritising tiles that share constrains with the staring tile
public class BackTrackingProximity implements IStrategySolve{
    List<Tile> tiles= new ArrayList<>();
    Observer observer=new Observer();
    TileManager manager;
    int[][] grid;
    public boolean solve(int[][] grid) {
        this.grid=grid;
        manager= new TileManager(grid);
        manager.setAdjacency();
        tiles=manager.getTileListByProximity(0,0);
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
