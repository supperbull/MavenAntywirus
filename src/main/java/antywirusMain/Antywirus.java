package antywirusMain;

/**
 * Created by Redbullek on 2016-11-22.
 */
public class Antywirus {

        private Long id_nazwa;
        private String nazwaAntywir;
        private String opis;
        private int ocena;
        private Long IdPakiet;

        public Long getId_nazwa() {
            return id_nazwa;
        }

        public void setId_nazwa(Long id_nazwa) {
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

        public int getocena() {
            return ocena;
        }

        public void setocena(int ocena) {
            this.ocena = ocena;
        }

        public Long getIdPakiet() {
            return IdPakiet;
        }

        public void setIdPakiet(Long IdPakiet) {
            this.IdPakiet = IdPakiet;
        }


    }
