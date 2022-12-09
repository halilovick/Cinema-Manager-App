package ba.unsa.etf.rpr;

import java.sql.Date;
import java.time.LocalDateTime;

public class Kupac {
    private int id;
    private String kupac_ime;
    private Date vrijeme_kupovine;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKupac_ime() {
        return kupac_ime;
    }

    public void setKupac_ime(String kupac_ime) {
        this.kupac_ime = kupac_ime;
    }

    public Date getVrijeme_kupovine() {
        return vrijeme_kupovine;
    }

    public void setVrijeme_kupovine(Date vrijeme_kupovine) {
        this.vrijeme_kupovine = vrijeme_kupovine;
    }
    @Override
    public String toString() {
        return "Kupac{id=" + id + ", ime=" + kupac_ime + ", vrijeme kupovine=" + vrijeme_kupovine + "}" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kupac kupac = (Kupac) o;
        return id == kupac.id;
    }
}
