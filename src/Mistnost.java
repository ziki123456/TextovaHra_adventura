import java.util.ArrayList;
import java.util.List;

public class Mistnost {
        private int id;
        private String nazev;
        private int idSever;
        private int idZapad;
        private int idJih;
        private int idVychod;

    List<Vec> veci = new ArrayList<Vec>();
    List<Clovek> lidi = new ArrayList<Clovek>();

        public int getId() {
            return id;
        }

        public int getIdSever() {
            return idSever;
        }

        public int getIdZapad() {
            return idZapad;
        }

        public int getIdJih() {
            return idJih;
        }

        public int getIdVychod() {
            return idVychod;
        }

        public Mistnost(String[] pole) {
            this.id = Integer.parseInt(pole[0]);
            this.nazev = pole[1];
            this.idSever = Integer.parseInt(pole[2]);
            this.idZapad = Integer.parseInt(pole[3]);
            this.idJih = Integer.parseInt(pole[4]);
            this.idVychod = Integer.parseInt(pole[5]);
        }

        public String getNazev() {
            return nazev;
        }
    public boolean jdeJitSmerem(String smer) {
        if (smer.equals("S"))   return idSever >= 0;
        else if (smer.equals("J"))   return idJih >= 0;
        else if (smer.equals("Z"))   return idZapad >= 0;
        else if (smer.equals("V"))   return idVychod >= 0;
        else return false;
    }
    public Integer vratIdMistnostSmerem(String smer) {
        if (smer.equals("S"))   return this.getIdSever();
        else if (smer.equals("J"))   return this.getIdJih();
        else if (smer.equals("Z"))   return this.getIdZapad();
        else if (smer.equals("V"))   return this.getIdVychod();
        else return this.getId();
    }
    public void pridejPredmet(Vec vec) {
        veci.add(vec);
    }

    public void odeberPredmet(Vec vec) {
        veci.remove(vec);
    }
}

