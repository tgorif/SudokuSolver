package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Memento {
    private TileManager manager;
    private List<TileChange> changes;
    private List<int[]> changes2;
    public Memento(TileManager manager){
        this.manager=manager;
        changes=new ArrayList<>();
        changes2=new ArrayList<>();
    }
    public void assign(int x,int y,int v){
        //changes.add(new TileChange(x,y,v));
        changes2.add(new int[]{x, y, v});
    }
    public void revert(){
        if(changes2.get(changes2.size()-1).length==1){
            changes2.get(changes2.size()-1)[0]++;
        }
        else{
            changes2.add(new int[]{1});
        }
    }
    public void printChangesSize(){
        System.out.println(changes.size() + " changes have been made");
    }
}
