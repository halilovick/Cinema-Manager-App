package ba.unsa.etf.rpr.domain;

public class Karta implements Idable {
    private int id;
    private User user;
    private Film film;

    public Karta() {
    }

    public Karta(User user, Film film) {
        this.user = user;
        this.film = film;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return "Karta{id=" + id + ", id kupca=" + user.getId() + ", id filma=" + film.getId() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Karta karta = (Karta) o;
        return id == karta.id;
    }
}
