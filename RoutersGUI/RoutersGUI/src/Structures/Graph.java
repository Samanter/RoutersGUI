package Structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {
    private final int V;
    private final List<List<iPair>> adj;
 
    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>();
        
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }
 
    public void addEdge(int u, int v, int w) {
        adj.get(u).add(new iPair(v, w));
        adj.get(v).add(new iPair(u, w));
    }
    
    public void blockEdge(int u, int v) {
        for (iPair edge : adj.get(u)) {
            if (edge.first == v) {
                adj.get(u).remove(edge);
                break;
            }
        }

        for (iPair edge : adj.get(v)) {
            if (edge.first == u) {
                adj.get(v).remove(edge);
                break;
            }
        }
    }
 
    public PathInfo shortestPath(int src, int dest) {
        PriorityQueue<iPair> pq = new PriorityQueue<>(V, Comparator.comparingInt(o -> o.first));
        int[] dist = new int[V];
        int[] parent = new int[V];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        pq.add(new iPair(0, src));
        
        dist[src] = 0;

        while (!pq.isEmpty()) {
            int u = pq.poll().second;
 
            for (iPair v : adj.get(u)) {
                if (dist[v.first] > dist[u] + v.second) {
                    dist[v.first] = dist[u] + v.second;
                    parent[v.first] = u;
                    pq.add(new iPair(dist[v.first], v.first));
                }
            }
        }

        ArrayList<Integer> path = new ArrayList();
        int current = dest;
        
        while (current != -1) {
            path.add(current);
            current = parent[current];
        }
        
        Collections.reverse(path);

        return new PathInfo(dist[dest], path);
    }
    
    public ArrayList<PathInfo> findThreeShortestPaths(int src, int dest) {
        ArrayList<PathInfo> shortestPaths = new ArrayList();
        for (int i = 0; i < 3; i++) {
            PathInfo shortestPath = shortestPath(src, dest);
            
            if (shortestPath.getDistance() == Integer.MAX_VALUE) {
                break;
            }
            
            shortestPaths.add(shortestPath);
            ArrayList<Integer> path = shortestPath.getPath();
            
            for (int j = 0; j < path.size() - 1; j++) {
                int u = path.get(j);
                int v = path.get(j + 1);
                
                blockEdge(u, v);
                blockEdge(v, u);
            }
        }
        
        return shortestPaths;
    }
}