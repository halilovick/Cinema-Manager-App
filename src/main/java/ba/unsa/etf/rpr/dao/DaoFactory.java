package ba.unsa.etf.rpr.dao;

public class DaoFactory {
    private static final FilmDao filmDao = new FilmDaoSQLImpl();
    private static final KartaDao kartaDao = new KartaDaoSQLImpl();
    private static final UsersDao usersDao = new UsersDaoSQLImpl();

    private DaoFactory() {

    }

    public static FilmDao filmDao() {
        return filmDao;
    }

    public static KartaDao kartaDao() {
        return kartaDao;
    }

    public static UsersDao usersDao() {
        return usersDao;
    }
}
