package ba.unsa.etf.rpr.dao;

/**
 * The type Dao factory.
 */
public class DaoFactory {
    private static final FilmDao filmDao = FilmDaoSQLImpl.getInstance();
    private static final KartaDao kartaDao = KartaDaoSQLImpl.getInstance();
    private static final UsersDao usersDao = UsersDaoSQLImpl.getInstance();

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
