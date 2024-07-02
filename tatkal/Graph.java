import java.util.Arrays;
import java.util.Stack;

class Graph {
    private int vertices; // Number of vertices
    private int[][] adjacencyMatrix;

    Graph(int vertices) {
        this.vertices = vertices;
        adjacencyMatrix = new int[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            Arrays.fill(adjacencyMatrix[i], Integer.MAX_VALUE); // Initialize the matrix with infinity
            adjacencyMatrix[i][i] = 0; // Distance to itself is zero
        }
    }

    void addEdge(int src, int dest, int weight) {
        adjacencyMatrix[src][dest] = weight;
    }

    void dijkstra(int src) {
        int[] dist = new int[vertices]; // Output array, dist[i] will hold the shortest distance from src to i
        boolean[] sptSet = new boolean[vertices]; // sptSet[i] will be true if vertex i is included in shortest path tree

        // Initialize all distances as INFINITE and sptSet[] as false
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(sptSet, false);

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < vertices - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices not yet processed
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the picked vertex
            for (int v = 0; v < vertices; v++) {
                // Update dist[v] if it's not in sptSet, there is an edge from u to v, and total weight of path from src to v through u is smaller than current value of dist[v]
                if (!sptSet[v] && adjacencyMatrix[u][v] != Integer.MAX_VALUE && dist[u] != Integer.MAX_VALUE && dist[u] + adjacencyMatrix[u][v] < dist[v]) {
                    dist[v] = dist[u] + adjacencyMatrix[u][v];
                }
            }
        }

        // Print the constructed distance array
        printSolution(dist);
    }

    int minDistance(int[] dist, boolean[] sptSet) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < vertices; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }

    void DFS(int startVertex) {
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();

        // Mark the current node as visited and push it to the stack
        visited[startVertex] = true;
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            // Pop a vertex from stack and print it
            int vertex = stack.pop();
            System.out.print(vertex + " ");

            // Get all adjacent vertices of the popped vertex
            // If an adjacent vertex has not been visited, then mark it visited and push it to the stack
            for (int i = 0; i < vertices; i++) {
                if (adjacencyMatrix[vertex][i] != Integer.MAX_VALUE && !visited[i]) {
                    stack.push(i);
                    visited[i] = true;
                }
            }
        }
    }

    void printSolution(int[] dist) {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int vertices = 9;
        Graph graph = new Graph(vertices);

        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 8, 2);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(6, 8, 6);
        graph.addEdge(7, 8, 7);

        System.out.println("Dijkstra's Algorithm:");
        graph.dijkstra(0);

        System.out.println("\nDepth-First Search (starting from vertex 0):");
        graph.DFS(0);
    }
}
