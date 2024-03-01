public class SeberCommand implements Command {
    private Hra hra;
    private Vec vec;

    public SeberCommand(Hra hra, Vec vec) {
        this.hra = hra;
        this.vec = vec;

    }

    @Override
    public void execute() {
        hra.seberPredmet(vec);

        //hra.postava.setAktMistnost(hra.mistnosti.get(this.hra.postava.getAktMistnost().vratIdMistnostSmerem(this.smer)));

    }
}
