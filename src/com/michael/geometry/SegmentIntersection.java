package com.michael.geometry;

/**
 * Determining whether 2 line segments intersect
 */
public class SegmentIntersection {

    /**
     * Find the direction of a point from a line segment
     * @param s
     * @param p
     * @return
     */
    public static int direction(Segment s, Segment.Point p){

        return (s.b.x - s.a.x) * (p.y - s.a.y) - (s.b.y - s.a.y) * (p.x - s.a.x);
    }


    public static boolean onSegment(Segment s, Segment.Point p){

        if(Math.min(s.a.x, s.b.x) <= p.x && p.x <= Math.max(s.a.x, s.b.x) && Math.min(s.a.y, s.b.y) <= p.y && p.y <= Math.max(s.a.y, s.b.y))
            return true;
        return false;
    }

    public static boolean solve(Segment s1 , Segment s2){

        int d1 = direction(s1, s2.a);
        int d2 = direction(s1, s2.b);
        int d3 = direction(s2, s1.a), d4 = direction(s2, s1.b);

        if(((d1 < 0 && d2 > 0) || (d1 > 0 && d2 < 0)) && ((d3 < 0 && d4 > 0) || (d3 > 0 && d4 < 0)))
            return true;

        else if(d1 == 0 && onSegment(s1,s2.a))
            return true;
        else if(d2 == 0 && onSegment(s1,s2.b))
            return true;
        else if(d3 == 0 && onSegment(s2, s1.a))
            return true;
        else if(d4 == 0 && onSegment(s2, s1.b))
            return true;

        return false;
    }


    public static void main(String [] args){


        Segment s1 = new Segment(new Segment.Point(-3,0), new Segment.Point(5,0));
        Segment s2 = new Segment(new Segment.Point(0,-4), new Segment.Point(0,4));

        if(solve(s1,s2)) System.out.println("They intersect");
        else System.out.println("They don't intersect");
    }

}
