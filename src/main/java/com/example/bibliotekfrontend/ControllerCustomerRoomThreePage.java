package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ControllerCustomerRoomThreePage {

    @FXML
    private void cBackFromRoomThreeToCustomerScheduleRoom(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerScheduleRoom.fxml");
    }
}
