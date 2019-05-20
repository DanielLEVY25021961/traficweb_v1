package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription;

/**
 * INTERFACE IRapporteurImpoDesc :<br/>
 * <p>
 * RESPONSABILITE : RAPPORTER LES ERREURS LORS DE 
 * L'IMPORT D'UNE DESCRIPTION.
 * </p>
 * <p>
 * Tout ImportateurDescription doit générer 
 * et mettre à disposition de l'application un rapport 
 * en cas d'erreur lors de l'import d'une Description de fichier.<br/>
 * Factorisation des méthodes de rapport d'import 
 * des ImportateurDescription.<br/>
 * </p>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 30 juin 2014
 *
 */
public interface IRapporteurImpoDesc {
	
	
	/**
	 * Getter du boolean qui stipule si le ImportateurDescription
	 * doit rapporter ou pas 
	 * (rapport d'erreur lors de la lecture des descriptions).<br/>
	 * <br/>
	 * 
	 * @return logImportDescription : boolean.<br/>
	 */
	boolean isLogImportDescription();



	/**
	 * Setter du boolean qui stipule si le ImportateurDescription
	 * doit rapporter ou pas 
	 * (rapport d'erreur lors de la lecture des descriptions).<br/>
	 * 
	 * @param pLogImportDescription : boolean.<br/>
	 */
	void setLogImportDescription(boolean pLogImportDescription);


		
	/**
	 * Getter du rapport du ImportateurDescription.<br/>
	 * <br/>
	 * Ce rapport n'est jamais null. Tester si il est vide.<br/>
	 * <br/>
	 * 
	 * @return rapportImportDescriptionStb : StringBuffer.<br/>
	 */
	StringBuffer getRapportImportDescriptionStb();



	/**
	 * Setter du rapport du ImportateurDescription.<br/>
	 * 
	 * @param pRapportImportDescriptionStb : StringBuffer.<br/>
	 */
	void setRapportImportDescriptionStb(
			StringBuffer pRapportImportDescriptionStb);


			
	/**
	 * Fournit la clé du niveau d'anomalie 
	 * stocké dans messages_techniques.properties.<br/>
	 * <br/>
	 * Si le niveau est 1 (anomalie bloquante), 
	 * une erreur dans la lecture d'une 
	 * ligne de description doit arrêter le programme.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleNiveauAnomalie();
	
	
	
	/**
	 * Fournit la clé du log des erreurs
	 * stocké dans messages_techniques.properties.<br/>
	 * <br/>
	 * Sert à passer le boolean this.logDescription à true ou false.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleLogErreur();
	


} // Fin de L'INTERFACE IRapporteurImpoDesc.---------------------------------
