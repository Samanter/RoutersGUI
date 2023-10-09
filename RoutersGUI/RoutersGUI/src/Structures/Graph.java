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
            adj.add(new ArrayList());
        }
    }
 
    public void addEdge(int u, int v, int w) {
        adj.get(u).add(new iPair(v, w));    
        adj.get(v).add(new iPair(u, w));
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
    
    public ArrayList<PathInfo> shortestPaths(int src) {
        ArrayList<PathInfo> paths = new ArrayList();
        
        for (int dest = 0; dest < V; dest++) {
            if (src == dest) paths.add(null);
            else paths.add(shortestPath(src, dest));
        }

        return paths;
    }
}