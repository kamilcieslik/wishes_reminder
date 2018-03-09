package pl.escience.zdpp.lab03gr1.javafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Label labelHeader, labelUserNameAndSurname, labelUserLogin, labelDetailsNameAndSurname,
            labelDetailsRelation, labelDetailsAnniversaryKind, labelDetailsAnniversaryDate,
            labelDetailsPriceWithCurrency, labelDetailsNumberOfSentWiches, labelDetailsEmail,
            labelDetailsStreet, labelDetailsPostalCode, labelDetailsCity, labelDetailsCountry,
            labelSelectedAlreadySentWishKind, labelAlreadySentWishesNameAndSurname, labelSelectedAlreadySentWishSentBy,
            labelNewWishKind, labelNewWishNameAndSurname;

    @FXML
    private TableView<?> tableViewPersonAnniversary;

    @FXML
    private TableColumn<?, String> tableColumnPersonAnniversaryName, tableColumnPersonAnniversarySurname,
            tableColumnPersonAnniversaryRelation, tableColumnPersonAnniversaryAnniversaryKind,
            tableColumnPersonAnniversaryAnniversaryDate;

    @FXML
    private TableColumn<?, Integer> tableColumnPersonAnniversaryNumberOfDays;

    @FXML
    private HBox hBoxModifyAnaDeleteSelectedPersonAnniversary;

    @FXML
    private RadioButton radioButtonSendWishes, radioButtonAlreadySentWishes, radioButtonDetails;

    @FXML
    private VBox vBoxDetailsMode, vBoxAlreadySentWishesMode, vBoxNewWishMode, vBoxEmailSubject;

    @FXML
    private TableView<?> tableViewAlreadySentWishes;

    @FXML
    private TableColumn<?, Date> tableColumnAlreadySentWishesPostDate;

    @FXML
    private TableColumn<?, String> tableColumnAlreadySentWishesText;

    @FXML
    private TextArea textAreaSelectedAlreadySentWishText, textFieldNewWishText;

    @FXML
    private TableView<?> tableViewNewWishWishTemplates;

    @FXML
    private TableColumn<?, String> tableColumnNewWishWishTemplatesText;

    @FXML
    private CheckBox checkBoxNewWishSentByEmail, checkBoxNewWishSentByList;

    @FXML
    private TextField textFieldNewWishEmailSubject;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void buttonAddPersonAnniversary_onAction() {

    }

    @FXML
    void buttonDeletePersonAnniversary_onAction() {

    }

    @FXML
    void buttonModifyPersonAnniversary_onAction() {

    }

    @FXML
    void menuItemModifyUserProfile_onAction() {

    }

    @FXML
    void menuItemModifyWishTemplates_onAction() {

    }

    @FXML
    void radioButtonAlreadySentWishes_onAction() {

    }

    @FXML
    void radioButtonDetails_onAction() {

    }

    @FXML
    void radioButtonSendWishes_onAction() {

    }

    @FXML
    void tableViewAnswerCards_onMouseClicked() {

    }

    @FXML
    void tableViewTestTemplates_onMouseClicked() {

    }
}
