package com.example.demo.service;

import com.example.demo.dto.RobotRequest;
import com.example.demo.dto.RobotResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by RENT on 2017-08-22.
 */
public class RobotServiceTest {
    private RobotService robotService;
    private RobotRequest robotRequest;
    private ObjectMapper objectMapper;
    private RobotResponse expectedResponse;

    private Robot robot;


    @Before
    public void setUp(){
        robotService = new RobotService();
        objectMapper = new ObjectMapper();

        try {
            robotRequest = createTestRequest();
            expectedResponse = createTestResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldDoTheRequest(){
        Robot robot = new Robot(robotRequest);
        RobotResponse resultResponse = new RobotResponse();
        resultResponse.setCoords(robot.cleanTheRoom());
        resultResponse.setPatches(robot.getCleanedPatches());

        Assert.assertArrayEquals(expectedResponse.getCoords(),resultResponse.getCoords());
        Assert.assertEquals(expectedResponse.getPatches(), resultResponse.getPatches());
    }



    private RobotRequest createTestRequest() throws IOException {
        String requestAsJson = readAsString("src\\test\\resource\\request.json");

        return objectMapper.readValue(requestAsJson, RobotRequest.class);
    }
    private RobotResponse createTestResponse()throws IOException {
        String responseAsJson = readAsString("src\\test\\resource\\response.json");
        return objectMapper.readValue(responseAsJson, RobotResponse.class);
    }
    private String readAsString(String path) throws IOException {
        return new Scanner(new File(path)).useDelimiter("\\Z")
                .next();
    }
}