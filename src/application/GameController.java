package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameController {

    @FXML private GridPane attackGrid1, attackGrid2;
    @FXML private Label turnLabel, player1Stats, player2Stats;

    private final int SIZE = 10;
    private Rectangle[][] grid1 = new Rectangle[SIZE][SIZE];
    private Rectangle[][] grid2 = new Rectangle[SIZE][SIZE];

    private boolean player1Turn = true;
    private int[][] hits1 = new int[SIZE][SIZE]; // Player1 hits Player2
    private int[][] hits2 = new int[SIZE][SIZE]; // Player2 hits Player1

    public static int[][][] playerShips;

    @FXML
    public void initialize() {
        buildGrid(attackGrid1, grid1, true);
        buildGrid(attackGrid2, grid2, false);
        updateTurnLabel();
        updateStats();
    }

    private void buildGrid(GridPane pane, Rectangle[][] cells, boolean player1) {
        pane.getChildren().clear();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                Rectangle rect = new Rectangle(50, 50);
                rect.setFill(Color.LIGHTBLUE);
                rect.setStroke(Color.BLACK);
                int row = r, col = c;
                rect.setOnMouseClicked(e -> attack(row, col, player1));
                pane.add(rect, c, r);
                cells[r][c] = rect;
            }
        }
    }

    private void attack(int row, int col, boolean isPlayer1Grid) {
        if(player1Turn != isPlayer1Grid) return; // only current player can attack their board

        int opponent = player1Turn ? 1 : 0;
        int[][] opponentHits = player1Turn ? hits1 : hits2;
        Rectangle[][] ownGrid = player1Turn ? grid1 : grid2;
        GridPane gridPane = player1Turn ? attackGrid1 : attackGrid2;

        if(ownGrid[row][col].getFill() != Color.LIGHTBLUE) return;

        Label text = new Label();
        text.setStyle("-fx-font-weight:bold; -fx-font-size:18px; -fx-alignment:center;");

        if(playerShips[opponent][row][col] == 1) {
            ownGrid[row][col].setFill(Color.DARKRED); // still use color for visual cue
            text.setText("Hit");
            text.setTextFill(Color.WHITE);
            opponentHits[row][col] = 1;
        } else {
            ownGrid[row][col].setFill(Color.DARKGRAY);
            text.setText("Miss");
            text.setTextFill(Color.YELLOW);
            opponentHits[row][col] = 2;
        }

        GridPane.setRowIndex(text, row);
        GridPane.setColumnIndex(text, col);
        gridPane.getChildren().add(text);

        if(checkWin(opponent)) {
            // Gather stats
            int p1Hits=0,p1Miss=0,p1Remain=0,p2Hits=0,p2Miss=0,p2Remain=0;
            for(int r=0;r<SIZE;r++){
                for(int c=0;c<SIZE;c++){
                    if(hits1[r][c]==1) p1Hits++; else if(hits1[r][c]==2) p1Miss++;
                    if(playerShips[1][r][c]==1 && hits1[r][c]!=1) p2Remain++;

                    if(hits2[r][c]==1) p2Hits++; else if(hits2[r][c]==2) p2Miss++;
                    if(playerShips[0][r][c]==1 && hits2[r][c]!=1) p1Remain++;
                }
            }

            // Set GameOver data
            GameOverController.setData(player1Turn ? 1 : 2, p1Hits, p1Miss, p1Remain, p2Hits, p2Miss, p2Remain);

            // Switch to GameOver scene
            try {
                Main.switchScene("gameover.fxml");
            } catch(Exception e) { e.printStackTrace(); }
            return;
        }


        player1Turn = !player1Turn;
        updateTurnLabel();
        updateStats();
    }



    private boolean checkWin(int playerIdx) {
        for (int r = 0; r < SIZE; r++)
            for (int c = 0; c < SIZE; c++)
                if (playerShips[playerIdx][r][c] == 1) {
                    int[][] opponentHits = (playerIdx == 0) ? hits2 : hits1;
                    if (opponentHits[r][c] != 1) return false;
                }
        return true;
    }

    private void updateTurnLabel() {
        turnLabel.setText(player1Turn ? "Player 1's Turn" : "Player 2's Turn");
    }

    private void updateStats() {
        int p1Hits = 0, p1Miss = 0, p1Remain = 0;
        int p2Hits = 0, p2Miss = 0, p2Remain = 0;

        for (int r = 0; r < SIZE; r++)
            for (int c = 0; c < SIZE; c++) {
                if (hits1[r][c] == 1) p1Hits++;
                else if (hits1[r][c] == 2) p1Miss++;
                if (playerShips[1][r][c] == 1 && hits1[r][c] != 1) p2Remain++;

                if (hits2[r][c] == 1) p2Hits++;
                else if (hits2[r][c] == 2) p2Miss++;
                if (playerShips[0][r][c] == 1 && hits2[r][c] != 1) p1Remain++;
            }

        player1Stats.setText("Remaining: " + p2Remain + " | Hit: " + p1Hits + " | Miss: " + p1Miss);
        player2Stats.setText("Remaining: " + p1Remain + " | Hit: " + p2Hits + " | Miss: " + p2Miss);
    }
}
