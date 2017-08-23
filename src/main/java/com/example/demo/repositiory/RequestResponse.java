package com.example.demo.repositiory;

import com.example.demo.dto.RobotRequest;
import com.example.demo.dto.RobotResponse;
import com.example.demo.service.Robot;
import org.springframework.data.annotation.Id;

/**
 * Created by RENT on 2017-08-23.
 */
public class RequestResponse {
    private RobotRequest robotRequest;
    private RobotResponse robotResponse;

    @Id
    private String id;

    public RequestResponse(){

    }

    public RequestResponse(RobotRequest robotRequest, RobotResponse robotResponse) {
        this.robotRequest = robotRequest;
        this.robotResponse = robotResponse;
    }

    public RobotResponse getRobotResponse() {
        return robotResponse;
    }

    public void setRobotResponse(RobotResponse robotResponse) {
        this.robotResponse = robotResponse;
    }

    public RobotRequest getRobotRequest() {
        return robotRequest;
    }

    public void setRobotRequest(RobotRequest robotRequest) {
        this.robotRequest = robotRequest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
