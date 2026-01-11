package tictactoecoree;

public class TestMain {
    public static void main(String[] args) {
        GameEngine engine = new GameEngine();

        System.out.println("Initial state: " + engine.getState());
        System.out.println("Initial turn: " + engine.getCurrentTurn());

        // X move
        System.out.println("Move X (0,0): " + engine.makeMove(0, 0));
        System.out.println("Turn after move: " + engine.getCurrentTurn());

        // O move
        System.out.println("Move O (1,1): " + engine.makeMove(1, 1));
        System.out.println("Turn after move: " + engine.getCurrentTurn());

        // Invalid move: same spot
        System.out.println("Invalid overwrite (1,1): " + engine.makeMove(1, 1));
        System.out.println("State: " + engine.getState());

        // X move
        System.out.println("Move X (0,1): " + engine.makeMove(0, 1));
        // O move
        System.out.println("Move O (2,2): " + engine.makeMove(2, 2));
        // X move (this should win row 0)
        System.out.println("Move X (0,2): " + engine.makeMove(0, 2));

        System.out.println("Final state: " + engine.getState());

        // Print board
        Board b = engine.getBoard();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                System.out.print(b.getMark(r, c) + " ");
            }
            System.out.println();
        }
    }
}