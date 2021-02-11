package com.example.myapplication;

public class Observer {
    private final TileManager manager;
    private final Analytics analytics;
    private final Memento memento;
    public Observer(TileManager manager){
        this.manager=manager;
        this.memento=new Memento(this.manager.deepCopy());
        analytics=new Analytics();
    }
    public void deadEnd(int x,int y){
        analytics.invalidAssignment();
        memento.revert();
    }
    public void assignment(int x,int y,int v){
        memento.assign(x,y,v);
    }
    public void printResults(){
        analytics.printResults();
        memento.printChangesSize();
    }
    public void end() {
        analytics.setEnd();
    }
    public void start() {
        analytics.setStart();
    }
}
