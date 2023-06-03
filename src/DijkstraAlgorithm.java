import java.util.*;

public class DijkstraAlgorithm {
    private static final int INFINITY = Integer.MAX_VALUE;

    public static void dijkstra(List<List<Node>> graph, int source) {
        int numNodes = graph.size();
        int[] distances = new int[numNodes];
        boolean[] visited = new boolean[numNodes];
        int[] previous = new int[numNodes];

        Arrays.fill(distances, INFINITY);
        Arrays.fill(previous, -1);

        distances[source] = 0;

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(numNodes, Comparator.comparingInt(node -> node.distance));
        priorityQueue.offer(new Node(source, 0));

        while (!priorityQueue.isEmpty()) {
            int currentNode = priorityQueue.poll().vertex;

            if (visited[currentNode]) {
                continue;
            }

            visited[currentNode] = true;

            List<Node> neighbors = graph.get(currentNode);
            for (Node neighbor : neighbors) {
                int nextNode = neighbor.vertex;
                int edgeWeight = neighbor.distance;

                int newDistance = distances[currentNode] + edgeWeight;
                if (newDistance < distances[nextNode]) {
                    distances[nextNode] = newDistance;
                    previous[nextNode] = currentNode;
                    priorityQueue.offer(new Node(nextNode, newDistance));
                }
            }
        }

        // Imprimir las distancias más cortas desde el nodo origen hasta los demás nodos
        for (int i = 0; i < numNodes; i++) {
            if (distances[i] == INFINITY) {
                System.out.println("No hay camino desde el nodo origen hasta el nodo " + i);
            } else {
                System.out.println("La distancia más corta desde el nodo origen hasta el nodo " + i + " es " + distances[i]);
            }
        }

        // Imprimir el camino más corto desde el nodo origen hasta el nodo destino
        int destination = numNodes - 1; // Nodo destino
        List<Integer> shortestPath = new ArrayList<>();
        while (destination != -1) {
            shortestPath.add(destination);
            destination = previous[destination];
        }
        Collections.reverse(shortestPath);
        System.out.println("El camino más corto desde el nodo origen hasta el nodo destino es: " + shortestPath);
    }

    public static void main(String[] args) {
        int numNodes = 6; // Número total de nodos en el grafo

        // Crear el grafo con su lista de adyacencia
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            graph.add(new ArrayList<>());
        }

        // Agregar las aristas al grafo
        graph.get(0).add(new Node(1, 5)); // Arista: (0, 1) con peso 5
        graph.get(0).add(new Node(2, 3)); // Arista: (0, 2) con peso 3
        graph.get(1).add(new Node(3, 6)); // Arista: (1, 3) con peso 6
        graph.get(1).add(new Node(2, 2)); // Arista: (1, 2) con peso 2
        graph.get(2).add(new Node(4, 4)); // Arista: (2, 4) con peso 4
        graph.get(2).add(new Node(5, 2)); // Arista: (2, 5) con peso 2
        graph.get(2).add(new Node(3, 7)); // Arista: (2, 3) con peso 7
        graph.get(3).add(new Node(4, -1)); // Arista: (3, 4) con peso -1
        graph.get(4).add(new Node(5, -2)); // Arista: (4, 5) con peso -2

        int source = 0; // Nodo origen
        dijkstra(graph, source);
    }

    static class Node {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}
