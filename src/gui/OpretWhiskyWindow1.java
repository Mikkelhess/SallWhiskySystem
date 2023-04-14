package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logik.Destillering;
import logik.WhiskyProdukt;

public class OpretWhiskyWindow1 extends Stage {

    private final TextField txfLiter = new TextField();
    private final TextField txfBeskrivelse = new TextField();
    private final TextField txfWhiskyType = new TextField();
    private final TextField txfVandLokation = new TextField();


    public OpretWhiskyWindow1(String title, Stage owner) {
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

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblLiter = new Label("Flaske Størrelse: (Liter): ");
        pane.add(lblLiter, 0, 1);
        txfLiter.setPromptText("f.eks. 1 liter");
        txfLiter.setFocusTraversable(false);

        pane.add(txfLiter, 1,1,2,1);

        Label lblWhiskyType = new Label("Whisky Type: ");
        pane.add(lblWhiskyType, 0, 2);
        pane.add(txfWhiskyType, 1, 2,2,1);
        txfWhiskyType.setFocusTraversable(false);

        Label lblVandLokation = new Label("Vand Lokation: ");
        pane.add(lblVandLokation, 0, 3);
        pane.add(txfVandLokation, 1, 3,2,1);
        txfVandLokation.setFocusTraversable(false);

        Label lblBeskrivelse = new Label("Beskrivelse: ");
        pane.add(lblBeskrivelse, 0, 4);
        pane.add(txfBeskrivelse, 1, 4,2,1);
        txfBeskrivelse.setFocusTraversable(false);

        HBox buttonBox = new HBox(20);
        pane.add(buttonBox, 0, 5);
        buttonBox.setPadding(new Insets(10, 10, 0, 10));
        buttonBox.setAlignment(Pos.TOP_RIGHT);

        Button btnCancel = new Button("Cancel");
        buttonBox.getChildren().add(btnCancel);
        btnCancel.setOnAction(event -> this.cancelAction());
        btnCancel.requestFocus();

        Button btnOK = new Button("OK");
        buttonBox.getChildren().add(btnOK);
        btnOK.setOnAction(event -> this.okAction());

    }

    private void cancelAction() {

        txfLiter.clear();
        txfBeskrivelse.clear();
        txfWhiskyType.clear();
        txfVandLokation.clear();

        OpretWhiskyWindow1.this.hide();
    }

    private void okAction() {

        double flaskeStørrelse = Double.parseDouble(txfLiter.getText().trim());

        String whiskyType = txfWhiskyType.getText();
        String vandLokation = txfVandLokation.getText();
        String beskrivelse = txfBeskrivelse.getText();


        OpretWhiskyWindow2 opretWhiskyWindow2 = new OpretWhiskyWindow2("Opret Whisky", OpretWhiskyWindow1.this, flaskeStørrelse);
        opretWhiskyWindow2.showAndWait();
        OpretWhiskyWindow1.this.hide();

        WhiskyProdukt whiskyProdukt = Controller.opretWhiskyProdukt(whiskyType, opretWhiskyWindow2.getMængdeLiter(), opretWhiskyWindow2.getAntalFlasker(), flaskeStørrelse, vandLokation, opretWhiskyWindow2.getVandLiter(), beskrivelse, opretWhiskyWindow2.getDestillatListe());

        txfBeskrivelse.clear();
        txfLiter.clear();
        txfVandLokation.clear();
        txfWhiskyType.clear();

    }
}
