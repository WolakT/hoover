package com.example.demo.controller;

import com.example.demo.dto.RobotRequest;
import com.example.demo.dto.RobotResponse;
import com.example.demo.repositiory.RequestResponse;
import com.example.demo.repositiory.RobotRepository;
import com.example.demo.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Tomcio on 2017-08-20.
 */
@RestController
public class RobotController {
    private RobotService robotService;

    @Autowired
    private RobotRepository robotRepository;

    @Autowired
    public RobotController(RobotService robotService){
        this.robotService = robotService;
    }

    @RequestMapping(value = "/robot", method = RequestMethod.POST)
    public ResponseEntity<RobotResponse> processRequest(@Valid @RequestBody RobotRequest robotRequest){
        RobotResponse robotResponse = robotService.doRequest(robotRequest);
        RequestResponse requestResponse = new RequestResponse(robotRequest, robotResponse);
        robotRepository.save(requestResponse);
        return new ResponseEntity<RobotResponse>(robotResponse, HttpStatus.OK);
    }

}
