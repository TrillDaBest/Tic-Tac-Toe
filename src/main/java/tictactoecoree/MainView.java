package tictactoecoree;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainView extends BorderPane {

    private final GameEngine engine;
    private final BoardView boardView;

    private final Label statusLabel;
    private final Button resetButton;

    public MainView() {
        engine = new GameEngine();

        engine.setVsComputer(true);
        engine.setHumanMark(PlayerMark.X);

        boardView = new BoardView();

        statusLabel = new Label();
        resetButton = new Button("Reset");

        setPadding(new Insets(16));
        setCenter(boardView);
        setTop(buildTopBar());

        hookEvents();
        refreshAll();

        boolean aiPlayed = engine.makeComputerMoveIfNeeded();
        if (aiPlayed) {
            refreshAll();
        }
    }

    private HBox buildTopBar() {
        HBox bar = new HBox(12);
        bar.setAlignment(Pos.CENTER_LEFT);

        bar.getChildren().add(statusLabel);
        bar.getChildren().add(resetButton);

        return bar;
    }

    private void hookEvents() {
            boardView.setOnCellClicked((row, col) -> {
                boolean ok = engine.makeMove(row, col);
                if (ok) {
                    refreshAll();

                    boolean aiPlayed = engine.makeComputerMoveIfNeeded();
                    if (aiPlayed) {
                        refreshAll();
                    }
                }
            });

            resetButton.setOnAction(e -> {
                engine.reset();
                refreshAll();

                boolean aiPlayed = engine.makeComputerMoveIfNeeded();
                if (aiPlayed) {
                    refreshAll();
                }
            });
        }



    private void refreshAll() {
        Board b = engine.getBoard();

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                boardView.setCell(r, c, b.getMark(r, c));
            }
        }

        GameState state = engine.getState();
        updateStatusText(state);

        if (state == GameState.IN_PROGRESS) {
            boardView.setBoardDisabled(false);
        } else {
            boardView.setBoardDisabled(true);
        }
    }

    private void updateStatusText(GameState state) {
        if (state == GameState.IN_PROGRESS) {
            statusLabel.setText("Turn: " + engine.getCurrentTurn());
            return;
        }

        if (state == GameState.X_WINS) {
            statusLabel.setText("X wins!");
            return;
        }

        if (state == GameState.O_WINS) {
            statusLabel.setText("O wins!");
            return;
        }

        statusLabel.setText("Draw!");
    }
}