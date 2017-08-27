package com.example.demo.view;

import com.example.demo.dto.RobotRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by Tomcio on 2017-08-27.
 */
public class RobotViewTest {

    private ObjectMapper objectMapper;
    private RobotView robotView;
    private RobotRequest exampleRequest;

    @Before
    public void setUp(){
        objectMapper = new ObjectMapper();
        robotView = new RobotView();
        try {
            exampleRequest = createTestRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldDrawAMatrixWithRoomSize(){
        int[][] locations = {
                {1,2},
                {1,3},
                {3,3}
        };
        robotView.drawRobotRequest(exampleRequest,locations);

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