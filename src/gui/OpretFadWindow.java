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
import logik.Fad;
import logik.FadType;

public class OpretFadWindow extends Stage {

    public OpretFadWindow(String title, Stage owner) {
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

   private Fad fad;
    private final TextField txfFadType = new TextField();
    private final TextField txfFadLiter = new TextField();

    private ComboBox<FadType> cbbFadType;

    private Fad actualFad = null;


    private void initContent(GridPane pane) {
        // pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);


        Label lblFadType = new Label("Fad type:");
        pane.add(lblFadType, 0, 1);
        //pane.add(txfFadType, 1,1,2,1);

        cbbFadType = new ComboBox<>();
        pane.add(cbbFadType, 1, 1);
        cbbFadType.getItems().addAll(FadType.values());

        Label lblFadLiter = new Label("Fad liter:");
        pane.add(lblFadLiter, 0, 2);
        pane.add(txfFadLiter, 1,2,2,1);

        HBox buttonBox = new HBox(20);
        pane.add(buttonBox, 0, 6);
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

        txfFadType.clear();
        txfFadLiter.clear();
        actualFad = null;
        OpretFadWindow.this.hide();
    }

    private void okAction() {
        FadType fadType = cbbFadType.getSelectionModel().getSelectedItem();
        String fadLiter = txfFadLiter.getText();

        if (fadType != null) {
            txfFadType.clear();
            OpretFadWindow.this.hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Opret fad");
            alert.setHeaderText("Manglende information");
            alert.setContentText("Indtast fad type");
            alert.show();
        }

        if (fadLiter.length() > 0) {
            txfFadLiter.clear();
            OpretFadWindow.this.hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Opret fad");
            alert.setHeaderText("Manglende information");
            alert.setContentText("Indtast fad liter");
            alert.show();
        }


        double fadLiter1 = Double.parseDouble(fadLiter);
        Fad fad1 = Controller.opretFad(fadLiter1,fadType);

    }

    // -------------------------------------------------------------------------

    public Fad getActualFad() {
        return actualFad;
    }
}

