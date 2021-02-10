package com.example.myapplication;
import java.util.HashSet;
import java.util.Set;

public class Sudoku {
    Tile[][] board;
    Set<Tile> remainingTiles;
    IStrategySolve strategy;
    int grid[][]= new int[9][9];
    public Sudoku(int[][] grid,IStrategySolve strategy){
        this.strategy=strategy;
        this.grid=grid;
    }
    public boolean solve(){
        return strategy.solve(grid);
    }
    private void setAdjacency(){
        for(int i=0;i<board.length;i++){
            for(int j=0;j< board.length;j++){
                setAdjacentTiles(board[i][j]);
            }
        }
    }
    private void createTiles(){
        if(grid.length!=9 || grid[0].length!=9) return;
        board= new Tile[9][9];
        remainingTiles=new HashSet<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                Tile t;
                if(grid[i][j]>0 && grid[i][j]<10) {
                    t= new Tile.TileBuilder(i,j).value(grid[i][j]).build();
                }
                else{
                    t= new Tile.TileBuilder(i,j).build();
                }
                board[i][j] = t;
                remainingTiles.add(t);
            }
        }
    }
    private int fillObviousTiles(){
        int changed=0;
        for(Tile t : remainingTiles){
            if(t.reduceCandidates()){
                remainingTiles.remove(t);
                changed++;
            }
        }
        return changed;
    }
    private void setAdjacentTiles(Tile t){
        if(t.value!=null) {
            remainingTiles.remove(t);
            return;
        }
        Set<Tile> set = new HashSet<>();
        for(int i=0;i<9;i++){
            set.add(board[t.x][i]);
            set.add(board[i][t.y]);
        }
        int a=t.x/3;
        int b=t.y/3;
        for(int i=a*3;i<(a+1)*3;i++){
            for(int j=b*3;j<(b+1)*3;j++){
                    set.add(board[i][j]);
                }
            }
        t.setNeighbors(set);
    }
}
