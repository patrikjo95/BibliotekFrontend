package com.example.bibliotekfrontend;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;

import com.google.gson.Gson;


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
        String input = u.encodeToURL(searchBooksTextField.getText());
        searchBookList.getItems().clear();
        response = connectionManager.sendGetRequest("/searchBook?check_book=" + input);
        response = u.trimResponse(response);
        //System.out.println("Response: " + response);

            JSONArray array = new JSONArray(response);


            for(int i = 0; i < array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                searchBookList.getItems().add("Title: " + object.getString("book_title") + " Author: " + object.getString("book_author") + " Genre: " + object.getString("book_genre") + " Publishing year: " + object.getString("book_year"));

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

