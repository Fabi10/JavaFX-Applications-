package gui.patient;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class View extends VBox {

    private Presenter presenter;

    private TableView<Patient> data;

    public View(Presenter presenter) {
        this.presenter = presenter;
        initView();
    }

    public void initView() {
        data = new TableView<>();

        TableColumn<Patient, String> name = new TableColumn<Patient, String>("Vor-und Nachname");
        TableColumn<Patient, String> n1 = new TableColumn<Patient, String>("Notizen");
        TableColumn<Patient, String> n11 = new TableColumn<Patient, String>("Diagnose-Code");
        TableColumn<Patient, String> n2 = new TableColumn<Patient, String>("Notfall?");
        TableColumn<Patient, Integer> n3 = new TableColumn<Patient, Integer>("Schmerzlevel \n [von 1-10]");

        name.setPrefWidth(150);
        n1.setPrefWidth(200);
        n11.setPrefWidth(90);
        n2.setPrefWidth(100);
        n3.setPrefWidth(100);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        n1.setCellValueFactory(new PropertyValueFactory<>("note1"));
        n11.setCellValueFactory(new PropertyValueFactory<>("note11"));
        n2.setCellValueFactory(new PropertyValueFactory<>("note2"));
        n3.setCellValueFactory(new PropertyValueFactory<>("note3"));
        data.getColumns().addAll(name, n1, n11, n2, n3);

        Button b = new Button("Neuen Patient erfassen");
        Button delete = new Button("Patient löschen");
        getChildren().addAll(data, b, delete);
        setSpacing(10);
        setPadding(new Insets(10));

        b.setOnAction(e -> presenter.getDialog());
        delete.setOnAction(e -> deletePatient());

    }

    public void deletePatient() {
        if (data.getSelectionModel().getSelectedItem() != null && !data.getItems().isEmpty()) {
            presenter.deletePatient(data.getSelectionModel().getSelectedItem());
        }
    }

    public void updateTable(ObservableList<Patient> tabledata) {
        data.getItems().clear();
        data.getItems().addAll(tabledata);

    }

}
