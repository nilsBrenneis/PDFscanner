package de.bre;

import de.bre.model.PdfPage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PdfProcessor {

    public List<PdfPage> process(final List<PdfPage> pdfPages) {

        for (PdfPage pdfPage : pdfPages) {
            String pageText = pdfPage.getText();
            pdfPage.setText(removeHyphens(pageText));
            pageText = pdfPage.getText();
            pdfPage.setText(removeLinefeed(pageText));
        }

        return pdfPages;
    }

    private String removeLinefeed(final String pageText) {
        return pageText.replace("\n", "");
    }

    private String removeHyphens(final String pageText) {
        Pattern hyphenatedWordsPattern = Pattern.compile("[a-zA-Z]*[\\-][\\n][a-zA-Z]*");
        Matcher hyphenatedWordsMatcher = hyphenatedWordsPattern.matcher(pageText);

        String pageTextWorkingCopy = pageText;
        while (hyphenatedWordsMatcher.find()) {
            pageTextWorkingCopy = replaceFinding(hyphenatedWordsMatcher, pageTextWorkingCopy);
        }

        return pageTextWorkingCopy;
    }

    private String replaceFinding(final Matcher matcher, final String text) {
        String wordWithHyphen = matcher.group();
        String wordWithoutHyphen = wordWithHyphen.replace("-\n", "") + "\n";

        return text.replace(wordWithHyphen, wordWithoutHyphen);
    }
}
