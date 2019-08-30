package gui.patient;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Dialog {

    private Presenter presenter;

    private TextField nameF;

    private TextField noteF;

    private TextField codeF;

    private TextField sosF;

    private TextField levelF;

    private Label status;

    public Dialog(Presenter presenter) {
        this.presenter = presenter;
        show();

    }

    public void show() {

        VBox root = new VBox();

        HBox box1 = new HBox();
        Label name = new Label("Name");
        nameF = new TextField();
        box1.getChildren().addAll(name, nameF);
        box1.setSpacing(10);

        HBox box2 = new HBox();
        Label note = new Label("Notiz");
        noteF = new TextField();
        box2.getChildren().addAll(note, noteF);
        box2.setSpacing(10);

        HBox box22 = new HBox();
        Label code = new Label("Diagnose-Code:");
        codeF = new TextField();
        box22.getChildren().addAll(code, codeF);
        box22.setSpacing(10);

        HBox box3 = new HBox();
        Label sos = new Label("Notfall?");
        sosF = new TextField();
        box3.getChildren().addAll(sos, sosF);
        box3.setSpacing(10);

        HBox box4 = new HBox();
        Label level = new Label("Schmerzlevel [1-10]");
        levelF = new TextField();
        box4.getChildren().addAll(level, levelF);
        box4.setSpacing(10);

        HBox buttons = new HBox();
        Button add = new Button("Hinzufügen");
        Button cancel = new Button("Abbrechen");
        buttons.getChildren().addAll(add, cancel);
        buttons.setSpacing(10);

        status = new Label();

        root.getChildren().addAll(box1, box2, box22, box3, box4, buttons, status);
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Stage popup = new Stage();
        Scene scene2 = new Scene(root, 400, 300);
        popup.setScene(scene2);
        popup.setTitle("Neue Patientenaufnahme");
        popup.show();

        cancel.setOnAction(e -> popup.close());

        add.setOnAction(e -> addPatient());

    }

    private void addPatient() {
        try {
            String name = nameF.getText();
            String note1 = noteF.getText();
            String note11 = codeF.getText();
            String note2 = sosF.getText();
            int note3 = Integer.parseInt(levelF.getText());

            Patient p = new Patient(name, note1, note11, note2, note3);
            presenter.addPatient(p);

            nameF.setText("");
            noteF.setText("");
            codeF.setText("");
            sosF.setText("");
            levelF.setText("");

        } catch (

        NumberFormatException e) {
            status.setText("Warnung: Ungültige Eingabe");
        }
    }

}
