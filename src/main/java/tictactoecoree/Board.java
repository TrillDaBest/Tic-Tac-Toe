package tictactoecoree;

public class Board {
    private final PlayerMark[][] board;
    public Board() {
        board = new PlayerMark[3][3];
        reset();
    }
    public Board(Board other) {
        board = new PlayerMark[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = other.board[r][c];
            }
        }
    }

    public Board copy() {
        return new Board(this);
    }
    public void reset() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board[i][j] = PlayerMark.EMPTY;
            }
        }
    }
    public PlayerMark getMark(int row, int col) {
        return board[row][col];
    }
    public boolean placeMark(int row, int col, PlayerMark mark) {
        if (row < 0 || row > 2) {
            return false;
        }
        if (col < 0 || col > 2) {
            return false;
        }
        if (mark == PlayerMark.EMPTY) {
            return false;
        }
        if (board[row][col] != PlayerMark.EMPTY) {
            return false;
        }

        board[row][col] = mark;
        return true;
    }
    public boolean isFull() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == PlayerMark.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
