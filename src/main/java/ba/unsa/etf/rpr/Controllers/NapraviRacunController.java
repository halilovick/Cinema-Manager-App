package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.business.usersManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.FilmoviException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.EmailValidator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NapraviRacunController {
    public TextField usernameTextField;
    public TextField lozinkaTextField;
    public TextField emailTextField;
    public TextField adresaTextField;
    public TextField gradTextField;
    public DatePicker datumRodjenjaField;
    public TextField imeTextField;
    public Label passwordStrengthLabel;
    public Label usernameZauzetLabel;
    public Label dateRegex;
    private boolean ispravanEmail = false;
    private usersManager umanager = new usersManager();

    private static String passwordCheck(String password) {
        int n = password.length();
        boolean hasLower = false, hasUpper = false, hasDigit = false, specialChar = false;
        Set<Character> set = new HashSet<Character>(Arrays.asList('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+'));
        for (char i : password.toCharArray()) {
            if (Character.isLowerCase(i)) hasLower = true;
            if (Character.isUpperCase(i)) hasUpper = true;
            if (Character.isDigit(i)) hasDigit = true;
            if (set.contains(i)) specialChar = true;
        }
        if (hasDigit && hasLower && hasUpper && specialChar && (n >= 8)) return "S"; //strong
        else if ((hasLower || hasUpper || specialChar) && (n >= 6)) return "M"; //moderate
        else return "W"; //weak
    }

    private static boolean dateOfBirthCheck(Date d) {
        Date d2 = Date.valueOf("2007-01-01");
        if (d.compareTo(d2) > 0) {
            return false;
        }
        return true;
    }

    @FXML
    void initialize() {
        usernameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (!umanager.userExists(newValue)) {
                        usernameTextField.getStyleClass().removeAll("poljeNijeIspravno");
                        usernameTextField.getStyleClass().add("poljeIspravno");
                        usernameZauzetLabel.setText("");
                    } else {
                        usernameTextField.getStyleClass().removeAll("poljeIspravno");
                        usernameTextField.getStyleClass().add("poljeNijeIspravno");
                        usernameZauzetLabel.setText("Username already exists!");
                    }
                } catch (FilmoviException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        lozinkaTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (passwordCheck(newValue).equals("S")) {
                    lozinkaTextField.getStyleClass().removeAll("moderatePassword", "weakPassword");
                    lozinkaTextField.getStyleClass().add("strongPassword");
                    passwordStrengthLabel.setText("Password strength: Strong");
                } else if (passwordCheck(newValue).equals("M")) {
                    lozinkaTextField.getStyleClass().removeAll("strongPassword", "weakPassword");
                    lozinkaTextField.getStyleClass().add("moderatePassword");
                    passwordStrengthLabel.setText("Password strength: Moderate");
                } else {
                    lozinkaTextField.getStyleClass().removeAll("strongPassword", "moderatePassword");
                    lozinkaTextField.getStyleClass().add("weakPassword");
                    passwordStrengthLabel.setText("Password strength: Weak");
                }
            }
        });
        emailTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                EmailValidator validator = EmailValidator.getInstance();
                if (validator.isValid(newValue)) {
                    emailTextField.getStyleClass().removeAll("poljeNijeIspravno");
                    emailTextField.getStyleClass().add("poljeIspravno");
                    ispravanEmail = true;
                } else {
                    emailTextField.getStyleClass().removeAll("poljeIspravno");
                    emailTextField.getStyleClass().add("poljeNijeIspravno");
                    ispravanEmail = false;
                }
            }
        });
        datumRodjenjaField.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
                if (!dateOfBirthCheck(Date.valueOf(datumRodjenjaField.getValue()))) {
                    datumRodjenjaField.getStyleClass().removeAll("poljeIspravno");
                    datumRodjenjaField.getStyleClass().add("poljeNijeIspravno");
                    dateRegex.setText("To create an account, you must be at least 16 years old.");
                }
                else{
                    datumRodjenjaField.getStyleClass().removeAll("poljeNijeIspravno");
                    dateRegex.setText("");
                }
            }
        });
    }

    public void napraviAccountButtonClick(ActionEvent actionEvent) throws FilmoviException {
        if (usernameTextField.getText().isEmpty() || lozinkaTextField.getText().isEmpty() || imeTextField.getText().isEmpty() || emailTextField.getText().isEmpty() || adresaTextField.getText().isEmpty() || gradTextField.getText().isEmpty() || datumRodjenjaField.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing credentials!");
            alert.setContentText("You must fill out all of the fields.");
            alert.showAndWait();
            return;
        }
        if (umanager.userExists(usernameTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid username!");
            alert.setContentText("User with such username already exists!");
            alert.showAndWait();
            return;
        }
        if (passwordCheck(lozinkaTextField.getText()).equals("W")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Password too weak!");
            alert.setContentText("Please enter a stronger password.");
            alert.showAndWait();
            return;
        }
        if (!ispravanEmail) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Email not valid!");
            alert.setContentText("Please enter a valid email address.");
            alert.showAndWait();
            return;
        }
        if (Date.valueOf(datumRodjenjaField.getValue()).compareTo(Date.valueOf("2007-01-01")) > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid date of birth!");
            alert.setContentText("To create an account, you must be at least 16 years old.");
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
        umanager.add(u);
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText(null);
        alert.setContentText("Your account has been created. You can now log in.");
        alert.showAndWait();
        stage.close();
    }
}
