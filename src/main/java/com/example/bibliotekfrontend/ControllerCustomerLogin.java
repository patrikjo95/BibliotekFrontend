package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ControllerCustomerLogin {

    private Parent root;
    private Stage stage;
    private Scene scene;
    private String response;

    Utility utility = new Utility();

    ConnectionManager connectionManager = new ConnectionManager();

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Label errorLabelLoginCustomer;


    @FXML
    private void cBackToFirstPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeToFirstPage();

    }

    @FXML
    private void cToRegisterPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerRegister.fxml");
    }

    @FXML
    private void cToLoginCustomerFirstPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerLoginFirstPage.fxml");
    }

    @FXML
    private void cSendLoginDataToBackEnd(ActionEvent event) throws IOException {
        String customer_pnr = utility.encodeToURL(usernameTextField.getText());
        String customer_pin = utility.encodeToURL(passwordTextField.getText());
        response = connectionManager.sendGetRequest("/login_customer/?test_pnr=" + customer_pnr + "&test_pin=" + customer_pin);
        System.out.println(response);

        if (response.contains("wrong pnr")) {
            errorLabelLoginCustomer.setVisible(true);
            errorLabelLoginCustomer.setTextFill(Color.RED);
            errorLabelLoginCustomer.setText("Detta personnummer kunde inte hittas i v??rat register.");
        } else if (response.contains("wrong pin")) {
            errorLabelLoginCustomer.setVisible(true);
            errorLabelLoginCustomer.setTextFill(Color.RED);
            errorLabelLoginCustomer.setText("Fel PIN!");
        } else if (response.contains("success")) {
            try {
                FileWriter myWriter = new FileWriter("src/main/resources/com/example/bibliotekfrontend/customer_pnr_txt_file.txt");
                myWriter.write(customer_pnr);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
                Application a = new Application();
                a.changeScene("customerLoginFirstPage.fxml");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}