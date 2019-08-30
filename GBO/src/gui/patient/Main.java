package gui.patient;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) {
        Presenter presenter = new Presenter();
        View view = new View(presenter);
        Model model = new Model();
        presenter.setModel(model);
        presenter.setView(view);
        view.updateTable(model.getData());

        Scene scene = new Scene(presenter.getView(), 700, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Patientenaufnahme");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
