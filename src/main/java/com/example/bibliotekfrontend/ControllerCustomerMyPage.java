package com.example.bibliotekfrontend;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControllerCustomerMyPage implements Initializable {

    //private String customer_pnr_from_file;
    private String response;

    JSONObject object = new JSONObject();

    ConnectionManager connectionManager = new ConnectionManager();

    Utility utility = new Utility();

    private int ID_book_selected;

    @FXML
    private void cButtonBackToCustomerLoginFirstPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerLoginFirstPage.fxml");
    }

    @FXML
    private Label customerLoggedInAsDetails;

    @FXML
    private ListView listViewBorrowedBooksSpecificCustomer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            String customer_pnr_from_file = "";

            try {
                File file = new File("src/main/resources/com/example/bibliotekfrontend/customer_pnr_txt_file.txt");
                Scanner scanner = new Scanner(file);
                customer_pnr_from_file = scanner.next();

            } catch (FileNotFoundException ex) {
                System.err.println(ex);
            }

            customerLoggedInAsDetails.setText("You are logged in as: " + customer_pnr_from_file);
            customerLoggedInAsDetails.setVisible(true);

            populateListViewCustomerBorrowedBooks(customer_pnr_from_file);
        });
    }

    private void populateListViewCustomerBorrowedBooks(String customer_pnr_from_file) {
        listViewBorrowedBooksSpecificCustomer.getItems().clear();
        response = connectionManager.sendGetRequest("/which_books_are_borrowed?customer_pnr_live=" + customer_pnr_from_file);
        System.out.println(response);

        response = utility.trimResponse(response);
        System.out.println(response);
        JSONArray array = new JSONArray(response);
        System.out.println(array);

        for (int i = 0; i < array.length(); i++) {
            object = array.getJSONObject(i);
            System.out.println(object);
            listViewBorrowedBooksSpecificCustomer.getItems().add("Title: " + object.getString("book_title") + " | " + "Author: " + object.getString("book_author") + " | " + "Bok ID: " + object.getInt("ID_book") + " | " + "Återlämnas senast: " + object.getString("return_date"));
            System.out.println(listViewBorrowedBooksSpecificCustomer);
        }
    }

    private String selectedBookString;
    private String selectedBookID;

    @FXML
    private void cSelectedBorrowedBookFromListViewToReturn(MouseEvent event){
        listViewBorrowedBooksSpecificCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                selectedBookString = (String) listViewBorrowedBooksSpecificCustomer.getSelectionModel().getSelectedItem();
                selectedBookID = utility.getBookIDFromSelectedString(selectedBookString);

            }
        });
    }


    @FXML
    private void cReturnBook(ActionEvent event) {
        System.out.println(selectedBookID);
        response = connectionManager.sendGetRequest("/return_book/?book_id=" + selectedBookID);
        System.out.println(response);
        String customer_pnr_from_file = "";
        //
        try {
            File file = new File("src/main/resources/com/example/bibliotekfrontend/customer_pnr_txt_file.txt");
            Scanner scanner = new Scanner(file);
            customer_pnr_from_file = scanner.next();
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        populateListViewCustomerBorrowedBooks(customer_pnr_from_file);
    }
}
