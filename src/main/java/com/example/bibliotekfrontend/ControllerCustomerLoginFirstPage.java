package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ControllerCustomerLoginFirstPage {
    @FXML
    private void cGoToShowBookPage(ActionEvent event) {
    }
    @FXML
    private void cGoToRoomFirstPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerScheduleRoom.fxml");
    }
    @FXML
    private void cCustomerLogoutButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("firstPage.fxml");
    }
}
