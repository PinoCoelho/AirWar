package AirWar;

import java.util.Random;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Map {
    static int[][] arrTierra = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 3, 3, 3, 3, 3, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 3, 3, 3, 3, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 2, 2, 0, 0, 3, 3, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 2, 2, 2, 0, 0, 3, 3, 3, 0},
            {0, 0, 1, 1, 1, 1, 1, 0, 0, 2, 2, 2, 2, 2, 0, 0, 3, 3, 3, 0},
            {0, 0, 1, 1, 1, 1, 1, 0, 0, 2, 2, 2, 2, 2, 0, 0, 3, 3, 3, 0},
            {0, 0, 1, 1, 1, 1, 1, 0, 0, 2, 2, 2, 2, 2, 0, 0, 3, 3, 3, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 3, 3, 3, 0, 0},
    };

    static int[] coordX = {0, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900, 950};
    static int[] coordY = {0, 50, 100, 150, 200, 250, 300, 350, 400, 450};
    static int cantCarrier = 0;
    static int cantAirport = 0;
    static ListaEnlazada listaBuildings = new ListaEnlazada();
    static ListaLabelWeight listaLabels = new ListaLabelWeight();
    static Graph graphBuildings;
    static Random r = new Random();

    static class Building {
        int x;
        int y;
        int id;
        String type;
        String continent;
        int maxAviones;

        Building(int x, int y, int id, String type, String continent, int maxAviones) {
            this.x = x;
            this.y = y;
            this.id = id;
            this.type = type;
            this.continent = continent;
            this.maxAviones = maxAviones;
        }
    }

    static class weightLabels {
        Label labelWeight;
        int source;
        int destination;
        int id;

        weightLabels(Label labelWeight, int source, int destination, int id) {
            this.labelWeight = labelWeight;
            this.source = source;
            this.destination = destination;
            this.id = id; // el id debe ser source + destination
        }
    }

    static class Aviones {

        String name;
        int id;
        int source;
        int destination;
        int fortaleza;
        int eficiencia;
        int velocidad;

        Aviones(String name, int id, int source, int destination, int fortaleza, int eficiencia, int velocidad) {
            this.name = name;
            this.id = id;
            this.source = source;
            this.destination = destination;
            this.fortaleza = fortaleza;
            this.eficiencia = eficiencia;
            this.velocidad = velocidad;
        }

    }

    static int calcularPeso(int source, int destination) {
        int peso = 0;
        peso = peso + (Math.abs(listaBuildings.get(source).x - listaBuildings.get(destination).x));
        peso = peso + (Math.abs(listaBuildings.get(source).y - listaBuildings.get(destination).y));
        peso = (peso/100);
        if (listaBuildings.get(destination).type.equals("airport")) {
            peso = peso + 2;
        }
        if (!listaBuildings.get(source).continent.equals(listaBuildings.get(destination).continent)) {
            peso = peso + 3;
        }
        return peso;
    }

    static void asignarRutas() {
        int numVertices = cantAirport + cantCarrier;
        graphBuildings = new Graph(numVertices);
        int source;
        int destination;
        int rand = r.nextInt(10, 21);

        for (int i=0; i<rand; i++) {
            source = r.nextInt(numVertices);
            destination = r.nextInt(numVertices);
            if (source != destination) {
                graphBuildings.addEdge(source, destination, calcularPeso(source, destination));
            }
        }

        /**
        int[] shortestPath = graphBuildings.shortestPath(3, 2);


        if (shortestPath.length > 0) {
                System.out.print("Shortest path from " + 3 + " to " + 2 + ": ");
                for (int vertex : shortestPath) {
                    System.out.print(vertex + " ");

                }
        }
        else {
            System.out.println("No hay ruta");
        }**/

    }

    static void startPlaneAnimation(String planeName, int source, int destination) {
        Image image = new Image("C:\\Users\\josep\\IdeaProjects\\AirWar\\src\\Imagenes\\Airport.png");
        ImageView imageView = new ImageView(image);

        // Set the initial position of the image
        double startX = listaBuildings.get(source).x; // Replace with your desired starting X position
        double startY = listaBuildings.get(source).y; // Replace with your desired starting Y position
        imageView.setLayoutX(startX);
        imageView.setLayoutY(startY);
        Main.layout.getChildren().add(imageView);

        // Create a TranslateTransition to move the image to the destination
        double destinationX = listaBuildings.get(destination).x; // Replace with your desired destination X position
        double destinationY = listaBuildings.get(destination).y; // Replace with your desired destination Y position
        TranslateTransition transition = new TranslateTransition(Duration.seconds(graphBuildings.getAdjacencyMatrix()[source][destination]*10), imageView);
        transition.setToX(destinationX - startX);
        transition.setToY(destinationY - startY);

        // Print current position at regular intervals
        Duration interval = Duration.seconds(0.5); // Set the interval duration
        transition.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            int currentX = (int) (startX + transition.getToX() * (newTime.toMillis() / transition.getDuration().toMillis()));
            int currentY = (int) (startY + transition.getToY() * (newTime.toMillis() / transition.getDuration().toMillis()));
            System.out.println("Current position: (" + currentX + ", " + currentY + ")");
        });

        // Start the transition
        transition.play();
    }
}
