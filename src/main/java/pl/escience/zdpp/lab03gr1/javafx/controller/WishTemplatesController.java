package pl.escience.zdpp.lab03gr1.javafx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.escience.zdpp.lab03gr1.app.WishesReminder;
import pl.escience.zdpp.lab03gr1.database.entity.WishTemplate;
import pl.escience.zdpp.lab03gr1.javafx.CustomMessageBox;
import pl.escience.zdpp.lab03gr1.xml_parser.Parser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WishTemplatesController implements Initializable {
    private CustomMessageBox customMessageBox;
    private WishTemplate wishTemplate;
    private ObservableList<WishTemplate> wishTemplateObservableList= FXCollections.observableArrayList();

    @FXML
    private Label labelHeader, labelNumberOfWishTemplates;
    @FXML
    private TableView<WishTemplate> tableViewWishTemplates;
    @FXML
    private TableColumn<WishTemplate, String> tableColumnWishTemplatesText;
    @FXML
    private VBox vBoxNewWishMode;
    @FXML
    private TextArea textAreaSelectedWishTemplateText, textAreaNewWishText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customMessageBox = new CustomMessageBox("image/icon.png");
        labelNumberOfWishTemplates.setText("------");
        textAreaNewWishText.setText("");
        textAreaSelectedWishTemplateText.setText("");
        initTableView();
    }

    @FXML
    void buttonAdd_onAction() {

    }

    @FXML
    void buttonBack_onAction() {
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
            primaryStage.setScene(new Scene(parent, 1599, 900));
            Stage stage = (Stage) textAreaNewWishText.getScene().getWindow();
            stage.hide();
            primaryStage.show();
        } catch (IOException ioEcx) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ioEcx);
        }
    }

    @FXML
    void buttonDelete_onAction() {

    }

    @FXML
    void buttonReadFromFile_onAction() {
        String xmlPath;
        FileChooser frontCoversFileChooser = new FileChooser();
        frontCoversFileChooser.setTitle("Wybór tekstu szablonu życzeń z pliku");
        frontCoversFileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Szablony życzeń", "*.xml"));
        File file = frontCoversFileChooser.showOpenDialog(WishesReminder.getMainStage());
        if (file != null) {
            xmlPath = file.toString();
            Parser parser = new Parser();
            wishTemplate = parser.readFromXMLFile(xmlPath);
            wishTemplateObservableList.add(wishTemplate);
        }
    }

    @FXML
    void buttonWriteToFile_onAction() {
        if (tableViewWishTemplates.getSelectionModel().getSelectedItem() != null){
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Wybór lokalizacji zapisu szablonu życzeń");
            File directory = chooser.showDialog(WishesReminder.getMainStage());
            if (directory != null) {
                Parser parser = new Parser();
                WishTemplate wishTemplate = tableViewWishTemplates.getSelectionModel().getSelectedItem();
                parser.saveToXMLFile(wishTemplate);
            }
        }
        else{

        }

    }

    @FXML
    void tableViewWishTemplates_onMouseClicked() {

    }

    private void initTableView() {
        // TODO:
    }
}
