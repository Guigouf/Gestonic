package com.guibou.gestonic.report;

import com.guibou.gestonic.model.TemplateModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ExpenseReportController implements Initializable {

    @FXML
    public ComboBox membersComboBox;
    @FXML
    public ChoiceBox membersChoiceBox;
    @FXML
    public GridPane memberDataGrid;
    @FXML
    public TableView<List<String>> reportTable;
    @FXML
    TemplateModel templateModel;

    public ExpenseReportController(TemplateModel pTemplateModel) {
        this.templateModel = pTemplateModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        templateModel.memberData().forEach(this::fillMemberDataInGrid);
        templateModel.tableColums().forEach(this::initiateTableReport);

        reportTable.getItems().add(FXCollections.observableArrayList("", "", ""));
    }

    private void fillMemberDataInGrid(final String dataName) {
        final Label titleData = new Label(dataName);
        final TextField dataField = new TextField();
        StringProperty textValue = new SimpleStringProperty();
        textValue.bind(dataField.promptTextProperty());
        final int indexLine = memberDataGrid.getRowCount();
        memberDataGrid.add(titleData, 0, indexLine);
        memberDataGrid.add(dataField, 1, indexLine);
    }

    private void initiateTableReport(final String columnName) {
        final TableColumn<List<String>, String> column = new TableColumn<>(columnName);
        reportTable.getColumns().add(column);
        column.setCellFactory(TextFieldTableCell.forTableColumn());
    }
}
