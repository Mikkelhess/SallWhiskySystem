package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logik.Fad;

public class OmhældDestillatTilFadWindow1 extends Stage {

    private Fad fad;
    private ListView<Fad> lvwFade;
    private Button btnCancel;
    private Button btnAccepter;

    private HBox HBox;
    private OmhældDestillatTilFadWindow2 omhældDestillatTilFadWindow2;


    //vælg et fad (fjern Fad fad fra listviewet)
    //åben et nyt vindue med to listviews og pile.
    //flyt dem.... :-)

    public OmhældDestillatTilFadWindow1(String title, Stage owner, Fad fad) {
        this.initOwner(owner);
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setMinHeight(100);
        this.setMinWidth(200);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();

        this.fad = fad;

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

        Label lblFade = new Label("Vælg et fad du vil omhælde til: ");

        lvwFade = new ListView<>();
        lvwFade.setPrefSize(350, 400);
        lvwFade.getItems().setAll(Controller.getFadMap().values());
        lvwFade.getItems().remove(fad);

        pane.add(lblFade, 0, 0);
        pane.add(lvwFade, 0, 1);


        HBox = new HBox();
        pane.add(HBox, 0, 2);
        HBox.setSpacing(20);
        HBox.setAlignment(Pos.CENTER);

        btnCancel = new Button("Cancel");
        HBox.getChildren().add(btnCancel);
        btnCancel.setOnAction(event -> cancelAction());

        btnAccepter = new Button("Accepter");
        HBox.getChildren().add(btnAccepter);
        btnAccepter.setOnAction(event -> accepterAction());
        btnCancel = new Button();

    }

    private void cancelAction() {

    }

    private void accepterAction() {
        Fad selectedFad = lvwFade.getSelectionModel().getSelectedItem();
        omhældDestillatTilFadWindow2 = new OmhældDestillatTilFadWindow2("Omhæld Destillat", new Stage(), fad, selectedFad);
        omhældDestillatTilFadWindow2.showAndWait();



    }

}

