public class TestCompteur  {

    private static final int NB_TESTS = 50;
    private static final int NB_THREADS = 9;
    private static final int NB_INCREMENTS = 1111111;
    private static final int VALEUR_ATTENDUE = NB_THREADS * NB_INCREMENTS;


    public static void main(String[] args) {
       testRaceCondition();
    }

    private static void testRaceCondition() {
        int succesCount = 0;
        long minValeur = Long.MAX_VALUE;
        long maxValeur = Long.MIN_VALUE;


        for (int i = 0; i < NB_TESTS; i++) {
            CompteurDangereux.resetCompteur();

            Thread[] threads = new Thread[NB_THREADS];

            for (int t = 0; t < NB_THREADS; t++) {
                threads[t] = new Thread(new CompteurDangereux.Incrementeur("Thread" + t, NB_INCREMENTS));
                threads[t].start();
            }

            for (Thread th : threads) {
                try {
                    th.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            long resultatObtenu = CompteurDangereux.getCompteur();
            if (resultatObtenu == VALEUR_ATTENDUE) {
                succesCount++;
            }
            if (resultatObtenu < minValeur) {
                minValeur = resultatObtenu;
            }
            if (resultatObtenu > maxValeur) {
                maxValeur = resultatObtenu;
            }
        }    


        double pourcentageSucces = ((double) succesCount / NB_TESTS) * 100.0;

        System.out.println("\n Statistiques");
        System.out.println("Nombre de réussites : " + succesCount + " / " + NB_TESTS);
        System.out.println("Pourcentage de réussite : " + pourcentageSucces + "%");
        System.out.println("Valeur minimale observée : " + minValeur);
        System.out.println("Valeur maximale observée : " + maxValeur);



    }
}    