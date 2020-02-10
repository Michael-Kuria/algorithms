package com.michael.hashcode.hashcode_2017;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The problem description can be found in resources/TaskDescription.pdf
 */
public class Pizza {
    public char[][] pizza;
    public int[][] pizzaSum;
    private int R,C,L,H;
    public List<Slice> solution;
    private int totalnumberOfCellsOnSlices ;


    public Pizza(int R,int C, int L, int H){
        this.C = C;
        this.H = H;
        this.L = L;
        this.R = R;
        totalnumberOfCellsOnSlices = 0;
        solution = new ArrayList<>();

        pizza = new char[this.R][this.C];
        pizzaSum = new int[this.R][this.C];
    }

    public int solveCol(int r1, int r2, LinkedList<Slice> s){
        int c1 = 0, sum = 0;
        while(c1 < C){
            int c2 = c1;
            boolean isPreviousOk = false;
            while(c2 < C){
                Slice slice = new Slice(r1,r2,c1,c2);
                if(slice.isValid())
                    isPreviousOk = true;
                else if(isPreviousOk){
                    s.add(new Slice(r1,r2,c1,c2-1));
                    sum += s.getLast().size();
                    break;

                }
                c2 ++;
            }

            if(!isPreviousOk)
                c1 ++;
            else
                c1 = c2;

        }

        // The total number of cells in the available slices
        return sum;

    }

    public void solve(){
        int r1 = 0;
        while(r1 < R){
            int r2 = r1 ,best = -1;
            List<Slice> at = new LinkedList<>();
            while(r2 < R){
                LinkedList<Slice> v = new LinkedList<>();
                int res = solveCol(r1,r2,v);

                if(res < best){
                    r2 --;
                    break;
                }
                best = res;
                at = v;
                r2++;
            }
            for(Slice s: at){
                solution.add(s);
                totalnumberOfCellsOnSlices += s.size();
            }
            r1 = r2 + 1;
        }


    }

    public static void main(String [] args) throws IOException {

        String[] fileNames = {"example.in","small.in","medium.in", "big.in" };
        int cells = 0;

        for(int i = 0; i < fileNames.length; i ++){
            String fileName = fileNames[i];
            FileManager fileManager = new FileManager("src/com/michael/hashcode/hashcode_2017/resources/" + fileName,
                    "src/com/michael/hashcode/hashcode_2017/solutions/" +fileName.substring(0,fileName.indexOf("."))+".out" );

            Pizza pizza = fileManager.readPizza();
            pizza.solve();

            fileManager.writePizza();
            cells += pizza.totalnumberOfCellsOnSlices;

            System.out.println("File: " + fileName+ " completed");


        }

        System.out.println("Expected score : === ====  > "+ cells);
    }







































    public class Slice{

        public int r1,r2,c1,c2;

        public Slice(int r1,int r2, int c1,int c2){
            this.r1 = r1;
            this.r2 = r2;
            this.c1 = c1;
            this.c2 = c2;
        }

        public int size(){
            return ((r2 - r1) + 1) * ((c2 - c1) + 1);
        }

        //make use of the cumulative sum array
        public int getNumberOfM(){
            return (pizzaSum[r2][c2]
                    - (r1 > 0 ? pizzaSum[r1-1][c2] : 0 )
                    - (c1 > 0 ? pizzaSum[r2][c1 -1] : 0)
                    + (r1 > 0 && c1 > 0 ? pizzaSum[r1 - 1][c1 -1] : 0));

        }

        public boolean isValid(){
            int size = this.size();
            int mushrooms = this.getNumberOfM();

            if( size > H )
                return false;
            if(mushrooms < L || (size - mushrooms) < L )
                return false;

            return true;

        }

        @Override
        public String toString(){
            return r1 + " " + c1 + " " + r2 + " " + c2;
        }
    }


}
