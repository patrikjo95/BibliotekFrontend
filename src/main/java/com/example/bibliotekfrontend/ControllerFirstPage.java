package com.example.bibliotekfrontend;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.Gson;


public class ControllerFirstPage implements Initializable {


    private Gson gson = new Gson();
    Utility u = new Utility();

    private Parent root;
    private Stage stage;
    private Scene scene;
    private String response;
    private String responseForPopularBooks;

    @FXML
    private ListView displayPopularBooksListView;
    @FXML
    private ChoiceBox changeGenreChoiceBox;
    @FXML
    private Button customerLoginButton;
    @FXML
    private Button adminLoginButton;
    @FXML
    private TextField searchBooksTextField;
    @FXML
    private ListView<String> searchBookList;

    ConnectionManager connectionManager = new ConnectionManager();

    @FXML
    private void cLoginCustomerButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customer-login.fxml");
    }

    @FXML
    private void cLoginAdminButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("admin-login.fxml");


    }

    @FXML
    private void cSearchBooksButton(ActionEvent event) {
        Platform.runLater(() -> {
            String input = u.encodeToURL(searchBooksTextField.getText());
            searchBookList.getItems().clear();
            response = connectionManager.sendGetRequest("/search_for_a_book_everyone?check_book=" + input);
            response = u.trimResponse(response);

            JSONArray array = new JSONArray(response);


            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                searchBookList.getItems().add("Titel: " + object.getString("book_title") + " | " + "Författare: " + object.getString("book_author") + " | " + "Genre: " + object.getString("book_genre") + " | " + "År: " + object.getString("book_year"));
            }
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        populatePopularTable();

    }


    @FXML
    private ComboBox chooseGenrePopularBooks;

    @FXML
    private void cChooseGenreForPopularBooks(ActionEvent actionEvent) {
        displayPopularBooksListView.getItems().clear();
        responseForPopularBooks = connectionManager.sendGetRequest("/show_popular_books_view?book_genre=" + chooseGenrePopularBooks.getSelectionModel().getSelectedItem()); // input ska vara book_genre
        responseForPopularBooks = u.trimResponse(responseForPopularBooks);


        JSONArray array = new JSONArray(responseForPopularBooks);


        for (int i = 0; i < array.length(); i++) {

            JSONObject object = array.getJSONObject(i);
            displayPopularBooksListView.getItems().add("Titel: " + object.getString("book_title") + " | " + "Författare: " + object.getString("book_author") + " | " + "År: " + object.getString("book_year") + " | " + "Genre: " + object.getString("book_genre"));

        }
    }

    public void populatePopularTable() {
        chooseGenrePopularBooks.getItems().removeAll(chooseGenrePopularBooks.getItems());
        chooseGenrePopularBooks.getItems().addAll("Alla", "Action & Äventyr", "Deckare", "Drama", "Fantasy", "Filosofi & Religion", "Komedi", "Manga", "Mat & Dryck", "Resor", "Thriller", "Ungdom", "Vetenskap & Teknik");
        chooseGenrePopularBooks.getSelectionModel().select("Alla");

        displayPopularBooksListView.getItems().clear();
        responseForPopularBooks = connectionManager.sendGetRequest("/show_popular_books_view?book_genre=" + chooseGenrePopularBooks.getSelectionModel().getSelectedItem()); // input ska vara book_genre
        System.out.println(responseForPopularBooks);
        responseForPopularBooks = u.trimResponse(responseForPopularBooks);


        if (responseForPopularBooks.contains("empty")) {
            System.out.println("This genre is not popular.");
        } else {
            JSONArray array = new JSONArray(responseForPopularBooks);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                displayPopularBooksListView.getItems().add("Titel: " + object.getString("book_title") + " | " + "Författare: " + object.getString("book_author") + " | " + "År: " + object.getString("book_year") + " | " + "Genre: " + object.getString("book_genre"));
            }
        }
    }
}

