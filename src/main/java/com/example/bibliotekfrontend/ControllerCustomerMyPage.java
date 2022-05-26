package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControllerCustomerMyPage implements Initializable {

    private String response;

    private ConnectionManager connectionManager;

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
        String customer_pnr_from_file = "";
        //
        try {
            File file = new File("src/main/resources/com/example/bibliotekfrontend/customer_pnr_txt_file.txt");
            Scanner scanner = new Scanner(file);
            customer_pnr_from_file = scanner.next();
            //while (scanner.hasNext()) {
            //    customer_pnr_from_file = scanner.next()
            //}
            //customer_pnr_from_file = scanner.hasNext();
            //customer_pnr_from_file.appendText()

            // YourTextArea.appendText(s.nextInt() + " ");
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        /*
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
         */
        //
        customerLoggedInAsDetails.setText("You are logged in as: " + customer_pnr_from_file);
        customerLoggedInAsDetails.setVisible(true);

        // ska ta inloggat customer_pnr för att populate listview
        // listViewBorrowedBooksSpecificCustomer.

        // FORTSÄTT HÄR! gör backend för which_books_are_borrowed()
        response = connectionManager.sendGetRequest("/which_books_are_borrowed?customer_pnr_live=" + customer_pnr_from_file);
        System.out.println(response);
        // listViewBorrowedBooksSpecificCustomer = connectionManager.sendGetRequest("/which_books_are_borrowed?customer_pnr_live=" + customer_pnr_from_file);


    }

}
