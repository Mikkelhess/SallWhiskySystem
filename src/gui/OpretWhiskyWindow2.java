package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logik.Fad;
import logik.LeafDestillat;

import java.util.ArrayList;

public class OpretWhiskyWindow2 extends Stage {

    private ListView<Fad> fadListView;
    private ListView<LeafDestillat> destillatListView;
    private ListView<LeafDestillat> tilføjDestillaterListView;
    private TextField txfDestillater;
    private TextField txfVand;
    private TextField txfAlkoholProcent;
    private TextField txfAntalFlasker;
    private TextField txfMængde;

    private ArrayList<LeafDestillat> destillatListe = new ArrayList<>();
    private double flaskeStørrelse;
    private double destillatLiter;
    private double vandLiter;
    private int antalFlasker;
    private double mængdeLiter;
    private double alkoholProcent;

    public OpretWhiskyWindow2(String title, Stage owner, double flaskeStørrelse) {
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

        this.flaskeStørrelse = flaskeStørrelse;

    }


    private void initContent(GridPane pane) {

            // pane.setGridLinesVisible(true);
            pane.setPadding(new Insets(20));
            pane.setHgap(10);
            pane.setVgap(10);

            // Fade
            Label lblFade = new Label("Fade");
            fadListView = new ListView<>();
            pane.add(lblFade, 0, 0);
            pane.add(fadListView, 0, 1);
            fadListView.getItems().setAll(Controller.getFadMap().values());

            ChangeListener<Fad> listener1 = (ov, o, n) -> this.selectedFadChanged();
            fadListView.getSelectionModel().selectedItemProperty().addListener(listener1);

            // Destillater
            Label lblDestillater = new Label("Destillater");
            destillatListView = new ListView<>();
            pane.add(lblDestillater, 1, 0);
            pane.add(destillatListView, 1, 1);

            // Tilføj Destillater
            Label lblTilføjDestillater = new Label("Tilføj Destillater");
            tilføjDestillaterListView = new ListView<>();
            pane.add(lblTilføjDestillater, 2, 0);
            pane.add(tilføjDestillaterListView, 2, 1);

            ChangeListener<LeafDestillat> listener2 = (ov, o, n) -> this.selectedDestillatChanged();
            tilføjDestillaterListView.getSelectionModel().selectedItemProperty().addListener(listener2);

            Button btnMoveRight = new Button("--->");
            GridPane.setHalignment(btnMoveRight, HPos.CENTER);
            pane.add(btnMoveRight, 1, 2);
            btnMoveRight.setOnAction(event -> this.rightArrowAction());

            Button btnMoveLeft = new Button("<---");
            GridPane.setHalignment(btnMoveLeft, HPos.CENTER);
            pane.add(btnMoveLeft, 2, 2);
            btnMoveLeft.setOnAction(event -> this.leftArrowAction());

            //er dette nødvendigt
            GridPane.setVgrow(fadListView, Priority.ALWAYS);
            GridPane.setVgrow(destillatListView, Priority.ALWAYS);
            GridPane.setVgrow(tilføjDestillaterListView, Priority.ALWAYS);

            Label lblVand = new Label("Vand: (Liter)");
            txfVand = new TextField("0");
            pane.add(lblVand, 0, 3);
            pane.add(txfVand, 1, 3);

            ChangeListener<String> listener3 = (ov, o, n) -> this.vandTxfChanged();
            txfVand.textProperty().addListener(listener3);

            Label lblTotalDestillater = new Label("Destillater: (Liter)");
            txfDestillater = new TextField("0");
            txfDestillater.setEditable(false);
            txfDestillater.setDisable(true);
            txfDestillater.setOpacity(0.5);
            pane.add(lblTotalDestillater, 0, 4);
            pane.add(txfDestillater, 1, 4);

            Label lblMængde = new Label("Mængde: (Liter)");
            txfMængde = new TextField("0");
            txfMængde.setEditable(false);
            txfMængde.setDisable(true);
            txfMængde.setOpacity(0.5);
            pane.add(lblMængde, 0,5);
            pane.add(txfMængde, 1, 5);

             Label lblAlkoholProcent = new Label("Alkoholprocent: ");
             txfAlkoholProcent = new TextField();
            txfAlkoholProcent.setEditable(false);
            txfAlkoholProcent.setDisable(true);
            txfAlkoholProcent.setOpacity(0.5);
            pane.add(lblAlkoholProcent, 0, 6);
            pane.add(txfAlkoholProcent, 1, 6);

            Label lblAntalFlasker = new Label("Antal Flasker: ");
            txfAntalFlasker = new TextField();
            txfAntalFlasker.setText("0");
            txfAntalFlasker.setEditable(false);
            txfAntalFlasker.setDisable(true);
            txfAntalFlasker.setOpacity(0.5);
            pane.add(lblAntalFlasker, 0, 7);
            pane.add(txfAntalFlasker, 1, 7);

            HBox buttonBox = new HBox(20);
            pane.add(buttonBox, 0,  8, 3, 1);
            buttonBox.setPadding(new Insets(10, 10, 0, 10));
            buttonBox.setAlignment(Pos.CENTER);

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
        OpretWhiskyWindow2.this.hide();
    }

