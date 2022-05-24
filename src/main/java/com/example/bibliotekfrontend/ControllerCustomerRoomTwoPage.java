package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ControllerCustomerRoomTwoPage {
    @FXML
    private void cBackFromRoomTwoToCustomerScheduleRoom(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerScheduleRoom.fxml");

    }
}
