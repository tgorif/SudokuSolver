package com.example.myapplication;
import java.util.HashSet;
import java.util.Set;

public class Tile {
    int x;
    int y;
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
            setCandidates();
            if(!isValid()) return  null;
            return new Tile(this);
        }
        private boolean isValid(){
            if(x<0 || y<0|| x>8 || y>8 || value<0 || value>9) return false;
            return  true;
        }
        private void setCandidates(){
            candidates= new HashSet<>();
            if(this.value==0) {
                for (int i = 1; i < 10; i++) {
                    candidates.add(i);
                }
            }
        }
    }
    private Tile(TileBuilder tb){
        this.x=tb.x;
        this.y=tb.y;
        this.value=tb.value;
        this.candidates=tb.candidates;
    }
    public Set<Integer> getCandidates(){
        return candidates;
    }
    public void setNeighbors(Set<Tile> set){
        adjacent=set;
    }
    public boolean reduceCandidates(){
        for(Tile t : adjacent){
            if(t.value!=null){
                candidates.remove(t.value);
            }
        }
        if(candidates.size()==1){
            value=(Integer)candidates.toArray()[0];
            return true;
        }
        return false;
    }
}
