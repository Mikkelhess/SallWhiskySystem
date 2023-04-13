package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logik.CompositeDestillat;
import logik.Fad;
import logik.FadHistorik;
import logik.LeafDestillat;

import java.time.LocalDate;

public class FadPane extends GridPane {

    private ListView<Fad> lvwFade;
    private ListView<CompositeDestillat> lvwDestillat;
    private Button btnOpretFad;
    private Button btnFjernFad;
    private Button btnDetaljer;
    private Button btnHistorik;
    private Button btnOmhæld;

    private Button btnTilføj;
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

        Image image = new Image("https://media.licdn.com/dms/image/D4E03AQGCwb2C1UhYJQ/profile-displayphoto-shrink_800_800/0/1666204752005?e=2147483647&v=beta&t=CHOMCJlUcyN1I1l40l_oenz7hJn7Ae_0Pwt5qlGHw18");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(400);
        imageView.setFitHeight(500);
        this.add(imageView, 2, 1);

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

    private void detaljerAction() {
        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        if (fad != null) {
            visDetaljerWindow(fad);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vælg et fad");
            alert.setHeaderText(null);
            alert.setContentText("Vælg et fad du vil se detaljer for");
            alert.showAndWait();
        }
    }

    private static void visDetaljerWindow(Fad fad) {

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

        int rowIndex = 6;
        for (LeafDestillat leafDestillat : fad.getLeafDestillatMap().values()) {
            gridPane.addRow(rowIndex, new Label("Destillat-del " + leafDestillat.getLeafNewMakeNummer() + ", liter: " +
                    leafDestillat.getLiter() + ", alkohol procent: " + leafDestillat.getAlkoholProcent()));
            rowIndex++;
        }

        detailsStage.setScene(new Scene(gridPane, 650, 400));
        detailsStage.setTitle("Fad Detaljer");
        detailsStage.show();
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

        Stage historikStage = new Stage();
        historikStage.initModality(Modality.WINDOW_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        gridPane.addRow(0, new Label("Fad Historik for Fad " + fad.getFadId()));
        gridPane.addRow(1, new Label());

        int rowIndex = 2;

        for (int i = 0; i < fad.getFadHistorikList().size(); i++) {
            FadHistorik fadHistorik = fad.getFadHistorikList().get(i);
            LeafDestillat leafDestillat = fadHistorik.getDestillat();
            String leafNewMakeNummer = leafDestillat.getLeafNewMakeNummer();
            LocalDate tilføjetDato = fadHistorik.getTilføjetDato();
            LocalDate fjernetDato = fadHistorik.getFjernetDato();

            if (tilføjetDato != null) {
                gridPane.addRow(rowIndex, new Label("Destillat " + leafNewMakeNummer +
                        " lagt på fad: " + tilføjetDato));
            } else {
                gridPane.addRow(rowIndex, new Label("Destillat " + leafNewMakeNummer +
                        " omhældt fra fad " + fad.getFadId() + " til fad " + fad.getOmhældtFad().getFadId() + ": " +  fjernetDato));
            }
            rowIndex++;
        }
        historikStage.setScene(new Scene(gridPane, 650, 400));
        historikStage.setTitle("Fad Historik");
        historikStage.show();
    }

    public void updateList() {
        lvwFade.getItems().setAll(Controller.getFadMap().values());
        lvwDestillat.getItems().setAll(Controller.getDestillatMap().values());
    }

}
