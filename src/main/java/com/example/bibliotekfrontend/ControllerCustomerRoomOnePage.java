package com.example.bibliotekfrontend;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.json.JSONObject;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCustomerRoomOnePage implements Initializable  {







    //@FXML
    //private Button BookRoomButton;

    @FXML
    private TableView<String> calenderTableView;

    @FXML
    private TableColumn<String,String> col_Date;
    @FXML
    private TableColumn<String,String > col_0910;
    @FXML
    private TableColumn<String,String> col_1011;
    @FXML
    private TableColumn<String,String> col_1112;
    @FXML
    private TableColumn<String,String> col_1213;
    @FXML
    private TableColumn<String,String> col_1314;
    @FXML
    private TableColumn<String,String> col_1415;
    @FXML
    private TableColumn<String,String> col_1516;
    @FXML
    private TableColumn<String,String> col_1617;
    @FXML
    private TableColumn<String,String> col_1718;







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




    }



    @FXML
    private void cGoBackToCustomerScheduleRoom(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerScheduleRoom.fxml");
    }

    @FXML
    public void cBookRoomButton(ActionEvent event) {


    }
    @FXML
    private void cTableView(MouseEvent event) {

    }


}


