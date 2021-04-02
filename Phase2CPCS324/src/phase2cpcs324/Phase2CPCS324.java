package phase2cpcs324;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Phase2CPCS324 {

    public static void main(String args[]) {

        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");     // for avoiding "java.lang.IllegalArgumentException: Comparison method violates its general contract" error.

        Scanner input = new Scanner(System.in);
        System.out.println("---------------------------- Welcome ----------------------------"
                + "\n\n --------------------------------------------"
                + "\n  >> 1:  1000 nodes and 10,000 edges"
                + "\n  >> 2:  1000 nodes and 15,000 edges"
                + "\n  >> 3:  1000 nodes and 25,000 edges"
                + "\n  >> 4:  5000 nodes and 15,000 edges"
                + "\n  >> 5:  5000 nodes and 25,000 edges"
                + "\n  >> 6:  10,000 nodes and 15,000 edges"
                + "\n  >> 7:  10,000 nodes and 25,000 edges"
                + "\n  >> 8:  20,000 nodes and 200,000 edges"
                + "\n  >> 9:  20,000 nodes and 300,000 edges"
                + "\n  >> 10: 50,000 nodes and 1,000,000 edges"
                + "\n --------------------------------------------"
                + "\n\nPlease enter your choice: ");

        int choice = input.nextInt();
        // initailizing graphs.
        Graph<Integer> graphPrim = new Graph<>(false);
        Graph<Integer> graphKruskal = new Graph<>(false);
        Graph<Integer> graphPrimQueue = new Graph<>(false);

        switch (choice) {
            case 1:
                graphPrim = make_graph(1000, 10000);
                break;
            case 2:
                graphPrim = make_graph(1000, 15000);
                break;
            case 3:
                graphPrim = make_graph(1000, 25000);
                break;
            case 4:
                graphPrim = make_graph(5000, 15000);
                break;
            case 5:
                graphPrim = make_graph(5000, 25000);
                break;
            case 6:
                graphPrim = make_graph(10000, 15000);
                break;
            case 7:
                graphPrim = make_graph(10000, 25000);
                break;
            case 8:
                graphPrim = make_graph(20000, 200000);
                break;
            case 9:
                graphPrim = make_graph(20000, 300000);
                break;
            case 10:
                graphPrim = make_graph(50000, 1000000);
                break;
        }

        // the three graphs of (graphPrim, graphKruskal, graphPrimQueue) have the same graph (edges, and vertices),
        graphKruskal = graphPrim;
        graphPrimQueue = graphPrim;
        

        // --------------------------------------- PRIMS USING MIN-HEAP -----------------------------------------
        System.out.println("\n\n\n--------------------------------------- PRIMS USING MIN-HEAP -----------------------------------------\n");
        PrimMST prims = new PrimMST();

        long start = System.nanoTime();
        Collection<Edge<Integer>> edges = prims.primMST(graphPrim);
        long end = System.nanoTime();

        double t1 = (double) start;
        double t2 = (double) end;
        double time = (t2 - t1) / 1000000;

        System.out.println("\n    >> Time taken to implement Prim's using min-heap:  " + time + " ms");

// --------------------------------------- PRIMS USING PRIORITY QUEUE -----------------------------------------
        System.out.println("\n\n\n------------------------------------- PRIMS USING PRIORITY QUEUE ---------------------------------------------\n");

        PrimQueue.primQueueGraph primQ = new PrimQueue.primQueueGraph(graphPrimQueue);

        // add all the edges of the graphPrimQueue in the class of PrimQueue.primQueueGraph.
        for (int i = 0; i < graphPrimQueue.getAllEdges().size(); i++) {
            primQ.addEdge((int) graphPrimQueue.getAllEdges().get(i).vertex1.id, (int) graphPrimQueue.getAllEdges().get(i).vertex2.id, graphPrimQueue.getAllEdges().get(i).weight);
        }
        primQ.primMST();

// ----------------------------------------------- KRUSKAL ----------------------------------------------------
        System.out.println("\n\n\n----------------------------------------------- KRUSKAL ----------------------------------------------------\n");
        KruskalMST mst = new KruskalMST();
        start = System.nanoTime();
        List<Edge<Integer>> result = mst.getMST(graphKruskal);
        end = System.nanoTime();

        t1 = (double) start;
        t2 = (double) end;
        time = (t2 - t1) / 1000000;
        System.out.println("\n    >> Time taken to implement Kruskal:  " + time + " ms");

// ----------------------------------------------- DIJKSTRA --------------------------------------------
        System.out.println("\n\n\n----------------------------------------------- DIJKSTRA ----------------------------------------------------\n");
        Graph<Integer> dijkstraGraph = new Graph<>(false);
        // call the method "dijkstra" to fill "dijkstraGraph".
        dijkstra(dijkstraGraph);
        DijkstraShortestPath dsp = new DijkstraShortestPath();
        // source vertex of "dijkstraGraph" is the vertex(0) with name "Jeddah".
        Vertex<Integer> sourceVertex = dijkstraGraph.getVertex(0);
        start = System.nanoTime();
        // implement dijkstra.
        Map<Vertex<Integer>, Integer> distance = dsp.shortestPath(dijkstraGraph, sourceVertex);
        end = System.nanoTime();
        printDijkstra(distance);
        t1 = (double) start;
        t2 = (double) end;
        time = (t2 - t1) / 1000000;
        System.out.println("\n    >> Time taken to implement Dijkstra:  " + time + " ms");

    }

    // ----------------------------------------------- METHODS -----------------------------------------------
    
    
    // this method takes fill the graph randomly by specifying the number of edges and verticesfrom the user.
    public static Graph make_graph(int vertices, int edges) {
        Vertex x = new Vertex(0);
        Vertex y = new Vertex(0);
        Graph<Integer> graph = new Graph<>(false);
        for (int i = 0; i < vertices; i++) {
            x = new Vertex(i);
            graph.addVertex(x);
        }
        for (int i = 0; i < edges; i++) {
            long node1 = (int) (Math.random() * vertices);
            long node2 = (int) (Math.random() * vertices);
            graph.getVertex(node1);
            graph.getVertex(node2);
            
            // the weight of all edges are in the range of (0-10).
            int weight = (int) (1 + (Math.random() * 10));
            graph.addEdge(node1, node2, weight);

        }
        return graph;
    }
    
    
    
// this method fill the graph of dijkstra algorithm.
    public static void dijkstra(Graph graph) {

        Vertex x = new Vertex(0);
        for (int i = 0; i < 12; i++) {
            x = new Vertex(i);
            graph.addVertex(x);

            switch (i) {
                case 0: {
                    x.setName("Jeddah");
                    break;
                }
                case 1: {
                    x.setName("Makkah");
                    break;
                }
                case 2: {
                    x.setName("Madinah");
                    break;
                }
                case 3: {
                    x.setName("Riyadh");
                    break;
                }
                case 4: {
                    x.setName("Dammam");
                    break;
                }
                case 5: {
                    x.setName("Taif");
                    break;
                }
                case 6: {
                    x.setName("Abha");
                    break;
                }
                case 7: {
                    x.setName("Tabuk");
                    break;
                }
                case 8: {
                    x.setName("Qasim");
                    break;
                }
                case 9: {
                    x.setName("Hail");
                    break;
                }
                case 10: {
                    x.setName("Jizan");
                    break;
                }
                case 11: {
                    x.setName("Najran");
                    break;
                }
            }
        }

        graph.addEdge(graph.getVertex(0).id, graph.getVertex(0).id, 0);
        graph.addEdge(graph.getVertex(0).id, graph.getVertex(1).id, 79);
        graph.addEdge(graph.getVertex(0).id, graph.getVertex(2).id, 420);
        graph.addEdge(graph.getVertex(0).id, graph.getVertex(3).id, 949);
        graph.addEdge(graph.getVertex(0).id, graph.getVertex(4).id, 1343);
        graph.addEdge(graph.getVertex(0).id, graph.getVertex(5).id, 167);
        graph.addEdge(graph.getVertex(0).id, graph.getVertex(6).id, 625);
        graph.addEdge(graph.getVertex(0).id, graph.getVertex(7).id, 1024);
        graph.addEdge(graph.getVertex(0).id, graph.getVertex(8).id, 863);
        graph.addEdge(graph.getVertex(0).id, graph.getVertex(9).id, 777);
        graph.addEdge(graph.getVertex(0).id, graph.getVertex(10).id, 710);
        graph.addEdge(graph.getVertex(0).id, graph.getVertex(11).id, 905);

        graph.addEdge(graph.getVertex(1).id, graph.getVertex(1).id, 0);
        graph.addEdge(graph.getVertex(1).id, graph.getVertex(2).id, 358);
        graph.addEdge(graph.getVertex(1).id, graph.getVertex(3).id, 870);
        graph.addEdge(graph.getVertex(1).id, graph.getVertex(4).id, 1265);
        graph.addEdge(graph.getVertex(1).id, graph.getVertex(5).id, 88);
        graph.addEdge(graph.getVertex(1).id, graph.getVertex(6).id, 627);
        graph.addEdge(graph.getVertex(1).id, graph.getVertex(7).id, 1037);
        graph.addEdge(graph.getVertex(1).id, graph.getVertex(8).id, 876);
        graph.addEdge(graph.getVertex(1).id, graph.getVertex(9).id, 790);
        graph.addEdge(graph.getVertex(1).id, graph.getVertex(10).id, 685);
        graph.addEdge(graph.getVertex(1).id, graph.getVertex(11).id, 912);

        graph.addEdge(graph.getVertex(2).id, graph.getVertex(2).id, 0);
        graph.addEdge(graph.getVertex(2).id, graph.getVertex(3).id, 848);
        graph.addEdge(graph.getVertex(2).id, graph.getVertex(4).id, 1343);
        graph.addEdge(graph.getVertex(2).id, graph.getVertex(5).id, 446);
        graph.addEdge(graph.getVertex(2).id, graph.getVertex(6).id, 985);
        graph.addEdge(graph.getVertex(2).id, graph.getVertex(7).id, 679);
        graph.addEdge(graph.getVertex(2).id, graph.getVertex(8).id, 518);
        graph.addEdge(graph.getVertex(2).id, graph.getVertex(9).id, 432);
        graph.addEdge(graph.getVertex(2).id, graph.getVertex(10).id, 1043);
        graph.addEdge(graph.getVertex(2).id, graph.getVertex(11).id, 1270);

        graph.addEdge(graph.getVertex(3).id, graph.getVertex(3).id, 0);
        graph.addEdge(graph.getVertex(3).id, graph.getVertex(4).id, 395);
        graph.addEdge(graph.getVertex(3).id, graph.getVertex(5).id, 782);
        graph.addEdge(graph.getVertex(3).id, graph.getVertex(6).id, 1064);
        graph.addEdge(graph.getVertex(3).id, graph.getVertex(7).id, 1304);
        graph.addEdge(graph.getVertex(3).id, graph.getVertex(8).id, 330);
        graph.addEdge(graph.getVertex(3).id, graph.getVertex(9).id, 640);
        graph.addEdge(graph.getVertex(3).id, graph.getVertex(10).id, 1272);
        graph.addEdge(graph.getVertex(3).id, graph.getVertex(11).id, 950);
        graph.addEdge(graph.getVertex(4).id, graph.getVertex(4).id, 0);
        graph.addEdge(graph.getVertex(4).id, graph.getVertex(5).id, 1177);
        graph.addEdge(graph.getVertex(4).id, graph.getVertex(6).id, 1495);
        graph.addEdge(graph.getVertex(4).id, graph.getVertex(7).id, 1729);
        graph.addEdge(graph.getVertex(4).id, graph.getVertex(8).id, 725);
        graph.addEdge(graph.getVertex(4).id, graph.getVertex(9).id, 1035);
        graph.addEdge(graph.getVertex(4).id, graph.getVertex(10).id, 1667);
        graph.addEdge(graph.getVertex(4).id, graph.getVertex(11).id, 1345);

        graph.addEdge(graph.getVertex(5).id, graph.getVertex(5).id, 0);
        graph.addEdge(graph.getVertex(5).id, graph.getVertex(6).id, 561);
        graph.addEdge(graph.getVertex(5).id, graph.getVertex(7).id, 1204);
        graph.addEdge(graph.getVertex(5).id, graph.getVertex(8).id, 936);
        graph.addEdge(graph.getVertex(5).id, graph.getVertex(9).id, 957);
        graph.addEdge(graph.getVertex(5).id, graph.getVertex(10).id, 763);
        graph.addEdge(graph.getVertex(5).id, graph.getVertex(11).id, 864);

        graph.addEdge(graph.getVertex(6).id, graph.getVertex(6).id, 0);
        graph.addEdge(graph.getVertex(6).id, graph.getVertex(7).id, 1649);
        graph.addEdge(graph.getVertex(6).id, graph.getVertex(8).id, 1488);
        graph.addEdge(graph.getVertex(6).id, graph.getVertex(9).id, 1402);
        graph.addEdge(graph.getVertex(6).id, graph.getVertex(10).id, 202);
        graph.addEdge(graph.getVertex(6).id, graph.getVertex(11).id, 280);

        graph.addEdge(graph.getVertex(7).id, graph.getVertex(7).id, 0);
        graph.addEdge(graph.getVertex(7).id, graph.getVertex(8).id, 974);
        graph.addEdge(graph.getVertex(7).id, graph.getVertex(9).id, 664);
        graph.addEdge(graph.getVertex(7).id, graph.getVertex(10).id, 1722);
        graph.addEdge(graph.getVertex(7).id, graph.getVertex(11).id, 1929);

        graph.addEdge(graph.getVertex(8).id, graph.getVertex(8).id, 0);
        graph.addEdge(graph.getVertex(8).id, graph.getVertex(9).id, 310);
        graph.addEdge(graph.getVertex(8).id, graph.getVertex(10).id, 1561);
        graph.addEdge(graph.getVertex(8).id, graph.getVertex(11).id, 1280);

        graph.addEdge(graph.getVertex(9).id, graph.getVertex(9).id, 0);
        graph.addEdge(graph.getVertex(9).id, graph.getVertex(10).id, 1475);
        graph.addEdge(graph.getVertex(9).id, graph.getVertex(11).id, 1590);

        graph.addEdge(graph.getVertex(10).id, graph.getVertex(10).id, 0);
        graph.addEdge(graph.getVertex(10).id, graph.getVertex(11).id, 482);

        graph.addEdge(graph.getVertex(11).id, graph.getVertex(11).id, 0);

    }

    
    // this method prints the graph of dijkstra algorithm.
    public static void printDijkstra(Map<Vertex<Integer>, Integer> distance) {
        Collection keysSet = distance.keySet();
        Collection valuesSet = distance.values();
        Object[] keys = keysSet.toArray();
        Object[] values = valuesSet.toArray();

        for (int i = 0; i < keys.length; i++) {
            System.out.println("Distance from Jeddah to " + ((Vertex) keys[i]).getName() + " = " + values[i]);
        }
    }

}
