package tictactoecoree;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BoardView extends GridPane {

    public interface CellClickListener {
        void onCellClick(int row, int col);
    }

    private final Button[][] cells;
    private CellClickListener listener;

    public BoardView() {
        cells = new Button[3][3];

        setAlignment(Pos.CENTER);
        setHgap(12);
        setVgap(12);
        setPadding(new Insets(16));

        buildGrid();
    }

    private void buildGrid() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                Button b = new Button("");
                b.setMinSize(120, 120);
                b.setPrefSize(140, 140);

                final int rr = r;
                final int cc = c;

                b.setOnAction(e -> {
                    if (listener != null) {
                        listener.onCellClick(rr, cc);
                    }
                });

                cells[r][c] = b;
                add(b, c, r);
            }
        }
    }

    public void setOnCellClicked(CellClickListener listener) {
        this.listener = listener;
    }

    public void clear() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                Button b = cells[r][c];
                b.setText("");
                b.getStyleClass().removeAll("mark-x", "mark-o", "win");
            }
        }
    }

    public void setBoardDisabled(boolean disabled) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                cells[r][c].setDisable(disabled);
            }
        }
    }

    public void setCell(int row, int col, PlayerMark mark) {
        Button b = cells[row][col];

        b.getStyleClass().removeAll("mark-x", "mark-o");

        if (mark == PlayerMark.X) {
            b.setText("X");
            b.getStyleClass().add("mark-x");
            return;
        }

        if (mark == PlayerMark.O) {
            b.setText("O");
            b.getStyleClass().add("mark-o");
            return;
        }

        b.setText("");
    }
}
