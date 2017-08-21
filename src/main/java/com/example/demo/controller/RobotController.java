package com.example.demo.controller;

import com.example.demo.dto.RobotRequest;
import com.example.demo.dto.RobotResponse;
import com.example.demo.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tomcio on 2017-08-20.
 */
@RestController
public class RobotController {
    private RobotService robotService;

    @Autowired
    public RobotController(RobotService robotService){
        this.robotService = robotService;
    }

    @RequestMapping(value = "/robot", method = RequestMethod.POST)
    public ResponseEntity<RobotResponse> test(@RequestBody RobotRequest robotRequest){
        RobotResponse robotResponse = robotService.doSomething(robotRequest);
        return new ResponseEntity<RobotResponse>(robotResponse, HttpStatus.OK);
    }

}
