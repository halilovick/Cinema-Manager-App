package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        stage.setTitle("Prodaja");
        stage.setScene(scene);
        stage.show();
    }

    public void promjenaFilmovaButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/PromjenaFilmova.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        PromjenaFilmovaController pfc = fxmlLoader.getController();
        stage.setTitle("Promjena filmova");
        stage.setScene(scene);
        stage.show();
    }
}
