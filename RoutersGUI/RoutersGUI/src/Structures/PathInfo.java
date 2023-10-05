package Structures;

import java.util.List;

public class PathInfo {
        int distance;
        List<Integer> path;

        public PathInfo(int distance, List<Integer> path) {
            this.distance = distance;
            this.path = path;
        }

        public int getDistance() {
            return distance;
        }

        public List<Integer> getPath() {
            return path;
        }
    }