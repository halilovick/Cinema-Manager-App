package ba.unsa.etf.rpr;

public class Karta {
    private int id;
    private String ime_filma;
    private int kupac_id;
    private int film_id;
    private int cijena;
    private int broj_sale;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme_filma() {
        return ime_filma;
    }

    public void setIme_filma(String ime_filma) {
        this.ime_filma = ime_filma;
    }

    public int getKupac_id() {
        return kupac_id;
    }

    public void setKupac_id(int kupac_id) {
        this.kupac_id = kupac_id;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
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
        return "Karta{id=" + id + ", ime filma='" + ime_filma + "\', id kupca=" + kupac_id + ", id filma=" + film_id + ", cijena=" + cijena + ", broj sale=" + broj_sale + "}" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Karta karta = (Karta) o;
        return id == karta.id;
    }
}
