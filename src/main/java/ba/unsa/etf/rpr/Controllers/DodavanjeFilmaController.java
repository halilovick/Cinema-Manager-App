package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.dao.FilmDao;
import ba.unsa.etf.rpr.dao.FilmDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Film;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DodavanjeFilmaController {
    public Button dodajButton;
    public TextField imeFilmaTextField;
    public TextField zanrTextField;
    public TextField trajanjeTextField;
    public TextField cijenaTextField;
    public TextField brojSaleTextField;

    public void dodajButtonClick(ActionEvent actionEvent) {
        if(imeFilmaTextField.getText().isEmpty() || zanrTextField.getText().isEmpty() || trajanjeTextField.getText().isEmpty() || cijenaTextField.getText().isEmpty() || brojSaleTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Pogresni podaci");
            alert.setContentText("Uneseni su nevalidni podaci!");
            alert.showAndWait();
            return;
        }
        try {
            Film f = new Film();
            f.setIme(imeFilmaTextField.getText());
            f.setZanr(zanrTextField.getText());
            f.setTrajanje(Integer.parseInt(trajanjeTextField.getText()));
            f.setCijena(Integer.parseInt(cijenaTextField.getText()));
            f.setBroj_sale(Integer.parseInt(brojSaleTextField.getText()));
            FilmDao fd = new FilmDaoSQLImpl();
            fd.add(f);
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dodavanje uspješno");
            alert.setHeaderText(null);
            alert.setContentText("Film je dodan!");
            alert.showAndWait();
            stage.close();
        } catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Pogresni podaci");
            alert.setContentText("Uneseni su nevalidni podaci!");
            alert.showAndWait();
        }
    }
}
