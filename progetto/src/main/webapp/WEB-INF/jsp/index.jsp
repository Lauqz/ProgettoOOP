<!DOCTYPE html>
<html lang="en">
<script>
    function show () {
        document.getElementById("js").style.display = 'inline';
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
    <button class="button" href="/meta">
        Visualizza metadati della classe
    </button>
    <hr>
    <button class="button" href="/obj">
        Visualizza oggetti della classe
    </button>
    <hr>
    <button class="button" onClick="show()">
        Genera statistiche sui dati
    </button>
    <hr>
    <form action="/stat" method="POST" id="js">
        <label for="attr"><strong>Inserisci l'attributo</strong></label>
        <input name="attr" type="radio"> Operatore </input>
        <input name="attr" type="radio"> Altitudine </input>
        <input name="attr" type="radio"> Frequenza </input>
        <br>
        <label for="op"><strong>Inserisci la statistica</strong></label>
        <input name="op" type="radio"> Media </input>
        <input name="op" type="radio"> Minimo </input>
        <input name="op" type="radio"> Massimo </input>
        <input name="op" type="radio"> Somma </input>
        <input name="op" type="radio"> Deviazione standard </input>
        <input name="op" type="radio"> Conta </input>
        <br>
        <input type="submit" value="Cerca">
    </form>
</body>
</html>