package com.example.demo.dto;

import java.util.List;

/**
 * Created by RENT on 2017-08-21.
 */
public class Robot{
    private RobotRequest robotRequest;
    private int[] currentLocation;
    private Percolation perc;
    private int cleanedPatches;

    public int getCleanedPatches() {
        return cleanedPatches;
    }

    public Robot(RobotRequest robotRequest){
        this.robotRequest = robotRequest;
        currentLocation = robotRequest.getCoords();
        perc = new Percolation(robotRequest.getRoomSize()[0]);
        perc.open(robotRequest.getCoords()[0],robotRequest.getCoords()[1]);

    }

    public int[] cleanTheRoom(){
        String[] directions = robotRequest.getInstructions().split("");
        for (int i = 0; i < directions.length; i++) {
            makeStep(directions[i]);
            if(isPatchCleaned()) cleanedPatches++;
        }
        return currentLocation;
    }

    private int[] makeStep(String direction){
        switch (direction){

            case "N": currentLocation[1]++;
            perc.open();
            break;
            case "S": currentLocation[1]--;
            break;
            case "E": currentLocation[0]++;
            break;
            case "W": currentLocation[0]--;

        }
        return currentLocation;
    }

    private int convert(int row, int column) {
        // System.out.println(row*N+(column-N-1));
        return row * robotRequest.getRoomSize()[0] + (column - robotRequest.getRoomSize()[1] - 1);
    }
    private boolean isPatchCleaned(){
        for (int i = 0; i < robotRequest.getPatches().length; i++) {

            if(currentLocation[0] == robotRequest.getPatches()[i][0] &&
                    currentLocation[1] == robotRequest.getPatches()[i][1]){
                return true;
            }

        }
        return false;
    }
}
