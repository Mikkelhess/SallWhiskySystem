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

public class OpretDestillatWindow extends Stage {

    private final TextField txfTotalLiter = new TextField();
    private final TextField txfAlkoholProcent = new TextField();
    Destillering destillering;


    public OpretDestillatWindow(String title, Stage owner, Destillering destillering) {
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

        this.destillering = destillering;
    }

    // -------------------------------------------------------------------------


    private void initContent(GridPane pane) {
        // pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);


        Label lblTotalLiter = new Label("Total liter: ");
        pane.add(lblTotalLiter, 0, 1);
        pane.add(txfTotalLiter, 1,1,2,1);


        Label lblAlkoholProcent = new Label("Alkohol Procent:");
        pane.add(lblAlkoholProcent, 0, 2);
        pane.add(txfAlkoholProcent, 1, 2,2,1);



        HBox buttonBox = new HBox(20);
        pane.add(buttonBox, 0, 4);
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

        txfTotalLiter.clear();
        txfAlkoholProcent.clear();

        OpretDestillatWindow.this.hide();
    }

    private void okAction() {
        double totalLiter;
        try {
            totalLiter = Double.parseDouble(txfTotalLiter.getText());
        } catch (NumberFormatException e) {
            showAlert("Ugyldigt input", "Indtast venligst et tal for destillat liter");
            return;
        }

        double alkoholProcent;
        try {
            alkoholProcent = Double.parseDouble(txfAlkoholProcent.getText());
        } catch (NumberFormatException e) {
            showAlert("Ugyldigt input", "Indtast venligst et tal for destillat alkohol procent");
            return;
        }

        double remainingCapacity = destillering.udregnBrugteLiter();
        if (totalLiter > remainingCapacity) {
            showAlert("Opret Destillat", "Destillater overstiger kapacitet af destillering");
            return;
        }

        txfTotalLiter.clear();
        txfAlkoholProcent.clear();
        OpretDestillatWindow.this.hide();
        CompositeDestillat compositeDestillat = destillering.createDestillat(totalLiter, alkoholProcent);
    }


    private void showAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Opret Destillering");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }

}
