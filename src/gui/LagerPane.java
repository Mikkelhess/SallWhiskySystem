package gui;


import controller.Controller;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import logik.Fad;
import logik.Hylde;
import logik.Lager;
import logik.Reol;

import java.util.Optional;


public class LagerPane extends GridPane {



    // -------------------------------------------------------------------------
    private ListView<Lager> lvwLagre;
    private ListView<Reol> lvwReoler;

    private ListView<Hylde> lvwHylder;

    private ListView <Fad> lvwFade;
    private Button btnOpretLager, btnOpretReol, btnOpretHylde, btnTilføjFad;
    private Button btnFjernLager, btnFjernReol, btnFjernHylde, btnFjernFad;
    private TilføjFadWindow tilføjFadWindow;



    public LagerPane() {
        this.setGridLinesVisible(false);
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);

        tilføjFadWindow = new TilføjFadWindow("Tilføj Fad", new Stage());

        lvwLagre = new ListView<>();
        this.add(lvwLagre, 0, 1, 1, 1);
        lvwLagre.setPrefSize(220, 200);
        lvwLagre.getItems().setAll(Controller.getLagerMap().values());

        ChangeListener<Lager> listener = (ov, o, n) -> this.selectedLagerChanged();
        lvwLagre.getSelectionModel().selectedItemProperty().addListener(listener);


        lvwReoler = new ListView<>();
        this.add(lvwReoler, 1, 1, 1, 1);
        lvwReoler.setPrefSize(220, 200);

        ChangeListener<Reol> listener2 = (ov, o, n) -> this.selectedReolChanged();
        lvwReoler.getSelectionModel().selectedItemProperty().addListener(listener2);

        lvwHylder = new ListView<>();
        this.add(lvwHylder, 0, 3, 1, 1);
        lvwHylder.setPrefSize(220, 200);


        ChangeListener<Hylde> listener3 = (ov, o, n) -> this.selectedHyldeChanged();
        lvwHylder.getSelectionModel().selectedItemProperty().addListener(listener3);

        lvwFade = new ListView<>();
        this.add(lvwFade, 1, 3, 1, 1);
        lvwFade.setPrefSize(220, 200);


        ChangeListener<Fad> listener4 = (ov, o, n) -> this.selectedFadChanged();
        lvwFade.getSelectionModel().selectedItemProperty().addListener(listener4);

        Label lblLagre = new Label("Lagre");
        this.add(lblLagre, 0, 0);

        Label lblReoler = new Label("Reoler");
        this.add(lblReoler, 1, 0);

        Label lblHylder = new Label("Hylder");
        this.add(lblHylder, 0, 2);

        Label lblFade = new Label("Fade ");
        this.add(lblFade, 1, 2);

        btnOpretLager = new Button("Opret");
        btnOpretLager.setOnAction(event -> this.opretLagerAction());
        btnFjernLager = new Button("Fjern");
        btnFjernLager.setOnAction(event -> this.removeLagerAction());
        VBox lagerButtons = new VBox(10, btnOpretLager, btnFjernLager);
        lagerButtons.setAlignment(Pos.CENTER);

        btnOpretReol = new Button("Opret");
        btnOpretReol.setOnAction(event -> this.opretReolAction());
        btnFjernReol = new Button("Fjern");
        btnFjernReol.setOnAction(event -> this.removeReolAction());
        VBox reolButtons = new VBox(10, btnOpretReol, btnFjernReol);
        reolButtons.setAlignment(Pos.CENTER);

        btnOpretHylde = new Button("Opret");
        btnOpretHylde.setOnAction(event -> this.opretHyldeAction());
        btnFjernHylde = new Button("Fjern");
        btnFjernHylde.setOnAction(event -> this.removeHyldeAction());
        VBox hyldeButtons = new VBox(10, btnOpretHylde, btnFjernHylde);
        hyldeButtons.setAlignment(Pos.CENTER);

        btnTilføjFad = new Button("Tilføj");
        btnTilføjFad.setOnAction(event -> tilføjFadAction());
        btnFjernFad = new Button("Fjern");
        btnFjernFad.setOnAction(event -> removeFadAction());
        VBox fadButtons = new VBox(10, btnTilføjFad, btnFjernFad);
        fadButtons.setAlignment(Pos.CENTER);

        HBox lagerHBox = new HBox(lblLagre, lvwLagre, lagerButtons);
        HBox reolHBox = new HBox(lblReoler, lvwReoler, reolButtons);
        HBox hyldeHBox = new HBox(lblHylder, lvwHylder, hyldeButtons);
        HBox fadHBox = new HBox(lblFade, lvwFade, fadButtons);

        lagerHBox.setSpacing(10);
        reolHBox.setSpacing(10);
        hyldeHBox.setSpacing(10);
        fadHBox.setSpacing(10);

