public class TestSemaphore {

    public static void main(String[] args) throws InterruptedException {
        ProducteurConsommateur pc = new ProducteurConsommateur();

        Thread p1 = new Thread(new Producteur(pc));
        Thread p2 = new Thread(new Producteur(pc));
        Thread c1 = new Thread(new Consommateur(pc, 10));
        Thread c2 = new Thread(new Consommateur(pc, 10));

        p1.start(); 
        p2.start(); 
        c1.start(); 
        c2.start();
        p1.join();  
        p2.join();  
        c1.join();  
        c2.join();

        System.out.println("Buffer vide : " + pc.buffer.isEmpty());
        System.out.println("Places libres : " + pc.placesLibres.availablePermits());
        System.out.println("Éléments disponibles : " + pc.elementsDisponibles.availablePermits());
    }
}