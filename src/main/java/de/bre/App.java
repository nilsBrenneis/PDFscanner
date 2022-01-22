package de.bre;

import de.bre.model.PdfPage;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        PdfReader pdfReader = new PdfReader();
        File pdfFile = pdfReader.getPdfFromResourcesByName("B3S.pdf");
        PDDocument pdfDocument = PDDocument.load(pdfFile);

        List<PdfPage> pdfPages = pdfReader.getPdfPages(pdfDocument);

        PdfProcessor pdfProcessor = new PdfProcessor();
        pdfPages = pdfProcessor.process(pdfPages);

        CsvWriter csvWriter = new CsvWriter();
        csvWriter.createCSVFile(pdfPages);

        for (PdfPage pdfPage : pdfPages) {
            System.out.println("Seite: " + pdfPage.getPageNo() + " --------------------------------------------" +
                    "------------------------------------------------------");
            System.out.println(pdfPage.getText());
        }
    }
}
