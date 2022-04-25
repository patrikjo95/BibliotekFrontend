module com.example.bibliotekfrontend {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bibliotekfrontend to javafx.fxml;
    exports com.example.bibliotekfrontend;
}