import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Output {
    public Output(){
        try {new BufferedWriter(new FileWriter("output.txt", false));
        }catch (
                IOException e) {
            e.printStackTrace();
        }
    }
    public void writeFile(String text) {
        try {BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
            writer.write(text);
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeBoard(ArrayList<ArrayList<String>> board){
        board.forEach(line -> {
            for (Object item : line) {
                writeFile(item + " ");
            }
            writeFile("\n");
        });
        writeFile("\n");
    }
}
