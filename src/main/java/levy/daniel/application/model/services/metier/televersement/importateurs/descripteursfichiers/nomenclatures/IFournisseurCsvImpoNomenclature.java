package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;


/**
 * class IFournisseurCsvImpoNomenclature :<br/>
 * RESPONSABILITE : AFFICHER ET CREER UN FICHIER DE NOMENCLATURE AU FORMAT CSV.<br/>
 * Interface factorisant les méthodes garantissant 
 * qu'un objet qui l'implémente saura s'écrire sous forme de fichier csv 
 * avec séparateur ';'.<br/>
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
 * @since 16 juil. 2014
 *
 */
public interface IFournisseurCsvImpoNomenclature {

	
	
	/**
	 * METHODE_GENERERNOMENCLATURE : String :<br/>
	 * "Méthode genererNomenclatureCsvFile(....) - ".<br/>
	 */
	String METHODE_GENERERNOMENCLATURE 
		= "Méthode genererNomenclatureCsvFile(....) - ";
	
	
		
	/**
	 * method fournirLigneEnTetesCsv() :<br/>
	 * retourne "Clé;Libellé;".<br/>
	 * <br/>
	 *
	 * @return : String : "Clé;Libellé;".<br/>
	 */
	String fournirLigneEnTetesCsv();
	
	
	
	/**
	 * method fournirLigneValeursCsv(int pL) :<br/>
	 * retourne la pL-ième ligne (1-based) de la nomenclature 
	 * sous forme csv avec le séparateur ';'.<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si pL n'existe pas.<br/>
	 * <br/>
	 *
	 * @param pL : int : numéro de la ligne (1-based) 
	 * de la nomenclature à retourner en csv avec séparateur ';'.<br/>
	 * 
	 * @return : String : Ligne en csv.<br/>
	 */
	String fournirLigneValeursCsv(int pL);
	

	
	/**
	 * method genererNomenclatureCsvString() :<br/>
	 * .<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 *
	 * @return : String :  .<br/>
	 */
	String genererNomenclatureCsvString();
	
	
	
	/**
	 * method genererNomenclatureCsvString(
	 * boolean pAvecLigneEntetes) :<br/>
	 * Génère la nomenclature au format csv 
	 * avec séparateur ';' sous forme de String.<br/>
	 * Rajoute la ligne d'en-têtes si pAvecLigneEntetes vaut true.<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 *
	 * @param pAvecLigneEntetes : boolean : boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv produit.<br/>
	 * 
	 * @return : String : La nomenclature au format 
	 * csv avec séparateur ';' sous forme de String.<br/>
	 */
	String genererNomenclatureCsvString(boolean pAvecLigneEntetes);
	

		
	/**
	 * method genererNomenclatureCsvFileUtf8() :<br/>
	 * Génère un fichier csv avec séparateur ';' 
	 * encodé en UTF-8 encapsulant la nomenclature.<br/>
	 * rajoute la ligne d'en-tête.<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_UTF-8.csv.<br/>
	 * <br/>
	 *
	 * @return : File : Le fichier csv généré.<br/>
	 * 
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws FichierInexistantException : si this.nomenclature n'existe pas.<br/>
	 * @throws FichierNullException : si this.nomenclature est null.<br/>
	 */
	File genererNomenclatureCsvFileUtf8() 
				throws IOException
					, FichierNullException
						, FichierInexistantException;
	

	
	/**
	 * method genererNomenclatureCsvFileLatin9() :<br/>
	 * Génère un fichier csv avec séparateur ';' 
	 * encodé en ISO-8859-15 encapsulant la nomenclature.<br/>
	 * rajoute la ligne d'en-tête.<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_ISO-8859-15.csv.<br/>
	 * <br/>
	 *
	 * @return : File : Le fichier csv généré.<br/>
	 * 
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws FichierInexistantException : si this.nomenclature n'existe pas.<br/>
	 * @throws FichierNullException : si this.nomenclature est null.<br/>
	 */
	File genererNomenclatureCsvFileLatin9() 
			throws IOException
				, FichierNullException
					, FichierInexistantException;


	
	/**
	 * method genererNomenclatureCsvFileUtf8(
	 * File pFile) :<br/>
	 * Génère un fichier csv avec séparateur ';' 
	 * encodé en UTF-8 encapsulant la nomenclature.<br/>
	 * rajoute la ligne d'en-tête.<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_UTF-8.csv 
	 * si pFile est null.<br/>
	 * <br/>
	 *
	 * @param pFile : File : le fichier csv à générer.<br/>
	 * 
	 * @return : File : Le fichier csv généré.<br/>
	 * 
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws FichierInexistantException : si this.nomenclature n'existe pas.<br/>
	 * @throws FichierNullException : si this.nomenclature est null.<br/>
	 */
	File genererNomenclatureCsvFileUtf8(File pFile) 
			throws IOException
				, FichierNullException
					, FichierInexistantException;
	

	
	/**
	 * method genererNomenclatureCsvFileLatin9(
	 * File pFile) :<br/>
	 * Génère un fichier csv avec séparateur ';' 
	 * encodé en ISO-8859-15 encapsulant la nomenclature.<br/>
	 * rajoute la ligne d'en-tête.<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_ISO-8859-15.csv 
	 * si pFile est null.<br/>
	 * <br/>
	 *
	 * @param pFile : File : le fichier csv à générer.<br/>
	 * 
	 * @return : File : Le fichier csv généré.<br/>
	 * 
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws FichierInexistantException : si this.nomenclature n'existe pas.<br/>
	 * @throws FichierNullException : si this.nomenclature est null.<br/>
	 */
	File genererNomenclatureCsvFileLatin9(File pFile) 
			throws IOException
				, FichierNullException
					, FichierInexistantException;
	
	
	
