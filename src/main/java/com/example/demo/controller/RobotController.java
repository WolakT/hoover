package com.example.demo.controller;

import com.example.demo.dto.RobotRequest;
import com.example.demo.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Tomcio on 2017-08-20.
 */
@Controller
public class RobotController {
    private RobotService robotService;

    @Autowired
    public RobotController(RobotService robotService){
        this.robotService = robotService;
    }

    @RequestMapping(value = "/robot", method = RequestMethod.POST)
    public ResponseEntity<String> test(@RequestBody RobotRequest robotRequest){
        String something = robotService.doSomething(robotRequest);
        return new ResponseEntity<String>(something, HttpStatus.OK);
    }

}
