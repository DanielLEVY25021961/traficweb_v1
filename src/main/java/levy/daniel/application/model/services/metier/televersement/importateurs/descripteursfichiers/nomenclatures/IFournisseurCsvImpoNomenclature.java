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
	 * "Méthode genererNomenclatureCsvFile(....) - ".<br/>
	 */
	String METHODE_GENERERNOMENCLATURE 
		= "Méthode genererNomenclatureCsvFile(....) - ";


	
	/**
	 * retourne "Clé;Libellé;".<br/>
	 * <br/>
	 *
	 * @return : String : "Clé;Libellé;".<br/>
	 */
	String fournirLigneEnTetesCsv();
	
	
	
	/**
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
	 * génère une nomenclature au format csv 
	 * avec séparateur ';' sous forme de String.<br/>
	 * Rajoute la ligne d'en-tête.<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 *
	 * @return : String : La nomenclature au format 
	 * csv avec séparateur ';' sous forme de String et avec en-tête.<br/>
	 */
	String genererNomenclatureCsvString();
	
	
	
	/**
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
	 * <b>Génère un fichier csv avec séparateur ';' 
	 * encodé en UTF-8</b> avec en-tête encapsulant la nomenclature 
	 * <code>this.nomenclature</code>.<br/>
	 * Génère le fichier <b>à côte de <code>this.nomenclature</code></b>.<br/>
	 * <ul>
	 * <li>retourne le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_UTF-8.csv.</li>
	 * <li>crée un fichier destination à génerer vide sur disque 
	 * et son arborescence si il n'existe pas.</li>
	 * <li>injecte la nomenclature générée dans le fichier destination 
	 * même si il existait déjà 
	 * (pour prendre en compte une éventuelle modification 
	 * de la nomenclature).</li>
	 * <li>Ajoute le BOM-UTF8 au début du fichier généré.</li>
	 * <li>rajoute la ligne d'en-tête.</li>
	 * <li>retourne null si <code>this.nomenclatureMap</code> est null.</li>
	 * <li><b>Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_UTF-8.csv</b>.</li>
	 * </ul>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
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
	 * <b>Génère un fichier csv avec séparateur ';' 
	 * encodé en ISO-8859-15</b> avec en-tête encapsulant la nomenclature 
	 * <code>this.nomenclature</code>.<br/>
	 * Génère le fichier <b>à côte de <code>this.nomenclature</code></b>.<br/>
	 * <ul>
	 * <li>retourne le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_ISO-8859-15.csv.</li>
	 * <li>crée un fichier destination à génerer vide sur disque 
	 * et son arborescence si il n'existe pas.</li>
	 * <li>injecte la nomenclature générée dans le fichier destination 
	 * même si il existait déjà 
	 * (pour prendre en compte une éventuelle modification 
	 * de la nomenclature).</li>
	 * <li>rajoute la ligne d'en-tête.</li>
	 * <li>retourne null si <code>this.nomenclatureMap</code> est null.</li>
	 * <li><b>Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_ISO-8859-15.csv</b>.</li>
	 * </ul>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
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
	 * <b>Génère un fichier csv avec séparateur ';' 
	 * encodé en UTF-8</b> dans pFile encapsulant la nomenclature 
	 * <code>this.nomenclature</code>.<br/>
	 * <ul>
	 * <li>retourne le fichier généré.</li>
	 * <li>crée un fichier destination pFile à génerer VIDE sur disque 
	 * et son arborescence si il n'existe pas.</li>
	 * <li>injecte la nomenclature générée dans le fichier destination 
	 * pFile même si il existait déjà 
	 * (pour prendre en compte une éventuelle modification 
	 * de la nomenclature).</li>
	 * <li>Ajoute le BOM-UTF8 au début du fichier généré.</li>
	 * <li>rajoute la ligne d'en-tête.</li>
	 * <li>retourne null si <code>this.nomenclatureMap</code> est null.</li>
	 * <li><b>Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_UTF-8.csv 
	 * si pFile est null</b>.</li>
	 * </ul>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 *
	 * @param pFile : File : le fichier csv à générer 
	 * <i>(this.nomenclature avec l'extension _genere_UTF-8.csv 
	 * si pFile est null)</i>.<br/>
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
	 * <b>Génère un fichier csv avec séparateur ';' 
	 * encodé en ISO-8859-15</b> dans pFile encapsulant la nomenclature 
	 * <code>this.nomenclature</code>.<br/>
	 * <ul>
	 * <li>retourne le fichier généré.</li>
	 * <li>crée un fichier destination pFile à génerer VIDE sur disque 
	 * et son arborescence si il n'existe pas.</li>
	 * <li>injecte la nomenclature générée dans le fichier destination 
	 * pFile même si il existait déjà 
	 * (pour prendre en compte une éventuelle modification 
	 * de la nomenclature).</li>
	 * <li>rajoute la ligne d'en-tête.</li>
	 * <li>retourne null si <code>this.nomenclatureMap</code> est null.</li>
	 * <li><b>Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_ISO-8859-15.csv 
	 * si pFile est null</b>.</li>
	 * </ul>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 *
	 * @param pFile : File : le fichier csv à générer 
	 * <i>(this.nomenclature avec l'extension _genere_ISO-8859-15.csv 
	 * si pFile est null)</i>.<br/>
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
	 * <b>Génère un fichier csv avec séparateur ';' 
	 * encodé en UTF-8</b> dans pFile encapsulant la nomenclature 
	 * <code>this.nomenclature</code>.<br/>
	 * <ul>
	 * <li>retourne le fichier généré.</li>
	 * <li>crée un fichier destination pFile à génerer VIDE sur disque 
	 * et son arborescence si il n'existe pas.</li>
	 * <li>injecte la nomenclature générée dans le fichier destination 
	 * pFile même si il existait déjà 
	 * (pour prendre en compte une éventuelle modification 
	 * de la nomenclature).</li>
	 * <li>Ajoute le BOM-UTF8 au début du fichier généré.</li>
	 * <li>rajoute la ligne d'en-tête si pAvecLigneEntetes vaut true.</li>
	 * <li>retourne null si <code>this.nomenclatureMap</code> est null.</li>
	 * <li><b>Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_UTF-8.csv 
	 * si pFile est null</b>.</li>
	 * </ul>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 *
	 * @param pAvecLigneEntetes : boolean : boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv produit 
	 * (ajouter la ligne d'en-tête si true).<br/>
	 * @param pFile : File : le fichier csv à générer.<br/>
	 * 
	 * @return : File : Le fichier csv généré<i> 
	 * (this.nomenclature avec l'extension _genere_UTF-8.csv 
	 * si pFile est null)</i>.<br/>
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
	 * <b>Génère un fichier csv avec séparateur ';' 
	 * encodé en ISO-8859-15</b>  dans pFile encapsulant la nomenclature 
	 * <code>this.nomenclature</code>.<br/>
	 * <ul>
	 * <li>retourne le fichier généré.</li>
	 * <li>crée un fichier destination pFile à génerer VIDE sur disque 
	 * et son arborescence si il n'existe pas.</li>
	 * <li>injecte la nomenclature générée dans le fichier destination 
	 * pFile même si il existait déjà 
	 * (pour prendre en compte une éventuelle modification 
	 * de la nomenclature).</li>
	 * <li>rajoute la ligne d'en-tête si pAvecLigneEntetes vaut true.</li>
	 * <li>retourne null si <code>this.nomenclatureMap</code> est null.</li>
	 * <li><b>Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_ISO-8859-15.csv 
	 * si pFile est null</b>.</li>
	 * </ul>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 *
	 * @param pAvecLigneEntetes : boolean : boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv produit.<br/>
	 * @param pFile : File : le fichier csv à générer.<br/>
	 * 
	 * @return : File : Le fichier csv généré 
	 * <i>(this.nomenclature avec l'extension _genere_ISO-8859-15.csv 
	 * si pFile est null)</i>
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
	 * <b>Génère un fichier csv avec séparateur ';' pFile
	 * encodé en pCharset</b> (avec la ligne d'en-tête 
	 * si pAvecLigneEntetes==true)  
	 * encapsulant la nomenclature 
	 * <code>this.nomenclature</code>.<br/>
	 * <ul>
	 * <li>retourne le fichier généré.</li>
	 * <li>crée un fichier destination pFile à génerer VIDE sur disque 
	 * et son arborescence si il n'existe pas.</li>
	 * <li>injecte la nomenclature générée dans le fichier destination 
	 * pFile même si il existait déjà 
	 * (pour prendre en compte une éventuelle modification 
	 * de la nomenclature).</li>
	 * <li>choisit automatiquement le Charset UTF-8 si pCharset == null.</li>
	 * <li>Ajoute le BOM-UTF8 au début du fichier généré si charset 
	 * vaut Charset-UTF8.</li>
	 * <li>rajoute la ligne d'en-tête si pAvecLigneEntetes vaut true.</li>
	 * <li>retourne null si <code>this.nomenclatureMap</code> est null.</li>
	 * <li><b>Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.nomenclature avec l'extension _genere_charset.csv 
	 * si pFile est null</b>.</li>
	 * </ul>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 *
	 * @param pAvecLigneEntetes : boolean : boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv produit.<br/>
	 * @param pFile : File : le fichier csv à générer.<br/>
	 * @param pCharset : Charset : l'encodage voulu pour le fichier généré.<br/>
	 * 
	 * @return : File : Le fichier csv généré 
	 * <i>(this.nomenclature avec l'extension _genere_Charset.csv 
	 * si pFile est null)</i>
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
