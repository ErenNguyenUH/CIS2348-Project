package sample;

import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateFile {
    public static void updateFile(ObservableList<Asset> temp) {
        FileWriter write ;
        try {
            write = new FileWriter("assets.csv");
            PrintWriter out = new PrintWriter(write);
            for(Asset t: temp) {
                out.println(t.line());
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
