package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierPasNormalException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.MapNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.MapVideException;

/**
 * INTERFACE IFournisseurCsvImpoDesc :<br/>
 * <p>
 * RESPONSABILITE : AFFICHER ET GENERER UN FICHIER DE DESCRIPTION AU FORMAT CSV.
 * </p>
 * 
 * <p>
 * Interface factorisant les méthodes garantissant 
 * qu'un objet qui l'implémente saura s'écrire sous forme de fichier csv 
 * avec séparateur ';'.
 * </p>
 * 
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
	 * <code>ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;typeJava;
	 * aNomenclature;aLexique;colonneDebut;colonneFin;longueurCalculee;</code> 
	 * pour un DescripteurChampHistoF07.<br/>
	 * <code>ordreChamps;intitule;nomenclature;champJava;typeJava;
	 * aNomenclature;aLexique;</code> 
	 * pour un DescripteurChampDarwinCsv.<br/>
	 * <ul>
	 * <li>utilise le IDescriptionChamp 
	 * <code><b>this.this.descriptionChamp</b></code> pour connaitre 
	 * l'en-tête de la description de fichier.</li>
	 * </ul>
	 * - retourne null si this.descriptionChamp est null.<br/>
	 * <br/>
	 *
	 * @return String : ligne d'en-têtes csv avec séparateur ';'.<br/>
	 */
	String fournirLigneEnTetesCsv();

	
	
	/**
	 * <b>Fournit une ligne csv avec un séparateur ';'
	 * contenant les valeurs de description d'un champ 
	 * à la pL-ième ligne (1-based) de la description du fichier</b>.<br/>
	 * - retourne la ligne d'en-têtes csv si pL == 0.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * <code>1;1-3;3;Numéro de Département;calé à gauche;numDepartment;
	 * Integer;false;false;1;3;3;</code> pour le champ 'Numéro de Département' 
	 * (1ère ligne) de la description d'un HISTO_F07.<br/>
	 * <code>2;route;Route au format Isidor (ex : A0034b1 ou A0006);
	 * route;String;false;false;</code> 
	 * pour le champ 'route' 
	 * (2ème ligne) dans la description du fichier DARWIN_CSV.<br/>
	 * <br/>
	 * - retourne la ligne d'en-têtes csv si pL == 0.<br/>
	 * - retourne null si 
	 * <code><b>this.specificationChampsMap</b></code> est null.<br/>
	 * - retourne null si
	 * <code><b>this.descriptionChamp</b></code> est null.<br/>
	 * - retourne null si la l-ième ligne (1-based) n'existe pas 
	 * dans la description.<br/>
	 * <br/>
	 * 
	 * @param pL : int : numéro d'ordre (1-based) de la ligne dans le fichier de description.<br/>
	 *
	 * @return String : ligne csv contenant la description du champ 
	 * avec séparateur ';'.<br/>
	 */
	String fournirLigneValeursCsv(int pL);
	
	
		
	/**
	 * <b>Génère la description du fichier au format csv 
	 * avec séparateur ';' sous forme de String</b>.<br/>
	 * Rajoute automatiquement la ligne d'en-têtes.<br/>
	 * Pour affichage à la console.<br/>
	 * 
	 * @return : String : La description au format 
	 * csv avec séparateur ';' sous forme de String.<br/>
	 */
	String genererDescriptionCsvString();
	
	
	
	/**
	 * <b>Génère la description du fichier au format csv 
	 * avec séparateur ';' sous forme de String</b>.<br/>
	 * Rajoute la ligne d'en-têtes si pAvecLigneEntetes vaut true.<br/>
	 * Pour affichage à la console.<br/>
	 *
	 * @param pAvecLigneEntetes : boolean : boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv produit.<br/>
	 * 
	 * @return : String : La description au format 
	 * csv avec séparateur ';' sous forme de String.<br/>
	 */
	String genererDescriptionCsvString(boolean pAvecLigneEntetes);
	


	/**
	 * <b>Génère un fichier csv avec séparateur ';' 
	 * this.descriptionDuFichierFile avec l'extension _genere_UTF-8.csv
	 * encodé en UTF-8 avec la ligne d'en-tête</b>  
	 * encapsulant la description de fichier 
	 * <code><b>this.descriptionDuFichierFile</b></code>.<br/>
	 * <ul>
	 * <li>retourne le fichier généré.</li>
	 * <li>crée un fichier destination pFile à génerer VIDE sur disque 
	 * et son arborescence si il n'existe pas.</li>
	 * <li>injecte la description de fichier générée 
	 * dans le fichier destination 
	 * pFile même si il existait déjà 
	 * (pour prendre en compte une éventuelle modification 
	 * de la description de fichier).</li>
	 * <li>Ajoute le BOM-UTF8 au début du fichier généré.</li>
	 * <li>rajoute la ligne d'en-tête.</li>
	 * <li>retourne null si <code><b>this.specificationChampsMap</b></code> 
	 * est null.</li>
	 * <li><b>Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.descriptionDuFichierFile avec 
	 * l'extension _genere_UTF-8.csv</b>.</li>
	 * </ul>
	 * ATTENTION : faire importerDescription(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - LOG.fatal et jette une MapNullException si 
	 * <code><b>this.specificationChampsMap</b></code> est null.<br/>
	 * - LOG.fatal et jette une MapVideException 
	 * si <code><b>this.specificationChampsMap</b></code> est vide.<br/>
	 * - LOG.fatal et jette une FichierNullException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est null.<br/>
	 * - LOG.fatal et jette une FichierVideException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est vide.<br/>
	 * - LOG.fatal et jette une FichierInexistantException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est inexistant.<br/>
	 * - LOG.fatal et jette une FichierPasNormalException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est un répertoire.<br/>  
	 * <br/>
	 * 	
	 * @param pFile : File : le fichier csv à générer contenant 
	 * la description de fichier.<br/>
	 * 
	 * @return : File : 
	 * <i>descriptionDuFichierFile_genere_UTF-8.csv dans le même répertoire 
	 * que la description de fichier</i>.<br/>
	 * 
	 * @throws MapNullException lorsque : la Map 
	 * <code><b>this.specificationChampsMap</b></code> est null.<br/>
	 * @throws MapVideException lorsque : la Map 
	 * <code><b>this.specificationChampsMap</b></code> est vide.<br/>
	 * @throws FichierNullException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est null.<br/>
	 * @throws FichierVideException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est vide.<br/>
	 * @throws FichierInexistantException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est inexistant.<br/>
	 * @throws FichierPasNormalException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est un répertoire.<br/>
	 * @throws IOException  lorsque : 
	 * problème d'écriture du fichier de sortie.<br/>
	 * @throws Exception 
	 */
	File genererDescriptionCsvFileUtf8() 
			throws MapNullException
			, MapVideException
				, FichierNullException
					, FichierVideException
						, FichierInexistantException
							, FichierPasNormalException
								, IOException
									, Exception;
	

	
	/**
	 * <b>Génère un fichier csv avec séparateur ';' 
	 * this.descriptionDuFichierFile avec l'extension _genere_ISO-8859-15.csv
	 * encodé en ISO-8859-15 avec la ligne d'en-tête</b>  
	 * encapsulant la description de fichier 
	 * <code><b>this.descriptionDuFichierFile</b></code>.<br/>
	 * <ul>
	 * <li>retourne le fichier généré.</li>
	 * <li>crée un fichier destination pFile à génerer VIDE sur disque 
	 * et son arborescence si il n'existe pas.</li>
	 * <li>injecte la description de fichier générée 
	 * dans le fichier destination 
	 * pFile même si il existait déjà 
	 * (pour prendre en compte une éventuelle modification 
	 * de la description de fichier).</li>
	 * <li>rajoute la ligne d'en-tête.</li>
	 * <li>retourne null si <code><b>this.specificationChampsMap</b></code> 
	 * est null.</li>
	 * <li><b>Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.descriptionDuFichierFile avec 
	 * l'extension _genere_ISO-8859-15.csv</b>.</li>
	 * </ul>
	 * ATTENTION : faire importerDescription(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - LOG.fatal et jette une MapNullException si 
	 * <code><b>this.specificationChampsMap</b></code> est null.<br/>
	 * - LOG.fatal et jette une MapVideException 
	 * si <code><b>this.specificationChampsMap</b></code> est vide.<br/>
	 * - LOG.fatal et jette une FichierNullException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est null.<br/>
	 * - LOG.fatal et jette une FichierVideException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est vide.<br/>
	 * - LOG.fatal et jette une FichierInexistantException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est inexistant.<br/>
	 * - LOG.fatal et jette une FichierPasNormalException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est un répertoire.<br/>  
	 * <br/>
	 * 	
	 * @param pFile : File : le fichier csv à générer contenant 
	 * la description de fichier.<br/>
	 * 
	 * @return : File : 
	 * <i>descriptionDuFichierFile_genere_ISO-8859-15.csv dans le même répertoire 
	 * que la description de fichier</i>.<br/>
	 * 
	 * @throws MapNullException lorsque : la Map 
	 * <code><b>this.specificationChampsMap</b></code> est null.<br/>
	 * @throws MapVideException lorsque : la Map 
	 * <code><b>this.specificationChampsMap</b></code> est vide.<br/>
	 * @throws FichierNullException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est null.<br/>
	 * @throws FichierVideException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est vide.<br/>
	 * @throws FichierInexistantException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est inexistant.<br/>
	 * @throws FichierPasNormalException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est un répertoire.<br/>
	 * @throws IOException  lorsque : 
	 * problème d'écriture du fichier de sortie.<br/>
	 * @throws Exception 
	 */
	File genererDescriptionCsvFileLatin9() 
			throws MapNullException
			, MapVideException
				, FichierNullException
					, FichierVideException
						, FichierInexistantException
							, FichierPasNormalException
								, IOException
									, Exception;

	
		
	/**
	 * <b>Génère un fichier csv avec séparateur ';' pFile
	 * encodé en UTF-8 avec la ligne d'en-tête</b>  
	 * encapsulant la description de fichier 
	 * <code><b>this.descriptionDuFichierFile</b></code>.<br/>
	 * <ul>
	 * <li>retourne le fichier généré.</li>
	 * <li>crée un fichier destination pFile à génerer VIDE sur disque 
	 * et son arborescence si il n'existe pas.</li>
	 * <li>injecte la description de fichier générée 
	 * dans le fichier destination 
	 * pFile même si il existait déjà 
	 * (pour prendre en compte une éventuelle modification 
	 * de la description de fichier).</li>
	 * <li>Ajoute le BOM-UTF8 au début du fichier généré.</li>
	 * <li>rajoute la ligne d'en-tête.</li>
	 * <li>retourne null si <code><b>this.specificationChampsMap</b></code> 
	 * est null.</li>
	 * <li><b>Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.descriptionDuFichierFile avec l'extension _genere_UTF-8.csv 
	 * si pFile est null</b>.</li>
	 * </ul>
	 * ATTENTION : faire importerDescription(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - LOG.fatal et jette une MapNullException si 
	 * <code><b>this.specificationChampsMap</b></code> est null.<br/>
	 * - LOG.fatal et jette une MapVideException 
	 * si <code><b>this.specificationChampsMap</b></code> est vide.<br/>
	 * - LOG.fatal et jette une FichierNullException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est null.<br/>
	 * - LOG.fatal et jette une FichierVideException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est vide.<br/>
	 * - LOG.fatal et jette une FichierInexistantException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est inexistant.<br/>
	 * - LOG.fatal et jette une FichierPasNormalException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est un répertoire.<br/>  
	 * <br/>
	 * 	
	 * @param pFile : File : le fichier csv à générer contenant 
	 * la description de fichier.<br/>
	 * 
	 * @return : File : le fichier csv généré contenant 
	 * la description de fichier 
	 * <i>(descriptionDuFichierFile_genere_UTF-8.csv dans le même répertoire 
	 * que la description de fichier si pFile = null)</i>.<br/>
	 * 
	 * @throws MapNullException lorsque : la Map 
	 * <code><b>this.specificationChampsMap</b></code> est null.<br/>
	 * @throws MapVideException lorsque : la Map 
	 * <code><b>this.specificationChampsMap</b></code> est vide.<br/>
	 * @throws FichierNullException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est null.<br/>
	 * @throws FichierVideException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est vide.<br/>
	 * @throws FichierInexistantException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est inexistant.<br/>
	 * @throws FichierPasNormalException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est un répertoire.<br/>
	 * @throws IOException  lorsque : 
	 * problème d'écriture du fichier de sortie.<br/>
	 * @throws Exception 
	 */
	File genererDescriptionCsvFileUtf8(File pFile) 
			throws MapNullException
			, MapVideException
				, FichierNullException
					, FichierVideException
						, FichierInexistantException
							, FichierPasNormalException
								, IOException
									, Exception;

	
	
	/**
	 * <b>Génère un fichier csv avec séparateur ';' pFile
	 * encodé en ISO-8859-15 avec la ligne d'en-tête</b>  
	 * encapsulant la description de fichier 
	 * <code><b>this.descriptionDuFichierFile</b></code>.<br/>
	 * <ul>
	 * <li>retourne le fichier généré.</li>
	 * <li>crée un fichier destination pFile à génerer VIDE sur disque 
	 * et son arborescence si il n'existe pas.</li>
	 * <li>injecte la description de fichier générée 
	 * dans le fichier destination 
	 * pFile même si il existait déjà 
	 * (pour prendre en compte une éventuelle modification 
	 * de la description de fichier).</li>
	 * <li>rajoute la ligne d'en-tête.</li>
	 * <li>retourne null si <code><b>this.specificationChampsMap</b></code> 
	 * est null.</li>
	 * <li><b>Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.descriptionDuFichierFile avec l'extension _genere_ISO-8859-15.csv 
	 * si pFile est null</b>.</li>
	 * </ul>
	 * ATTENTION : faire importerDescription(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - LOG.fatal et jette une MapNullException si 
	 * <code><b>this.specificationChampsMap</b></code> est null.<br/>
	 * - LOG.fatal et jette une MapVideException 
	 * si <code><b>this.specificationChampsMap</b></code> est vide.<br/>
	 * - LOG.fatal et jette une FichierNullException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est null.<br/>
	 * - LOG.fatal et jette une FichierVideException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est vide.<br/>
	 * - LOG.fatal et jette une FichierInexistantException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est inexistant.<br/>
	 * - LOG.fatal et jette une FichierPasNormalException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est un répertoire.<br/>  
	 * <br/>
	 * 	
	 * @param pFile : File : le fichier csv à générer contenant 
	 * la description de fichier.<br/>
	 * 
	 * @return : File : le fichier csv généré contenant 
	 * la description de fichier 
	 * <i>(descriptionDuFichierFile_genere_ISO-8859-15.csv dans le même répertoire 
	 * que la description de fichier si pFile = null)</i>.<br/>
	 * 
	 * @throws MapNullException lorsque : la Map 
	 * <code><b>this.specificationChampsMap</b></code> est null.<br/>
	 * @throws MapVideException lorsque : la Map 
	 * <code><b>this.specificationChampsMap</b></code> est vide.<br/>
	 * @throws FichierNullException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est null.<br/>
	 * @throws FichierVideException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est vide.<br/>
	 * @throws FichierInexistantException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est inexistant.<br/>
	 * @throws FichierPasNormalException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est un répertoire.<br/>
	 * @throws IOException  lorsque : 
	 * problème d'écriture du fichier de sortie.<br/>
	 * @throws Exception 
	 */
	File genererDescriptionCsvFileLatin9(File pFile) 
			throws MapNullException
			, MapVideException
				, FichierNullException
					, FichierVideException
						, FichierInexistantException
							, FichierPasNormalException
								, IOException
									, Exception;
	
	
	
	/**
	 * <b>Génère un fichier csv avec séparateur ';' pFile
	 * encodé en UTF-8</b> (avec la ligne d'en-tête 
	 * si pAvecLigneEntetes==true)  
	 * encapsulant la description de fichier 
	 * <code><b>this.descriptionDuFichierFile</b></code>.<br/>
	 * <ul>
	 * <li>retourne le fichier généré.</li>
	 * <li>crée un fichier destination pFile à génerer VIDE sur disque 
	 * et son arborescence si il n'existe pas.</li>
	 * <li>injecte la description de fichier générée 
	 * dans le fichier destination 
	 * pFile même si il existait déjà 
	 * (pour prendre en compte une éventuelle modification 
	 * de la description de fichier).</li>
	 * <li>Ajoute le BOM-UTF8 au début du fichier généré.</li>
	 * <li>rajoute la ligne d'en-tête si pAvecLigneEntetes vaut true.</li>
	 * <li>retourne null si <code><b>this.specificationChampsMap</b></code> 
	 * est null.</li>
	 * <li><b>Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.descriptionDuFichierFile avec l'extension _genere_UTF-8.csv 
	 * si pFile est null</b>.</li>
	 * </ul>
	 * ATTENTION : faire importerDescription(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - LOG.fatal et jette une MapNullException si 
	 * <code><b>this.specificationChampsMap</b></code> est null.<br/>
	 * - LOG.fatal et jette une MapVideException 
	 * si <code><b>this.specificationChampsMap</b></code> est vide.<br/>
	 * - LOG.fatal et jette une FichierNullException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est null.<br/>
	 * - LOG.fatal et jette une FichierVideException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est vide.<br/>
	 * - LOG.fatal et jette une FichierInexistantException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est inexistant.<br/>
	 * - LOG.fatal et jette une FichierPasNormalException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est un répertoire.<br/>  
	 * <br/>
	 * 	
	 * @param pAvecLigneEntetes : boolean : boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv produit.<br/>
	 * @param pFile : File : le fichier csv à générer contenant 
	 * la description de fichier.<br/>
	 * 
	 * @return : File : le fichier csv généré contenant 
	 * la description de fichier 
	 * <i>(descriptionDuFichierFile_genere_UTF-8.csv dans le même répertoire 
	 * que la description de fichier si pFile = null)</i>.<br/>
	 * 
	 * @throws MapNullException lorsque : la Map 
	 * <code><b>this.specificationChampsMap</b></code> est null.<br/>
	 * @throws MapVideException lorsque : la Map 
	 * <code><b>this.specificationChampsMap</b></code> est vide.<br/>
	 * @throws FichierNullException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est null.<br/>
	 * @throws FichierVideException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est vide.<br/>
	 * @throws FichierInexistantException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est inexistant.<br/>
	 * @throws FichierPasNormalException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est un répertoire.<br/>
	 * @throws IOException  lorsque : 
	 * problème d'écriture du fichier de sortie.<br/>
	 * @throws Exception 
	 */
	File genererDescriptionCsvFileUtf8(
			boolean pAvecLigneEntetes
				, File pFile) 
						throws MapNullException
						, MapVideException
							, FichierNullException
								, FichierVideException
									, FichierInexistantException
										, FichierPasNormalException
											, IOException
												, Exception;
	
	
	
	/**
	 * <b>Génère un fichier csv avec séparateur ';' pFile
	 * encodé en ISO-8859-15</b> (avec la ligne d'en-tête 
	 * si pAvecLigneEntetes==true)  
	 * encapsulant la description de fichier 
	 * <code><b>this.descriptionDuFichierFile</b></code>.<br/>
	 * <ul>
	 * <li>retourne le fichier généré.</li>
	 * <li>crée un fichier destination pFile à génerer VIDE sur disque 
	 * et son arborescence si il n'existe pas.</li>
	 * <li>injecte la description de fichier générée 
	 * dans le fichier destination 
	 * pFile même si il existait déjà 
	 * (pour prendre en compte une éventuelle modification 
	 * de la description de fichier).</li>
	 * <li>rajoute la ligne d'en-tête si pAvecLigneEntetes vaut true.</li>
	 * <li>retourne null si <code><b>this.specificationChampsMap</b></code> 
	 * est null.</li>
	 * <li><b>Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.descriptionDuFichierFile avec l'extension _genere_ISO-8859-15.csv 
	 * si pFile est null</b>.</li>
	 * </ul>
	 * ATTENTION : faire importerDescription(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - LOG.fatal et jette une MapNullException si 
	 * <code><b>this.specificationChampsMap</b></code> est null.<br/>
	 * - LOG.fatal et jette une MapVideException 
	 * si <code><b>this.specificationChampsMap</b></code> est vide.<br/>
	 * - LOG.fatal et jette une FichierNullException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est null.<br/>
	 * - LOG.fatal et jette une FichierVideException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est vide.<br/>
	 * - LOG.fatal et jette une FichierInexistantException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est inexistant.<br/>
	 * - LOG.fatal et jette une FichierPasNormalException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est un répertoire.<br/>  
	 * <br/>
	 * 	
	 * @param pAvecLigneEntetes : boolean : boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv produit.<br/>
	 * @param pFile : File : le fichier csv à générer contenant 
	 * la description de fichier.<br/>
	 * 
	 * @return : File : le fichier csv généré contenant 
	 * la description de fichier 
	 * <i>(descriptionDuFichierFile_genere_ISO-8859-15.csv dans le même répertoire 
	 * que la description de fichier si pFile = null)</i>.<br/>
	 * 
	 * @throws MapNullException lorsque : la Map 
	 * <code><b>this.specificationChampsMap</b></code> est null.<br/>
	 * @throws MapVideException lorsque : la Map 
	 * <code><b>this.specificationChampsMap</b></code> est vide.<br/>
	 * @throws FichierNullException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est null.<br/>
	 * @throws FichierVideException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est vide.<br/>
	 * @throws FichierInexistantException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est inexistant.<br/>
	 * @throws FichierPasNormalException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est un répertoire.<br/>
	 * @throws IOException  lorsque : 
	 * problème d'écriture du fichier de sortie.<br/>
	 * @throws Exception 
	 */
	File genererDescriptionCsvFileLatin9(
			boolean pAvecLigneEntetes
				, File pFile) 
						throws MapNullException
						, MapVideException
							, FichierNullException
								, FichierVideException
									, FichierInexistantException
										, FichierPasNormalException
											, IOException
												, Exception;
	

	
	/**
	 * <b>Génère un fichier csv avec séparateur ';' pFile
	 * encodé en pCharset</b> (avec la ligne d'en-tête 
	 * si pAvecLigneEntetes==true)  
	 * encapsulant la description de fichier 
	 * <code><b>this.descriptionDuFichierFile</b></code>.<br/>
	 * <ul>
	 * <li>retourne le fichier généré.</li>
	 * <li>crée un fichier destination pFile à génerer VIDE sur disque 
	 * et son arborescence si il n'existe pas.</li>
	 * <li>injecte la description de fichier générée 
	 * dans le fichier destination 
	 * pFile même si il existait déjà 
	 * (pour prendre en compte une éventuelle modification 
	 * de la description de fichier) - ECRASE ET REMPLACE.</li>
	 * <li>choisit automatiquement le Charset UTF-8 si pCharset == null 
	 * ou si pCharset ne peut encoder.</li>
	 * <li>Ajoute le BOM-UTF8 au début du fichier généré si charset 
	 * vaut Charset-UTF8.</li>
	 * <li>rajoute la ligne d'en-tête si pAvecLigneEntetes vaut true.</li>
	 * <li>retourne null si <code><b>this.specificationChampsMap</b></code> 
	 * est null.</li>
	 * <li><b>Génère automatiquement le fichier généré dans le même répertoire 
	 * que this.descriptionDuFichierFile avec l'extension _genere_charset.csv 
	 * si pFile est null</b>.</li>
	 * </ul>
	 * ATTENTION : faire importerDescription(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - LOG.fatal et jette une MapNullException si 
	 * <code><b>this.specificationChampsMap</b></code> est null.<br/>
	 * - LOG.fatal et jette une MapVideException 
	 * si <code><b>this.specificationChampsMap</b></code> est vide.<br/>
	 * - LOG.fatal et jette une FichierNullException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est null.<br/>
	 * - LOG.fatal et jette une FichierVideException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est vide.<br/>
	 * - LOG.fatal et jette une FichierInexistantException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est inexistant.<br/>
	 * - LOG.fatal et jette une FichierPasNormalException 
	 * si <code><b>this.descriptionDuFichierFile</b></code> est un répertoire.<br/>  
	 * <br/>
	 * 	
	 * @param pAvecLigneEntetes : boolean : boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv produit.<br/>
	 * @param pFile : File : le fichier csv à générer contenant 
	 * la description de fichier.<br/>
	 * @param pCharset : Charset : encodage dans 
	 * lequel on veut le fichier de sortie.<br/>
	 * 
	 * @return : File : le fichier csv généré contenant 
	 * la description de fichier 
	 * <i>(descriptionDuFichierFile_genere_charset.csv dans le même répertoire 
	 * que la description de fichier si pFile = null)</i>.<br/>
	 * 
	 * @throws MapNullException lorsque : la Map 
	 * <code><b>this.specificationChampsMap</b></code> est null.<br/>
	 * @throws MapVideException lorsque : la Map 
	 * <code><b>this.specificationChampsMap</b></code> est vide.<br/>
	 * @throws FichierNullException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est null.<br/>
	 * @throws FichierVideException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est vide.<br/>
	 * @throws FichierInexistantException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est inexistant.<br/>
	 * @throws FichierPasNormalException lorsque : 
	 * <code><b>this.descriptionDuFichierFile</b></code> est un répertoire.<br/>
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
								, FichierVideException
									, FichierInexistantException
										, FichierPasNormalException
											, IOException
												, Exception;
	
	
	
} // FIN DE L'INTERFACE IFournisseurCsvImpoDesc.-----------------------------
