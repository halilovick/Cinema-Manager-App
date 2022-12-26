package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.exceptions.FilmoviException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class App extends Application {
    @Override
    public void start(Stage primarystage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/loginProzor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        primarystage.setResizable(false);
        primarystage.setMinHeight(150);
        primarystage.setMinWidth(300);
        primarystage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
        primarystage.setTitle("Prijava");
        primarystage.setScene(scene);
        primarystage.show();
    }

    public static void main(String[] args) throws SQLException, FilmoviException {
        launch();
        // napraviti regex gdje god je moguce - dodati check jel username zauzet..
        // dodati help bar i help popups
        // dodati Users page (admin screen kao i za filmove)
        // dodati pretragu filmova
        // poboljsati dizajn - omoguciti resize prozora - napraviti sve u jednom prozoru
        // dodati menu bar
        // uraditi refactoring linija poput geticons, setresizable....
    }
}
