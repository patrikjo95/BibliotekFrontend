package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerAdminAddBook {


    @FXML
    private Label cConfirmationLabel;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField quantityTextField;
    @FXML
    private TextField authorTextField;
    @FXML
    private TextField genreTextField;
    @FXML
    private TextField yearTextField;
    @FXML
    private TextField urlTextField;
    @FXML
    private Button addBookButton;

    public String response;

    ConnectionManager connectionManager = new ConnectionManager();

    public void cAddBookToDatabase(ActionEvent event) {

    addBook();
    }

    public void addBook(){
        String title = titleTextField.getText();
        String quantity = quantityTextField.getText();
        String author = authorTextField.getText();
        String genre = genreTextField.getText();
        String year = yearTextField.getText();
        String urltext = urlTextField.getText();
        response = connectionManager.sendGetRequest("/insertBook?new_book_title=" + title + "&new_book_qty="+ quantity +"&new_book_author="+ author +"&new_book_genre=" + genre + "&new_book_year=" + year + "&new_book_URL=" + urltext);
        cConfirmationLabel.setText("Du har lagt till en Bok!");

        System.out.println(response);


    }

}