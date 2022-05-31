package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ControllerAdminAllBorrowedBooksPage {

    @FXML
    private void cAllBooksBackButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("adminLoginFirstPage.fxml");

    }
}
