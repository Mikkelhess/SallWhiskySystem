package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logik.CompositeDestillat;
import logik.Fad;
import logik.Hylde;
import logik.LeafDestillat;

import javax.xml.transform.Source;

public class FadPane extends GridPane {

    private ListView<Fad> lvwFade;

    private ListView<CompositeDestillat> lvwDestillat;
    private Button btnOpretFad;
    private Button btnFjernFad;
    private Button btnHistorik;

    private Button btnTilføj;

    private Button btnFjernDestillat;
    //private TextField txfTilføjLiter = new TextField();

    private HBox FadHBox, destillatHBox;

    private OpretFadWindow opretFadWindow;
    private TilføjDestillatWindow tilføjDestillatWindow;



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
        /*
        txfTilføjLiter.setPromptText("Tilføj Liter");
        destillatHBox.getChildren().add(txfTilføjLiter);
         */



    }


    //TODO Check om der er plads på fadet til destillaterne
    private void tilføjDestillatAction() {
        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        CompositeDestillat compositeDestillat = lvwDestillat.getSelectionModel().getSelectedItem();
        if (fad != null && compositeDestillat != null) {
            tilføjDestillatWindow = new TilføjDestillatWindow("Tilføj Destillater", new Stage(), compositeDestillat, fad);
            tilføjDestillatWindow.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vælg et destillat");
            alert.setHeaderText(null);
            alert.setContentText("Vælg et destillat som du vil tilføje til fadet");
            alert.showAndWait();
        }
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

        for (LeafDestillat leafDestillat : fad.getLeafDestillatMap().values()) {
            System.out.println(leafDestillat);
        }

        Stage detailsStage = new Stage();
        detailsStage.initModality(Modality.WINDOW_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        int rowIndex = 1;
        gridPane.addRow(0, new Label("laves færdig i version 1.3..."));
        for (CompositeDestillat compositeDestillat : fad.getDestillatMap().values()) {

            gridPane.addRow(rowIndex++, new Label(compositeDestillat.toString()));

        }
        /*
        int rowIndex = 0;
        gridPane.addRow(rowIndex, new Label());
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

         */

        detailsStage.setScene(new Scene(gridPane, 400, 300));
        detailsStage.setTitle("Fad Historik");
        detailsStage.show();
    }


    public void updateList() {
        lvwFade.getItems().setAll(Controller.getFadMap().values());
        lvwDestillat.getItems().setAll(Controller.getDestillatMap().values());
    }

}
