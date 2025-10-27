package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameOverController {

    @FXML private Label winnerLabel;
    @FXML private Label statsLabel;

    private static int winner;
    private static int p1Hits, p1Miss, p1Remain;
    private static int p2Hits, p2Miss, p2Remain;

    public static void setData(int w, int p1H, int p1M, int p1R, int p2H, int p2M, int p2R) {
        winner = w;
        p1Hits = p1H; p1Miss = p1M; p1Remain = p1R;
        p2Hits = p2H; p2Miss = p2M; p2Remain = p2R;
    }

    @FXML
    public void initialize() {
        winnerLabel.setText("Player " + winner + " Wins!");
        statsLabel.setText(
            "Player 1 -> Hits: " + p1Hits + ", Miss: " + p1Miss + ", Remaining: " + p1Remain + "\n" +
            "Player 2 -> Hits: " + p2Hits + ", Miss: " + p2Miss + ", Remaining: " + p2Remain
        );
    }

    @FXML
    private void replay() {
        try { Main.switchScene("placement.fxml"); } catch(Exception e){ e.printStackTrace(); }
    }

    @FXML
    private void mainMenu() {
        try { Main.switchScene("start.fxml"); } catch(Exception e){ e.printStackTrace(); }
    }

    @FXML
    private void exit() { System.exit(0); }
}
