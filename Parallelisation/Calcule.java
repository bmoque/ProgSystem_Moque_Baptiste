public class Calcule extends Thread {
    // TODO: Déclarer les attributs nécessaires

    private int valeur;
    private int multiplicateur;
    private final int index;

    public Calcule(int valeur,int multiplicateur,int index) {
        this.valeur = valeur;
        this.multiplicateur = multiplicateur;
        this.index = index;
    }

    @Override
    public void run() {
        // TODO: Implémenter le calcul intensif
        valeur = valeur * (valeur + 1) * multiplicateur;
        // Même logique que dans la boucle interne de CalculSequentiel
    }

    public int getResultat() {
        return valeur;
    }

}