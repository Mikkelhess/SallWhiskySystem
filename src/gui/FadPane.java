package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logik.Fad;
import logik.Lager;
import logik.Reol;

public class FadPane extends GridPane {

    private ListView<Fad> lvwFade;
    private Button  btnOpretFad;
    private Button btnFjernFad;

    public FadPane() {
        this.setGridLinesVisible(false);
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);

        lvwFade = new ListView<>();
        this.add(lvwFade, 1, 1, 1, 1);
        lvwFade.setPrefSize(350, 200);
        lvwFade.getItems().setAll(Controller.getFadMap().values());

        ChangeListener<Fad> listener = (ov, o, n) -> this.selectedFadchanged();
        lvwFade.getSelectionModel().selectedItemProperty().addListener(listener);


        Label lblFade = new Label("Fade");
        this.add(lblFade, 0, 0);

        btnOpretFad = new Button("Opret");
        btnOpretFad.setOnAction(event -> this.opretFadAction());
        btnFjernFad = new Button("Fjern");
        btnFjernFad.setOnAction(event -> this.removeFadAction());
        VBox fadButtons = new VBox(10, btnOpretFad, btnFjernFad);
        fadButtons.setAlignment(Pos.CENTER);


        HBox lagerHBox = new HBox(lblFade, lvwFade, fadButtons);

        lagerHBox.setSpacing(10);

        this.add(lagerHBox, 0, 0);

    }

    private void opretFadAction() {
        Controller.opretLager();
        lvwFade.getItems().setAll(Controller.getFadMap().values());
    }
    private void removeFadAction() {
        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        if (fad != null) {
            Controller.removeFadFraHylde(this,this,this,this);
            lvwFade.getItems().setAll(Controller.getFadMap().values());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vælg et fad");
            alert.setHeaderText(null);
            alert.setContentText("Vælg et fad som du vil fjerne");
            alert.showAndWait();
        }
    }

    private void selectedFadChanged() {
    }

    private void selectedHyldeChanged() {
    }

    public void updateList() {
        lvwFade.getItems().setAll(Controller.getFadMap().values());
    }


    //skal opdatere hylde list view (se konference)
    private void selectedFadchanged() {
    }
}
