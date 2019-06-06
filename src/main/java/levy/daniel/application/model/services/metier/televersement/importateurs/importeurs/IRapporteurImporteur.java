package levy.daniel.application.model.services.metier.televersement.importateurs.importeurs;

/**
 * INTERFACE IRapporteurImporteur :<br/>
 * RESPONSABILITE : RAPPORTER LES ERREURS E/S 
 * (Fichier null, inexistant, répertoire, vide, ...) 
 * LORS D'UN IMPORT D'UN FICHIER DE DONNEES.<br/>
 * Tout Importeur doit générer 
 * et mettre à disposition de l'application un rapport 
 * en cas d'erreur d'E/S lors de l'import d'un fichier 
 * (HIT, HistonatF07, Darwin csv, ...).<br/>
 * Factorisation des méthodes de rapport d'import 
 * des Importeur.<br/>
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
public interface IRapporteurImporteur {
	
	
	/**
	 * Getter du boolean qui stipule si le Importeur
	 * doit rapporter ou pas 
	 * (rapport d'erreur lors de l'import des fichiers).<br/>
	 * <br/>
	 * 
	 * @return logImport : boolean.<br/>
	 */
	boolean isLogImport();



	/**
	 * Setter du boolean qui stipule si le Importeur
	 * doit rapporter ou pas 
	 * (rapport d'erreur lors de l'import des fichiers).<br/>
	 * <br/>
	 * 
	 * @param pLogImport : boolean.<br/>
	 */
	void setLogImport(
			boolean pLogImport);


		
	/**
	 * Getter du rapport du Importeur.<br/>
	 * <br/>
	 * Ce rapport n'est null que si this.logImport vaut false. 
	 * <b>Tester si il est vide</b>.<br/>
	 * <br/>
	 * 
	 * @return rapportImportStb : StringBuffer.<br/>
	 */
	StringBuffer getRapportImportStb();



	/**
	 * Setter du rapport du Importeur.<br/>
	 * <br/>
	 * Ce rapport n'est null que si this.logImport vaut false. 
	 * <b>Tester si il est vide</b>.<br/>
	 * <br/>
	 * 
	 * @param pRapportImportStb : StringBuffer.<br/>
	 */
	void setRapportImportStb(
			StringBuffer pRapportImportStb);


			
	/**
	 * Fournit la clé du niveau d'anomalie 
	 * stocké dans ressources_externes/messages_techniques.properties.<br/>
	 * <br/>
	 * Si le niveau est 1 (anomalie bloquante), 
	 * une erreur dans la lecture d'une 
	 * ligne de fichier doit arrêter le programme.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleNiveauAnomalie();
	
	
	
	/**
	 * Fournit la clé du log des erreurs
	 * stocké dans ressources_externes/messages_techniques.properties.<br/>
	 * <br/>
	 * Sert à passer le boolean this.logImport à true ou false.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleLogErreur();
	

	
	/**
	 * Récupère le nom de la classe concrète de l'importateur.<br/>
	 * <br/>
	 * Par exemple : "Classe ImporteurHit".<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererNomClasse();
	
	
	
	/**
	 * Fournit dans chaque classe concrète la clé du message
	 * (stocké dans ressources_externes/messages_techniques.properties) 
	 * en cas d'import d'un fichier null.<br/>
	 * <br/>
	 * Par exemple : "importeurhit.importer.filenull".<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleImporterFileNull();
	
	
	
	
	/**
	 * Fournit dans chaque classe concrète la clé du message 
	 * (stocké dans ressources_externes/messages_techniques.properties) 
	 * en cas d'import d'un fichier vide.<br/>
	 * <br/>
	 * Par exemple : "importeurhit.importer.filevide".<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleImporterFileVide();

	
	
	
	/**
	 * Fournit dans chaque classe concrète la clé du message 
	 * (stocké dans ressources_externes/messages_techniques.properties) 
	 * en cas d'import d'un fichier inexistant.<br/>
	 * <br/>
	 * Par exemple : "importeurhit.importer.fileinexistant".<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleImporterFileInexistant();
	


	/**
	 * Fournit dans chaque classe concrète la clé du message 
	 * (stocké dans ressources_externes/messages_techniques.properties) 
	 * en cas d'import d'un répertoire.<br/>
	 * <br/>
	 * Par exemple : "importeurhit.importer.filepasnormal".<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleImporterFilePasNormal();

	
		
} // FIN DE L'INTERFACE IRapporteurImporteur.--------------------------------
