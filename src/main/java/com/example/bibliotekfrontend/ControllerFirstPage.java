package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerFirstPage {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Button customerLoginButton;
    @FXML
    private Button adminLoginButton;
    @FXML
    private TextField searchBooksTextField;
    @FXML
    private Button searchBooksButton;


    @FXML
    void cLoginCustomerButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customer-login.fxml");
    }
    @FXML
    void cLoginAdminButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("admin-login.fxml");
    }
}

