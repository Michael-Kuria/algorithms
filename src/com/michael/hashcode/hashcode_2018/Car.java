package com.michael.hashcode.hashcode_2018;

import java.util.LinkedList;

public class Car implements Comparable {
    private LinkedList<Integer> rides;
    private int step;
    private Intersection currentPosition;
    private int sortingDistance;

    public Car(){
        rides = new LinkedList<>();
        step = 0;
        currentPosition = new Intersection(0,0);
        sortingDistance = 0;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public Intersection getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Intersection currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getSortingDistance() {
        return sortingDistance;
    }

    public void setSortingDistance(int sortingDistance) {
        this.sortingDistance = sortingDistance;
    }

    public void addLast(int i){
        rides.addLast(i);
    }

    public void removeLast(){
        rides.removeLast();
    }

    @Override
    public String toString(){
        String str = rides.size()+" ";

        if(!rides.isEmpty()){
            for(Integer i : rides){
                str+= i +" ";
            }

        }
        return str;
    }

    @Override
    public int compareTo(Object o) {
        return ((Integer)(this.sortingDistance)).compareTo(((Car)o).sortingDistance);
    }
}
