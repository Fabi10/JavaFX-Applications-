package gui.diagram;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private TableView<Obst> diagram;

    private ObservableList<Obst> data;

    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        diagram = new TableView<>();
        data = FXCollections.observableArrayList();
        data.add(new Obst("Grapefruit", 13.0));
        data.add(new Obst("Orangen", 25.0));
        data.add(new Obst("Pflaumen", 10.0));
        data.add(new Obst("Birnen", 5.0));
        data.add(new Obst("Mirabellen", 17.0));
        data.add(new Obst("Bananen", 9.0));
        data.add(new Obst("Äpfel", 30.0));

        TableColumn<Obst, String> names = new TableColumn<>("Obst");
        TableColumn<Obst, Integer> menge = new TableColumn<>("Menge");
        names.setCellValueFactory(new PropertyValueFactory<>("name"));
        menge.setCellValueFactory(new PropertyValueFactory<>("wert"));
        diagram.getColumns().addAll(names, menge);
        diagram.getItems().addAll(data);

        root.setTop(diagram);

        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        list.add(new PieChart.Data("Grapefriut", 13.0));
        list.add(new PieChart.Data("Orangen", 25.0));
        list.add(new PieChart.Data("Pflaumen", 10.0));
        list.add(new PieChart.Data("Birnen", 5.0));
        list.add(new PieChart.Data("Mirabellen", 17.0));
        list.add(new PieChart.Data("Bananen", 9.0));
        list.add(new PieChart.Data("Äpfel", 30.0));

        PieChart pie = new PieChart(list);
        root.setCenter(pie);

        HBox sliders = new HBox();
        VBox box1 = new VBox();
        VBox box2 = new VBox();
        VBox box3 = new VBox();
        VBox box4 = new VBox();
        VBox box5 = new VBox();
        VBox box6 = new VBox();
        VBox box7 = new VBox();

        Slider s1 = new Slider();
        Slider s2 = new Slider();
        Slider s3 = new Slider();
        Slider s4 = new Slider();
        Slider s5 = new Slider();
        Slider s6 = new Slider();
        Slider s7 = new Slider();
        s1.orientationProperty().set(Orientation.VERTICAL);
        s2.orientationProperty().set(Orientation.VERTICAL);
        s3.orientationProperty().set(Orientation.VERTICAL);
        s4.orientationProperty().set(Orientation.VERTICAL);
        s5.orientationProperty().set(Orientation.VERTICAL);
        s6.orientationProperty().set(Orientation.VERTICAL);
        s7.orientationProperty().set(Orientation.VERTICAL);

        s1.valueProperty().bindBidirectional(list.get(0).pieValueProperty());
        s2.valueProperty().bindBidirectional(list.get(1).pieValueProperty());
        s3.valueProperty().bindBidirectional(list.get(2).pieValueProperty());
        s4.valueProperty().bindBidirectional(list.get(3).pieValueProperty());
        s5.valueProperty().bindBidirectional(list.get(4).pieValueProperty());
        s6.valueProperty().bindBidirectional(list.get(5).pieValueProperty());
        s7.valueProperty().bindBidirectional(list.get(6).pieValueProperty());

        s1.setShowTickLabels(true);
        s2.setShowTickLabels(true);
        s3.setShowTickLabels(true);
        s4.setShowTickLabels(true);
        s5.setShowTickLabels(true);
        s6.setShowTickLabels(true);
        s7.setShowTickLabels(true);

        Label l1 = new Label("Grapefruit");
        Label l2 = new Label("Orangen");
        Label l3 = new Label("Pflaumen");
        Label l4 = new Label("Birnen");
        Label l5 = new Label("Mirabellen");
        Label l6 = new Label("Bananen");
        Label l7 = new Label("Äpfel");

        box1.getChildren().addAll(s1, l1);
        box2.getChildren().addAll(s2, l2);
        box3.getChildren().addAll(s3, l3);
        box4.getChildren().addAll(s4, l4);
        box5.getChildren().addAll(s5, l5);
        box6.getChildren().addAll(s6, l6);
        box7.getChildren().addAll(s7, l7);

        sliders.getChildren().addAll(box1, box2, box3, box4, box5, box6, box7);
        sliders.setSpacing(40);
        root.setBottom(sliders);

        Scene scene = new Scene(root, 700, 900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tortendiagramm");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

}
