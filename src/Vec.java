public class Vec {
    private int id;
    private String nazev;
    private Mistnost mistnost;
    private Clovek drzitel;

    public Vec(String[] pole) {
        this.id = Integer.parseInt(pole[0]);
        this.nazev = pole[2];
        //this.mistnost = mistnost;
        //this.drzitel = drzitel;
    }

    public String getNazev() {
        return nazev;
    }

    public int getId() {
        return id;
    }
}
