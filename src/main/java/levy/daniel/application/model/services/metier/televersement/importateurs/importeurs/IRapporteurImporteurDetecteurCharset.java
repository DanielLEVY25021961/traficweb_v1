package levy.daniel.application.model.services.metier.televersement.importateurs.importeurs;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * INTERFACE IRapporteurImporteurDetecteurCharset :<br/>
 * RESPONSABILITE : FOURNIR UN RAPPORT DE DETECTION DE CHARSET.<br/>
 * Interface garantissant que tous les objets l'implémentant 
 * sauront fournir un rapport de détection 
 * des Charsets de chaque ligne d'un fichier à importer.<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 22 juil. 2014
 *
 */
public interface IRapporteurImporteurDetecteurCharset {
	

	
	
	/**
	 * Génère un fichier csv avec séparateur ';' encapsulant 
	 * le rapport de détection des Charsets ligne par ligne.<br/>
	 * Ecrit automatiquement la ligne d'en-tête.<br/>
	 * Ecrit le fichier généré dans le même répertoire 
	 * que this.fichierAImporter avec l'extension 
	 * _rapportDetectionCharsetCsv_ISO-8859-15.csv.<br/>
	 * Encode le fichier généré en ISO-8859-15.<br/>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si this.rapportDetectionCharsetCsv est null.<br/>
	 * - retourne null si pFile est null et 
	 * this.fichierAImporter est introuvable.<br/>
	 * <br/>
	 *
	 * @return : File : Fichier en capsulant 
	 * le rapport de détection de charset.<br/>
	 * 
	 * @throws IOException : lorsque problème d'entrée-sortie.<br/>
	 */
	File genererRapportDetectionCsvLatin9() 
			throws IOException;
	

	
	/**
	 * Génère un fichier csv avec séparateur ';' encapsulant 
	 * le rapport de détection des Charsets ligne par ligne.<br/>
	 * Ecrit automatiquement la ligne d'en-tête.<br/>
	 * Ecrit le fichier généré dans le même répertoire 
	 * que this.fichierAImporter avec l'extension 
	 * _rapportDetectionCharsetCsv_UTF-8.csv.<br/>
	 * Encode le fichier généré en UTF-8.<br/>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si this.rapportDetectionCharsetCsv est null.<br/>
	 * - retourne null si pFile est null et 
	 * this.fichierAImporter est introuvable.<br/>
	 * <br/>
	 *
	 * @return : File : Fichier en capsulant 
	 * le rapport de détection de charset.<br/>
	 * 
	 * @throws IOException : lorsque problème d'entrée-sortie.<br/>
	 */
	File genererRapportDetectionCsvUtf8() 
										throws IOException;


	
	/**
	 * Génère un fichier csv avec séparateur ';' encapsulant 
	 * le rapport de détection des Charsets ligne par ligne.<br/>
	 * Ecrit automatiquement la ligne d'en-tête.<br/>
	 * Ecrit le fichier généré dans pFile.<br/>
	 * Encode le fichier généré en pCharset.<br/>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * Génère automatiquement le fichier de sortie dans le 
	 * même répertoire que this.fichierAImporter 
	 * avec l'extension _rapportDetectionCharsetCsv_charset.csv 
	 * si pFile est null.<br/>
	 * <br/>
	 * - retourne null si this.rapportDetectionCharsetCsv est null.<br/>
	 * - retourne null si pFile est null et 
	 * this.fichierAImporter est introuvable.<br/>
	 * <br/>
	 * 
	 *
	 * @param pCharset : Charset : charset dans lequel 
	 * on encode le fichier de rapport de détection de charset produit.<br/>
	 * @param pFile : File : Fichier encapsulant 
	 * le rapport de détection de charset produit.<br/>
	 * 
	 * @return : File : Fichier en capsulant 
	 * le rapport de détection de charset.<br/>
	 * 
	 * @throws IOException : lorsque problème d'entrée-sortie.<br/>
	 */
	File genererRapportDetectionCsv(
			Charset pCharset
				, File pFile) throws IOException;
	
	

	/**
	 * Getter du boolean qui stipule si les ImporteurAsciiDetecteurCharset
	 * doivent rapporter ou pas 
	 * (rapport de détection de Charset lors de la lecture de fichiers).<br/>
	 * <br/>
	 *
	 * @return logImportDetec : boolean :.<br/>
	 */
	boolean isLogImportDetec();



	/**
	 * Setter du boolean qui stipule si les ImporteurAsciiDetecteurCharset
	 * doivent rapporter ou pas 
	 * (rapport de détection de Charset lors de la lecture de fichiers).<br/>
	 * <br/>
	 *
	 * @param pLogImportDetec : boolean : 
	 * valeur à passer à logImportDetec.<br/>
	 */
	void setLogImportDetec(
			boolean pLogImportDetec);



	/**
	 * Getter de l'Encapsulation du rapport de détection 
	 * des Charsets de chaque ligne du fichier à importer.<br/>
	 * Ce rapport n'est pas null. Tester si il est vide.<br/>
	 * <br/>
	 *
	 * @return rapportDetectionCharset : StringBuffer :.<br/>
	 */
	StringBuffer getRapportDetectionCharset();



	/**
	 * Setter de l'Encapsulation du rapport de détection 
	 * des Charsets de chaque ligne du fichier à importer.<br/>
	 * Ce rapport n'est pas null. Tester si il est vide.<br/>
	 * <br/>
	 *
	 * @param pRapportDetectionCharset : StringBuffer : 
	 * valeur à passer à rapportDetectionCharset.<br/>
	 */
	void setRapportDetectionCharset(
			StringBuffer pRapportDetectionCharset);


	
	/**
	 * Getter de l'Encapsulation du rapport de détection en CSV
	 * des Charsets de chaque ligne du fichier à importer.<br/>
	 * Ce rapport n'est pas null. Tester si il est vide.<br/>
	 * <br/>
	 *
	 * @return rapportDetectionCharsetCsv : StringBuffer.<br/>
	 */
	StringBuffer getRapportDetectionCharsetCsv();
	


	/**
	 * Setter de l'Encapsulation du rapport de détection en CSV
	 * des Charsets de chaque ligne du fichier à importer.<br/>
	 * Ce rapport n'est pas null. Tester si il est vide.<br/>
	 * <br/>
	 *
	 * @param pRapportDetectionCharsetCsv : StringBuffer : 
	 * valeur à passer à rapportDetectionCharsetCsv.<br/>
	 */
	void setRapportDetectionCharsetCsv(
			StringBuffer pRapportDetectionCharsetCsv);



} // FIN DE L'INTERFACE IRapporteurImporteurDetecteurCharset.----------------
