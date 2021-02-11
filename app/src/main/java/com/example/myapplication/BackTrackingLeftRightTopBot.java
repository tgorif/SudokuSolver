package com.example.myapplication;

import java.util.List;
// Backtracking approach Tiles are being iterated according to their x and y coordinates
public class BackTrackingLeftRightTopBot implements IStrategySolve {
    List<Tile> tiles;
    Observer observer;
    TileManager manager;
    public boolean solve(int[][] grid) {
        manager= new TileManager(grid);
        observer= new Observer(manager);
        tiles=manager.getList();
        observer.start();
        return solve(tiles,0);
    }
    private boolean solve(List<Tile> list,int k){
        for(int c=k;c<list.size();c++){
            Tile t=list.get(c);
            if(t.value==0) {
                for (int i=1;i<10;i++) {
                    t.value = i;
                    observer.assignment(t.x,t.y,i);
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
    public void printAnalytics() {
        observer.printResults();
    }
    public void printSolution(){
        manager.printSolution();
    }
}
