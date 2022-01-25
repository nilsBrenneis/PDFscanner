package de.bre.pdf;

import de.bre.model.PdfPage;
import junit.framework.TestCase;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@RunWith(JUnit4.class)
public class PdfReaderTest extends TestCase {

    private PDDocument pdfDocument;

    @Before
    public void createPDDocument() throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL fileUrl = classLoader.getResource("test.pdf");
        File pdfFile = new File(fileUrl.getFile());
        pdfDocument = PDDocument.load(pdfFile);
    }

    @Test
    public void getPdfPages_GivenPdfFromResource_ShouldHaveFourPages() throws IOException {
        PdfReader pdfReader = new PdfReader();
        List<PdfPage> pdfPages = pdfReader.getPdfPages(pdfDocument);

        assertEquals(pdfPages.size(), 4);
    }

    @Test
    public void getPdfPages_GivenPdfFromResource_ShouldReturnContentAsString() throws IOException {
        PdfReader pdfReader = new PdfReader();
        List<PdfPage> pdfPages = pdfReader.getPdfPages(pdfDocument);

        String expectedTextPageAtZero = "test\nnils.brenneis\nJanuary 2022\n1 Introduction\n1\n";
        String expectedTextPageAtOne = "hier ist auch text\n2\n";
        String expectedTextPageAtTwo = "hier ein paar Schlagwörter: B3S, Betreiber\n3\n";
        String expectedTextPageAtThree = "Auch hier findet sich Text. Straße, UTF-8 öä\n4\n";

        assertEquals(expectedTextPageAtZero, pdfPages.get(0).getText());
        assertEquals(expectedTextPageAtOne, pdfPages.get(1).getText());
        assertEquals(expectedTextPageAtTwo, pdfPages.get(2).getText());
        assertEquals(expectedTextPageAtThree, pdfPages.get(3).getText());
    }

    @Test
    public void getPdfPages_GivenNullPDDocument_ShouldReturnEmptyList() throws IOException {
        PdfReader pdfReader = new PdfReader();
        List<PdfPage> pdfPages = pdfReader.getPdfPages(null);

        assertEquals(pdfPages.size(), 0);
    }
}