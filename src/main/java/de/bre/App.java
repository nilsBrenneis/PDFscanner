package de.bre;

import de.bre.model.PdfPage;
import de.bre.pdf.PdfPageProcessor;
import de.bre.pdf.PdfReader;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {

        PdfReader pdfReader = new PdfReader();
        PdfPageProcessor pdfPageProcessor = new PdfPageProcessor();
        PageAnalyser pageAnalyser = new PageAnalyser();
        CsvWriter csvWriter = new CsvWriter();

        List<Path> pdfFilePaths = pdfReader.getPdfPathsFromResourceFolder();

        for (Path pdfFilePath : pdfFilePaths) {
            File pdfFile = pdfFilePath.toFile();
            PDDocument pdfDocument = PDDocument.load(pdfFile);

            List<PdfPage> pdfPages = pdfReader.getPdfPages(pdfDocument);

            pdfPageProcessor.preprocess(pdfPages);
            pageAnalyser.countWords(pdfPages);
            csvWriter.createCSVFile(pdfPages, pdfFile.getName());

        }
    }
}
