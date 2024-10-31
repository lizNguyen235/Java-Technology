package org.example;

import Domain.PdfTextWriter;
import Domain.PlainTextWriter;
import Domain.TextEditor;
import Domain.TextWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {
    @Bean
    public PdfTextWriter pdfTextWriter() {
        return new PdfTextWriter();
    }
    @Bean
    public PlainTextWriter plainTextWriter() {
        return new PlainTextWriter();
    }
    @Bean
    @Autowired
    public TextEditor textEditor(@Qualifier("plainTextWriter") TextWriter textEditor) {
        return new TextEditor(textEditor);
    }
}
