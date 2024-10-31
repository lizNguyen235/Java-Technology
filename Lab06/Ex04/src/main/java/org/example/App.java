package org.example;

import Domain.TextEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        TextEditor textEditor =  context.getBean(TextEditor.class);
        textEditor.input("anh yeu em nhieu lam huhu");
        textEditor.save("C:\\Lit\\Programming\\java\\abc.txt");
    }
}
