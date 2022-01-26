package de.bre;

import de.bre.model.PdfPage;
import de.bre.pdf.PdfPageProcessor;
import de.bre.pdf.PdfReader;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        final String documentName = "B3S";

        PdfReader pdfReader = new PdfReader();
        File pdfFile = pdfReader.getPdfFromResourceByName(documentName + ".pdf");
        PDDocument pdfDocument = PDDocument.load(pdfFile);

        List<PdfPage> pdfPages = pdfReader.getPdfPages(pdfDocument);

        PdfPageProcessor pdfPageProcessor = new PdfPageProcessor();
        pdfPageProcessor.preprocess(pdfPages);

        PageAnalyser pageAnalyser = new PageAnalyser();
        pageAnalyser.countWords(pdfPages);

        CsvWriter csvWriter = new CsvWriter();
        csvWriter.createCSVFile(pdfPages, documentName);
    }
}
