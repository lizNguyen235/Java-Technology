package Domain;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PlainTextWriter implements TextWriter {
    @Override
    public void write(String fileName, String text) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(new FileWriter(file));
            out.println(text);
            out.close();
            System.out.println("File written to " + fileName);
            System.out.println("Content " + text);
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
