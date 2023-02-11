package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Karta;
import ba.unsa.etf.rpr.exceptions.FilmoviException;

import java.util.List;

/**
 * Manager class for karte
 */
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

    /**
     * Adds ticket to database
     *
     * @param k the ticket
     * @throws FilmoviException the filmovi exception
     */
    public Karta add(Karta k) throws FilmoviException {
        try {
            return DaoFactory.kartaDao().add(k);
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    /**
     * Deletes ticket from database
     *
     * @param kartaId the ticket id
     * @throws FilmoviException the filmovi exception
     */
    public void delete(int kartaId) throws FilmoviException {
        if (!kartaExists(kartaId)) throw new FilmoviException("Karta does not exist!");
        try {
            DaoFactory.kartaDao().delete(kartaId);
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    /**
     * Updates tickets in database.
     *
     * @param karta the ticket
     * @throws FilmoviException the filmovi exception
     */
    public Karta update(Karta karta) throws FilmoviException {
        return DaoFactory.kartaDao().update(karta);
    }

    /**
     * Gets all tickets from database.
     *
     * @return a list with tickets.
     * @throws FilmoviException the filmovi exception
     */
    public List<Karta> getAll() throws FilmoviException {
        return DaoFactory.kartaDao().getAll();
    }

    /**
     * Gets ticket by id.
     *
     * @param id the ticket id
     * @return the ticket
     * @throws FilmoviException the filmovi exception
     */
    public Karta getById(int id) throws FilmoviException {
        return DaoFactory.kartaDao().getById(id);
    }

    /**
     * Deletes tickets with related film id.
     *
     * @param id the film id
     * @throws FilmoviException the filmovi exception
     */
    public void deleteWithFilmId(int id) throws FilmoviException {
        DaoFactory.kartaDao().deleteWithFilmId(id);
    }

    /**
     * Deletes tickets with related user id.
     *
     * @param id the user id
     * @throws FilmoviException the filmovi exception
     */
    public void deleteWithUserId(int id) throws FilmoviException {
        DaoFactory.kartaDao().deleteWithUserId(id);
    }
}
