package phase2cpcs324;

import java.util.Comparator;
import javafx.util.Pair;
import java.util.LinkedList;
import java.util.PriorityQueue;



public class PrimQueue {

    static class Edg {

        int src;
        int dest;
        int wt;

        public Edg(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }

    static class Result {

        int parent;
        int wt;
    }

    static class primQueueGraph {

        int vertex;
        LinkedList<Edg>[] adjList;

        primQueueGraph(Graph graph) {
            this.vertex = graph.getAllVertex().size();
            adjList = new LinkedList[graph.getAllVertex().size()];
//initialize adjacency lists for all the vertex
            for (int i = 0; i < vertex; i++) {
                adjList[i] = new LinkedList<>();
            }

        }

        public void addEdge(int src, int dest, int wt) {

            Edg edge = new Edg(src, dest, wt);
            adjList[src].addFirst(edge);
            edge = new Edg(dest, src, wt);
            adjList[dest].addFirst(edge); //for undirected graph
        }

        public void primMST() {
            
            long start = System.nanoTime();

            boolean[] mst = new boolean[vertex];
            Result[] resultSet = new Result[vertex];
            int[] key = new int[vertex]; //keys used to store the key to know whether priority queue update is required
//Initialize all the keys to infinity and
//initialize resultSet for all the vertex
            for (int i = 0; i < vertex; i++) {
                key[i] = Integer.MAX_VALUE;
                resultSet[i] = new Result();
            }
//Initialize priority queue
//override the comparator to do the sorting based keys
            PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(vertex, new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
//sort using key values
                    int key1 = p1.getKey();
                    int key2 = p2.getKey();
                    return key1 - key2;
                }
            });
//create the pair for for the first index, 0 key 0 index
            key[0] = 0;
            Pair<Integer, Integer> p0 = new Pair<>(key[0], 0);
//add it to pq
            pq.offer(p0);
            resultSet[0] = new Result();
            resultSet[0].parent = -1;
//while priority queue is not empty
            while (!pq.isEmpty()) {
//extract the min
                Pair<Integer, Integer> extPair = pq.poll();
//extracted vertex
                int extVert = extPair.getValue();
                mst[extVert] = true;
//iterate through all the adjacent vertex and update the keys
                LinkedList<Edg> list = adjList[extVert];
                for (int i = 0; i < list.size(); i++) {
                    Edg edge = list.get(i);
//only if edge dest is not present in mst
                    if (mst[edge.dest] == false) {
                        int dest = edge.dest;
                        int newKey = edge.wt;
//check if updated key < existing key, if yes, update if
                        if (key[dest] > newKey) {
//add it to the priority queue
                            Pair<Integer, Integer> p = new Pair<>(newKey, dest);
                            pq.offer(p);
//update the resultSet for dest vertex
                            resultSet[dest].parent = extVert;
                            resultSet[dest].wt = newKey;
//update the key[]
                            key[dest] = newKey;
                        }
                    }
                }
            }

            long end = System.nanoTime();

//print mst
            printMSTtime(resultSet, end, start);
        }

        public void printMSTtime(Result[] resultSet, long end, long start) {
            double t1 = (double) start;
            double t2 = (double) end;
            double time = (t2 - t1) / 1000000;
            
            System.out.println("\n    >> Time taken to implement Prim's using priority queue:  " + time + " ms");
        }
    }
}