        this.add(lagerHBox, 0, 0);
        this.add(reolHBox, 1, 0);
        this.add(hyldeHBox, 0, 1);
        this.add(fadHBox, 1, 1);
    }

    private void selectedFadChanged() {
    }

    private void opretLagerAction() {
        Controller.opretLager();
        lvwLagre.getItems().setAll(Controller.getLagerMap().values());
    }

    private void removeLagerAction() {
        Lager lager = lvwLagre.getSelectionModel().getSelectedItem();
        if (lager != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Fjern reol");
            alert.setHeaderText(null);
            alert.setContentText("Er du sikker på, at du vil fjerne lageret? Dette vil også fjerne alle reoler på lageret.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Controller.removeLager(lager);
                lvwLagre.getItems().setAll(Controller.getLagerMap().values());
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vælg et Lager");
            alert.setHeaderText(null);
            alert.setContentText("Vælg et lager som du vil fjerne");
            alert.showAndWait();
        }
    }

    private void opretReolAction() {
        Lager lager = lvwLagre.getSelectionModel().getSelectedItem();
        if (lager != null) {
            Controller.opretReol(lager);
            lvwReoler.getItems().setAll(lager.getReolMap().values());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vælg et lager");
            alert.setHeaderText(null);
            alert.setContentText("Vælg et lager først, hvor reolen skal oprettes");
            alert.showAndWait();
        }
    }
    private void removeReolAction() {
        Lager lager = lvwLagre.getSelectionModel().getSelectedItem();
        Reol reol = lvwReoler.getSelectionModel().getSelectedItem();
        if (reol != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Fjern reol");
            alert.setHeaderText(null);
            alert.setContentText("Er du sikker på, at du vil fjerne reolen? Dette vil også fjerne alle hylder i reolen.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Controller.removeReol(lager.getLagerId(), reol.getReolId());
                lvwReoler.getItems().setAll(lager.getReolMap().values());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vælg en reol");
            alert.setHeaderText(null);
            alert.setContentText("Vælg en reol som du vil fjerne");
            alert.showAndWait();
        }
    }

    private void opretHyldeAction() {
        Reol reol = lvwReoler.getSelectionModel().getSelectedItem();
        if (reol != null) {
            Controller.opretHylde(reol);
            lvwHylder.getItems().setAll(reol.getHyldeMap().values());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vælg en reol");
            alert.setHeaderText(null);
            alert.setContentText("Vælg en reol først, hvor hylden skal oprettes");
            alert.showAndWait();
        }
    }

    private void removeHyldeAction() {
        Lager lager = lvwLagre.getSelectionModel().getSelectedItem();
        Reol reol = lvwReoler.getSelectionModel().getSelectedItem();
        Hylde hylde = lvwHylder.getSelectionModel().getSelectedItem();
        if (hylde != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Fjern hylde");
            alert.setHeaderText(null);
            //fjerner den alle fad helt eller bare fra hylden!!!
            alert.setContentText("Er du sikker på, at du vil fjerne hylden?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Controller.removeHylde(lager.getLagerId(), reol.getReolId(), hylde.getHyldeId());
                lvwHylder.getItems().setAll(reol.getHyldeMap().values());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vælg en hylde");
            alert.setHeaderText(null);
            alert.setContentText("Vælg en hylde som du vil fjerne");
            alert.showAndWait();
        }
    }

    //skal åbne et vindue hvor man vælger et fad som ikke allerede er på en hylde, og som man så tilføjer.
    //skal man kunne flytte et fad fra en hylde til en anden? i guess man bare kan gøre det via 2 steps
    //skal kunne tilføjere mere end et fad af gangen. måske 2 list views, et med alle fad og et med dem man vil tilføje
    //to listviews, pil til højre og venstre.
    private void tilføjFadAction() {
            Hylde hylde = lvwHylder.getSelectionModel().getSelectedItem();
            tilføjFadWindow.showAndWait();
            lvwFade.getItems().setAll(hylde.getFadPåHyldeMap().values());

    }

    private void removeFadAction() {
        Lager lager = lvwLagre.getSelectionModel().getSelectedItem();
        Reol reol = lvwReoler.getSelectionModel().getSelectedItem();
        Hylde hylde = lvwHylder.getSelectionModel().getSelectedItem();
        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        if (fad != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Fjern fad");
            alert.setHeaderText(null);
            alert.setContentText("Er du sikker på, at du vil fjerne fadet?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Controller.removeFad(lager.getLagerId(), reol.getReolId(), hylde.getHyldeId(), fad.getFadId());
                lvwFade.getItems().setAll(hylde.getFadPåHyldeMap().values());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vælg et fad");
            alert.setHeaderText(null);
            alert.setContentText("Vælg et fad som du vil fjerne");
            alert.showAndWait();
        }
    }







    public void updateList() {
        lvwLagre.getItems().setAll(Controller.getLagerMap().values());
    }


    //skal opdatere hylde list view (se konference)
    private void selectedLagerChanged() {
      Lager lager = lvwLagre.getSelectionModel().getSelectedItem();
        if (lager != null) {
            lvwReoler.getItems().setAll(lager.getReolMap().values());
        } else {
            lvwReoler.getItems().clear();
        }
    }
    private void selectedReolChanged() {
       Reol reol = lvwReoler.getSelectionModel().getSelectedItem();
       if (reol != null) {
           lvwHylder.getItems().setAll(reol.getHyldeMap().values());
       } else {
           lvwHylder.getItems().clear();
       }

    }

    private void selectedHyldeChanged() {

        Hylde hylde = lvwHylder.getSelectionModel().getSelectedItem();
        if (hylde != null) {
            lvwFade.getItems().setAll(hylde.getFadPåHyldeMap().values());
        } else {
            lvwFade.getItems().clear();
        }

    }

}
