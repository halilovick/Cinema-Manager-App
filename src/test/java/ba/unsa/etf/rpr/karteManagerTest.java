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

class karteManagerTest {
    filmoviManager fm = new filmoviManager();
    karteManager km = new karteManager();
    usersManager um = new usersManager();

    /**
     * Deleting ticket with incorrect id.
     *
     * @throws FilmoviException the filmovi exception
     */
    @Test
    void deletingTicketWithoutId() {
        Assertions.assertThrows(FilmoviException.class, () -> {
            km.delete(1000);
        });
    }

    /**
     * Adding unexisting film to ticket.
     *
     * @throws FilmoviException the filmovi exception
     */
    @Test
    void unexistingFilmToTicketTest() {
        Film f = new Film();
        f.setIme("Test");
        User u = new User();
        u.setIme("TestIme");
        Karta k = new Karta();
        k.setFilm(f);
        k.setUser(u);
        Assertions.assertThrows(FilmoviException.class, () -> {
            km.add(k);
        });
    }
}
