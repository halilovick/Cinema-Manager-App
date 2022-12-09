package ba.unsa.etf.rpr;

import java.util.List;

public class App {
    public static void main(String[] args) {
        KartaDao dao = new KartaDaoSQLImpl();
        List<Karta> karte = dao.getAll();
        System.out.println(karte);
    }
}
