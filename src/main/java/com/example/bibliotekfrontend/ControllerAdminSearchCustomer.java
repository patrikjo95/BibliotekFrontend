package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControllerAdminSearchCustomer implements Initializable {

    @FXML
    Label adminLoggedInAsEmail;

    @FXML
    Button adminSearchCustPNR_Button;

    @FXML
    public void cAdminSearchCustPNR_Button(ActionEvent event) {
        System.out.println("Toros");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String adminEmail = "";
        try {
            File file = new File("src/main/resources/com/example/bibliotekfrontend/admin_email.txt");
            Scanner scanner = new Scanner(file);
            adminEmail = scanner.next();

        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        adminLoggedInAsEmail.setText("You are logged in as: " + adminEmail);
        adminLoggedInAsEmail.setVisible(true);
    }
}
