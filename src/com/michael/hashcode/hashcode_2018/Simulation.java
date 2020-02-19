package com.michael.hashcode.hashcode_2018;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Contains all the details that are necessary to run the application
 * i.e Rows, Columns, Rides, Bonus, simulation-steps, fleet-number, the number of rides
 *
 */
public class Simulation {

    public int R, C, F, N, B, T;
    public LinkedList<Car> solution;
    public LinkedList<Ride> rideList;
    public int score;
    public int bonus = 0;

    public Simulation(int r, int c, int f, int n, int b, int t){
        R = r;
        C = c;
        F = f;// number of vehicles
        N = n;// number of rides
        B = b;// bonus
        T = t;// number of simulation steps

        solution = new LinkedList<>();
        rideList = new LinkedList<>();
    }

    public int isRideValid(Car car, Ride ride){
        int score = 0;
        int tempStep = car.getStep() + car.getCurrentPosition().getDistanceBtnIntersections(ride.getStart());
        int waitingTime = 0;

        if(tempStep > T){
            return -1;
        }

        if(tempStep < ride.getEarliestStart() ){
            waitingTime = ride.getEarliestStart() - tempStep;
            tempStep += waitingTime;
        }

        if(tempStep == ride.getEarliestStart()){
            score += (B + ride.getDistance());
            return score;

        }else if(tempStep > ride.getEarliestStart()){
            if((tempStep + ride.getDistance()) > ride.getLatestFinish()){
                return  -1;
            }

            return 0;

        }
        return - 1;

    }

    public void updateCars( Ride ride){
        for(int i = 0 ; i < solution.size(); i ++){
            Car car = solution.get(i);
            car.setSortingDistance(car.getCurrentPosition().getDistanceBtnIntersections(ride.getStart()));
            solution.set(i,car);
        }
    }

    public void initSimulation(){

        for(int i = 0 ; i < F ; i ++){
            Ride ride = rideList.get(i);
            Car car = new Car();

            int k = isRideValid(car, ride);
            int waitingTime = 0;
            int rideDist = ride.getDistance();
            int travelDist = car.getCurrentPosition().getDistanceBtnIntersections(ride.getStart());

            if(k > 0){
                bonus ++;
                score += k;
                waitingTime = ride.getEarliestStart();
            }else if(k == 0){
                score += rideDist;
            }

            car.addLast(ride.getIndex());
            car.setStep(travelDist + rideDist + waitingTime);
            car.setCurrentPosition(ride.getFinish());
        }

    }

    public void solve(){
        initSimulation();

        for(int i = F ; i < N; i ++ ){
            Ride ride = rideList.get(i);
            updateCars(ride);
            Collections.sort(solution);

            int isOk = -1;
            int waitingTime = 0;
            int j = 0;
            boolean bonus = false;
            for(Car car : solution){
                int k = isRideValid(car,ride);
                if(k > 0){
                    score += k;
                    car.addLast(ride.getIndex());
                    int tempStep = car.getStep() + car.getSortingDistance();
                    if(tempStep < ride.getEarliestStart()){
                        waitingTime = ride.getEarliestStart() - tempStep;
                    }
                    car.setStep(car.getStep() + car.getSortingDistance() + waitingTime + ride.getDistance());
                    car.setCurrentPosition(ride.getFinish());
                    this.bonus ++;
                    bonus = true;
                    break;
                }else if( k == 0 && isOk == -1){
                    isOk = j;
                }
                j ++;
            }

            if(isOk != -1 && !bonus){
                Car car = solution.get(isOk);

                score += ride.getDistance();
                car.addLast(ride.getIndex());
                car.setStep(car.getStep() + car.getSortingDistance() + ride.getDistance());
                car.setCurrentPosition(ride.getFinish());
            }

        }
    }

    public static void main(String [] args){
        String [] fileNames = {"a_example.in", "b_should_be_easy.in" ,"e_high_bonus.in","c_no_hurry.in","d_metropolis.in"};

        int totalScore = 0;

        for(int i = 0; i < fileNames.length; i ++){
            String name = fileNames[i];
            System.out.println("Solving ......... >>>>>>>>> " + name);
            FileManager fileManager = new FileManager(name);

            Simulation simulation = fileManager.readFile();

            simulation.solve();

            System.out.println("Score : " + simulation.score +" bonus " + simulation.bonus + " bonus total " + (simulation.bonus  * simulation.B));

            totalScore += simulation.score;
        }

        System.out.println("Total score : "+ totalScore);
    }
}
