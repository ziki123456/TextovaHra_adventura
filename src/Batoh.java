import java.util.*;

public class Batoh {
    public List<Vec> obsah;
    private static final int KAPACITA  = 2;

    public Batoh() {
        obsah = new ArrayList<>();
    }

    public boolean pridej(Vec vec) {
        if (obsah.size() < KAPACITA) {
            obsah.add(vec);
            return true;
        }
        return false;
    }

    public void odeber(Vec vec) {
        obsah.remove(vec);

    }

    public String veciVBatohu() {
        String text = "";
        if (obsah.size() == 0) text += "V batohu nejsou žádné věci." + "\r\n";
        else {
            text += "V batohu jsou tyto veci: ";
            for (int i = 0; i < obsah.size(); i++) {
                Vec item = obsah.get(i);
                text += i + " - " + item.getNazev() + ", ";
            }
            text = text.substring(0,text.length()-2);
            text += "\r\n";
        }
        return text;
    }
}
