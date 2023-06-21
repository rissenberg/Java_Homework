package application;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;


public class AuthController {
    @FXML
    private Button ok;
    @FXML
    private Button quit;
    @FXML
    public Label login;
    @FXML
    public Label error;
    @FXML
    private TextField loginfield;
    @FXML
    private TextField passwordfield;

    private String nickname = "";
    private String password = "";

    public ArrayList<User> users = User.getUsers();

    @FXML
    public void authenticate(ActionEvent event) {
        nickname = loginfield.getText();
        password = passwordfield.getText();
        boolean isFound = false;
        User foundUser = new User("", "", "");

        for(int i = 0; i < users.size(); ++i) {
            if (users.get(i).username.equals(nickname) && users.get(i).password.equals(password)) {
                isFound = true;
                foundUser = users.get(i);
                break;
            }
        }

        if (isFound) {
            //error.setText("GOOD");
            Main.setAuthuser(foundUser);
            Stage stage = (Stage) ok.getScene().getWindow();
            stage.close();
        } else {
            error.setText("Incorrect login or password!");
        }

    }

    @FXML
    public void quitapp(ActionEvent event) {
        System.exit(0);
    }

}

