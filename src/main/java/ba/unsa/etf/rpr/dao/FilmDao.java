package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Film;
import ba.unsa.etf.rpr.exceptions.FilmoviException;

import java.util.List;

public interface FilmDao extends Dao<Film>{
    List<String> getAllNames() throws FilmoviException;
    Film getByIme(String ime) throws FilmoviException;
}