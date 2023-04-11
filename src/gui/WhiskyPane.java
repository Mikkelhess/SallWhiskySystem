package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logik.CompositeDestillat;
import logik.WhiskyProdukt;

public class WhiskyPane extends GridPane {

    private ListView<WhiskyProdukt> lvwWhisky;
    private ListView<CompositeDestillat> lvwDestillat;
    private Button btnOpretWhisky;
    private Button btnFjernWhisky;
    private Button btnHistorik;
    private Button btnDetaljer;
    private HBox FadHBox;
    private OpretWhiskyWindow1 opretWhiskyWindow1;

    public WhiskyPane() {
        this.setGridLinesVisible(false);
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);

        opretWhiskyWindow1 = new OpretWhiskyWindow1("Opret Whisky", new Stage());

        Label lblWhisky = new Label("Whisky");
        this.add(lblWhisky, 0, 0);
        lblWhisky.setAlignment(Pos.TOP_LEFT);

        lvwWhisky = new ListView<>();
        this.add(lvwWhisky, 0, 1, 1, 1);
        lvwWhisky.setPrefSize(350, 400);
        lvwWhisky.getItems().setAll(Controller.getWhiskyMap().values());

        btnOpretWhisky = new Button("Opret Whisky");
        btnOpretWhisky.setOnAction(event -> opretWhiskyAction());
        btnFjernWhisky = new Button("Fjern Whisky");
        btnFjernWhisky.setOnAction(event -> fjernWhiskyAction());
        btnDetaljer = new Button("Detaljer");
        btnDetaljer.setOnAction(event -> detaljerAction());


        HBox whiskyHBOX = new HBox(10, btnOpretWhisky, btnFjernWhisky, btnDetaljer);
        whiskyHBOX.setAlignment(Pos.CENTER);

        this.add(whiskyHBOX, 0, 2);
    }

    //der skal tjekkes om destillaterne har lagret i 3 år
    private void opretWhiskyAction() {
        opretWhiskyWindow1.showAndWait();
        lvwWhisky.getItems().setAll(Controller.getWhiskyMap().values());
    }

    private void fjernWhiskyAction() {

    }

    private void detaljerAction() {

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}