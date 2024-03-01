import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Hra {
    public Postava postava;

    List<Mistnost> mistnosti = new ArrayList<Mistnost>();
    HashMap objekty = new HashMap();
    HashMap lidi = new HashMap();

    private boolean konec;


    public Hra() {
    }
    public void Hrej() {
        System.out.println("Vitejte v nemocnici plné nepokojených lidí. Vaším úkolem je zajistit spokojenost všech v nemocnici." + "\r\n\n" );
        CommandCenter cc = new CommandCenter(this);

        konec = false;
        Scanner sc = new Scanner(System.in);
        while (!konec) {
            System.out.println(this.vypisMistnost());
            String choice;

            do {
                System.out.print("Co chces udelat? (S - sebrat, J - jít, P - položit, M - mluvit, K - konec)> ");

                choice = sc.nextLine().toUpperCase();

                if (!choice.matches("[SJPMK]")) {
                    System.out.println("Zadejte prosím jednu z možností S, J, P, M nebo K!");
                }
            } while (!choice.matches("[SJPMK]"));

            if (choice.equals("K")) konec = true;

            else if (choice.equals("J")) {
                do {
                    System.out.print("Kterým směrem se chceš vydat? (S - sever, Z - zapad, J - jíh, V - vychod)> ");

                    choice = sc.nextLine().toUpperCase();

                    if (!choice.matches("[SZJV]")) {
                        System.out.println("Zadejte prosím jednu z možností S, Z, J nebo V!");
                    } else if (!this.postava.getAktMistnost().jdeJitSmerem(choice)) {
                        System.out.println("Tímto směrem se jít nedá!");
                    }

                } while (!choice.matches("[SZJV]") || !this.postava.getAktMistnost().jdeJitSmerem(choice));

                cc.jdi(choice);
            }

            else if (choice.equals("S")) {
                if (this.postava.getAktMistnost().veci.size()>0) {


                    do {
                        System.out.println("Jakou vec chces sebrat: " + this.vypisVeci());
                        choice = sc.nextLine();

                        if (!choice.matches("[0-9]")) {
                            System.out.println("Zadejte prosím číslo předmětu který chcete sebrat: ");
                        } else if ((Integer.parseInt(choice) < 0) || (Integer.parseInt(choice) > this.postava.getAktMistnost().veci.size() - 1)) {
                            System.out.println("Tato věc se zde nenachází!");
                        }


                    } while (!choice.matches("[0-9]") || (Integer.parseInt(choice) < 0) || (Integer.parseInt(choice) > this.postava.getAktMistnost().veci.size() - 1));
                    Command seberCommand = new SeberCommand(this, postava.getAktMistnost().veci.get(Integer.parseInt(choice)));
                    seberCommand.execute();
                }
                else System.out.println(this.vypisVeci());
            }
            else if (choice.equals("P")) {
                if (this.postava.batoh.obsah.size()>0) {


                    do {
                        System.out.println("Jakou vec chceš položit: " + postava.batoh.veciVBatohu());
                        choice = sc.nextLine();

                        if (!choice.matches("[0-9]")) {
                            System.out.println("Zadejte prosím číslo předmětu který chcete sebrat: ");
                        } else if ((Integer.parseInt(choice) < 0) || (Integer.parseInt(choice) > this.postava.batoh.obsah.size() - 1)) {
                            System.out.println("Tato věc v batohu není!");
                        }


                    } while (!choice.matches("[0-9]") || (Integer.parseInt(choice) < 0) || (Integer.parseInt(choice) > this.postava.batoh.obsah.size() - 1));
                    Command polozCommand = new PolozCommand(this, postava.batoh.obsah.get(Integer.parseInt(choice)));
                    polozCommand.execute();
                }
                else System.out.println(this.vypisVeci());
            }

            else if (choice.equals("M")) {

                do {
                    System.out.println("S kým chceš mluvit: " + this.vypisLidi());
                    choice = sc.nextLine();

                    if (!choice.matches("[0-9]")) {
                        System.out.println("Zadejte prosím číslo člověka, se kterým chceš mluvit: ");
                    } else if ((Integer.parseInt(choice) < 0) || (Integer.parseInt(choice) > this.postava.getAktMistnost().lidi.size()-1)) {
                        System.out.println("Tato osoba zde není!");

                    }


                } while (!choice.matches("[0-9]") || (Integer.parseInt(choice) < 0) || (Integer.parseInt(choice) > this.postava.getAktMistnost().lidi.size()-1));
                Command promluvCommand = new PromluvCommand(this, postava.getAktMistnost().lidi.get(Integer.parseInt(choice)) );
                promluvCommand.execute();

            }
        }
    }

    public void nactiMistnosti(String cesta) {
        try (BufferedReader soubor = new BufferedReader(new FileReader(cesta))){
            String line;
            line = soubor.readLine();
            while ((line = soubor.readLine()) != null) {
                String[] pole = line.split(";");
                mistnosti.add(Integer.parseInt(pole[0]),new Mistnost(pole));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Soubor nenalezen.");

        } catch (IOException e) {
            System.out.println("Soubor je poskozen.");

        } catch (Exception e) {
            System.out.println("Nekde je chyba.");

        }
    }
    public String vypisMistnost() {
        String text = "Nacházíš se v místnosti " + this.postava.getAktMistnost().getNazev() + "\r\n";
        text += this.vypisVeci();
        text += this.vypisLidi();
        text += this.postava.vypisVeci();
        return text;
    }
    public String vypisVeci() {
        String text = "";
        if (this.postava.getAktMistnost().veci.size() == 0) text += "V místnosti nejsou žádné věci." + "\r\n";
        else {
            text += "V mistnosti jsou tyto veci: ";
            for (int i = 0; i < this.postava.getAktMistnost().veci.size(); i++) {
                Vec item = this.postava.getAktMistnost().veci.get(i);
                text += i + " - " + item.getNazev() + ", ";
            }
            text = text.substring(0,text.length()-2);
            text += "\r\n";
        }
        return text;
    }

    public String vypisLidi() {
        String text = "";
        if (this.postava.getAktMistnost().lidi.size() == 0) text += "V místnosti nejsou žádní lidé." + "\r\n";
        else {
            text += "V mistnosti jsou tito lidé: ";
            for (int i = 0; i < this.postava.getAktMistnost().lidi.size(); i++) {
                Clovek item = this.postava.getAktMistnost().lidi.get(i);
                text += i + " - " + item.getNazev()+"("+item.getPopis()+")" + ", ";
            }
            text = text.substring(0,text.length()-2);
            text += "\r\n";
        }
        return text;
    }

    public void seberPredmet(Vec vec) {

        if (vec != null) {
            if (postava.batoh.pridej(vec)) {
                postava.getAktMistnost().odeberPredmet(vec);
                System.out.println("Sebral(a) jste předmět: " + vec.getNazev());
            } else {
                System.out.println("Batoh je plný, nemůžete přidat další předmět.");
            }
        } else {
            System.out.println("Tento předmět v místnosti není k dispozici.");
        }
    }

    public void promluvNa(Clovek clovek) {

        if (clovek != null) {

                System.out.println("Oslovil jsi: " + clovek.getNazev());
                if (clovek.jeSpokojen())  System.out.println(clovek.getNazev() + " se již nechce bavit.");
                else {

                    if (postava.batoh.obsah.contains(clovek.getPozadovanaVec())) {
                        System.out.println("Dal jsi osobě " + clovek.getNazev() + " " + clovek.chceVec() + " a už je spokojená.");
                        postava.batoh.odeber(clovek.getPozadovanaVec());
                        if (clovek.obdrzelVec()) {
                            System.out.println("Všichni v nemocnici jsou spokojeni. Díky za hru!!!");
                            konec=true;
                        } else System.out.println("Spokojenost v nemocnici je: " + (double) Clovek.pocetSpokojenychPostav/Clovek.pocetPostav * 100 + "%");
                    }
                    else System.out.println(clovek.getNazev() + " chce, abys přinesl " + clovek.chceVec());
                }

       }
    }

    public void umistiPostavu() {
        this.postava = new Postava(this.mistnosti.get(0));
    }
    public void nactiObjekty(String cesta) {
        try (BufferedReader soubor = new BufferedReader(new FileReader(cesta))){
            Vec v;
            Clovek c;
            String line;
            line = soubor.readLine();
            while ((line = soubor.readLine()) != null) {
                String[] pole = line.split(";");

                if (pole[1].equals("vec")) {
                    v = new Vec(pole);
                    objekty.put(Integer.parseInt(pole[0]),v);

                    mistnosti.get(Integer.parseInt(pole[4])).veci.add( v);
                } else  {
                    c = new Clovek(pole);
                    lidi.put(Integer.parseInt(pole[0]),c);
                    mistnosti.get(Integer.parseInt(pole[4])).lidi.add( c);
                }
            }

            lidi.forEach((key, value) -> {
                Clovek cl = (Clovek) value;
                Vec ve = (Vec)objekty.get(cl.getIdPozadovaneVeci());
                cl.setPozadovanaVec(ve);
            });



        } catch (FileNotFoundException e) {
            System.out.println("Soubor nenalezen.");

        } catch (IOException e) {
            System.out.println("Soubor je poskozen.");

        } catch (Exception e) {
            System.out.println("Nekde je chyba.");

        }
    }



}
