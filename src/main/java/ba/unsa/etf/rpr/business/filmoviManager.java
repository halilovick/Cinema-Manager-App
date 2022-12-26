package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Film;
import ba.unsa.etf.rpr.exceptions.FilmoviException;

import java.util.List;

public class filmoviManager {
    public Film add(Film f) throws FilmoviException {
        try {
            return DaoFactory.filmDao().add(f);
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    public void delete(int filmId) throws FilmoviException {
        try {
            DaoFactory.filmDao().delete(filmId);
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    public Film update(Film film) throws FilmoviException {
        return DaoFactory.filmDao().update(film);
    }

    public List<Film> getAll() throws FilmoviException {
        return DaoFactory.filmDao().getAll();
    }

    public Film getByIme(String ime) throws FilmoviException {
        return DaoFactory.filmDao().getByIme(ime);
    }

    public List<String> getAllNames() throws FilmoviException {
        return DaoFactory.filmDao().getAllNames();
    }

    public List<Film> searchByTrajanje(int a, int b) throws FilmoviException {
        return DaoFactory.filmDao().searchByTrajanje(a, b);
    }

    public List<Film> searchByZanr(String zanr) throws FilmoviException {
        return DaoFactory.filmDao().searchByZanr(zanr);
    }
}
