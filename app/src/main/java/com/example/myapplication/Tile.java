package com.example.myapplication;
import java.util.HashSet;
import java.util.Set;

public class Tile {
    int x;
    int y;
    int staringClues=0;
    Integer value;
    Set<Integer> candidates;
    Set<Tile> adjacent;
    public static class TileBuilder{
        private final int x;
        private final int y;
        private int value=0;
        private Set<Integer> candidates;

        public TileBuilder(int x,int y){
            this.x=x;
            this.y=y;
        }
        public TileBuilder value(int value){
            this.value=value;
            return this;
        }
        public Tile build(){
            if(!isValid()) return  null;
            return new Tile(this);
        }
        private boolean isValid(){
            if(x<0 || y<0|| x>8 || y>8 || value<0 || value>9) return false;
            return  true;
        }
    }
    private Tile(TileBuilder tb){
        this.x=tb.x;
        this.y=tb.y;
        this.value=tb.value;
    }
    public Set<Integer> getCandidates(){
        return candidates;
    }
    public void setNeighbors(Set<Tile> set){
        adjacent=set;
    }
    public void setCandidates(){
        candidates=new HashSet<>();
        if(this.value>0 && this.value<10){
            candidates.add(this.value);
        }
        else {
            for (int i = 1; i < 10; i++) {
                candidates.add(i);
            }
            for (Tile t : adjacent) {
                if (t.value>0) {
                    candidates.remove(t.value);
                }
            }
        }
    }
    public void setValue(int n){
        value=n;
        for (Tile t : adjacent){
            t.updateCandidates(n);
        }
    }
    public void resetValue(){
        value=0;
        for (Tile t : adjacent){
            t.revertUpdate();
        }
    }
    private void updateCandidates(int n){
        if(candidates.contains(n)){
            candidates.remove(n);
        }
    }
    private void revertUpdate(){
        setCandidates();
    }
    public void setStartingClues(){
        for (Tile t : adjacent){
            if(t.value!=0) staringClues++;
        }
    }
}
