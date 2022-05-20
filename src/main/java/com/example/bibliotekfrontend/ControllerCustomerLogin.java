package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCustomerLogin {
    private Parent root;
    private Stage stage;
    private Scene scene;
    private String response;

    Utility utility = new Utility();

    ConnectionManager connectionManager = new ConnectionManager();

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button loginButton;

    @FXML
    private void cBackToFirstPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeToFirstPage();

    }

    @FXML
    private void cToRegisterPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerRegister.fxml");
    }

    @FXML
    private void cToLoginCustomerFirstPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerLoginFirstPage.fxml");
    }

    @FXML
    private void cSendLoginDataToBackEnd(ActionEvent event){
        String customer_pnr = utility.encodeToURL(usernameTextField.getText());
        String customer_pin = utility.encodeToURL(passwordTextField.getText());
        response = connectionManager.sendGetRequest("/login_customer/?test_pnr=" + customer_pnr + "&test_pin=" + customer_pin);
        System.out.println(response);
/*
        if (response.contains("wrong pnr")) {
            cConfirmationLabel.setVisible(false);
            cErrorLabel.setVisible(true);
            cErrorLabel.setText("That book already exists");
        } else if (response.contains("wrong pin")) {
            cConfirmationLabel.setVisible(false);
            cErrorLabel.setVisible(true);
            cErrorLabel.setText("That year is not valid");
        } else if (response.contains("Its not year")) {
            cConfirmationLabel.setVisible(false);
            cErrorLabel.setVisible(true);
            cErrorLabel.setText("Please enter a year");
        } else if (response.contains("error qty int")) {
            cConfirmationLabel.setVisible(false);
            cErrorLabel.setVisible(true);
            cErrorLabel.setText("Incorrect quantity");
        } else if (response.contains("0")) {
            cConfirmationLabel.setVisible(true);
            cErrorLabel.setVisible(false);
            cConfirmationLabel.setText("Du har lagt till en Bok!");
        }
 */
    }
}