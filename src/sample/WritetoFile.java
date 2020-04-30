package sample;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WritetoFile {

    public static void writetoFile(String temp) {
        FileWriter write ;
        try {
            write = new FileWriter("assets.csv",  true);
            PrintWriter out = new PrintWriter(write);
            out.println(temp);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
