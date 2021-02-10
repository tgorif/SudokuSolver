package com.example.myapplication;

public class Observer {
    private int invalidAssignments=0;
    private long start;
    private long end;
    public void deadEnd(){
        invalidAssignments++;
    }
    public void printResults(){
        System.out.println("invalid Assignments: " + invalidAssignments);
        System.out.println("Start: " + start + " End: " + end + " TotalTime: " + (end-start));
    }
    public void end() {
        end=System.currentTimeMillis();
    }
    public void start() {
        start=System.currentTimeMillis();
    }
}
