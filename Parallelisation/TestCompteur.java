public class TestCompteur  {

    public static void main(String[] args) {
       testRaceCondition();
    }

    private static void testRaceCondition() {
        for (int i = 0; i < 50; i++) {
            CompteurDangereux.resetCompteur();
        }
    }
}    