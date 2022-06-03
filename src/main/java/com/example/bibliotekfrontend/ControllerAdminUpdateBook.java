package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ControllerAdminUpdateBook {

    @FXML
    private TextField ISBNTextField;
    @FXML
    private ListView searchBookList;

    private String response;

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
        String input = u.encodeToURL(ISBNTextField.getText());
        response = connectionManager.sendGetRequest("/search_for_a_book_qty?check_book=" + input);
        response = u.trimResponse(response);

        JSONArray array = new JSONArray(response);


        for(int i = 0; i < array.length(); i++){
            object = array.getJSONObject(i);
            searchBookList.getItems().add("ISBN: " + object.getInt("ISBN_book") + " | " + "Titel: " + object.getString("book_title") + " | " + "Författare: " + object.getString("book_author") + " | " +  "Utgivningsår: " + object.getString("book_year") + " | " + "Antal: " +  object.getString("book_qty"));

        }

    }

    @FXML
    private void cListView(MouseEvent event) {


    }


    public void cUpdateBookButton(ActionEvent event) {



    }

    @FXML
    private void cHelpPopup(MouseEvent event) {


    }

    public void cHelpPopupClose(MouseEvent event) {


    }
}
