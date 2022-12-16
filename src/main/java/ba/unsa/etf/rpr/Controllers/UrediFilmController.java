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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UrediFilmController {
    public Button promjeniButton;
    public TextField imeFilmaTextField;
    public TextField zanrTextField;
    public TextField trajanjeTextField;
    public TextField cijenaTextField;
    public TextField brojSaleTextField;
    public ChoiceBox filmoviChoiceBox;
    private FilmDao f = new FilmDaoSQLImpl();
    private ObservableList<String> filmovi = FXCollections.observableArrayList(f.getAllNames());
    private int idFilm;

    @FXML
    private void initialize(){
        filmoviChoiceBox.setItems(filmovi);
        filmoviChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Film film = f.getByIme(f.getAllNames().get(newValue.intValue()));
                imeFilmaTextField.setText(film.getIme());
                zanrTextField.setText(film.getZanr());
                trajanjeTextField.setText(String.valueOf(film.getTrajanje()));
                cijenaTextField.setText(String.valueOf(film.getCijena()));
                brojSaleTextField.setText(String.valueOf(film.getBroj_sale()));
                idFilm = film.getId();
            }
        });
    }
    public void promjeniButtonClick(ActionEvent actionEvent) {
        Film f = new Film();
        FilmDao fd = new FilmDaoSQLImpl();
        f.setId(idFilm);
        f.setIme(imeFilmaTextField.getText());
        f.setZanr(zanrTextField.getText());
        f.setTrajanje(Integer.parseInt(trajanjeTextField.getText()));
        f.setCijena(Integer.parseInt(cijenaTextField.getText()));
        f.setBroj_sale(Integer.parseInt(brojSaleTextField.getText()));
        fd.update(f);
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Uređivanje uspješno!");
        alert.setHeaderText(null);
        alert.setContentText("Film je uređen!");
        alert.showAndWait();
        stage.close();
    }
}
