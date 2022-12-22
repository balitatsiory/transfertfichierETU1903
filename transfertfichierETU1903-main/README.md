# transfertfichierETU1903
Razafindrakoto Balita Tsiory Sarobidy transfertfichier ETU1903 n 157

Mon projet : Transfert de fichier

Explication:
le client peut soit 
-uploader: 
    choisit un fichier txt, puis l envoyee, 
    Du cote serveur: il le recoit, divise le contenue du fichier en 3 part egals (en byte), puis donne une part chacune aux serveurs secondaires pour qu ils les stockent dans des dossiers differents chancuns
-downloader : 
    Le serveur l'envoit une liste de nom de fichier que le client peut downloader, Le client selectionne un nom dans la liste et envoit automatiquement le nom du fichier selectionne
    Le serveur recoit le nom du fichier selectionne ; il recupere chaque bout du fichier dans les repertoires des serveurs secondaires puis l envoit au cleint
    Le client quant a lui recoit le fichiers et l assemble bout a bout

ordre de compilation : 
d'abord les serveurs secondaire :
java SC1.java
java SC2.java
java SC3.java

puis le serveur principale:
java Server.java

enfin le client:
java Client.java
