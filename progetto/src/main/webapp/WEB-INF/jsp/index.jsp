<!DOCTYPE html>
<html lang="en">
<script>
    function show () {
        document.getElementById("js").style.display = 'inline';
    }
    function show1 () {
        document.getElementById("fltr").style.display = 'inline';
    }
</script>
<head>
    <meta charset="UTF-8">
    <title>Progetto di OOP</title>
    <link type="text/css" href="/css/main.css" rel="stylesheet">
</head>
<header>
    <h1 class="css">
        <strong>Download e parse del dataset completato</strong>
    </h1>
</header>
<body bgcolor=#AFEEEE>
    <button class="button" onclick="window.location.href='/meta'">
        Visualizza metadati della classe
    </button>
    <hr>
    <button class="button" onclick="window.location.href='/obj'">
        Visualizza oggetti della classe
    </button>
    <hr>
    <button class="button" onClick="show()">
        Genera statistiche sui dati
    </button>
    <hr>
    <button class="button" onClick="show1()">
        Filtra tutti i dati
    </button>
    <hr>
    <form action="/stat" method="GET" id="js">
        <label for="Att"><strong>Inserisci l'attributo </strong></label>
        <input name="Att" type="radio" value=0 checked> Operatore </input>
        <input name="Att" type="radio" value=1> Altitudine </input>
        <input name="Att" type="radio" value=2> Frequenza </input>
        <br>
        <label for="Stat"><strong>Inserisci la statistica </strong></label>
        <input name="Stat" type="radio" value=0 checked> Media </input>
        <input name="Stat" type="radio" value=1> Minimo </input>
        <input name="Stat" type="radio" value=2> Massimo </input>
        <input name="Stat" type="radio" value=3> Somma </input>
        <input name="Stat" type="radio" value=4> Deviazione standard </input>
        <input name="Stat" type="radio" value=5> Conta </input>
        <br>
        <input type="submit" value="Cerca">
    </form>
    <br>
    <form action="/filters" method="POST" id="fltr">
        <label for="Att"><strong>Inserisci l'attributo </strong></label>
        <input name="Att" type="radio" value=1> Altitudine </input>
        <input name="Att" type="radio" value=2> Frequenza </input>
        <br>
        <label for="Stat"><strong>Inserisci i filtri </strong></label>
        <br>
        <input name="Stat" type="radio" value=0 checked> <= </input>
        <input name="Stat" type="radio" value=1> >= </input>
        <br>
        <input name="Num" type="text" placeholder="Numero"> Numero </input>
        <br>
        <input name="Logic" type="radio" value=0> Or </input>
        <input name="Logic" type="radio" value=1> And </input>
        <input name="Logic" type="radio" value=null> -- </input>
        <br>
        <input name="Stat1" type="radio" value=0> <= </input>
        <input name="Stat1" type="radio" value=1> >= </input>
        <input name="Stat1" type="radio" value=null> -- </input>
        <br>
        <input name="Num" type="text" placeholder="Numero" default=null> Numero </input>
        <br>
        <input type="submit" value="Cerca">
    </form>
</body>
</html>