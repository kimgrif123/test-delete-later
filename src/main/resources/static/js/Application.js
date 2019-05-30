function validateRecipeForm()
{
    var a =document.forms["recipe"]["rname"].value;
    var b =document.forms["recipe"]["duration"].value;
    var c =document.forms["recipe"]["difficulty"].value;
    var d =document.forms["recipe"]["pplnr"].value;
    var e =document.forms["recipe"]["cprocess"].value;
    var u =document.forms["recipe"]["picURL"].value;

    var f =document.forms["recipe"]["inna"].value;
    var g =document.forms["recipe"]["innr"].value;
    var h =document.forms["recipe"]["inun"].value;



    if (a==null || a=="" || b==null || b == ""|| c==null || c=="" || d==null || d==""|| e==null || e=="")
    {
        alert("Bitte alle Pflichtfelder füllen!");
        return false;
    }

    if (f=="" && g!="" && h!="")
    {
        alert("Bitte alle Zutaten Felder füllen!");
        return false;
    }
    if (f!="" && g=="" && h!="")
    {
        alert("Bitte alle Zutaten Felder füllen!");
        return false;
    }
    if (f!="" && g!= "" && h=="")
    {
        alert("Bitte alle Zutaten Felder füllen!");
        return false;
    }

    if(u!="")
    {
       return confirm("Bestätigen Sie, dass die hier angegebene Bildquelle gegen keinerlei Datenschutzrichtlinien Dritter verstößt.");
    }

}




function validateValuationForm()
{
    var i = document.getElementById("score").value;

    if(isInt(i)&& i >10 || i < 1 )
    {
        alert("Deine Bewertung befindet sich nicht zwischen 1 - 10! ");
        return false;
    }
}

function validateCalcForm()
{
    var patt = /[0-9],[0-9]/;
    var x = document.getElementById("calc").value;

    if(isInt(x) && x < 1 || x > 100)
    {
        alert("Damit können wir nicht rechnen! (0 - 100)");
        return false
    }

    //It searches a string for a pattern, and returns true or false, depending on the result.

    if(patt.test(x))
    {
        alert("Wir sind hier nicht bei 'Two and a Half Man'!");
        return false;
    }

}

function isInt(value)
{
    //https://stackoverflow.com/questions/14636536/how-to-check-if-a-variable-is-an-integer-in-javascript
    //The isNaN() function determines whether a value is an illegal number (Not-a-Number).
    //https://developer.mozilla.org/de/docs/Web/JavaScript/Reference/Global_Objects/parseInt
    return !isNaN(value) &&
        parseInt(Number(value)) == value &&
        !isNaN(parseInt(value, 10));
}


// Automatic Slideshow - change image every 3 seconds
function carousel()
{
    var i;
    var x = document.getElementsByClassName("slideshow");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    myIndex++;
    if (myIndex > x.length) {myIndex = 1}
    x[myIndex-1].style.display = "block";
    setTimeout(carousel, 3000);
}