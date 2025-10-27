package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlacementController {

    @FXML private GridPane placementGrid;
    @FXML private Label infoLabel;
    @FXML private Button rotateButton, nextPlayerButton;

    private final int SIZE = 10;
    private Rectangle[][] cells = new Rectangle[SIZE][SIZE];
    private boolean horizontal = true;
    private int currentPlayer = 0; // 0 = Player1, 1 = Player2
    private int[][][] playerShips = new int[2][SIZE][SIZE];
    private int battleshipCount = 1;
    private int destroyerCount = 2;
    private int submarineCount = 3;

    @FXML
    public void initialize() {
        buildGrid();
        updateInfo();
    }

    private void buildGrid() {
        placementGrid.getChildren().clear();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                Rectangle rect = new Rectangle(50, 50);
                rect.setFill(Color.LIGHTBLUE);
                rect.setStroke(Color.BLACK);
                int row = r, col = c;
                rect.setOnMouseClicked(e -> placeShip(row, col));
                placementGrid.add(rect, c, r);
                cells[r][c] = rect;
            }
        }
    }

    private void placeShip(int row, int col) {
        int type = getNextShipType();
        if (type == 0) return;

        int length = (type == 1 ? 3 : type == 2 ? 2 : 1);

        if (!canPlace(row, col, length, horizontal)) return;

        for (int i = 0; i < length; i++) {
            int r = row + (horizontal ? 0 : i);
            int c = col + (horizontal ? i : 0);
            playerShips[currentPlayer][r][c] = 1;
            cells[r][c].setFill(type == 1 ? Color.DARKRED : type == 2 ? Color.DARKORANGE : Color.DARKGREEN);
        }

        decrementShipCount(type);
        updateInfo();
    }

    private boolean canPlace(int row, int col, int length, boolean horizontal) {
        for (int i = 0; i < length; i++) {
            int r = row + (horizontal ? 0 : i);
            int c = col + (horizontal ? i : 0);
            if (r >= SIZE || c >= SIZE) return false;
            if (playerShips[currentPlayer][r][c] == 1) return false;
        }
        return true;
    }

    private int getNextShipType() {
        if (battleshipCount > 0) return 1;
        if (destroyerCount > 0) return 2;
        if (submarineCount > 0) return 3;
        return 0;
    }

    private void decrementShipCount(int type) {
        if (type == 1) battleshipCount--;
        else if (type == 2) destroyerCount--;
        else submarineCount--;
    }

    @FXML
    private void rotateShip() {
        horizontal = !horizontal;
        updateInfo();
    }

    @FXML
    private void nextPlayer() {
        if (battleshipCount + destroyerCount + submarineCount > 0) return;
        if (currentPlayer == 0) {
            currentPlayer = 1;
            battleshipCount = 1;
            destroyerCount = 2;
            submarineCount = 3;
            for (int r = 0; r < SIZE; r++)
                for (int c = 0; c < SIZE; c++)
                    cells[r][c].setFill(Color.LIGHTBLUE);
            playerShips[currentPlayer] = new int[SIZE][SIZE];
            updateInfo();
        } else {
            GameController.playerShips = playerShips;
            try {
                Main.switchScene("game.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateInfo() {
        infoLabel.setText("Player " + (currentPlayer + 1) + " placing ships. Next: " +
                (battleshipCount > 0 ? "Battleship " : "") +
                (destroyerCount > 0 ? "Destroyer " : "") +
                (submarineCount > 0 ? "Submarine " : "") +
                (horizontal ? "(Horizontal)" : "(Vertical)"));
    }
}
