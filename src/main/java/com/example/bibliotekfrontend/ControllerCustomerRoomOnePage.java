package com.example.bibliotekfrontend;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerCustomerRoomOnePage implements Initializable {

    @FXML
    private TableView<RoomCalender> tableCalender;
    @FXML
    private TableColumn<RoomCalender, String> col_ID_Room;
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
    @FXML
    private Label errorLabel;
    @FXML
    private Label confirmationLabel;

    @FXML
    private Button buttonLoad;  // belongs to cLoadDataFromDatabaseButton
    @FXML
    private Button BookRoom; //BookRoomButton
    public ObservableList<RoomCalender> items = FXCollections.observableArrayList();


    Utility u = new Utility();
    private String response;
    ConnectionManager connectionManager = new ConnectionManager();
    JSONObject object = new JSONObject();


    public void settCalender(String calender) {
        response = calender;
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            ObservableList<RoomCalender> list = cLoadDataFromDatabase("vattenmelon");

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
    private void cBackFromRoomOneToCustomerScheduleRoom(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("customerScheduleRoom.fxml");

    }


    @FXML
    public ObservableList<RoomCalender> cLoadDataFromDatabase(String room_name_live) {


            tableCalender.getItems().clear();
            response = connectionManager.sendGetRequest("/Room_calender?room_name_live=" + room_name_live);
            response = u.trimResponse(response);
            System.out.println( response);



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

    //@FXML
    //private void cTableView(MouseEvent event) {
        //tableCalender.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            //@Override
            //public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                //tableCalender.setItems((ObservableList<RoomCalender>) object);
                //tableCalender.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                //TableView.TableViewSelectionModel<RoomCalender> selectionModel = tableCalender.getSelectionModel();
              //  ObservableList<String> selectedItems = selectionModel.getSelectedItems();
               // buttonLoad.setText(String.valueOf(selectedItems));













               // String selectedRoomString = tableCalender.getSelectionModel().getSelectedItem();
                //String selectedID_room = u.getID_roomFromSelectedString(selectedRoomString);
                //buttonLoad.setText(String.valueOf(selectedID_room));







    @FXML
    private void cBookRoomButton(ActionEvent event) {



    }


    //public void bookRoom() {
        //String date_pick;
        //String time_in;
        //String customer_pnr_live;
        //String room_name_live;


      //  response = connectionManager.sendGetRequest("/pick_a_room?room_name_live=" + room_name_live + "date_pick=" + date_pick + "customer_pnr_live" + customer_pnr_live);
       // System.out.println(response);


        //if (response.contains(date_pick)) {
          //  confirmationLabel.setVisible(false);
            //errorLabel.setVisible(true);
            //errorLabel.setText("Vänligen boka en ledig tid");
        //};




















    public void clickedColumn(MouseEvent event) {
        System.out.println("check");
    }
    }




