package com.example.demo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Tomcio on 2017-08-20.
 */
public class RobotRequest {

    @Size(min = 2, max =2)
    private int[] roomSize;
    @Size(min = 2, max =2)
    private int[] coords;
    @NotNull
    private int[][] patches;
    @Pattern(regexp = "[NEWS]*")
    private String instructions;

    public RobotRequest(){}

    public RobotRequest(int[] roomSize, int[] coords, int[][] patches, String instructions) {
        this.roomSize = roomSize;
        this.coords = coords;
        this.patches = patches;
        this.instructions = instructions;
    }

    public int[] getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int[] roomSize) {
        this.roomSize = roomSize;
    }

    public int[] getCoords() {
        return coords;
    }

    public void setCoords(int[] coords) {
        this.coords = coords;
    }

    public int[][] getPatches() {
        return patches;
    }

    public void setPatches(int[][] patches) {
        this.patches = patches;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
