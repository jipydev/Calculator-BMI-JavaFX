module com.mycompany.bmicalculator {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.bmicalculator to javafx.fxml;
    opens com.mycompany.bmicalculator.controller to javafx.fxml;
    exports com.mycompany.bmicalculator;
    exports com.mycompany.bmicalculator.model;
}
