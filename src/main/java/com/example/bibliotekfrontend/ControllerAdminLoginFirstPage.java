package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAdminLoginFirstPage {



    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Button AddBookButton;
    @FXML
    private Button UpdateBookButton;
    @FXML
    private Button DeleteBookButton;
    @FXML
    private Button ShowBookButton;
    @FXML
    private Button cAdminLogoutButton;


    @FXML
    private void cGoToAddBookPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("adminAddBook.fxml");
    }

    @FXML
    private void cGoToUpdateBookPage(ActionEvent event) {
    }

    @FXML
    private void cGoToDeleteBookPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("adminDeleteBook.fxml");
    }

    @FXML
    private void cGoToShowBookPage(ActionEvent event) {
    }

    @FXML
    private void cAdminLogoutButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("admin-login.fxml");
    }
}
