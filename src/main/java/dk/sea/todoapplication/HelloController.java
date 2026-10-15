package dk.sea.todoapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static java.awt.SystemColor.text;

public class HelloController implements Initializable {
    @FXML
    private Button btnCreate;
    @FXML
    private ListView<String> listviewToDos;
    @FXML
    public TextField txtField;
    @FXML
    private ListView<String> listviewInProgress;
    @FXML
    private ListView<String> listviewDone;

    private ObservableList<String> itemList = FXCollections.observableArrayList();
    private ObservableList<String> inProgressList = FXCollections.observableArrayList();
    private ObservableList<String> doneList = FXCollections.observableArrayList();
    int selectedIndex = -1 ;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        listviewToDos.setItems(itemList);
        listviewInProgress.setItems(inProgressList);
        listviewDone.setItems(doneList);
            btnCreate.setOnAction(event -> {
                String text = txtField.getText();
                if (!text.isEmpty()) {
                    if (selectedIndex == -1) {
                        itemList.add(text);

                    } else {
                        itemList.set(selectedIndex, text);
                        selectedIndex = -1;
                    }
                    txtField.clear(); 
                }
            });

            listviewToDos.setOnMouseClicked(event ->{
                selectedIndex = listviewToDos.getSelectionModel().getSelectedIndex();
                String clickText = listviewToDos.getSelectionModel().getSelectedItem();
                txtField.setText(clickText);
            });
        
    }





    public void btnMoveForward1(ActionEvent actionEvent) {

        int index = listviewToDos.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            String item = itemList.get(index);
            itemList.remove(index);
            inProgressList.add(item);


        }
    }


    public void btnMoveBack1(ActionEvent actionEvent) {
        int index = listviewInProgress.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            String item = inProgressList.get(index);
            inProgressList.remove(index);
            itemList.add(item);
        }

    }

    public void btnMoveForward2(ActionEvent actionEvent) {
        int index = listviewInProgress.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            String item = inProgressList.get(index);
            inProgressList.remove(index);
            doneList.add(item);
        }
    }

    public void btnMoveBack2(ActionEvent actionEvent) {
        int index = listviewDone.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            String item = doneList.get(index);
            doneList.remove(index);
            inProgressList.add(item);
        }
    }


}
