package de.bre;

import de.bre.model.PdfPage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PdfReader {

    public File getPdfFromResourcesByName(final String filename) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL fileUrl = classLoader.getResource(filename);
        assert fileUrl != null;
        return new File(fileUrl.getFile());
    }

    public List<PdfPage> getPdfPages(final PDDocument document) throws IOException {
        int totalPageCount = document.getNumberOfPages();
        List<PdfPage> pdfPages = stripPdf(document, totalPageCount);

        document.close();
        return pdfPages;
    }

    private ArrayList<PdfPage> stripPdf(PDDocument document, int totalPageCount) throws IOException {
        PDFTextStripper pdfStripper = new PDFTextStripper();
        ArrayList<PdfPage> pdfPages = new ArrayList<>();

        for (int pageNo = 1; pageNo <= totalPageCount; pageNo++) {
            setPdfPageToStrip(pdfStripper, pageNo);
            String text = pdfStripper.getText(document);

            PdfPage page = new PdfPage(pageNo, text);
            pdfPages.add(page);
        }
        return pdfPages;
    }

    private void setPdfPageToStrip(final PDFTextStripper pdfStripper, final int pageNo) {
        pdfStripper.setStartPage(pageNo - 1);
        pdfStripper.setEndPage(pageNo);
    }
}
