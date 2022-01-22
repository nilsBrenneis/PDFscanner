package de.bre;

import de.bre.model.PdfPage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {

    public void createCSVFile(final List<PdfPage> pdfPages) throws IOException {
        try (FileWriter writer = new FileWriter("b3s.csv");
             CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withHeader("Seitennummer", "Text"));) {

            for (PdfPage pdfPage : pdfPages) {
                printer.printRecord(pdfPage.getPageNo(), pdfPage.getText());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
