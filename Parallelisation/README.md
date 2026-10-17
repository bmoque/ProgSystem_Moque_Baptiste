# Questions : calcul séquentiel, parallèle

### 1/ Performnace
C'est en théorie la version parallèle qui doit être plus rapide car elle répartit la charge sur plusieurs cœurs CPU. 
Cependant, comme les calculs sont très courts, la création et la gestion des threads rajoutent du temps.
La version parallèle devient plus performante lorsque les calculs sont longs.
### 2/ Résultats
Oui, les résultats sont identiques. Il n'y a pas de race condition car chaque thread travaille en mémoire sur sa propre donnée. L'agrégation est faite par le thread principal, une fois que tous les threads ont terminé.

# Questions : race conditions

### 1/ Impact du nombre de threads (1, 2, 5, 10)
Plus le nombre de threads augmente, plus les accès concurrents au compteur sont fréquents et le risque de race condition augmente. Avec un seul thread, il n’y a pas de concurrence entre threads.

### 2/ Impact du nombre d’incréments par thread
Plus chaque thread effectue d’incréments, plus il y a d’occasions que des accès se chevauchent et provoquent des pertes. 

### 3/ Pourquoi compteurGlobal++ n’est-il pas atomique ?
compteurGlobal++ combine trois étapes : LOAD (lire la valeur), ADD (ajouter 1) et STORE (écrire le résultat). Si deux threads lisent la même valeur avant de l’écrire, l’un peut écraser l’incrément de l’autre et le compteur final sera incorrect.