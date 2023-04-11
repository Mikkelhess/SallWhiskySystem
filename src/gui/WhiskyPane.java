package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logik.CompositeDestillat;
import logik.Fad;
import logik.Whisky;

public class WhiskyPane extends GridPane {

    private ListView<Whisky> lvwWhisky;
    private ListView<CompositeDestillat> lvwDestillat;
    private Button btnOpretWhisky;
    private Button btnFjernWhisky;
    private Button btnHistorik;
    private HBox FadHBox;

    private OpretFadWindow opretFadWindow;
    private TilføjDestillatWindow tilføjDestillatWindow;


    public WhiskyPane() {
        this.setGridLinesVisible(false);
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);


        lvwWhisky = new ListView<>();
        this.add(lvwWhisky, 0, 1, 1, 1);
        lvwWhisky.setPrefSize(350, 400);
        lvwWhisky.getItems().setAll(Controller.getWhiskyMap().values());

        Label lblWhisky = new Label("Whisky");
        this.add(lblWhisky, 0, 0);
        lblWhisky.setAlignment(Pos.TOP_LEFT);

        Image image = new Image("C:\\Users\\JohnL\\OneDrive\\Skrivebord\\SALL.png");
        ImageView imageView = new ImageView(image);
        this.add(imageView, 1, 1);
    }

    private void tilføjDestillatAction() {


    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}