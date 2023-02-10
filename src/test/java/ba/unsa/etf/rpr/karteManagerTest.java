package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.filmoviManager;
import ba.unsa.etf.rpr.business.karteManager;
import ba.unsa.etf.rpr.business.usersManager;
import ba.unsa.etf.rpr.exceptions.FilmoviException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class karteManagerTest {

    filmoviManager fm = new filmoviManager();
    karteManager km = new karteManager();
    usersManager um = new usersManager();

    @Test
    void deletingTicketWithoutId() {
        Assertions.assertThrows(FilmoviException.class, () -> {
            km.delete(1000);
        });
    }
}
