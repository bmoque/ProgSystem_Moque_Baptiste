# Compte Rendu — Système de Fichiers Virtuel

## Questions de réflexion

### Question 1 : Représentation binaire
Définir une convention évite la confusion sur l'ordre des octets entre machines. Si writeInt écrit en Big-Endian et readInt lit en Little-Endian, l'ordre des octets est inversé. 

### Question 2 : Signes et octets
En Java, byte est signé (-128 à 127). Lors du passage en int, le nombre conserve le signe. L'opération & 0xFF permet de récupérer la valeur non signée.

### Question 3 : Sérialisation
readInt(writeInt(x)) == x ne prouve pas le bon emplacement des octets dans la mémoire.
Il faut tester plusieurs valeurs de x comme : 0, -1, Integer.MAX_VALUE, Integer.MIN_VALUE.

### Question 4 : Bitmap
Un bitmap utilise 1 bit par bloc au lieu de 32 bits pour un entier.
Taille pour N blocs : N / 8 octets.

### Question 5 : Layout
Des adresses fixes permettent de retrouver les structures plus rapidement.
Si le bitmap se déplaçait, l'allocateur écraserait la zone de données.

### Question 6 : Inode
Permet de gérer les métadonnées et le contenu des fichiers de façon indépendante.
L'inode contient des pointeurs vers la zone de données.

### Question 7 : Allocation
C'est le premier bloc de la zone de données.
L'allocateur écraserait les zones réservées par le superblock.