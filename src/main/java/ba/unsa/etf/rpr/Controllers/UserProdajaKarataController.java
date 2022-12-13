package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.Film;
import ba.unsa.etf.rpr.domain.Karta;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.util.List;

import static ba.unsa.etf.rpr.Controllers.LoginController.user;

public class UserProdajaKarataController {
    public ChoiceBox<Integer> brojKarataChoiceBox;
    public Label ukupnaCijenaLabel;
    public FilmDao f = new FilmDaoSQLImpl();
    public List<String> listaFilmova = f.getAllNames();
    public ObservableList<String> filmovi = FXCollections.observableArrayList(listaFilmova);
    public ObservableList<Integer> brKarata = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    public ChoiceBox<String> filmChoiceBox;
    public String imeOdabranogFilma = new String();
    public int brojKarata = 0;
    public DatePicker odabirDatuma;
    public LocalDate datum;
    public Button kupiButton;
    public Film film = new Film();

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
                        ukupnaCijenaLabel.setText("Ukupna cijena: " + brojKarata*ft.getByIme(imeOdabranogFilma).getCijena());
                    }
                });
            }
        });
    }

    public void odabirDatumaClick(ActionEvent actionEvent) {
        datum = odabirDatuma.getValue();
        //System.out.println(datum);
    }

    public void kupiButtonClick(ActionEvent actionEvent) {
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
    }
}
