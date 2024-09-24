# labb 1

F�ljande krav st�lls p� er l�sning:

- B�de konstruktionsprogrammet och s�kprogrammet ska vara skrivna i ett riktigt programspr�k och inte n�got operativsystemn�ra skriptspr�k eller liknande.

- Konkordansen ska inte skilja p� stora och sm� bokst�ver. Anv�ndaren ska allts� kunna skriva in alla s�kfr�gor med sm� bokst�ver, stora bokst�ver eller godtycklig blandning.

- Det givna programmet tokenizer.c p� kurskatalogen (se nedan) definierar hur texten ska delas upp i enskilda ord.
- Konstruktionsprogrammet beh�ver inte vara j�ttesnabbt eftersom det bara ska k�ras en g�ng, men det m�ste vara n�gorlunda effektivt s� att det kan skapa konkordansdatastrukturen p� rimlig tid. Det f�r inte ta mer �n tre minuter att skapa konkordansdatastrukturen p� en Ubuntudator (ut�ver tiden att k�ra tokenizer och sort).

- S�kprogrammets utmatning ska inledas med en rad som anger antalet f�rekomster. D�refter ska varje f�rekomst av ordet presenteras p� varje rad med till exempel 30 tecken f�re och 30 tecken efter. Ers�tt radbyten med mellanslag. Om det finns fler �n 25 f�rekomster ska programmet, efter att ha skrivit ut dom f�rsta 25 f�rekomsterna, fr�ga anv�ndaren om den vill ha resterande f�rekomster utskrivna p� sk�rmen.

- Man ska kunna s�ka efter ett ord, till exempel bil, genom att i terminalf�nstret ge kommandot ./konkordans bil (om ni anv�nt C, C++ eller liknande), python3 konkordans.py bil (om ni anv�nt Python) eller java Konkordans bil (om ni anv�nt Java).

- Svaret (som allts� inneh�ller antalet f�rekomster men h�gst 25 rader med f�rekomster) m�ste komma inom en sekund p� skolans Ubuntudatorer. Vid redovisningen ska programmet exekveras p� en av skolans Ubuntudatorer (f�r fj�rrinloggning, se nedan).

- S�kprogrammet ska inte l�sa igenom hela texten och f�r inte anv�nda speciellt mycket internminne. Internminnesbehovet ska inte v�xa med antalet distinkta ord i den ursprungliga texten (med andra ord ha konstant internminneskomplexitet). Ni ska d�rf�r anv�nda latmanshashning (se f�rel�sning 2) som datastruktur. Vid redovisningen ska ni kunna motivera att internminneskomplexiteten �r konstant f�r s�kprogrammet.

- Prova att bara anv�nda linj�rs�kning i indexfilen och j�mf�r med att f�rst anv�nda bin�rs�kning tills s�kintervallet �r litet och d�refter linj�rs�kning (se pseudokoden i f�rel�sning 2) och se om det g�r skillnad i k�rtid f�r n�got s�kord. F�r vilken typ av s�kord borde det teoretiskt sett g� snabbare med bin�rs�kning? Ni kan till exempel m�ta k�rtiden med Unixkommandot time (skriv time f�rst i kommandot som startar ert s�kprogram) och kolla p� tiden f�r user. 

