    package gui;

    import controller.Controller;
    import javafx.application.Application;
    import javafx.scene.Scene;
    import javafx.scene.control.Tab;
    import javafx.scene.control.TabPane;
    import javafx.scene.layout.*;
    import javafx.stage.Stage;

    public class StartWindow extends Application {

        @Override
        public void init() {
            Controller.initStorage();
        }

        @Override
        public void start(Stage stage) {
            stage.setTitle("Sall Whisky System");
            BorderPane pane = new BorderPane();
            this.initContent(pane);

            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.show();
        }

        //--------------------------------------------------------------------

        private void initContent(BorderPane pane) {
            TabPane tabPane = new TabPane();
            this.initTabPane(tabPane);
            pane.setCenter(tabPane);
        }
        private void initTabPane(TabPane tabPane) {
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

            Tab tabLagerManager = new Tab("Lager Manager");
            tabPane.getTabs().add(tabLagerManager);

            LagerPane lagerPane = new LagerPane();
            tabLagerManager.setContent(lagerPane);
            tabLagerManager.setOnSelectionChanged(event -> lagerPane.updateList());

            Tab tabFad = new Tab("Fad");
            tabPane.getTabs().add(tabFad);

            FadPane fadPane = new FadPane();
            tabFad.setContent(fadPane);
            tabFad.setOnSelectionChanged(event -> fadPane.updateList());


            Tab tabDestillering = new Tab("Destillering");
            tabPane.getTabs().add(tabDestillering);

            DestilleringPane destilleringPane = new DestilleringPane();
            tabDestillering.setContent(destilleringPane);

            Tab tabWhisky = new Tab("Whisky");
            tabPane.getTabs().add(tabWhisky);
            WhiskyPane whiskyPane = new WhiskyPane();

            tabWhisky.setContent(whiskyPane);

        }


    }
