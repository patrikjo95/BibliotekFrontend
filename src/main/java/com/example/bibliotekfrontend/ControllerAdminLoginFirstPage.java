package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControllerAdminLoginFirstPage implements Initializable {


    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Button AddBookButton;
    @FXML
    private Button UpdateBookButton;
    @FXML
    private Button DeleteBookButton;
    @FXML
    private Button cAdminLogoutButton;


    @FXML
    private void cGoToAddBookPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("adminAddBook.fxml");
    }

    @FXML
    private void cGoToUpdateBookPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("adminUpdateBook.fxml");
    }

    @FXML
    private void cGoToDeleteBookPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("adminDeleteBook.fxml");
    }



    @FXML
    private void cAdminLogoutButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("admin-login.fxml");
    }

    @FXML
    private void cGoToAddCustomerAdminPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("adminAddCustomer.fxml");
    }

    @FXML
    private void cGoToAdminSearchCustomerPage(ActionEvent event) throws IOException {
        Application application = new Application();
        application.changeScene("adminSearchForCustomer.fxml");
    }

    @FXML
    private Label adminLoggedInAsEmail;

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
        adminLoggedInAsEmail.setText("Du är inloggad som: " + adminEmail);
        adminLoggedInAsEmail.setVisible(true);
    }

    public void cGoToBorrowedBooksPage(ActionEvent actionEvent) throws IOException {
        Application application = new Application();
        application.changeScene("adminAllBorrowedBooksPage.fxml");
    }
}
