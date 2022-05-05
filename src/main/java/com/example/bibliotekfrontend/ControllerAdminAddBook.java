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
    private Label cErrorLabel;
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

    public void addBook() {
        String title = titleTextField.getText();
        String quantity = quantityTextField.getText();
        System.out.println(quantity);
        String author = authorTextField.getText();
        String genre = genreTextField.getText();
        String year = yearTextField.getText();
        String urltext = urlTextField.getText();
        response = connectionManager.sendGetRequest("/insertBook/?new_book_title=" + title + "&new_book_qty=" + quantity + "&new_book_author=" + author + "&new_book_genre=" + genre + "&new_book_year=" + year + "&new_book_URL=" + urltext);
        System.out.println(response);

        if (response.contains("error duplicated")) {
            cConfirmationLabel.setVisible(false);
            cErrorLabel.setVisible(true);
            cErrorLabel.setText("That book already exists");
        } else if (response.contains("Not valid year")) {
            cConfirmationLabel.setVisible(false);
            cErrorLabel.setVisible(true);
            cErrorLabel.setText("That year is not valid");
        } else if (response.contains("Its not year")) {
            cConfirmationLabel.setVisible(false);
            cErrorLabel.setVisible(true);
            cErrorLabel.setText("Please enter a year");
        } else if (response.contains("error qty int")) {
            cConfirmationLabel.setVisible(false);
            cErrorLabel.setVisible(true);
            cErrorLabel.setText("Incorrect quantity");
        } else if (response.contains("0")) {
            cConfirmationLabel.setVisible(true);
            cErrorLabel.setVisible(false);
            cConfirmationLabel.setText("Du har lagt till en Bok!");

        }
    }

}