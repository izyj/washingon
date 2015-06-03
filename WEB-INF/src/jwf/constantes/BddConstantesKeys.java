package jwf.constantes;

public abstract class BddConstantesKeys {

	// proprietes de connexion a la base de données
	public static String urlConnexion = "jdbc:mysql://localhost/musique";

	public static String login = "root";

	public static String motDePasse = "";

	// Table Administrateur
	public static String SQL_TABLE_ADMIN = "administrateur";
	public static String SQL_ADMIN_ID = "ADMIN_id";
	public static String SQL_ADMIN_PSEUDO = "ADMIN_pseudo";
	public static String SQL_ADMIN_MPD = "ADMIN_mdp";

	// Table client
	public static String SQL_TABLE_CLIENT = "client";
	public static String SQL_CLIENT_ID = "CL_id";
	public static String SQL_CLIENT_NOM = "CL_nom";
	public static String SQL_CLIENT_PRENOM = "CL_prenom";
	public static String SQL_CLIENT_CODEP = "CL_codep";
	public static String SQL_CLIENT_VILLE = "CL_ville";
	public static String SQL_CLIENT_RUE = "CL_rue";
	public static String SQL_CLIENT_NUMTEL = "CL_numtel";
	public static String SQL_CLIENT_PSEUDO = "pseudo";
	public static String SQL_CLIENT_MPD = "motdepasse";

	// Table Artiste
	public static String SQL_TABLE_ARTISTE = "artiste";
	public static String SQL_ARTISTE_ID = "N°artiste";
	public static String SQL_ARTISTE_NOM = "nom";
	public static String SQL_ARTISTE_ID_ALBUM = "idalbum";
	public static String SQL_ARTISTE_NUMGENRE = "numgenre";

	// Table Album
	public static String SQL_TABLE_ALBUM = "album";
	public static String SQL_ALBUM_ID = "idalbum";
	public static String SQL_ALBUM_NOM = "nomalbum";
	public static String SQL_ALBUM_NB_TITRE = "nbtitre";
	public static String SQL_ALBUM_NUMGENRE = "numgenre";
	public static String SQL_ALBUM_PRIX = "prix";
	public static String SQL_ALBUM_PRIX_REDUC = "prixreduc";
	public static String SQL_ALBUM_NUM_IMAGE = "num_image";
	public static String SQL_ALBUM_DATE_AJOUT = "date_ajout";

	// Table Genre
	public static String SQL_TABLE_GENRE = "genre";
	public static String SQL_GENRE_ID = "numgenre";
	public static String SQL_GENRE_NOM = "nomgenre";

	// Table News
	public static String SQL_TABLE_NEWS = "news";
	public static String SQL_NEWS_ID = "ID";
	public static String SQL_NEWS_AUTEUR = "auteur";
	public static String SQL_NEWS_TITRE = "titre";
	public static String SQL_NEWS_DATE = "date";
	public static String SQL_NEWS_TEXT = "text_news";

	// Table single

	public static String SQL_TABLE_SINGLE = "single";
	public static String SQL_SINGLE_ID = "numsingle";
	public static String SQL_SINGLE_NOM = "nomsingle";
	public static String SQL_SINGLE_IDALBUM = "idalbum";
	public static String SQL_SINGLE_NUMGENRE = "numgenre";

}
