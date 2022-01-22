package de.bre;

import de.bre.model.PdfPage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PdfProcessor {

    public List<PdfPage> process(final List<PdfPage> pdfPages) {

        for (PdfPage pdfPage : pdfPages) {
            pdfPage.setText(removeHyphens(pdfPage.getText()));
        }

        return pdfPages;
    }

    private String removeHyphens(final String text) {
        Pattern hyphenatedWords = Pattern.compile("[a-zA-Z]*[\\-][\\n][a-zA-Z]*");
        Matcher matcher = hyphenatedWords.matcher(text);

        String newText = text;
        while (matcher.find()) {
            String wordWithHyphen = matcher.group();
            String wordWithoutHyphen = wordWithHyphen.replace("-\n", "") + "\n";

            newText = newText.replace(wordWithHyphen, wordWithoutHyphen);
        }

        return newText;
    }
}
