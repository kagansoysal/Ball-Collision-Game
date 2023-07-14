import java.util.ArrayList;
import java.util.Objects;

public class Movement {
    int score;
    int rowOfWhite;
    int columnOfWhite;
    String symbolWhite = "*";
    ArrayList<ArrayList<String>> board;

    public Movement(int row, int column, ArrayList<ArrayList<String>> board) {
        this.rowOfWhite = row;
        this.columnOfWhite = column;
        this.board = board;
    }

    public void left() {
        changing(this.rowOfWhite, this.columnOfWhite - 1);
    }

    public void right() {
        changing(this.rowOfWhite, this.columnOfWhite + 1);
    }

    public void up() {
        changing(this.rowOfWhite - 1, this.columnOfWhite);
    }

    public void down() {
        changing(this.rowOfWhite + 1, this.columnOfWhite);
    }

    private void changing(int rowO, int columnO) {
        rowO = rules(rowO, columnO)[0];
        columnO = rules(rowO, columnO)[1];
        String valueO = this.board.get(rowO).get(columnO);

        switch (valueO) {
            case "R":
                score += 10;
                valueO = "X";
                break;
            case "Y":
                score += 5;
                valueO = "X";
                break;
            case "B":
                score -= 5;
                valueO = "X";
                break;
            case "H":
                symbolWhite = "H";
                valueO = " ";
        }
        this.board.get(rowO).set(columnO, symbolWhite);
        this.board.get(this.rowOfWhite).set(this.columnOfWhite, valueO);
        this.rowOfWhite = rowO;
        this.columnOfWhite = columnO;
    }

    private int[] wall(int rowO, int columnO) {
        return new int[]{2 * (rowOfWhite - rowO), 2 * (columnOfWhite - columnO)};
    }

    private int[] outOfSize(int rowO, int columnO) {
        if (rowO == board.size()) {
            rowO = 0;
        } else if (rowO == -1) {
            rowO = board.size() - 1;
        } else if (columnO == board.get(rowO).size()) {
            columnO = 0;
        } else if (columnO == -1) {
            columnO = board.get(rowO).size() - 1;
        }
        return new int[]{rowO, columnO};
    }

    private int[] rules(int rowO, int columnO) {
        int exRow = rowO;
        int exColumn = columnO;

        rowO = outOfSize(rowO, columnO)[0];
        columnO = outOfSize(rowO, columnO)[1];

        if (Objects.equals(this.board.get(rowO).get(columnO), "W")) {
            exRow += wall(exRow, exColumn)[0];
            exColumn += wall(exRow, exColumn)[1];
        }
        rowO = outOfSize(exRow, exColumn)[0];
        columnO = outOfSize(exRow, exColumn)[1];
        return new int[]{rowO, columnO};
    }
}