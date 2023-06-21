package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button kj;
    @FXML
    private Button C;
    @FXML
    private Button kj1;

    @FXML
    private Button kj10;

    @FXML
    private Button kj11;

    @FXML
    private Button kj12;

    @FXML
    private Button kj13;

    @FXML
    private Button kj14;

    @FXML
    private Button kj15;

    @FXML
    private Button kj16;

    @FXML
    private Button kj17;

    @FXML
    private Button kj18;

    @FXML
    private Button kj19;

    @FXML
    private Button kj22;

    @FXML
    private Button kj2;

    @FXML
    private Button kj20;

    @FXML
    private Button kj21;

    @FXML
    private Button kj3;

    @FXML
    private Button kj4;

    @FXML
    private Button kj5;

    @FXML
    private Button kj6;

    @FXML
    private Button kj7;

    @FXML
    private Button kj8;

    @FXML
    private Button kj9;

    @FXML
    private Button startbutton;
    @FXML
    private Label result;
    @FXML
    public Label userlabel;
    @FXML
    public Label counterlabel;
    private float number1 = 0;

    private float number2 = 0;

    private String operator = "";


    private boolean start = true;
    private Calclulate calculate = new Calclulate();
    private int counter = 10;
    private User user;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        user = Main.getAuthuser();

        userlabel.setText("Welcome, " + user.username + "! Your status: " + user.status);
        counterlabel.setText("");
        if (user.status.equals("FREE")) {
            kj.setDisable(true);
            kj12.setDisable(true);
            kj16.setDisable(true);
            kj17.setDisable(true);
            kj21.setDisable(true);
            kj22.setDisable(true);
        }
        if (user.status.equals("STANDARD")) {
            counter = 5;
            counterlabel.setText("You have " + counter + " operations left");
        }
        if (user.status.equals("SUPERVIP")) {
            counterlabel.setText("Calculation history is being written to History.txt");
        }
    }
    @FXML
    public void processNumber(ActionEvent event) {
        if (start) {
            result.setText("");
            start = false;
        }
        String value = ((Button) event.getSource()).getText();

        if (user.status.equals("FREE") && !result.getText().equals(""))
        {
            if (Float.parseFloat(result.getText()) < 1000 && Float.parseFloat(result.getText()) >= 100 && !value.equals("."))
                return;
            if (Float.parseFloat(result.getText()) >= 1000)
                return;
        }


        for (int i = 0; i < result.getText().length(); ++i) {
            if (result.getText().charAt(i) == '.' && value.equals(".")) {
                value = "";
            }
        }
        result.setText(result.getText() + value);
    }

    @FXML
    public void TwoOperator(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        if (user.status.equals("FREE") && !result.getText().equals(""))
            if (Float.parseFloat(result.getText()) >= 1000)
                return;

        if (!value.equals("=")) {
            if (!operator.isEmpty())
                return;

            operator = value;
            number1 = Float.parseFloat(result.getText());
            result.setText("");

        } else {
            if (operator.isEmpty())
                return;

            if (user.status.equals("STANDARD") && counter <= 0)
                return;

            number2 = Float.parseFloat(result.getText());
            float output = calculate.calculateTwoNumber(number1, number2, operator);
            result.setText(String.valueOf(output));
            if (output == 0 && (operator.equals("/")) || operator.equals("Mod")) {
                result.setText("Деление на 0");
            }
            if (user.status.equals("STANDARD")) {
                counter--;
                counterlabel.setText("You have " + counter + " operations left");
            }

            if (user.status.equals("SUPERVIP")) {
                try (FileWriter history = new FileWriter("History.txt", true)) {
                    history.write(Float.toString(number1));
                    history.write(' ');
                    history.write(operator);
                    history.write(' ');
                    history.write(Float.toString(number2));
                    history.write(' ');
                    history.write('=');
                    history.write(' ');
                    history.write(Float.toString(output));
                    history.write('\n');
                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                }
            }

            operator = "";
        }
    }

    public void OneOperator(ActionEvent event) {

        String value = ((Button) event.getSource()).getText();
        if (!operator.isEmpty())
            return;

        if (user.status.equals("STANDARD") && counter <= 0)
            return;

        operator = value;
        number1 = Float.parseFloat(result.getText());
        result.setText("");
        if (Objects.equals(operator, "√") && number1 < 0) {
            result.setText("Корень из отрицательного числа");
            return;
        }
        if (Objects.equals(operator, "1/x") && number1 == 0){
            result.setText("Деление на 0");
            return;
        }

        float output = calculate.calculateOneNumber(number1, operator);
        result.setText(String.valueOf(output));

        if (user.status.equals("STANDARD")) {
            counter--;
            counterlabel.setText("You have " + counter + " operations left");
        }

        if (user.status.equals("SUPERVIP")) {
            try (FileWriter history = new FileWriter("History.txt")) {
                history.write(operator);
                history.write(' ');
                history.write(Float.toString(number1));
                history.write(' ');
                history.write('=');
                history.write(Float.toString(output));
                history.write('\n');
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }

        operator = "";
    }

    public void ClearFunction(ActionEvent event) {
        operator = "";
        start = true;
        result.setText("");
    }

    public void ClearFunctionCM(ActionEvent event) {
        operator = "";
        number1 = Float.parseFloat(result.getText());
        start = true;
        int counter = 0;
        for (int i = 0; i < Float.toString(number1).length(); i++) {
            if (Float.toString(number1).charAt(i) == '.' && Float.toString(number1).charAt(i + 1) == '0') {
                counter = 3;
            }
            if (Float.toString(number1).charAt(i) == '.' && Float.toString(number1).charAt(i + 1) != '0') {
                counter = 1;
            }
        }
        char[] number = new char[Float.toString(number1).length() - counter];
        for (int i = 0; i < Float.toString(number1).length() - counter; ++i) {
            number[i] = Float.toString(number1).charAt(i);
        }
        String newNumber = new String(number);
        result.setText(newNumber);
    }

}

