package com.michael.hashcode.hashcode_2017;

import java.io.*;
import java.util.Scanner;

public class FileManager {

    private String source = null;
    private String destination = null;
    public Pizza p = null;

    public FileManager(String src , String destination){
        if(src != null || !src.equals(""))
            this.source = src;

        if(destination != null || !destination.equals(""))
            this.destination = destination;
    }


    public  Pizza readPizza(){
        FileReader reader;
        Scanner scanner = null;

        try{
            reader = new FileReader(new File(this.source));
            scanner = new Scanner(reader);
            String [] details = scanner.nextLine().split(" ");

            int R = Integer.parseInt(details[0]);
            int C = Integer.parseInt(details[1]);
            int L = Integer.parseInt(details[2]);
            int H = Integer.parseInt(details[3]);
            p = new Pizza(R,C,L,H);

            for(int i = 0; i < R ; i ++){
                if(scanner.hasNextLine()){
                    String row = scanner.nextLine();
                    for(int j = 0; j < C; j ++){
                        p.pizza[i][j] = row.charAt(j);
                        p.pizzaSum[i][j] = (row.charAt(j) == 'M')? 1 : 0;
                    }
                }
            }
            //Cumulative sum of Mushrooms for the sub-grids
            for(int i = 0; i < R; i ++){
                for(int j = 0; j < C; j ++){
                    p.pizzaSum[i][j] = p.pizzaSum[i][j] + ((i > 0) ? p.pizzaSum[i - 1][j] : 0)
                            +((j > 0) ? p.pizzaSum[i][j - 1] : 0)
                            -((i > 0 && j > 0) ? p.pizzaSum[i - 1][j - 1] : 0);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            scanner.close();
        }

        return p;

    }

    public void writePizza() throws IOException {
        FileWriter writer = null;
        try{
            writer = new FileWriter(this.destination);
            int k = p.solution.size();

            writer.write(k +"\n");

            for(Pizza.Slice s: p.solution){
                writer.write(s.toString()+"\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            writer.flush();
            writer.close();
        }
    }
}
