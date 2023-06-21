package application;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;


public class Main extends Application {
    public static User authuser;
    @Override
    public void start(Stage Stage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/application/Main.fxml"));
        FXMLLoader loaderAuth = new FXMLLoader();
        loaderAuth.setLocation(Main.class.getResource("/application/Auth.fxml"));

        try {
            loaderAuth.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loaderAuth.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

        if (authuser == null)
            System.exit(0);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root_main = loader.getRoot();
        Stage stage_main = new Stage();
        stage_main.setScene(new Scene(root_main));
        stage_main.showAndWait();

    }

    public static void setAuthuser(User user) {
        authuser = user;
    }

    public static User getAuthuser() {
        return authuser;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
