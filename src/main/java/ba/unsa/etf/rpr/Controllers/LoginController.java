package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.App;
import ba.unsa.etf.rpr.dao.UsersDao;
import ba.unsa.etf.rpr.dao.UsersDaoSQLImpl;
import ba.unsa.etf.rpr.domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {
    public PasswordField fieldPassword;
    public TextField fieldUsername;
    public static User user = new User();
    public static Stage homeStage = new Stage();

    public void prijavaButtonClick(ActionEvent actionEvent) throws SQLException, IOException {
        UsersDao u = new UsersDaoSQLImpl();
        int loginId = u.getLoggedInId(fieldUsername.getText(), fieldPassword.getText());
        if (loginId != 0) {
            user = u.getById(loginId);
            if (user.isAdmin()) {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/AdminPage.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                AdminPageController apc = fxmlLoader.getController();
                apc.dobrodosaoLabela.setText(apc.dobrodosaoLabela.getText() + fieldUsername.getText() + "!");
                homeStage.setResizable(false);
                homeStage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
                homeStage.setTitle("Admin Page");
                homeStage.setScene(scene);
                homeStage.show();
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/UserPage.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                UserPageController upc = fxmlLoader.getController();
                upc.dobrodosaoLabela.setText(upc.dobrodosaoLabela.getText() + fieldUsername.getText() + "!");
                homeStage.setResizable(false);
                homeStage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
                homeStage.setTitle("User Page");
                homeStage.setScene(scene);
                homeStage.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Pogresni podaci!");
            alert.setContentText("Molim vas pokušajte ponovo");
            alert.showAndWait();
        }
    }
    public User getUser() {
        return user;
    }
}