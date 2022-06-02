package com.example.bibliotekfrontend;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCustomerRoomOnePage implements Initializable {

    @FXML
    private TableView<String> tableCalender;
    @FXML
    private TableColumn<String, String> col_ID_Room;
    @FXML
    private TableColumn<String, String> col_Date;
    @FXML
    private TableColumn<String, String> col_0910;
    @FXML
    private TableColumn<String, String> col_1011;
    @FXML
    private TableColumn<String, String> col_1112;
    @FXML
    private TableColumn<String, String> col_1213;
    @FXML
    private TableColumn<String, String> col_1314;
    @FXML
    private TableColumn<String, String> col_1415;
    @FXML
    private TableColumn<String, String> col_1516;
    @FXML
    private TableColumn<String, String> col_1617;
    @FXML
    private TableColumn<String, String> col_1718;
    @FXML
    private Button buttonLoad;  // belongs to cLoadDataFromDatabaseButton
    @FXML
    private Button BookRoom;


    Utility u = new Utility();
    private String response;
    ConnectionManager connectionManager = new ConnectionManager();
    JSONObject object = new JSONObject();

    public void settCalender(String calender) {
        response = calender;
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // col_ID_Room.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        //col_Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        //col_0910 .setCellValueFactory(new PropertyValueFactory<>("09.00-10.00"));
        //col_1011 .setCellValueFactory(new PropertyValueFactory<>("10.00-11.00"));
        //col_1112 .setCellValueFactory(new PropertyValueFactory<>("11.00-12.00"));
        //col_1213 .setCellValueFactory(new PropertyValueFactory<>("12.00-13.00"));
        //col_1314 .setCellValueFactory(new PropertyValueFactory<>("13.00-14.00"));
        //col_1415 .setCellValueFactory(new PropertyValueFactory<>("14.00-15.00"));
        //col_1516 .setCellValueFactory(new PropertyValueFactory<>("15.00-16.00"));
        //col_1617 .setCellValueFactory(new PropertyValueFactory<>("16.00-17.00"));
        //col_1718 .setCellValueFactory(new PropertyValueFactory<>("17.00-18.00"));







    }

    @FXML
    private void cBackFromRoomOneToCustomerScheduleRoom(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerScheduleRoom.fxml");

    }

    @FXML
    private void cLoadDataFromDatabaseButton(ActionEvent event) throws IOException {
        Platform.runLater(() -> {
            //String input = u.encodeToURL(buttonLoad.getText());
            tableCalender.getItems().clear();
            response = connectionManager.sendGetRequest("/Room_calender?room_name_live=ture2");
            System.out.println(response);
            response = u.trimResponse(response);


            JSONArray array = new JSONArray(response);
            ObservableList items = FXCollections.observableArrayList();
            tableCalender.setItems((items));
           // ObservableList items = FXCollections.observableArrayList(array);
            //tableCalender.setItems(items);


            for (int i = 0; i < array.length(); i++) {
                object = array.getJSONObject(i);

                tableCalender.getItems().add(col_ID_Room + object.getString("ID_room"));
                tableCalender.getItems().add(col_Date + object.getString("Date"));
                tableCalender.getItems().add(col_0910 + object.getString("09.00-10.00"));
                tableCalender.getItems().add(col_1011 + object.getString("10.00-11.00"));
                tableCalender.getItems().add(col_1112 + object.getString("11.00.12.00"));
                tableCalender.getItems().add(col_1213 + object.getString("12.00-13.00"));
                tableCalender.getItems().add(col_1314 + object.getString("13.00-14.00"));
                tableCalender.getItems().add(col_1415 + object.getString("14.00-15.00"));
                tableCalender.getItems().add(col_1516 + object.getString("15.00-16.00"));
                tableCalender.getItems().add(col_1617 + object.getString("16.00-17.00"));
                tableCalender.getItems().add(col_1718 + object.getString("17.00-18.00"));






            }
        });

    }

    @FXML
    private void cTableView(MouseEvent event) {
        tableCalender.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                tableCalender.setItems((ObservableList<String>) object);
                tableCalender.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                TableView.TableViewSelectionModel<String> selectionModel = tableCalender.getSelectionModel();
                ObservableList<String> selectedItems = selectionModel.getSelectedItems();
                buttonLoad.setText(String.valueOf(selectedItems));













               // String selectedRoomString = tableCalender.getSelectionModel().getSelectedItem();
                //String selectedID_room = u.getID_roomFromSelectedString(selectedRoomString);
                //buttonLoad.setText(String.valueOf(selectedID_room));





            }
        });

    }
    @FXML
    private void cBookRoomButton(ActionEvent event) throws IOException {

    }


}
