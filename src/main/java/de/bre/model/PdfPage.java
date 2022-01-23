package de.bre.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@RequiredArgsConstructor
public class PdfPage {

    @NonNull
    private int pageNo;

    @NonNull
    private String text;

    HashMap<String, WordOccurrence> wordsOccurrence = new HashMap<>();
}
