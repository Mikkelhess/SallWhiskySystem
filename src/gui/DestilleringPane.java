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
