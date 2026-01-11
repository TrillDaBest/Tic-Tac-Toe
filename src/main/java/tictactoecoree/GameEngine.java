package tictactoecoree;

public class GameEngine {
    private final Board board;
    private final OutcomeCheck checker;
    private boolean vsComputer;
    private PlayerMark humanMark;
    private PlayerMark aiMark;
    private final MinimaxAI ai;
    private PlayerMark currentTurn;
    private GameState state;

    public GameEngine() {
        board = new Board();
        checker = new OutcomeCheck();
        ai = new MinimaxAI(checker);

        vsComputer = false;
        humanMark = PlayerMark.X;
        aiMark = PlayerMark.O;

        reset();
    }
    public void setVsComputer(boolean enabled) {
        vsComputer = enabled;
    }
    public void setHumanMark(PlayerMark mark) {
        if (mark == PlayerMark.X || mark == PlayerMark.O) {
            humanMark = mark;
            if (humanMark == PlayerMark.X) {
                aiMark = PlayerMark.O;
            } else {
                aiMark = PlayerMark.X;
            }
            reset();
        }
    }
    public boolean makeComputerMoveIfNeeded() {
        if (!vsComputer) {
            return false;
        }
        if (state != GameState.IN_PROGRESS) {
            return false;
        }
        if (currentTurn != aiMark) {
            return false;
        }

        Move move = ai.chooseMove(board, aiMark);
        if (move == null) {
            return false;
        }

        boolean ok = makeMove(move.getRow(), move.getCol());
        return ok;
    }
    public void reset() {
        board.reset();
        currentTurn = PlayerMark.X;
        state = GameState.IN_PROGRESS;
    }

    public Board getBoard() {
        return board;
    }

    public PlayerMark getCurrentTurn() {
        return currentTurn;
    }

    public GameState getState() {
        return state;
    }

    public boolean makeMove(int row, int col) {
        if (state != GameState.IN_PROGRESS) {
            return false;
        }

        boolean placed = board.placeMark(row, col, currentTurn);
        if (!placed) {
            return false;
        }

        state = checker.evaluate(board);

        if (state == GameState.IN_PROGRESS) {
            if (currentTurn == PlayerMark.X) {
                currentTurn = PlayerMark.O;
            } else {
                currentTurn = PlayerMark.X;
            }
        }

        return true;
    }
}
