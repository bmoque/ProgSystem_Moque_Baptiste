class Producteur implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            //int produit = genererProduit();

            // TODO: Attendre une place libre
            // TODO: Acquérir l'accès exclusif au buffer  
            // TODO: Ajouter l'élément au buffer
            // TODO: Libérer l'accès au buffer
            // TODO: Signaler qu'un élément est disponible

            // acquire, release
            try {
                
            } catch (InterruptedException e) {

            }
            
            
        }
    }
}