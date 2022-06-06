package com.example.bibliotekfrontend;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControllerAdminDeleteBook{

    @FXML
    private Label errorISBN;
    @FXML
    private Button closeButtonID;
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
    public String selected_ISBN_book;
    public String selected_ISBN_book_from_file;
    @FXML
    private void cSearchBooksButton(ActionEvent event) {
        populateListViewDeleteBooks();

    }

    public void populateListViewDeleteBooks() {
        Platform.runLater(() -> {
            System.out.println(searchBookList);
            searchBookList.requestFocus();
            String input = u.encodeToURL(searchBooksTextField.getText());
            searchBookList.getItems().clear();
            searchBookList.getSelectionModel().select(0);

            response = connectionManager.sendGetRequest("/search_for_a_book_admin?check_book=" + input);
            response = u.trimResponse(response);

            JSONArray array = new JSONArray(response);


            for (int i = 0; i < array.length(); i++) {
                object = array.getJSONObject(i);
                searchBookList.getItems().add("ID: " + object.getInt("ID_book") + " | " + "Titel: " + object.getString("book_title") + " | " + "Författare: " + object.getString("book_author") + " | " + "År: " + object.getString("book_year") + " | " + "ISBN: " + object.getInt("ISBN_book"));

            }

        });
    }

    @FXML
    private void cListView(MouseEvent event) {
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
    private void cDeleteBookISBN_Button(ActionEvent event) throws IOException {
        Application a = new Application();
        selected_ISBN_book = u.encodeToURL(inputISBN_TextField.getText());
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/com/example/bibliotekfrontend/selectedISBNToDelete.txt");
            myWriter.write(selected_ISBN_book);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        response = connectionManager.sendGetRequest("/delete_book_ISBN/?selected_ISBN_book=" + selected_ISBN_book);
        System.out.println(response);
        if (response.contains("is not int")) {
            errorISBN.setVisible(true);
            errorISBN.setTextFill(Color.RED);
            errorISBN.setText("Var god skriv enbart siffror.");
        } else if (response.contains("ISBN Does not exist")) {
            errorISBN.setVisible(true);
            errorISBN.setTextFill(Color.RED);
            errorISBN.setText("Detta ISBN finns ej.");
        } else if (response.contains("Borrowed books isbn")) {
            errorISBN.setVisible(true);
            errorISBN.setTextFill(Color.RED);
            errorISBN.setText("Detta ISBN har utlånade bok/böcker för tillfället.");
        } else if (response.contains("success")) {
            System.out.println(response);
            a.openPopup2("areYouSureYouWantToDelete.fxml");
            try {
                File file = new File("src/main/resources/com/example/bibliotekfrontend/selectedISBNToDelete.txt");
                Scanner scanner = new Scanner(file);
                selected_ISBN_book_from_file = scanner.next();
            } catch (FileNotFoundException ex) {
                System.err.println(ex);
            }
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

    @FXML
    private void cDeleteAllBooksWithThisISBN(ActionEvent event) {
        try {
            File file = new File("src/main/resources/com/example/bibliotekfrontend/selectedISBNToDelete.txt");
            Scanner scanner = new Scanner(file);
            selected_ISBN_book_from_file = scanner.next();
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        response = connectionManager.sendGetRequest("/yes_delete/?ISBN=" + selected_ISBN_book_from_file);
        Stage stage = (Stage) closeButtonID.getScene().getWindow();
        stage.close();

    }
    @FXML
    private void cGoBackToDeleteBook(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("AdminDeleteBook.fxml");
        Stage stage = (Stage) closeButtonID.getScene().getWindow();
        stage.close();

    }
}