package fr.eni.javaee.eniencheres.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL=10000;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC=10001;
	
	/**
	 * Echec de la lecture 
	 */
	public static final int CONNEXION_UTILISATEUR_ECHEC=10002;
	
	/**
	 * Echec de l'affichage du profil
	 */
	public static final int AFFICHAGE_PROFIL_ERR=10003;
	/**
	 * Echec lors de la suppression de l'utilisateur
	 */
	public static final int SUPPRESSION_UTILISATEUR_ERR=10004;
	/**
	 * Echec lors de la modification de l'utilisateur
	 */
	public static final int UPDATE_UTILISATEUR_ERR=10005;
	/**
	 * Echec lors de la requète de vérification de l'unicité du pseudo
	 */
	public static final int UNIQUE_PSEUDO_ERR=10006;
	/**
	 * Echec lors de la requète de vérification de l'unicité du mail
	 */
	public static final int UNIQUE_MAIL_ERR=10007;
	/**
	 * echec de la lecture de la categorie
	 */
	public static final int SELECT_CAT_ECHEC=10008;
	/**
	 * echec de la lecture de l'article
	 */
	public static final int LECTURE_ARTICLE_ECHEC=10009;
	/**
	 * Echec de la lecture de l'enchère
	 */
	public static final int LECTURE_ENCHERE_ECHEC=10010;
	/**
	 * Echec lors de la modification du crédit
	 */
	public static final int UPDATE_CREDIT_ERR=10011;
	/**
	 * Echec lors de la modification de l'enchere
	 */
	public static final int UPDATE_ENCHERE_ERR=10012;
	
}