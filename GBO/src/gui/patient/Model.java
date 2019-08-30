package gui.patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {

    private ObservableList<Patient> data;

    public Model() {
        data = FXCollections.observableArrayList();
        data.add(new Patient("Maria Höhn", "hingefallen,umgeknickt", "E10", "nein", 8));

    }

    public void addPatient(Patient p) {
        data.add(p);
    }

    public void deletePatient(Patient p) {
        data.remove(p);
    }

    public ObservableList<Patient> getData() {
        return data;
    }

}
