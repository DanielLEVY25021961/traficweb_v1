package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.MapNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.MapVideException;

/**
 * class IFournisseurCsvImpoDesc :<br/>
 * RESPONSABILITE : AFFICHER ET GENERER UN FICHIER DE DESCRIPTION AU FORMAT CSV.<br/>
 * Interface factorisant les méthodes garantissant 
 * qu'un objet qui l'implémente saura s'écrire sous forme de fichier csv 
 * avec séparateur ';'.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * generer fichier, générer fichier, écrire fichier, ecrire fichier
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
public interface IFournisseurCsvImpoDesc {

	
	
	/**
	 * Fournit une ligne csv avec un séparateur ';' pour les en-têtes
	 * de la description de fichier.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;typeJava;
	 * aNomenclature;colonneDebut;colonneFin;longueurCalculee; 
	 * pour un DescripteurChampHistoF07.<br/>
	 * ordreChamps;intitule;nomenclature;champJava;typeJava;aNomenclature; 
	 * pour un DescripteurChampDarwinCsv.<br/>
	 * <br/>
	 * - retourne null si this.descriptionChamp est null.<br/>
	 * <br/>
	 *
	 * @return String : ligne d'en-têtes csv avec séparateur ';'.<br/>
	 */
	String fournirLigneEnTetesCsv();

	
	
	/**
	 * Fournit une ligne csv avec un séparateur ';'
	 * contenant les valeurs de description d'un champ 
	 * stockées dans valeursDescriptionMap.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * 1;1-3;3;Numéro de Département;calé à gauche;numDepartment;
	 * Integer;false;1;3;3; pour le champ 'Numéro de Département' 
	 * (1ère ligne) de la description d'un HistoNatF07.<br/>
	 * 2;route;Route au format Isidor (ex : A0034b1 ou A0006);
	 * route;String;false; 
	 * pour le champ 'route' 
	 * (2ème ligne) dans la description du fichier Darwin csv.<br/>
	 * <br/>
	 * - retourne "" si valeursDescriptionMap est null.<br/>
	 * - retourne "" si valeursDescriptionMap est vide.<br/>
	 * <br/>
	 * 
	 * @param pL : int : 
	 *
	 * @return String : ligne csv contenant la description du champ 
	 * avec séparateur ';'.<br/>
	 */
	String fournirLigneValeursCsv(int pL);
	
	
		
	/**
	 * Génère la description du fichier au format csv 
	 * avec séparateur ';' sous forme de String.<br/>
	 * Rajoute automatiquement la ligne d'en-têtes.<br/>
	 * <br/>
	 * 
	 * @return : String : La description au format 
	 * csv avec séparateur ';' sous forme de String.<br/>
	 */
	String genererDescriptionCsvString();
	
	
	
	/**
	 * Génère la description du fichier au format csv 
	 * avec séparateur ';' sous forme de String.<br/>
	 * Rajoute la ligne d'en-têtes si pAvecLigneEntetes vaut true.<br/>
	 * <br/>
	 *
	 * @param pAvecLigneEntetes : boolean : boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv produit.<br/>
	 * 
	 * @return : String : La description au format 
	 * csv avec séparateur ';' sous forme de String.<br/>
	 */
	String genererDescriptionCsvString(boolean pAvecLigneEntetes);

	
	
	/**
	 * Génère un fichier csv avec séparateur ';' de la description 
	 * et le stocke dans le même répertoire que this.descriptionDuFichierFile 
	 * avec l'extension _genere.csv.<br/>
	 * Rajoute la ligne d'en-têtes.<br/>
	 * <br/>
	 * - LOG.fatal et jette une MapNullException si 
	 * this.specificationChampsMap est null.<br/>
	 * - LOG.fatal et jette une MapVideException 
	 * si this.specificationChampsMap est vide.<br/>
	 * - LOG.fatal et jette une FichierNullException 
	 * si this.descriptionDuFichierFile est vide.<br/>
	 * - LOG.fatal et jette une FichierInexistantException 
	 * si this.descriptionDuFichierFile est inexistant.<br/> 
	 * <br/>
	 * 	
	 * @return : File : le fichier csv contenant 
	 * la description de fichier.<br/>
	 * 
	 * @throws MapNullException lorsque : la Map 
	 * 'specificationChampsMap' est null.<br/>
	 * @throws MapVideException lorsque : la Map 
	 * 'specificationChampsMap' est vide.<br/>
	 * @throws FichierNullException lorsque : 
	 * this.descriptionDuFichierFile est null.<br/>
	 * @throws FichierInexistantException lorsque : 
	 * this.descriptionDuFichierFile est inexistant.<br/>
	 * @throws IOException  lorsque : 
	 * problème d'écriture du fichier de sortie.<br/>
	 * @throws Exception 
	 */
	File genererDescriptionCsvFile() 
					throws MapNullException
						, MapVideException
							, FichierNullException
								, FichierInexistantException
									, IOException, Exception;
	
	
		
	/**
	 * Génère un fichier csv avec séparateur ';' de la description 
	 * et le stocke dans pFile.<br/>
	 * Rajoute la ligne d'en-têtes.<br/>
	 * Génère automatiquement le fichier de sortie 
	 * dans le même répertoire que this.descriptionDuFichierFile 
	 * avec l'extension _genere.csv si pFile est null.<br/>
	 * <br/>
	 * - LOG.fatal et jette une MapNullException si 
	 * this.specificationChampsMap est null.<br/>
	 * - LOG.fatal et jette une MapVideException 
	 * si this.specificationChampsMap est vide.<br/>
	 * - LOG.fatal et jette une FichierNullException 
	 * si this.descriptionDuFichierFile est vide.<br/>
	 * - LOG.fatal et jette une FichierInexistantException 
	 * si this.descriptionDuFichierFile est inexistant.<br/> 
	 * <br/>
	 * 	
	 * @param pFile : File : le fichier csv contenant 
	 * la description de fichier.<br/>
	 * 
	 * @return : File : le fichier csv contenant 
	 * la description de fichier.<br/>
	 * 
	 * @throws MapNullException lorsque : la Map 
	 * 'specificationChampsMap' est null.<br/>
	 * @throws MapVideException lorsque : la Map 
	 * 'specificationChampsMap' est vide.<br/>
	 * @throws FichierNullException lorsque : 
	 * this.descriptionDuFichierFile est null.<br/>
	 * @throws FichierInexistantException lorsque : 
	 * this.descriptionDuFichierFile est inexistant.<br/>
	 * @throws IOException  lorsque : 
	 * problème d'écriture du fichier de sortie.<br/>
	 * @throws Exception 
	 */
	File genererDescriptionCsvFile(File pFile) 
					throws MapNullException
						, MapVideException
							, FichierNullException
								, FichierInexistantException
									, IOException, Exception;
	
	
	
	/**
	 * Génère un fichier csv avec séparateur ';' encodé en UTF-8 
	 * de la description 
	 * et le stocke dans pFile.<br/>
	 * Rajoute la ligne d'en-têtes si pAvecLigneEntetes vaut true.<br/>
	 * Génère automatiquement le fichier de sortie 
	 * dans le même répertoire que this.descriptionDuFichierFile 
	 * avec l'extension _UTF-8.csv si pFile est null.<br/>
	 * <br/>
	 * - LOG.fatal et jette une MapNullException si 
	 * this.specificationChampsMap est null.<br/>
	 * - LOG.fatal et jette une MapVideException 
	 * si this.specificationChampsMap est vide.<br/>
	 * - LOG.fatal et jette une FichierNullException 
	 * si this.descriptionDuFichierFile est vide.<br/>
	 * - LOG.fatal et jette une FichierInexistantException 
	 * si this.descriptionDuFichierFile est inexistant.<br/> 
	 * <br/>
	 * 	
	 * @param pAvecLigneEntetes : boolean : boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv produit.<br/>
	 * @param pFile : File : le fichier csv contenant 
	 * la description de fichier.<br/>
	 * 
	 * @return : File : le fichier csv contenant 
	 * la description de fichier.<br/>
	 * 
	 * @throws MapNullException lorsque : la Map 
	 * 'specificationChampsMap' est null.<br/>
	 * @throws MapVideException lorsque : la Map 
	 * 'specificationChampsMap' est vide.<br/>
	 * @throws FichierNullException lorsque : 
	 * this.descriptionDuFichierFile est null.<br/>
	 * @throws FichierInexistantException lorsque : 
	 * this.descriptionDuFichierFile est inexistant.<br/>
	 * @throws IOException  lorsque : 
	 * problème d'écriture du fichier de sortie.<br/>
	 * @throws Exception 
	 */
	File genererDescriptionCsvFile(
			boolean pAvecLigneEntetes
				, File pFile) 
					throws MapNullException
						, MapVideException
							, FichierNullException
								, FichierInexistantException
									, IOException, Exception;
	


	
	/**
	 * Génère un fichier csv avec séparateur ';' encodé en LATIN9 
	 * de la description 
	 * et le stocke dans pFile.<br/>
	 * Rajoute la ligne d'en-têtes.<br/>
	 * Génère automatiquement le fichier de sortie 
	 * dans le même répertoire que this.descriptionDuFichierFile 
	 * avec l'extension _ISO-8859-15.csv.<br/>
	 * <br/>
	 * - LOG.fatal et jette une MapNullException si 
	 * this.specificationChampsMap est null.<br/>
	 * - LOG.fatal et jette une MapVideException 
	 * si this.specificationChampsMap est vide.<br/>
	 * - LOG.fatal et jette une FichierNullException 
	 * si this.descriptionDuFichierFile est vide.<br/>
	 * - LOG.fatal et jette une FichierInexistantException 
	 * si this.descriptionDuFichierFile est inexistant.<br/> 
	 * <br/>
	 * 	
	 * 
	 * @return : File : le fichier csv contenant 
	 * la description de fichier.<br/>
	 * 
	 * @throws MapNullException lorsque : la Map 
	 * 'specificationChampsMap' est null.<br/>
	 * @throws MapVideException lorsque : la Map 
	 * 'specificationChampsMap' est vide.<br/>
	 * @throws FichierNullException lorsque : 
	 * this.descriptionDuFichierFile est null.<br/>
	 * @throws FichierInexistantException lorsque : 
	 * this.descriptionDuFichierFile est inexistant.<br/>
	 * @throws IOException  lorsque : 
	 * problème d'écriture du fichier de sortie.<br/>
	 * @throws Exception 
	 */
	File genererDescriptionCsvFileLatin9() 
			throws MapNullException
			, MapVideException
			, FichierNullException
			, FichierInexistantException
			, IOException, Exception;
	


	/**
	 * Génère un fichier csv avec séparateur ';' encodé en UTF-8 
	 * de la description 
	 * et le stocke dans pFile.<br/>
	 * Rajoute la ligne d'en-têtes.<br/>
	 * Génère automatiquement le fichier de sortie 
	 * dans le même répertoire que this.descriptionDuFichierFile 
	 * avec l'extension _UTF-8.csv.<br/>
	 * <br/>
	 * - LOG.fatal et jette une MapNullException si 
	 * this.specificationChampsMap est null.<br/>
	 * - LOG.fatal et jette une MapVideException 
	 * si this.specificationChampsMap est vide.<br/>
	 * - LOG.fatal et jette une FichierNullException 
	 * si this.descriptionDuFichierFile est vide.<br/>
	 * - LOG.fatal et jette une FichierInexistantException 
	 * si this.descriptionDuFichierFile est inexistant.<br/> 
	 * <br/>
	 * 	
	 * 
	 * @return : File : le fichier csv contenant 
	 * la description de fichier.<br/>
	 * 
	 * @throws MapNullException lorsque : la Map 
	 * 'specificationChampsMap' est null.<br/>
	 * @throws MapVideException lorsque : la Map 
	 * 'specificationChampsMap' est vide.<br/>
	 * @throws FichierNullException lorsque : 
	 * this.descriptionDuFichierFile est null.<br/>
	 * @throws FichierInexistantException lorsque : 
	 * this.descriptionDuFichierFile est inexistant.<br/>
	 * @throws IOException  lorsque : 
	 * problème d'écriture du fichier de sortie.<br/>
	 * @throws Exception 
	 */
	File genererDescriptionCsvFileUtf8() 
			throws MapNullException
			, MapVideException
			, FichierNullException
			, FichierInexistantException
			, IOException, Exception;
	

	
	/**
	 * Génère un fichier csv avec séparateur ';' encodé en pCharset 
	 * de la description 
	 * et le stocke dans pFile.<br/>
	 * Rajoute la ligne d'en-têtes si pAvecLigneEntetes vaut true.<br/>
	 * Génère automatiquement le fichier de sortie 
	 * dans le même répertoire que this.descriptionDuFichierFile 
	 * avec l'extension _charset.csv si pFile est null.<br/>
	 * <br/>
	 * - LOG.fatal et jette une MapNullException si 
	 * this.specificationChampsMap est null.<br/>
	 * - LOG.fatal et jette une MapVideException 
	 * si this.specificationChampsMap est vide.<br/>
	 * - LOG.fatal et jette une FichierNullException 
	 * si this.descriptionDuFichierFile est vide.<br/>
	 * - LOG.fatal et jette une FichierInexistantException 
	 * si this.descriptionDuFichierFile est inexistant.<br/> 
	 * <br/>
	 * 	
	 * @param pAvecLigneEntetes : boolean : boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv produit.<br/>
	 * @param pFile : File : le fichier csv contenant 
	 * la description de fichier.<br/>
	 * @param pCharset : Charset : encodage dans 
	 * lequel on veut le fichier de sortie.<br/>
	 * 
	 * @return : File : le fichier csv contenant 
	 * la description de fichier.<br/>
	 * 
	 * @throws MapNullException lorsque : la Map 
	 * 'specificationChampsMap' est null.<br/>
	 * @throws MapVideException lorsque : la Map 
	 * 'specificationChampsMap' est vide.<br/>
	 * @throws FichierNullException lorsque : 
	 * this.descriptionDuFichierFile est null.<br/>
	 * @throws FichierInexistantException lorsque : 
	 * this.descriptionDuFichierFile est inexistant.<br/>
	 * @throws IOException  lorsque : 
	 * problème d'écriture du fichier de sortie.<br/>
	 * @throws Exception 
	 */
	File genererDescriptionCsvFile(
			boolean pAvecLigneEntetes
				, File pFile
					, Charset pCharset) 
					throws MapNullException
						, MapVideException
							, FichierNullException
								, FichierInexistantException
									, IOException, Exception;
	
	
	
} // FIN DE L'INTERFACE IFournisseurCsvImpoDesc.-----------------------------
