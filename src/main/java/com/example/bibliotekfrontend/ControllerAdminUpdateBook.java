package com.example.bibliotekfrontend;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ControllerAdminUpdateBook {

    @FXML
    private Label updateBookErrorLabel;
    @FXML
    private TextField searchBooksTextField;
    @FXML
    private TextField qtyTextField;
    @FXML
    private TextField ISBNTextField;
    @FXML
    private ListView<String> searchBookList;

    private String response;
    private String selectedBook;

    Utility u = new Utility();
    ConnectionManager connectionManager = new ConnectionManager();
    JSONObject object = new JSONObject();


    @FXML
    private void cBackButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("adminLoginFirstPage.fxml");


    }

    @FXML
    private void cSearchBooksButton(ActionEvent event) {
        searchBookList.getItems().clear();
        String input = u.encodeToURL(searchBooksTextField.getText());
        response = connectionManager.sendGetRequest("/search_for_a_book_qty?check_book=" + input);
        response = u.trimResponse(response);

        JSONArray array = new JSONArray(response);


        for(int i = 0; i < array.length(); i++){
            object = array.getJSONObject(i);
            searchBookList.getItems().add("ISBN: " + object.getInt("ISBN_book") + " | " + "Titel: " + object.getString("book_title") + " | " + "Författare: " + object.getString("book_author") + " | " +  "Utgivningsår: " + object.getString("book_year") + " | " + "Antal: " +  object.getInt("book_qty"));

        }

    }

    @FXML
    private void cListView(MouseEvent event) {

        searchBookList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                //String selectedBookString = borrowBookListView.getSelectionModel().getSelectedItem();
                selectedBook = searchBookList.getSelectionModel().getSelectedItem();
                String selectedISBN = u.getIsbnFromSelectedString(selectedBook);
                ISBNTextField.setText(selectedISBN);

            }
        });


    }


    public void cUpdateBookButton(ActionEvent event) {

        response = connectionManager.sendGetRequest("/update_book?ISBN_book_live=" + ISBNTextField.getText() + "&qty_book=" + qtyTextField.getText());
        System.out.println(response);

        if(response.contains("Not valid qty")){
            updateBookErrorLabel.setTextFill(Color.RED);
            updateBookErrorLabel.setVisible(true);
            updateBookErrorLabel.setText("Felaktig kvantitet");
        }else if(response.contains("Not valid ISBN")){
            updateBookErrorLabel.setTextFill(Color.RED);
            updateBookErrorLabel.setVisible(true);
            updateBookErrorLabel.setText("Felaktigt ISBN");
        }else if(response.contains("success")){
            updateBookErrorLabel.setVisible(true);
            updateBookErrorLabel.setTextFill(Color.GREEN);
            updateBookErrorLabel.setText("Bokens antal har uppdaterats!");
            ISBNTextField.clear();
            qtyTextField.clear();
            populateTable();
        }

    }

    @FXML
    private void cHelpPopup(MouseEvent event) {


    }

    @FXML
    private void cHelpPopupClose(MouseEvent event) {


    }

    public void populateTable(){
        searchBookList.getItems().clear();
        String input = u.encodeToURL(searchBooksTextField.getText());
        response = connectionManager.sendGetRequest("/search_for_a_book_qty?check_book=" + input);
        response = u.trimResponse(response);

        JSONArray array = new JSONArray(response);


        for(int i = 0; i < array.length(); i++){
            object = array.getJSONObject(i);
            searchBookList.getItems().add("ISBN: " + object.getInt("ISBN_book") + " | " + "Titel: " + object.getString("book_title") + " | " + "Författare: " + object.getString("book_author") + " | " +  "Utgivningsår: " + object.getString("book_year") + " | " + "Antal: " +  object.getInt("book_qty"));

        }

    }
}
