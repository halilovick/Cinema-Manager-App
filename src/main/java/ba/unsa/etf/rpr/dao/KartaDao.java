package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Karta;
import ba.unsa.etf.rpr.exceptions.FilmoviException;

public interface KartaDao extends Dao<Karta> {
    public void deleteAll() throws FilmoviException;

    public void deleteWithFilmId(int film_id) throws FilmoviException;
}
