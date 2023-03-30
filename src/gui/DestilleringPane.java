package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logik.*;

import java.util.List;
import java.util.Optional;

public class DestilleringPane extends GridPane {

    // -------------------------------------------------------------------------
    private ListView<Destillering> lvwDestilleringer;
    private ListView<Destillat> lvwDestillater;
    private Button btnOpretDestillering;
    private Button btnFjernDestillering;
    private Button btnOpretDestillat;
    private Button btnFjernDestillat;

    public DestilleringPane() {
        this.setGridLinesVisible(false);
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);


        Label label = new Label("Destilleringer");
        this.add(label, 0, 0);
        lvwDestilleringer = new ListView<>();
        this.add(lvwDestilleringer, 0, 1, 1, 1);
        lvwDestilleringer.setPrefSize(350, 400);
        lvwDestilleringer.getItems().setAll(Controller.getDestilleringMap().values());

        Label label2 = new Label("Destillater");
        this.add(label2, 1, 0);
        lvwDestillater = new ListView<>();
        this.add(lvwDestillater, 1, 1, 1, 1);
        lvwDestillater.setPrefSize(350, 400);

        ChangeListener<Destillering> listener = (ov, o, n) -> this.selectedDestilleringChanged();
        lvwDestilleringer.getSelectionModel().selectedItemProperty().addListener(listener);

        btnOpretDestillering = new Button("Opret");
        btnOpretDestillering.setOnAction(event -> this.opretDestilleringAction());
        btnFjernDestillering = new Button("Fjern");
        btnFjernDestillering.setOnAction(event -> this.removeDestilleringAction());
        HBox destilleringButtons = new HBox(10, btnOpretDestillering, btnFjernDestillering);
        destilleringButtons.setAlignment(Pos.CENTER);

        btnOpretDestillat = new Button("Opret");
        btnOpretDestillat.setOnAction(event -> this.opretDestillatAction());
        btnFjernDestillat = new Button("Fjern");
        btnFjernDestillat.setOnAction(event -> this.fjernDestillatAction());
        HBox destillatButtons = new HBox(10, btnOpretDestillat, btnFjernDestillat);
        destillatButtons.setAlignment(Pos.CENTER);

        VBox destilleringVBox = new VBox(label, lvwDestilleringer, destilleringButtons);
        VBox destillatVBox = new VBox(label2, lvwDestillater, destillatButtons);

        destilleringVBox.setSpacing(10);
        destillatVBox.setSpacing(10);

        this.add(destilleringVBox, 0, 0);
        this.add(destillatVBox, 1, 0);
    }

    private void fjernDestillatAction() {
    }

    private void opretDestillatAction() {
    }

    private void removeDestilleringAction() {
    }

    private void opretDestilleringAction() {
    }

    private void selectedDestilleringChanged() {
        Destillering destillering = lvwDestilleringer.getSelectionModel().getSelectedItem();
        if (destillering != null) {
            lvwDestillater.getItems().setAll(destillering.getDestillatMap().values());
        } else {
            lvwDestillater.getItems().clear();
        }

    }

    public void updateList() {
        //lvwDestilleringer.getItems().setAll(Controller.getLagerMap().values());
    }

}
