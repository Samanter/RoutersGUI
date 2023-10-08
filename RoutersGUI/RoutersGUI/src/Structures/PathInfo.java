package Structures;

import java.util.ArrayList;

public class PathInfo {
    private final int distance;
    private final ArrayList<Integer> path;

    public PathInfo(int distance, ArrayList<Integer> path) {
        this.distance = distance;
        this.path = path;
    }

    public int getDistance() {
        return distance;
    }

    public ArrayList<Integer> getPath() {
        return path;
    }
}