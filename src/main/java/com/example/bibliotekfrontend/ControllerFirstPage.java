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
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerFirstPage implements Initializable {

    private Parent root;
    private Stage stage;
    private Scene scene;

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
    void cLoginCustomerButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customer-login.fxml");
    }
    @FXML
    void cLoginAdminButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("admin-login.fxml");
    }

    @FXML
    void cSearchBooksButton(ActionEvent event)throws IOException{
        Platform.runLater(()->{
            searchBooksTextField.getText();
            System.out.println(searchBooksTextField.getText());
            connectionManager.sendGetRequest("/downloadAllBooks");

        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

