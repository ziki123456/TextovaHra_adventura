public class PromluvCommand implements Command {
    private Hra hra;
    private Clovek clovek;

    public PromluvCommand(Hra hra, Clovek clovek) {
        this.hra = hra;
        this.clovek = clovek;

    }

    @Override
    public void execute() {
        hra.promluvNa(clovek);

        //hra.postava.setAktMistnost(hra.mistnosti.get(this.hra.postava.getAktMistnost().vratIdMistnostSmerem(this.smer)));

    }
}
