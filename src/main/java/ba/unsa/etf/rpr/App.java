package ba.unsa.etf.rpr;

import java.util.List;

public class App {
    public static void main(String[] args) {
        FilmDao dao = new FilmDaoSQLImpl();
        List<Film> filmovi = dao.getAll();
        System.out.println(filmovi);
    }
}
