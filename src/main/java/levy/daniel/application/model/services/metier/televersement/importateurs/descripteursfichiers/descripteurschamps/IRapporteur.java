package levy.daniel.application.metier.importateurs.descripteursfichiers.descripteurschamps;

/**
 * class IRapporteur :<br/>
 * RESPONSABILITE : RAPPORTER LES ERREURS LORS DE L'IMPORT D'UNE DESCRIPTION.<br/>
 * Factorisation des méthodes de rapport de lecture des DescriptionChamp.<br/>
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
 * @since 29 juin 2014
 *
 */
public interface IRapporteur {
	
	
	/**
	 * method isLogDescription() :<br/>
	 * Getter du boolean qui stipule si le DescriptionChamp
	 * doit rapporter ou pas 
	 * (rapport d'erreur lors de la lecture des descriptions).<br/>
	 * <br/>
	 * 
	 * @return logDescription : boolean.<br/>
	 */
	boolean isLogDescription();



	/**
	 * method setLogDescription(
	 * boolean pLogDescription) :<br/>
	 * Setter du boolean qui stipule si le DescriptionChamp
	 * doit rapporter ou pas 
	 * (rapport d'erreur lors de la lecture des descriptions).<br/>
	 * <br/>
	 * 
	 * @param pLogDescription : boolean.<br/>
	 */
	void setLogDescription(
			final boolean pLogDescription);


		
	/**
	 * method getRapportDescriptionStb() :<br/>
	 * Getter du rapport du DescriptionChamp.<br/>
	 * <br/>
	 * 
	 * @return rapportDescriptionStb : StringBuffer.<br/>
	 */
	StringBuffer getRapportDescriptionStb();



	/**
	 * method setRapportDescriptionStb(
	 * StringBuffer pRapportDescriptionStb) :<br/>
	 * Setter du rapport du DescriptionChamp.<br/>
	 * <br/>
	 * 
	 * @param pRapportDescriptionStb : StringBuffer.<br/>
	 */
	void setRapportDescriptionStb(
			final StringBuffer pRapportDescriptionStb);


			
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
	 * @return : String : "descriptionchamphistof07.niveau.anomalie" 
	 * pour un DescriptionChampHistoF07.<br/>
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
	 * @return : String : "descriptionchamphistof07.log.erreur" 
	 * pour un DescriptionChampHistoF07.<br/>
	 */
	String recupererCleLogErreur();
	


} // FIN DE L'INTERFACE IRapporteur.-----------------------------------------
