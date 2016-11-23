package antywirusTabele;

/**
 * Created by Redbullek on 2016-11-22.
 */
public class TabelaAntywir {
        private Long id_nazwa;
        private String nazwaAntywir;
        private String opis;
        private double ocena;
        private Long idpakiet;

        public Long getid_nazwa() {
            return id_nazwa;
        }

        public void setid_nazwa(Long id_nazwa) {
            this.id_nazwa = id_nazwa;
        }

        public String getNazwaAntywir() {
            return nazwaAntywir;
        }

        public void setNazwaAntywir(String nazwaAntywir) {
            this.nazwaAntywir = nazwaAntywir;
        }

        public String getOpis() {
            return opis;
        }

        public void setOpis(String opis) {
            this.opis = opis;
        }

        public double getocena() {
            return ocena;
        }

        public void setocena(int ocena) {
            this.ocena = ocena;
        }

        public Long getidpakiet() {
            return idpakiet;
        }

        public void setidpakiet(Long idpakiet) {
            this.idpakiet = idpakiet;
        }
}
