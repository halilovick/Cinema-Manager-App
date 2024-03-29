package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.App;
import ba.unsa.etf.rpr.business.usersManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.FilmoviException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {
    public PasswordField fieldPassword;
    public TextField fieldUsername;
    public static User user = new User();
    public static Stage homeStage = new Stage();
    public Button prijavaButton;
    private usersManager umanager = new usersManager();

    /**
     * Prijava button click.
     *
     * @param actionEvent the action event
     * @throws FilmoviException the filmovi exception
     * @throws IOException      the io exception
     */
    public void prijavaButtonClick(ActionEvent actionEvent) throws FilmoviException, IOException {
        int loginId = umanager.getLoggedInId(fieldUsername.getText(), fieldPassword.getText());
        if (loginId != 0) {
            user = umanager.getById(loginId);
            if (user.isAdmin()) {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/AdminPage.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                AdminPageController apc = fxmlLoader.getController();
                //homeStage.setResizable(false);
                homeStage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
                homeStage.setTitle("Admin Page");
                homeStage.setScene(scene);
                homeStage.show();
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/UserPage.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                UserPageController upc = fxmlLoader.getController();
                homeStage.setResizable(false);
                homeStage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
                homeStage.setTitle("User Page");
                homeStage.setScene(scene);
                homeStage.show();
            }
            Node n = (Node) actionEvent.getSource();
            Stage stage2 = (Stage) n.getScene().getWindow();
            stage2.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong credentials");
            alert.setContentText("Invalid username or password");
            alert.showAndWait();
        }
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Create account button click.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void createAccountButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/napraviRacun.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        NapraviRacunController nrc = fxmlLoader.getController();
        stage.setResizable(false);
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
        stage.setTitle("Sign up");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Enter key pressed on password field.
     *
     * @param keyEvent the key event
     * @throws IOException      the io exception
     * @throws FilmoviException the filmovi exception
     */
    public void passwordEnterPressed(javafx.scene.input.KeyEvent keyEvent) throws IOException, FilmoviException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            prijavaButton.fire();
        }
    }

    /**
     * Enter key pressed on username field.
     *
     * @param keyEvent the key event
     * @throws IOException      the io exception
     * @throws FilmoviException the filmovi exception
     */
    public void usernameKeyPress(KeyEvent keyEvent) throws IOException, FilmoviException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            prijavaButton.fire();
        }
    }
}