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


public class LagerPane extends GridPane {



    // -------------------------------------------------------------------------
    private ListView<Lager> lvwLagre;
    private ListView<Reol> lvwReoler;

    private ListView<Hylde> lvwHylder;

    private ListView <Fad> lvwFade;
    private Button btnOpretLager, btnOpretReol, btnOpretHylde, btnTilføjFad;
    private Button btnFjernLager, btnFjernReol, btnFjernHylde, btnFjernFad;


    public LagerPane() {
        this.setGridLinesVisible(false);
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);

        lvwLagre = new ListView<>();
        this.add(lvwLagre, 0, 1, 1, 1);
        lvwLagre.setPrefSize(220, 200);
        lvwLagre.getItems().setAll(Controller.getLagerMap().values());

        ChangeListener<Lager> listener = (ov, o, n) -> this.selectedLagerChanged();
        lvwLagre.getSelectionModel().selectedItemProperty().addListener(listener);



        lvwReoler = new ListView<>();
        this.add(lvwReoler, 1, 1, 1, 1);
        lvwReoler.setPrefSize(220, 200);
        lvwReoler.getItems().setAll(Controller.getReolMap().values());

        ChangeListener<Reol> listener2 = (ov, o, n) -> this.selectedReolChanged();
        lvwReoler.getSelectionModel().selectedItemProperty().addListener(listener2);

        lvwHylder = new ListView<>();
        this.add(lvwHylder, 0, 3, 1, 1);
        lvwHylder.setPrefSize(220, 200);
        lvwHylder.getItems().setAll(Controller.getHyldeMap().values());

        ChangeListener<Hylde> listener3 = (ov, o, n) -> this.selectedHyldeChanged();
        lvwHylder.getSelectionModel().selectedItemProperty().addListener(listener3);

        lvwFade = new ListView<>();
        this.add(lvwFade, 1, 3, 1, 1);
        lvwFade.setPrefSize(220, 200);
        lvwFade.getItems().setAll(Controller.getFadMap().values());

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
        btnFjernReol = new Button("Fjern");
        VBox reolButtons = new VBox(10, btnOpretReol, btnFjernReol);
        reolButtons.setAlignment(Pos.CENTER);

        btnOpretHylde = new Button("Opret");
        btnFjernHylde = new Button("Fjern");
        VBox hyldeButtons = new VBox(10, btnOpretHylde, btnFjernHylde);
        hyldeButtons.setAlignment(Pos.CENTER);

        btnTilføjFad = new Button("Tilføj");
        btnFjernFad = new Button("Fjern");
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

    private void opretLagerAction() {
        Controller.opretLager();
        lvwLagre.getItems().setAll(Controller.getLagerMap().values());
    }
    private void removeLagerAction() {
        Lager lager = lvwLagre.getSelectionModel().getSelectedItem();
        Controller.removeLager(lager);
        lvwLagre.getItems().setAll(Controller.getLagerMap().values());
    }

    private void selectedFadChanged() {
    }

    private void selectedHyldeChanged() {
    }

    public void updateList() {
        lvwLagre.getItems().setAll(Controller.getLagerMap().values());
    }

    private void filAction() {
        Reol selectedReol = lvwReoler.getSelectionModel().getSelectedItem();
        if (selectedReol != null) {
        }
    }

    private void opretAction() {
    }

    //skal opdatere hylde list view (se konference)
    private void selectedLagerChanged() {
      Lager lager = lvwLagre.getSelectionModel().getSelectedItem();
    }
    private void selectedReolChanged() {
       Reol selectedReolChanged = lvwReoler.getSelectionModel().getSelectedItem();

    }
}
