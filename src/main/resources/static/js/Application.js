function addIngredient()
{
    var previnput = document.getElementsByClassName("ingredient").lastChild;
    var prevId = previnput.attributes.id;
    console.log(prevId);
    var newId = prevId + 1;
    var input = document.createElement("input");

        input.setAttribute("type", "number");


    var para  =   document.createElement("p");
    var Menge = document.createTextNode("Menge: ");
    var element = document.getElementById("recipe");
    element.appendChild(para);
    para.appendChild(Menge);
    para.appendChild(input);
}