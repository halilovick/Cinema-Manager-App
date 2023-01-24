package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Karta;
import ba.unsa.etf.rpr.exceptions.FilmoviException;

/**
 * The interface Karta dao.
 */
public interface KartaDao extends Dao<Karta> {
    /**
     * Delete all tickets.
     *
     * @throws FilmoviException the filmovi exception
     */
    void deleteAll() throws FilmoviException;

    /**
     * Delete all tickets with film id.
     *
     * @param film_id the film id
     * @throws FilmoviException the filmovi exception
     */
    void deleteWithFilmId(int film_id) throws FilmoviException;

    void deleteWithUserId(int user_id) throws FilmoviException;
}
