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
import javafx.stage.Stage;
import logik.Destillat;
import logik.Fad;
import logik.Hylde;

public class FadPane extends GridPane {

    private ListView<Fad> lvwFade;

    private ListView<Destillat> lvwDestillat;
    private Button btnOpretFad;
    private Button btnFjernFad;
    private Button btnHistorik;

    private Button btnTilføj;

    private Button btnFjernDestillat;

    private HBox btnFadBox, btnDestillatBox;

    private OpretFadWindow opretFadWindow;

    public FadPane() {
        this.setGridLinesVisible(false);
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);

        opretFadWindow = new OpretFadWindow("Opret fad", new Stage());

        lvwFade = new ListView<>();
        this.add(lvwFade, 0, 1, 1, 1);
        lvwFade.setPrefSize(350, 400);
        lvwFade.getItems().setAll(Controller.getFadMap().values());

        lvwDestillat = new ListView<>();
        this.add(lvwDestillat, 1, 1, 1, 1);
        lvwDestillat.setPrefSize(350, 400);
        lvwDestillat.getItems().setAll(Controller.getDestillatMap().values());

        ChangeListener<Fad> listener = (ov, o, n) -> this.selectedFadchanged();
        lvwFade.getSelectionModel().selectedItemProperty().addListener(listener);

        ChangeListener<Destillat> listener2 = (ov, o, n) -> this.selectedFadchanged();
        lvwDestillat.getSelectionModel().selectedItemProperty().addListener(listener2);


        Label lblFade = new Label("Fade");
        this.add(lblFade, 0, 0);
        lblFade.setAlignment(Pos.TOP_LEFT);

        btnFadBox = new HBox();
        this.add(btnFadBox, 0, 4);
        btnFadBox.setSpacing(20);

        btnDestillatBox = new HBox();
        this.add(btnDestillatBox, 1, 4);
        btnDestillatBox.setSpacing(20);


        btnOpretFad = new Button("Opret");
        btnFadBox.getChildren().add(btnOpretFad);
        btnOpretFad.setOnAction(event -> this.opretFadAction());

        btnFjernFad = new Button("Fjern");
        btnFadBox.getChildren().add(btnFjernFad);
        btnFjernFad.setOnAction(event -> this.removeFadAction());

        btnHistorik = new Button("Historik");
        btnFadBox.getChildren().add(btnHistorik);
        btnHistorik.setOnAction(event -> this.historikAction());

        btnTilføj = new Button("Tilføj");
        btnDestillatBox.getChildren().add(btnTilføj);
        btnTilføj.setOnAction(event -> this.tilføjDestillatAction());

        btnFjernDestillat = new Button("Fjern");
        btnDestillatBox.getChildren().add(btnFjernDestillat);
        btnFjernDestillat.setOnAction(event -> this.removeDestillatAction());


    }

    private void removeDestillatAction() {
       Destillat destillat = lvwDestillat.getSelectionModel().getSelectedItem();
        if (destillat != null) {
            Controller.getDestillatMap().values().remove(destillat);
            lvwDestillat.getItems().setAll(Controller.getDestillatMap().values());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vælg et Destillat");
            alert.setHeaderText(null);
            alert.setContentText("Vælg et destillat som du vil fjerne");
            alert.showAndWait();
        }
    }

    private void tilføjDestillatAction() {

    }

    private void opretFadAction() {
        opretFadWindow.showAndWait();
        lvwFade.getItems().setAll(Controller.getFadMap().values());
    }

    private void removeFadAction() {

        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        if (fad != null) {
            Controller.getFadMap().values().remove(fad);
            lvwFade.getItems().setAll(Controller.getFadMap().values());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vælg et fad");
            alert.setHeaderText(null);
            alert.setContentText("Vælg et fad som du vil fjerne");
            alert.showAndWait();
        }
    }

    private void historikAction() {

    }

    public void updateList() {
        lvwFade.getItems().setAll(Controller.getFadMap().values());
    }


    private void selectedFadchanged() {
    }
}
