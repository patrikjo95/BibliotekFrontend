package com.example.bibliotekfrontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class ControllerAdminLogin {
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button loginButton;

    @FXML
    private Label adminLoginError;

    @FXML
    Utility utility = new Utility();

    @FXML
    private String response;

    @FXML
    ConnectionManager connectionManager = new ConnectionManager();

    public void cBackToFirstPage(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeToFirstPage();
    }

    @FXML
    private void cSendAdminLoginDataToBackEnd(ActionEvent event) throws IOException {
        String adminEmail = usernameTextField.getText();
        System.out.println(adminEmail);
        String adminPassword = passwordTextField.getText();
        System.out.println(adminPassword);
        response = connectionManager.sendGetRequest("/scan_mail_and_password_admin/?test_mail=" + utility.encodeToURL(adminEmail) + "&test_password=" + utility.encodeToURL(adminPassword));
        System.out.println(response);

        if (response.contains("wrong mail")) {
            adminLoginError.setVisible(true);
            adminLoginError.setTextFill(Color.RED);
            adminLoginError.setText("Denna mejladress kunde inte hittas i vårat register.");
        } else if (response.contains("wrong password")) {
            adminLoginError.setVisible(true);
            adminLoginError.setTextFill(Color.RED);
            adminLoginError.setText("Fel lösenord!");
        } else if (response.contains(adminEmail)) {
            try {
                FileWriter myWriter = new FileWriter("src/main/resources/com/example/bibliotekfrontend/admin_email.txt");
                myWriter.write(adminEmail);
                myWriter.close();
                System.out.println("Successfully wrote to file.");
                Application a = new Application();
                a.changeScene("adminLoginFirstPage.fxml");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}
