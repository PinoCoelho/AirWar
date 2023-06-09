package AirWar;

class Graph {
    private int[][] adjacencyMatrix;
    private int numVertices;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjacencyMatrix = new int[numVertices][numVertices];
    }

    public void addEdge(int source, int destination, int weight) {
        adjacencyMatrix[source][destination] = weight;
    }

    public int[] shortestPath(int source, int destination) {
        boolean[] visited = new boolean[numVertices];
        int[] distance = new int[numVertices];
        int[] parent = new int[numVertices];

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
            distance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }

        distance[source] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            try {
                int minVertex = findMinVertex(distance, visited);
                visited[minVertex] = true;

                for (int j = 0; j < numVertices; j++) {
                    if (!visited[j] && adjacencyMatrix[minVertex][j] != 0) {
                        int newDistance = distance[minVertex] + adjacencyMatrix[minVertex][j];
                        if (newDistance < distance[j]) {
                            distance[j] = newDistance;
                            parent[j] = minVertex;
                        }
                    }
                }
            }
            catch (Exception e) {

            }
        }

        return getPath(source, destination, parent);
    }

    private int findMinVertex(int[] distance, boolean[] visited) {
        int minVertex = -1;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && distance[i] < minDistance) {
                minVertex = i;
                minDistance = distance[i];
            }
        }
        return minVertex;
    }

    private int[] getPath(int source, int destination, int[] parent) {
        int[] path = new int[numVertices];
        int pathIndex = 0;

        int current = destination;
        while (current != -1) {
            path[pathIndex] = current;
            pathIndex++;
            current = parent[current];
        }

        int[] shortestPath = new int[pathIndex];
        for (int i = pathIndex - 1; i >= 0; i--) {
            shortestPath[pathIndex - i - 1] = path[i];
        }

        if (shortestPath[0] == source) {
            return shortestPath;
        }
        return new int[0];
    }

    public boolean isDirectPath(int source, int destination) {
        if (adjacencyMatrix[source][destination] == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
}

