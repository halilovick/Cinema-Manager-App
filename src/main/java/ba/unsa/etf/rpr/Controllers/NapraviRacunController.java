package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.FilmoviException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.EmailValidator;

import java.sql.Date;

public class NapraviRacunController {
    public TextField usernameTextField;
    public TextField lozinkaTextField;
    public TextField emailTextField;
    public TextField adresaTextField;
    public TextField gradTextField;
    public DatePicker datumRodjenjaField;
    public TextField imeTextField;

    @FXML
    void initialize() {
        emailTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                EmailValidator validator = EmailValidator.getInstance();
                if (validator.isValid(newValue)) {
                    emailTextField.getStyleClass().removeAll("poljeNijeIspravno");
                    emailTextField.getStyleClass().add("poljeIspravno");
                } else {
                    emailTextField.getStyleClass().removeAll("poljeIspravno");
                    emailTextField.getStyleClass().add("poljeNijeIspravno");
                }
            }
        });
    }

    public void napraviAccountButtonClick(ActionEvent actionEvent) throws FilmoviException {
        if (usernameTextField.getText().isEmpty() || lozinkaTextField.getText().isEmpty() || imeTextField.getText().isEmpty() || emailTextField.getText().isEmpty() || adresaTextField.getText().isEmpty() || gradTextField.getText().isEmpty() || datumRodjenjaField.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nedostaju podaci!");
            alert.setContentText("Molim vas upišite sve tražene podatke.");
            alert.showAndWait();
            return;
        }
        User u = new User();
        u.setUser(usernameTextField.getText());
        u.setPassword(lozinkaTextField.getText());
        u.setIme(imeTextField.getText());
        u.setEmail(emailTextField.getText());
        u.setAdresa(adresaTextField.getText());
        u.setGrad(gradTextField.getText());
        u.setDatum_rodjenja(Date.valueOf(datumRodjenjaField.getValue()));
        DaoFactory.usersDao().add(u);
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Racun kreiran!");
        alert.setHeaderText(null);
        alert.setContentText("Vaš račun je uspješno kreiran! Ulogujte se.");
        alert.showAndWait();
        stage.close();
    }
}
