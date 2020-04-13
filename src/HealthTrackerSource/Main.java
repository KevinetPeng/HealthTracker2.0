package HealthTrackerSource;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //connect root stage to fxml
        Parent root = FXMLLoader.load(getClass().getResource("Views/design.fxml"));

        primaryStage.setTitle("Health Tracker");

        Scene scene = new Scene(root, 800, 500);

        primaryStage.setScene(scene);

        //make stage undecorated
        primaryStage.initStyle(StageStyle.UNDECORATED);

        //add the stylesheet Style.css for stage styling
        scene.getStylesheets().add("HealthTrackerSource/Views/Style.css");

        //load semi-bold open sans font
        Font.loadFont(Main.class.getResource("Fonts/OpenSans-Semibold-webfont.ttf").toExternalForm(), 12);

        //show stage
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
