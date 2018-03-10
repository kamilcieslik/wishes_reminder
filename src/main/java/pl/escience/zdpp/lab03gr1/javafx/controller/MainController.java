package pl.escience.zdpp.lab03gr1.javafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.escience.zdpp.lab03gr1.javafx.CustomMessageBox;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private CustomMessageBox customMessageBox;

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
    private RadioButton radioButtonNewWish, radioButtonAlreadySentWishes, radioButtonDetails;
    @FXML
    private VBox vBoxDetailsMode, vBoxAlreadySentWishesMode, vBoxNewWishMode, vBoxEmailSubject;
    @FXML
    private TableView<?> tableViewAlreadySentWishes;
    @FXML
    private TableColumn<?, Date> tableColumnAlreadySentWishesPostDate;
    @FXML
    private TableColumn<?, String> tableColumnAlreadySentWishesText;
    @FXML
    private TextArea textAreaSelectedAlreadySentWishText, textAreaNewWishText;
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
        customMessageBox = new CustomMessageBox("image/icon.png");
        initRadioButtons();
        clearModesComponents();
        initTableViews();
    }

    @FXML
    void buttonSendNewWish_onAction() {

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
        preparePersonAnniversaryModeComponents("already_sent_wishes");
    }

    @FXML
    void radioButtonDetails_onAction() {
        preparePersonAnniversaryModeComponents("details");
    }

    @FXML
    void radioButtonNewWish_onAction() {
        preparePersonAnniversaryModeComponents("new_wish");
    }

    @FXML
    void checkBoxNewWishSentByEmail_onAction() {
        if (checkBoxNewWishSentByEmail.isSelected())
            setVBoxVisible(vBoxEmailSubject, true);
        else
            setVBoxVisible(vBoxEmailSubject, false);
    }

    @FXML
    void tableViewPersonAnniversary_onMouseClicked() {
        // ExtendedPersonAnniversaryView extendedPersonAnniversaryView = tableViewPersonAnniversary.getSelectionModel()
        // .getSelectedItem();
        // if (extendedPersonAnniversaryView != null) {
        // clearModesComponents();
        // setHBoxVisible(hBoxModifyAnaDeleteSelectedPersonAnniversary, true);
        // TODO: Uzupełnienie wszysktich labels i wypełnienie tableViews. Zablokować możliwość kliknięcia
        // TODO: checkBox'a sentByList w przypadku braku danych adresowych nadawcy lub odbiorcy.
        // }
    }

    @FXML
    void tableViewNewWishWishTemplates_onMouseClicked() {
        // WishTemplate wishTemplate = tableViewNewWishWishTemplates.getSelectionModel()
        // .getSelectedItem();
        // if (wishTemplate != null) {
        // TODO: Przepisanie treści wiadomości szablonowej do textArea nowej wiadomości.
        // }
    }

    @FXML
    void tableViewAlreadySentWishes_onMouseClicked() {
        // Wish wish = tableViewAlreadySentWishes.getSelectionModel()
        // .getSelectedItem();
        // if (wish != null) {
        // TODO: Wypełnienie odpowiednich labels i textArea informacjami o wybranym życzeniu.
        // }
    }

    private void initRadioButtons() {
        ToggleGroup toggleGroupGameModes = new ToggleGroup();
        radioButtonNewWish.setToggleGroup(toggleGroupGameModes);
        radioButtonAlreadySentWishes.setToggleGroup(toggleGroupGameModes);
        radioButtonDetails.setToggleGroup(toggleGroupGameModes);

        preparePersonAnniversaryModeComponents("new_wish");
        radioButtonNewWish.setSelected(true);
    }

    private void setVBoxVisible(VBox vBox, Boolean visible) {
        if (visible) {
            vBox.setVisible(true);
            vBox.setDisable(false);
            vBox.setMinWidth(Control.USE_COMPUTED_SIZE);
            vBox.setMinHeight(Control.USE_COMPUTED_SIZE);
            vBox.setPrefWidth(Control.USE_COMPUTED_SIZE);
            vBox.setPrefHeight(Control.USE_COMPUTED_SIZE);
            vBox.setMaxHeight(Control.USE_COMPUTED_SIZE);
            vBox.setMaxWidth(Control.USE_COMPUTED_SIZE);
        } else {
            vBox.setVisible(false);
            vBox.setDisable(true);
            vBox.setMinWidth(0);
            vBox.setPrefWidth(0);
            vBox.setMinHeight(0);
            vBox.setPrefHeight(0);
            vBox.setMaxWidth(0);
            vBox.setMaxHeight(0);
        }
    }

    private void setHBoxVisible(HBox hBox, Boolean visible) {
        if (visible) {
            hBox.setVisible(true);
            hBox.setDisable(false);
            hBox.setMinWidth(Control.USE_COMPUTED_SIZE);
            hBox.setMinHeight(Control.USE_COMPUTED_SIZE);
            hBox.setPrefHeight(Control.USE_COMPUTED_SIZE);
            hBox.setPrefWidth(Control.USE_COMPUTED_SIZE);
            hBox.setMaxHeight(Control.USE_COMPUTED_SIZE);
            hBox.setMaxWidth(Control.USE_COMPUTED_SIZE);
        } else {
            hBox.setVisible(false);
            hBox.setDisable(true);
            hBox.setMinWidth(0);
            hBox.setMinHeight(0);
            hBox.setPrefWidth(0);
            hBox.setPrefHeight(0);
            hBox.setMaxWidth(0);
            hBox.setMaxHeight(0);
        }
    }

    private void preparePersonAnniversaryModeComponents(String mode) {
        switch (mode) {
            case "new_wish":
                setVBoxVisible(vBoxNewWishMode, true);
                setVBoxVisible(vBoxAlreadySentWishesMode, false);
                setVBoxVisible(vBoxDetailsMode, false);
                break;
            case "already_sent_wishes":
                setVBoxVisible(vBoxNewWishMode, false);
                setVBoxVisible(vBoxAlreadySentWishesMode, true);
                setVBoxVisible(vBoxDetailsMode, false);
                break;
            case "details":
                setVBoxVisible(vBoxNewWishMode, false);
                setVBoxVisible(vBoxAlreadySentWishesMode, false);
                setVBoxVisible(vBoxDetailsMode, true);
                break;
        }
    }

    private void clearDetailsModeComponents() {
        labelDetailsNameAndSurname.setText("------");
        labelDetailsRelation.setText("------");
        labelDetailsAnniversaryKind.setText("------");
        labelDetailsAnniversaryDate.setText("------");
        labelDetailsNumberOfSentWiches.setText("------");
        labelDetailsEmail.setText("------");
        labelDetailsStreet.setText("------");
        labelDetailsPostalCode.setText("------");
        labelDetailsCity.setText("------");
        labelDetailsCountry.setText("------");
    }

    private void clearAlreadySentWishesModeComponents() {
        labelAlreadySentWishesNameAndSurname.setText("------");
        labelSelectedAlreadySentWishKind.setText("------");
        labelSelectedAlreadySentWishSentBy.setText("------");
        textAreaSelectedAlreadySentWishText.setText("");

        //TODO: ObservableList alreadySentWishes.clear();
    }

    private void clearNewWishModeComponents() {
        labelNewWishNameAndSurname.setText("------");
        labelNewWishKind.setText("------");

        checkBoxNewWishSentByEmail.setSelected(false);
        checkBoxNewWishSentByList.setSelected(false);

        textAreaNewWishText.setText("");
        setVBoxVisible(vBoxEmailSubject, false);

        //TODO: ObservableList wishTemplates.clear();
    }

    private void clearModesComponents() {
        clearAlreadySentWishesModeComponents();
        clearNewWishModeComponents();
        clearDetailsModeComponents();
        setHBoxVisible(hBoxModifyAnaDeleteSelectedPersonAnniversary, false);
    }

    private void initTableViews() {
        //TODO:
    }
}
