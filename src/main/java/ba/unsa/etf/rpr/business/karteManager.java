package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Karta;
import ba.unsa.etf.rpr.exceptions.FilmoviException;

import java.util.List;

public class karteManager {
    private boolean kartaExists(int id) throws FilmoviException {
        karteManager km = new karteManager();
        try {
            Karta k = km.getById(id);
        } catch (FilmoviException f) {
            return false;
        }
        return true;
    }

    public Karta add(Karta k) throws FilmoviException {
        try {
            return DaoFactory.kartaDao().add(k);
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    public void delete(int kartaId) throws FilmoviException {
        if (!kartaExists(kartaId)) throw new FilmoviException("Karta does not exist!");
        try {
            DaoFactory.kartaDao().delete(kartaId);
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    public Karta update(Karta karta) throws FilmoviException {
        return DaoFactory.kartaDao().update(karta);
    }

    public List<Karta> getAll() throws FilmoviException {
        return DaoFactory.kartaDao().getAll();
    }

    public Karta getById(int id) throws FilmoviException {
        return DaoFactory.kartaDao().getById(id);
    }

    public void deleteAll() throws FilmoviException {
        DaoFactory.kartaDao().deleteAll();
    }

    public void deleteWithFilmId(int id) throws FilmoviException {
        DaoFactory.kartaDao().deleteWithFilmId(id);
    }

    public void deleteWithUserId(int id) throws FilmoviException {
        DaoFactory.kartaDao().deleteWithUserId(id);
    }
}
