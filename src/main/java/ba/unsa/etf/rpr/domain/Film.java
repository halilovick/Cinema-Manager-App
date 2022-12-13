package ba.unsa.etf.rpr.domain;

public class Film {
    private int id;
    private String ime;
    private String zanr;
    private int trajanje;
    private int cijena;
    private int broj_sale;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    @Override
    public String toString() {
        return "Film{id=" + id + ", ime='" + ime + "\', zanr=" + zanr + ", trajanje=" + trajanje + "}" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id == film.id;
    }
}
