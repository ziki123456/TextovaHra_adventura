public class PolozCommand implements Command {
    private Hra hra;
    private Vec vec;

    public PolozCommand(Hra hra, Vec vec) {
        this.hra = hra;
        this.vec = vec;

    }

    @Override
    public void execute() {
        hra.postava.batoh.odeber(vec);
        hra.postava.getAktMistnost().pridejPredmet(vec);

        //hra.postava.setAktMistnost(hra.mistnosti.get(this.hra.postava.getAktMistnost().vratIdMistnostSmerem(this.smer)));

    }
}