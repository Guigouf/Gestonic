package com.guibou.gestonic.parser.exemples;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;

public class TextReader {

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("D:\\Documents\\Musique\\Administration\\Supersonic\\Compte Rendu AG 2020.docx");
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
            System.out.println(extractor.getText());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
