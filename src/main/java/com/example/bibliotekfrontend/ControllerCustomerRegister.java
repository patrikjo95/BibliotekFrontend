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
                registerLabel.setText("Denna användare finns redan");
            } else if (response.contains("pnr is not an int")) {
                registerLabel.setVisible(true);
                registerLabel.setTextFill(Color.RED);
                registerLabel.setText("Var god skriv in siffror i följande format: ÅÅÅÅMMDDXXXX");
            } else if (response.contains("length pnr")) {
                registerLabel.setVisible(true);
                registerLabel.setTextFill(Color.RED);
                registerLabel.setText("Var god skriv in siffror med korrekt längd: ÅÅÅÅMMDDXXXX");
            } else if (response.contains("year")) {
                registerLabel.setVisible(true);
                registerLabel.setTextFill(Color.RED);
                registerLabel.setText("Var god skriv in korrekta siffror för ÅÅÅÅ");
            } else if (response.contains("month")) {
                registerLabel.setVisible(true);
                registerLabel.setTextFill(Color.RED);
                registerLabel.setText("Var god skriv in korrekta siffror för MM");
            } else if (response.contains("day")) {
                registerLabel.setVisible(true);
                registerLabel.setTextFill(Color.RED);
                registerLabel.setText("Var god skriv in korrekta siffror för DD");
            } else if (response.contains("length pin")) {
                registerLabel.setVisible(true);
                registerLabel.setTextFill(Color.RED);
                registerLabel.setText("Var god skriv in fyra siffror för PIN\"");
            } else if (response.contains("pin is not int")) {
                registerLabel.setVisible(true);
                registerLabel.setTextFill(Color.RED);
                registerLabel.setText("Var god skriv in enbart siffror för PIN");
            } else {
                registerLabel.setVisible(true);
                registerLabel.setTextFill(Color.GREEN);
                registerLabel.setText("En ny medlem har skapats!");
                a.openPopup("BankIDPopup.fxml");
            }
        } else {
            pinErrorLabel.setVisible(true);
            pinErrorLabel.setText("PIN måste matcha");
            pinErrorLabel.setTextFill(Color.RED);
            registerLabel.setVisible(false);
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
