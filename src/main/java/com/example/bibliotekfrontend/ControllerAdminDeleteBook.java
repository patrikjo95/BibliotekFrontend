package com.example.bibliotekfrontend;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ControllerAdminDeleteBook {

    @FXML
    private Label deleteBookErrorLabel;
    @FXML
    private Label searchBookErrorLabel;
    @FXML
    private TextField searchBooksTextField;
    @FXML
    private TextField isbnTextField;
    @FXML
    private Button deleteBookButton;
    @FXML
    private Button cBackButton;
    @FXML
    private Button searchButton;
    @FXML
    private ListView<String> searchBookList;

    Utility u = new Utility();
    private String response;
    ConnectionManager connectionManager = new ConnectionManager();
    JSONObject object = new JSONObject();

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
                object = array.getJSONObject(i);
                searchBookList.getItems().add("ISBN: " + object.getInt("ISBN") +  " | " + "Title: " + object.getString("book_title") + " | " + "Author: " + object.getString("book_author") + " | " +  "Publishing year: " + object.getString("book_year"));

            }

        });

    }

    @FXML
    private void cListView(MouseEvent event){
        //isbnTextField.setText(String.valueOf(object.getInt("ISBN")));
        searchBookList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String selectedBookString = searchBookList.getSelectionModel().getSelectedItem();
                String selectedIsbn = u.getIsbnFromString(selectedBookString);
                isbnTextField.setText(String.valueOf(selectedIsbn));
            }
        });
    }


    @FXML
    public void cBackButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("adminLoginFirstPage.fxml");
    }

    @FXML
    public void cDeleteBookButton(ActionEvent event) {
        String ISBN = u.encodeToURL(isbnTextField.getText());
        response = connectionManager.sendGetRequest("/deleteBookByID/?ID_book=" + ISBN);
        //System.out.println("response:" + response);

        if(response.contains("this is not int")){
            deleteBookErrorLabel.setVisible(true);
            deleteBookErrorLabel.setText("Please enter a number");
        }else if(response.contains("int incorrect")){
            deleteBookErrorLabel.setVisible(true);
            deleteBookErrorLabel.setText("That ISBN does not exist");
        }else if(response.contains("null")){
            deleteBookErrorLabel.setVisible(true);
            deleteBookErrorLabel.setTextFill(Color.LIGHTGREEN);
            deleteBookErrorLabel.setText("Book with ISBN " + ISBN + " deleted");
        }
    }
}


