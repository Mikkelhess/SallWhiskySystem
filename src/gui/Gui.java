package gui;


import controller.Controller;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logik.Fad;
import logik.Hylde;
import logik.Lager;
import logik.Reol;


public class Gui extends Application {

    @Override
    public void init() {
        Controller.initStorage();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Sall Whisky Distillery");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    // -------------------------------------------------------------------------
    private ListView<Lager> lvwLager;
    private ListView<Reol> lvwReoler;

    private ListView<Hylde> lvwHylder;

    private ListView <Fad> lvwFade;


    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);

        lvwLager = new ListView<>();
        pane.add(lvwLager, 0, 1,1,5);
        lvwLager.setPrefSize(220, 200);
        lvwLager.getItems().setAll(Controller.getLager()/);

        ChangeListener<Lager> listener = (ov, o, n) -> this.selectedLagerChanged();
        lvwLager.getSelectionModel().selectedItemProperty().addListener(listener);

        lvwReoler = new ListView<>();
        pane.add(lvwReoler, 1, 1,1,5);
        lvwReoler.setPrefSize(220, 200);
        lvwReoler.getItems().setAll(Controller.getReol());

        ChangeListener<Reol> listener1 = (ov, o, n) -> this.selectedReolChanged();
        lvwReoler.getSelectionModel().selectedItemProperty().addListener(listener1);


        Label lblAktiveAnnoncer = new Label("Lager");
        pane.add(lblAktiveAnnoncer, 0, 0);

        Label lblVarer = new Label("Hylder");
        pane.add(lblVarer, 1, 0);


        Button btnOpretLager = new Button("Opret");
        pane.add(btnOpretLager, 3, 3);
        btnOpretLager.setOnAction(event -> this.opretAction());

        Button btnFjernLager = new Button("Fjern");
        pane.add(btnFjernLager, 3, 7);
        btnFjernLager.setOnAction(event -> this.filAction());

    }


    private void filAction() {
        Reol selectedReol = lvwReoler.getSelectionModel().getSelectedItem();
        if (selectedReol != null) {
        }
    }

    private void opretAction() {
    }

    private void selectedLagerChanged() {
      Lager lager = lvwLager.getSelectionModel().getSelectedItem();
    }
    private void selectedReolChanged() {
       Reol selectedReolChanged = lvwReoler.getSelectionModel().getSelectedItem();

    }
}
