package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class ControllerCustomerScheduleRoom {
    String calender;
    public void settCalender(String response) {
        calender = response;
    }// ta emot respons typen.
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("customerRoomTwoPage.fxml"));
        loader.load();

        ControllerCustomerRoomTwoPage RoomTwo = loader.getController();
        RoomTwo.settCalender(calender);
        a.changeScene("customerRoomTwoPage.fxml");
    }

    @FXML
    private void cGoToRoomThreePage(ActionEvent event) throws IOException {
        Application a = new Application();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("customerRoomThreePage.fxml"));
        loader.load();

        ControllerCustomerRoomThreePage RoomThree = loader.getController();
        RoomThree.settCalender(calender);



        a.changeScene("customerRoomThreePage.fxml");
    }
    @FXML
    private void cGoToRoomFourPage(ActionEvent event) throws IOException {
        Application a = new Application();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("customerRoomFourPage.fxml"));
        loader.load();

        ControllerCustomerRoomFourPage Roomfour = loader.getController();
        Roomfour.settCalender(calender);


        a.changeScene("customerRoomFourPage.fxml");
    }
    @FXML
    private void cGoToRoomFivePage(ActionEvent event) throws IOException {
        Application a = new Application();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("customerRoomFivePage.fxml"));
        loader.load();

        ControllerCustomerRoomFivePage Roomfive = loader.getController();
        Roomfive.settCalender(calender);

        a.changeScene("customerRoomFivePage.fxml");
    }


    }

