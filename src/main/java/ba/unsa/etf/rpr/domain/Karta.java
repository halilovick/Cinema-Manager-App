package ba.unsa.etf.rpr.domain;

public class Karta {
    private int id;
    private User user;
    private Film film;
    private int cijena;
    private int broj_sale;

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

    public int getCijena() {
        return cijena;
    }

    public void setCijena(int cijena) {
        this.cijena = cijena;
    }

    public int getBroj_sale() {
        return broj_sale;
    }

    public void setBroj_sale(int broj_sale) {
        this.broj_sale = broj_sale;
    }

    @Override
    public String toString() {
        return "Karta{id=" + id + ", id kupca=" + user.getId() + ", id filma=" + film.getId() + ", cijena=" + cijena + ", broj sale=" + broj_sale + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Karta karta = (Karta) o;
        return id == karta.id;
    }
}
