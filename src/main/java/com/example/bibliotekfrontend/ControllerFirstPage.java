package com.example.bibliotekfrontend;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.ResourceBundle;

public class ControllerFirstPage {

    private Parent root;
    private Stage stage;
    private Scene scene;
    private String response;

    @FXML
    private Button customerLoginButton;
    @FXML
    private Button adminLoginButton;
    @FXML
    private TextField searchBooksTextField;
    @FXML
    private Button searchBooksButton;
    @FXML
    private ListView<String> searchBookList;


    ConnectionManager connectionManager = new ConnectionManager();
    @FXML
    private void cLoginCustomerButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customer-login.fxml");
    }
    @FXML
    private void cLoginAdminButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("admin-login.fxml");
    }

    @FXML
    private void cSearchBooksButton(ActionEvent event) throws IOException{

            String hej = searchBooksTextField.getText();
            response = connectionManager.sendGetRequest("/searchBook?check_book=" + hej);
        System.out.println(response);

            //connectionManager.sendGetRequest("/downloadAllBooks");
           // System.out.println(connectionManager.sendGetRequest("/downloadAllBooks"));


    }


}

