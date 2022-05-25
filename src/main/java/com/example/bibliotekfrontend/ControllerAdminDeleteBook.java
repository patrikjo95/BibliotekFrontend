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
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ControllerAdminDeleteBook {

    @FXML
    private AnchorPane helpPopUp;
    @FXML
    private Label de;
    @FXML
    private AnchorPane helpTextpopupPane;
    @FXML
    private Text helpText;
    @FXML
    private Label colorllabel;
    @FXML
    private Label deleteBookErrorLabel;
    @FXML
    private Label searchBookErrorLabel;
    @FXML
    private TextField searchBooksTextField;
    @FXML
    private TextField bookIDTextField;
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
    private void cSearchBooksButton(ActionEvent event) {
        Platform.runLater(() -> {
            String input = u.encodeToURL(searchBooksTextField.getText());
            searchBookList.getItems().clear();
            response = connectionManager.sendGetRequest("/search_for_a_book_admin?check_book=" + input);
            response = u.trimResponse(response);
            //System.out.println("Response: " + response);

            JSONArray array = new JSONArray(response);


            for (int i = 0; i < array.length(); i++) {
                object = array.getJSONObject(i);
                searchBookList.getItems().add("ID: " + object.getInt("ID_book") + " | " + "Titel: " + object.getString("book_title") + " | " + "Författare: " + object.getString("book_author") + " | " + "År: " + object.getString("book_year") + " | " + "ISBN: " + object.getInt("ISBN_book"));

            }

        });

    }

    @FXML
    private void cListView(MouseEvent event) {
        //isbnTextField.setText(String.valueOf(object.getInt("ISBN")));
        searchBookList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String selectedBookString = searchBookList.getSelectionModel().getSelectedItem();
                String selectedIsbn = u.getIsbnFromString(selectedBookString);
                bookIDTextField.setText(String.valueOf(selectedIsbn));
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
        String bookID = u.encodeToURL(bookIDTextField.getText());
        response = connectionManager.sendGetRequest("/deleteBookByID/?ID_book=" + bookID);

        if (response.contains("this is not int")) {
            deleteBookErrorLabel.setVisible(true);
            deleteBookErrorLabel.setTextFill(Color.RED);
            deleteBookErrorLabel.setText("Var god skriv enbart siffror.");
        } else if (response.contains("int incorrect")) {
            deleteBookErrorLabel.setVisible(true);
            deleteBookErrorLabel.setTextFill(Color.RED);
            deleteBookErrorLabel.setText("Detta ID finns ej.");
        } else if (response.contains("null")) {
            deleteBookErrorLabel.setVisible(true);
            deleteBookErrorLabel.setTextFill(Color.LIGHTGREEN);
            deleteBookErrorLabel.setText("Boken med ID " + bookID + " har raderats");
        }
    }

    @FXML
    private void cHelpPopupClose(MouseEvent mouseEvent) {
        helpPopUp.setVisible(false);
    }

    @FXML
    private void cHelpPopup(MouseEvent mouseEvent) {
        helpPopUp.setVisible(true);
    }
}