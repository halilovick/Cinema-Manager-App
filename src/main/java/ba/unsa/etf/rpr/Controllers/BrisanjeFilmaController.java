package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.dao.FilmDao;
import ba.unsa.etf.rpr.dao.FilmDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Film;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class BrisanjeFilmaController {
    public ChoiceBox brisanjeChoiceBox;
    public Button obrisiButtonBrisanjeFilma;
    private FilmDao f = new FilmDaoSQLImpl();
    private ObservableList<String> filmovi = FXCollections.observableArrayList(f.getAllNames());
    private String imeOdabranogFilma = new String();

    @FXML
    private void initialize() {
        brisanjeChoiceBox.setItems(filmovi);
        brisanjeChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                imeOdabranogFilma = f.getAllNames().get(newValue.intValue());
            }
        });
    }

    public void obrisiButtonClick(ActionEvent actionEvent) {
        if(imeOdabranogFilma.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Odaberite film!");
            alert.setContentText("Niti jedan film nije odabran.");
            alert.showAndWait();
            return;
        }
        FilmDao fd = new FilmDaoSQLImpl();
        Film film = fd.getByIme(imeOdabranogFilma);
        fd.delete(film.getId());
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Brisanje uspješno");
        alert.setHeaderText(null);
        alert.setContentText("Odabrani film je obrisan!");
        alert.showAndWait();
        stage.close();
    }
}
