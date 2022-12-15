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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static ba.unsa.etf.rpr.Controllers.LoginController.user;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class UserProdajaKarataController {
    public ChoiceBox<Integer> brojKarataChoiceBox;
    public Label ukupnaCijenaLabel;
    private FilmDao f = new FilmDaoSQLImpl();
    private List<String> listaFilmova = f.getAllNames();
    private ObservableList<String> filmovi = FXCollections.observableArrayList(listaFilmova);
    private ObservableList<Integer> brKarata = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
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
        brojKarataChoiceBox.setItems(brKarata);
        filmChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                imeOdabranogFilma = listaFilmova.get(newValue.intValue());
                brojKarataChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        FilmDao ft = new FilmDaoSQLImpl();
                        //film = ft.getByIme(odabraniFilm);
                        brojKarata = brKarata.get(newValue.intValue());
                        ukupnaCijena = brojKarata*ft.getByIme(imeOdabranogFilma).getCijena();
                        ukupnaCijenaLabel.setText("Ukupna cijena: " + ukupnaCijena);
                    }
                });
            }
        });
    }

    public void odabirDatumaClick(ActionEvent actionEvent) {
        datum = odabirDatuma.getValue();
    }

    public void kupiButtonClick(ActionEvent actionEvent) throws IOException {
        int br = brojKarata;
        KartaDao kdao = new KartaDaoSQLImpl();
        UsersDao u = new UsersDaoSQLImpl();
        while(br != 0){
            Karta k = new Karta();
            film = f.getByIme(imeOdabranogFilma);
            k.setFilm(film);
            k.setUser(user);
            kdao.add(k);
            br--;
        }
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/KupljenaKarta.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        KupljenaKartaController kkc = fxmlLoader.getController();
        kkc.imeFilma.setText("Ime filma: " + imeOdabranogFilma);
        kkc.datumFilma.setText("Datum: " + datum);
        kkc.cijenaKarte.setText("Cijena: " + ukupnaCijena + "KM");
        stage.setTitle("Karta kupljena!");
        stage.setScene(scene);
        stage.show();
    }
}
