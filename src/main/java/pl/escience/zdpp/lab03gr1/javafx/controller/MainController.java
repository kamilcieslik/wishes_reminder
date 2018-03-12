package pl.escience.zdpp.lab03gr1.javafx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import pl.escience.zdpp.lab03gr1.app.WishesReminder;
import pl.escience.zdpp.lab03gr1.database.entity.User;
import pl.escience.zdpp.lab03gr1.database.service.ReminderService;
import pl.escience.zdpp.lab03gr1.database.view.ViewExtendedPersonAnniversary;
import pl.escience.zdpp.lab03gr1.javafx.CustomMessageBox;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController implements Initializable {
    private ReminderService reminderService;
    private User loggedUser;
    private CustomMessageBox customMessageBox;

    private List<ViewExtendedPersonAnniversary> viewExtendedPersonAnniversaries;
    private ObservableList<ViewExtendedPersonAnniversary> viewExtendedPersonAnniversaryObservableList
            = FXCollections.observableArrayList();

    @FXML
    private Label labelHeader, labelUserNameAndSurname, labelUserLogin, labelDetailsNameAndSurname,
            labelDetailsRelation, labelDetailsAnniversaryKind, labelDetailsAnniversaryDate,
            labelDetailsPriceWithCurrency, labelDetailsNumberOfSentWiches, labelDetailsEmail,
            labelDetailsStreet, labelDetailsPostalCode, labelDetailsCity, labelDetailsCountry,
            labelSelectedAlreadySentWishKind, labelAlreadySentWishesNameAndSurname, labelSelectedAlreadySentWishSentBy,
            labelNewWishKind, labelNewWishNameAndSurname;
    @FXML
    private TableView<ViewExtendedPersonAnniversary> tableViewPersonAnniversary;
    @FXML
    private TableColumn<ViewExtendedPersonAnniversary, String> tableColumnPersonAnniversaryName,
            tableColumnPersonAnniversarySurname, tableColumnPersonAnniversaryRelation,
            tableColumnPersonAnniversaryAnniversaryKind;
    @FXML
    private TableColumn<ViewExtendedPersonAnniversary, Date> tableColumnPersonAnniversaryNextAnniversaryDate;
    @FXML
    private TableColumn<ViewExtendedPersonAnniversary, Integer> tableColumnPersonAnniversaryNumberOfDays;
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
        reminderService = WishesReminder.getReminderService();
        loggedUser = WishesReminder.getLoggedUser();

        customMessageBox = new CustomMessageBox("image/icon.png");
        initRadioButtons();
        clearModesComponents();
        initUserData();
        fillEventsTable();
    }

    @FXML
    void buttonSendNewWish_onAction() {
        if(checkBoxNewWishSentByEmail.isSelected()){
            Email email = new SimpleEmail();
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator("wishesreminder@onet.pl",
                    "Mojehaslo123$"));
            email.setDebug(false);
            email.setHostName("smtp.poczta.onet.pl");
            try {
                email.setFrom("wishesreminder@onet.pl");
                email.setSubject(textFieldNewWishEmailSubject.getText());
                email.setMsg(textAreaNewWishText.getText());
                email.addTo("patrykz8@o2.pl");
                email.setStartTLSEnabled(true);
                email.send();
                System.out.println("SENT");
            } catch (EmailException e) {
                e.printStackTrace();
            }

        }


    }

    @FXML
    void buttonAddPersonAnniversary_onAction() {
        // TODO:

        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getClassLoader().getResource("fxml/add_or_modify_person_anniversary.fxml"));
            loader.load();
            Parent parent = loader.getRoot();
            Stage primaryStage = new Stage();
            WishesReminder.setMainStage(primaryStage);
            primaryStage.setTitle("Wishes Reminder");
            primaryStage.getIcons().add(new Image("/image/icon.png"));
            primaryStage.setMinWidth(1100);
            primaryStage.setMinHeight(765);
            primaryStage.setScene(new Scene(parent, 1200, 780));
            Stage stage = (Stage) textAreaNewWishText.getScene().getWindow();
            stage.hide();
            primaryStage.show();
        } catch (IOException ioEcx) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ioEcx);
        }
    }

    @FXML
    void buttonDeletePersonAnniversary_onAction() {

    }

    @FXML
    void buttonModifyPersonAnniversary_onAction() {

        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getClassLoader().getResource("fxml/add_or_modify_person_anniversary.fxml"));
            loader.load();
            AddOrModifyPersonAnniversary controller = loader.getController();
            controller.initPersonAnniversaryData();
            Parent parent = loader.getRoot();
            Stage primaryStage = new Stage();
            WishesReminder.setMainStage(primaryStage);
            primaryStage.setTitle("Wishes Reminder");
            primaryStage.getIcons().add(new Image("/image/icon.png"));
            primaryStage.setMinWidth(1100);
            primaryStage.setMinHeight(765);
            primaryStage.setScene(new Scene(parent, 1200, 780));
            Stage stage = (Stage) textAreaNewWishText.getScene().getWindow();
            stage.hide();
            primaryStage.show();
        } catch (IOException ioEcx) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ioEcx);
        }
    }

    @FXML
    void menuItemModifyUserProfile_onAction() {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getClassLoader().getResource("fxml/register_or_modify_account.fxml"));
            loader.load();
            RegisterOrModifyAccountController controller = loader.getController();
            controller.initUserData();
            Parent parent = loader.getRoot();
            Stage primaryStage = new Stage();
            WishesReminder.setMainStage(primaryStage);
            primaryStage.setTitle("Wishes Reminder");
            primaryStage.getIcons().add(new Image("/image/icon.png"));
            primaryStage.setMinWidth(1100);
            primaryStage.setMinHeight(765);
            primaryStage.setScene(new Scene(parent, 1200, 780));
            Stage stage = (Stage) textAreaNewWishText.getScene().getWindow();
            stage.hide();
            primaryStage.show();
        } catch (IOException ioEcx) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ioEcx);
        }
    }

    @FXML
    void menuItemModifyWishTemplates_onAction() {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getClassLoader().getResource("fxml/wish_templates.fxml"));
            loader.load();
            Parent parent = loader.getRoot();
            Stage primaryStage = new Stage();
            WishesReminder.setMainStage(primaryStage);
            primaryStage.setTitle("Wishes Reminder");
            primaryStage.getIcons().add(new Image("/image/icon.png"));
            primaryStage.setMinWidth(1100);
            primaryStage.setMinHeight(765);
            primaryStage.setScene(new Scene(parent, 1600, 900));
            Stage stage = (Stage) textAreaNewWishText.getScene().getWindow();
            stage.hide();
            primaryStage.show();
        } catch (IOException ioEcx) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ioEcx);
        }
    }

    @FXML
    void menuItemLogout_onAction() {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getClassLoader().getResource("fxml/login.fxml"));
            loader.load();
            Parent parent = loader.getRoot();
            Stage primaryStage = new Stage();
            WishesReminder.setMainStage(primaryStage);
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.resizableProperty().setValue(Boolean.FALSE);
            primaryStage.setTitle("Wishes Reminder");
            primaryStage.getIcons().add(new Image("/image/icon.png"));
            primaryStage.setScene(new Scene(parent, 1186, 585));
            Stage stage = (Stage) textAreaNewWishText.getScene().getWindow();
            stage.hide();
            primaryStage.show();
        } catch (IOException ioEcx) {
            Logger.getLogger(WelcomeBannerController.class.getName()).log(Level.SEVERE, null, ioEcx);
        }
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

    private void initUserData() {
        labelUserLogin.setText(loggedUser.getLogin());
        labelUserNameAndSurname.setText(loggedUser.getFirstName() + " " + loggedUser.getLastName());

        // TODO: Wypełnienie tablicy wydarzeń (personAnniversaries).
        // TODO: bservableList.addAll(personAnniversariesExtendedView.getEntitiesByUserId(loggedUser.getId()));
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
        tableColumnPersonAnniversaryName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableColumnPersonAnniversarySurname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableColumnPersonAnniversaryRelation.setCellValueFactory(new PropertyValueFactory<>("relationName"));
        tableColumnPersonAnniversaryAnniversaryKind.setCellValueFactory(new PropertyValueFactory<>("anniversaryKind"));

        tableColumnPersonAnniversaryNextAnniversaryDate
                .setCellValueFactory(new PropertyValueFactory<>("nextAnniversaryDate"));
        tableColumnPersonAnniversaryNextAnniversaryDate.setCellFactory(col -> localDateFormat());
        tableColumnPersonAnniversaryNumberOfDays
                .setCellValueFactory(new PropertyValueFactory<>("numberOfDaysToNextAnniversary"));
    }

    private void fillEventsTable() {
        viewExtendedPersonAnniversaries = reminderService.getViewExtendedContactsByUserId(loggedUser.getId());
        viewExtendedPersonAnniversaries.forEach(ViewExtendedPersonAnniversary::calculateNextAnniversaryFields);
        initTableViews();
        viewExtendedPersonAnniversaryObservableList.addAll(viewExtendedPersonAnniversaries);
        tableViewPersonAnniversary.setItems(viewExtendedPersonAnniversaryObservableList);
    }

    private TableCell<ViewExtendedPersonAnniversary, Date> localDateFormat() {
        return new TableCell<ViewExtendedPersonAnniversary, Date>() {
            @Override
            public void updateItem(Date date, boolean empty) {
                super.updateItem(date, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(new SimpleDateFormat("dd-MM-yyyy").format(date));
                }
            }
        };
    }
}
