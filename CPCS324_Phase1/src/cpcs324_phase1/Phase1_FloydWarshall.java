package cpcs324_phase1;

public class Phase1_FloydWarshall {

    final static int INF = 99999;
    int vertices;
    int[][] graph;
    int[][] dist;

    public Phase1_FloydWarshall() {
        //initialize graph with adjacency matrix
        graph = new int[][]{
            {0, 10, INF, INF, INF, 5, INF, INF, INF, INF},
            {INF, 0, 3, INF, 3, INF, INF, INF, INF, INF},
            {INF, INF, 0, 4, INF, INF, INF, 5, INF, INF},
            {INF, INF, INF, 0, INF, INF, INF, INF, 4, INF},
            {INF, INF, 4, INF, 0, INF, 2, INF, INF, INF},
            {INF, 3, INF, INF, INF, 0, INF, INF, INF, 2},
            {INF, INF, INF, 7, INF, INF, 0, INF, INF, INF},
            {INF, INF, INF, 4, INF, INF, INF, 0, 3, INF},
            {INF, INF, INF, INF, INF, INF, INF, INF, 0, INF},
            {INF, 6, INF, INF, INF, INF, 8, INF, INF, 0}
        };

        vertices = 10;   //initialize total vertices
        dist = new int[vertices][vertices];   //initialize distances matrix
    }

    public void findShortestPaths() {

        int i, j, k;

//initialize distance matrix
        for (i = 0; i < vertices; i++) {
            for (j = 0; j < vertices; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        //print the weight matrix
        System.out.println("Weight matrix");
        this.printDistances();
        System.out.println("\n\n");
        
        
//finding shortest paths
        for (k = 0; k < vertices; k++) {
// Pick all vertices as source one by one
            for (i = 0; i < vertices; i++) {
// Pick all vertices as destination for the
// above picked source
                for (j = 0; j < vertices; j++) {
// If vertex k is on the shortest path from
// i to j, then update the value of dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }

//print intermediate result
            System.out.println("Shortest distances from vertex "
                    + (char) ('A' + k) + " to all other vertices");
            this.printDistances();
            System.out.println();
        }
    }

    public void printDistances() {
        System.out.print("\t");
        for (int i = 0; i < vertices; i++) {
            System.out.print((char) ('A' + i) + "\t");
        }
        System.out.println();
        for (int i = 0; i < vertices; ++i) {
            System.out.print((char) ('A' + i) + "\t");
            for (int j = 0; j < vertices; ++j) {
                if (dist[i][j] == INF) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(dist[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

// Driver program to test above function
    public static void main(String[] args) {
        Phase1_FloydWarshall fw = new Phase1_FloydWarshall();



// Print the solution
        fw.findShortestPaths();

//print final result
        System.out.println("\n\nFinal shortest distances matrix:");
        fw.printDistances();
    }
}
