package de.bre;

import de.bre.model.PdfPage;
import de.bre.model.WordsToLookFor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {

    public void createCSVFile(final List<PdfPage> pdfPages) {
        try (FileWriter writer = new FileWriter("b3s.csv");
             CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withHeader("Seitennummer", "Text",
                             WordsToLookFor.B3S.toString(),
                             WordsToLookFor.STANDARD.toString(),
                             WordsToLookFor.ISO.toString(),
                             WordsToLookFor.INFRASTRUKTUR.toString(),
                             WordsToLookFor.MITARBEITER.toString(),
                             WordsToLookFor.ANGESTELLTE.toString(),
                             WordsToLookFor.RISIKO.toString(),
                             WordsToLookFor.EIGHTA.toString()))) {

            printRecordsIntoCsv(pdfPages, printer);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printRecordsIntoCsv(List<PdfPage> pdfPages, CSVPrinter printer) throws IOException {
        for (PdfPage pdfPage : pdfPages) {
            printer.printRecord(pdfPage.getPageNo(), pdfPage.getText(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.B3S.toString()).getOccurrencesCount(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.STANDARD.toString()).getOccurrencesCount(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.ISO.toString()).getOccurrencesCount(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.INFRASTRUKTUR.toString()).getOccurrencesCount(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.MITARBEITER.toString()).getOccurrencesCount(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.ANGESTELLTE.toString()).getOccurrencesCount(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.RISIKO.toString()).getOccurrencesCount(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.EIGHTA.toString()).getOccurrencesCount());
        }
    }
}
