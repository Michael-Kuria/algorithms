package com.michael.meetingScheduler;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MeetingScheduler {


    public ArrayList<TimeInterval> mergedTimeInvervals;
    public TimeInterval globalBounds;
    public ArrayList<TimeInterval> solution;


    public MeetingScheduler(){
        mergedTimeInvervals = new ArrayList<>();
        solution = new ArrayList<>();
        globalBounds = null;
    }


    public void setMergedTimeIntervals(Schedule ... schedules) {

        //return mergedTimeInvervals;
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
     * This class represent the schedule of a single person for a day
     */
    public class Schedule{
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
        }
    }

    /**
     * This class represent a single interval of time with start being less than end
     */
    public static class TimeInterval{
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

    }
}
