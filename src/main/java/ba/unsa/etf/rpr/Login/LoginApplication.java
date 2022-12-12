package ba.unsa.etf.rpr.Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginApplication extends Application {
    @Override
    public void start(Stage primarystage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("loginProzor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        //primarystage.setResizable(false);
        primarystage.setMinHeight(150);
        primarystage.setMinWidth(300);
        primarystage.setTitle("Prijava");
        primarystage.setScene(scene);
        primarystage.show();
    }
}
