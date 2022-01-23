package de.bre.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WordOccurrence {

    @Getter
    private Integer occurrencesCount = 0;

    public void addOccurrence() {
        occurrencesCount += 1;
    }
}
