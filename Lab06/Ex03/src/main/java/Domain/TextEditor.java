package Domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class TextEditor {
    private String text;
    private TextWriter writer;


    public TextEditor(TextWriter writer) {
        this.text = null;
        this.writer = writer;
    }

    public TextEditor() {}

    public void input(String text) {
        this.text = text;
    }

    public void save(String filename) {
        try {
            writer.write(filename, text);
        }catch (NullPointerException e) {
            System.out.println("filename or text is null");
        }
    }
}