	/**
	 * method genererNomenclatureCsvFileUtf8(
	 * boolean pAvecLigneEntetes
	 * , File pFile) :<br/>
	 * Génère un fichier csv avec séparateur ';' 
	 * encodé en UTF-8 encapsulant la nomenclature.<br/>
	 * rajoute la ligne d'en-tête si pAvecLigneEntetes vaut true.<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_UTF-8.csv 
	 * si pFile est null.<br/>
	 * <br/>
	 *
	 * @param pAvecLigneEntetes : boolean : boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv produit.<br/>
	 * @param pFile : File : le fichier csv à générer.<br/>
	 * 
	 * @return : File : Le fichier csv généré.<br/>
	 * 
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws FichierInexistantException : si this.nomenclature n'existe pas.<br/>
	 * @throws FichierNullException : si this.nomenclature est null.<br/>
	 */
	File genererNomenclatureCsvFileUtf8(boolean pAvecLigneEntetes, File pFile)
			throws IOException
			, FichierNullException
				, FichierInexistantException;
	
	
	
	/**
	 * method genererNomenclatureCsvFileLatin9(
	 * boolean pAvecLigneEntetes
	 * , File pFile) :<br/>
	 * Génère un fichier csv avec séparateur ';' 
	 * encodé en ISO-8859-15 encapsulant la nomenclature.<br/>
	 * rajoute la ligne d'en-tête si pAvecLigneEntetes vaut true.<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_ISO-8859-15.csv 
	 * si pFile est null.<br/>
	 * <br/>
	 *
	 * @param pAvecLigneEntetes : boolean : boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv produit.<br/>
	 * @param pFile : File : le fichier csv à générer.<br/>
	 * 
	 * @return : File : Le fichier csv généré.<br/>
	 * 
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws FichierInexistantException : si this.nomenclature n'existe pas.<br/>
	 * @throws FichierNullException : si this.nomenclature est null.<br/>
	 */
	File genererNomenclatureCsvFileLatin9(
			boolean pAvecLigneEntetes, File pFile)
							throws IOException
								, FichierNullException
									, FichierInexistantException;
	
	
	
	/**
	 * method genererNomenclatureCsvFile(
	 * boolean pAvecLigneEntetes
	 * , File pFile
	 * , Charset pCharset) :<br/>
	 * Génère un fichier csv avec séparateur ';' 
	 * encodé en pCharset encapsulant la nomenclature.<br/>
	 * rajoute la ligne d'en-tête si pAvecLigneEntetes vaut true.<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_charset.csv 
	 * si pFile est null.<br/>
	 * <br/>
	 *
	 * @param pAvecLigneEntetes : boolean : boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv produit.<br/>
	 * @param pFile : File : le fichier csv à générer.<br/>
	 * @param pCharset : Charset : l'encodage voulu pour le fichier généré.<br/>
	 * 
	 * @return : File : Le fichier csv généré.<br/>
	 * 
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws FichierInexistantException : si this.nomenclature n'existe pas.<br/>
	 * @throws FichierNullException : si this.nomenclature est null.<br/>
	 */
	File genererNomenclatureCsvFile(
			boolean pAvecLigneEntetes
				, File pFile
					, Charset pCharset) 
							throws IOException
								, FichierNullException
									, FichierInexistantException;
	
	
	
} // FIN DE L'INTERFACE IFournisseurCsvImpoNomenclature._____________________
