package pl.escience.zdpp.lab03gr1.javafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterAccountController implements Initializable {
    @FXML
    private TextField textFieldName, textFieldSurname, textFieldEmail, textFieldStreet, textFieldPostalCode,
            textFieldCity, textFieldCountry, textFieldLogin;

    @FXML
    private Label labelName, labelSurname, labelEmail, labelStreet, labelPostalCode, labelCity, labelCountry,
            labelLogin, labelPassword, labelConfirmPassword;

    @FXML
    private PasswordField passwordFieldPassword, passwordFieldConfirmPassword;

    @FXML
    void buttonRegister_onAction() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
