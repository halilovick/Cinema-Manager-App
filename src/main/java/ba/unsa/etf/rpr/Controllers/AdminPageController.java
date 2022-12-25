package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AdminPageController {
    public Label dobrodosaoLabela;
    public Button promjenaFilmovaButton;
    public Button prodajaKarataButton;

    public void prodajaKarataButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/UserProdajaKarata.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        UserProdajaKarataController upkc = fxmlLoader.getController();
        stage.setResizable(false);
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
        stage.setTitle("Prodaja");
        stage.setScene(scene);
        stage.show();
        Node n = (Node) actionEvent.getSource();
        Stage stage2 = (Stage) n.getScene().getWindow();
        stage2.close();
    }

    public void promjenaFilmovaButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/pregledFilmovaAdmin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        pregledFilmovaAdminController pfac = fxmlLoader.getController();
        stage.setResizable(false);
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
        stage.setTitle("Promjena filmova");
        stage.setScene(scene);
        stage.show();
        Node n = (Node) actionEvent.getSource();
        Stage stage2 = (Stage) n.getScene().getWindow();
        stage2.close();
    }

    public void signOutButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/loginProzor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        LoginController lc = fxmlLoader.getController();
        stage.setResizable(false);
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
        stage.setTitle("Prijava");
        stage.setScene(scene);
        stage.show();
        Node n = (Node) actionEvent.getSource();
        Stage stage2 = (Stage) n.getScene().getWindow();
        stage2.close();
    }

    public void useriButtonClick(ActionEvent actionEvent) {
    }
}
