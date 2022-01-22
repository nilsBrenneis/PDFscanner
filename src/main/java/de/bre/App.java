package de.bre;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        PdfReader pdfReader = new PdfReader();
        File file = pdfReader.getFile();
        PDDocument document = PDDocument.load(file);
        pdfReader.readPDF(document);
    }
}
