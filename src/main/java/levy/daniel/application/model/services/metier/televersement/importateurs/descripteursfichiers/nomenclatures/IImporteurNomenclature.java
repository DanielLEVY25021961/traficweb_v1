package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import levy.daniel.application.IConstantesSautsLigne;
import levy.daniel.application.IConstantesSeparateurs;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierPasNormalException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.NomenclatureMauvaiseRunTimeException;

/**
 * INTERFACE IImporteurNomenclature :<br/>
 * <p>
 * RESPONSABILITE : IMPORTER LES NOMENCLATURES.<br/>
 * Interface factorisant les méthodes des ImporteurNomenclature.<br/>
 * </p>
 * 
 * <p>
 *  Une <b>nomenclature</b> doit être un fichier csv avec séparateur ';' 
 *  encodé en UTF-8
 * sous forme [Integer, String] signifiant [Clé, Libellé] comme par exemple 
 * pour le SENS dans un HIT :<br/>
 * 1; sens des PR croissants pour route à 2 sens;<br/>
 * 2; sens des PR décroissants pour route à 2 sens;<br/>
 * 3; sens confondus pour une route à 2 sens;<br/>
 * ...........................................<br/>
 *</p>
 *
 * <p>
 * La <b>clé est donc toujours entière dans une nomenclature</b>.
 * </p>
 *
 *<div>
 *<ul>
 * <li>
 * Tous les ImporteurNomenclature possèdent une 
 * méthode <b><code>importerNomenclature(File pNomenclature)</code></b> 
 * où pNomenclature encapsule la nomenclature en csv de la donnée 
 * (sens, nature, catégorie administrative, ...) à servir.
 * </li>
 * <li>
 * La nomenclature est servie sous forme de 
 * <b>SortedMap&lt;Integer, String&gt; <code>nomenclatureMap</code></b> 
 * retournée par importerNomenclature(File pNomenclature).
 * </li>
 * <li>
 * La méthode importerNomenclature(File pNomenclature) permet également 
 * d'alimenter un <b>Set&lt;Integer&gt; <code>clesPossiblesSet</code></b> 
 * qui contient toutes les valeurs possibles pour la clé dans la nomenclature.
 * </li>
 * </ul>
 * </div>
 * 
 * <p>
 * <b><span style="text-decoration:underline;">
 * Diagramme de classe du ImportateurNomenclature : 
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../../../../../javadoc/images/model/services/metier/televersement/importateurs/descripteursfichiers/nomenclatures/diagramme_de_classes_ImporteurNomenclature.png" 
 * alt="Diagramme de classe du ImportateurNomenclature" />
 * </p>
 * 
 * <br/>
 *
 * <p>
 * - Exemple d'utilisation :
 * </p>
 * <p>
 * <code> // Instanciation d'un ImporteurNomenclature</code><br/>
 * <code><b>IImporteurNomenclature IMPORTEUR_NOMENCLATURE = new ImporteurNomenclature();</b></code><br/>
 * <code> // Import du fichier de nomenclature nomenclatureFile</code><br/>
 * <code><b>Map&lt;Integer, String&gt; resultat = IMPORTEUR_NOMENCLATURE.importerNomenclatureEnUtf8(nomenclatureFile);</b></code><br/>
 * <code> // Récupération du Set de clés possibles</code><br/>
 * <code><b>Set&lt;Integer&gt; clesPossiblesSet = IMPORTEUR_NOMENCLATURE.getClesPossiblesSet();</b></code><br/>
 * <code> // Génère la nomenclature importée dans le fichier nomenclatureGenereeFile avec en-tête et encodée en UTF-8.</code><br/>
 * <code><b>IMPORTEUR_NOMENCLATURE.genererNomenclatureCsvFile(true, nomenclatureGenereeFile, charsetUtf8);</b></code><br/>
 * </p>
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
public interface IImporteurNomenclature 
	extends IFournisseurJTableNomenclature
				, IFournisseurCsvImpoNomenclature
					, IConstantesSautsLigne, IConstantesSeparateurs {
	
	
	/**
	 * "Méthode importerNomenclature(File pNomenclature)".<br/>
	 */
	String METHODE_IMPORTER_NOMENCLATURE_EN_LATIN9 
		= "Méthode importerNomenclature(File pNomenclature)";
	

	
	/**
	 * "Méthode importerNomenclatureEnUtf8(File pNomenclature)".<br/>
	 */
	String METHODE_IMPORTER_NOMENCLATURE_EN_UTF8 
		= "Méthode importerNomenclatureEnUtf8(File pNomenclature)";
	
	
	
	/**
	 * Importe une Nomenclature encapsulée dans le fichier csv 
	 * encodé en LATIN9 pNomenclature 
	 * et la retourne sous forme de 
	 * SortedMap&lt;Integer, String&gt; nomenclatureMap avec : <br/>
	 * - Integer : la clé dans la nomenclature.<br/>
	 * - String : le libellé dans la nomenclature.<br/>
	 * <ul>
	 * <li>vérifie que la nomenclature est au bon format CSV 
	 * [Integer;String;].</li>
	 * <li>Alimente automatiquement le File 
	 * <code>this.nomenclature</code>.</li>
	 * <li>Alimente automatiquement le set des clés possibles 
	 * <code>this.clesPossiblesSet</code>.<br/></li>
	 * </ul>
	 * - LOG.FATAL et jette une exception si pNomenclature est incorrect 
	 * (null, vide, inexistant, répertoire, mauvaise nomenclature).<br/>
	 * <br/>
	 *
	 * @param pNomenclature : File : le fichier csv encapsulant 
	 * la nomenclature.<br/>
	 * 
	 * @return : SortedMap&lt;Integer, String&gt; : 
	 * la map triée encapsulant la nomenclature.<br/>
	 * 
	 * @throws FichierNullException : si pNomenclature est null.<br/>
	 * @throws FichierVideException  : si pNomenclature est vide.<br/>
	 * @throws FichierInexistantException  : si pNomenclature n'existe pas.<br/>
	 * @throws FichierPasNormalException : si pNomenclature est un répertoire.<br/>
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws NomenclatureMauvaiseRunTimeException : 
	 * si la nomenclature n'est pas au format CSV ou 
	 * pas de le forme [Integer;String;].<br/>
	 * @throws Exception 
	 */
	SortedMap<Integer, String> importerNomenclatureEnLatin9(
			File pNomenclature) 
					throws FichierNullException
						, FichierVideException
							, FichierInexistantException
								, FichierPasNormalException
									, IOException
										, Exception;
	
	
	
	/**
	 * Importe une Nomenclature encapsulée dans le fichier csv 
	 * encodé en UTF-8 pNomenclature 
	 * et la retourne sous forme de 
	 * SortedMap&lt;Integer, String&gt; nomenclatureMap avec : <br/>
	 * - Integer : la clé dans la nomenclature.<br/>
	 * - String : le libellé dans la nomenclature.<br/>
	 * <ul>
	 * <li>vérifie que la nomenclature est au bon format CSV 
	 * [Integer;String;].</li>
	 * <li>Alimente automatiquement le File 
	 * <code>this.nomenclature</code>.</li>
	 * <li>Alimente automatiquement le set des clés possibles 
	 * <code>this.clesPossiblesSet</code>.<br/></li>
	 * </ul>
	 * - LOG.FATAL et jette une exception si pNomenclature est incorrect 
	 * (null, vide, inexistant, répertoire, mauvaise nomenclature).<br/>
	 * <br/>
	 *
	 * @param pNomenclature : File : le fichier csv encapsulant 
	 * la nomenclature.<br/>
	 * 
	 * @return : SortedMap&lt;Integer, String&gt; : 
	 * la map triée encapsulant la nomenclature.<br/>
	 * 
	 * @throws FichierNullException : si pNomenclature est null.<br/>
	 * @throws FichierVideException  : si pNomenclature est vide.<br/>
	 * @throws FichierInexistantException  : si pNomenclature n'existe pas.<br/>
	 * @throws FichierPasNormalException : si pNomenclature est un répertoire.<br/>
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws NomenclatureMauvaiseRunTimeException : 
	 * si la nomenclature n'est pas au format CSV ou 
	 * pas de le forme [Integer;String;].<br/>
	 * @throws Exception 
	 */
	SortedMap<Integer, String> importerNomenclatureEnUtf8(
			File pNomenclature) 
						throws FichierNullException
							, FichierVideException
								, FichierInexistantException
									, FichierPasNormalException
										, IOException, Exception;

	
	
	/**
	 * <b>importe une nomenclature</b> pNomenclature (fichier csv avec 
	 * [cle : Integer - libellé : String]) 
	 * en la <b>décodant avec pCharset</b> et la 
	 * <b>retourne sous forme de SortedMap&lt;Integer, String&gt;</b> avec : <br/>
	 * - Integer : la clé dans la nomenclature.<br/>
	 * - String : le libellé dans la nomenclature.<br/>
	 * <ul>
	 * <li>vérifie que la nomenclature est au bon format CSV 
	 * [Integer;String;].</li>
	 * <li>Alimente automatiquement le File 
	 * <code>this.nomenclature</code>.</li>
	 * <li>Alimente automatiquement le set des clés possibles 
	 * <code>this.clesPossiblesSet</code>.<br/></li>
	 * <li>choisit automatiquement le Charset UTF-8 si pCharset == null.</li>
	 * <li>utilise <code>InputStreamReader isr 
	 * = new InputStreamReader(fis, charset);</code> pour lire le fichier.</li>
	 * <li>utilise la Regex 
	 * <code>Pattern.compile(SEP_PV).split(ligneLue)</code> 
	 * pour casser les lignes de nomenclature</li>
	 * </ul>
	 * - LOG.FATAL et jette une exception si pNomenclature est incorrect 
	 * (null, vide, inexistant, répertoire, mauvaise nomenclature).<br/>
	 * <br/>
	 *
	 * @param pNomenclature : File : fichier csv contenant la nomenclature.
	 * @param pMethode : String : 
	 * nom de la méthode appelant la présente.
	 * @param pCharset : Charset : 
	 * charset d'encodage de la nomenclature pNomenclature
	 * 
	 * @return this.nomenclatureMap : SortedMap<Integer,String> : 
	 * la nomenclature sous forme de Map.<br/>
	 * 
	 * @throws FichierNullException : si pNomenclature est null.<br/>
	 * @throws FichierVideException  : si pNomenclature est vide.<br/>
	 * @throws FichierInexistantException  : si pNomenclature n'existe pas.<br/>
	 * @throws FichierPasNormalException : si pNomenclature est un répertoire.<br/>
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws NomenclatureMauvaiseRunTimeException : 
	 * si la nomenclature n'est pas au format CSV ou 
	 * pas de le forme [Integer;String;].<br/>
	 * @throws Exception 
	 */
	SortedMap<Integer, String> importerNomenclature(
			File pNomenclature
				, String pMethode
					, Charset pCharset) 
							throws Exception;	
	
	
	
	/**
	 * Récupère le nom de la classe.<br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererNomClasse();
	
	
	
	/**
	 * Fournit la clé du message en cas d'import d'un fichier null.<br/>
	 * stocké dans ressources_externes/messages_techniques.properties.<br/>
	 * <code>"importeurnomenclature.importer.filenull"</code><br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleImporterFileNull();
	
	
		
	/**
	 * Fournit la clé du message en cas d'import d'un fichier vide.<br/>
	 * stocké dans ressources_externes/messages_techniques.properties.<br/>
	 * <code>"importeurnomenclature.importer.filevide"</code><br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleImporterFileVide();

	
		
	/**
	 * Fournit la clé du message en cas d'import d'un fichier inexistant.<br/>
	 * stocké dans ressources_externes/messages_techniques.properties.<br/>
	 * <code>"importeurnomenclature.importer.fileinexistant"</code><br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleImporterFileInexistant();
	


	/**
	 * Fournit la clé du message en cas d'import d'un répertoire.<br/>
	 * stocké dans ressources_externes/messages_techniques.properties.<br/>
	 * <code>"importeurnomenclature.importer.filepasnormal"</code><br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleImporterFilePasNormal();

	

	/**
	 * Getter de la SortedMap&lt;Integer, String&gt; triée avec : <br/>
	 * - Integer : la clé dans la nomenclature.<br/>
	 * - String : le libellé dans la nomenclature.<br/>
	 * <br/>
	 *
	 * @return nomenclatureMap : SortedMap&lt;Integer, String&gt;.<br/>
	 */
	SortedMap<Integer, String> getNomenclatureMap();
	
	
		
	/**
	 * Setter de la SortedMap&lt;Integer, String&gt; triée avec : <br/>
	 * - Integer : la clé dans la nomenclature.<br/>
	 * - String : le libellé dans la nomenclature.<br/>
	 * <br/>
	 *
	 * @param pNomenclatureMap : SortedMap&lt;Integer, String&gt; : 
	 * valeur à passer à nomenclatureMap.<br/>
	 */
	void setNomenclatureMap(
			SortedMap<Integer, String> pNomenclatureMap);
	

	
	/**
	 * Getter du File en csv avec séparateur ';' 
	 * qui encapsule la nomenclature.<br/>
	 * <br/>
	 *
	 * @return nomenclature : File.<br/>
	 */
	File getNomenclature();
	
	
	
	/**
	 * Setter du File en csv avec séparateur ';' 
	 * qui encapsule la nomenclature.<br/>
	 * <br/>
	 *
	 * @param pNomenclature : File : valeur à passer à nomenclature.<br/>
	 */
	void setNomenclature(
			File pNomenclature);
	

	
	/**
	 * Getter de l'Ensemble des valeurs de clés possibles 
	 * pour la nomenclature.<br/>
	 * <br/>
	 *
	 * @return clesPossiblesSet : Set&lt;Integer&gt;.<br/>
	 */
	Set<Integer> getClesPossiblesSet();
	
	
	
	/**
	 * Setter de l'Ensemble des valeurs de clés possibles 
	 * pour la nomenclature.<br/>
	 * <br/>
	 *
	 * @param pClesPossiblesSet : Set&lt;Integer&gt; : 
	 * valeur à passer à clesPossiblesSet.<br/>
	 */
	void setClesPossiblesSet(
			Set<Integer> pClesPossiblesSet);
	
	
	
	/**
	 * retourne une String pour l'affichage de 
	 * <code>this.nomenclatureMap</code> à la console.<br/>
	 * <br/>
	 * - retourne null si this.nomenclatureMap == null.<br/>
	 * <br/>
	 *
	 * @return : String : String pour affichage.<br/>
	 */
	String afficherNomenclatureMap();	
	
	
	
	/**
	 * retourne une String pour l'affichage 
	 * à la console d'une Map&lt;Integer, String&gt;.<br/>
	 * <br/>
	 * - retourne null si pMap == null.<br/>
	 * <br/>
	 * 
	 *
	 * @param pMap : Map&lt;Integer, String&gt;
	 * 
	 * @return : String : String pour affichage.<br/>
	 */
	String afficherMapIntegerString(Map<Integer, String> pMap);
	
	
	
} // FIN DE L'INTERFACE IImporteurNomenclature.------------------------------
