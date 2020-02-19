package com.michael.hashcode.hashcode_2018;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;

public class FileManager {

    public Simulation simulation;
    public String inputFileName;
    public String outputFileName;


    public FileManager(String inputFileName){
        this.inputFileName = inputFileName;
        this.outputFileName = inputFileName.substring(0, inputFileName.indexOf(".")) + ".out";
        simulation = null;
    }


    public Simulation readFile(){

        Scanner scanner = null;
        FileReader reader = null;

        try{
            reader = new FileReader("src/com/michael/hashcode/hashcode_2018/resources/"+ inputFileName);
            scanner = new Scanner(reader);

            String[] info = scanner.nextLine().split(" ");
            int R = Integer.parseInt(info[0]);
            int C = Integer.parseInt(info[1]);
            int F = Integer.parseInt(info[2]);
            int N = Integer.parseInt(info[3]);
            int B = Integer.parseInt(info[4]);
            int T = Integer.parseInt(info[5]);

            simulation = new Simulation(R,C,F,N,B,T);

            int index = 0;
            while(scanner.hasNextLine()){
                String[] ride = scanner.nextLine().split(" ");
                Intersection start = new Intersection(Integer.parseInt(ride[0]),Integer.parseInt(ride[1]));
                Intersection finish = new Intersection(Integer.parseInt(ride[2]),Integer.parseInt(ride[3]));

                int earliestStart = Integer.parseInt(ride[4]);
                int earliestfinish = Integer.parseInt(ride[5]);

                Ride ride1 = new Ride(start,finish,earliestStart,earliestfinish);
                ride1.setIndex(index);

                simulation.rideList.addLast(ride1);
                index ++;
            }
            Collections.sort(simulation.rideList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        return simulation;
    }

    public void writeFile(){
        PrintWriter writer = null;

        try{
            writer = new PrintWriter("src/com/michael/hashcode/hashcode_2018/solution/"+ outputFileName);

            for(Car car : simulation.solution){
                writer.println(car);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            writer.flush();
            writer.close();
        }

    }
}
