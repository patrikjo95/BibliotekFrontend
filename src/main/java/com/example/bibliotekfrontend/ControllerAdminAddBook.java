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
        connectionManager.sendGetRequest("/insertBook?book_title=" + title + "&book_qty="+ quantity +"&book_author="+ author +"&book_genre=" + genre + "&book_year=" + year + "&book_URL=" + urltext);
        cConfirmationLabel.setText("Du har lagt till en Bok!");


    }

}