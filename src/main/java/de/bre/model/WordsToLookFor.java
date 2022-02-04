package de.bre.model;

import java.util.Arrays;

public enum WordsToLookFor {

    GELTUNGSBEREICH("Geltungsbereich"),
    ANWENDUNGSBEREICH("Anwendungsbereich"),
    GESCHAEFTSPROZESS("Geschäftsprozess"),
    SYSTEM("System"),
    GRENZE("Grenze"),
    ABGRENZUNG("Abgrenzung"),
    DIENSTLEISTER("Dienstleister"),
    VERTRAG("Vertrag"),
    VERTRAEGE("Verträge"),
    SERVICE("Service"),
    AUSLAGERUNG("Auslagerung"),
    ABSICHERUNG("Absicherung"),
    SCHUTZZIEL("Schutzziel"),
    GRUNDWERTE("Grundwerte"),
    VERTRAULICHKEIT("Vertraulichkeit"),
    INTEGRITAET("Integrität"),
    VERFUEGBARKEIT("Verfügbarkeit"),
    DATENSCHUTZ("Datenschutz"),
    AUTHENTIZITAET("Authentizität"),
    ENERGIE("Energie"),
    WASSER("Wasser"),
    ERNAEHRUNG("Ernährung"),
    INFORMATIONSTECHNIK("Informationstechnik"),
    TELEKOMMUNIKATION("Telekommunikation"),
    GESUNDHEIT("Gesundheit"),
    FINANZWESEN("Finanzwesen"),
    VERSICHERUNGSWESEN("Versicherungswesen"),
    TRANSPORT("Transport"),
    VERKEHR("Verkehr"),
    GEFAHR("Gefahr"),
    GEFAEHRDUNG("Gefährdung"),
    RISIKOMANAGEMENT("Risikomanagement"),
    RISIKO("Risiko"),
    KATEGORIEN("Kategorien"),
    RISIKOBEHANDLUNG("Risikobehandlung"),
    ERMITTLUNG("Ermittlung"),
    INFORMATIONSWERTE("Informationswerte"),
    INFORMATIONSSICHERHEITSRISIKEN("Informationssicherheitsrisiken"),
    MASSNAHMEN("Maßnahmen"),
    UEBERWACHUNG("Überwachung"),
    WARTUNG("Wartung"),
    WIEDERANLAUF("Wiederanlauf"),
    WIEDERANLAUFKONZEPT("Wiederanlaufkonzept"),
    BACKUP("Backup"),
    UNTERBRECHUNGSFREI("unterbrechungsfrei"),
    VORFALL("Vorfall"),
    MELDUNG("Meldung"),
    DATENVERARBEITUNG("Datenverarbeitung"),
    ITSICHERHEIT("IT-Sicherheit"),
    ANGRIFF("Angriff"),
    VIRUS("Virus"),
    MALWARE("Malware"),
    BOT("Bot"),
    DOS("DoS"),
    DDOS("DDoS"),
    DATENBANK("Datenbank"),
    PERSONAL("Personal"),
    MITARBEITER("Mitarbeiter"),
    FUEHRUNGSZEUGNIS("Führungszeugnis"),
    VERANTWORTUNG("Verantwortung"),
    LEGITIMIERUNG("Legitimierung"),
    DELEGITIMIERUNG("Delegitimierung"),
    PHYSISCH("physisch"),
    PHYSISCHE("physische"),
    ZUGANGSKONTROLLE("Zugangskontrolle"),
    GEBAEUDE("Gebäude"),
    RECHENZENTRUM("Rechenzentrum"),
    SERVER("Server"),
    MELDEPFLICHT("Meldepflicht"),
    SICHERHEITSVORFALL("Sicherheitsvorfall"),
    PROTOKOLL("Protokoll"),
    PROTOKOLLIERUNG("Protokollierung"),
    REVISION("Revision"),
    KONTINUIERLICH("kontinuierlich"),
    KONTINUITAET("Kontinuität"),
    UEBERPRUEFUNG("Überprüfung"),
    PRUEFUNG("Prüfung"),
    KONFORMITAET("Konformität"),
    NACHWEIS("Nachweis"),
    AUDIT("Audit"),
    NACHWEISERBRINGUNG("Nachweiserbringung"),
    ZERTIFIZIERUNG("Zertifizierung"),
    INTERNATIONAL("international"),
    ISO("ISO"),
    NORM("Norm"),
    HINWEISE("Hinweise"),
    DISKUSSION("Diskussion"),
    KONKRET("konkret"),
    BEISPIEL("Beispiel"),
    EIGHTA("8a"),
    BSIGESETZ("BSI-Gesetz"),
    BSIG("BSIG"),
    BSIKRITISV("BSI-KritisV"),
    GESETZGEBER("Gesetzgeber"),
    GESETZ("Gesetz");

    private final String enumValue;

    WordsToLookFor(String word) {
        enumValue = word;
    }

    @Override
    public String toString() {
        return enumValue;
    }

    public static String[] getValuesAsArray(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::toString).toArray(String[]::new);
    }
}

