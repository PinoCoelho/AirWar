package AirWar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Main extends Application{

    static Pane layout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Air War");

        // Genera la variable para la imagen
        Image map = new Image(new FileInputStream("C:\\Users\\josep\\IdeaProjects\\AirWar\\src\\Imagenes\\Map.png"));
        Image airport = new Image(new FileInputStream("C:\\Users\\josep\\IdeaProjects\\AirWar\\src\\Imagenes\\Airport.png"));
        Image carrier = new Image(new FileInputStream("C:\\Users\\josep\\IdeaProjects\\AirWar\\src\\Imagenes\\carrier.png"));

        //Setting the image view
        ImageView mapView = new ImageView(map);

        //Labels nombres continentes
        Text textCamarei = new Text();
        textCamarei.setText("CAMAREI");
        textCamarei.setLayoutX(550);
        textCamarei.setLayoutY(400);

        Text textRacafi = new Text();
        textRacafi.setText("RACAFI");
        textRacafi.setLayoutX(250);
        textRacafi.setLayoutY(250);

        Text textUpoera = new Text();
        textUpoera.setText("UPOERA");
        textUpoera.setLayoutX(825);
        textUpoera.setLayoutY(250);

        layout = new Pane();
        layout.getChildren().addAll(mapView, textCamarei, textRacafi, textUpoera);

        // Se generan los edificios en el mapa
        Random r = new Random();
        Map.cantAirport = r.nextInt(1, 5);
        Map.cantCarrier = r.nextInt(1, 5);
        int tempc = 0;
        int tempa = 0;
        int randi;
        int randj;
        int tempid = Map.cantAirport + Map.cantCarrier - 1;

        while (true) {
            if (tempc < Map.cantCarrier) {
                randi = r.nextInt(10);
                randj = r.nextInt(20);
                if (Map.arrTierra[randi][randj] == 0) {
                    ImageView carrierView = new ImageView(carrier);
                    carrierView.setLayoutX(Map.coordX[randj]);
                    carrierView.setLayoutY(Map.coordY[randi]);
                    Map.arrTierra[randi][randj] = -1;
                    layout.getChildren().add(carrierView);
                    tempc++;
                    Map.listaBuildings.insertFirst(new Map.Building(Map.coordX[randj], Map.coordY[randi], tempid, "carrier", "na", r.nextInt(6)));
                    tempid--;
                }
            }
            else if (tempa < Map.cantAirport) {
                randi = r.nextInt(10);
                randj = r.nextInt(20);
                if (Map.arrTierra[randi][randj] > 0) {
                    ImageView airportView = new ImageView(airport);
                    airportView.setLayoutX(Map.coordX[randj]);
                    airportView.setLayoutY(Map.coordY[randi]);
                    layout.getChildren().add(airportView);
                    tempa++;
                    if (Map.arrTierra[randi][randj] == 1) {
                        Map.listaBuildings.insertFirst(new Map.Building(Map.coordX[randj], Map.coordY[randi], tempid, "airport", "racafi", r.nextInt(6)));
                        tempid--;
                        Map.arrTierra[randi][randj] = -1;
                    }
                    else if (Map.arrTierra[randi][randj] == 2) {
                        Map.listaBuildings.insertFirst(new Map.Building(Map.coordX[randj], Map.coordY[randi], tempid, "airport", "camarei", r.nextInt(6)));
                        tempid--;
                        Map.arrTierra[randi][randj] = -1;
                    }
                    else {
                        Map.listaBuildings.insertFirst(new Map.Building(Map.coordX[randj], Map.coordY[randi], tempid, "airport", "upoera", r.nextInt(6)));
                        tempid--;
                        Map.arrTierra[randi][randj] = -1;
                    }
                }
            }
            else {
                break;
            }
        }

        // Se asignan las rutas de los edificios
        Map.asignarRutas();

        // Se crean las flechas para simbolizar las rutas
        int[][] matrizAdyacencia = Map.graphBuildings.getAdjacencyMatrix();
        int numVertices = Map.cantAirport + Map.cantCarrier;
        for (int source=0 ; source<numVertices; source++) {
            for (int destination=0; destination<numVertices; destination++) {
                if (matrizAdyacencia[source][destination] > 0) {
                    int startX = Map.listaBuildings.get(source).x;
                    int startY = Map.listaBuildings.get(source).y;
                    int endX = Map.listaBuildings.get(destination).x;
                    int endY = Map.listaBuildings.get(destination).y;
                    Arrow arrow = new Arrow(startX+25, startY+25, endX+25, endY+25);
                    layout.getChildren().add(arrow);

                    int id = (int) (Math.pow(source, 2) + Math.pow(destination,2));
                    if (!Map.listaLabels.contains(id)) {
                        Label label = new Label();
                        label.setText(Integer.toString(matrizAdyacencia[source][destination]));
                        label.setTranslateX((Math.abs(startX + endX)/2)+25);
                        label.setTranslateY((Math.abs(startY + endY)/2)+25);
                        label.setStyle("-fx-background-color: #DADAD7");
                        layout.getChildren().add(label);
                        Map.listaLabels.insertFirst(new Map.weightLabels(label, source, destination, id));
                    }
                }
            }
        }

        Scene scene = new Scene(layout, 1000, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}