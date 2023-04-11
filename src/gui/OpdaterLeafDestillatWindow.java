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
import logik.CompositeDestillat;
import logik.LeafDestillat;

import java.time.LocalDate;

public class OpdaterLeafDestillatWindow extends Stage {

    public OpdaterLeafDestillatWindow(String title, Stage owner, LeafDestillat leafDestillat, CompositeDestillat compositeDestillat) {
        this.initOwner(owner);
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setMinHeight(100);
        this.setMinWidth(200);
        this.setResizable(false);

        this.leafdestillat = leafDestillat;
        this.compositeDestillat = compositeDestillat;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    // -------------------------------------------------------------------------

    private final TextField txfLiter = new TextField();

    private LeafDestillat leafdestillat;

    private ListView<LeafDestillat> lvwLeafDestillater;
    private CompositeDestillat compositeDestillat;

    private void initContent(GridPane pane) {
        // pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblLiter = new Label("Liter:");
        pane.add(lblLiter, 0, 0);
        pane.add(txfLiter, 1, 0, 2, 1);


        HBox buttonBox = new HBox(20);
        pane.add(buttonBox, 0, 6);
        buttonBox.setPadding(new Insets(10, 10, 0, 10));
        buttonBox.setAlignment(Pos.TOP_LEFT);

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
        txfLiter.clear();
        OpdaterLeafDestillatWindow.this.hide();
    }

    private void okAction() {
        String liter1 = txfLiter.getText().trim();
        double liter = 0;

        try {
            double liter2 = Double.parseDouble(liter1);
            if (liter2 > 0) {
                // Validering: Tjek om den nye liter vil gøre, at CompositeDestillat overskrider totalLiter
                double totalLiter = compositeDestillat.getTotalLiter();
                double brugteLiter = compositeDestillat.getBrugteLiter();
                double nyBrugteLiter = brugteLiter - leafdestillat.getLiter() + liter2;
                if (nyBrugteLiter > totalLiter) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Opdater destillat");
                    alert.setHeaderText("Invalid input");
                    alert.setContentText("You cannot add that amount as it will exceed the total allowed amount.");
                    alert.show();
                    return;
                }
                liter = liter2;
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Opdater destillat");
                alert.setHeaderText("Invalid input");
                alert.setContentText("Liter must be a positive number");
                alert.show();
                return;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Opdater destillat");
            alert.setHeaderText("Invalid input");
            alert.setContentText("Liter must be a number");
            alert.show();
            return;
        }

        txfLiter.clear();

        // Opdater LeafDestillat
        leafdestillat.setLiter(liter);

        // Opdater CompositeDestillat
        compositeDestillat.setBrugteLiter(compositeDestillat.getBrugteLiter() - leafdestillat.getLiter() + liter);
        OpdaterLeafDestillatWindow.this.hide();
    }

    // ----------------------------------------------------------------------
}
