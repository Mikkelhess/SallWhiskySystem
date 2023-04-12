package gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logik.Fad;
import logik.LeafDestillat;

public class OmhældDestillatTilFadWindow2 extends Stage {

    private Fad fad1;
    private Fad fad2;
    private ListView<LeafDestillat> lvwDestillatFra;
    private ListView<LeafDestillat> lvwDestillatTil;

    public OmhældDestillatTilFadWindow2(String title, Stage owner, Fad fad1, Fad fad2) {
        this.initOwner(owner);
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setMinHeight(100);
        this.setMinWidth(200);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();

        this.fad1 = fad1;
        this.fad2 = fad2;

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

        Label lblDestillatFra = new Label("Destillater Fra Fad");
        pane.add(lblDestillatFra, 0, 0);

        lvwDestillatFra = new ListView<>();
        pane.add(lvwDestillatFra, 0, 1);
        lvwDestillatFra.getItems().setAll(fad1.getLeafDestillatMap().values());

        Label lblDestillatTil = new Label("Destillater til Fad");
        pane.add(lblDestillatTil, 2, 0);

        lvwDestillatTil = new ListView<>();
        pane.add(lvwDestillatTil, 2, 1);

        Button btnRightArrow = new Button("--->");
        btnRightArrow.setOnAction(e -> rightArrowAction());

        Button btnLeftArrow = new Button("<---");
        btnLeftArrow.setOnAction(e -> leftArrowAction());

        VBox arrowBox = new VBox(5);
        arrowBox.setAlignment(Pos.CENTER);
        arrowBox.getChildren().addAll(btnRightArrow, btnLeftArrow);
        pane.add(arrowBox, 1, 1);

        Button btnAccepter = new Button("Accepter");
        btnAccepter.setOnAction(e -> accepterAction());
        pane.add(btnAccepter, 1, 2);
        GridPane.setHalignment(btnAccepter, HPos.CENTER);
    }

    private void leftArrowAction() {

    }

    private void rightArrowAction() {

    }

    private void cancelAction() {

    }

    private void accepterAction() {


    }

}

