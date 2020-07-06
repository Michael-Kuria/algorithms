package com.michael.geometry;

/**
 * This class will represent a line segment
 */
public class Segment {

    public static class Point{

        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }


    public Point a, b;

    public Segment(Point a, Point b){
        this.a = a;
        this.b = b;
    }


}
