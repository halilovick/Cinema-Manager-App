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

    @Test
    void deleteUserTest() throws FilmoviException {
        User u = new User();
        u.setUser("Test");
        u.setPassword("Test1");
        um.add(u);
        um.delete(u.getId());
        boolean exists = true;
        if (um.userExists("Test")) exists = false;
        Assertions.assertTrue(exists);
    }

    @Test
    void getLoggedInTest() throws FilmoviException {
        Assertions.assertEquals(0, um.getLoggedInId("doesnt_exist", "doesnt_exist!"));
    }

    @Test
    void getByUsernameTest() throws FilmoviException {
        User u1 = new User();
        u1.setUser("user1");
        um.add(u1);
        User u2 = um.getByUsername("user1");
        Assertions.assertEquals(u1, u2);
        um.delete(u1.getId());
    }
}
