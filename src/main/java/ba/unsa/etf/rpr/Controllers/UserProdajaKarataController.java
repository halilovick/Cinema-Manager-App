package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.App;
import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.Film;
import ba.unsa.etf.rpr.domain.Karta;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static ba.unsa.etf.rpr.Controllers.LoginController.user;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class UserProdajaKarataController {
    public TextField brojKarataTextField;
    private FilmDao f = new FilmDaoSQLImpl();
    private List<String> listaFilmova = f.getAllNames();
    private ObservableList<String> filmovi = FXCollections.observableArrayList(listaFilmova);
    public ChoiceBox<String> filmChoiceBox;
    private String imeOdabranogFilma = new String();
    private int brojKarata = 0;
    public DatePicker odabirDatuma;
    private LocalDate datum;
    public Button kupiButton;
    private Film film = new Film();
    private int ukupnaCijena;

    @FXML
    private void initialize() {
        filmChoiceBox.setItems(filmovi);
        filmChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                imeOdabranogFilma = listaFilmova.get(newValue.intValue());
            }
        });
    }

    public void odabirDatumaClick(ActionEvent actionEvent) {
        datum = odabirDatuma.getValue();
    }

    public void kupiButtonClick(ActionEvent actionEvent) throws IOException {
        try {
            brojKarata = Integer.parseInt(brojKarataTextField.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Pogresni podaci");
            alert.setContentText("Uneseni su nevalidni podaci!");
            alert.showAndWait();
        }
        FilmDao ft = new FilmDaoSQLImpl();
        ukupnaCijena = brojKarata * ft.getByIme(imeOdabranogFilma).getCijena();
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
        KartaDao kdao = new KartaDaoSQLImpl();
        UsersDao u = new UsersDaoSQLImpl();
        while (brojKarata != 0) {
            Karta k = new Karta();
            film = f.getByIme(imeOdabranogFilma);
            k.setFilm(film);
            k.setUser(user);
            kdao.add(k);
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
}
