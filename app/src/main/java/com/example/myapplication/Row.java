package com.example.myapplication;

import java.util.HashSet;
import java.util.Set;

public class Row {
    Set<Tile> tiles;
    private int valuesSet=0;
    Set<Tile> remainingTiles;
    public Row(Set<Tile> set){
        tiles=set;
        init();
    }
    private  void SetClues(){

    }
    private  void init(){
        remainingTiles= new HashSet<>(tiles);
        for(Tile t : tiles){
            if(t.value==0) remainingTiles.add(t);
            else valuesSet++;
        }
    }
    private void update(){
        for (Tile t: remainingTiles){
            if(t.value!=0){
                valuesSet++;
            }
        }

    }

}
