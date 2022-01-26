package de.bre;

import de.bre.model.PdfPage;
import de.bre.model.WordOccurrence;
import de.bre.model.WordsToLookFor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
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
        String pageNoHeader = "Seitennummer";
        String textHeader = "Text";
        String[] staticHeaderPart = new String[]{pageNoHeader, textHeader};
        String[] wordsToLookForHeaderPart = WordsToLookFor.getAsArray(WordsToLookFor.class);
        return Stream.of(staticHeaderPart, wordsToLookForHeaderPart)
                .flatMap(Stream::of)
                .toArray(String[]::new);
    }

    private void printRecordsIntoCsv(final List<PdfPage> pdfPages, final CSVPrinter printer) throws IOException {
        String[] wordsToLookFor = WordsToLookFor.getAsArray(WordsToLookFor.class);
        for (PdfPage pdfPage : pdfPages) {
            int[] wordOccurrenceCounts = getWordOccurrenceCounts(wordsToLookFor, pdfPage);
            String[] printRecordValues = getPrintRecordValues(pdfPage, wordOccurrenceCounts);

            printer.printRecord((Object[]) printRecordValues);
        }
    }

    private String[] getPrintRecordValues(PdfPage pdfPage, int[] wordOccurrenceCount) {
        String[] wordOccurrencePrintRecordValues = Arrays.stream(wordOccurrenceCount)
                .mapToObj(String::valueOf).toArray(String[]::new);

        String[] pageNoAndPageTextPrintRecordValues = new String[]{String.valueOf(
                pdfPage.getPageNo()), pdfPage.getText()};

        return Stream.of(pageNoAndPageTextPrintRecordValues, wordOccurrencePrintRecordValues)
                .flatMap(Stream::of)
                .toArray(String[]::new);
    }

    private int[] getWordOccurrenceCounts(String[] wordsToLookFor, PdfPage pdfPage) {
        int[] wordOccurrenceCount = new int[WordsToLookFor.values().length];
        for (int i = 0; i < wordOccurrenceCount.length; i++) {
            WordOccurrence wordOccurrence = pdfPage.getWordsOccurrence().get(wordsToLookFor[i]);
            wordOccurrenceCount[i] = wordOccurrence.getOccurrencesCount();
        }
        return wordOccurrenceCount;
    }
}
