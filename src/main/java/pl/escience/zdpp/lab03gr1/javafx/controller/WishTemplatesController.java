package pl.escience.zdpp.lab03gr1.javafx.controller;

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
import pl.escience.zdpp.lab03gr1.app.Main;
import pl.escience.zdpp.lab03gr1.javafx.CustomMessageBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WishTemplatesController implements Initializable {
    private CustomMessageBox customMessageBox;

    @FXML
    private Label labelHeader, labelNumberOfWishTemplates;
    @FXML
    private TableView<?> tableViewWishTemplates;
    @FXML
    private TableColumn<?, ?> tableColumnWishTemplatesText;
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
            Main.setMainStage(primaryStage);
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
        FileChooser frontCoversFileChooser = new FileChooser();
        frontCoversFileChooser.setTitle("Wybór tekstu szablonu życzeń z pliku");
        frontCoversFileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Szablony życzeń", "*.xml"));
        File file = frontCoversFileChooser.showOpenDialog(Main.getMainStage());
        if (file != null) {
            // TODO: Odczyt szablonu, utworzenie szablonu, dodanie do BD, dodanie do listy.
        }
    }

    @FXML
    void buttonWriteToFile_onAction() {
        // if (tableViewSelectedRow != null) then
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Wybór lokalizacji zapisu szablonu życzeń");
        File directory = chooser.showDialog(Main.getMainStage());
        if (directory != null) {
            // TODO:
        }
    }

    @FXML
    void tableViewWishTemplates_onMouseClicked() {

    }

    private void initTableView() {
        // TODO:
    }
}
