package tictactoecoree;

public class OutcomeCheck {
    public GameState evaluate(Board board) {
        PlayerMark winner = findWinner(board);

        if (winner == PlayerMark.X) {
            return GameState.X_WINS;
        }

        if (winner == PlayerMark.O) {
            return GameState.O_WINS;
        }

        if (board.isFull()) {
            return GameState.DRAW;
        }

        return GameState.IN_PROGRESS;
    }
    private PlayerMark findWinner(Board board) {
        //Rows
        for (int r = 0; r < 3; r++) {
            PlayerMark a = board.getMark(r, 0);
            PlayerMark b = board.getMark(r, 1);
            PlayerMark c = board.getMark(r, 2);

            if (a != PlayerMark.EMPTY && a == b && a == c) {
                return a;
            }
        }

        //Columns
        for (int col = 0; col < 3; col++) {
            PlayerMark a = board.getMark(0, col);
            PlayerMark b = board.getMark(1, col);
            PlayerMark c = board.getMark(2, col);

            if (a != PlayerMark.EMPTY && a == b && a == c) {
                return a;
            }
        }

        //Diagonals
        PlayerMark center = board.getMark(1, 1);
        if (center != PlayerMark.EMPTY) {
            PlayerMark topLeft = board.getMark(0, 0);
            PlayerMark bottomRight = board.getMark(2, 2);

            if (center == topLeft && center == bottomRight) {
                return center;
            }

            PlayerMark topRight = board.getMark(0, 2);
            PlayerMark bottomLeft = board.getMark(2, 0);

            if (center == topRight && center == bottomLeft) {
                return center;
            }
        }

        return PlayerMark.EMPTY;
    }
}

