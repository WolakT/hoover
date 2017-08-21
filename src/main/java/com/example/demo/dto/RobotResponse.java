package com.example.demo.dto;

/**
 * Created by RENT on 2017-08-21.
 */
public class RobotResponse {
    private int[] coords;
    private int patches;

    public int[] getCoords() {
        return coords;
    }

    public void setCoords(int[] coords) {
        this.coords = coords;
    }

    public int getPatches() {
        return patches;
    }

    public void setPatches(int patches) {
        this.patches = patches;
    }
}
