package HealthTrackerSource.Classes;

import com.google.gson.Gson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


public class Controller {

    private DataSheet dataMap;
    private Gson gson;

    //json strings for sliders and comments respectively
    private String input_json;
    private String comment_json;

    //Observable list for populating tableView
    private ObservableList<DataEntry> tableData;

    //Note the difference between the Controller constructor method and the initialize method.
    //The constructor is called before @FXML annotations are created, and does not have access to them
    //Initialize is called after @FXML annotations are created, which gives it access to them

    //Controller constructor
    public Controller() throws IOException {
        dataMap = new DataSheet();
        gson = new Gson();
    }

    //initialize method that is inherited from javaFX
    public void initialize(){
        tableData = FXCollections.observableArrayList();
        //iterate through the dailyInput hashmap to add slider values to tableData ObservableList to populate tableView
        //key corresponds to date string, val corresponds to slider integer array

        //not all dates contain comments, so first check if comment get returns null. If null, insert empty string for comment value
        //else populate tableData with the DataEntry class which contains all the data corresponding to the date
        for(Map.Entry mapElement : dataMap.dailyInput.entrySet()){
            String key = (String)mapElement.getKey();
            ArrayList<Double> val = (ArrayList<Double>)mapElement.getValue();

            if(dataMap.dailyComment.get(key) == null)
                tableData.add(new DataEntry(key, "", val.get(0).intValue(), val.get(1).intValue(), val.get(2).intValue()));
            else
                tableData.add(new DataEntry(key, dataMap.dailyComment.get(key), val.get(0).intValue(), val.get(1).intValue(), val.get(2).intValue()));

        }

        //create cell value factory for each column in table
        dateColumn.setCellValueFactory(new PropertyValueFactory<DataEntry, String>("date"));
        mentalColumn.setCellValueFactory(new PropertyValueFactory<DataEntry, Integer>("mentalNum"));
        physicalColumn.setCellValueFactory(new PropertyValueFactory<DataEntry, Integer>("physicalNum"));
        happinessColumn.setCellValueFactory(new PropertyValueFactory<DataEntry, Integer>("happinessNum"));
        commentsColumn.setCellValueFactory(new PropertyValueFactory<DataEntry, String>("comment"));

        //Set date sort type
        dateColumn.setSortType(TableColumn.SortType.ASCENDING);

        //Set the items in our dataTable to be from the tableData observable list
        dataTable.setItems(tableData);

        //Sort date column to ASCENDING type previously set
        dataTable.getSortOrder().add(dateColumn);
        dataTable.sort();
    }


    //annotate the closeButton node
    @FXML
    private Button closeButton;

    @FXML
    private void closeApp(){
        // get a handle to the stage by using the closeButton node
        Stage stage = (Stage) closeButton.getScene().getWindow();
        System.out.println("closing");
        // close stage
        stage.close();
    }

    //minimize window by getting stage handle through the close button (same method as closeApp function)
    @FXML
    private void minimizeWindow(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        System.out.println("minimizing");

        //minimize window
        stage.setIconified(true);
    }

    // window drag methods
    private double xOffset, yOffset = 0;

    //get offset, aka the x,y on mouse click
    @FXML
    public void startWindowMove(MouseEvent evt) {
        xOffset = evt.getSceneX();
        yOffset = evt.getSceneY();
    }
    //on dragging, change the x,y positions to current position subtracted by the offset(initial x,y)
    @FXML
    public void dragWindowMove(MouseEvent evt) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.setX(evt.getScreenX() - xOffset);
        stage.setY(evt.getScreenY() - yOffset);
    }

    //annotate three sliders on main tab
    @FXML
    private Slider mentalSlider;
    @FXML
    private Slider physicalSlider;
    @FXML
    private Slider happySlider;

    //annotate comment text area
    @FXML
    private TextArea commentBox;

    //annotate tableView
    @FXML
    private TableView<DataEntry> dataTable;

    //annotate columns
    @FXML
    private TableColumn dateColumn;
    @FXML
    private TableColumn mentalColumn;
    @FXML
    private TableColumn physicalColumn;
    @FXML
    private TableColumn happinessColumn;
    @FXML
    private TableColumn commentsColumn;

    //Enter button pressed method
    @FXML
    public void enterInfo() {

        //input the slider input on click
        dataMap.InsertInput((int)mentalSlider.getValue(), (int)physicalSlider.getValue(), (int)happySlider.getValue(), commentBox.getText());

        // serialize the dailyInput hashmap to json string
        input_json = gson.toJson(dataMap.dailyInput);

        //serialize the dailyComment hashmap to json string
        comment_json = gson.toJson(dataMap.dailyComment);

        //Write JSON file for slider input
        try (FileWriter file = new FileWriter("data/healthNumData.json")) {
            file.write(input_json);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Write JSON file for comment input
        try (FileWriter file = new FileWriter("data/commentData.json")) {
            file.write(comment_json);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(tableData.removeIf(o -> o.date == dataMap.date.getDateString()));

        tableData.add(new DataEntry(dataMap.date.getDateString(), commentBox.getText(), (int)mentalSlider.getValue(), (int)physicalSlider.getValue(), (int)happySlider.getValue()));

        //refresh tableView
        dataTable.refresh();
        dataTable.sort();
    }
}



//TODO: sliders dont click when clicking slightly lower than the slider bar
