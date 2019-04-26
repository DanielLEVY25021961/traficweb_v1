package levy.daniel.application.metier.importateurs.descripteursfichiers.importateursdescription;

/**
 * class IRapporteurImpoDesc :<br/>
 * RESPONSABILITE : RAPPORTER LES ERREURS LORS DE L'IMPORT D'UNE DESCRIPTION.<br/>
 * Tout ImportateurDescription doit générer 
 * et mettre à disposition de l'application un rapport 
 * en cas d'erreur lors de l'import d'une Description de fichier.<br/>
 * Factorisation des méthodes de rapport d'import 
 * des ImportateurDescription.<br/>
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
	 * method isLogImportDescription() :<br/>
	 * Getter du boolean qui stipule si le ImportateurDescription
	 * doit rapporter ou pas 
	 * (rapport d'erreur lors de la lecture des descriptions).<br/>
	 * <br/>
	 * 
	 * @return logImportDescription : boolean.<br/>
	 */
	boolean isLogImportDescription();



	/**
	 * method setLogImportDescription(
	 * boolean pLogImportDescription) :<br/>
	 * Setter du boolean qui stipule si le ImportateurDescription
	 * doit rapporter ou pas 
	 * (rapport d'erreur lors de la lecture des descriptions).<br/>
	 * <br/>
	 * 
	 * @param pLogImportDescription : boolean.<br/>
	 */
	void setLogImportDescription(
			final boolean pLogImportDescription);


		
	/**
	 * method getRapportImportDescriptionStb() :<br/>
	 * Getter du rapport du ImportateurDescription.<br/>
	 * <br/>
	 * Ce rapport n'est jamais null. Tester si il est vide.<br/>
	 * <br/>
	 * 
	 * @return rapportImportDescriptionStb : StringBuffer.<br/>
	 */
	StringBuffer getRapportImportDescriptionStb();



	/**
	 * method setRapportImportDescriptionStb(
	 * StringBuffer pRapportImportDescriptionStb) :<br/>
	 * Setter du rapport du ImportateurDescription.<br/>
	 * <br/>
	 * 
	 * @param pRapportImportDescriptionStb : StringBuffer.<br/>
	 */
	void setRapportImportDescriptionStb(
			final StringBuffer pRapportImportDescriptionStb);


			
	/**
	 * method recupererCleNiveauAnomalie() :<br/>
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
	 * method recupererCleLogErreur() :<br/>
	 * Fournit la clé du log des erreurs
	 * stocké dans messages_techniques.properties.<br/>
	 * <br/>
	 * Sert à passer le boolean this.logDescription à true ou false.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleLogErreur();
	



} // Fin de IRapporteurImpoDesc.---------------------------------------------
