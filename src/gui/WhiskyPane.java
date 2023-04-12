package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logik.CompositeDestillat;
import logik.Destillering;
import logik.LeafDestillat;
import logik.WhiskyProdukt;

public class WhiskyPane extends GridPane {

    private ListView<WhiskyProdukt> lvwWhisky;
    private Button btnOpretWhisky;
    private Button btnFjernWhisky;
    private Button btnHistorik;
    private Button btnDetaljer;
    private HBox FadHBox;
    private OpretWhiskyWindow1 opretWhiskyWindow1;

    public WhiskyPane() {
        this.setGridLinesVisible(false);
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);

        opretWhiskyWindow1 = new OpretWhiskyWindow1("Opret Whisky", new Stage());

        Label lblWhisky = new Label("Whisky");
        this.add(lblWhisky, 0, 0);
        lblWhisky.setAlignment(Pos.TOP_LEFT);

        lvwWhisky = new ListView<>();
        this.add(lvwWhisky, 0, 1, 1, 1);
        lvwWhisky.setPrefSize(400, 400);
        lvwWhisky.getItems().setAll(Controller.getWhiskyMap().values());

        btnOpretWhisky = new Button("Opret Whisky");
        btnOpretWhisky.setOnAction(event -> opretWhiskyAction());
        btnFjernWhisky = new Button("Fjern Whisky");
        btnFjernWhisky.setOnAction(event -> fjernWhiskyAction());
        btnDetaljer = new Button("Detaljer");
        btnDetaljer.setOnAction(event -> visDetaljerAction());


        HBox whiskyHBOX = new HBox(10, btnOpretWhisky, btnFjernWhisky, btnDetaljer);
        whiskyHBOX.setAlignment(Pos.CENTER);

        this.add(whiskyHBOX, 0, 2);
    }

    //der skal tjekkes om destillaterne har lagret i 3 år
    private void opretWhiskyAction() {
        opretWhiskyWindow1.showAndWait();
        lvwWhisky.getItems().setAll(Controller.getWhiskyMap().values());
    }

    private void fjernWhiskyAction() {
        WhiskyProdukt whiskyProdukt = lvwWhisky.getSelectionModel().getSelectedItem();
        if (whiskyProdukt != null) {
            Controller.getWhiskyMap().values().remove(whiskyProdukt);
            lvwWhisky.getItems().setAll(Controller.getWhiskyMap().values());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vælg et Whisky Produkt");
            alert.setHeaderText(null);
            alert.setContentText("Vælg et whisky produkt som du vil fjerne");
            alert.showAndWait();
        }

    }

        private void visDetaljerAction() {
            WhiskyProdukt whiskyProdukt = lvwWhisky.getSelectionModel().getSelectedItem();
            if (whiskyProdukt != null) {
                visDetaljerWindow(whiskyProdukt);
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Vælg et whisky produkt");
                alert.setHeaderText(null);
                alert.setContentText("Vælg et whisky produkt som du vil se detaljer for");
                alert.showAndWait();
            }
        }


    private void visDetaljerWindow(WhiskyProdukt whiskyProdukt) {
        Stage detailsStage = new Stage();
        detailsStage.initModality(Modality.WINDOW_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        gridPane.addRow(0, new Label("Whisky " + whiskyProdukt.getWhiskyId()));
        gridPane.addRow(1, new Label("Whisky'en er en " + whiskyProdukt.getWhiskyType()));
        gridPane.addRow(2, new Label("Liter Whisky: " + whiskyProdukt.getLiter()));
        gridPane.addRow(3, new Label("Heraf " + whiskyProdukt.getVandLiter() + " liter er vand"));
        gridPane.addRow(4, new Label("Antal Flasker: " + whiskyProdukt.getAntalFlasker()));
        gridPane.addRow(5, new Label("Flaskestørrelse: " + whiskyProdukt.getFlaskeStørrelse() + " liter"));
        gridPane.addRow(6, new Label("Vand er fra: " + whiskyProdukt.getVandLokation()));
        gridPane.addRow(7, new Label("Beskrivelse: " + whiskyProdukt.getBeskrivelse()));
        gridPane.addRow(8, new Label("INDSÆT TEKST OM HVILKE FAD WHISKYEN ER DESTILLERET PÅ"));
        gridPane.addRow(9, new Label("OG ANDET INFORMATION SOM F.EKS: HISTORIK OG FADTYPE"));

        /*
        gridPane.addRow(0, new Label("Whisky "), new Label(destillering.getMedarbejderNavn()));
        gridPane.addRow(1, new Label("Start Dato: "), new Label(destillering.getStartDato().toString()));
        gridPane.addRow(2, new Label("Slut Dato: "), new Label(destillering.getSlutDato().toString()));
        gridPane.addRow(3, new Label("Malt batch: "), new Label(destillering.getMaltBatch()));
        gridPane.addRow(4, new Label("Kornsort: "), new Label(destillering.getKornsort()));
        gridPane.addRow(5, new Label("Kapacitet: "), new Label(destillering.udregnBrugteLiter() + " ud af " + destillering.getTotalLiter() + " liter"));
        gridPane.addRow(6, new Label("Ryge materiale: "), new Label(destillering.getRygeMateriale()));
        gridPane.addRow(7, new Label("Kommentar: "), new Label(destillering.getKommentar()));

         */

        detailsStage.setScene(new Scene(gridPane, 400, 300));
        detailsStage.setTitle("Destillering Detaljer");
        detailsStage.show();
    }

}