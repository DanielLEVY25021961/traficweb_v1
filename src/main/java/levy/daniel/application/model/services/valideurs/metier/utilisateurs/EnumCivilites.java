package levy.daniel.application.model.services.valideurs.metier.utilisateurs;

/**
 * ENUMERATION EnumCivilites :<br/>
 * Enumération des civilités.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * énumération, enumération, enumeration, Enumeration,<br/>
 * Enumeration à 2 valeurs, à deux champs, <br/>
 * constructeur à 2 valeurs, constructeur à 2 paramètres, <br/>
 * constructeur à 2 arguments,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 28 mars 2019
 *
 */
public enum EnumCivilites {
	
	/**
	 * "M.", "Monsieur".<br/>
	 */
	MONSIEUR("M.", "Monsieur")
	
	, /**
	 * "Mme", "Madame".<br/>
	 */
	MADAME("Mme", "Madame")
	
	, /**
	 * "Mlle", "Mademoiselle".<br/>
	 */
	MADEMOISELLE("Mlle", "Mademoiselle")
	
	, /**
	 * "Dr", "Docteur".<br/>
	 */
	DOCTEUR("Dr", "Docteur")
	
	, /**
	 * "Maître", "Maître".<br/>
	 */
	MAITRE("Maître", "Maître")
	
	, /**
	 * "Mr", "Mister".<br/>
	 */
	MISTER("Mr", "Mister");

	
	/**
	 * abreviation de la civilité.<br/>
	 */
	private final String abreviation;
	
	/**
	 * civilité complète.<br/>
	 */
	private final String civilite;
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 *
	 * @param pAbreviation
	 * @param pCivilite
	 */
	EnumCivilites(String pAbreviation, String pCivilite) {
		this.abreviation = pAbreviation;
		this.civilite = pCivilite;		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________


	
	/**
	 * Getter de l'abreviation de la civilité.<br/>
	 *
	 * @return this.abreviation : String.<br/>
	 */
	public final String getAbreviation() {
		return this.abreviation;
	} // Fin de getAbreviation().__________________________________________


	
	/**
	 * Getter de la civilité complète.<br/>
	 *
	 * @return this.civilite : String.<br/>
	 */
	public final String getCivilite() {
		return this.civilite;
	} // Fin de getCivilite()._____________________________________________
	
		

} // FIN DE L' ENUMERATION EnumCivilites.------------------------------------
