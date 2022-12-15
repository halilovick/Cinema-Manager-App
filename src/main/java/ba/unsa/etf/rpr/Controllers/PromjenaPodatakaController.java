package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.dao.UsersDao;
import ba.unsa.etf.rpr.dao.UsersDaoSQLImpl;
import ba.unsa.etf.rpr.domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;

import static ba.unsa.etf.rpr.Controllers.LoginController.user;
public class PromjenaPodatakaController {
    public TextField usernameTextField;
    public TextField lozinkaTextField;
    public TextField emailTextField;
    public TextField adresaTextField;
    public TextField gradTextField;
    public Button promjeniPodatkeButton;
    public Button zatvoriButton;
    public DatePicker datumRodjenjaField;

    @FXML
    private void initialize(){
        usernameTextField.setText(user.getUser());
        lozinkaTextField.setText(user.getPassword());
        emailTextField.setText(user.getEmail());
        adresaTextField.setText(user.getAdresa());
        gradTextField.setText(user.getGrad());
        datumRodjenjaField.setValue(user.getDatum_rodjenja().toLocalDate());
    }

    public void promjeniPodatkeButtonClick(ActionEvent actionEvent) {
        User u = new User();
        u.setId(user.getId());
        u.setUser(usernameTextField.getText());
        u.setPassword(lozinkaTextField.getText());
        u.setEmail(emailTextField.getText());
        u.setAdresa(adresaTextField.getText());
        u.setGrad(gradTextField.getText());
        u.setDatum_rodjenja(Date.valueOf(datumRodjenjaField.getValue()));
        UsersDao ud = new UsersDaoSQLImpl();
        ud.update(u);
    }

    public void zatvoriButtonClick(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
