package com.example.myapplication;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.util.*;

public class TileManager {
    private Tile[][] board;
    int[][] grid;
    List<Tile> tiles= new ArrayList<>();
    public TileManager(int[][] grid){
        this.grid=grid;
        init();
    }
    private void init(){
        createTiles();
    }
    private void createTiles(){
        if(grid.length!=9 || grid[0].length!=9) return;
        board= new Tile[9][9];
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
            }
        }
    }
    public  void setAdjacency(){
        for (Tile[] value : board) {
            for (int j = 0; j < board.length; j++) {
                setAdjacentTiles(value[j]);
            }
        }
    }
    private void setAdjacentTiles(Tile t){
        Set<Tile> set = new HashSet<>();
        for(int i=0;i<9;i++){
            set.add(board[t.x][i]);
            set.add(board[i][t.y]);
        }
        int a=t.x/3;
        int b=t.y/3;
        for(int i=a*3;i<(a+1)*3;i++){
            set.addAll(Arrays.asList(board[i]).subList(b * 3, (b + 1) * 3));
        }
        set.remove(t);
        t.setNeighbors(set);
    }
    public void setCandidates(){
        for(Tile[] a : board){
            for(Tile b : a){
                b.setCandidates();
            }
        }
    }
    private void removeGivenClues(){
        List<Tile> r= new ArrayList<>();
        int i=0;
        while (i<tiles.size()){
            if (tiles.get(i).value!=0){
                r.add(tiles.get(i));
            }
            i++;
        }
        for(Tile t : r){
            tiles.remove(t);
        }
    }
    private void setTileList(){
        for (Tile[] i : board){
            tiles.addAll(Arrays.asList(i));
        }
    }
    public List<Tile> getList(){
        setTileList();
        return  tiles;
    }
    public List<Tile> getFilteredList(){
        setTileList();
        removeGivenClues();
        return  tiles;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Tile> getList(Comparator<Tile> comparator){
        setTileList();
        tiles.sort(comparator);
        return tiles;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Tile> getFilteredList(Comparator<Tile> comparator){
        setTileList();
        removeGivenClues();
        if(tiles==null) System.out.println("tiles==null");
        if(comparator==null) System.out.println("Comparator==null");
        tiles.sort(comparator);
        return tiles;
    }
    public void setStartingClues(){
        for(Tile t :tiles){
            t.setStartingClues();
        }
    }
    public void printSolution(){
        for(Tile[] a : board){
            for(Tile b : a){
                System.out.print(b.value + " ");
            }
            System.out.println();
        }
    }
    public boolean isValid(Tile t){
        for (Tile other : t.adjacent){
            if(other.value.equals(t.value))return false;
        }
        return true;
    }
    public List<Tile> getTileListByProximity(int x,int y){
        tiles.add(board[x][y]);
        List<Tile> added= new ArrayList<>();
        for(Tile t : board[0][0].adjacent){
            tiles.add(t);
            added.add(t);
        }
        for (Tile t : added){
            setTileListByProximity(t);
        }
        removeGivenClues();
        return tiles;
    }
    private void setTileListByProximity(Tile t){
        List<Tile> added= new ArrayList<>();
        for(Tile ot : t.adjacent){
            if(!tiles.contains(ot)){
                tiles.add(ot);
                added.add(ot);
            }
        }
        for (Tile ot : added){
            setTileListByProximity(ot);
        }
    }
}
