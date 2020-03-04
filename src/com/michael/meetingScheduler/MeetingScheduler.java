package com.michael.meetingScheduler;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Kush
 */
public class MeetingScheduler {

    /**
     * This class represent the schedule of a single person for a day
     */
    public static class Schedule{
        public ArrayList<TimeInterval> timeIntervalsOccupied;
        public TimeInterval bounds;

        public Schedule(String start, String end){
            bounds = new TimeInterval(start, end);
            timeIntervalsOccupied = new ArrayList<>();
        }

        public Schedule(String start, String end , String [] ... occupied){
            this(start,end);

            for(int i = 0; i < occupied.length; i ++){

                this.timeIntervalsOccupied.add(new TimeInterval(occupied[i][0], occupied[i][1]));
            }
            Collections.sort(timeIntervalsOccupied);
        }
    }

    /**
     * This class represent a single interval of time with start being less than end
     */
    public static class TimeInterval implements Comparable{
        public LocalTime start;
        public LocalTime end;
        public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        public TimeInterval(LocalTime start, LocalTime end){
            if(start.compareTo(end) < 0){
                this.start = start;
                this.end = end;
            }else{
                System.out.println("Error creating TimeInterval");
            }

        }

        public TimeInterval(String start, String end){

            this(parseLocalTime(start),parseLocalTime(end));
        }


        public static LocalTime parseLocalTime(String str){

            return LocalTime.parse(str,TimeInterval.formatter);
        }


        @Override
        public String toString(){

            return "{"+this.start+","+this.end+"}";
        }

        /**
         * Sort according to the start time
         */
        @Override
        public int compareTo(Object o) {
            return this.start.compareTo(((TimeInterval)o).start);
        }
    }





    public ArrayList<TimeInterval> mergedTimeInvervals;
    public TimeInterval globalBounds;
    public ArrayList<TimeInterval> solution;


    public MeetingScheduler(){
        mergedTimeInvervals = new ArrayList<>();
        solution = new ArrayList<>();
        globalBounds = null;
    }


    /**
     * This function will initialize the mergedTimeIntervals. If there is any time overlap the time will be merged int one.
     *      e.g  {{"09:00", "10:30"} ,{"10:00","11:00"}, ... } this 2 entries can be considered as one entry i.e {{"09:00","11:00"}, ... }
     *          since no meeting can be held in between them.
     *
     * At the end we will have a list will all the time Intervals when the users are occupied.
     * @param schedules
     */
    public void setMergedTimeIntervals(Schedule ... schedules) {



        for(int i = 0; i < schedules.length; i ++){
            mergedTimeInvervals.addAll(schedules[i].timeIntervalsOccupied);
        }
        // Sort the mergedTimeIntervals to make it easy to combine the overlapping times
        Collections.sort(mergedTimeInvervals);


        print("Before Merging >>>> ",mergedTimeInvervals);
        int j = 0;
        while(true){
            ArrayList<TimeInterval> merged = new ArrayList<>();
            int i = 0;
            int count = 0;

            while(i < mergedTimeInvervals.size()){

                //compare the end time of i and the start time of i + 1 to see if they can be combined

                if(i < mergedTimeInvervals.size() - 1 && mergedTimeInvervals.get(i).end.compareTo(mergedTimeInvervals.get(i + 1).start) >= 0){
                    if(mergedTimeInvervals.get(i).end.compareTo(mergedTimeInvervals.get(i + 1).end) > 0){
                        merged.add(mergedTimeInvervals.get(i));
                    }else{
                        merged.add(new TimeInterval(mergedTimeInvervals.get(i).start, mergedTimeInvervals.get(i + 1).end));
                    }

                    i += 2;
                }else{

                    count ++;
                    merged.add(mergedTimeInvervals.get(i));
                    i += 1;
                }

            }


            print("Step "+ j +" >>>> ",merged);
            if(count == merged.size()  ){
                break;
            }else{
                mergedTimeInvervals = merged;
                //print("Step "+j +" >>>> ",merged);
            }

            j ++;
        }
    }

    /**
     * Setting the globalBounds by taking the largest start time and smallest end time for all the bounds of schedules provided.
     */
    public void setGlobalBounds(Schedule ... schedules){
        LocalTime start = schedules[0].bounds.start;
        LocalTime end = schedules[0].bounds.end;

        for(int i = 0; i < schedules.length; i ++){

            if(start.compareTo(schedules[i].bounds.start) < 0){
                start = schedules[i].bounds.start;
            }

            if(end.compareTo(schedules[i].bounds.end) > 0){
                end = schedules[i].bounds.end;
            }
        }
        globalBounds = new TimeInterval(start,end);
    }

    /**
     * This will function will find the final solution. i.e the time Intervals when the all the users will be available for a meeting
     * putting into consideration the global bounds;
     */
    public void schedule(Schedule ... schedules){
        setGlobalBounds(schedules);
        setMergedTimeIntervals(schedules);
        solution = new ArrayList<>();

        //check to see if there is any meeting that can be held from global lower bound to 0.start

        if(globalBounds.start.compareTo(mergedTimeInvervals.get(0).start) < 0){
            solution.add(new TimeInterval(globalBounds.start, mergedTimeInvervals.get(0).start));
        }

        // Check all the available time and within the bounds
        for(int i = 0; i < mergedTimeInvervals.size() - 1; i ++){
            LocalTime start = mergedTimeInvervals.get(i).end;
            LocalTime end = mergedTimeInvervals.get(i + 1).start;

            if(start.compareTo(globalBounds.start) < 0){
                if(end.compareTo(globalBounds.start) < 0)
                    continue;
                start = globalBounds.start;

            }


            if(end.compareTo(globalBounds.end) > 0){
                if(start.compareTo(globalBounds.end) > 0)
                    continue;
                end = globalBounds.end;
            }

            solution.add(new TimeInterval(start,end));
        }

        // Check to see if there is any meeting that can be held from size().end to the global upper bound
        if(globalBounds.end.compareTo(mergedTimeInvervals.get(mergedTimeInvervals.size() - 1).end) > 0){
            solution.add(new TimeInterval(mergedTimeInvervals.get(mergedTimeInvervals.size() - 1).end,globalBounds.end));
        }

    }


    public void print(String message, List<TimeInterval> list){

        System.out.print(message);
        System.out.print("{ ");

        for(TimeInterval timeInterval: list){
            System.out.print( timeInterval + " ");
        }
        System.out.println("}");
    }




    public static void main(String [] args){
        String[][] stdSchedule = {{"06:00","07:00"},{"07:30","09:30"},{"09:00","09:30"},{"09:30","10:00"},{"12:00","13:00"},{"16:00","18:00"}};


        String [][] tSchedule = {{"10:00","11:30"},{"12:30","14:30"},{"14:30","15:00"},{"16:00","17:00"}};


        Schedule student = new Schedule("07:00","20:00",stdSchedule);

        Schedule teacher = new Schedule("06:00","18:30",tSchedule);

        MeetingScheduler scheduler = new MeetingScheduler();
        scheduler.schedule(student,teacher);
        scheduler.print("solution >>>>> ",scheduler.solution);
        System.out.println("With bounds: "+scheduler.globalBounds);
    }





}
