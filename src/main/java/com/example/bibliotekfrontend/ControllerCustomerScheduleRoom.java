package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class ControllerCustomerScheduleRoom {
    String calender;
    public void settCalender(String response) {
        calender = response;
    }; // ta emot respons typen.
    @FXML
    private void cGoBackToCustomerPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerLoginFirstPage.fxml");

    }

    @FXML
    private void cGoToRoomOnePage(ActionEvent event) throws IOException {
        Application a = new Application();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("customerRoomOnePage.fxml"));
        loader.load();

        ControllerCustomerRoomOnePage RoomOne = loader.getController();
        RoomOne.settCalender(calender);
        a.changeScene("customerRoomOnePage.fxml");
    }




    @FXML
    private void cGoToRoomTwoPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerRoomTwoPage.fxml");
    }

    @FXML
    private void cGoToRoomThreePage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerRoomThreePage.fxml");
    }
    @FXML
    private void cGoToRoomFourPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerRoomFourPage.fxml");
    }
    @FXML
    private void cGoToRoomFivePage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerRoomFivePage.fxml");
    }


    }

