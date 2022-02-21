module com.example.projetks {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    requires javafx.swing;


    opens com.example.projetks to javafx.fxml;
    exports com.example.projetks;
}