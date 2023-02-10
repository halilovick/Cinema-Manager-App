package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.filmoviManager;
import ba.unsa.etf.rpr.business.karteManager;
import ba.unsa.etf.rpr.business.usersManager;
import ba.unsa.etf.rpr.domain.Film;
import ba.unsa.etf.rpr.domain.Karta;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.FilmoviException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class filmoviManagerTest {

    filmoviManager fm = new filmoviManager();
    karteManager km = new karteManager();
    usersManager um = new usersManager();

    @Test
    void deletingFilmWithRelatedTicketTest() throws FilmoviException {
        Film f = new Film();
        f.setIme("Test");
        fm.add(f);
        User u = new User();
        u.setIme("Ime");
        um.add(u);
        Karta k = new Karta();
        k.setUser(u);
        k.setFilm(f);
        km.add(k);
        Assertions.assertThrows(FilmoviException.class, () -> {
            fm.delete(f.getId());
        });
        km.delete(k.getId());
        fm.delete(f.getId());
        um.delete(u.getId());
    }

    @Test
    void addingFilmWithIdTest() {
        Film f = new Film();
        f.setId(10);
        f.setIme("Test");
        Assertions.assertThrows(FilmoviException.class, () -> {
            fm.add(f);
        });
    }

    @Test
    void addFilmTest() throws FilmoviException {
        Film f = new Film();
        f.setIme("Test");
        fm.add(f);
        List<Film> films = fm.getAll();
        boolean found = false;
        for (Film fi : films) {
            if (f.equals(fi)) found = true;
        }
        Assertions.assertTrue(found);
        fm.delete(f.getId());
    }
}
