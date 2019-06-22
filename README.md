


# Progetto di Programmazione ad Oggetti 

il seguente file andrà ad introdurre ed esplicare il progetto svolto dagli studenti Antici Francesco e Laudenzi Guido, valido per il corso di programmazione ad oggetti a.a. 2018/2019.

### Come eseguire

Posizionarsi tramite command line nella cartella /progetto contente i file eseguibili e digitare l'istruzione: 
```
mvn spring-boot:run
```
###  Iniziare

Una volta scaricato il progetto dal sito <https://github.com> e aperto con un editor per eseguire progetti creati con framework spring, si visiti il link <http://localhost:8080/>.

Si presenterà la homepage della webapp che farà in automatico il download del file CSV, successivo a parsing del file JSON e verifica di presenza precedente del file, contenente i dati di Catasto.

### Tests

La schermata permetterà l'esecuzione di diverse operazioni:

* visualizzazione dei dati: ritorna gli oggetti catasto del file CSV in formato JSON;

* visualizzazione dei metadati: ritorna i metadati riguardanti gli oggetti del file CSV in formato JSON;

* calcolo statistiche: permette di calcolare delle statistiche, su richiesta del client, sui dati del file CSV ;

* filtraggio dati: permette di eseguire filtraggi, su richiesta del client, sui dati del file CSV.

# Diagrammi UML

![](/Section.png)