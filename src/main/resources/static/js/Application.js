function validateRecipeForm()
{
    var a =document.forms["recipe"]["rname"].value;
    var b =document.forms["recipe"]["duration"].value;
    var c =document.forms["recipe"]["difficulty"].value;
    var d =document.forms["recipe"]["pplnr"].value;
    var e =document.forms["recipe"]["cprocess"].value;

    if (a==null || a=="" || b==null || b == ""|| c==null || c=="" || d==null || d==""|| e==null || e=="")
    {
        alert("Bitte alle Pflichtfelder füllen!");
        return false;
    }
}

function validateIngredientForm()
{
    var f =document.forms["ingredient"]["inna"].value;
    var g =document.forms["ingredient"]["innr"].value;
    var h =document.forms["ingredient"]["inun"].value;

    if (f==null || f=="" || g==null || g == ""|| h==null || h=="")
    {
        alert("Bitte alle Pflichtfelder füllen!");
        return false;
    }
}
