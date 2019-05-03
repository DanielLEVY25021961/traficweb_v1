package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps;

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
	 * Getter du boolean qui stipule si le DescriptionChamp
	 * doit rapporter ou pas 
	 * (rapport d'erreur lors de la lecture des descriptions).<br/>
	 * <br/>
	 * 
	 * @return logDescription : boolean.<br/>
	 */
	boolean isLogDescription();



	/**
	 * Setter du boolean qui stipule si le DescriptionChamp
	 * doit rapporter ou pas 
	 * (rapport d'erreur lors de la lecture des descriptions).<br/>
	 * <br/>
	 * 
	 * @param pLogDescription : boolean.<br/>
	 */
	void setLogDescription(boolean pLogDescription);


		
	/**
	 * Getter du rapport du DescriptionChamp.<br/>
	 * 
	 * @return rapportDescriptionStb : StringBuffer.<br/>
	 */
	StringBuffer getRapportDescriptionStb();



	/**
	 * Setter du rapport du DescriptionChamp.<br/>
	 * 
	 * @param pRapportDescriptionStb : StringBuffer.<br/>
	 */
	void setRapportDescriptionStb(StringBuffer pRapportDescriptionStb);


			
	/**
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
