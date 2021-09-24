package lesson6.gb.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = getClass().getResource("/client.fxml");

        System.out.println("URL " + url);
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("2k18Chat");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
