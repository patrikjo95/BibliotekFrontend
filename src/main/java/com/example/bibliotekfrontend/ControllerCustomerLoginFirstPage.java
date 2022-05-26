package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControllerCustomerLoginFirstPage implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String customer_pnr_from_file = "";
        //
        try {
            File file = new File("src/main/resources/com/example/bibliotekfrontend/customer_pnr_txt_file.txt");
            Scanner scanner = new Scanner(file);
            customer_pnr_from_file = scanner.next();
            //while (scanner.hasNext()) {
            //    customer_pnr_from_file = scanner.next()
            //}
            //customer_pnr_from_file = scanner.hasNext();
            //customer_pnr_from_file.appendText()

            // YourTextArea.appendText(s.nextInt() + " ");
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        /*
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
         */
        //
        customerLoggedInAsDetails.setText("You are logged in as: " + customer_pnr_from_file);
        customerLoggedInAsDetails.setVisible(true);
    }

    @FXML
    private Label customerLoggedInAsDetails;


    @FXML
    private void showCustomerLoginPNR(MouseEvent mouseEvent) {
        //customerLoggedInAsDetails.setVisible(true);
        //customerLoggedInAsDetails.setText(); //hämta pnr från txt
    }

    //showCustomerLoginPNR();

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
