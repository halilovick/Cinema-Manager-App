package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.App;
import ba.unsa.etf.rpr.business.usersManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.FilmoviException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

import static ba.unsa.etf.rpr.Controllers.LoginController.homeStage;
import static ba.unsa.etf.rpr.Controllers.LoginController.user;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class PromjenaPodatakaController {
    public TextField usernameTextField;
    public TextField lozinkaTextField;
    public TextField emailTextField;
    public TextField adresaTextField;
    public TextField gradTextField;
    public Button promjeniPodatkeButton;
    public Button zatvoriButton;
    public DatePicker datumRodjenjaField;
    public TextField imeTextField;
    private usersManager umanager = new usersManager();

    @FXML
    private void initialize() {
        usernameTextField.setText(user.getUser());
        lozinkaTextField.setText(user.getPassword());
        imeTextField.setText(user.getIme());
        emailTextField.setText(user.getEmail());
        adresaTextField.setText(user.getAdresa());
        gradTextField.setText(user.getGrad());
        datumRodjenjaField.setValue(user.getDatum_rodjenja().toLocalDate());
    }

    public void promjeniPodatkeButtonClick(ActionEvent actionEvent) throws FilmoviException {
        User u = new User();
        u.setId(user.getId());
        u.setUser(usernameTextField.getText());
        u.setPassword(lozinkaTextField.getText());
        u.setIme(imeTextField.getText());
        u.setEmail(emailTextField.getText());
        u.setAdresa(adresaTextField.getText());
        u.setGrad(gradTextField.getText());
        u.setDatum_rodjenja(Date.valueOf(datumRodjenjaField.getValue()));
        umanager.update(u);
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Uređivanje uspješno!");
        alert.setHeaderText(null);
        alert.setContentText("Podaci su spašeni. Ulogujte se ponovo.");
        alert.showAndWait();
        stage.close();
        homeStage.close();
    }

    public void zatvoriButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/UserPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        UserPageController upc = fxmlLoader.getController();
        stage.setResizable(false);
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
        stage.setTitle("User page");
        stage.setScene(scene);
        stage.show();
        Node n = (Node) actionEvent.getSource();
        Stage stage2 = (Stage) n.getScene().getWindow();
        stage2.close();
    }
}
