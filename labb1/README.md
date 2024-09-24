# labb 1

Följande krav ställs på er lösning:

- Både konstruktionsprogrammet och sökprogrammet ska vara skrivna i ett riktigt programspråk och inte något operativsystemnära skriptspråk eller liknande.

- Konkordansen ska inte skilja på stora och små bokstäver. Användaren ska alltså kunna skriva in alla sökfrågor med små bokstäver, stora bokstäver eller godtycklig blandning.

- Det givna programmet tokenizer.c på kurskatalogen (se nedan) definierar hur texten ska delas upp i enskilda ord.
- Konstruktionsprogrammet behöver inte vara jättesnabbt eftersom det bara ska köras en gång, men det måste vara någorlunda effektivt så att det kan skapa konkordansdatastrukturen på rimlig tid. Det får inte ta mer än tre minuter att skapa konkordansdatastrukturen på en Ubuntudator (utöver tiden att köra tokenizer och sort).

- Sökprogrammets utmatning ska inledas med en rad som anger antalet förekomster. Därefter ska varje förekomst av ordet presenteras på varje rad med till exempel 30 tecken före och 30 tecken efter. Ersätt radbyten med mellanslag. Om det finns fler än 25 förekomster ska programmet, efter att ha skrivit ut dom första 25 förekomsterna, fråga användaren om den vill ha resterande förekomster utskrivna på skärmen.

- Man ska kunna söka efter ett ord, till exempel bil, genom att i terminalfönstret ge kommandot ./konkordans bil (om ni använt C, C++ eller liknande), python3 konkordans.py bil (om ni använt Python) eller java Konkordans bil (om ni använt Java).

- Svaret (som alltså innehåller antalet förekomster men högst 25 rader med förekomster) måste komma inom en sekund på skolans Ubuntudatorer. Vid redovisningen ska programmet exekveras på en av skolans Ubuntudatorer (för fjärrinloggning, se nedan).

- Sökprogrammet ska inte läsa igenom hela texten och får inte använda speciellt mycket internminne. Internminnesbehovet ska inte växa med antalet distinkta ord i den ursprungliga texten (med andra ord ha konstant internminneskomplexitet). Ni ska därför använda latmanshashning (se föreläsning 2) som datastruktur. Vid redovisningen ska ni kunna motivera att internminneskomplexiteten är konstant för sökprogrammet.

- Prova att bara använda linjärsökning i indexfilen och jämför med att först använda binärsökning tills sökintervallet är litet och därefter linjärsökning (se pseudokoden i föreläsning 2) och se om det gör skillnad i körtid för något sökord. För vilken typ av sökord borde det teoretiskt sett gå snabbare med binärsökning? Ni kan till exempel mäta körtiden med Unixkommandot time (skriv time först i kommandot som startar ert sökprogram) och kolla på tiden för user. 

