package de.bre.pdf;

import de.bre.model.PdfPage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PdfReader {

    public List<Path> getPdfPathsFromResourceFolder() throws IOException {
        List<Path> fileNames = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get("./src/main/resources/"))) {
            paths.filter(Files::isRegularFile).forEach(fileNames::add);
        }
        return fileNames;
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
