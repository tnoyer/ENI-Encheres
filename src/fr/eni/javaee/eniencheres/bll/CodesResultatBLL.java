package fr.eni.javaee.eniencheres.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	/**
	 * Echec quand le pseudo n'accepte pas que des caractères alphanumériques
	 */
	public static final int PSEUDO_FORMAT_ERR=20000;
	/**
	 * Echec quand le mot de passe et la confirmation ne sont pas identiques
	 */
	public static final int PASSWORD_ERR=20001;
	/**
	 * Echec si les champs requis ne sont pas remplis
	 */
	public static final int NOM_CHAMPS_OBLIGATOIRE=20002;
	/**
	 * Echec si email non conforme
	 */
	public static final int EMAIL_ERR=20003;
	/**
	 * Echec si pseudo déjà existant
	 */
	public static final int UNIQUE_PSEUDO_ERR=20004;
	/**
	 * Echec si mail déjà existant
	 */
	public static final int UNIQUE_MAIL_ERR=20005;
	/**
	 * Echec si le mot de passe actuel est faux
	 */
	public static final int ACTUAL_PASSWORD_ERR=20006;
	/**
	 * Echec si date de début est supérieur à la date de fin
	 */
	public static final int DATE_ERR=20007;
	/**
	 * echec si date de début est supérieur à la date du jour
	 */
	public static final int DATE_DEBUT_ERR=20008;
	/**
	 * Echec si montant enchere inférieur au prix de vente
	 */
	public static final int MONTANT_ENCHERE_ERR=20009;
	/**
	 * echec si le montant enchere supérieur au crédit de l'utilisateur
	 */
	public static final int CREDIT_ERR=20010;
	
}
