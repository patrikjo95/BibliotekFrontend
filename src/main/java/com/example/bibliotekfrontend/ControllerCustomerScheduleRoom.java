package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ControllerCustomerScheduleRoom {
    @FXML
    private void cGoBackToCustomerPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerLoginFirstPage.fxml");
    }
}
