package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.App;
import ba.unsa.etf.rpr.dao.FilmDao;
import ba.unsa.etf.rpr.dao.FilmDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class pregledFilmovaAdminController {
    private Integer id;
    public TextField imeField;
    public TextField zanrField;
    public TextField trajanjeField;
    public TextField cijenaField;
    public TextField brojsaleField;
    public TableColumn colID;
    public TableColumn colIme;
    public TableColumn colZanr;
    public TableColumn colTrajanje;
    public TableColumn colCijena;
    public TableColumn colBrojSale;
    public TableView<Film> tabelaFilmova;

    public void dodajButtonClick(ActionEvent actionEvent) {
        if(imeField.getText().isEmpty() || zanrField.getText().isEmpty() || trajanjeField.getText().isEmpty() || cijenaField.getText().isEmpty() || brojsaleField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Pogresni podaci");
            alert.setContentText("Uneseni su nevalidni podaci!");
            alert.showAndWait();
            return;
        }
        try {
            Film f = new Film();
            f.setIme(imeField.getText());
            f.setZanr(zanrField.getText());
            f.setTrajanje(Integer.parseInt(trajanjeField.getText()));
            f.setCijena(Integer.parseInt(cijenaField.getText()));
            f.setBroj_sale(Integer.parseInt(brojsaleField.getText()));
            FilmDao fd = new FilmDaoSQLImpl();
            fd.add(f);
            UpdateTable();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dodavanje uspješno");
            alert.setHeaderText(null);
            alert.setContentText("Film je dodan!");
            alert.showAndWait();
        } catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Pogresni podaci");
            alert.setContentText("Uneseni su nevalidni podaci!");
            alert.showAndWait();
        }
    }

    public void azurirajButtonClick(ActionEvent actionEvent) {
        if(imeField.getText().isEmpty() || zanrField.getText().isEmpty() || trajanjeField.getText().isEmpty() || cijenaField.getText().isEmpty() || brojsaleField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Odaberite film!");
            alert.setContentText("Niti jedan film nije odabran.");
            alert.showAndWait();
            return;
        }
        Film f = new Film();
        FilmDao fd = new FilmDaoSQLImpl();
        f.setId(id);
        f.setIme(imeField.getText());
        f.setZanr(zanrField.getText());
        f.setTrajanje(Integer.parseInt(trajanjeField.getText()));
        f.setCijena(Integer.parseInt(cijenaField.getText()));
        f.setBroj_sale(Integer.parseInt(brojsaleField.getText()));
        fd.update(f);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Uređivanje uspješno!");
        alert.setHeaderText(null);
        alert.setContentText("Film je uređen!");
        UpdateTable();
        alert.showAndWait();
    }

    public void obrisiButtonClick(ActionEvent actionEvent) {
        if(imeField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Odaberite film!");
            alert.setContentText("Niti jedan film nije odabran.");
            alert.showAndWait();
            return;
        }
        FilmDao fd = new FilmDaoSQLImpl();
        Film film = fd.getByIme(imeField.getText());
        fd.delete(film.getId());
        UpdateTable();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Brisanje uspješno");
        alert.setHeaderText(null);
        alert.setContentText("Odabrani film je obrisan!");
        alert.showAndWait();
    }

    public void getSelected(MouseEvent mouseEvent) {
        int index = tabelaFilmova.getSelectionModel().getSelectedIndex();
        if(index <= -1) return;
        id = Integer.valueOf(colID.getCellData(index).toString());
        imeField.setText(colIme.getCellData(index).toString());
        zanrField.setText(colZanr.getCellData(index).toString());
        trajanjeField.setText(colTrajanje.getCellData(index).toString());
        cijenaField.setText(colCijena.getCellData(index).toString());
        brojsaleField.setText(colBrojSale.getCellData(index).toString());
    }

    public void UpdateTable(){
        colID.setCellValueFactory(new PropertyValueFactory<Film, Integer>("id"));
        colIme.setCellValueFactory(new PropertyValueFactory<Film, String>("ime"));
        colZanr.setCellValueFactory(new PropertyValueFactory<Film, String>("zanr"));
        colTrajanje.setCellValueFactory(new PropertyValueFactory<Film, Integer>("trajanje"));
        colCijena.setCellValueFactory(new PropertyValueFactory<Film, Integer>("cijena"));
        colBrojSale.setCellValueFactory(new PropertyValueFactory<Film, Integer>("broj_sale"));
        FilmDao fd = new FilmDaoSQLImpl();
        List<Film> filmovi = fd.getAll();
        ObservableList<Film> f = FXCollections.observableArrayList(filmovi);
        tabelaFilmova.setItems(f);
        tabelaFilmova.refresh();
    }

    @FXML
    public void initialize(){
        UpdateTable();
    }

    public void nazadButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/AdminPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        AdminPageController apc = fxmlLoader.getController();
        stage.setResizable(false);
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/3418/3418886.png"));
        stage.setTitle("Admin page");
        stage.setScene(scene);
        stage.show();
        Node n = (Node) actionEvent.getSource();
        Stage stage2 = (Stage) n.getScene().getWindow();
        stage2.close();
    }
}
