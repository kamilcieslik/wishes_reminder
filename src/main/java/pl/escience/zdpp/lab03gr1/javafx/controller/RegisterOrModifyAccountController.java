package pl.escience.zdpp.lab03gr1.javafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.escience.zdpp.lab03gr1.app.Main;
import pl.escience.zdpp.lab03gr1.javafx.CustomMessageBox;
import pl.escience.zdpp.lab03gr1.javafx.ListenerMethods;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterOrModifyAccountController implements Initializable {
    private CustomMessageBox customMessageBox;
    // private User loggedUser;

    @FXML
    private Button buttonRegister;
    @FXML
    private TextField textFieldName, textFieldSurname, textFieldEmail, textFieldStreet, textFieldPostalCode,
            textFieldCity, textFieldCountry, textFieldLogin;
    @FXML
    private Label labelName, labelSurname, labelEmail, labelStreet, labelPostalCode, labelCity, labelCountry,
            labelLogin, labelPassword, labelConfirmPassword, labelEnterYourData, labelEnterYourPassword;
    @FXML
    private PasswordField passwordFieldPassword, passwordFieldConfirmPassword;
    @FXML
    private HBox hBoxSetCurrentData;

    public void initUserData() {
        buttonRegister.setText("Modyfikuj");
        labelEnterYourData.setText("Modyfikuj swoje dane");
        labelEnterYourPassword.setText("Modyfikuj hasło identyfikujące konto");

        textFieldLogin.setEditable(false);
        textFieldLogin.setStyle("-fx-background-color: #808080; -fx-background-radius: 20");

        hBoxSetCurrentData.setVisible(true);
        hBoxSetCurrentData.setDisable(false);
        hBoxSetCurrentData.setMinWidth(Control.USE_COMPUTED_SIZE);
        hBoxSetCurrentData.setMinHeight(Control.USE_COMPUTED_SIZE);
        hBoxSetCurrentData.setPrefHeight(Control.USE_COMPUTED_SIZE);
        hBoxSetCurrentData.setPrefWidth(Control.USE_COMPUTED_SIZE);
        hBoxSetCurrentData.setMaxHeight(Control.USE_COMPUTED_SIZE);
        hBoxSetCurrentData.setMaxWidth(Control.USE_COMPUTED_SIZE);

        //TODO: Wywołanie metody wypełniającej textFields i passwordFields danymi użytkownika.
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customMessageBox = new CustomMessageBox("image/icon.png");

        ListenerMethods listenerMethods = new ListenerMethods();
        textFieldName.textProperty().addListener((observable, oldValue, newValue) -> listenerMethods
                .changeLabelTextField("^[A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]+$", textFieldName, labelName,
                        "Podaj imię.", "Niepoprawny format", 50,
                        "Przekroczono limit znaków"));
        textFieldSurname.textProperty().addListener((observable, oldValue, newValue) -> listenerMethods
                .changeLabelTextField("^[A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]+$", textFieldSurname, labelSurname,
                        "Podaj nazwisko.", "Niepoprawny format", 50,
                        "Przekroczono limit znaków"));
        textFieldEmail.textProperty().addListener((observable, oldValue, newValue) -> listenerMethods
                .changeLabelTextField("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\" +
                                "x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(" +
                                "?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]" +
                                "|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01" +
                                "-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])",
                        textFieldEmail, labelEmail, "Podaj adres email.", "Niepoprawny format",
                        50, "Przekroczono limit znaków"));

        textFieldStreet.textProperty().addListener((observable, oldValue, newValue) -> listenerMethods
                .changeLabelTextField("^[A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]+\\s([A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]+" +
                                "\\s)?[1-9][0-9]*[A-Z]?(/[1-9][0-9]*[A-Z]?)?$", textFieldStreet, labelStreet,
                        "Podaj ulicę i nr domu/mieszkania.", "Niepoprawny format", 80,
                        "Przekroczono limit znaków"));

        textFieldPostalCode.textProperty().addListener((observable, oldValue, newValue) -> listenerMethods
                .changeLabelTextField("^[0-9]{2}-[0-9]{3}$", textFieldPostalCode, labelPostalCode,
                        "Podaj kod pocztowy.", "Niepoprawny format", 6,
                        "Przekroczono limit znaków"));

        textFieldCity.textProperty().addListener((observable, oldValue, newValue) -> listenerMethods
                .changeLabelTextField("^[A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]+(\\s[A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]+)?$", textFieldCity, labelCity,
                        "Podaj miasto.", "Niepoprawny format", 50,
                        "Przekroczono limit znaków"));

        textFieldCountry.textProperty().addListener((observable, oldValue, newValue) -> listenerMethods
                .changeLabelTextField("^[A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]+(\\s[A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]+)?$",
                        textFieldCountry, labelCountry, "Podaj kraj.", "Niepoprawny format", 40,
                        "Przekroczono limit znaków"));

        textFieldLogin.textProperty().addListener((observable, oldValue, newValue) -> listenerMethods
                .changeLabelTextField("^(?=.{6,}$)[a-z]+[0-9]*$", textFieldLogin, labelLogin,
                        "Podaj login.", "Niepoprawny format", 20,
                        "Przekroczono limit znaków"));

        passwordFieldPassword.textProperty().addListener((observable, oldValue, newValue) -> changePasswordFieldText());

        passwordFieldConfirmPassword.textProperty().addListener((observable, oldValue, newValue) ->
                changeConfirmPasswordFieldText());
    }


    @FXML
    void buttonRegister_onAction() {
        String name = labelName.getText();
        String surname = labelSurname.getText();
        String email = labelEmail.getText();

        String street = labelStreet.getText();
        String postalCode = labelPostalCode.getText();
        String city = labelCity.getText();
        String country = labelCountry.getText();
        String login = labelLogin.getText();
        String password = labelPassword.getText();
        String confirmedPassword = labelConfirmPassword.getText();

        if (buttonRegister.getText().equals("Zarejestruj")) {
            if (name.isEmpty() && surname.isEmpty() && email.isEmpty() && street.isEmpty()
                    && postalCode.isEmpty() && city.isEmpty() && country.isEmpty() && login.isEmpty() && password.isEmpty()
                    && confirmedPassword.isEmpty()) {
                // TODO: Utworzenie użytkownika wraz z adresem. Przejście do głównej sceny jako zalogowany użytkownik.
                // TODO: Przejście do głównej sceny z wywołaniem metody initUserData(loggedUser).
            } else if (name.isEmpty() && surname.isEmpty() && email.isEmpty()
                    && street.equals("Podaj ulicę i nr domu/mieszkania.") && postalCode.equals("Podaj kod pocztowy.")
                    && city.equals("Podaj miasto.") && country.equals("Podaj kraj.") && login.isEmpty()
                    && password.isEmpty() && confirmedPassword.isEmpty()) {
                // TODO: Utworzenie użytkownika bez adresu. Przejście do głównej sceny jako zalogowany użytkownik.
                // TODO: Przejście do głównej sceny z wywołaniem metody initUserData(loggedUser).
            } else
                customMessageBox.showMessageBox(Alert.AlertType.WARNING, "Ostrzeżenie",
                        "Operacja rejestracji nie powiedzie się.",
                        "Powód: Nie wszystkie wartości mają poprawny format.")
                        .showAndWait();
        } else {
            if (name.isEmpty() && surname.isEmpty() && email.isEmpty() && street.isEmpty()
                    && postalCode.isEmpty() && city.isEmpty() && country.isEmpty() && login.isEmpty() && password.isEmpty()
                    && confirmedPassword.isEmpty()) {
                // TODO: Modyfikacja użytkownika z adresem. Sprawdzenie czy użytkownik posiada adres.
                // TODO: Jeżeli tak to modyfikacja adresu, w przeciwnym razie utworzenie adresu użytkownika.
                // TODO: Powrót do głównej sceny z wywołaniem metody initUserData(loggedUser).
            } else if (name.isEmpty() && surname.isEmpty() && email.isEmpty()
                    && street.equals("Podaj ulicę i nr domu/mieszkania.") && postalCode.equals("Podaj kod pocztowy.")
                    && city.equals("Podaj miasto.") && country.equals("Podaj kraj.") && login.isEmpty()
                    && password.isEmpty() && confirmedPassword.isEmpty()) {
                System.out.println("Użytkownik bez adresu.");
                // TODO: Modyfikacja użytkownika bez adresu. Sprawdzenie czy użytkownik posiada adres.
                // TODO: Jeżeli tak to usunięcie adresu użytkownika.
                // TODO: Powrót do głównej sceny z wywołaniem metody initUserData(loggedUser).
            } else
                customMessageBox.showMessageBox(Alert.AlertType.WARNING, "Ostrzeżenie",
                        "Operacja modyfikacji danych nie powiedzie się.",
                        "Powód: Nie wszystkie wartości mają poprawny format.")
                        .showAndWait();
        }
    }

    @FXML
    void buttonCancel_onAction() {
        if (buttonRegister.getText().equals("Zarejestruj")) {
            FXMLLoader loader = new FXMLLoader();
            try {
                loader.setLocation(getClass().getClassLoader().getResource("fxml/login.fxml"));
                loader.load();
                Parent parent = loader.getRoot();
                Stage primaryStage = new Stage();
                Main.setMainStage(primaryStage);
                primaryStage.initStyle(StageStyle.DECORATED);
                primaryStage.resizableProperty().setValue(Boolean.FALSE);
                primaryStage.setTitle("Wishes Reminder");
                primaryStage.getIcons().add(new Image("/image/icon.png"));
                primaryStage.setScene(new Scene(parent, 1184, 585));

                Stage stage = (Stage) textFieldCity.getScene().getWindow();
                stage.hide();
                primaryStage.show();
            } catch (IOException ioEcx) {
                Logger.getLogger(WelcomeBannerController.class.getName()).log(Level.SEVERE, null, ioEcx);
            }
        } else {
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
                primaryStage.setScene(new Scene(parent, 1601, 900));
                Stage stage = (Stage) textFieldCity.getScene().getWindow();
                stage.hide();
                primaryStage.show();
            } catch (IOException ioEcx) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ioEcx);
            }
        }
    }

    @FXML
    void buttonSetCurrentData_onAction() {
        // TODO: Wywołanie metody wypełniającej textFields i passwordFields danymi użytkownika.
    }

    private void fillFieldsByUserData() {
        // textFieldName.setText(loggedUser.getFirstName);
        // ...
    }

    @SuppressWarnings("Duplicates")
    private void changePasswordFieldText() {
        if (passwordFieldPassword.getText().isEmpty())
            labelPassword.setText("Podaj hasło.");
        else if (!passwordFieldPassword.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])" +
                "(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$"))
            labelPassword.setText("Niepoprawny format.");
        else if (labelConfirmPassword.getText().isEmpty() && !(passwordFieldConfirmPassword
                .getText().equals(passwordFieldPassword.getText())))
            labelConfirmPassword.setText("Hasła nie zgadzają się.");
        else if (labelConfirmPassword.getText().equals("Hasła nie zgadzają się.")
                && (passwordFieldConfirmPassword.getText().equals(passwordFieldPassword.getText())))
            labelConfirmPassword.setText("");
        else
            labelPassword.setText("");
    }

    @SuppressWarnings("Duplicates")
    private void changeConfirmPasswordFieldText() {
        if (passwordFieldConfirmPassword.getText().isEmpty())
            labelConfirmPassword.setText("Ponownie wprowadź hasło.");
        else if (!passwordFieldConfirmPassword.getText().matches("^(?=.*[0-9])" +
                "(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$"))
            labelConfirmPassword.setText("Niepoprawny format.");
        else if (labelPassword.getText().isEmpty() && !(passwordFieldConfirmPassword
                .getText().equals(passwordFieldPassword.getText())))
            labelConfirmPassword.setText("Hasła nie zgadzają się.");
        else if (labelConfirmPassword.getText().equals("Hasła nie zgadzają się.")
                && (passwordFieldConfirmPassword.getText().equals(passwordFieldPassword.getText())))
            labelConfirmPassword.setText("");
        else
            labelConfirmPassword.setText("");
    }
}
