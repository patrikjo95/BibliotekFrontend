package com.example.bibliotekfrontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAdminAllBorrowedBooksPage implements Initializable {
    @FXML
    private TableColumn<BorrowedBook, String> PNRColumn;
    @FXML
    private TableColumn<BorrowedBook, String>  BookIDColumn;
    @FXML
    private TableColumn<BorrowedBook, String>  BookTitleColumn;
    @FXML
    private TableColumn<BorrowedBook, String>  BookAuthorColumn;
    @FXML
    private TableColumn<BorrowedBook, String>  BookYearColumn;
    @FXML
    private TableColumn<BorrowedBook, String>  ReturnDateColumn;
    @FXML
    private TableView<BorrowedBook> allBorrowedBooksTable;

    Utility u = new Utility();
    ConnectionManager connectionManager = new ConnectionManager();
    private String response;
    JSONObject object = new JSONObject();
    public ObservableList<BorrowedBook> table = FXCollections.observableArrayList();



    @FXML
    private void cAllBooksBackButton(ActionEvent event) throws IOException {
        Application a = new Application();
        a.changeScene("adminLoginFirstPage.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<BorrowedBook> list = populateTable();

        PNRColumn.setCellValueFactory(new PropertyValueFactory<>("customerPNR"));
        BookIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID_book"));
        BookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("book_title"));
        BookAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("book_author"));
        BookYearColumn.setCellValueFactory(new PropertyValueFactory<>("book_year"));
        ReturnDateColumn.setCellValueFactory(new PropertyValueFactory<>("return_date"));

        allBorrowedBooksTable.setItems(list);


    }


    @FXML
    public ObservableList<BorrowedBook> populateTable(){

        response = connectionManager.sendGetRequest("/select_all_borrowed_books");
        response = u.trimResponse(response);
        System.out.println(response);

        JSONArray array = new JSONArray(response);
        System.out.println("Array: " + array);
        ObservableList<BorrowedBook> table = FXCollections.observableArrayList();

        for (int i = 0; i < array.length(); i++) {
            object = array.getJSONObject(i);

            BorrowedBook borrowedBook = new BorrowedBook(object.getString("Personnummer"), object.getString("Bok ID"), object.getString("Titel"), object.getString("Författare"), object.getString("Utgivningsår"), object.getString("Återlämnas senast"));

            System.out.println("borrowedBook" + borrowedBook);

            table.add(borrowedBook);

        }

        System.out.println("Table: " + table);
        return table;

    }
}
