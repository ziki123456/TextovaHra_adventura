public class Postava {
    private Mistnost aktMistnost;
    public Batoh batoh;



    public Postava(Mistnost aktMistnost) {
        this.aktMistnost = aktMistnost;
        this.batoh = new Batoh();

    }
    public Mistnost getAktMistnost() {
        return aktMistnost;
    }

    public void setAktMistnost(Mistnost aktMistnost) {
        this.aktMistnost = aktMistnost;
    }

    public String vypisVeci() {
        String text = "";
        if (batoh.obsah.size() == 0) text += "V batohu nemáš žádné věci." + "\r\n";
        else {
            text += "V batohu máš tyto veci: ";
            for (int i = 0; i < batoh.obsah.size(); i++) {
                Vec item = batoh.obsah.get(i);
                text += i + " - " + item.getNazev() + ", ";
            }
            text = text.substring(0,text.length()-2);
        }
        return text;
    }
}
