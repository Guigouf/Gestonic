package com.guibou.gestonic.parser.word;

import javafx.beans.property.StringProperty;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateNoteDeFrais {

    private String templateFile = "";
    private final List<String> keysParagraph = new ArrayList<>();
    private final List<String> keysTable = new ArrayList<>();
    private final Map<String, StringProperty> keyToValues = new HashMap<>();

    public void readTemplate(final String file) {
        // CLear template data if new data are to be loaded.
        if (!templateFile.isEmpty()) {
            clearTemplate();
        }

        templateFile = file;

        // Read the file and find keys written <key> in the document
        try {
            FileInputStream fis = new FileInputStream(templateFile);
            XWPFDocument doc = new XWPFDocument(OPCPackage.open(fis));

            keysParagraph.addAll(DocReader.readKeysOnDocument(doc));
            keysTable.addAll(DocReader.readKeysOnTable(doc));

            doc.close();

            System.out.println("keysParagraph : " + keysParagraph);
            System.out.println("keysTable : " + keysTable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearTemplate() {
        keysTable.clear();
        keysParagraph.clear();
        keyToValues.clear();
    }

    public void addFilledKeyValue(final String key, final StringProperty valueFilled) {
        keyToValues.put(key, valueFilled);
    }

    public void printValues() {
        keyToValues.forEach((key, value) -> System.out.println(key + " : " + value.getValue()));
    }

    public List<String> getKeysParagraph() {
        return List.copyOf(keysParagraph);
    }

    public List<String> getKeysTable() {
        return List.copyOf(keysTable);
    }
}
