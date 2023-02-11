package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Controllers.NapraviRacunController;
import ba.unsa.etf.rpr.business.usersManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.FilmoviException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;

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

    @Test
    void passwordStrengthTest() throws FilmoviException {
        usersManager mockU = Mockito.mock(usersManager.class);
        Mockito.when(mockU.getById(0)).thenReturn(new User("user", "TestPassword1!", "ime", false));
        Assertions.assertEquals("S", NapraviRacunController.passwordCheck(mockU.getById(0).getPassword()));
        Mockito.when(mockU.getById(0)).thenReturn(new User("user", "testpassword", "ime", false));
        Assertions.assertEquals("M", NapraviRacunController.passwordCheck(mockU.getById(0).getPassword()));
        Mockito.when(mockU.getById(0)).thenReturn(new User("user", "test", "ime", false));
        Assertions.assertEquals("W", NapraviRacunController.passwordCheck(mockU.getById(0).getPassword()));
    }

    @Test
    void dobCheckTest() throws FilmoviException {
        usersManager mockU = Mockito.mock(usersManager.class);
        Mockito.when(mockU.getById(0)).thenReturn(new User("user", "password", "ime", "email@gmail.com", "Kosevo", "Sarajevo", Date.valueOf("2005-10-09"), false));
        Assertions.assertTrue(NapraviRacunController.dateOfBirthCheck(mockU.getById(0).getDatum_rodjenja()));
        Mockito.when(mockU.getById(0)).thenReturn(new User("user", "password", "ime", "email@gmail.com", "Kosevo", "Sarajevo", Date.valueOf("1989-11-03"), false));
        Assertions.assertTrue(NapraviRacunController.dateOfBirthCheck(mockU.getById(0).getDatum_rodjenja()));
        Mockito.when(mockU.getById(0)).thenReturn(new User("user", "password", "ime", "email@gmail.com", "Kosevo", "Sarajevo", Date.valueOf("2012-10-09"), false));
        Assertions.assertFalse(NapraviRacunController.dateOfBirthCheck(mockU.getById(0).getDatum_rodjenja()));
        Mockito.when(mockU.getById(0)).thenReturn(new User("user", "password", "ime", "email@gmail.com", "Kosevo", "Sarajevo", Date.valueOf("2050-11-03"), false));
        Assertions.assertFalse(NapraviRacunController.dateOfBirthCheck(mockU.getById(0).getDatum_rodjenja()));
    }
}
