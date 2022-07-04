# progetto-oop
L’applicativo si prefigge l’obbiettivo di fornire un’interfaccia che permette all’utente/cliente di
comporre un’ordinazione che può contenere più panini personalizzati a suo piacimento attraverso
un menù “a spunta”. Per identificare in modo univoco l’ordinazione viene assegnato un numero
incrementale al momento della creazione dell’ordine, inoltre viene richiesto all’utente di inserire
anche un cognome indicativo che verrà usato al momento della consegna (per interfacciarsi in
maniera più semplice con il cliente, se questo non inserisce un cognome attendibile, come ad es. un
numero, non è un problema essendo l'ordinazione identificato solamente in base al numero univoco
lasciato al cliente). Il programma provvede a lanciare un’eccezione nel caso in cui un ordinazione
non presenta la scelta di un panino. Una volta confermata l’ordinazione, questa viene inserita in una
coda delle ordinazioni (gestita appunto secondo una politica FIFO), la quale viene utilizzata dai
cuochi della paninoteca per prelevare le ordinazioni e preparare i rispettivi panini. Quando un cuoco
termina un ordine, provvede a salvare in una struttura il numero dell’ordinazione, il cognome e il
conto di quest’ultimo. Infine il cassiere accederà a questa struttura nel momento in cui un cliente
deve saldare il conto, per poi eliminare dalla struttura il riferimento a quel cliente.
Altre funzionalità:
E' previsto un backup su file di testo del buffer delle ordinazioni prese e delle ordinazioni servite
ogni volta che il contenuto dei rispettivi buffer cambia; in caso di anomalia il programma può essere
riavviato alle condizioni di arresto).
Il programma inoltre prevede di validare o no dei possibili sconti in base ai numeri di panini
ordinati; in particolare, se l'ordine:
<li> supera i 3 panini, si ha uno sconto del 15% sul conto complessivo </li>
<li> supera i 9 panini, si ha uno sconto del 20% sul conto complessivo </li>
