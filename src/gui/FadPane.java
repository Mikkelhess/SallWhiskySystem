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
import logik.LeafDestillat;

public class FadPane extends GridPane {

    private ListView<Fad> lvwFade;
    private ListView<CompositeDestillat> lvwDestillat;
    private Button btnOpretFad;
    private Button btnFjernFad;
    private Button btnDetaljer;
    private Button btnHistorik;
    private Button btnOmhæld;

    private Button btnTilføj;

    private Button btnFjernDestillat;
    private HBox FadHBox, destillatHBox;

    private OpretFadWindow opretFadWindow;
    private TilføjDestillatWindow tilføjDestillatWindow;
    private OmhældDestillatTilFadWindow1 omhældDestillatTilFadWindow1;



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
        destillatHBox.setAlignment(Pos.CENTER);


        btnOpretFad = new Button("Opret");
        FadHBox.getChildren().add(btnOpretFad);
        btnOpretFad.setOnAction(event -> this.opretFadAction());

        btnFjernFad = new Button("Fjern");
        FadHBox.getChildren().add(btnFjernFad);
        btnFjernFad.setOnAction(event -> this.removeFadAction());

        btnDetaljer = new Button("Detaljer");
        FadHBox.getChildren().add(btnDetaljer);
        btnDetaljer.setOnAction(event -> this.detaljerAction());

        btnHistorik = new Button("Historik");
        FadHBox.getChildren().add(btnHistorik);
        btnHistorik.setOnAction(event -> this.historikAction());

        btnOmhæld = new Button("Omhæld");
        FadHBox.getChildren().add(btnOmhæld);
        btnOmhæld.setOnAction(event -> this.omhældAction());

        btnTilføj = new Button("Tilføj Destillat til Fad");
        destillatHBox.getChildren().add(btnTilføj);
        btnTilføj.setOnAction(event -> this.tilføjDestillatAction());

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

    private void omhældAction() {
        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        if (fad != null) {
            omhældDestillatTilFadWindow1 = new OmhældDestillatTilFadWindow1("Omhæld Destillat", new Stage(), fad);
            omhældDestillatTilFadWindow1.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vælg et fad");
            alert.setHeaderText(null);
            alert.setContentText("Vælg et fad som du vil omhælde");
            alert.showAndWait();
        }

    }

    //måske detaljer og historik???
    private void detaljerAction() {
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

    //vis nuværende destillater med info, derudover vis al historik for fadet.
    private void historikAction() {

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

        gridPane.addRow(0, new Label("Fad " + fad.getFadId()));
        gridPane.addRow(1, new Label(fad.getFadLiter() + " liter i fadet."));
        gridPane.addRow(2, new Label("Fad type: " + fad.getFadType()));
        gridPane.addRow(3, new Label("Leverandør: " + fad.getLeverandør()));
        gridPane.addRow(4, new Separator());

        gridPane.addRow(5, new Label("Destillater på fadet"));

        //omhældning og lagring skal fixes
        int rowIndex = 6;
        for (LeafDestillat leafDestillat : fad.getLeafDestillatMap().values()) {
            gridPane.addRow(rowIndex, new Label("Destillat-del " + leafDestillat.getLeafNewMakeNummer() + ", liter: " +
                    leafDestillat.getLiter() + ", alkohol procent: " + leafDestillat.getAlkoholProcent() + ", Lagrings Dato: " + leafDestillat.getLagringsDato() + ", Omhældningsdato: " + leafDestillat.getOmhældningsDato()));
            rowIndex++;
        }

        detailsStage.setScene(new Scene(gridPane, 650, 400));
        detailsStage.setTitle("Fad Detaljer");
        detailsStage.show();
    }


    public void updateList() {
        lvwFade.getItems().setAll(Controller.getFadMap().values());
        lvwDestillat.getItems().setAll(Controller.getDestillatMap().values());
    }

}
