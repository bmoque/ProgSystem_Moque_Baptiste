1/ C'est en théorie la version parallèle qui doit être plus rapide car elle répartit la charge sur plusieurs cœurs CPU. 
Cependant, comme les calculs sont très courts, la création et la gestion des threads rajoutent du temps.
La version parallèle devient plus performante lorsque les calculs sont longs.
2/ Oui, les résultats sont identiques. Il n'y a pas de race condition car :   Chaque thread travaille en mémoire sur sa propre donnée. L'agrégation est faite par le thread principal, une fois que tous les threads ont terminé.