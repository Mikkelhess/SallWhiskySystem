package gui;

import controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import logik.Destillat;
import logik.Destillering;
import logik.Fad;

public class FadPane extends GridPane {

    private ListView<Fad> lvwFade;

    private ListView<Destillat> lvwDestillat;
    private Button btnOpretFad;
    private Button btnFjernFad;
    private Button btnHistorik;

    private Button btnTilføj;

    private Button btnFjernDestillat;
    private TextField txfTilføjLiter = new TextField();

    private HBox FadHBox, destillatHBox;

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



        Label lblFade = new Label("Fade");
        this.add(lblFade, 0, 0);
        lblFade.setAlignment(Pos.TOP_LEFT);

        Label lblDestillater = new Label("Destillater");
        this.add(lblDestillater, 1, 0);

        FadHBox = new HBox();
        this.add(FadHBox, 0, 4);
        FadHBox.setSpacing(20);

        destillatHBox = new HBox();
        this.add(destillatHBox, 1, 4);
        destillatHBox.setSpacing(20);


        btnOpretFad = new Button("Opret");
        FadHBox.getChildren().add(btnOpretFad);
        btnOpretFad.setOnAction(event -> this.opretFadAction());

        btnFjernFad = new Button("Fjern");
        FadHBox.getChildren().add(btnFjernFad);
        btnFjernFad.setOnAction(event -> this.removeFadAction());

        btnHistorik = new Button("Historik");
        FadHBox.getChildren().add(btnHistorik);
        btnHistorik.setOnAction(event -> this.historikAction());

        btnTilføj = new Button("Tilføj Destillat til Fad");
        destillatHBox.getChildren().add(btnTilføj);
        btnTilføj.setOnAction(event -> this.tilføjDestillatAction());

        txfTilføjLiter.setPromptText("Tilføj Liter");
        destillatHBox.getChildren().add(txfTilføjLiter);



    }


    private void tilføjDestillatAction() {
        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        Destillat destillat = lvwDestillat.getSelectionModel().getSelectedItem();

        if (fad == null || destillat == null) {
            showAlert("Fejl", "Vælg venligst et fad og et destillat");
            return;
        }

        fad.addDestillat(destillat.getNewMakeNummer(), destillat);
        String liter = txfTilføjLiter.getText();
        double tilføjLiter = Double.parseDouble(liter);
        if (destillat.getCurrentLiter() - tilføjLiter < 0) {
            showAlert("Tilføj Destillat","Du prøver at tilføje mere væske end der er tilbage i destillatet");
            return;
        }
        destillat.hældPåFad(tilføjLiter);
        lvwDestillat.getItems().setAll(Controller.getDestillatMap().values());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        if (fad != null) {
            visHistorikWindow(fad);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vælg et fad");
            alert.setHeaderText(null);
            alert.setContentText("Vælg et fad du vil se historik for");
            alert.showAndWait();
        }



    }

    private void visHistorikWindow(Fad fad) {
        Stage detailsStage = new Stage();
        detailsStage.initModality(Modality.WINDOW_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        int rowIndex = 0;

        for (Destillat destillat : fad.getDestillatMap().values()) {

            //Label addedDateLabel = new Label("Added to Fad:");
            //Label removedDateLabel = new Label("Removed from Fad:");

            //Label addedDateValueLabel = new Label(destillat.getAddedDate());
            //Label removedDateValueLabel = new Label(destillat.getRemovedDate());

            gridPane.addRow(rowIndex++,  new Label(destillat.getCurrentLiter() + " liter tilføjet fra destillat " + destillat.getNewMakeNummer() ));
            //gridPane.addRow(rowIndex++, addedDateLabel, addedDateValueLabel);
            //gridPane.addRow(rowIndex++, removedDateLabel, removedDateValueLabel);
            gridPane.addRow(rowIndex++, new Separator(Orientation.HORIZONTAL));
        }

        detailsStage.setScene(new Scene(gridPane, 400, 300));
        detailsStage.setTitle("Fad Historik");
        detailsStage.show();
    }


    public void updateList() {
        lvwFade.getItems().setAll(Controller.getFadMap().values());
    }

}
