package de.bre.pdf;

import de.bre.model.PdfPage;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class PdfPageProcessorTest extends TestCase {

    private List<PdfPage> testPdfPages;

    @Before
    public void setUp() {
        final String pageOneText = "Das ist ein Text, dessen Komma fehlen wird";
        final String pageTwoText = "Das ist die zweite Sei-te.\n" +
                "Den Binde-Strich bitte entfernen.\nDie linefeeds\nauch.";
        final String pageThreeText = "Das ist die dritte Sei-\nte. Hinfort mit dem Bindestrich";

        testPdfPages = new ArrayList<>();
        PdfPage pdfPageOne = new PdfPage(1, pageOneText);
        PdfPage pdfPageTwo = new PdfPage(2, pageTwoText);
        PdfPage pdfPageThree = new PdfPage(3, pageThreeText);
        testPdfPages.add(pdfPageOne);
        testPdfPages.add(pdfPageTwo);
        testPdfPages.add(pdfPageThree);
    }

    @Test
    public void preprocess_GivenPdfPageWithNoTextToPreprocess_ShouldReturnUnchangedContent() {
        PdfPageProcessor pdfPageProcessor = new PdfPageProcessor();
        pdfPageProcessor.preprocess(testPdfPages);

        String expectedText = "DAS IST EIN TEXT DESSEN KOMMA FEHLEN WIRD";
        String actualPdfPage = testPdfPages.get(0).getText();

        assertEquals(expectedText, actualPdfPage);
    }

    @Test
    public void preprocess_GivenPdfPageWithNoTextToPreprocess_ShouldReturnContentWithoutLinefeed() {
        PdfPageProcessor pdfPageProcessor = new PdfPageProcessor();
        pdfPageProcessor.preprocess(testPdfPages);

        String expectedText = "DAS IST DIE ZWEITE SEITE DEN BINDESTRICH BITTE ENTFERNEN DIE LINEFEEDS AUCH";
        String actualPdfPage = testPdfPages.get(1).getText();

        assertEquals(expectedText, actualPdfPage);
    }

    @Test
    public void preprocess_GivenPdfPageWithNoTextToPreprocess_ShouldReturnContentWithoutHyphens() {
        PdfPageProcessor pdfPageProcessor = new PdfPageProcessor();
        pdfPageProcessor.preprocess(testPdfPages);

        String expectedText = "DAS IST DIE DRITTE SEITE  HINFORT MIT DEM BINDESTRICH";
        String actualPdfPage = testPdfPages.get(2).getText();

        assertEquals(expectedText, actualPdfPage);
    }
}