public class Clovek {
    private int id;
    private String nazev;
    private Vec pozadovanaVec;
    private int idPozadovaneVeci;
    private boolean jeSpokojen = false;

    private String popis;

    public static int pocetPostav = 0;
    public static int pocetSpokojenychPostav = 0;


    public Clovek(String[] pole) {
        this.id = Integer.parseInt(pole[0]);
        this.nazev = pole[2];
        this.idPozadovaneVeci  =  Integer.parseInt(pole[6]);
        this.popis = pole[5];
        //this.mistnost = mistnost;
        this.pocetPostav++;
    }


    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public boolean jeSpokojen ()  {
        return jeSpokojen;
    }
    public String getNazev() {
        return nazev;
    }

    public int getIdPozadovaneVeci() {
        return idPozadovaneVeci;
    }

    public void setPozadovanaVec(Vec vec) {
        this.pozadovanaVec = vec;
    }

    public Vec getPozadovanaVec() {
        return this.pozadovanaVec;
    }

    public boolean obdrzelVec()  {
        jeSpokojen  = true;
        pocetSpokojenychPostav++;
        if (pocetSpokojenychPostav==pocetPostav) return true;
        else return false;
    }
    public String chceVec() {
        return pozadovanaVec.getNazev();
    }
}
