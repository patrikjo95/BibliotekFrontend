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
    private TextField inputISBN_TextField;
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
        populateListViewDeleteBooks();

    }

    public void populateListViewDeleteBooks() {
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
    private void cDeleteBookButton(ActionEvent event) {
        String bookID = u.encodeToURL(bookIDTextField.getText());
        response = connectionManager.sendGetRequest("/delete_book_ID/?ID_book=" + bookID);

        if (response.contains("this is not int")) {
            deleteBookErrorLabel.setVisible(true);
            deleteBookErrorLabel.setTextFill(Color.RED);
            deleteBookErrorLabel.setText("Var god skriv enbart siffror.");
        } else if (response.contains("int incorrect")) {
            deleteBookErrorLabel.setVisible(true);
            deleteBookErrorLabel.setTextFill(Color.RED);
            deleteBookErrorLabel.setText("Detta ID finns ej.");
        } else if (response.contains("Borrowed")) {
            deleteBookErrorLabel.setVisible(true);
            deleteBookErrorLabel.setTextFill(Color.RED);
            deleteBookErrorLabel.setText("Denna bok är tyvärr utlånad för tillfället.");
        } else if (response.contains("success")) {
            deleteBookErrorLabel.setVisible(true);
            deleteBookErrorLabel.setTextFill(Color.GREEN);
            deleteBookErrorLabel.setText("Boken med ID " + bookID + " har raderats");
        }
        populateListViewDeleteBooks();
    }

    @FXML
    private void cDeleteBookISBN_Button(ActionEvent event) {
        String selected_ISBN_book = u.encodeToURL(inputISBN_TextField.getText());
        response = connectionManager.sendGetRequest("/delete_book_ISBN/?selected_ISBN_book=" + selected_ISBN_book);
        System.out.println(response);
        if (response.contains("is not int")) {
            deleteBookErrorLabel.setVisible(true);
            deleteBookErrorLabel.setTextFill(Color.RED);
            deleteBookErrorLabel.setText("Var god skriv enbart siffror.");
        } else if (response.contains("ISBN Does not exist")) {
            deleteBookErrorLabel.setVisible(true);
            deleteBookErrorLabel.setTextFill(Color.RED);
            deleteBookErrorLabel.setText("Detta ISBN finns ej.");
        } else if (response.contains("Borrowed books isbn")) {
            deleteBookErrorLabel.setVisible(true);
            deleteBookErrorLabel.setTextFill(Color.RED);
            deleteBookErrorLabel.setText("Detta ISBN har utlånade bok/böcker för tillfället.");
        } else if (response.contains("success")) {
            deleteBookErrorLabel.setVisible(true);
            deleteBookErrorLabel.setTextFill(Color.GREEN);
            deleteBookErrorLabel.setText("Boken/Böckerna med ISBN " + selected_ISBN_book + " har raderats");
        }
        populateListViewDeleteBooks();
    }

    @FXML
    private void cHelpPopupClose(MouseEvent mouseEvent) {
        helpPopUp.setVisible(false);
    }

    @FXML
    private void cHelpPopup(MouseEvent mouseEvent) {
        helpPopUp.setVisible(true);
    }

    /*
    private void populateListViewCustomerBorrowedBooks() {
        listViewBorrowedBooksSpecificCustomer.getItems().clear();
        response = connectionManager.sendGetRequest("/which_books_are_borrowed?customer_pnr_live=" + customer_pnr_from_file);
        System.out.println(response);
        //listViewBorrowedBooksSpecificCustomer.;

        response = utility.trimResponse(response);
        System.out.println(response);
        // nedan error
        JSONArray array = new JSONArray(response);
        System.out.println(array);

        for (int i = 0; i < array.length(); i++) {
            object = array.getJSONObject(i);
            System.out.println(object);
            listViewBorrowedBooksSpecificCustomer.getItems().add("Title: " + object.getString("book_title") + " | " + "Author: " + object.getString("book_author") + " | " + "Genre: " + object.getString("book_genre") + " | " + "Bok ID: " + object.getInt("ID_book") + " | " + "Återlämnas senast: " + object.getString("return_date"));
            System.out.println(listViewBorrowedBooksSpecificCustomer);
        }
    }
     */
}