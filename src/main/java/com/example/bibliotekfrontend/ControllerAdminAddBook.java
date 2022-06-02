package com.example.bibliotekfrontend;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAdminAddBook implements Initializable {

    @FXML
    private Button cAddBookBackButton;
    @FXML
    private ChoiceBox genreChoiceBox;
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
        String genre = utility.encodeToURL((String) genreChoiceBox.getSelectionModel().getSelectedItem());
        String year = utility.encodeToURL(yearTextField.getText());
        String urltext = utility.encodeToURL(urlTextField.getText());
        response = connectionManager.sendGetRequest("/insertBook/?new_book_title=" + title + "&new_book_qty=" + quantity + "&new_book_author=" + author + "&new_book_genre=" + genre + "&new_book_year=" + year + "&new_book_URL=" + urltext);
        System.out.println(response);

        if (response.contains("error duplicated")) {
            cConfirmationLabel.setVisible(false);
            cErrorLabel.setVisible(true);
            cErrorLabel.setText("Den boken finns redan.");
        } else if (response.contains("Not valid year")) {
            cConfirmationLabel.setVisible(false);
            cErrorLabel.setVisible(true);
            cErrorLabel.setText("Var god skriv år i enbart siffror");
        } else if (response.contains("Its not year")) {
            cConfirmationLabel.setVisible(false);
            cErrorLabel.setVisible(true);
            cErrorLabel.setText("Var god skriv år i enbart siffror.");
        } else if (response.contains("error qty int")) {
            cConfirmationLabel.setVisible(false);
            cErrorLabel.setVisible(true);
            cErrorLabel.setText("Inkorrekt kvantitet");
        } else if (response.contains("success")) {
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genreChoiceBox.getItems().removeAll(genreChoiceBox.getItems());
        genreChoiceBox.getItems().addAll("Action & Äventyr",  "Deckare",  "Drama","Fantasy", "Filosofi & Religion", "Komedi",   "Manga", "Mat & Dryck", "Resor",  "Thriller","Ungdom", "Vetenskap & Teknik");
        genreChoiceBox.getSelectionModel().select("Drama");

    }
}