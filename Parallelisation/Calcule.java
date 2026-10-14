public class Calcule extends Thread {

    private int valeur;
    private int multiplicateur;
    private final int index;
    private long resultat = 0;

    public Calcule(int valeur,int multiplicateur,int index) {
        this.valeur = valeur;
        this.multiplicateur = multiplicateur;
        this.index = index;
    }

    @Override
    public void run() {
        for (int j = 0; j < multiplicateur; j++) {
            resultat += (long) valeur * valeur + valeur;
        }
    }

    public long getResultat() {
        return resultat;
    }

}