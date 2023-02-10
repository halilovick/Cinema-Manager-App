package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.usersManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.FilmoviException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class usersManagerTest {
    usersManager um = new usersManager();

    @Test
    void uniqueUsernameTest() throws FilmoviException {
        um.add(new User("unique", "pass", "u", false));
        Assertions.assertThrows(FilmoviException.class, () -> {
            um.add(new User("unique", "123", "uq", false));
        });
        um.delete(um.getByUsername("unique").getId());
    }

    @Test
    void addUserTest() throws FilmoviException {
        User u = new User();
        u.setUser("Test");
        u.setPassword("Test1");
        um.add(u);
        boolean exists = false;
        if (um.userExists("Test")) exists = true;
        Assertions.assertTrue(exists);
        um.delete(u.getId());
    }
}
