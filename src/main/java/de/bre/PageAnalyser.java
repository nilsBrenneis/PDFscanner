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
        HashMap<String, WordOccurrence> wordOccurrenceHashMap = getHashMapWithWordsToLookFor(pdfPage);
        countWordOccurrences(pdfPage, wordOccurrenceHashMap);
    }

    private void countWordOccurrences(PdfPage pdfPage, HashMap<String, WordOccurrence> wordOccurrenceHashMap) {
        String[] words = pdfPage.getText().split("\\s+");
        for (String word : words) {
            if (wordOccurrenceHashMap.containsKey(word)) {
                wordOccurrenceHashMap.get(word).addOccurrence();
            }
        }
    }

    private HashMap<String, WordOccurrence> getHashMapWithWordsToLookFor(final PdfPage pdfPage) {
        HashMap<String, WordOccurrence> wordOccurrenceHashMap = pdfPage.getWordsOccurrence();
        for (String wordToLookFor : WordsToLookFor.getWordsAsArray(WordsToLookFor.class)) {
            wordOccurrenceHashMap.put(wordToLookFor, new WordOccurrence());
        }
        return wordOccurrenceHashMap;
    }
}
