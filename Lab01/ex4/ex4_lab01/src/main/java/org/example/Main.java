package org.example;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.routines.UrlValidator;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please specify an URL to a file");
            return;
        }

        String fileUrl = args[0];
        UrlValidator urlValidator = new UrlValidator();

        if (!urlValidator.isValid(fileUrl)) {
            System.out.println("This is not a valid URL");
            return;
        }

        try {
            URL url = new URL(fileUrl);
            String fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
            FileUtils.copyURLToFile(url, new File(fileName));
            System.out.println("File downloaded: " + fileName);
        } catch (MalformedURLException e) {
            System.out.println("This is not a valid URL");
        } catch (IOException e) {
            System.out.println("Error downloading file: " + e.getMessage());
        }
    }
}