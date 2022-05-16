package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class ControllerAdminAddCustomer {

    private String response;
    @FXML
    private Label samePinErrorLabel;
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
        String YMDX = YMDXField.getText();
        String pin1 = pinField1.getText();
        String pin2 = pinField2.getText();
        if (pin1.equals(pin2)) {
            response = connectionManager.sendGetRequest("/add_customer?pnr=" + YMDX + "&pin=" + pin1);
            System.out.println(response);
            samePinErrorLabel.setVisible(false);
        } else {
            samePinErrorLabel.setText("Skriv samma pinkod din dumme fan");
            samePinErrorLabel.setTextFill(Color.RED);
        }

    }
}
