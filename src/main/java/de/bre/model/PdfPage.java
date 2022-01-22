package de.bre.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PdfPage {

    @NonNull
    private int pageNo;

    @NonNull
    private String text;
}
