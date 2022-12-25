package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Film;
import ba.unsa.etf.rpr.exceptions.FilmoviException;

import java.util.List;

public interface FilmDao extends Dao<Film>{
    List<Film> searchByTrajanje(int a, int b) throws FilmoviException;
    List<Film> searchByZanr(String zanr) throws FilmoviException;
    List<String> getAllNames() throws FilmoviException;
    Film getByIme(String ime) throws FilmoviException;
}