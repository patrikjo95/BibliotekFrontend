package com.example.bibliotekfrontend;

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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControllerAdminSearchCustomer implements Initializable {

    @FXML
    private Label adminLoggedInAsEmail;

    @FXML
    private Label adminSearchCustPNR_Error;

    @FXML
    private Button adminSearchCustPNR_Button;

    @FXML
    private String response;

    @FXML
    private ListView listViewBorrowedBooksSpecificCustomer;

    @FXML
    private TextField adminSearchCustPNR_Input;

    private String selectedBookString;

    private String selectedBookID;

    private String personnummerToCustomer;


    ConnectionManager connectionManager = new ConnectionManager();

    Utility utility = new Utility();

    JSONObject object = new JSONObject();

    @FXML
    private void cAdminSearchCustPNR_Button(ActionEvent event) {

        personnummerToCustomer = utility.encodeToURL(adminSearchCustPNR_Input.getText());

        // för att mata in PNR som ska skickas till databasen
        populateListViewCustomerBorrowedBooks(personnummerToCustomer); // personnummer som argument från textfield: adminSearchCustPNR_Input

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String adminEmail = "";
        try {
            File file = new File("src/main/resources/com/example/bibliotekfrontend/admin_email.txt");
            Scanner scanner = new Scanner(file);
            adminEmail = scanner.next();

        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        adminLoggedInAsEmail.setText("You are logged in as: " + adminEmail);
        adminLoggedInAsEmail.setVisible(true);
    }

    @FXML
    private void cSelectedBorrowedBookFromListViewToReturnAsAdmin(MouseEvent mouseEvent) {
        listViewBorrowedBooksSpecificCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                selectedBookString = (String) listViewBorrowedBooksSpecificCustomer.getSelectionModel().getSelectedItem();
                selectedBookID = utility.getBookIDFromSelectedString(selectedBookString);
            }
        });
    }

    @FXML
    private void cReturnBookAsAdmin(ActionEvent event) {
        System.out.println(selectedBookID);
        System.out.println("T7");
        response = connectionManager.sendGetRequest("/return_book/?book_id=" + selectedBookID);
        System.out.println("T5");
        System.out.println(response);
        System.out.println("T6");
        populateListViewCustomerBorrowedBooks(personnummerToCustomer);
    }

    @FXML
    private void cButtonBackToAdminLoginFirstPage(ActionEvent actionEvent) throws IOException {
        Application application = new Application();
        application.changeScene("AdminLoginFirstPage.fxml");
    }

    private void populateListViewCustomerBorrowedBooks(String customer_pnr_from_input) {
        listViewBorrowedBooksSpecificCustomer.getItems().clear();
        response = connectionManager.sendGetRequest("/which_books_are_borrowed?customer_pnr_live=" + customer_pnr_from_input);
        System.out.println(response);
        //listViewBorrowedBooksSpecificCustomer.;
        if (response.contains("wrong pnr")) {
            adminSearchCustPNR_Error.setVisible(true);
            adminSearchCustPNR_Error.setText("Felaktigt personnummer!");

        } else if (response.contains("success")) {
            adminSearchCustPNR_Error.setVisible(false);

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
    }
}
