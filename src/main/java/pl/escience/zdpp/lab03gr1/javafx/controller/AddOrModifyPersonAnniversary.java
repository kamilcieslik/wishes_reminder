package pl.escience.zdpp.lab03gr1.javafx.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pl.escience.zdpp.lab03gr1.app.WishesReminder;
import pl.escience.zdpp.lab03gr1.database.entity.Relation;
import pl.escience.zdpp.lab03gr1.javafx.CustomMessageBox;
import pl.escience.zdpp.lab03gr1.javafx.ListenerMethods;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddOrModifyPersonAnniversary implements Initializable {
    private ObservableList<Relation> relationObservableList;
    private CustomMessageBox customMessageBox;

    @FXML
    private Label labelEnterPrimaryData, labelRelation, labelSurname, labelName, labelEmail, labelEnterAddress,
            labelStreet, labelPostalCode, labelCity, labelCountry, labelAnniversaryDate;
    @FXML
    private ComboBox<Relation> comboBoxRelation;
    @FXML
    private TextField textFieldName, textFieldSurname, textFieldEmail, textFieldStreet, textFieldPostalCode,
            textFieldCountry, textFieldCity;
    @FXML
    private RadioButton radioButtonRelationNameday, radioButtonRelationBirthday;
    @FXML
    private Button buttonAdd;
    @FXML
    private HBox hBoxSetCurrentData;
    @FXML
    private DatePicker datePickerAnniversaryDate;

    public void initPersonAnniversaryData() {
        buttonAdd.setText("Modyfikuj");
        labelEnterPrimaryData.setText("Modyfikuj podstawowe dane wydarzenia");
        labelEnterAddress.setText("Modyfikuj dane adresowe osoby, której dotyczy wydarzenie (opcjonalne)");

        hBoxSetCurrentData.setVisible(true);
        hBoxSetCurrentData.setDisable(false);
        hBoxSetCurrentData.setMinWidth(Control.USE_COMPUTED_SIZE);
        hBoxSetCurrentData.setMinHeight(Control.USE_COMPUTED_SIZE);
        hBoxSetCurrentData.setPrefHeight(Control.USE_COMPUTED_SIZE);
        hBoxSetCurrentData.setPrefWidth(Control.USE_COMPUTED_SIZE);
        hBoxSetCurrentData.setMaxHeight(Control.USE_COMPUTED_SIZE);
        hBoxSetCurrentData.setMaxWidth(Control.USE_COMPUTED_SIZE);

        //TODO: Wywołanie metody wypełniającej textFields i passwordFields danymi wydarzenia.
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        relationObservableList = WishesReminder.getRelationObservableList();
        comboBoxRelation.setItems(relationObservableList);

        customMessageBox = new CustomMessageBox("image/icon.png");
        initRadioButtons();

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

        comboBoxRelation.valueProperty().addListener((observable, oldValue, newValue) -> listenerMethods
                .changeLabelComboBox(comboBoxRelation, labelRelation,
                        "Wybierz relację."));

        datePickerAnniversaryDate.getEditor().textProperty().addListener((observable, oldValue, newValue) -> listenerMethods
                .changeLabelDatePicker(datePickerAnniversaryDate, labelAnniversaryDate,
                        "Podaj dzień i miesiąc wydarzenia."));
    }

    @FXML
    void buttonAdd_onAction() {
        String name = labelName.getText();
        String surname = labelSurname.getText();
        String email = labelEmail.getText();
        String date = labelAnniversaryDate.getText();
        String relation = labelRelation.getText();

        String street = labelStreet.getText();
        String postalCode = labelPostalCode.getText();
        String city = labelCity.getText();
        String country = labelCountry.getText();

        // String relation = comboBoxRelation.getSelectionModel().getSelectedItem();
        // TODO: Uwzględnić relation w konstrukcjach warunkowych.
        if (buttonAdd.getText().equals("Dodaj")) {
            if (date.isEmpty() && relation.isEmpty() && name.isEmpty() && surname.isEmpty() && email.isEmpty() &&
                    street.isEmpty() && postalCode.isEmpty() && city.isEmpty() && country.isEmpty()) {
                // TODO: Utworzenie wydarzenia wraz z adresem. Przejście do głównej sceny - odświeżenie widoku wydarzeń.
            } else if (date.isEmpty() && relation.isEmpty() && name.isEmpty() && surname.isEmpty() && email.isEmpty()
                    && street.equals("Podaj ulicę i nr domu/mieszkania.") && postalCode.equals("Podaj kod pocztowy.")
                    && city.equals("Podaj miasto.") && country.equals("Podaj kraj.")) {
                // TODO: Utworzenie wydarzenia bez adresu. Przejście do głównej sceny - odświeżenie widoku wydarzeń.
            } else
                customMessageBox.showMessageBox(Alert.AlertType.WARNING, "Ostrzeżenie",
                        "Operacja utworzenia wydarzenia nie powiedzie się.",
                        "Powód: Nie wszystkie wartości mają poprawny format.")
                        .showAndWait();
        } else {
            if (date.isEmpty() && relation.isEmpty() && name.isEmpty() && surname.isEmpty() && email.isEmpty() &&
                    street.isEmpty() && postalCode.isEmpty() && city.isEmpty() && country.isEmpty()) {
                // TODO: Modyfikacja wydarzenia wraz z adresem. Jeżeli adres nie istnieje - utworzenie adresu.
                // TODO: Przejście do głównej sceny - odświeżenie widoku wydarzeń.
            } else if (date.isEmpty() && relation.isEmpty() && name.isEmpty() && surname.isEmpty() && email.isEmpty()
                    && street.equals("Podaj ulicę i nr domu/mieszkania.") && postalCode.equals("Podaj kod pocztowy.")) {
                // TODO: Modyfikacja wydarzenia wraz z adresem. Jeżeli adres istnieje - usunięcie adresu.
                // TODO: Przejście do głównej sceny - odświeżenie widoku wydarzeń.
            } else
                customMessageBox.showMessageBox(Alert.AlertType.WARNING, "Ostrzeżenie",
                        "Operacja modyfikacji wydarzenia nie powiedzie się.",
                        "Powód: Nie wszystkie wartości mają poprawny format.")
                        .showAndWait();
        }
    }

    @FXML
    void buttonCancel_onAction() {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getClassLoader().getResource("fxml/main.fxml"));
            loader.load();
            Parent parent = loader.getRoot();
            Stage primaryStage = new Stage();
            WishesReminder.setMainStage(primaryStage);
            primaryStage.setTitle("Wishes Reminder");
            primaryStage.getIcons().add(new Image("/image/icon.png"));
            primaryStage.setMinWidth(950);
            primaryStage.setMinHeight(890);
            primaryStage.setScene(new Scene(parent, 1598, 900));
            Stage stage = (Stage) textFieldCity.getScene().getWindow();
            stage.hide();
            primaryStage.show();
        } catch (IOException ioEcx) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ioEcx);
        }
    }

    @FXML
    void buttonSetCurrentData_onAction() {

    }

    private void initRadioButtons() {
        ToggleGroup toggleGroupGameModes = new ToggleGroup();
        radioButtonRelationBirthday.setToggleGroup(toggleGroupGameModes);
        radioButtonRelationNameday.setToggleGroup(toggleGroupGameModes);

        radioButtonRelationBirthday.setSelected(true);
    }
}
