package com.example.bibliotekfrontend;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ControllerAdminAddBook {


    public Button cAddBookBackButton;
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
    Utility utility = new Utility();

    ConnectionManager connectionManager = new ConnectionManager();

    public void cAddBookToDatabase(ActionEvent event) {

    addBook();
    }

    public void addBook() {

        String title = utility.encodeToURL(titleTextField.getText());
        String quantity = utility.encodeToURL(quantityTextField.getText());
        String author = utility.encodeToURL(authorTextField.getText());
        String genre = utility.encodeToURL(genreTextField.getText());
        String year = utility.encodeToURL(yearTextField.getText());
        String urltext = utility.encodeToURL(urlTextField.getText());
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

    @FXML
    private void cAddBookBackButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("adminLoginFirstPage.fxml");
    }
    
}