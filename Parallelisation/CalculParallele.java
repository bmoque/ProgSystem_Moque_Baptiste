public class CalculParallele  {

    private static final int[] DONNEES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static final int MULTIPLICATEUR = 1000;

    private static Calcule[] threads = new Calcule[DONNEES.length];

    private static int somme;

    public static void main(String[] args) {
        long debut = System.nanoTime();
        long sommeTotal = 0;

        for (int i = 0; i < DONNEES.length; i++) {
            Calcule th = new Calcule(DONNEES[i],MULTIPLICATEUR,i);
            threads[i] = th;
            th.start();
        }
        somme = 0;
        for (Calcule thread : threads) {
            try {
                thread.join(); 
                somme += thread.getResultat();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        long duree = (System.nanoTime() - debut) / 1_000_000;
        System.out.println("Résultat : " + somme);
        System.out.println("Durée : " + duree + " ms");

        
    }
}    