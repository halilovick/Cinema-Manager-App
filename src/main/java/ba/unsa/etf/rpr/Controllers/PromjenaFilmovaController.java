package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
        stage.setResizable(false);
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
        stage.setTitle("Dodavanje filma");
        stage.setScene(scene);
        stage.show();
    }

    public void urediFilmButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/UrediFilm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        UrediFilmController ufc = fxmlLoader.getController();
        stage.setResizable(false);
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
        stage.setTitle("Uredi film");
        stage.setScene(scene);
        stage.show();
    }

    public void obrisiFilmButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/BrisanjeFilma.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        BrisanjeFilmaController bfc = fxmlLoader.getController();
        stage.setResizable(false);
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
        stage.setTitle("Brisanje filma");
        stage.setScene(scene);
        stage.show();
    }
}
