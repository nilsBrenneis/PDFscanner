package de.bre;

import de.bre.model.PdfPage;
import de.bre.model.WordsToLookFor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class CsvWriter {

    public void createCSVFile(final List<PdfPage> pdfPages) {
        String[] header = getHeader();

        try (FileWriter writer = new FileWriter("b3s.csv");
             CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(header))) {

            printRecordsIntoCsv(pdfPages, printer);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String[] getHeader() {
        String[] staticHeaderPart = new String[]{"Seitennummer", "Text"};
        String[] wordsToLookForHeaderPart = WordsToLookFor.getAsArray(WordsToLookFor.class);
        return Stream.of(staticHeaderPart, wordsToLookForHeaderPart)
                .flatMap(Stream::of)
                .toArray(String[]::new);
    }

    private void printRecordsIntoCsv(List<PdfPage> pdfPages, CSVPrinter printer) throws IOException {
        for (PdfPage pdfPage : pdfPages) {
            printer.printRecord(pdfPage.getPageNo(), pdfPage.getText(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.B3S.toString()).getOccurrencesCount(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.STANDARD.toString()).getOccurrencesCount(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.ISO.toString()).getOccurrencesCount(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.GESETZ.toString()).getOccurrencesCount(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.INFRASTRUKTUR.toString()).getOccurrencesCount(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.MITARBEITER.toString()).getOccurrencesCount(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.ANGESTELLTE.toString()).getOccurrencesCount(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.RISIKO.toString()).getOccurrencesCount(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.EIGHTA.toString()).getOccurrencesCount(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.GELTUNGSBEREICH.toString()).getOccurrencesCount(),
                    pdfPage.getWordsOccurrence().get(WordsToLookFor.BETREIBER.toString()).getOccurrencesCount());
        }
    }
}
