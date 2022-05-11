module com.example.bibliotekfrontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    //requires com.google.gson;
    requires json.simple;
    requires com.google.gson;
    requires org.json;


    opens com.example.bibliotekfrontend to javafx.fxml;
    exports com.example.bibliotekfrontend;
}