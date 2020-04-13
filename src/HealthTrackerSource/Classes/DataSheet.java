package HealthTrackerSource.Classes;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import com.google.gson.*;

public class DataSheet {

    //declare map for daily user inputs
    public HashMap<String, Integer[]> dailyInput;

    //declare map for daily comments
    public HashMap<String, String> dailyComment;

    //declare date object
    private Time date;

    //declare gson object
    Gson gson;


    //constructor class
    public DataSheet() throws IOException {

        //instantiate gson object from Gson class
        gson = new Gson();

        //instantiate hash map for mapping date to the three integer inputs
        Reader num_reader = Files.newBufferedReader(Paths.get("data/healthNumData.json"));
        File healthNumFile = new File("data/healthNumData.json");
        if(healthNumFile.length()==0){
            dailyInput = new HashMap<>();
        }
        else{
            dailyInput = gson.fromJson(num_reader, HashMap.class);
        }

        //instantiate hash map for respective daily comments
        Reader comment_reader = Files.newBufferedReader(Paths.get("data/commentData.json"));
        File commentFile = new File("data/commentData.json");
        if(commentFile.length()==0){
            dailyComment = new HashMap<>();
        }
        else{
            dailyComment = gson.fromJson(comment_reader, HashMap.class);
        }

        //instantiate date object
        date = new Time();
    }

    /*
    Method inputs: 3 integer values corresponding to personal ratings (from slider inputs) from 0-10 for the user
                   and string value for comments.
    Outputs: void
    Intended use: Inserts 3 slider inputs and comment into corresponding hash-maps as values with keys of today's current date
    */
    public void InsertInput(int mentalHealth, int physicalHealth, int happiness, String comment){
        //set slider_in to [0,0,0] for mental, physical, and happiness respectively
        Integer[] sliderTemporary = new Integer[3];

        //set temp array for respective slider inputs
        sliderTemporary[0] = mentalHealth;
        sliderTemporary[1] = physicalHealth;
        sliderTemporary[2] = happiness;

        //put stringed date and temp array into hash map
        dailyInput.put(date.getDateString(), sliderTemporary);

        //put stringed date and comment into hash map
        dailyComment.put(date.getDateString(), comment);
    }


}
