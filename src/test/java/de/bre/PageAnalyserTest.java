package de.bre;

import de.bre.model.PdfPage;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class PageAnalyserTest extends TestCase {

    private List<PdfPage> testPdfPages;

    @Before
    public void setUp() throws Exception {
        final String pageOneText = "DAS IST EIN TEXT DER B3S ENTHÄLT";
        final String pageTwoText = "DAS IST DIE ZWEITE SEITE ISOABC WIRD ALS WORT ISO NICHT ERKANNT";
        final String pageThreeText = "EIN TEXT MIT ISO ABER AUCH MIT GELTUNGSBEREICH";

        testPdfPages = new ArrayList<>();
        PdfPage pdfPageOne = new PdfPage(1, pageOneText);
        PdfPage pdfPageTwo = new PdfPage(2, pageTwoText);
        PdfPage pdfPageThree = new PdfPage(3, pageThreeText);
        testPdfPages.add(pdfPageOne);
        testPdfPages.add(pdfPageTwo);
        testPdfPages.add(pdfPageThree);
    }

    @Test
    public void testCountWords_GivenPdfPages_ShouldReturnWordOccurrences() {
        PageAnalyser pageAnalyser = new PageAnalyser();
        pageAnalyser.countWords(testPdfPages);

        int occurrenceCountB3sPageOne = testPdfPages.get(0).getWordsOccurrence().get("B3S").getOccurrencesCount();
        int occurrenceCountGesetzPageOne = testPdfPages.get(0).getWordsOccurrence().get("GESETZ").getOccurrencesCount();
        assertEquals(1, occurrenceCountB3sPageOne);
        assertEquals(0, occurrenceCountGesetzPageOne);

        int occurrenceCountIsoPageTwo = testPdfPages.get(1).getWordsOccurrence().get("ISO").getOccurrencesCount();
        int occurrenceCountGesetzPageTwo = testPdfPages.get(1).getWordsOccurrence().get("GESETZ").getOccurrencesCount();
        assertEquals(1, occurrenceCountIsoPageTwo);
        assertEquals(0, occurrenceCountGesetzPageTwo);

        int occurrenceCountIsoPageThree = testPdfPages.get(2).getWordsOccurrence().get("ISO").getOccurrencesCount();
        int occurrenceCountGeltungsbereichPageThree =
                testPdfPages.get(2).getWordsOccurrence().get("GELTUNGSBEREICH").getOccurrencesCount();
        assertEquals(1, occurrenceCountIsoPageThree);
        assertEquals(1, occurrenceCountGeltungsbereichPageThree);
    }
}