public class Main {
    public static void main(String[] args) {
        String cestaMistnosti = "TextovaHraMapa.csv";
        String cestaObjekty = "TextovaHraObjekty.csv";
        Hra hra = new Hra();
        hra.nactiMistnosti(cestaMistnosti);
        hra.nactiObjekty(cestaObjekty);
        hra.umistiPostavu();
        hra.Hrej();

    }
}