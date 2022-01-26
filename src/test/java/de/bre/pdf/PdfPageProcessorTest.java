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

    private final String pageOneText = "Das ist ein Text, der nicht preprocessed werden soll";
    private final String pageTwoText = "Das ist die erste Sei-te.\nDen Bindestrich bitte nicht entfernen.\nDie linefeeds\naber.";
    private final String pageThreeText = "Das ist die zweite Sei-\nte. Hinfort mit dem Bindestrich";

    @Before
    public void setUp() throws Exception {
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

        String expectedText = pageOneText;
        String actualPdfPage = testPdfPages.get(0).getText();

        assertEquals(expectedText, actualPdfPage);
    }

    @Test
    public void preprocess_GivenPdfPageWithNoTextToPreprocess_ShouldReturnContentWithoutLinefeed() {
        PdfPageProcessor pdfPageProcessor = new PdfPageProcessor();
        pdfPageProcessor.preprocess(testPdfPages);

        String expectedText = "Das ist die erste Sei-te. Den Bindestrich bitte nicht entfernen. Die linefeeds aber.";
        String actualPdfPage = testPdfPages.get(1).getText();

        assertEquals(expectedText, actualPdfPage);
    }

    @Test
    public void preprocess_GivenPdfPageWithNoTextToPreprocess_ShouldReturnContentWithoutHyphens() {
        PdfPageProcessor pdfPageProcessor = new PdfPageProcessor();
        pdfPageProcessor.preprocess(testPdfPages);

        String expectedText = "Das ist die zweite Seite . Hinfort mit dem Bindestrich";
        String actualPdfPage = testPdfPages.get(2).getText();

        assertEquals(expectedText, actualPdfPage);
    }
}