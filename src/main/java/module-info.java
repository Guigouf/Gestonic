module com.guibou.gestonic {
    requires javafx.controls;
    requires javafx.fxml;
    requires poi.ooxml;

    opens com.guibou.gestonic to javafx.fxml;
    opens com.guibou.gestonic.report to javafx.fxml;
    exports com.guibou.gestonic;
    exports com.guibou.gestonic.parser.exemples;
    opens com.guibou.gestonic.parser.exemples to javafx.fxml;
}