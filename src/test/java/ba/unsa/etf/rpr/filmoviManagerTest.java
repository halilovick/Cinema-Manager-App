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

    /**
     * Deleting film that has a ticket related to it
     *
     * @throws FilmoviException the filmovi exception
     */
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

    /**
     * Adding film that already has an id assigned.
     */
    @Test
    void addingFilmWithIdTest() {
        Film f = new Film();
        f.setId(10);
        f.setIme("Test");
        Assertions.assertThrows(FilmoviException.class, () -> {
            fm.add(f);
        });
    }

    /**
     * Adding film to database test.
     *
     * @throws FilmoviException the filmovi exception
     */
    @Test
    void addFilmTest() throws FilmoviException {
        Film f = new Film();
        f.setIme("Test");
        fm.add(f);
        List<Film> films = fm.getAll();
        boolean isAdded = false;
        for (Film fi : films) {
            if (f.equals(fi)) isAdded = true;
        }
        Assertions.assertTrue(isAdded);
        fm.delete(f.getId());
    }

    /**
     * Deleting film from database test.
     *
     * @throws FilmoviException the filmovi exception
     */
    @Test
    void deleteFilmTest() throws FilmoviException {
        Film f = new Film();
        f.setIme("Test");
        fm.add(f);
        fm.delete(f.getId());
        List<Film> films = fm.getAll();
        boolean isDeleted = true;
        for (Film fi : films) {
            if (f.equals(fi)) isDeleted = false;
        }
        Assertions.assertTrue(isDeleted);
    }
}
