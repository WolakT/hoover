package com.example.demo.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by RENT on 2017-08-21.
 */
public class Robot{
    private RobotRequest robotRequest;
    private int[] currentLocation;
    private Set<Point> setPatches = new HashSet<>();
    private int cleanedPatches;

    public int getCleanedPatches() {
        return cleanedPatches;
    }

    public Robot(RobotRequest robotRequest){
        this.robotRequest = robotRequest;
        currentLocation = robotRequest.getCoords();
        for (int i = 0; i < robotRequest.getPatches().length; i++) {
            int x = robotRequest.getPatches()[i][0];
            int y = robotRequest.getPatches()[i][1];
            setPatches.add(new Point(x,y));
        }

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

            break;
            case "S": currentLocation[1]--;
            break;
            case "E": currentLocation[0]++;
            break;
            case "W": currentLocation[0]--;

        }
        checkBoundries();
        return currentLocation;
    }

    private void checkBoundries(){
        int boundriesX = robotRequest.getRoomSize()[0];
        int boundriesY = robotRequest.getRoomSize()[1];
        if(currentLocation[0]>boundriesX){
            currentLocation[0] = boundriesX;
        } else if(currentLocation[1]>boundriesY){
            currentLocation[1] = boundriesY;
        } else if (currentLocation[0]<0){
            currentLocation[0]=0;
        }else if (currentLocation[1]<0) {
            currentLocation[1] = 0;


        }

    }
    private int convert(int row, int column) {
        // System.out.println(row*N+(column-N-1));
        return row * robotRequest.getRoomSize()[0] + (column - robotRequest.getRoomSize()[1] - 1);
    }
    private boolean isPatchCleaned(){
        int locationX = currentLocation[0];
        int locationY = currentLocation[1];
        Point locationPoint = new Point(locationX,locationY);
        if(setPatches.contains(locationPoint)){
            setPatches.remove(locationPoint);
            return true;
        } else {
            return false;
        }
    }
}
