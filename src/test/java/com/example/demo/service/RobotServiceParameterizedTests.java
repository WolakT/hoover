package com.example.demo.service;

import com.example.demo.dto.RobotRequest;
import com.example.demo.dto.RobotResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by RENT on 2017-08-23.
 */
@RunWith(JUnitParamsRunner.class)
public class RobotServiceParameterizedTests {
    private List<List<Object>> testsList;
    private ObjectMapper objectMapper;

    @Before
    public void setUp(){
        objectMapper = new ObjectMapper();
    }

    private Object[][] dataForTesting(){
        testsList = getListOfFiles();
        Object[][] testData = new Object[testsList.size()][2];

        for (int i = 0; i < testsList.size(); i++) {
            testData[i][0] = testsList.get(i).get(0);
            testData[i][1] = testsList.get(i).get(1);
        }

       return testData;
    }
    @Test
    @Parameters(method = "dataForTesting")
    public void shouldDoTheRequest(Object testRequest, Object testResponse){
        RobotResponse expectedResponse=null;
        RobotRequest robotRequest = null;
        try {
            expectedResponse = createTestResponse((File) testResponse);
            robotRequest = createTestRequest((File) testRequest);
        } catch (IOException e){

        }
        Robot robot = new Robot(robotRequest);
        RobotResponse resultResponse = new RobotResponse();
        resultResponse.setCoords(robot.cleanTheRoom());
        resultResponse.setPatches(robot.getCleanedPatches());

        Assert.assertArrayEquals(expectedResponse.getCoords(),resultResponse.getCoords());
        Assert.assertEquals(expectedResponse.getPatches(), resultResponse.getPatches());
    }

    private static List<List<Object>> getListOfFiles(){
        List<List<Object>> listJsons = new ArrayList<>();
        File f = new File("src\\test\\resource\\");
        File[] files = f.listFiles();
        for (int i = 0;i<files.length;i++ ) {
            System.out.println(files[i]);
            String requestString = files[i].getName();
            if(requestString.contains("Request.json")) {
                List<Object> innerList = new ArrayList<>();
                innerList.add(files[i]);
                requestString = requestString.replace("Request.json", "");
                for (int j = i+1; j < files.length; j++) {
                    String responseString = files[j].getName();
                    if(responseString.contains(requestString + "Response.json")){
                        innerList.add(files[j]);
                        listJsons.add(innerList);
                    }
                }
            }


        }
        return listJsons;

    }
    private RobotRequest createTestRequest(File file) throws IOException {
        String requestAsJson = readAsString(file.getPath());

        return objectMapper.readValue(requestAsJson, RobotRequest.class);
    }
    private RobotResponse createTestResponse(File file)throws IOException {
        String responseAsJson = readAsString(file.getPath());
        return objectMapper.readValue(responseAsJson, RobotResponse.class);
    }
    private String readAsString(String path) throws IOException {
        return new Scanner(new File(path)).useDelimiter("\\Z")
                .next();
    }
}
