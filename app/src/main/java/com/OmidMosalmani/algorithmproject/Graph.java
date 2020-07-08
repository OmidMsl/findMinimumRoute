package com.OmidMosalmani.algorithmproject;

import java.util.*;

public class Graph {
    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency Lists

    // Constructor
    Graph(int v) {
        V=v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
    }


    ArrayList<Integer> ShortestDistance(int s, int dest) {
        // predecessor[i] array stores predecessor of
        // i and distance array stores distance of i
        // from s
        int [] pred=new int[V];

        boolean flag=false;
        /////////////////////
        // a queue to maintain queue of vertices whose
        // adjacency list is to be scanned as per normal
        // DFS algorithm
        Queue<Integer> queue = new LinkedList<>();

        // boolean array visited[] which stores the
        // information whether ith vertex is reached
        // at least once in the Breadth first search
        boolean[] visited = new boolean[V];

        // initially all vertices are unvisited
        // so v[i] for all i is false
        // and as no path is yet constructed
        // dist[i] for all i set to infinity
        for (int i = 0; i < V; i++) {
            visited[i] = false;
            pred[i] = -1;
        }

        // now source is first to be visited and
        // distance from source to itself should be 0
        visited[s] = true;
        queue.add(s);

        // standard BFS algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj[u].size(); i++) {
                if (!visited[adj[u].get(i)]) {
                    visited[adj[u].get(i)] = true;
                    pred[adj[u].get(i)] = u;
                    queue.add(adj[u].get(i));

                    // We stop BFS when we find
                    // destination.
                    if (adj[u].get(i) == dest){
                        flag=true;
                    }
                }
            }
        }

        /////////////////////
        if (!flag)
        {
            //cout << "Given source and destination are not connected";
            return null;
        }

        // vector path stores the shortest path
        ArrayList<Integer> path = new ArrayList<>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }

        return path;
    }

}
