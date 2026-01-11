package tictactoecoree;

public class MinimaxAI {

    private final OutcomeCheck checker;

    public MinimaxAI(OutcomeCheck checker) {
        this.checker = checker;
    }

    public Move chooseMove(Board board, PlayerMark aiMark) {
        PlayerMark humanMark = getOpponent(aiMark);

        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board.getMark(r, c) == PlayerMark.EMPTY) {
                    Board next = board.copy();
                    next.placeMark(r, c, aiMark);

                    int score = minimax(next, 1, false, aiMark, humanMark);

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new Move(r, c);
                    }
                }
            }
        }

        return bestMove;
    }

    private int minimax(Board board, int depth, boolean maximizing, PlayerMark aiMark, PlayerMark humanMark) {
        GameState state = checker.evaluate(board);

        if (state != GameState.IN_PROGRESS) {
            return scoreTerminal(state, depth, aiMark, humanMark);
        }

        if (maximizing) {
            int best = Integer.MIN_VALUE;

            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (board.getMark(r, c) == PlayerMark.EMPTY) {
                        Board next = board.copy();
                        next.placeMark(r, c, aiMark);

                        int val = minimax(next, depth + 1, false, aiMark, humanMark);
                        if (val > best) {
                            best = val;
                        }
                    }
                }
            }

            return best;
        } else {
            int best = Integer.MAX_VALUE;

            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (board.getMark(r, c) == PlayerMark.EMPTY) {
                        Board next = board.copy();
                        next.placeMark(r, c, humanMark);

                        int val = minimax(next, depth + 1, true, aiMark, humanMark);
                        if (val < best) {
                            best = val;
                        }
                    }
                }
            }

            return best;
        }
    }

    private int scoreTerminal(GameState state, int depth, PlayerMark aiMark, PlayerMark humanMark) {
        if (state == GameState.DRAW) {
            return 0;
        }

        if (state == GameState.X_WINS) {
            if (aiMark == PlayerMark.X) {
                return 10 - depth;
            }
            return depth - 10;
        }

        if (state == GameState.O_WINS) {
            if (aiMark == PlayerMark.O) {
                return 10 - depth;
            }
            return depth - 10;
        }

        return 0;
    }

    private PlayerMark getOpponent(PlayerMark mark) {
        if (mark == PlayerMark.X) {
            return PlayerMark.O;
        }
        return PlayerMark.X;
    }
}
