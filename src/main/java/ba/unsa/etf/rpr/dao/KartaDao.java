package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Karta;

public interface KartaDao extends Dao<Karta>{
    void resetIncrement();
    void deleteAll();
}
