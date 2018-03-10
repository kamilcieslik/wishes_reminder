package pl.escience.zdpp.lab03gr1.javafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pl.escience.zdpp.lab03gr1.app.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController implements Initializable {
    @FXML
    private TextField textFieldLogin;
    @FXML
    private PasswordField passwordFieldPassword;
    @FXML
    private Label labelInvalidLoginOrPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void buttonLogin_onAction() {
        //TODO: Login

        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getClassLoader().getResource("fxml/main.fxml"));
            loader.load();
            Parent parent = loader.getRoot();
            Stage primaryStage = new Stage();
            Main.setMainStage(primaryStage);
            primaryStage.setTitle("Wishes Reminder");
            primaryStage.getIcons().add(new Image("/image/icon.png"));
            primaryStage.setMinWidth(950);
            primaryStage.setMinHeight(890);
            primaryStage.setScene(new Scene(parent, 1600, 900));
            Stage stage = (Stage) textFieldLogin.getScene().getWindow();
            stage.hide();
            primaryStage.show();
        } catch (IOException ioEcx) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ioEcx);
        }
    }

    @FXML
    void buttonRegister_onAction() {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getClassLoader().getResource("fxml/register_or_modify_account.fxml"));
            loader.load();
            Parent parent = loader.getRoot();
            Stage primaryStage = new Stage();
            Main.setMainStage(primaryStage);
            primaryStage.setTitle("Wishes Reminder");
            primaryStage.getIcons().add(new Image("/image/icon.png"));
            primaryStage.setMinWidth(1100);
            primaryStage.setMinHeight(765);
            primaryStage.setScene(new Scene(parent, 1200, 780));
            Stage stage = (Stage) textFieldLogin.getScene().getWindow();
            stage.hide();
            primaryStage.show();
        } catch (IOException ioEcx) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ioEcx);
        }
    }

    @FXML
    void passwordFieldPassword_onAction() {
        if (!labelInvalidLoginOrPassword.getText().equals(""))
            labelInvalidLoginOrPassword.setText("");
    }

    @FXML
    void textFieldLogin_onAction() {
        if (!labelInvalidLoginOrPassword.getText().equals(""))
            labelInvalidLoginOrPassword.setText("");
    }
}
