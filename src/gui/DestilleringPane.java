package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logik.*;

import java.util.Optional;

public class DestilleringPane extends GridPane {

    // -------------------------------------------------------------------------
    private ListView<Destillering> lvwDestilleringer;
    private ListView<CompositeDestillat> lvwCompositeDestillater;
    private ListView<LeafDestillat> lvwLeafDestillater;
    private Button btnOpretDestillering;
    private Button btnFjernDestillering;
    private Button btnOpretDestillat;
    private Button btnFjernDestillat;
    private Button btnVisDetaljer;
    private Button btnOpretDel;
    private Button btnOpdaterDel;
    private OpretDestilleringWindow opretDestilleringWindow;
    private OpretDestillatWindow opretDestillatWindow;

    public DestilleringPane() {
        this.setGridLinesVisible(false);
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);

        opretDestilleringWindow = new OpretDestilleringWindow("Opret Destillering", new Stage());

        Label label = new Label("Destilleringer");
        this.add(label, 0, 0);
        lvwDestilleringer = new ListView<>();
        this.add(lvwDestilleringer, 0, 1, 1, 1);
        lvwDestilleringer.setPrefSize(350, 400);
        lvwDestilleringer.getItems().setAll(Controller.getDestilleringMap().values());

        Label label2 = new Label("Over-Destillater");
        this.add(label2, 1, 0);
        lvwCompositeDestillater = new ListView<>();
        this.add(lvwCompositeDestillater, 1, 1, 1, 1);
        lvwCompositeDestillater.setPrefSize(350, 400);

        Label label3 = new Label("Del-Destillater");
        this.add(label3, 2, 0);
        lvwLeafDestillater = new ListView<>();
        this.add(lvwLeafDestillater, 2, 1, 1, 1);
        lvwLeafDestillater.setPrefSize(350, 400);

        ChangeListener<Destillering> listener = (ov, o, n) -> this.selectedDestilleringChanged();
        lvwDestilleringer.getSelectionModel().selectedItemProperty().addListener(listener);

        btnOpretDestillering = new Button("Opret");
        btnOpretDestillering.setOnAction(event -> this.opretDestilleringAction());
        btnFjernDestillering = new Button("Fjern");
        btnFjernDestillering.setOnAction(event -> this.removeDestilleringAction());
        btnVisDetaljer = new Button("Vis Detaljer");
        btnVisDetaljer.setOnAction(event -> this.visDetaljerAction());
        HBox destilleringButtons = new HBox(10, btnOpretDestillering, btnFjernDestillering, btnVisDetaljer);
        destilleringButtons.setAlignment(Pos.CENTER);

        btnOpretDestillat = new Button("Opret");
        btnOpretDestillat.setOnAction(event -> this.opretDestillatAction());
        btnFjernDestillat = new Button("Fjern");
        btnFjernDestillat.setOnAction(event -> this.fjernCompositeDestillatAction());
        HBox destillatButtons = new HBox(10, btnOpretDestillat, btnFjernDestillat);
        destillatButtons.setAlignment(Pos.CENTER);

        btnOpretDel = new Button("Opret dele");
        btnOpretDel.setOnAction(event -> this.opretDeleAction());
        btnOpdaterDel = new Button("Opdater dele");
        btnOpdaterDel.setOnAction(event -> this.opdaterDeleAction());
        HBox delButtons = new HBox(10, btnOpretDel, btnOpdaterDel);
        delButtons.setAlignment(Pos.CENTER);

        VBox destilleringVBox = new VBox(label, lvwDestilleringer, destilleringButtons);
        VBox destillatVBox = new VBox(label2, lvwCompositeDestillater, destillatButtons);
        VBox delVBox = new VBox(label3, lvwLeafDestillater, delButtons);

        destilleringVBox.setSpacing(10);
        destillatVBox.setSpacing(10);
        delVBox.setSpacing(10);

