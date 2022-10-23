package com.guibou.gestonic.parser.exemples;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

public class TableReader {

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("D:\\Documents\\Musique\\Administration\\Supersonic\\Compte Rendu AG 2020.docx");
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            Iterator<IBodyElement> bodyElementIterator = xdoc.getBodyElementsIterator();
            while (bodyElementIterator.hasNext()) {
                IBodyElement element = bodyElementIterator.next();

                if ("TABLE".equalsIgnoreCase(element.getElementType().name())) {
                    List<XWPFTable> tableList = element.getBody().getTables();
                    for (XWPFTable table : tableList) {
                        System.out.println("Total Number of Rows of Table:" + table.getNumberOfRows());
                        for (int i = 0; i < table.getRows().size(); i++) {

                            for (int j = 0; j < table.getRow(i).getTableCells().size(); j++) {
                                System.out.println(table.getRow(i).getCell(j).getText());
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}