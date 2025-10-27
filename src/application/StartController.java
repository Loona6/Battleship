package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StartController {

    @FXML private Button startButton;
    @FXML private ImageView startImage;

    @FXML
    private void initialize() {
        try {
            startImage.setImage(new Image("file:resources/start_image.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void startGame() {
        try {
            Main.switchScene("placement.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
