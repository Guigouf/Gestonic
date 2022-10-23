package com.guibou.gestonic;

import com.guibou.gestonic.model.TemplateModel;
import com.guibou.gestonic.parser.word.DocReader;
import com.guibou.gestonic.parser.word.TemplateNoteDeFrais;
import com.guibou.gestonic.report.ExpenseReportController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class TemplateChoiceController {

    @FXML
    private Label welcomeText;

    @FXML
    private Button loadTemplate;

    @FXML
    private Button generateButton;

    @FXML
    private AnchorPane parent;

    @FXML
    private GridPane keysGridPane;

    ResizeListener resizeListener;

    private final TemplateNoteDeFrais templateNoteDeFrais = new TemplateNoteDeFrais();

    @FXML
    protected void onLoadButtonClick() {
        // Get the chosen file
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(parent.getScene().getWindow());
        // Read the chosen file
        if (file != null) {

            // Read the file and find keys written <key> in the document
            try {
                final String templateFile = file.getAbsolutePath();
                FileInputStream fis = new FileInputStream(templateFile);
                XWPFDocument doc = new XWPFDocument(OPCPackage.open(fis));

                TemplateModel templateModel = DocReader.read(doc);

                doc.close();

                System.out.println("member data : " + templateModel.memberData());
                System.out.println("table columns : " + templateModel.tableColums());

                // Open the expense report window filled with the template model
                final FXMLLoader loader = new FXMLLoader();
                final ExpenseReportController controller = new ExpenseReportController(templateModel);
                loader.setController(controller);
                loader.setLocation(MainApplication.class.getResource("fxml/expenseReport.fxml"));
                AnchorPane pane = loader.load();
                final Scene scene = new Scene(pane);
                ((Stage)parent.getScene().getWindow()).setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }



            /*

            templateNoteDeFrais.clearTemplate();
            final String templateFile = file.getAbsolutePath();
            System.out.println("Lecture File " + file.getName() + " (" + templateFile + ").");
            templateNoteDeFrais.readTemplate(file.getAbsolutePath());
            // Clear display : Remove generate button and all added hbox
            clearDisplay();
            // Update the display
            displayKeysToFill();
            // Add generate button and activate it
            parent.getChildren().add(generateButton);
            generateButton.setDisable(false); */
        }
    }

    /**
     * Clear display : Remove generate button and all added keys
     */
    private void clearDisplay() {
        parent.getChildren().remove(generateButton);
        keysGridPane.getChildren().clear();
    }

    @FXML
    protected void onGenerateButtonClick() {
        templateNoteDeFrais.printValues();
    }

    /**
     * Update the display
     */
    private void displayKeysToFill() {
        final List<String> keysParagraph = templateNoteDeFrais.getKeysParagraph();
        final List<String> keysTable = templateNoteDeFrais.getKeysTable();

        keysParagraph.forEach(this::createAndAddKeyToFill);
        keysTable.forEach(this::createAndAddKeyToFill);
    }

    private void createAndAddKeyToFill(final String key) {
        final Label titleKey = new Label(key);
        final TextField entryKey = new TextField();
        StringProperty textValue = new SimpleStringProperty();
        textValue.bind(entryKey.promptTextProperty());
        final int indexLine = keysGridPane.getRowCount();
        keysGridPane.add(titleKey, 0, indexLine);
        keysGridPane.add(entryKey, 1, indexLine);
        templateNoteDeFrais.addFilledKeyValue(key, entryKey.textProperty());
    }

    public void registerResizeListener(final ResizeListener listener) {
        resizeListener = listener;
    }
}