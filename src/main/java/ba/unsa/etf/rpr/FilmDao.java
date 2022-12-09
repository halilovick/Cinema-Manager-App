package ba.unsa.etf.rpr;

import java.util.List;

public interface FilmDao extends Dao<Film>{
    List<Film> searchByTrajanje(int a, int b);
    List<Film> searchByZanr(String zanr);
}