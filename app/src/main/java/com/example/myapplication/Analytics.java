package com.example.myapplication;

public class Analytics {
    private long startTime;
    private long endTime;
    private int invalidAssignments=0;

    public void setStart(){
        startTime=System.currentTimeMillis();
    }
    public void setEnd(){
        endTime=System.currentTimeMillis();
    }
    public void invalidAssignment(){
        invalidAssignments++;
    }
    public void printResults() {
        System.out.println("invalid Assignments: " + invalidAssignments);
        System.out.println("Start: " + startTime + " End: " + endTime + " TotalTime: " + (endTime-startTime));
        long freePercentage=(100*Runtime.getRuntime().freeMemory())/Runtime.getRuntime().totalMemory();
        System.out.println("Memory Total " + (Runtime.getRuntime().totalMemory()));
        System.out.println("Memory Usage " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        System.out.println("Memory Free " + freePercentage + "%");
    }
}
