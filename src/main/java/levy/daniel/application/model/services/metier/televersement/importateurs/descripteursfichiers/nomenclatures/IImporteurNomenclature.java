package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.SortedMap;

import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierPasNormalException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideException;

/**
 * class IImporteurNomenclature :<br/>
 * RESPONSABILITE : IMPORTER LES NOMENCLATURES.<br/>
 * Interface factorisant les méthodes des ImporteurNomenclature.<br/>
 * <br/>
 * Une nomenclature doit être un fichier csv avec séparateur ';' 
 * sous forme [Integer, String] signifiant [Clé, Libellé] comme par exemple 
 * pour le sens dans un HIT :<br/>
 * 1; sens des PR croissants pour route à 2 sens;<br/>
 * 2; sens des PR décroissants pour route à 2 sens;<br/>
 * 3; sens confondus pour une route à 2 sens;<br/>
 * ...........................................<br/>
 * <br/>
 * Tous les ImporteurNomenclature possèdent une 
 * méthode importerNomenclature(File pNomenclature) 
 * où pNomenclature encapsule la nomenclature en csv de la donnée 
 * (sens, nature, catégorie administrative, ...) à servir.<br/>
 * La nomenclature est servie sous forme de 
 * SortedMap&lt;Integer, String&gt; nomenclatureMap 
 * retournée par importerNomenclature(File pNomenclature).<br/>
 * La méthode importerNomenclature(File pNomenclature) permet également 
 * d'alimenter un Set&lt;Integer&gt; clesPossiblesSet 
 * qui contient toutes les valeurs possibles pour la clé dans la nomenclature.<br/>
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
public interface IImporteurNomenclature 
	extends IFournisseurJTableNomenclature
				, IFournisseurCsvImpoNomenclature {
	
	
	/**
	 * METHODE_IMPORTER_NOMENCLATURE : String :<br/>
	 * "Méthode importerNomenclature(File pNomenclature) - ".<br/>
	 */
	String METHODE_IMPORTER_NOMENCLATURE 
		= "Méthode importerNomenclature(File pNomenclature) - ";
	

	
	/**
	 * METHODE_IMPORTER_NOMENCLATURE_EN_UTF8 : String :<br/>
	 * "Méthode importerNomenclatureEnUtf8(File pNomenclature) - ".<br/>
	 */
	String METHODE_IMPORTER_NOMENCLATURE_EN_UTF8 
		= "Méthode importerNomenclatureEnUtf8(File pNomenclature) - ";
	
	
	
	/**
	 * method importerNomenclature(
	 * File pNomenclature) :<br/>
	 * Importe une Nomenclature encapsulée dans le fichier csv 
	 * encodé en LATIN9 pNomenclature 
	 * et la retourne sous forme de 
	 * SortedMap&lt;Integer, String&gt; nomenclatureMap avec : <br/>
	 * - Integer : la clé dans la nomenclature.<br/>
	 * - String : le libellé dans la nomenclature.<br/>
	 * <br/>
	 * Alimente également le set des clés possibles clesPossiblesSet.<br/>
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
	 * @throws Exception 
	 */
	SortedMap<Integer, String> importerNomenclature(
			File pNomenclature) 
					throws FichierNullException
						, FichierVideException
							, FichierInexistantException
								, FichierPasNormalException
									, IOException
										, Exception;
	
	
	
	/**
	 * method importerNomenclatureEnUtf8() :<br/>
	 * Importe une Nomenclature encapsulée dans le fichier csv 
	 * encodé en UTF-8 pNomenclature 
	 * et la retourne sous forme de 
	 * SortedMap&lt;Integer, String&gt; nomenclatureMap avec : <br/>
	 * - Integer : la clé dans la nomenclature.<br/>
	 * - String : le libellé dans la nomenclature.<br/>
	 * <br/>
	 * Alimente également le set des clés possibles clesPossiblesSet.<br/>
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
	 * method recupererNomClasse() :<br/>
	 * Récupère le nom de la classe.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererNomClasse();
	
	
	
	/**
	 * method recupererCleImporterFileNull() :<br/>
	 * Fournit la clé du message en cas d'import d'un fichier null
	 * stocké dans messages_techniques.properties.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleImporterFileNull();
	
	
		
	/**
	 * method recupererCleImporterFileVide() :<br/>
	 * Fournit la clé du message en cas d'import d'un fichier vide
	 * stocké dans messages_techniques.properties.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleImporterFileVide();

	
		
	/**
	 * method recupererCleImporterFileInexistant() :<br/>
	 * Fournit la clé du message en cas d'import d'un fichier inexistant
	 * stocké dans messages_techniques.properties.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleImporterFileInexistant();
	


	/**
	 * method recupererCleImporterFilePasNormal() :<br/>
	 * Fournit la clé du message en cas d'import d'un répertoire
	 * stocké dans messages_techniques.properties.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleImporterFilePasNormal();

	

	/**
	 * method getNomenclatureMap() :<br/>
	 * Getter de la SortedMap&lt;Integer, String&gt; triée avec : <br/>
	 * - Integer : la clé dans la nomenclature.<br/>
	 * - String : le libellé dans la nomenclature.<br/>
	 * <br/>
	 *
	 * @return nomenclatureMap : SortedMap&lt;Integer, String&gt;.<br/>
	 */
	SortedMap<Integer, String> getNomenclatureMap();
	
	
		
	/**
	 * method setNomenclatureMap(
	 * SortedMap&lt;Integer, String&gt; pNomenclatureMap) :<br/>
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
	 * method getNomenclature() :<br/>
	 * Getter du File en csv avec séparateur ';' 
	 * qui encapsule la nomenclature.<br/>
	 * <br/>
	 *
	 * @return nomenclature : File.<br/>
	 */
	File getNomenclature();
	
	
	
	/**
	 * method setNomenclature(
	 * File pNomenclature) :<br/>
	 * Setter du File en csv avec séparateur ';' 
	 * qui encapsule la nomenclature.<br/>
	 * <br/>
	 *
	 * @param pNomenclature : File : valeur à passer à nomenclature.<br/>
	 */
	void setNomenclature(
			File pNomenclature);
	

	
	/**
	 * method getClesPossiblesSet() :<br/>
	 * Getter de l'Ensemble des valeurs de clés possibles 
	 * pour la nomenclature.<br/>
	 * <br/>
	 *
	 * @return clesPossiblesSet : Set&lt;Integer&gt;.<br/>
	 */
	Set<Integer> getClesPossiblesSet();
	
	
	
	/**
	 * method setClesPossiblesSet(
	 * Set&lt;Integer&gt; pClesPossiblesSet) :<br/>
	 * Setter de l'Ensemble des valeurs de clés possibles 
	 * pour la nomenclature.<br/>
	 * <br/>
	 *
	 * @param pClesPossiblesSet : Set&lt;Integer&gt; : 
	 * valeur à passer à clesPossiblesSet.<br/>
	 */
	void setClesPossiblesSet(
			Set<Integer> pClesPossiblesSet);
	
	
	
} // FIN DE L'INTERFACE IImporteurNomenclature.------------------------------
