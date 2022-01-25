package de.bre.pdf;

import de.bre.model.PdfPage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PdfReader {

    public File getPdfFromResourceByName(final String filename) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL fileUrl = classLoader.getResource(filename);
        assert fileUrl != null;
        return new File(fileUrl.getFile());
    }

    public List<PdfPage> getPdfPages(final PDDocument document) throws IOException {
        List<PdfPage> pdfPages = new ArrayList<>();

        if (document != null) {
            stripPdf(document, pdfPages);
            document.close();
        }

        return pdfPages;
    }

    private void stripPdf(PDDocument document, final List<PdfPage> pdfPages) throws IOException {
        PDFTextStripper pdfStripper = new PDFTextStripper();
        int totalPageCount = document.getNumberOfPages();

        for (int pageNo = 1; pageNo <= totalPageCount; pageNo++) {
            setPdfPageToStrip(pdfStripper, pageNo);
            String text = pdfStripper.getText(document);

            PdfPage page = new PdfPage(pageNo, text);
            pdfPages.add(page);
        }
    }

    private void setPdfPageToStrip(final PDFTextStripper pdfStripper, final int pageNo) {
        pdfStripper.setStartPage(pageNo);
        pdfStripper.setEndPage(pageNo);
    }
}
