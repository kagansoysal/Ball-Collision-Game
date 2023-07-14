import java.util.ArrayList;

public class Playing {
    ArrayList<String> move;
    Output output = new Output();

    public Playing(int row, int column, ArrayList<ArrayList<String>> board, ArrayList<String> move) {
        this.move = move;
        Movement movement = new Movement(row, column, board);
        beforeGameOutput(movement);
        game(move, movement);
        afterGameOutput(movement);
    }

    public void game(ArrayList<String> move, Movement movement){
        for (String command : move) {
            if (movement.symbolWhite.equals("H")) {break;
            }
            output.writeFile(command + " ");
            switch (command) {
                case "L":
                    movement.left();
                    break;
                case "R":
                    movement.right();
                    break;
                case "U":
                    movement.up();
                    break;
                case "D":
                    movement.down();
            }
        }
    }

    private void beforeGameOutput(Movement movement){
        output.writeFile("Game board:\n");
        output.writeBoard(movement.board);
        output.writeFile("Your movement is:\n");
    }

    private void afterGameOutput(Movement movement){
        output.writeFile("\n\nYour output is:\n");
        output.writeBoard(movement.board);
        if (movement.symbolWhite.equals("H")) {output.writeFile("Game Over:\n");
        }
        output.writeFile("Score: " + movement.score);
    }
}
