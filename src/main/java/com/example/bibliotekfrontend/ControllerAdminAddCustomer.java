package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class ControllerAdminAddCustomer {


    Utility u = new Utility();
    private String response;
    @FXML
    private Label errorRegisterConfirmationLabel;
    @FXML
    private Label samePinErrorLabel;
    @FXML
    private Label registerConfirmationLabel;
    @FXML
    private TextField YMDXField;
    @FXML
    private PasswordField pinField1;
    @FXML
    private PasswordField pinField2;

    ConnectionManager connectionManager = new ConnectionManager();

    @FXML
    private void cBackToAdminFirstPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("adminLoginFirstPage.fxml");
    }

    @FXML
    private void cAddCustomerButton(ActionEvent event) {

        String YMDX = u.encodeToURL(YMDXField.getText());
        String pin1 = u.encodeToURL(pinField1.getText());
        String pin2 = u.encodeToURL(pinField2.getText());
        if (pin1.equals(pin2)) {
            response = connectionManager.sendGetRequest("/add_customer?pnr=" + YMDX + "&pin=" + pin1);
            System.out.println(response);
            samePinErrorLabel.setVisible(false);
            if (response.contains("duplicate")){
                errorRegisterConfirmationLabel.setVisible(true);
                registerConfirmationLabel.setVisible(false);
                errorRegisterConfirmationLabel.setText("User already exists");
            } else if (response.contains("pnr is not an int")) {
                errorRegisterConfirmationLabel.setVisible(true);
                registerConfirmationLabel.setVisible(false);
                errorRegisterConfirmationLabel.setText("Please input correct numbers for YYYYMMDDXXXX");
            } else if (response.contains("length pnr")) {
                errorRegisterConfirmationLabel.setVisible(true);
                registerConfirmationLabel.setVisible(false);
                errorRegisterConfirmationLabel.setText("Please input correct length for YYYYMMDDXXXX");
            } else if (response.contains("year")) {
                errorRegisterConfirmationLabel.setVisible(true);
                registerConfirmationLabel.setVisible(false);
                errorRegisterConfirmationLabel.setText("Please input correct numbers for YYYY");
            } else if (response.contains("month")) {
                errorRegisterConfirmationLabel.setVisible(true);
                registerConfirmationLabel.setVisible(false);
                errorRegisterConfirmationLabel.setText("Please input correct numbers for MM");
            } else if (response.contains("day")) {
                errorRegisterConfirmationLabel.setVisible(true);
                registerConfirmationLabel.setVisible(false);
                errorRegisterConfirmationLabel.setText("Please input correct numbers for DD");
            } else if (response.contains("length pin")) {
                errorRegisterConfirmationLabel.setVisible(true);
                registerConfirmationLabel.setVisible(false);
                errorRegisterConfirmationLabel.setText("Please input four numbers for PIN");
            } else if (response.contains("pin is not int")) {
                errorRegisterConfirmationLabel.setVisible(true);
                registerConfirmationLabel.setVisible(false);
                errorRegisterConfirmationLabel.setText("Please input only numbers for PIN");
            } else {
                errorRegisterConfirmationLabel.setVisible(false);
                registerConfirmationLabel.setVisible(true);
                registerConfirmationLabel.setText("A member has been added!");
            }
        } else {
            samePinErrorLabel.setText("PIN must match");
            samePinErrorLabel.setTextFill(Color.RED);
        }

    }
}
