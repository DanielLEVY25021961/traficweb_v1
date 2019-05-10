package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierPasNormalException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.NomenclatureMauvaiseRunTimeException;

/**
 * INTERFACE IImporteurLexique :<br/>
 * RESPONSABILITE : IMPORTER LES LEXIQUES.<br/>
 * Interface factorisant les méthodes des ImporteurLexique.<br/>
 * <br/>
 * Un lexique doit être un fichier csv avec séparateur ';' 
 * sous forme [String, String] signifiant [Clé, Libellé] comme par exemple 
 * pour le PROFIL EN TRAVERS dans un HISTO_F07 :<br/>
 * 1V;chaussée unique 1 voie;<br/>
 * 2V<7;chaussée unique 2 voies < 7m;<br/>
 * 2V>=7;chaussée unique 2 voies >= 7m;<br/>
 * ...........................................<br/>
 * <br/>
 * Tous les ImporteurLexique possèdent une 
 * méthode importerLexique(File pLexique) 
 * où pLexique encapsule le lexique en csv de la donnée 
 * (sens, nature, catégorie administrative, ...) à servir.<br/>
 * Le lexique est servi sous forme de 
 * SortedMap&lt;String, String&gt; lexiqueMap 
 * retournée par importerLexique(File pLexique).<br/>
 * La méthode importerLexique(File pLexique) permet également 
 * d'alimenter un Set&lt;String&gt; clesPossiblesSet 
 * qui contient toutes les valeurs possibles pour la clé dans le lexique.<br/>
 * <br/>
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
public interface IImporteurLexique 
	extends IFournisseurJTableNomenclature
				, IFournisseurCsvImpoNomenclature {
	
	
	/**
	 * "Méthode importerLexique(File pLexique)".<br/>
	 */
	String METHODE_IMPORTER_LEXIQUE_EN_LATIN9 
		= "Méthode importerLexique(File pLexique)";
	

	
	/**
	 * "Méthode importerLexiqueEnUtf8(File pLexique)".<br/>
	 */
	String METHODE_IMPORTER_LEXIQUE_EN_UTF8 
		= "Méthode importerLexiqueEnUtf8(File pLexique)";
	
	
	
	/**
	 * Importe un Lexique encapsulé dans le fichier csv 
	 * encodé en LATIN9 pLexique 
	 * et le retourne sous forme de 
	 * SortedMap&lt;String, String&gt; lexiqueMap avec : <br/>
	 * - String : la clé dans le lexique.<br/>
	 * - String : le libellé dans le lexique.<br/>
	 * <ul>
	 * <li>vérifie que le lexique est au bon format CSV 
	 * [String;String;].</li>
	 * <li>Alimente automatiquement le File 
	 * <code>this.lexique</code>.</li>
	 * <li>Alimente automatiquement le set des clés possibles 
	 * <code>this.clesPossiblesSet</code>.<br/></li>
	 * </ul>
	 * - LOG.FATAL et jette une exception si pLexique est incorrect 
	 * (null, vide, inexistant, répertoire, mauvais lexique).<br/>
	 * <br/>
	 *
	 * @param pLexique : File : le fichier csv encapsulant 
	 * le lexique.<br/>
	 * 
	 * @return : SortedMap&lt;String, String&gt; : 
	 * la map triée encapsulant le lexique.<br/>
	 * 
	 * @throws FichierNullException : si pLexique est null.<br/>
	 * @throws FichierVideException  : si pLexique est vide.<br/>
	 * @throws FichierInexistantException  : si pLexique n'existe pas.<br/>
	 * @throws FichierPasNormalException : si pLexique est un répertoire.<br/>
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws NomenclatureMauvaiseRunTimeException : 
	 * si le lexique n'est pas au format CSV ou 
	 * pas de le forme [String;String;].<br/>
	 * @throws Exception 
	 */
	SortedMap<String, String> importerLexiqueEnLatin9(
			File pLexique) 
					throws FichierNullException
						, FichierVideException
							, FichierInexistantException
								, FichierPasNormalException
									, IOException
										, Exception;
	
	
	
	/**
	 * Importe un Lexique encapsulé dans le fichier csv 
	 * encodé en UTF-8 pLexique 
	 * et le retourne sous forme de 
	 * SortedMap&lt;String, String&gt; lexiqueMap avec : <br/>
	 * - String : la clé dans le lexique.<br/>
	 * - String : le libellé dans le lexique.<br/>
	 * <ul>
	 * <li>vérifie que le lexique est au bon format CSV 
	 * [String;String;].</li>
	 * <li>Alimente automatiquement le File 
	 * <code>this.lexique</code>.</li>
	 * <li>Alimente automatiquement le set des clés possibles 
	 * <code>this.clesPossiblesSet</code>.<br/></li>
	 * </ul>
	 * - LOG.FATAL et jette une exception si pLexique est incorrect 
	 * (null, vide, inexistant, répertoire, mauvais lexique).<br/>
	 * <br/>
	 *
	 * @param pLexique : File : le fichier csv encapsulant 
	 * le lexique.<br/>
	 * 
	 * @return : SortedMap&lt;String, String&gt; : 
	 * la map triée encapsulant le lexique.<br/>
	 * 
	 * @throws FichierNullException : si pLexique est null.<br/>
	 * @throws FichierVideException  : si pLexique est vide.<br/>
	 * @throws FichierInexistantException  : si pLexique n'existe pas.<br/>
	 * @throws FichierPasNormalException : si pLexique est un répertoire.<br/>
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws NomenclatureMauvaiseRunTimeException : 
	 * si le lexique n'est pas au format CSV ou 
	 * pas de le forme [String;String;].<br/>
	 * @throws Exception 
	 */
	SortedMap<String, String> importerLexiqueEnUtf8(
			File pLexique) 
						throws FichierNullException
							, FichierVideException
								, FichierInexistantException
									, FichierPasNormalException
										, IOException, Exception;

	
	
	/**
	 * <b>importe un lexique</b> pLexique (fichier csv avec 
	 * [cle : String - libellé : String]) 
	 * en le <b>décodant avec pCharset</b> et le 
	 * <b>retourne sous forme de SortedMap&lt;String, String&gt;</b> avec : <br/>
	 * - String : la clé dans le lexique.<br/>
	 * - String : le libellé dans le lexique.<br/>
	 * <ul>
	 * <li>vérifie que le lexique est au bon format CSV 
	 * [String;String;].</li>
	 * <li>Alimente automatiquement le File 
	 * <code>this.lexique</code>.</li>
	 * <li>Alimente automatiquement le set des clés possibles 
	 * <code>this.clesPossiblesSet</code>.<br/></li>
	 * <li>choisit automatiquement le Charset UTF-8 si pCharset == null.</li>
	 * <li>utilise <code>InputStreamReader isr 
	 * = new InputStreamReader(fis, charset);</code> pour lire le fichier.</li>
	 * <li>utilise la Regex 
	 * <code>Pattern.compile(SEP_PV).split(ligneLue)</code> 
	 * pour casser les lignes de lexique</li>
	 * </ul>
	 * - LOG.FATAL et jette une exception si pLexique est incorrect 
	 * (null, vide, inexistant, répertoire, mauvais lexique).<br/>
	 * <br/>
	 *
	 * @param pLexique : File : fichier csv contenant le lexique.
	 * @param pMethode : String : 
	 * nom de la méthode appelant la présente.
	 * @param pCharset : Charset : 
	 * charset d'encodage de le lexique pLexique
	 * 
	 * @return this.lexiqueMap : SortedMap<Integer,String> : 
	 * le lexique sous forme de Map.<br/>
	 * 
	 * @throws FichierNullException : si pLexique est null.<br/>
	 * @throws FichierVideException  : si pLexique est vide.<br/>
	 * @throws FichierInexistantException  : si pLexique n'existe pas.<br/>
	 * @throws FichierPasNormalException : si pLexique est un répertoire.<br/>
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws NomenclatureMauvaiseRunTimeException : 
	 * si le lexique n'est pas au format CSV ou 
	 * pas de le forme [String;String;].<br/>
	 * @throws Exception 
	 */
	SortedMap<String, String> importerLexique(
			File pLexique
				, String pMethode
					, Charset pCharset) 
							throws Exception;	
	
	
	
	/**
	 * Récupère le nom de la classe.<br/>
	 * <br/>
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
	 * Getter de la SortedMap&lt;String, String&gt; triée avec : <br/>
	 * - String : la clé dans le lexique.<br/>
	 * - String : le libellé dans le lexique.<br/>
	 * <br/>
	 *
	 * @return lexiqueMap : SortedMap&lt;String, String&gt;.<br/>
	 */
	SortedMap<String, String> getLexiqueMap();
	
	
		
	/**
	 * Setter de la SortedMap&lt;String, String&gt; triée avec : <br/>
	 * - String : la clé dans le lexique.<br/>
	 * - String : le libellé dans le lexique.<br/>
	 * <br/>
	 *
	 * @param pLexiqueMap : SortedMap&lt;String, String&gt; : 
	 * valeur à passer à lexiqueMap.<br/>
	 */
	void setLexiqueMap(
			SortedMap<String, String> pLexiqueMap);
	

	
	/**
	 * Getter du File en csv avec séparateur ';' 
	 * qui encapsule le lexique.<br/>
	 * <br/>
	 *
	 * @return lexique : File.<br/>
	 */
	File getLexique();
	
	
	
	/**
	 * Setter du File en csv avec séparateur ';' 
	 * qui encapsule le lexique.<br/>
	 * <br/>
	 *
	 * @param pLexique : File : valeur à passer à lexique.<br/>
	 */
	void setLexique(
			File pLexique);
	

	
	/**
	 * Getter de l'Ensemble des valeurs de clés possibles 
	 * pour le lexique.<br/>
	 * <br/>
	 *
	 * @return clesPossiblesSet : Set&lt;String&gt;.<br/>
	 */
	Set<String> getClesPossiblesSet();
	
	
	
	/**
	 * Setter de l'Ensemble des valeurs de clés possibles 
	 * pour le lexique.<br/>
	 * <br/>
	 *
	 * @param pClesPossiblesSet : Set&lt;String&gt; : 
	 * valeur à passer à clesPossiblesSet.<br/>
	 */
	void setClesPossiblesSet(
			Set<String> pClesPossiblesSet);
	
	
	
	/**
	 * retourne une String pour l'affichage de 
	 * <code>this.lexiqueMap</code> à la console.<br/>
	 * <br/>
	 * - retourne null si this.lexiqueMap == null.<br/>
	 * <br/>
	 *
	 * @return : String : String pour affichage.<br/>
	 */
	String afficherLexiqueMap();	
	
	
	
	/**
	 * retourne une String pour l'affichage 
	 * à la console d'une Map&lt;String, String&gt;.<br/>
	 * <br/>
	 * - retourne null si pMap == null.<br/>
	 * <br/>
	 * 
	 *
	 * @param pMap : Map&lt;String, String&gt;
	 * 
	 * @return : String : String pour affichage.<br/>
	 */
	String afficherMapStringString(Map<String, String> pMap);
	
	
	
} // FIN DE L'INTERFACE IImporteurLexique.-----------------------------------
