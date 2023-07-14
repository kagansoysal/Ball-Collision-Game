import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String textFile = args[0];
        String textFile2 = args[1];
        ArrayList<String> boardTxt = readFile(textFile);
        ArrayList<String> moveTxt = readFile(textFile2);
        ArrayList<ArrayList<String>> board = getBoard(boardTxt);
        ArrayList<String> move = getMoves(moveTxt.get(0));
        getLocation(board, move);
    }

    private static void getLocation(ArrayList<ArrayList<String>> board, ArrayList<String> move ){
        for (ArrayList<String> line : board) {
            for (String item : line) {
                if (item.equals("*") ) {
                    new Playing(board.indexOf(line), line.indexOf(item), board, move);
                    return;
                }
            }
        }
    }

    private static ArrayList<ArrayList<String>> getBoard(ArrayList<String> boardLines){
        ArrayList<ArrayList<String>> board = new ArrayList<>();

        for (String line : boardLines){
            ArrayList<String> boardLine = new ArrayList<>();
            for (char item : line.toCharArray()){
                if (item != ' ') {boardLine.add(String.valueOf(item));
                }
            }
            board.add(boardLine);
        }

        return board;
    }

    private static ArrayList<String> getMoves(String moveLine){
        ArrayList<String> moves = new ArrayList<>();
        for (char item : moveLine.toCharArray()){
            if (item != ' ') {moves.add(String.valueOf(item));
            }
        }
        return moves;
    }

    public static ArrayList<String> readFile(String path) {
        try {return new ArrayList<>(Files.readAllLines(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}