    private void okAction() {
        if (tommeTextFields()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText("Tomme Felter");
            alert.setContentText("Nogle af felterne er tomme.");
            alert.showAndWait();
            return;
        }
        tilføjDestillaterListView.getItems().addAll(destillatListe);
        OpretWhiskyWindow2.this.hide();
    }

    private void rightArrowAction() {
        LeafDestillat selectedDestillat = destillatListView.getSelectionModel().getSelectedItem();
        if (selectedDestillat != null) {
            destillatListView.getItems().remove(selectedDestillat);
            tilføjDestillaterListView.getItems().add(selectedDestillat);
            opdaterDestillat();
            opdaterMængdeOgAlkoholProcent();
        }

    }

    private void leftArrowAction() {
        LeafDestillat selectedDestillat = tilføjDestillaterListView.getSelectionModel().getSelectedItem();
        if (selectedDestillat != null) {
            tilføjDestillaterListView.getItems().remove(selectedDestillat);
            destillatListView.getItems().add(selectedDestillat);
            opdaterDestillat();
            opdaterMængdeOgAlkoholProcent();
        }
    }

    private void selectedFadChanged() {
        Fad fad = fadListView.getSelectionModel().getSelectedItem();
        if (fad != null) {
            destillatListView.getItems().setAll(fad.getLeafDestillatMap().values());
        } else {
            destillatListView.getItems().clear();
        }
    }

    private void selectedDestillatChanged() {
        opdaterDestillat();
        opdaterMængdeOgAlkoholProcent();

    }

    //vis alert hvis bogstav
    private void vandTxfChanged() {
        String vandML = txfVand.getText().trim();

        if (!vandML.isEmpty()) {
            this.vandLiter = Double.parseDouble(txfVand.getText());
            opdaterMængdeOgAlkoholProcent();
        }
    }

    private void opdaterDestillat() {
        double ml = 0.0;
        for (LeafDestillat destillat : tilføjDestillaterListView.getItems()) {
            ml += destillat.getLiter();
        }
        txfDestillater.setText(String.valueOf(ml));
        this.destillatLiter = ml;
    }

    private void opdaterAntalFlasker() {
        this.antalFlasker = (int) Math.floor(mængdeLiter / flaskeStørrelse);
        txfAntalFlasker.setText(String.valueOf(antalFlasker));
    }

    private void opdaterMængdeOgAlkoholProcent() {
        double totalMængde = 0;
        double totalAlkohol = 0;

        for (LeafDestillat destillat : tilføjDestillaterListView.getItems()) {
            double destillatMængde = destillat.getLiter();
            double destillatAlkoholProcent = destillat.getAlkoholProcent();

            double destillatAlkohol = destillatMængde * destillatAlkoholProcent / 100;

            totalMængde += destillatMængde;
            totalAlkohol += destillatAlkohol;
        }

        double waterVolume = Double.parseDouble(txfVand.getText());
        totalMængde += waterVolume;

        if (totalMængde == 0) {
            // error
        } else {
            this.alkoholProcent = (totalAlkohol / totalMængde) * 100;
            txfAlkoholProcent.setText("" + alkoholProcent);

            this.mængdeLiter = totalMængde;
            txfMængde.setText("" + totalMængde);
            opdaterAntalFlasker();
        }
    }

    private boolean tommeTextFields() {
        if (txfDestillater.getText().trim().isEmpty() ||
                txfVand.getText().trim().isEmpty() ||
                txfAlkoholProcent.getText().trim().isEmpty() ||
                txfAntalFlasker.getText().trim().isEmpty() ||
                txfMængde.getText().trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public double getMængdeLiter() {
        return mængdeLiter;
    }

    public int getAntalFlasker() {
        return antalFlasker;
    }

    public double getVandLiter() {
        return vandLiter;
    }

    public ArrayList<LeafDestillat> getDestillatListe() {
        return new ArrayList<>(destillatListe);
    }
}




