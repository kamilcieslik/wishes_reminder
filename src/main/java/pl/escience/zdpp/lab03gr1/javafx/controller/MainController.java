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
import pl.escience.zdpp.lab03gr1.database.entity.SentWish;
import pl.escience.zdpp.lab03gr1.database.entity.User;
<<<<<<< HEAD
import pl.escience.zdpp.lab03gr1.database.exception.UniqueViolationException;
=======
import pl.escience.zdpp.lab03gr1.database.entity.WishTemplate;
>>>>>>> 4e5f4d41cbba8a702267ae693fc3ebb9a059483f
import pl.escience.zdpp.lab03gr1.database.service.ReminderService;
import pl.escience.zdpp.lab03gr1.database.view.ViewExtendedPersonAnniversary;
import pl.escience.zdpp.lab03gr1.javafx.CustomMessageBox;
import pl.escience.zdpp.lab03gr1.javafx.ListenerMethods;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController implements Initializable {
    private ReminderService reminderService;
    private User loggedUser;
    private CustomMessageBox customMessageBox;

    private List<ViewExtendedPersonAnniversary> viewExtendedPersonAnniversaries;
<<<<<<< HEAD
    private ObservableList<ViewExtendedPersonAnniversary> viewExtendedPersonAnniversaryObservableList = FXCollections.observableArrayList();
=======
    private ObservableList<ViewExtendedPersonAnniversary> viewExtendedPersonAnniversaryObservableList
            = FXCollections.observableArrayList();
    private List<SentWish> sentWishes;
    private ObservableList<SentWish> sentWishObservableList=FXCollections.observableArrayList();
    private List<WishTemplate> wishTemplates;
    private ObservableList<WishTemplate> wishTemplateObservableList=FXCollections.observableArrayList();
>>>>>>> 4e5f4d41cbba8a702267ae693fc3ebb9a059483f

    @FXML
    private Label labelHeader, labelUserNameAndSurname, labelUserLogin, labelDetailsNameAndSurname, labelDetailsRelation, labelDetailsAnniversaryKind, labelDetailsAnniversaryDate, labelDetailsPriceWithCurrency, labelDetailsNumberOfSentWiches, labelDetailsEmail, labelDetailsStreet, labelDetailsPostalCode, labelDetailsCity, labelDetailsCountry, labelSelectedAlreadySentWishKind, labelAlreadySentWishesNameAndSurname, labelSelectedAlreadySentWishSentBy, labelNewWishKind, labelNewWishNameAndSurname;
    @FXML
    private TableView<ViewExtendedPersonAnniversary> tableViewPersonAnniversary;
    @FXML
    private TableColumn<ViewExtendedPersonAnniversary, String> tableColumnPersonAnniversaryName, tableColumnPersonAnniversarySurname, tableColumnPersonAnniversaryRelation, tableColumnPersonAnniversaryAnniversaryKind;
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
    private TableView<SentWish> tableViewAlreadySentWishes;
    @FXML
    private TableColumn<SentWish, Date> tableColumnAlreadySentWishesPostDate;
    @FXML
    private TableColumn<SentWish, String> tableColumnAlreadySentWishesText;
    @FXML
    private TextArea textAreaSelectedAlreadySentWishText, textAreaNewWishText;
    @FXML
    private TableView<WishTemplate> tableViewNewWishWishTemplates;
    @FXML
    private TableColumn<WishTemplate, String> tableColumnNewWishWishTemplatesText;
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
        initCheckBoxes();
        clearModesComponents();
        initUserData();
        fillEventsTable();
    }

    @FXML
    void buttonSendNewWish_onAction() {
        if (tableViewPersonAnniversary.getSelectionModel().getSelectedItem() != null) {
            if (checkBoxNewWishSentByList.isSelected() || checkBoxNewWishSentByEmail.isSelected()) {
                if (!textAreaNewWishText.getText().equals("")){
                    if (checkBoxNewWishSentByEmail.isSelected()){
                        Email email = new SimpleEmail();
                        email.setSmtpPort(587);
                        email.setAuthenticator(new DefaultAuthenticator("pwr.wishesreminder@gmail.com", "Mojehaslo123$"));
                        email.setDebug(false);
                        email.setHostName("smtp.gmail.com");
                        try {
                            email.setFrom("pwr.wishesreminder@gmail.com");
                            email.setSubject(textFieldNewWishEmailSubject.getText());
                            email.setMsg(textAreaNewWishText.getText()+"\n"+"\n"+"\n"+"Email został" +
                                    " wysłany przy pomocy aplikacji Wishes Reminder przez:"+"\n"+loggedUser.getFirstName()
                                    +" "+loggedUser.getLastName()+
                            "\n"+"Adres e-mail: "+ loggedUser.getEmail());
                            email.addTo("patrykz13@gmail.com");
                            email.setStartTLSEnabled(true);
                            email.send();
                        } catch (EmailException e) {
                            e.printStackTrace();
                        }
                    }
                    SentWish sentWish = new SentWish(textAreaNewWishText.getText(), new Date(),checkBoxNewWishSentByList.isSelected(),
                            checkBoxNewWishSentByEmail.isSelected());
                    sentWish.setPersonAnniversary(reminderService.getPersonAnniversary(tableViewPersonAnniversary.getSelectionModel().getSelectedItem().getPersonAnniversaryId()));
                    reminderService.saveSentWish(sentWish);
                    customMessageBox.showMessageBox(Alert.AlertType.CONFIRMATION, "Powodzenie",
                            "Operacja wysłania życzeń  powiodła się.",
                            "Rekord został zaktualizowany").showAndWait();


                }
                else
                    customMessageBox.showMessageBox(Alert.AlertType.WARNING, "Ostrzeżenie",
                            "Operacja wysłania życzeń nie powiedzie się.",
                            "Powód: nie można wysłać pustych życzeń.").showAndWait();
            } else
                customMessageBox.showMessageBox(Alert.AlertType.WARNING, "Ostrzeżenie",
                        "Operacja wysłania życzeń nie powiedzie się.",
                        "Powód: nie wybrano sposobu nadania.").showAndWait();
        } else
            customMessageBox.showMessageBox(Alert.AlertType.WARNING, "Ostrzeżenie",
                    "Operacja wysłania życzeń nie powiedzie się.",
                    "Powód: nie zaznaczono wydarzenia.").showAndWait();






    }

    @FXML
    void buttonAddPersonAnniversary_onAction() {

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
        if (checkBoxNewWishSentByEmail.isSelected()) setVBoxVisible(vBoxEmailSubject, true);
        else setVBoxVisible(vBoxEmailSubject, false);
    }

    @FXML
    void tableViewPersonAnniversary_onMouseClicked() {

        clearModesComponents();
        fillNewWishTemplatesTable();
        initCheckBoxes();
        fillAlreadySentWishesTable();
        fillDetailsModeComponents();
    }

    @FXML
    void tableViewNewWishWishTemplates_onMouseClicked() {
        fillDetailsNewWishTemplatesComponents();
    }

    @FXML
    void tableViewAlreadySentWishes_onMouseClicked() {
        fillDetailsSentWishesComponents();
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
    }

    private void initCheckBoxes(){
        if (loggedUser.getAddress()==null) {
            checkBoxNewWishSentByList.setDisable(true);
            return;
        }

        ViewExtendedPersonAnniversary v = tableViewPersonAnniversary.getSelectionModel()
                .getSelectedItem();
        if(v==null)
            return;
        else{
            if(v.getAddressId()==null)
                checkBoxNewWishSentByList.setDisable(true);
            else
                checkBoxNewWishSentByList.setDisable(false);
        }
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
        sentWishObservableList.clear();
    }

    private void clearNewWishModeComponents() {
        labelNewWishNameAndSurname.setText("------");
        labelNewWishKind.setText("------");

        checkBoxNewWishSentByEmail.setSelected(false);
        checkBoxNewWishSentByList.setSelected(false);

        textAreaNewWishText.setText("");
        setVBoxVisible(vBoxEmailSubject, false);
        wishTemplateObservableList.clear();
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

        tableColumnPersonAnniversaryNextAnniversaryDate.setCellValueFactory(new PropertyValueFactory<>("nextAnniversaryDate"));
        tableColumnPersonAnniversaryNextAnniversaryDate.setCellFactory(col -> localDateFormat());
        tableColumnPersonAnniversaryNumberOfDays.setCellValueFactory(new PropertyValueFactory<>("numberOfDaysToNextAnniversary"));
    }

    private void fillEventsTable() {
        viewExtendedPersonAnniversaries = reminderService.getViewExtendedContactsByUserId(loggedUser.getId());
        viewExtendedPersonAnniversaries.forEach(ViewExtendedPersonAnniversary::calculateNextAnniversaryFields);
        initTableViews();
        viewExtendedPersonAnniversaryObservableList.addAll(viewExtendedPersonAnniversaries);
        tableViewPersonAnniversary.setItems(viewExtendedPersonAnniversaryObservableList);
    }

    private void initTableOfSentWishes(){
        tableColumnAlreadySentWishesPostDate.setCellValueFactory(new PropertyValueFactory<>("postDate"));
        tableColumnAlreadySentWishesPostDate.setCellFactory(col->localSentWishDateFormat());
        tableColumnAlreadySentWishesText.setCellValueFactory(new PropertyValueFactory<>("text"));

    }

    private void fillNewWishTemplatesTable(){
        wishTemplates=reminderService.getWishesTemplates();
        tableColumnNewWishWishTemplatesText.setCellValueFactory(new PropertyValueFactory<>("text"));
        for(WishTemplate w: wishTemplates){
            if(w.getUser().getId()==loggedUser.getId())
                wishTemplateObservableList.add(w);
        }
        tableViewNewWishWishTemplates.setItems(wishTemplateObservableList);
    }

    private void fillDetailsNewWishTemplatesComponents(){
        ViewExtendedPersonAnniversary v = tableViewPersonAnniversary.getSelectionModel()
                .getSelectedItem();
        WishTemplate wishTemplate=tableViewNewWishWishTemplates.getSelectionModel().getSelectedItem();
        labelNewWishKind.setText(v.getAnniversaryKind());
        textAreaNewWishText.setText(wishTemplate.getText());
        textFieldNewWishEmailSubject.setText(v.getEmail());
    }

    private void fillAlreadySentWishesTable() {
        ViewExtendedPersonAnniversary v = tableViewPersonAnniversary.getSelectionModel()
                .getSelectedItem();
        sentWishes = reminderService.getSentWishes();
        initTableOfSentWishes();
        for (SentWish s:sentWishes){
            if ((s.getPersonAnniversary().getId()==v.getPersonAnniversaryId()) && v.getUserId()==loggedUser.getId())
                sentWishObservableList.add(s);
        }
        tableViewAlreadySentWishes.setItems(sentWishObservableList);
        labelAlreadySentWishesNameAndSurname.setText(v.getFirstName()+" "+v.getLastName());
    }

    private void fillDetailsSentWishesComponents() {
        ViewExtendedPersonAnniversary v = tableViewPersonAnniversary.getSelectionModel()
                .getSelectedItem();
        SentWish sentWish = tableViewAlreadySentWishes.getSelectionModel().getSelectedItem();
        labelSelectedAlreadySentWishKind.setText(v.getAnniversaryKind());
        if(sentWish.getSentByEmail()){
            labelSelectedAlreadySentWishSentBy.setText("e-mail");
            if(sentWish.getSentByLetter()){
                labelSelectedAlreadySentWishSentBy.setText(labelSelectedAlreadySentWishSentBy.getText()+" oraz list");
            }
        }
        else
            labelSelectedAlreadySentWishSentBy.setText("list");
        textAreaSelectedAlreadySentWishText.setText(sentWish.getText());

    }

    private void fillDetailsModeComponents() {
        ViewExtendedPersonAnniversary v = tableViewPersonAnniversary.getSelectionModel()
                .getSelectedItem();
        labelDetailsNameAndSurname.setText(v.getFirstName()+" "+v.getLastName());
        labelDetailsRelation.setText(v.getRelationName());
        labelDetailsAnniversaryKind.setText(v.getAnniversaryKind());
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        labelDetailsAnniversaryDate.setText(String.valueOf(df.format(v.getNextAnniversaryDate()))+"  (ur. "
        +String.valueOf(df.format(v.getAnniversaryDate()))+")");
        labelDetailsNumberOfSentWiches.setText(String.valueOf(v.getNumberOfSentWishes()));
        labelDetailsEmail.setText(v.getEmail());
        labelDetailsStreet.setText(v.getStreet());
        labelDetailsPostalCode.setText(v.getPostalCode());
        labelDetailsCity.setText(v.getCity());
        labelDetailsCountry.setText(v.getCountry());
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

    private TableCell<SentWish, Date> localSentWishDateFormat() {
        return new TableCell<SentWish, Date>() {
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
