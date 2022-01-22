package de.bre;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PdfReader {

    public File getFile() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL fileUrl = classLoader.getResource("B3S.pdf");
        assert fileUrl != null;
        return new File(fileUrl.getFile());
    }

    public void readPDF(final PDDocument document) throws IOException {
        int pageCount = document.getNumberOfPages();
        loopOverPages(document, pageCount);

        document.close();
    }

    private void loopOverPages(PDDocument document, int pageCount) throws IOException {
        PDFTextStripper pdfStripper = new PDFTextStripper();
        for (int i = 0; i <= pageCount; i++) {
            pdfStripper.setStartPage(i);
            pdfStripper.setEndPage(i + 1);
            System.out.println("Neue Seite ------------------------------------------------ " + i);
            String text = pdfStripper.getText(document);
            System.out.println(text);
        }
    }
}
