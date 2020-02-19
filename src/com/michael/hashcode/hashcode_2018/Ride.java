package com.michael.hashcode.hashcode_2018;

public class Ride implements Comparable {
    private Intersection start;
    private Intersection finish;
    private int earliestStart;
    private int latestFinish;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Ride(Intersection start, Intersection finish, int earliestStart, int latestFinish) {
        this.start = start;
        this.finish = finish;
        this.earliestStart = earliestStart;
        this.latestFinish = latestFinish;
    }

    public int getDistance(){
        return Math.abs(start.getX() - finish.getX()) + Math.abs(start.getY() - finish.getY());
    }

    public Intersection getStart() {
        return start;
    }

    public Intersection getFinish() {
        return finish;
    }

    public int getEarliestStart() {
        return earliestStart;
    }

    public int getLatestFinish() {
        return latestFinish;
    }

    @Override
    public String toString(){
        return start + " "+ finish + " "+ earliestStart+ " "+latestFinish;
    }

    @Override
    public int compareTo(Object o) {
        return ((Integer)(this.earliestStart)).compareTo(((Ride)o).earliestStart);
    }
}
