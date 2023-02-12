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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static ba.unsa.etf.rpr.Controllers.LoginController.user;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AdminPageController {
    public TableColumn<User, Integer> colID1;
    public TableColumn<User, String> colUser1;
    public TableColumn<User, String> colPassword1;
    public TableColumn<User, String> colIme1;
    public TableColumn<User, String> colEmail1;
    public TableColumn<User, String> colAdresa1;
    public TableColumn<User, String> colGrad1;
    public TableColumn<User, Date> colDatumRodj1;
    public TableColumn<User, Boolean> colAdmin1;
    public TextField userField1;
    public TextField passwordField1;
    public TextField imeField1;
    public TextField emailField1;
    public TextField adminField1;
    public Button dodajButton1;
    public Button azurirajButton1;
    public Button obrisiButton1;
    public Button nazadButton21;
    public TableView<User> tabelaUsera;
    public Label cijenaLabelFiksna;
    public Label trajanjeLabelFiksna;
    public Label zanrLabelFiksna;
    public Tab prodajaKarataAdminButton;
    private final filmoviManager fmanager = new filmoviManager();
    private final karteManager kmanager = new karteManager();
    private final List<String> listaFilmova = fmanager.getAllNames();
    private final ObservableList<String> filmovi = FXCollections.observableArrayList(listaFilmova);
    public ChoiceBox<String> filmChoiceBox;
    public Label zanrLabel;
    public Label trajanjeLabel;
    public Label cijenaLabel;
    public TextField brojKarataTextField;
    public DatePicker odabirDatuma;
    private LocalDate datum;
    String imeOdabranogFilma = "";
    private Integer id;
    public TableColumn<Film, Integer> colID;
    public TableColumn<Film, String> colIme;
    public TableColumn<Film, String> colZanr;
    public TableColumn<Film, Integer> colTrajanje;
    public TableColumn<Film, Integer> colCijena;
    public TableColumn<Film, Integer> colBrojSale;
    public TableView<Film> tabelaFilmova;
    private final usersManager umanager = new usersManager();
    public TextField imeField;
    public TextField zanrField;
    public TextField trajanjeField;
    public TextField cijenaField;
    public TextField brojsaleField;

    public AdminPageController() throws FilmoviException {
    }

    /**
     * Dodaj film button click. Adds film to database.
     *
     * @throws FilmoviException the filmovi exception
     */
    public void dodajFilmButtonClick() throws FilmoviException {
        if (imeField.getText().isEmpty() || zanrField.getText().isEmpty() || trajanjeField.getText().isEmpty() || cijenaField.getText().isEmpty() || brojsaleField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid details");
            alert.setContentText("Please enter valid details!");
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
            fmanager.add(f);
            UpdateFilmoviTable();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("Film has been successfully added");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid details");
            alert.setContentText("Please enter valid details!");
            alert.showAndWait();
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    /**
     * Azuriraj film button click. Updates film in database.
     *
     * @throws FilmoviException the filmovi exception
     */
    public void azurirajFilmButtonClick() throws FilmoviException {
        if (imeField.getText().isEmpty() || zanrField.getText().isEmpty() || trajanjeField.getText().isEmpty() || cijenaField.getText().isEmpty() || brojsaleField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Choose a film!");
            alert.setContentText("A film has not been chosen");
            alert.showAndWait();
            return;
        }
        Film f = new Film();
        f.setId(id);
        f.setIme(imeField.getText());
        f.setZanr(zanrField.getText());
        f.setTrajanje(Integer.parseInt(trajanjeField.getText()));
        f.setCijena(Integer.parseInt(cijenaField.getText()));
        f.setBroj_sale(Integer.parseInt(brojsaleField.getText()));
        fmanager.update(f);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText(null);
        alert.setContentText("Film has been successfully updated");
        UpdateFilmoviTable();
        alert.showAndWait();
    }

    /**
     * Obrisi film button click. Deletes film in database.
     *
     * @throws FilmoviException the filmovi exception
     */
    public void obrisiFilmButtonClick() throws FilmoviException {
        if (imeField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Choose a film!");
            alert.setContentText("A film has not been chosen");
            alert.showAndWait();
            return;
        }
        kmanager.deleteWithFilmId(fmanager.getByIme(imeField.getText()).getId());
        fmanager.delete(fmanager.getByIme(imeField.getText()).getId());
        UpdateFilmoviTable();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText(null);
        alert.setContentText("Film has been successfully deleted");
        alert.showAndWait();
    }

    /**
     * Gets selected film.
     */
    public void getSelectedFilm() {
        int index = tabelaFilmova.getSelectionModel().getSelectedIndex();
        if (index <= -1) return;
        id = Integer.valueOf(colID.getCellData(index).toString());
        imeField.setText(colIme.getCellData(index));
        zanrField.setText(colZanr.getCellData(index));
        trajanjeField.setText(colTrajanje.getCellData(index).toString());
        cijenaField.setText(colCijena.getCellData(index).toString());
        brojsaleField.setText(colBrojSale.getCellData(index).toString());
    }

    /**
     * Update filmovi table. Updates table containing films.
     *
     * @throws FilmoviException the filmovi exception
     */
    public void UpdateFilmoviTable() throws FilmoviException {
        colID.setCellValueFactory(new PropertyValueFactory<Film, Integer>("id"));
        colIme.setCellValueFactory(new PropertyValueFactory<Film, String>("ime"));
        colZanr.setCellValueFactory(new PropertyValueFactory<Film, String>("zanr"));
        colTrajanje.setCellValueFactory(new PropertyValueFactory<Film, Integer>("trajanje"));
        colCijena.setCellValueFactory(new PropertyValueFactory<Film, Integer>("cijena"));
        colBrojSale.setCellValueFactory(new PropertyValueFactory<Film, Integer>("broj_sale"));
        List<Film> filmovi = fmanager.getAll();
        ObservableList<Film> f = FXCollections.observableArrayList(filmovi);
        tabelaFilmova.setItems(f);
        tabelaFilmova.refresh();
    }

    /**
     * Update prodaja table. Updates films available for ticket selling.
     */
    public void UpdateProdajaTable() {
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

    /**
     * Initializing the screens.
     *
     * @throws FilmoviException the filmovi exception
     */
    @FXML
    public void initialize() throws FilmoviException {
        UpdateProdajaTable();
        UpdateFilmoviTable();
        UpdateUseriTable();
        initializeDatePicker();
    }

    /**
     * Initializing the date picker.
     */
    private void initializeDatePicker() {
        odabirDatuma.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        odabirDatuma.setDayCellFactory(dayCellFactory);
    }

    /**
     * Nazad button click. Goes back to log in screen.
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
     * Dodaj user button click. Adds user to database.
     *
     * @param actionEvent the action event
     * @throws FilmoviException the filmovi exception
     */
    public void dodajUserButtonClick(ActionEvent actionEvent) throws FilmoviException {
        if (userField1.getText().isEmpty() || passwordField1.getText().isEmpty() || imeField1.getText().isEmpty() || emailField1.getText().isEmpty() || adminField1.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid details");
            alert.setContentText("Please enter valid details.");
            alert.showAndWait();
            return;
        }
        try {
            User u = new User();
            u.setUser(userField1.getText());
            u.setPassword(passwordField1.getText());
            u.setIme(imeField1.getText());
            u.setEmail(emailField1.getText());
            u.setAdmin(Boolean.parseBoolean(adminField1.getText()));
            umanager.add(u);
            UpdateUseriTable();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("User successfully added");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid details");
            alert.setContentText("Please enter valid details.");
            alert.showAndWait();
        } catch (FilmoviException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Cannot add an existing user!");
            alert.showAndWait();
            return;
        }
    }

    /**
     * Azuriraj user button click. Updates user in database.
     *
     * @throws FilmoviException the filmovi exception
     */
    public void azurirajUserButtonClick() throws FilmoviException {
        if (userField1.getText().isEmpty() || passwordField1.getText().isEmpty() || imeField1.getText().isEmpty() || emailField1.getText().isEmpty() || adminField1.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Choose a user!");
            alert.setContentText("A user has not been chosen!");
            alert.showAndWait();
            return;
        }
        User u = new User();
        u.setId(id);
        u.setUser(userField1.getText());
        u.setPassword(passwordField1.getText());
        u.setIme(imeField1.getText());
        u.setEmail(emailField1.getText());
        u.setAdresa(umanager.getById(id).getAdresa());
        u.setGrad(umanager.getById(id).getGrad());
        u.setDatum_rodjenja(umanager.getById(id).getDatum_rodjenja());
        u.setAdmin(Boolean.parseBoolean(adminField1.getText()));
        umanager.update(u);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText(null);
        alert.setContentText("User successfully updated!");
        UpdateUseriTable();
        alert.showAndWait();
    }

    /**
     * Obrisi user button click. Deletes user in database.
     *
     * @param actionEvent the action event
     * @throws FilmoviException the filmovi exception
     */
    public void obrisiUserButtonClick(ActionEvent actionEvent) throws FilmoviException {
        if (userField1.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Choose a film");
            alert.setContentText("A film has not been chosen!");
            alert.showAndWait();
            return;
        }
        kmanager.deleteWithUserId(id);
        umanager.delete(umanager.getLoggedInId(userField1.getText(), passwordField1.getText()));
        UpdateUseriTable();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText(null);
        alert.setContentText("User has been successfully deleted.");
        alert.showAndWait();
    }

    /**
     * Gets selected user.
     *
     * @param mouseEvent the mouse event
     */
    public void getSelectedUser(MouseEvent mouseEvent) {
        int index = tabelaUsera.getSelectionModel().getSelectedIndex();
        if (index <= -1) return;
        id = Integer.valueOf(colID1.getCellData(index).toString());
        userField1.setText(colUser1.getCellData(index));
        passwordField1.setText(colPassword1.getCellData(index));
        imeField1.setText(colIme1.getCellData(index));
        emailField1.setText(colEmail1.getCellData(index));
        adminField1.setText(colAdmin1.getCellData(index).toString());
    }

    /**
     * Update useri table. Updates existing users.
     *
     * @throws FilmoviException the filmovi exception
     */
    public void UpdateUseriTable() throws FilmoviException {
        colID1.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        colUser1.setCellValueFactory(new PropertyValueFactory<User, String>("user"));
        colPassword1.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        colIme1.setCellValueFactory(new PropertyValueFactory<User, String>("ime"));
        colEmail1.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        colAdresa1.setCellValueFactory(new PropertyValueFactory<User, String>("adresa"));
        colGrad1.setCellValueFactory(new PropertyValueFactory<User, String>("grad"));
        colDatumRodj1.setCellValueFactory(new PropertyValueFactory<User, Date>("datum_rodjenja"));
        colAdmin1.setCellValueFactory(new PropertyValueFactory<User, Boolean>("admin"));
        List<User> useri = umanager.getAll();
        ObservableList<User> u = FXCollections.observableArrayList(useri);
        tabelaUsera.setItems(u);
        tabelaUsera.refresh();
    }

    /**
     * Odabir datuma click. Converts chosen date to correct type.
     *
     * @param actionEvent the action event
     */
    public void odabirDatumaClick(ActionEvent actionEvent) {
        datum = odabirDatuma.getValue();
    }

    /**
     * Kupi button click. Adds ticket to database.
     *
     * @param actionEvent the action event
     * @throws FilmoviException the filmovi exception
     * @throws IOException      the io exception
     */
    public void kupiButtonClick(ActionEvent actionEvent) throws FilmoviException, IOException {
        int brojKarata = 0;
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
}
