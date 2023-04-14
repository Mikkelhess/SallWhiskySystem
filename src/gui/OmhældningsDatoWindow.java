package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logik.LeafDestillat;

import java.time.LocalDate;
import java.util.ArrayList;

public class OmhældningsDatoWindow extends Stage {
    DatePicker dpOmhældningsDato = new DatePicker();
    private ArrayList<LeafDestillat> destillatListe;
    private LocalDate omhældningsDato;
    private LocalDate originalLagringsDato;


    public OmhældningsDatoWindow(String title, Stage owner, ArrayList<LeafDestillat> destillatListe) {
        this.initOwner(owner);
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setMinHeight(100);
        this.setMinWidth(300);
        this.setResizable(false);
        this.setTitle(title);

        this.destillatListe = destillatListe;

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

        Label lblOmhældningsDato = new Label("Vælg Omhældningsdato for Destillat: ");

        pane.add(lblOmhældningsDato, 0, 0);
        pane.add(dpOmhældningsDato, 0, 1);


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

        dpOmhældningsDato.setValue(null);
        OmhældningsDatoWindow.this.hide();
    }

    private void okAction() {
        this.omhældningsDato = dpOmhældningsDato.getValue();
        this.hide();
    }

    public LocalDate getOmhældningsDato() {
        return omhældningsDato;
    }
}
