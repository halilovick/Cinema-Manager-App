package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class PromjenaFilmovaController {
    public Button dodajFilmButton;
    public Button urediFilmButton;
    public Button obrisiFilmButton;

    public void dodajFilmButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/DodavanjeFilma.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        DodavanjeFilmaController dfc = fxmlLoader.getController();
        stage.setTitle("Dodavanje filma");
        stage.setScene(scene);
        stage.show();
    }

    public void urediFilmButtonClick(ActionEvent actionEvent) {
    }

    public void obrisiFilmButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/BrisanjeFilma.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        BrisanjeFilmaController bfc = fxmlLoader.getController();
        stage.setTitle("Brisanje filma");
        stage.setScene(scene);
        stage.show();
    }
}
