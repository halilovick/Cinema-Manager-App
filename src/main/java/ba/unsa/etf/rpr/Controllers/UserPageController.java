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

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static ba.unsa.etf.rpr.Controllers.LoginController.user;
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
                trajanjeLabelFiksna.setText("TRAJANJE:");
                cijenaLabelFiksna.setText("CIJENA:");
                zanrLabelFiksna.setText("ZANR:");
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

    public void kupiButtonClick() throws IOException, FilmoviException {
        try {
            brojKarata = Integer.parseInt(brojKarataTextField.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Pogresni podaci");
            alert.setContentText("Uneseni su nevalidni podaci!");
            alert.showAndWait();
        }
        int ukupnaCijena = brojKarata * fmanager.getByIme(imeOdabranogFilma).getCijena();
        if (imeOdabranogFilma.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Odaberite film!");
            alert.setContentText("Niti jedan film nije odabran.");
            alert.showAndWait();
            return;
        } else if (brojKarata <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Odaberite broj karata!");
            alert.setContentText("Broj karata za film nije odabran.");
            alert.showAndWait();
            return;
        }
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
        kkc.imeFilma.setText("Ime filma: " + imeOdabranogFilma);
        kkc.datumFilma.setText("Datum: " + datum);
        kkc.cijenaKarte.setText("Cijena: " + ukupnaCijena + "KM");
        stage.setResizable(false);
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
        stage.setTitle("Karta kupljena!");
        stage.setScene(scene);
        stage.show();
    }

    public void nazadButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/loginProzor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        LoginController lc = fxmlLoader.getController();
        stage.setResizable(false);
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
        stage.setTitle("Prijava");
        stage.setScene(scene);
        stage.show();
        Node n = (Node) actionEvent.getSource();
        Stage stage2 = (Stage) n.getScene().getWindow();
        stage2.close();
    }

    public void promjeniPodatkeButtonClick(ActionEvent actionEvent) throws FilmoviException, IOException {
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
        Stage stage1 = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/loginProzor.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        LoginController lc = fxmlLoader.getController();
        stage1.setResizable(false);
        stage1.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
        stage1.setTitle("Prijava");
        stage1.setScene(scene1);
        stage1.show();
    }

    public void zatvoriButtonClick(ActionEvent actionEvent) throws IOException {
        nazadButtonClick(actionEvent);
    }

    public void odabirDatumaClick() {
        datum = odabirDatuma.getValue();
    }
}
