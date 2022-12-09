package ba.unsa.etf.rpr;

import java.util.List;

public class App {
    public static void main(String[] args) {
        /*KartaDao dao = new KartaDaoSQLImpl();
        List<Karta> karte = dao.getAll();
        System.out.println(karte);*/
        /*FilmDao dao = new FilmDaoSQLImpl();
        List<Film> filmovi = dao.getAll();
        System.out.println(filmovi);*/
        KupacDao dao = new KupacDaoSQLImpl();
        List<Kupac> kupci = dao.getAll();
        System.out.println(kupci);
    }
}
