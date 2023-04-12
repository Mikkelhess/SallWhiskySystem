package gui;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logik.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class TilføjDestillatWindow extends Stage{

    private Fad fad;
    private CompositeDestillat compositeDestillat;
    ListView<LeafDestillat> listViewLedigeDestillater;
    ListView<LeafDestillat> listViewTilføjDestillat;
    private ArrayList<LeafDestillat> destillatListe;
    private ArrayList<LeafDestillat> tilføjDestillatListe = new ArrayList<>();
    private LocalDate lagringsDato;
    private LagringsDatoWindow lagringsDatoWindow;

    public TilføjDestillatWindow(String title, Stage owner, CompositeDestillat compositeDestillat, Fad fad) {
        this.initOwner(owner);
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setMinHeight(100);
        this.setMinWidth(200);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();

        this.fad = fad;
        this.compositeDestillat = compositeDestillat;
        this.destillatListe = new ArrayList<>();
        for (LeafDestillat leafDestillat : compositeDestillat.getLeaves()) {
            if (!leafDestillat.påFad()) {
                destillatListe.add(leafDestillat);
            }
        }

        this.initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);

    }

    // -------------------------------------------------------------------------

    private void initContent(GridPane pane) {
        // pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label labelAlleFad = new Label("Ledige Destillater");
        Label labelTilfojFad = new Label("Tilføj Destillat til Fad");

        listViewLedigeDestillater = new ListView<>();
        listViewLedigeDestillater.getItems().setAll(destillatListe);
        listViewTilføjDestillat = new ListView<>();

        Button btnRightArrow = new Button("--->");
        btnRightArrow.setOnAction(event -> leftArrowAction()); // Corrected event handler
        Button btnLeftArrow = new Button("<---");
        btnLeftArrow.setOnAction(event -> rightArrowAction()); // Corrected event handler
        Button btnAccepter = new Button("Accepter");
        btnAccepter.setOnAction(event -> accepterAction());


        // Create VBox for buttons
        VBox buttons = new VBox(10);
        buttons.getChildren().addAll(btnRightArrow, btnLeftArrow, btnAccepter);
        buttons.setAlignment(Pos.CENTER);

        // Add components to the grid pane
        pane.add(labelAlleFad, 0, 0);
        pane.add(listViewLedigeDestillater, 0, 1);
        pane.add(buttons, 1, 1);
        pane.add(labelTilfojFad, 2, 0);
        pane.add(listViewTilføjDestillat, 2, 1);
    }

    private void leftArrowAction() {
        LeafDestillat leafDestillat = listViewLedigeDestillater.getSelectionModel().getSelectedItem();
        if (leafDestillat != null) {
            listViewLedigeDestillater.getItems().remove(leafDestillat);
            listViewTilføjDestillat.getItems().add(leafDestillat);
        }
    }

    private void rightArrowAction() {
        LeafDestillat leafDestillat = listViewTilføjDestillat.getSelectionModel().getSelectedItem();
        if (leafDestillat != null) {
            listViewTilføjDestillat.getItems().remove(leafDestillat);
            listViewLedigeDestillater.getItems().add(leafDestillat);
        }

    }

    private void accepterAction() {
        tilføjDestillatListe.clear();
        tilføjDestillatListe.addAll(listViewTilføjDestillat.getItems());
        LagringsDatoWindow lagringsDatoWindow = new LagringsDatoWindow("Sæt Lagringsdato", new Stage(), destillatListe);
        lagringsDatoWindow.showAndWait();
        tilføjDestillatListe.forEach(leafDestillat -> fad.addLeafDestillat(leafDestillat.getNewMakeNummer(), leafDestillat, leafDestillat.getLagringsDato()));
        listViewTilføjDestillat.getItems().forEach(leafDestillat -> leafDestillat.setPåFad(true));
        this.hide();
    }

}
