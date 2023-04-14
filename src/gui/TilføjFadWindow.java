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
import logik.*;
import storage.Storage;

import java.util.ArrayList;

public class TilføjFadWindow extends Stage {

    private Hylde hylde;
    ListView<Fad> listViewLedigeFad;
    ListView<Fad> listViewTilfojFad;
    private ArrayList<Fad> fadeUdenHylde = new ArrayList<>(Controller.getFadUdenHylde());
    private ArrayList<Fad> tilføjFadListe = new ArrayList<>();

    public TilføjFadWindow(String title, Stage owner, Hylde hylde) {
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

        this.hylde = hylde;
    }

    // -------------------------------------------------------------------------

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label labelAlleFad = new Label("Ledige Fade");
        Label labelTilfojFad = new Label("Tilføj Fad til Hylde");

        listViewLedigeFad = new ListView<>();
        listViewLedigeFad.getItems().setAll(fadeUdenHylde);
        listViewTilfojFad = new ListView<>();

        Button btnRightArrow = new Button("--->");
        btnRightArrow.setOnAction(event -> leftArrowAction());
        Button btnLeftArrow = new Button("<---");
        btnLeftArrow.setOnAction(event -> rightArrowAction());
        Button btnAccepter = new Button("Accepter");
        btnAccepter.setOnAction(event -> accepterAction());

        VBox buttons = new VBox(10);
        buttons.getChildren().addAll(btnRightArrow, btnLeftArrow, btnAccepter);
        buttons.setAlignment(Pos.CENTER);

        pane.add(labelAlleFad, 0, 0);
        pane.add(listViewLedigeFad, 0, 1);
        pane.add(buttons, 1, 1);
        pane.add(labelTilfojFad, 2, 0);
        pane.add(listViewTilfojFad, 2, 1);
    }

    private void leftArrowAction() {
        Fad selectedFad = listViewLedigeFad.getSelectionModel().getSelectedItem();
        if (selectedFad != null) {
            listViewLedigeFad.getItems().remove(selectedFad);
            listViewTilfojFad.getItems().add(selectedFad);
        }
    }

    private void rightArrowAction() {
        Fad selectedFad = listViewTilfojFad.getSelectionModel().getSelectedItem();
        if (selectedFad != null) {
            listViewTilfojFad.getItems().remove(selectedFad);
            listViewLedigeFad.getItems().add(selectedFad);
        }

    }

    private void accepterAction() {
        tilføjFadListe.clear();
        tilføjFadListe.addAll(listViewTilfojFad.getItems());
        tilføjFadListe.forEach(fad -> hylde.addFadTilHylde(fad));
        this.hide();
        }

    }


