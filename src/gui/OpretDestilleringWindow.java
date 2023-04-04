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
import javafx.stage.StageStyle;
import logik.Destillering;
import logik.Fad;
import logik.FadType;
import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.Date;

public class OpretDestilleringWindow extends Stage {

    public OpretDestilleringWindow(String title, Stage owner) {
        this.initOwner(owner);
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setMinHeight(100);
        this.setMinWidth(200);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    // -------------------------------------------------------------------------

    private final TextField txfMedarbejderNavn = new TextField();
    private final DatePicker dpStartDato = new DatePicker();
    private final DatePicker dpSlutDato = new DatePicker();
    private final TextField txfMaltBatch = new TextField();
    private final TextField txfKornsort = new TextField();
    private final TextField txfTotalLiter = new TextField();
    private final TextField txfRygeMateriale = new TextField();
    private final TextField txfKommentar = new TextField();




    private void initContent(GridPane pane) {
        // pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);


        Label lblMedarbejderNavn = new Label("Medarbejder Navn:");
        pane.add(lblMedarbejderNavn, 0, 1);
        pane.add(txfMedarbejderNavn, 1,1,2,1);

        Label lblStartDato = new Label("Start Dato:");
        pane.add(lblStartDato, 0, 2);
        pane.add(dpStartDato, 1, 2,2,1);

        Label lblSlutDato = new Label("Slut Dato:");
        pane.add(lblSlutDato, 0, 3);
        pane.add(dpSlutDato, 1, 3,2,1);

        Label lblMaltBatch = new Label("Malt Batch: ");
        pane.add(lblMaltBatch, 0, 4);
        pane.add(txfMaltBatch, 1, 4,2,1);

        Label lblKornsort = new Label("Kornsort: ");
        pane.add(lblKornsort, 0, 5);
        pane.add(txfKornsort, 1, 5,2,1);

        Label lblTotalLiter = new Label("Total Liter: ");
        pane.add(lblTotalLiter, 0, 6);
        pane.add(txfTotalLiter,1, 6, 2,1);

        Label lblRygeMateriale = new Label("Ryge materiale: ");
        pane.add(lblRygeMateriale, 0, 7);
        pane.add(txfRygeMateriale, 1, 7,2,1);

        Label lblKommentar = new Label("Kommentar: ");
        pane.add(lblKommentar, 0, 8);
        pane.add(txfKommentar, 1, 8,2,1);


        HBox buttonBox = new HBox(20);
        pane.add(buttonBox, 0, 10);
        buttonBox.setPadding(new Insets(10, 10, 0, 10));
        buttonBox.setAlignment(Pos.TOP_RIGHT);

        Button btnCancel = new Button("Cancel");
        buttonBox.getChildren().add(btnCancel);
        btnCancel.setOnAction(event -> this.cancelAction());

        Button btnOK = new Button("OK");
        buttonBox.getChildren().add(btnOK);
        btnOK.setOnAction(event -> this.okAction());


    }

    // -------------------------------------------------------------------------
    // Button actions

    private void cancelAction() {

        txfMedarbejderNavn.clear();
        dpStartDato.setValue(null);
        dpSlutDato.setValue(null);
        txfMaltBatch.clear();
        txfKornsort.clear();
        txfTotalLiter.clear();
        txfRygeMateriale.clear();
        txfKommentar.clear();

        OpretDestilleringWindow.this.hide();
    }

    private void okAction() {
        String medarbejderNavn = txfMedarbejderNavn.getText();
        LocalDate startDato = dpStartDato.getValue();
        LocalDate slutDato = dpSlutDato.getValue();
        String maltBatch = txfMaltBatch.getText();
        String kornsort = txfKornsort.getText();
        String liter = txfTotalLiter.getText();
        String rygeMateriale = txfRygeMateriale.getText();
        String kommentar = txfKommentar.getText();
        double totalLiter;

        try {
            totalLiter = Double.parseDouble(liter);
        } catch (NumberFormatException e) {
            showAlert("Ugyldigt input", "Indtast venligst et tal for destillering liter");
            return;
        }

        if (slutDato.isBefore(startDato)) {
            showAlert("Ugyldigt input", "Start dato skal være før slut dato");
            return;
        }

        txfMedarbejderNavn.clear();
        dpStartDato.setValue(null);
        dpSlutDato.setValue(null);
        txfMaltBatch.clear();
        txfKornsort.clear();
        txfTotalLiter.clear();
        txfRygeMateriale.clear();
        txfKommentar.clear();
        OpretDestilleringWindow.this.hide();

        Destillering destillering = Controller.opretDestillering(medarbejderNavn, startDato, slutDato, maltBatch, kornsort, totalLiter, rygeMateriale, kommentar);
    }

    private void showAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Opret Destillering");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }

}


