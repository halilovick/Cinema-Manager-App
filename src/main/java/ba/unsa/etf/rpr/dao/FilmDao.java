package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Film;

import java.util.List;

public interface FilmDao extends Dao<Film>{
    List<Film> searchByTrajanje(int a, int b);
    List<Film> searchByZanr(String zanr);
    List<String> getAllNames();
}