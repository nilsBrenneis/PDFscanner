package de.bre;

import de.bre.model.PdfPage;
import de.bre.model.WordOccurrence;
import de.bre.model.WordsToLookFor;

import java.util.HashMap;
import java.util.List;

public class PageAnalyser {

    public void countWords(final List<PdfPage> pdfPages) {
        for (PdfPage pdfPage : pdfPages) {
            countWordsPerPage(pdfPage);
        }
    }

    private void countWordsPerPage(final PdfPage pdfPage) {
        setHashMapWithWordsToLookFor(pdfPage);
        countWordOccurrences(pdfPage);
    }

    private void countWordOccurrences(PdfPage pdfPage) {
        String[] words = pdfPage.getText().split("\\s+");
        HashMap<String, WordOccurrence> wordOccurrenceHashMap = pdfPage.getWordsOccurrence();

        for (String word : words) {
            if (wordOccurrenceHashMap.containsKey(word)) {
                wordOccurrenceHashMap.get(word).addOccurrence();
            }
        }
    }

    private void setHashMapWithWordsToLookFor(final PdfPage pdfPage) {
        HashMap<String, WordOccurrence> wordOccurrenceHashMap = pdfPage.getWordsOccurrence();

        for (String wordToLookFor : WordsToLookFor.getWordsAsArray(WordsToLookFor.class)) {
            wordOccurrenceHashMap.put(wordToLookFor, new WordOccurrence());
        }

        pdfPage.setWordsOccurrence(wordOccurrenceHashMap);
    }
}
