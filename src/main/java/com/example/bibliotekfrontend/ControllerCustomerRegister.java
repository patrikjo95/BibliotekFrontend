package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCustomerRegister {

    private String response;
    Utility u = new Utility();
    ConnectionManager connectionManager = new ConnectionManager();
    @FXML
    private Label registerLabel;
    @FXML
    private Label pinErrorLabel;
    @FXML
    private TextField YMDXTextField;
    @FXML
    private PasswordField pin1PasswordField;
    @FXML
    private PasswordField pin2PasswordField;
    @FXML
    private Button closeButton;

    @FXML
    public void cBackToLoginButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customer-login.fxml");
    }


    @FXML
    private void cRegisterWithBankID(ActionEvent event) throws IOException {

        Application a = new Application();
        String YMDX = u.encodeToURL(YMDXTextField.getText());
        String pin1 = u.encodeToURL(pin1PasswordField.getText());
        String pin2 = u.encodeToURL(pin2PasswordField.getText());

        if (pin1.equals(pin2)) {
            response = connectionManager.sendGetRequest("/add_customer?pnr=" + YMDX + "&pin=" + pin1);
            System.out.println(response);
            pinErrorLabel.setVisible(false);
            if (response.contains("duplicate")){
                registerLabel.setVisible(true);
                registerLabel.setTextFill(Color.RED);
                registerLabel.setText("User already exists");
            } else if (response.contains("pnr is not an int")) {
                registerLabel.setVisible(true);
                registerLabel.setTextFill(Color.RED);
                registerLabel.setText("Please input correct numbers for YYYYMMDDXXXX");
            } else if (response.contains("length pnr")) {
                registerLabel.setVisible(true);
                registerLabel.setTextFill(Color.RED);
                registerLabel.setText("Please input correct length for YYYYMMDDXXXX");
            } else if (response.contains("year")) {
                registerLabel.setVisible(true);
                registerLabel.setTextFill(Color.RED);
                registerLabel.setText("Please input correct numbers for YYYY");
            } else if (response.contains("month")) {
                registerLabel.setVisible(true);
                registerLabel.setTextFill(Color.RED);
                registerLabel.setText("Please input correct numbers for MM");
            } else if (response.contains("day")) {
                registerLabel.setVisible(true);
                registerLabel.setTextFill(Color.RED);
                registerLabel.setText("Please input correct numbers for DD");
            } else if (response.contains("length pin")) {
                registerLabel.setVisible(true);
                registerLabel.setTextFill(Color.RED);
                registerLabel.setText("Please input four numbers for PIN");
            } else if (response.contains("pin is not int")) {
                registerLabel.setVisible(true);
                registerLabel.setTextFill(Color.RED);
                registerLabel.setText("Please input only numbers for PIN");
            } else {
                registerLabel.setVisible(true);
                registerLabel.setTextFill(Color.GREEN);
                registerLabel.setText("You have successfully registered!");
                a.openPopup("BankIDPopup.fxml");
            }
        } else {
            pinErrorLabel.setText("PIN must match");
            pinErrorLabel.setTextFill(Color.RED);
        }

    }


    @FXML
    private void cGoToBankIDConfirmed(ActionEvent event) throws IOException {
        Application a = new Application();
        a.openPopup("BankIDPopupConfirmed.fxml");
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    public void cGoBackToRegister(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
