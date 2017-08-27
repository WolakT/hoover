package com.example.demo.view;

import com.example.demo.dto.RobotRequest;
import com.example.demo.service.Robot;
import edu.princeton.cs.algs4.StdDraw;
import org.springframework.stereotype.Controller;

import java.awt.*;

/**
 * Created by Tomcio on 2017-08-27.
 */
@Controller
public class RobotView {

    private final static int N = 50;
    private final static int STEP_LENGTH = 5;

    public void drawRobotRequest(RobotRequest robotRequest, int[][] allLocations){
        drawRoom(robotRequest.getRoomSize());

        for (int i = 0; i < allLocations.length; i++) {
            if(i== allLocations.length-1){
                drawLocation(allLocations[i],Color.GREEN);
            } else {
                drawLocation(allLocations[i],Color.BLUE);
            }
        }
        drawLocation(robotRequest.getCoords(),Color.RED);


    }

    private void drawPatches(int[][] patches){
        for (int i = 0; i < patches.length; i++) {
            StdDraw.setPenColor(Color.black);
            StdDraw.line( 5+(STEP_LENGTH*patches[i][0]),5+(STEP_LENGTH*patches[i][1]),
                    10+(STEP_LENGTH*patches[i][0]), 10+(STEP_LENGTH*patches[i][1]));
            StdDraw.line( 10+(STEP_LENGTH*patches[i][0]),5+(STEP_LENGTH*patches[i][1]),
                    5+(STEP_LENGTH*patches[i][0]), 10+(STEP_LENGTH*patches[i][1]));

        }

    }


    public void drawRoom( int[] room){
        StdDraw.setXscale(0,N);
        StdDraw.setYscale(0,N);
        StdDraw.setPenRadius(0.008);
//        StdDraw.setCanvasSize(room[0]*STEP_LENGTH+50, room[1]*STEP_LENGTH+50);

        for (int i = 0; i <  room.length; i++) {
            for (int j = 0; j <= room[i]; j++) {
                if(i==0) {
                    //vertical
                    StdDraw.line( 5 + (5 * j), 5, 5 + (5 * j), room[1]*5 + STEP_LENGTH);
                } else{
                    //horizontal
                    StdDraw.line( 5, + 5 + (5 * j),  room[0]*5 + STEP_LENGTH,  5 + (5 * j));

                }

            }
        }
    }
    public void drawLocation(int[] coords, Color color) {
        StdDraw.setXscale(0,N);
        StdDraw.setYscale(0,N);
        StdDraw.setPenRadius(0.008);
        StdDraw.setPenColor(color);
        StdDraw.filledSquare(7.5+(STEP_LENGTH*coords[0]),7.5+(STEP_LENGTH*coords[1]),2.5);

    }

//    public static void main(String[] args) {
//        RobotView robotView = new RobotView();
//        int[] room = {5,5};
//        int[] coords = {1,2};
//        robotView.drawRoom(room);
//        robotView.drawLocation(coords, Color.BLUE);
//        int[][]patches = {
//                {1,1},
//                {1,2},
//        };
//        robotView.drawPatches(patches);
//    }


}
