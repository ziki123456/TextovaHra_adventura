class GoCommand implements Command {
    private Hra hra;
    private String smer;

    public GoCommand(Hra hra, String smer) {
        this.hra = hra;
        this.smer = smer;

    }

    @Override
    public void execute() {

        hra.postava.setAktMistnost(hra.mistnosti.get(this.hra.postava.getAktMistnost().vratIdMistnostSmerem(this.smer)));

    }
}

