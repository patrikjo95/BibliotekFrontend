package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ControllerBorrowBookPageForCustomer {

    @FXML
    private void backToCustomerFirstPage(ActionEvent event) throws IOException {
        Application application = new Application();
        application.changeScene("customerLoginFirstPage.fxml");
    }
}