        this.add(destilleringVBox, 0, 0);
        this.add(destillatVBox, 1, 0);
        this.add(delVBox, 2, 0);
    }




    private void opretDestilleringAction() {
        opretDestilleringWindow.showAndWait();
        lvwDestilleringer.getItems().setAll(Controller.getDestilleringMap().values());
    }

    private void removeDestilleringAction() {
        Destillering destillering = lvwDestilleringer.getSelectionModel().getSelectedItem();
        if (destillering != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Fjern Destillering");
            alert.setHeaderText(null);
            alert.setContentText("Er du sikker på, at du vil fjerne destilleringen? Dette vil også fjerne dens indhold.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Controller.removeDestillering(destillering);
                lvwDestilleringer.getItems().setAll(Controller.getDestilleringMap().values());
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vælg en Destillering");
            alert.setHeaderText(null);
            alert.setContentText("Vælg en destillering som du vil fjerne");
            alert.showAndWait();
        }
    }

    private void visDetaljerAction() {
        Destillering destillering = lvwDestilleringer.getSelectionModel().getSelectedItem();
        if (destillering != null) {
            visDetaljerWindow(destillering);
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vælg en Destillering");
            alert.setHeaderText(null);
            alert.setContentText("Vælg en destillering som du vil se detaljer for");
            alert.showAndWait();
        }


    }

    private void fjernCompositeDestillatAction() {
        Destillering destillering = lvwDestilleringer.getSelectionModel().getSelectedItem();
        CompositeDestillat compositeDestillat = lvwCompositeDestillater.getSelectionModel().getSelectedItem();

        if (destillering == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vælg en destillering");
            alert.setHeaderText(null);
            alert.setContentText("Vælg en destillering først");
            alert.showAndWait();
            return;
        }

        if (compositeDestillat != null) {
            destillering.removeDestillat(compositeDestillat.getNewMakeNummer());
            destillering.udregnLiter();
            lvwDestilleringer.getItems().setAll(Controller.getDestilleringMap().values());
            lvwCompositeDestillater.getItems().setAll(destillering.getDestillatMap().values());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vælg et destillat");
            alert.setHeaderText(null);
            alert.setContentText("Vælg et destillat som du vil fjerne");
            alert.showAndWait();
        }
    }


    private void opretDestillatAction() {
        Destillering destillering = lvwDestilleringer.getSelectionModel().getSelectedItem();
        if (destillering != null) {
            OpretDestillatWindow opretDestillatWindow = new OpretDestillatWindow("Opret Destillat",new Stage(),destillering);
            opretDestillatWindow.showAndWait();
            lvwDestilleringer.getItems().setAll(Controller.getDestilleringMap().values());
            lvwCompositeDestillater.getItems().setAll(destillering.getDestillatMap().values());
        }
    }

    private void opretDeleAction() {

    }

    private void opdaterDeleAction() {

    }

    private void selectedDestilleringChanged() {
        Destillering destillering = lvwDestilleringer.getSelectionModel().getSelectedItem();
        if (destillering != null) {
            lvwCompositeDestillater.getItems().setAll(destillering.getDestillatMap().values());
        } else {
            lvwCompositeDestillater.getItems().clear();
        }

    }

    private void visDetaljerWindow(Destillering destillering) {
        Stage detailsStage = new Stage();
        detailsStage.initModality(Modality.WINDOW_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Add labels and data for each attribute using destillering object
        gridPane.addRow(0, new Label("Medarbejder: "), new Label(destillering.getMedarbejderNavn()));
        gridPane.addRow(1, new Label("Start Dato: "), new Label(destillering.getStartDato().toString()));
        gridPane.addRow(2, new Label("Slut Dato: "), new Label(destillering.getSlutDato().toString()));
        gridPane.addRow(3, new Label("Malt batch: "), new Label(destillering.getMaltBatch()));
        gridPane.addRow(4, new Label("Kornsort: "), new Label(destillering.getKornsort()));
        gridPane.addRow(5, new Label("Kapacitet: "), new Label(destillering.udregnLiter() + " ud af " + destillering.getTotalLiter() + " liter"));
        gridPane.addRow(6, new Label("Ryge materiale: "), new Label(destillering.getRygeMateriale()));
        gridPane.addRow(7, new Label("Kommentar: "), new Label(destillering.getKommentar()));

        detailsStage.setScene(new Scene(gridPane, 400, 300));
        detailsStage.setTitle("Destillering Detaljer");
        detailsStage.show();
    }

}
