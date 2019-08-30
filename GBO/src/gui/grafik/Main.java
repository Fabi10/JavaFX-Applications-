package gui.grafik;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Main extends Application {

    private double x, y, c;

    private double xbegin, ybegin, endx, endy;

    private int counter1, counter2, counter3;

    private Rectangle rect;

    private BorderPane root;

    private Pane p, q;

    private ComboBox<Integer> size;

    private ComboBox<String> color;

    private RadioButton line;

    private RadioButton rec;

    private RadioButton circle;

    private RadioButton draw;

    private ObservableList<String> data2;

    private Line l;

    private ObservableList<Shape> shapes;

    private Label label;

    private Stage popup;

    private Button show;

    private TextArea text;

    private UndoRedoManager urm = new UndoRedoManager();

    public void start(Stage primaryStage) {

        root = new BorderPane();
        p = new Pane();
        q = new Pane();
        text = new TextArea();

        q.getChildren().add(text);
        popup = new Stage();
        Scene scene2 = new Scene(q);
        popup.setScene(scene2);
        popup.setTitle("Protokoll");

        counter1 = 0;
        counter2 = 0;
        counter3 = 0;

        size = new ComboBox<>();
        size.setPromptText("Strichdicke");
        ObservableList<Integer> data = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        size.getItems().addAll(data);
        size.setValue(data.get(0));

        color = new ComboBox<>();
        color.setPromptText("Farbe");
        data2 = FXCollections.observableArrayList("rot", "blau", "grün", "schwarz");
        color.getItems().addAll(data2);

        ToggleGroup tg = new ToggleGroup();
        line = new RadioButton("Linie");
        draw = new RadioButton("Zeichnen");
        rec = new RadioButton("Rechteck");
        circle = new RadioButton("Kreis");
        tg.getToggles().addAll(line, draw, rec, circle);
        line.setOnAction(e -> drawLine());
        draw.setOnAction(e -> drawing());
        rec.setOnAction(e -> drawRec());
        circle.setOnAction(e -> drawCircle());

        Button delete = new Button("Löschen");
        delete.setOnAction(e -> clear());

        HBox box = new HBox();
        box.getChildren().addAll(size, color, line, draw, rec, circle, delete);
        box.setSpacing(10);
        box.setPadding(new Insets(10));

        HBox box2 = new HBox();
        show = new Button("Protokoll");
        label = new Label();
        box2.getChildren().addAll(label, show);
        box2.setSpacing(5);

        show.setOnAction(e -> showProtokoll());

        root.setTop(box);
        root.setCenter(p);
        root.setBottom(box2);

        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zeichnen von Formen");
        primaryStage.show();

    }

    // Freihandzeichnen
    public void drawing() {
        p.setOnMouseReleased(e -> { // Damit das vorige Mausevent gelöscht
                                    // wird.
        });
        p.setOnMousePressed(e -> mousePressed(e.getX(), e.getY()));
        p.setOnMouseDragged(e -> mouseDragged(e.getX(), e.getY()));

    }

    public void mousePressed(double newX, double newY) {
        x = newX;
        y = newY;
        mouseDragged(x, y);
    }

    public void mouseDragged(double newX, double newY) {
        l = new Line(x, y, newX, newY);

        l.setStrokeWidth(size.getSelectionModel().getSelectedItem());

        if (color.getSelectionModel().getSelectedItem() == data2.get(0)) {
            l.setStroke(Color.RED);
        } else if (color.getSelectionModel().getSelectedItem() == data2.get(1)) {
            l.setStroke(Color.BLUE);
        } else if (color.getSelectionModel().getSelectedItem() == data2.get(2)) {
            l.setStroke(Color.GREEN);
        } else {
            l.setStroke(Color.BLACK);
        }

        p.getChildren().add(l);
        x = newX;
        y = newY;

    }

    // Starre Linien
    public void drawLine() {

        p.setOnMousePressed(e -> {
            xbegin = e.getX();
            ybegin = e.getY();
        });
        p.setOnMouseDragged(e -> {
            endx = e.getX();
            endy = e.getY();
            Line linie = new Line(xbegin, ybegin, endx, endy);

            if (p.getChildren().size() != 0) {
                p.getChildren().remove(p.getChildren().size() - 1);
            }
            p.getChildren().add(linie);
        });
        p.setOnMouseReleased(e -> {
            endx = e.getX();
            endy = e.getY();
            Line linie = new Line(xbegin, ybegin, endx, endy);
            p.getChildren().add(linie);
            counter1++;
            label.setText("Linien: " + counter1 + " Rechtecke: " + counter2 + " Kreise: " + counter3);

        });

    }

    // Rechtecke
    public void drawRec() {
        p.setOnMousePressed(e -> {
            xbegin = e.getX();
            ybegin = e.getY();
        });

        p.setOnMouseDragged(e -> {
            endx = e.getX();
            endy = e.getY();
            x = (endx - xbegin);
            y = (endy - ybegin);
            if (p.getChildren().size() != 0) {
                p.getChildren().remove(p.getChildren().size() - 1);
            }
            if (x <= 0 && y >= 0) {
                rect = new Rectangle(endx, ybegin, (x * -1), y);
            } else if (x >= 0 && y <= 0) {
                rect = new Rectangle(xbegin, endy, x, (y * -1));
            } else if (x <= 0 && y <= 0) {
                rect = new Rectangle(endx, endy, (x * -1), (y * -1));
            } else if (x >= 0 && y >= 0) {
                rect = new Rectangle(xbegin, ybegin, x, y);
            }
            rect.setStroke(Color.BLACK);
            rect.setFill(null);
            p.getChildren().add(rect);
        });

        p.setOnMouseReleased(e -> {
            endx = e.getX();
            endy = e.getY();

            if (x <= 0 && y >= 0) {
                rect = new Rectangle(endx, ybegin, (x * -1), y);
            } else if (x > 0 && y < 0) {
                rect = new Rectangle(xbegin, endy, x, (y * -1));
            } else if (x < 0 && y < 0) {
                rect = new Rectangle(endx, endy, (x * -1), (y * -1));
            } else if (x > 0 && y > 0) {
                rect = new Rectangle(xbegin, ybegin, x, y);
            }
            rect.setStroke(Color.BLACK);
            rect.setFill(null);
            p.getChildren().add(rect);
            counter2++;
            label.setText("Linien: " + counter1 + " Rechtecke: " + counter2 + " Kreise: " + counter3);

        });

    }

    // Kreise
    public void drawCircle() {
        p.setOnMousePressed(e -> {
            xbegin = e.getX();
            ybegin = e.getY();

        });

        p.setOnMouseDragged(e -> {
            endx = e.getX();
            endy = e.getY();
            double a = Math.pow((endx - xbegin), 2);
            double b = Math.pow((endy - ybegin), 2);
            c = Math.sqrt(a + b);
            Circle ci = new Circle(xbegin, ybegin, c);
            ci.setFill(null);
            ci.setStroke(Color.BLACK);
            ci.setStrokeWidth(5);
            if (p.getChildren().size() != 0) {
                p.getChildren().remove(p.getChildren().size() - 1);
            }
            p.getChildren().add(ci);
        });

        p.setOnMouseReleased(e -> {
            endx = e.getX();
            endy = e.getY();
            Circle ci = new Circle(xbegin, ybegin, c);
            ci.setFill(null);
            ci.setStroke(Color.BLACK);
            ci.setStrokeWidth(4);
            p.getChildren().add(ci);
            counter3++;
            label.setText("Linien: " + counter1 + " Rechtecke: " + counter2 + " Kreise: " + counter3);

        });

    }

    // Alle Objekte von der Pane löschen
    public void clear() {
        p.getChildren().clear();
        counter1 = 0;
        counter2 = 0;
        counter3 = 0;
        label.setText("Linien: " + counter1 + " Rechtecke: " + counter2 + " Kreise: " + counter3);

    }

    // Protokoll
    public void showProtokoll() {
        popup.show();

        text.clear();
        p.getChildren().forEach(e -> {
            if (e instanceof Rectangle) {
                Rectangle g = (Rectangle) e;
                text.appendText("Rechteck:[" + String.valueOf(g.getX()) + "," + String.valueOf(g.getY()) + "]\n");
            } else if (e instanceof Circle) {
                Circle ci = (Circle) e;
                text.appendText("Kreise:[" + String.valueOf(ci.getCenterX() + String.valueOf(ci.getScaleX()) + "," + "]\n"));
            }

        });
    }

    public static void main(String[] args) {
        launch(args);

    }

}
