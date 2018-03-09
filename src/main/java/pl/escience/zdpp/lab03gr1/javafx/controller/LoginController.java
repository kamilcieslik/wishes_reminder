package pl.escience.zdpp.lab03gr1.javafx.controller;

import javafx.application.Platform;
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
import javafx.stage.StageStyle;
import pl.escience.zdpp.lab03gr1.app.Main;
import sun.rmi.runtime.Log;

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

        Boolean sceneWasLoadedSuccessfully = true;
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getClassLoader().getResource("fxml/main.fxml"));
            loader.load();
        } catch (IOException ioEcx) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ioEcx);
            sceneWasLoadedSuccessfully = false;
        }

        if (sceneWasLoadedSuccessfully) {
            Parent parent = loader.getRoot();
            Stage stage = Main.getMainStage();
            stage.resizableProperty().setValue(Boolean.TRUE);
            stage.setOnHidden(event -> Platform.exit());
            Stage currentStage = (Stage) textFieldLogin.getScene().getWindow();
            Scene scene = new Scene(parent, currentStage.getWidth() - 16.0, currentStage.getHeight() - 42.5);
            stage.setScene(scene);
        }
    }

    @FXML
    void buttonRegister_onAction() {

    }

    @FXML
    void passwordFieldPassword_onAction() {

    }

    @FXML
    void textFieldLogin_onAction() {

    }
}
