package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Karta;
import ba.unsa.etf.rpr.exceptions.FilmoviException;

public interface KartaDao extends Dao<Karta> {
    void deleteAll() throws FilmoviException;
}
