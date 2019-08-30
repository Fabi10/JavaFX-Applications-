package gui.patient;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Patient {

    private SimpleStringProperty name;

    private SimpleStringProperty note1;

    private SimpleStringProperty note11;

    private SimpleStringProperty note2;

    private SimpleIntegerProperty note3;

    public Patient(String name, String note1, String note11, String note2, int note3) {
        this.name = new SimpleStringProperty(name);
        this.note1 = new SimpleStringProperty(note1);
        this.note11 = new SimpleStringProperty(note11);
        this.note2 = new SimpleStringProperty(note2);
        this.note3 = new SimpleIntegerProperty(note3);

    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty note1Property() {
        return note1;
    }

    public SimpleStringProperty note11Property() {
        return note11;
    }

    public SimpleStringProperty note2Property() {
        return note2;
    }

    public SimpleIntegerProperty note3Property() {
        return note3;
    }

}
