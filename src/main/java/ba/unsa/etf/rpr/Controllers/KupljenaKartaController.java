package ba.unsa.etf.rpr.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class KupljenaKartaController {
    public Label imeFilma;
    public Label datumFilma;
    public Label cijenaKarte;
    public Button zatvoriButton;

    public void zatvoriButtonClick(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
