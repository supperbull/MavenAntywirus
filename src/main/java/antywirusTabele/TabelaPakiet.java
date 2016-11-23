package antywirusTabele;

/**
 * Created by Redbullek on 2016-11-22.
 */
public class TabelaPakiet {

    private Long id_pakiet;
    private String pakiet;
    private String opis;
    private double cena;

    public Long getid_pakiet() {
        return id_pakiet;
    }

    public void setid_pakiet(Long id_pakiet) {
        this.id_pakiet = id_pakiet;
    }

    public String getpakiet() {
        return pakiet;
    }

    public void setpakiet(String pakiet) {
        this.pakiet = pakiet;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getcena() {
        return cena;
    }

    public void setcena(double cena) {
        this.cena = cena;
    }

}
