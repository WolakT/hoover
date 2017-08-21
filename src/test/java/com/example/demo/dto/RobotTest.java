package com.example.demo.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by RENT on 2017-08-21.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RobotTest {
    Robot testRobot;
    RobotRequest testRequest;


    @Before
    public void setUp() {
        int[] coords = {0,0};
        testRequest = new RobotRequest();
        testRequest.setCoords(coords);

        int[][] patches = new int[][]{
                {1,1},
                {2,2}
        };
        testRequest.setPatches(patches);

    }

    @Test
    public void shouldReturnCorrectPosition(){
        testRequest.setInstructions("N");
        testRobot = new Robot(testRequest);
        int[] correctPos = {0,1};
        int[] resultPos = testRobot.cleanTheRoom();

        Assert.assertEquals(correctPos[1],resultPos[1]);
        Assert.assertEquals(correctPos[0],resultPos[0]);
    }

    @Test
    public void shouldReturnCleanedPatches(){
        testRequest.setInstructions("NE");
        testRobot = new Robot(testRequest);
        int expectedResult = 1;
        testRobot.cleanTheRoom();
        int result = testRobot.getCleanedPatches();

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void shouldHitTheWall(){
        testRequest.setInstructions("S");
        testRobot = new Robot(testRequest);

        int[] correctPos = {0,1};
        int result[] = testRobot.cleanTheRoom();

        Assert.assertEquals(correctPos[1],result[1]);
        Assert.assertEquals(correctPos[0],result[0]);
    }

}