package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Film;
import ba.unsa.etf.rpr.exceptions.FilmoviException;

import java.util.List;

/**
 * The interface Film dao.
 */
public interface FilmDao extends Dao<Film>{
    /**
     * Gets all names.
     *
     * @return all Film names
     * @throws FilmoviException the filmovi exception
     */
    List<String> getAllNames() throws FilmoviException;

    /**
     * Gets by ime.
     *
     * @param ime the ime
     * @return the Film by ime
     * @throws FilmoviException the filmovi exception
     */
    Film getByIme(String ime) throws FilmoviException;
}