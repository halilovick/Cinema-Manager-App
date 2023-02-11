package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.App;
import ba.unsa.etf.rpr.business.filmoviManager;
import ba.unsa.etf.rpr.business.karteManager;
import ba.unsa.etf.rpr.business.usersManager;
import ba.unsa.etf.rpr.domain.Film;
import ba.unsa.etf.rpr.domain.Karta;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.FilmoviException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static ba.unsa.etf.rpr.Controllers.LoginController.user;
import static ba.unsa.etf.rpr.Controllers.NapraviRacunController.dateOfBirthCheck;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class UserPageController {

    public TextField brojKarataTextField;
    public Label zanrLabel;
    public Label trajanjeLabel;
    public Label cijenaLabel;
    public Label zanrLabelFiksna;
    public Label trajanjeLabelFiksna;
    public Label cijenaLabelFiksna;
    public ChoiceBox<String> filmChoiceBox;
    private String imeOdabranogFilma = "";
    private int brojKarata = 0;
    public DatePicker odabirDatuma;
    private LocalDate datum;
    public Button kupiButton;
    private final filmoviManager fmanager = new filmoviManager();
    private final karteManager kmanager = new karteManager();
    private final List<String> listaFilmova = fmanager.getAllNames();
    private final ObservableList<String> filmovi = FXCollections.observableArrayList(listaFilmova);
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

    public UserPageController() throws FilmoviException {
    }

    @FXML
    private void initialize() {
        initializeKupovinaKarata();
        initializePromjenaPodataka();
    }

    private void initializeKupovinaKarata() {
        filmChoiceBox.setItems(filmovi);
        filmChoiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            try {
                imeOdabranogFilma = listaFilmova.get(newValue.intValue());
                Film f = fmanager.getByIme(imeOdabranogFilma);
                trajanjeLabelFiksna.setText("DURATION:");
                cijenaLabelFiksna.setText("PRICE:");
                zanrLabelFiksna.setText("GENRE:");
                trajanjeLabel.setText(f.getTrajanje() + " MIN");
                zanrLabel.setText(f.getZanr());
                cijenaLabel.setText(f.getCijena() + " KM");
            } catch (FilmoviException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void initializePromjenaPodataka() {
        usernameTextField.setText(user.getUser());
        lozinkaTextField.setText(user.getPassword());
        imeTextField.setText(user.getIme());
        emailTextField.setText(user.getEmail());
        adresaTextField.setText(user.getAdresa());
        gradTextField.setText(user.getGrad());
        datumRodjenjaField.setValue(user.getDatum_rodjenja().toLocalDate());
    }

    /**
     * Kupi button click. Adds ticket do database.
     *
     * @param actionEvent the action event
     * @throws FilmoviException the filmovi exception
     * @throws IOException      the io exception
     */
    public void kupiButtonClick(ActionEvent actionEvent) throws FilmoviException, IOException {
        try {
            brojKarata = Integer.parseInt(brojKarataTextField.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Choose number of tickets!");
            alert.setContentText("A number of tickets has not been chosen");
            alert.showAndWait();
            return;
        }
        if (imeOdabranogFilma.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Choose a film!");
            alert.setContentText("A film has not been chosen");
            alert.showAndWait();
            return;
        } else if (brojKarata <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Choose number of tickets!");
            alert.setContentText("A number of tickets has not been chosen");
            alert.showAndWait();
            return;
        } else if (datum == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Choose a date!");
            alert.setContentText("A date has not been chosen");
            alert.showAndWait();
            return;
        }
        int ukupnaCijena = brojKarata * fmanager.getByIme(imeOdabranogFilma).getCijena();
        while (brojKarata != 0) {
            Karta k = new Karta();
            k.setFilm(fmanager.getByIme(imeOdabranogFilma));
            k.setUser(user);
            kmanager.add(k);
            brojKarata--;
        }
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/KupljenaKarta.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        KupljenaKartaController kkc = fxmlLoader.getController();
        kkc.imeFilma.setText(imeOdabranogFilma);
        kkc.datumFilma.setText(datum + "");
        kkc.cijenaKarte.setText(ukupnaCijena + "KM");
        stage.setResizable(false);
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
        stage.setTitle("Ticket successfully purchased!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Nazad button click. Goes back to login screen.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void nazadButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/loginProzor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        LoginController lc = fxmlLoader.getController();
        stage.setResizable(false);
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
        stage.setTitle("Login page");
        stage.setScene(scene);
        stage.show();
        Node n = (Node) actionEvent.getSource();
        Stage stage2 = (Stage) n.getScene().getWindow();
        stage2.close();
    }

    /**
     * Promjeni podatke button click. Updates user in database.
     *
     * @param actionEvent the action event
     * @throws FilmoviException the filmovi exception
     * @throws IOException      the io exception
     */
    public void promjeniPodatkeButtonClick(ActionEvent actionEvent) throws FilmoviException, IOException {
        if (!dateOfBirthCheck(Date.valueOf(datumRodjenjaField.getValue()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid date of birth!");
            alert.setContentText("To create an account, you must be at least 16 years old.");
            alert.showAndWait();
            return;
        } else if (!EmailValidator.getInstance().isValid(emailTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Email not valid!");
            alert.setContentText("Please enter a valid email address.");
            alert.showAndWait();
            return;
        }
        try {
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
            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("Credentials have been changed. Please log in again.");
            alert.showAndWait();
            stage.close();
            Stage stage1 = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/loginProzor.fxml"));
            Scene scene1 = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            LoginController lc = fxmlLoader.getController();
            stage1.setResizable(false);
            stage1.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
            stage1.setTitle("Login page");
            stage1.setScene(scene1);
            stage1.show();
        } catch (FilmoviException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username already exists!");
            alert.setContentText("Choose a different username.");
            alert.showAndWait();
        }
    }

    /**
     * Zatvori button click. Goes back to login screen.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void zatvoriButtonClick(ActionEvent actionEvent) throws IOException {
        nazadButtonClick(actionEvent);
    }

    /**
     * Odabir datuma click. Converts date to correct format.
     */
    public void odabirDatumaClick() {
        datum = odabirDatuma.getValue();
    }
}
