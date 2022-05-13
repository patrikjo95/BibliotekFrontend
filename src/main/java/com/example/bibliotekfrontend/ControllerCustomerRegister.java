package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ControllerCustomerRegister {

    @FXML
    public void cBackToLoginButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customer-login.fxml");
    }
}
