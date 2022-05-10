package com.example.bibliotekfrontend;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.ResourceBundle;
import com.google.gson.Gson;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ControllerFirstPage {

    private Gson gson = new Gson();
    Utility u = new Utility();

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
    private void cSearchBooksButton(ActionEvent event){
        Platform.runLater(()->{
        String hej = searchBooksTextField.getText();
        searchBookList.getItems().clear();
        response = connectionManager.sendGetRequest("/searchBook?check_book=" + hej);
        response = u.trimResponse(response);
        System.out.println("Response: " + response);
        //ObservableList<String> names = FXCollections.observableArrayList(response);


            JSONArray array = new JSONArray(response);


            for(int i = 0; i < array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                searchBookList.getItems().add(object.getString("book_title"));
                searchBookList.getItems().add(object.getString("book_author"));
                searchBookList.getItems().add(object.getString("book_genre"));
                searchBookList.getItems().add(object.getString("book_year"));
                System.out.println(object.getString("book_title"));
                System.out.print(object.getString("book_author"));
                System.out.print(object.getString("book_genre"));
                System.out.print(object.getString("book_year"));
            }






        /*
        while (resultSet.next()) {
            Book people = new Book(resultSet.getString("name"),
                    resultSet.getInt("age"));
            staffs.add(people);
        }

         */

        /*Book[] gsonBook = gson.fromJson(response, Book[].class);
            System.out.println(gsonBook);*/



        });

            //connectionManager.sendGetRequest("/downloadAllBooks");
           // System.out.println(connectionManager.sendGetRequest("/downloadAllBooks"));


    }


}

