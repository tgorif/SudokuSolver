package com.example.myapplication;

import java.util.List;
// Backtracking approach Tiles are being iterated according to their x and y coordinates
public class BackTrackingLeftRightTopBot implements IStrategySolve {
    List<Tile> tiles;
    Observer observer = new Observer();
    TileManager manager;
    public boolean solve(int[][] grid) {
        manager= new TileManager(grid);
        manager.setAdjacency();
        tiles=manager.getList();
        return solve(tiles);
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
                    if (manager.isValid(t) &&solve(list,c)) {
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
    public void printAnalytics() {
        observer.printResults();
    }
    public void printSolution(){
        manager.printSolution();
    }
}
