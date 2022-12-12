package ba.unsa.etf.rpr.Login;

import ba.unsa.etf.rpr.dao.UsersDao;
import ba.unsa.etf.rpr.dao.UsersDaoSQLImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class Controller {
    public PasswordField fieldPassword;
    public TextField fieldUsername;

    public void prijavaButtonClick(ActionEvent actionEvent) throws SQLException {
        UsersDao u = new UsersDaoSQLImpl();
        int loginId = u.getLoggedInId(fieldUsername.getText(), fieldPassword.getText());
        if(loginId != 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pozdrav");
            alert.setHeaderText("Podaci:");
            alert.setContentText("Vas id je: " + loginId);
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pozdrav");
            alert.setHeaderText("Pogresni podaci!");
            alert.setContentText("..");
            alert.showAndWait();
        }
    }
}