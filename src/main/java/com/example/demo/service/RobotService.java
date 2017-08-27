package com.example.demo.service;

import com.example.demo.dto.RobotRequest;
import com.example.demo.dto.RobotResponse;
import com.example.demo.view.RobotView;
import org.springframework.stereotype.Service;

/**
 * Created by Tomcio on 2017-08-20.
 */

@Service
public class RobotService {

    public RobotResponse doRequest(RobotRequest robotRequest){
        RobotResponse robotResponse = new RobotResponse();
        Robot robot = new Robot(robotRequest);
        int[] coords = robot.cleanTheRoom();
        robotResponse.setPatches(robot.getCleanedPatches());
        robotResponse.setCoords(coords);
//        RobotView robotView = new RobotView();
//        robotView.drawRobotRequest(robotRequest, robot.getAllLocations());
        return robotResponse;
    }
}
