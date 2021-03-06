package com.example.bibliotekfrontend;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControllerCustomerLoginFirstPage implements Initializable {
    Utility u = new Utility();
    private String response;
    ConnectionManager connectionManager = new ConnectionManager();
    JSONObject object = new JSONObject();
    @FXML
    private Label customerLoggedInAsDetails;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String customer_pnr_from_file = "";
        //
        try {
            File file = new File("src/main/resources/com/example/bibliotekfrontend/customer_pnr_txt_file.txt");
            Scanner scanner = new Scanner(file);
            customer_pnr_from_file = scanner.next();

        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }

        customerLoggedInAsDetails.setText("You are logged in as: " + customer_pnr_from_file);
        customerLoggedInAsDetails.setVisible(true);
    }


    @FXML
    private void cGoToRoomFirstPage(ActionEvent event) throws IOException {
        Application a = new Application();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("customerScheduleRoom.fxml"));
        Parent root = loader.load();

        ControllerCustomerScheduleRoom scheduleRoom = loader.getController();


        response = connectionManager.sendGetRequest("/one_month_calender");
            System.out.println(response);
            response = u.trimResponse(response);
            scheduleRoom.settCalender(response);

        a.changeScene("customerScheduleRoom.fxml");



    }

    @FXML
    private void cCustomerLogoutButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("firstPage.fxml");
    }

    @FXML
    private void borrowBookButtonForCustomer(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("borrowBookPageForCustomer.fxml");
    }

    @FXML
    private void cButtonMinaSidor(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerMyPage.fxml");
    }
}