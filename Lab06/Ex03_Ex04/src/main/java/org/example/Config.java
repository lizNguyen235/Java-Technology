package org.example;

import Domain.PdfTextWriter;
import Domain.PlainTextWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages="Domain")
        public class Config {

    @Bean
    public PlainTextWriter plainTextWriter() {

        return new PlainTextWriter();
    }

    @Bean
    public PdfTextWriter pdfTextWriter() {
        return new PdfTextWriter();
    }

}
