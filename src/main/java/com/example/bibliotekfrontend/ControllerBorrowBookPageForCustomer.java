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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ControllerBorrowBookPageForCustomer {

    @FXML
    private Label selectedBookForBorrow;
    @FXML
    private TextField searchBorrowBookTextField;
    @FXML
    private Button searchBorrowBookButton;
    @FXML
    private ListView<String> borrowBookListView;
    @FXML
    private Button backToCustomerFirstPageButton;

    ConnectionManager connectionManager = new ConnectionManager();
    Utility u = new Utility();
    private String response;
    JSONObject object = new JSONObject();
    private String isbnFromClick;

    @FXML
    private void backToCustomerFirstPage(ActionEvent event) throws IOException {
        Application application = new Application();
        application.changeScene("customerLoginFirstPage.fxml");
    }

    @FXML
    private void cSearchForBorrowBook(ActionEvent event) {
        Platform.runLater(()->{
            String input = u.encodeToURL(searchBorrowBookTextField.getText());
            borrowBookListView.getItems().clear();
            response = connectionManager.sendGetRequest("/search_for_a_book_borrow?check_book=" + input);
            response = u.trimResponse(response);

            JSONArray array = new JSONArray(response);


            for(int i = 0; i < array.length(); i++){
                object = array.getJSONObject(i);
                borrowBookListView.getItems().add("Title: " + object.getString("book_title") + " | " + "Author: " + object.getString("book_author") + " | " +  "Publishing year: " + object.getString("book_year") + " | " + "Genre: " + object.getString("book_genre") + " | " + "ISBN: " + object.getInt("ISBN_book") + " | " + object.getString("available_unavailable"));

            }

        });
    }
    @FXML
    private void cListViewForBorrow(MouseEvent event){
        //isbnTextField.setText(String.valueOf(object.getInt("ISBN")));
        borrowBookListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String selectedBookString = borrowBookListView.getSelectionModel().getSelectedItem();
                String selectedTitle = u.getTitleFromString(selectedBookString);
                selectedBookForBorrow.setText(selectedTitle);


            }
        });
    }
    @FXML
    private void cBorrowBook(ActionEvent event) {

    response = connectionManager.sendGetRequest("/borrow_book/?check_book=");
    }
}
