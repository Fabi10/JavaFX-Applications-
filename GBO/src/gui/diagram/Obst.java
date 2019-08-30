package gui.diagram;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Obst {

    private SimpleStringProperty name;

    private SimpleDoubleProperty wert;

    public Obst(String name, double wert) {
        this.name = new SimpleStringProperty(name);
        this.wert = new SimpleDoubleProperty(wert);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleDoubleProperty wertProperty() {
        return wert;
    }

}
