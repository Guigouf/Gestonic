package com.guibou.gestonic.parser.word;

import com.guibou.gestonic.model.TemplateModel;
import org.apache.poi.xwpf.usermodel.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DocReader {

    public static TemplateModel read(XWPFDocument doc) {
        final List<String> memberData = readKeysOnDocument(doc);
        final List<String> tableColumns = readKeysOnTable(doc);
        return new TemplateModel(memberData, tableColumns);
    }

    public static List<String> readKeysOnDocument(XWPFDocument doc) {
        final List<String> documentKeys = new LinkedList<>();
        for (final XWPFParagraph paragraph : doc.getParagraphs()) {
            for (XWPFRun r : paragraph.getRuns()) {
                String text = r.getText(0);

                while (text != null && text.contains("<") && text.contains(">")) {
                    int indexBegin = text.indexOf("<") + 1;
                    int indexEnd = text.indexOf(">");
                    final String keyToAdd = text.substring(indexBegin, indexEnd);
                    if (!documentKeys.contains(keyToAdd)) {
                        documentKeys.add(keyToAdd);
                    } else {
                        System.out.println("ERROR reading template : 2 identical keys (" + keyToAdd + ").");
                        documentKeys.clear();
                        break;
                    }
                    text = text.substring(indexEnd + 1);
                }
            }
        }
        return documentKeys;
    }

    public static List<String> readKeysOnTable(XWPFDocument doc) {
        final List<String> tableKeys = new ArrayList<>();
        // Récupération des mots à changer dans les tableaux
        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            String text = r.getText(0);
                            System.out.println("texte 0 : " + text);
                            while (text != null && text.contains("<") && text.contains(">")) {
                                int indexBegin = text.indexOf("<") + 1;
                                int indexEnd = text.indexOf(">");
                                final String keyToAdd = text.substring(indexBegin, indexEnd);
                                if (!tableKeys.contains(keyToAdd)) {
                                    tableKeys.add(keyToAdd);
                                } else {
                                    System.out.println("ERROR reading template : 2 identical keys (" + keyToAdd + ").");
                                    tableKeys.clear();
                                    break;
                                }
                                text = text.substring(indexEnd + 1);
                                System.out.println("texte table : " + text);
                            }
                        }
                    }
                }
            }
        }
        return tableKeys;
    }
}
