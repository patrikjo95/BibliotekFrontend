package com.example.bibliotekfrontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCustomerRoomTwoPage implements Initializable {
    @FXML
    private TableView<RoomCalender> tableCalender;
    @FXML
    private TableColumn<RoomCalender, String> col_Date;
    @FXML
    private TableColumn<RoomCalender, String> col_0910;
    @FXML
    private TableColumn<RoomCalender, String> col_1011;
    @FXML
    private TableColumn<RoomCalender, String> col_1112;
    @FXML
    private TableColumn<RoomCalender, String> col_1213;
    @FXML
    private TableColumn<RoomCalender, String> col_1314;
    @FXML
    private TableColumn<RoomCalender, String> col_1415;
    @FXML
    private TableColumn<RoomCalender, String> col_1516;
    @FXML
    private TableColumn<RoomCalender, String> col_1617;
    @FXML
    private TableColumn<RoomCalender, String> col_1718;

    public ObservableList<RoomCalender> items = FXCollections.observableArrayList();

    Utility u = new Utility();
    private String response;
    ConnectionManager connectionManager = new ConnectionManager();
    JSONObject object = new JSONObject();
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<RoomCalender> list = cLoadDataFromDatabaseTwo("grapefrukt");

        col_Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        col_0910.setCellValueFactory(new PropertyValueFactory<>("firstTime"));
        col_1011.setCellValueFactory(new PropertyValueFactory<>("secondTime"));
        col_1112.setCellValueFactory(new PropertyValueFactory<>("thirdTime"));
        col_1213.setCellValueFactory(new PropertyValueFactory<>("fourthTime"));
        col_1314.setCellValueFactory(new PropertyValueFactory<>("fifthTime"));
        col_1415.setCellValueFactory(new PropertyValueFactory<>("sixTime"));
        col_1516.setCellValueFactory(new PropertyValueFactory<>("sevenTime"));
        col_1617.setCellValueFactory(new PropertyValueFactory<>("eightTime"));
        col_1718.setCellValueFactory(new PropertyValueFactory<>("nineTime"));

        tableCalender.setItems(list);
        col_0910.setCellFactory(TextFieldTableCell.forTableColumn());

        tableCalender.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableCalender.getSelectionModel().setCellSelectionEnabled(true);


    }

    @FXML
    public ObservableList<RoomCalender> cLoadDataFromDatabaseTwo(String room_name_live) {


        tableCalender.getItems().clear();
        response = connectionManager.sendGetRequest("/Room_calender?room_name_live=" + room_name_live);
        response = u.trimResponse(response);
        System.out.println(response);


        JSONArray array = new JSONArray(response);
        System.out.println("Array" + array);
        ObservableList<RoomCalender> table = FXCollections.observableArrayList();


        for (int i = 0; i < array.length(); i++) {
            object = array.getJSONObject(i);

            RoomCalender roomCalender = new RoomCalender(
                    object.getString("Date"),
                    object.getString("09.00-10.00"),
                    object.getString("10.00-11.00"),
                    object.getString("11.00-12.00"),
                    object.getString("12.00-13.00"),
                    object.getString("13.00-14.00"),
                    object.getString("14.00-15.00"),
                    object.getString("15.00-16.00"),
                    object.getString("16.00-17.00"),
                    object.getString("17.00-18.00")
            );
            //System.out.println("roomCalender" + roomCalender);

            table.add(roomCalender);

        }

//return items;
        System.out.println("Table:" + table);
        return table;

    }

    public void settCalender(String calender) {
    }

    @FXML
    private void cBackFromRoomTwoToCustomerScheduleRoom(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerScheduleRoom.fxml");
    }
}































