package com.example.demo.controller;

import com.example.demo.dto.RobotRequest;
import com.example.demo.dto.RobotResponse;
import com.example.demo.repositiory.RobotRepository;
import com.example.demo.service.RobotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by RENT on 2017-08-22.
 */
public class RobotControllerTest {

    private RobotController robotController;
    private ObjectMapper objectMapper;
    private RobotRequest robotRequest;
    @Mock
    private RobotService robotService;

    @Mock
    private RobotRepository robotRepository;

    @Before
    public void setUP() {
        initMocks(this);
        objectMapper = new ObjectMapper();

        try {
            robotRequest = createTestRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldCallTheService() {
        RobotResponse robotResponse = new RobotResponse();
        robotResponse.setPatches(1);
        Mockito.when(robotService.doRequest(robotRequest)).thenReturn(robotResponse);
        robotController = new RobotController(robotService, robotRepository);
        robotController.processRequest(robotRequest);

        Mockito.verify(robotService,Mockito.times(1)).doRequest(robotRequest);

    }

    private RobotRequest createTestRequest() throws IOException {
        String requestAsJson = readAsString("src\\test\\resource\\request.json");

        return objectMapper.readValue(requestAsJson, RobotRequest.class);

    }

    private String readAsString(String path) throws IOException {
        return new Scanner(new File(path)).useDelimiter("\\Z")
                .next();
    }
}