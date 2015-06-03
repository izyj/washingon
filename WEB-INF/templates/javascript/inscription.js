function ValiderInscription() {

	var nom = document.getElementById("nom").value, prenom = document
			.getElementById("prenom").value, pseudo = document
			.getElementById("pseudo").value, mdp = document
			.getElementById("mdp").value, rep = false;
	if (nom !== "" && prenom !== "" && pseudo !== "" && mdp !== "") {
		rep = true;
	}
	if (rep === false) {
		alert("Certains champs sont obligatoire veuiller les remplir.");
	}
	alert("Vous avez été enregistrer.");
	return rep;
}

function validerConnexion() {

	var pseudo = document.getElementById("conPseudo").value, motdepasse = document
			.getElementById("conMdp").value, rep = false;

	if (pseudo !== "") {

		if (motdepasse !== "") {

			rep = true;
		} else {
			alert("Veuillez saisir un mot de passe");
		}
	} else {
		alert("veuiller saisir un pseudo");
	}

	return rep;

}
function validerSuppressionClient() {
	var id = document.getElementById("choix").value, rep = false;

	if (id !== "") {
		rep = true;
	} else {
		alert("Veuillez saisir l'identifiant d'un utilisateur !");
	}

	return rep;

}

function validerSuppressionAlbums() {
	var id = document.getElementById("choix").value, rep = false;

	if (id !== "") {
		rep = true;
	} else {
		alert("Veuillez saisir l'identifiant d'un album !");
	}

	return rep;

}

function validerAjoutAlbum() {
	var id = document.getElementById("nomalb").value,
	document.getElementById("nbtitre").value,
	document.getElementById("MAX_FILE_SIZE").value,
	document.getElementById("photo").value,
	document.getElementById("genre").value,rep = false;

	if (id !== "") {
		rep = true;
	} else {
		alert("Veuillez saisir l'identifiant d'un album !");
	}

	return rep;

}
