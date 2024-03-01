public class CommandCenter {
    private Command command;
    private Hra hra;
    Command jdiNaSever;
    Command jidNaZapad ;
    Command jdiNaJih ;
    Command jdiNaVychod ;

    public CommandCenter(Hra hra) {
        this.hra = hra;
        jdiNaSever = new GoCommand(this.hra, "S");
        jidNaZapad = new GoCommand(this.hra, "Z");
        jdiNaJih = new GoCommand(this.hra, "J");
        jdiNaVychod = new GoCommand(this.hra, "V");
    }
    public void jdi(String smer) {
        if (smer.equals("S")) jdiNaSever.execute();
        if (smer.equals("Z")) jidNaZapad.execute();
        if (smer.equals("J")) jdiNaJih.execute();
        if (smer.equals("V")) jdiNaVychod.execute();

    }
}
