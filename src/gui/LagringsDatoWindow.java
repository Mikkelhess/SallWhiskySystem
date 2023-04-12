package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logik.CompositeDestillat;
import logik.Destillering;
import logik.Fad;
import logik.LeafDestillat;

import java.time.LocalDate;
import java.util.ArrayList;

public class LagringsDatoWindow extends Stage {
    DatePicker dpLagringsDato = new DatePicker();
    LocalDate lagringsDato;
    Fad fad;
    private ArrayList<LeafDestillat> destillatListe;


    public LagringsDatoWindow(String title, Stage owner, ArrayList<LeafDestillat> destillatListe, Fad fad) {
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

        this.destillatListe = destillatListe;
        this.fad = fad;
    }

    // -------------------------------------------------------------------------


    private void initContent(GridPane pane) {
        // pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblTotalLiter = new Label("Vælg Lagringsdato for Destillat: ");

        pane.add(lblTotalLiter, 0, 0);
        pane.add(dpLagringsDato, 1, 0);


        HBox buttonBox = new HBox(20);
        pane.add(buttonBox, 0, 2);
        buttonBox.setPadding(new Insets(10, 10, 0, 10));
        buttonBox.setAlignment(Pos.TOP_RIGHT);

        Button btnCancel = new Button("Cancel");
        buttonBox.getChildren().add(btnCancel);
        btnCancel.setOnAction(event -> this.cancelAction());

        Button btnOK = new Button("OK");
        buttonBox.getChildren().add(btnOK);
        btnOK.setOnAction(event -> this.okAction());


    }

    private void cancelAction() {
        dpLagringsDato.setValue(null);
        LagringsDatoWindow.this.hide();
    }

    private void okAction() {
        this.lagringsDato = dpLagringsDato.getValue();
        this.hide();
    }

    public LocalDate getLagringsDato() {
        return lagringsDato;
    }
}

