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
import logik.Fad;

public class FadPane extends GridPane {

    private ListView<Fad> lvwFade;
    private Button btnOpretFad;
    private Button btnFjernFad;
    private Button btnHistorik;

    private HBox btnFadBox, btnServBox;

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

        ChangeListener<Fad> listener = (ov, o, n) -> this.selectedFadchanged();
        lvwFade.getSelectionModel().selectedItemProperty().addListener(listener);


        Label lblFade = new Label("Fade");
        this.add(lblFade, 0, 0);
        lblFade.setAlignment(Pos.TOP_LEFT);

        btnFadBox = new HBox();
        this.add(btnFadBox, 0, 4);
        btnFadBox.setSpacing(20);


        btnOpretFad = new Button("Opret");
        btnFadBox.getChildren().add(btnOpretFad);
        btnOpretFad.setOnAction(event -> this.opretFadAction());

        btnFjernFad = new Button("Fjern");
        btnFadBox.getChildren().add(btnFjernFad);
        btnFjernFad.setOnAction(event -> this.removeFadAction());

        btnHistorik = new Button("Historik");
        btnFadBox.getChildren().add(btnHistorik);
        btnHistorik.setOnAction(event -> this.historikAction());

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
