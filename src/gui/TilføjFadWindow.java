package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logik.Fad;
import logik.FadType;

public class TilføjFadWindow extends Stage {

    public TilføjFadWindow(String title, Stage owner) {
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

        // Create labels
        Label labelAlleFad = new Label("Ledige Fade");
        Label labelTilfojFad = new Label("Tilføj Fad til Hylde");

        // Create list views
        ListView<String> listViewAlleFad = new ListView<>();
        ListView<String> listViewTilfojFad = new ListView<>();

        // Create buttons
        Button btnRightArrow = new Button("--->");
        Button btnLeftArrow = new Button("<---");
        Button btnAccept = new Button("Accept");

        // Create VBox for buttons
        VBox buttons = new VBox(10);
        buttons.getChildren().addAll(btnRightArrow, btnLeftArrow, btnAccept);
        buttons.setAlignment(Pos.CENTER);

        // Add components to the grid pane
        pane.add(labelAlleFad, 0, 0);
        pane.add(listViewAlleFad, 0, 1);
        pane.add(buttons, 1, 1);
        pane.add(labelTilfojFad, 2, 0);
        pane.add(listViewTilfojFad, 2, 1);
    }


    // -------------------------------------------------------------------------
    // Button actions

    private void cancelAction() {

    }

    private void okAction() {
        FadType fadType = cbbFadType.getSelectionModel().getSelectedItem();
        String fadLiter = txfFadLiter.getText();

        if (fadType == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Opret fad");
            alert.setHeaderText("Manglende information");
            alert.setContentText("Indtast fad type");
            alert.show();
            return;
        }

        double fadLiterValue;
        try {
            fadLiterValue = Double.parseDouble(fadLiter);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Opret fad");
            alert.setHeaderText("Ugyldigt input");
            alert.setContentText("Indtast venligst et tal for fad liter");
            alert.show();
            return;
        }

        txfFadType.clear();
        txfFadLiter.clear();
        TilføjFadWindow.this.hide();

        Fad fad1 = Controller.opretFad(fadLiterValue, fadType);
    }

    // -------------------------------------------------------------------------

    public Fad getActualFad() {
        return actualFad;
    }
}

