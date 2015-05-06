function ValiderInscription() 
{
var reponse=true;
var nom = document.getElementById("nom").value;
var prenom = document.getElementById("prenom").value;
var pseudo = document.getElementById("pseudo").value;
var mdp = document.getElementById("mdp").value;
	if (name != '' && prenom !='' && pseudo !='' && mdp !='') {			
	return true;
			
		} else {
		alert("Certain champs sont obligatoire veuiller les remplir");
		return false;
		}
		
}