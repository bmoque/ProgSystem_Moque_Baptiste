import java.util.concurrent.Semaphore;
import java.util.LinkedList;
import java.util.Queue;


public class ProducteurConsommateur {

    private static final int TAILLE_BUFFER = 5;
    private final Integer[] buffer = new Integer[TAILLE_BUFFER];

    private final Semaphore placesLibres = new Semaphore(TAILLE_BUFFER);
    private final Semaphore elementsDisponibles = new Semaphore(0);  
    private final Semaphore mutexBuffer = new Semaphore(1); // quel erreur ici ? vous pouvez le résoudre plus tard

    public Semaphore getPlacesLibres() {
        return placesLibres;
    }

    public Semaphore getElementsDisponibles() {
        return elementsDisponibles;
    }

    public Semaphore getMutexBuffer() {
        return mutexBuffer;
    }
}
