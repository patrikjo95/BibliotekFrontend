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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControllerBorrowBookPageForCustomer implements Initializable {

    @FXML
    private Label selectedBookForBorrow;

    private String selectedBookString;

    @FXML
    private Label borrowErrorConfirmation;

    @FXML
    private TextField searchBorrowBookTextField;
    @FXML
    private Button searchBorrowBookButton;
    @FXML
    private ListView<String> borrowBookListView;
    @FXML
    private Button backToCustomerFirstPageButton;

    ConnectionManager connectionManager = new ConnectionManager();
    Utility u = new Utility();
    private String response;
    JSONObject object = new JSONObject();
    private String isbnFromClick;

    @FXML
    private void backToCustomerFirstPage(ActionEvent event) throws IOException {
        Application application = new Application();
        application.changeScene("customerLoginFirstPage.fxml");
    }

    @FXML
    private void cSearchForBorrowBook(ActionEvent event){

            String input = u.encodeToURL(searchBorrowBookTextField.getText());
            response = connectionManager.sendGetRequest("/search_for_a_book_borrow?check_book=" + input);
            response = u.trimResponse(response);

            JSONArray array = new JSONArray(response);


            for(int i = 0; i < array.length(); i++){
                object = array.getJSONObject(i);
                borrowBookListView.getItems().add("Title: " + object.getString("book_title") + " | " + "Author: " + object.getString("book_author") + " | " +  "Publishing year: " + object.getString("book_year") + " | " + "Genre: " + object.getString("book_genre") + " | " + "ISBN: " + object.getInt("ISBN_book") + " | " + object.getString("available_unavailable"));

            }


    }
    @FXML
    private void cListViewForBorrow(MouseEvent event){
        borrowBookListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                selectedBookString = borrowBookListView.getSelectionModel().getSelectedItem();
                String selectedTitle = u.getTitleFromString(selectedBookString);
                selectedBookForBorrow.setText(selectedTitle);


            }
        });
    }
    @FXML
    private void cBorrowBook(ActionEvent event) {

        String customer_pnr_live = "";
        try {
            File file = new File("src/main/resources/com/example/bibliotekfrontend/customer_pnr_txt_file.txt");
            Scanner scanner = new Scanner(file);
            customer_pnr_live = scanner.next();

        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }

        customer_pnr_live = u.encodeToURL(customer_pnr_live);
        System.out.println(customer_pnr_live);

        String ISBN_book_live = "";
        ISBN_book_live = u.getIsbnFromSelectedString(selectedBookString);
        ISBN_book_live = u.encodeToURL(ISBN_book_live);
        System.out.println(ISBN_book_live);

        response = connectionManager.sendGetRequest("/borrow_book?customer_pnr_live=" + customer_pnr_live + "&ISBN_book_live=" + ISBN_book_live);
        System.out.println(response);
        if (response.contains("qty")) {
            borrowErrorConfirmation.setVisible(true);
            borrowErrorConfirmation.setText("Denna bok är tyvärr otillgänglig för tillfället");
            borrowErrorConfirmation.setTextFill(Color.RED);
        } else if (response.contains("success")) {
            borrowErrorConfirmation.setVisible(true);
            borrowErrorConfirmation.setText("Du har lånat denna bok!");
            borrowErrorConfirmation.setTextFill(Color.GREEN);
        }

    }


    /**
     *
     * Fixar bug för listview där den inte reagerade på första gången man klickade på den
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        borrowBookListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                selectedBookString = borrowBookListView.getSelectionModel().getSelectedItem();
                String selectedTitle = u.getTitleFromString(selectedBookString);
                selectedBookForBorrow.setText(selectedTitle);


            }
        });
    }
}
