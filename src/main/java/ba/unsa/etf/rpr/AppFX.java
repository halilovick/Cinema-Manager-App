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

/**
 * Main class for working with JavaFX framework
 */
public class AppFX extends Application {
    @Override
    public void start(Stage primarystage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/loginProzor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        primarystage.setResizable(false);
        primarystage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
        primarystage.setTitle("Login page");
        primarystage.setScene(scene);
        primarystage.show();
    }

    public static void main(String[] args) throws SQLException, FilmoviException {
        launch();
    }
}
