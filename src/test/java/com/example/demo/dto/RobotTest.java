package com.example.demo.dto;

import com.example.demo.service.Robot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        int[] roomS = {5,5};
        testRequest.setRoomSize(roomS);
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
        Assert.assertArrayEquals(correctPos,resultPos);
    }

    @Test
    public void shouldReturnCorrectPositionWithDifferentPath(){
        testRequest.setInstructions("NENNSW");
        testRobot = new Robot(testRequest);
        int[] correctPos = {0,2};
        int[] resultPos = testRobot.cleanTheRoom();

        Assert.assertEquals(correctPos[1],resultPos[1]);
        Assert.assertEquals(correctPos[0],resultPos[0]);
        Assert.assertArrayEquals(correctPos,resultPos);
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

        int[] correctPos = {0,0};
        int result[] = testRobot.cleanTheRoom();

        Assert.assertArrayEquals(correctPos,result);
    }

}