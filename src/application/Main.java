package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage primaryStage;
    public static boolean darkMode = true;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        switchScene("start.fxml");
        primaryStage.setTitle("Battleship");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void switchScene(String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/" + fxmlFile));
        Scene scene = new Scene(loader.load(), 1200, 750); 
        scene.getStylesheets().add(darkMode ? "file:resources/dark.css" : "file:resources/light.css");
        primaryStage.setScene(scene);
    }

    public static void toggleTheme() {
        darkMode = !darkMode;
        Scene scene = primaryStage.getScene();
        if (scene != null) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(darkMode ? "file:resources/dark.css" : "file:resources/light.css");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
