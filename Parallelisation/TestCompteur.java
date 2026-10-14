public class TestCompteur  {

    private static final int NB_TESTS = 50;
    private static final int NB_THREADS = 9;
    private static final int NB_INCREMENTS = 1111111;
    private static final int VALEUR_ATTENDUE = NB_THREADS * NB_INCREMENTS;


    public static void main(String[] args) {
       testRaceCondition();
    }

    private static void testRaceCondition() {

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
            System.out.println("Test " + i + "  Résultat : " + resultatObtenu + "  Attendu : " + VALEUR_ATTENDUE + "\n");

        }    
    }
}    