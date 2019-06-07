package levy.daniel.application.model.services.metier.televersement.importateurs.importeurs;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.SortedMap;

import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierPasNormalException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.MapNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.MapVideException;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.IImportateurDescription;

/**
 * INTERFACE IImporteur :<br/>
 * RESPONSABILITE : IMPORTER LES FICHIERS (HIT, HISTO_F07, DARWIN_CSV, ...) 
 * ET LES METTRE A DISPOSITION DE L'APPLICATION SOUS FORME DE MAP 
 * (SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt;).<br/>
 * Interface modélisant tous les importeurs de fichiers 
 * (HIT, HISTO_F07, FEOR XML, DARWIN_CSV...).<br/>
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
 * @since 7 juil. 2014
 *
 */
public interface IImporteur 
	extends IFournisseurJTableImporteur
						, IFournisseurCsvImporteur
							, IFournisseurFichier
								, IRapporteurImporteur {
	

	
	/**
	 * "Méthode Importer(File pFile)".<br/>
	 */
	String METHODE_IMPORTER = "Méthode Importer(File pFile)";
	
	

	/**
	 * "Méthode compterNombreLignes(File pFile)".<br/>
	 */
	String METHODE_COMPTERNOMBRELIGNES 
		= "Méthode compterNombreLignes(File pFile)";
	

	
	/**
	 * <ul>
	 * <li>Importe un fichier de données 
	 * (HIT, HISTO_F07, HISTO_F08, DARWIN_CSV, FEOR XML, ...) 
	 * <b>encodé en UTF-8</b> 
	 * en utilisant sa <b>description de fichier</b> et le retourne 
	 * sous forme de 
	 * SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; 
	 * <code><b>this.fichierImporteMap</b></code> avec :
	 * <ul>
	 * <li>Integer : le numéro d'ordre de la ligne dans le fichier.</li>
	 * <li>SortedMap&lt;Integer, String&gt; avec :</li>
	 * <ul>
	 * <li>Integer : le numéro d'un champ dans une ligne.</li>
	 * <li>String : la valeur du champ sous forme de String encodée 
	 * avec l'encodage par défaut de la plateforme (UTF-8).</li>
	 * </ul>
	 * </ul>
	 * </li>
	 * <li>utilise <code>Files.readAllLines(
	 * this.fichierAImporter.toPath(), charset);</code> 
	 * pour lire les lignes du fichier.<br/>
	 * jette donc une Exception de décodage <code>malformedInputException</code> 
	 * si le charset ne correspond pas à celui du fichier de données.</li>
	 * <li>Alimente <code><b>this.fichierImporteMap</b></code>.</li>
	 * <li>Retire un éventuel caractère BOM-UTF-8 
	 * en début de chaque ligne.</li>
	 * <li>saute une éventuelle ligne vide dans le fichier à importer.</li>
	 * <li>Génère un rapport dans <code><b>this.rapportImportStb</b></code> 
	 * si souci d'import 
	 * (fichier null, inexistant, répertoire, vide ou I/O Exception) 
	 * et si <code><b>this.logImport</b></code> vaut true.</li>
	 * </ul>
	 * <br/>
	 *
	 * @param pFile : File : le fichier (HIT, DARWIN_CSV, ...) à importer.<br/>
	 * 
	 * @return : SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; : 
	 * Map triée encapsulant le fichier.<br/>
	 * 
	 * @throws FichierNullException : si pFile est null.<br/>
	 * @throws FichierVideException  : si pFile est vide.<br/>
	 * @throws FichierInexistantException  : si pFile n'existe pas.<br/>
	 * @throws FichierPasNormalException : si pFile est un répertoire.<br/>
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws MapNullException lorsque : la Map 'specificationChampsMap' 
	 * est null dans l'ImportateurDescription.<br/>
	 * @throws MapVideException lorsque : la Map 'specificationChampsMap' 
	 * est vide dans l'ImportateurDescription.<br/>
	 * @throws Exception
	 */
	SortedMap<Integer, SortedMap<Integer, String>> importer(
			File pFile) 
					throws FichierNullException
						, FichierVideException
							, FichierInexistantException
								, FichierPasNormalException
									, IOException
										, MapNullException
											, MapVideException
												, Exception;
	
	

	
	/**
	 * <ul>
	 * <li>Importe un fichier de données 
	 * (HIT, HISTO_F07, HISTO_F08, DARWIN_CSV, FEOR XML, ...) 
	 * <b>encodé en ISO-8859-15 (Latin 9)</b> 
	 * en utilisant sa <b>description de fichier</b> et le retourne 
	 * sous forme de 
	 * SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; 
	 * <code><b>this.fichierImporteMap</b></code> avec :
	 * <ul>
	 * <li>Integer : le numéro d'ordre de la ligne dans le fichier.</li>
	 * <li>SortedMap&lt;Integer, String&gt; avec :</li>
	 * <ul>
	 * <li>Integer : le numéro d'un champ dans une ligne.</li>
	 * <li>String : la valeur du champ sous forme de String encodée 
	 * avec l'encodage par défaut de la plateforme (UTF-8).</li>
	 * </ul>
	 * </ul>
	 * </li>
	 * <li>utilise <code>Files.readAllLines(
	 * this.fichierAImporter.toPath(), charset);</code> 
	 * pour lire les lignes du fichier.<br/>
	 * jette donc une Exception de décodage <code>malformedInputException</code> 
	 * si le charset ne correspond pas à celui du fichier de données.</li>
	 * <li>Alimente <code><b>this.fichierImporteMap</b></code>.</li>
	 * <li>Retire un éventuel caractère BOM-UTF-8 
	 * en début de chaque ligne.</li>
	 * <li>saute une éventuelle ligne vide dans le fichier à importer.</li>
	 * <li>Génère un rapport dans <code><b>this.rapportImportStb</b></code> 
	 * si souci d'import 
	 * (fichier null, inexistant, répertoire, vide ou I/O Exception) 
	 * et si <code><b>this.logImport</b></code> vaut true.</li>
	 * </ul>
	 * <br/>
	 *
	 * @param pFile : File : le fichier (HIT, DARWIN_CSV, ...) 
	 * à importer.<br/>
	 * 
	 * @return : SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; : 
	 * Map triée encapsulant le fichier.<br/>
	 * 
	 * @throws FichierNullException : si pFile est null.<br/>
	 * @throws FichierVideException  : si pFile est vide.<br/>
	 * @throws FichierInexistantException  : si pFile n'existe pas.<br/>
	 * @throws FichierPasNormalException : si pFile est un répertoire.<br/>
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws MapNullException lorsque : la Map 'specificationChampsMap' 
	 * est null dans l'ImportateurDescription.<br/>
	 * @throws MapVideException lorsque : la Map 'specificationChampsMap' 
	 * est vide dans l'ImportateurDescription.<br/>
	 * @throws Exception
	 */
	SortedMap<Integer, SortedMap<Integer, String>> 
			importerFichierEnLatin9(
				File pFile) 
							throws FichierNullException
							, FichierVideException
							, FichierInexistantException
							, FichierPasNormalException
							, IOException
							, MapNullException
							, MapVideException
							, Exception;
	

	
	/**
	 * <ul>
	 * <li>Importe un fichier de données 
	 * (HIT, HISTO_F07, HISTO_F08, DARWIN_CSV, FEOR XML, ...) 
	 * <b>encodé en ANSI (Windows-1252)</b> 
	 * en utilisant sa <b>description de fichier</b> et le retourne 
	 * sous forme de 
	 * SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; 
	 * <code><b>this.fichierImporteMap</b></code> avec :
	 * <ul>
	 * <li>Integer : le numéro d'ordre de la ligne dans le fichier.</li>
	 * <li>SortedMap&lt;Integer, String&gt; avec :</li>
	 * <ul>
	 * <li>Integer : le numéro d'un champ dans une ligne.</li>
	 * <li>String : la valeur du champ sous forme de String encodée 
	 * avec l'encodage par défaut de la plateforme (UTF-8).</li>
	 * </ul>
	 * </ul>
	 * </li>
	 * <li>utilise <code>Files.readAllLines(
	 * this.fichierAImporter.toPath(), charset);</code> 
	 * pour lire les lignes du fichier.<br/>
	 * jette donc une Exception de décodage <code>malformedInputException</code> 
	 * si le charset ne correspond pas à celui du fichier de données.</li>
	 * <li>Alimente <code><b>this.fichierImporteMap</b></code>.</li>
	 * <li>Retire un éventuel caractère BOM-UTF-8 
	 * en début de chaque ligne.</li>
	 * <li>saute une éventuelle ligne vide dans le fichier à importer.</li>
	 * <li>Génère un rapport dans <code><b>this.rapportImportStb</b></code> 
	 * si souci d'import 
	 * (fichier null, inexistant, répertoire, vide ou I/O Exception) 
	 * et si <code><b>this.logImport</b></code> vaut true.</li>
	 * </ul>
	 * <br/>
	 *
	 * @param pFile : File : le fichier (HIT, DARWIN_CSV, ...) 
	 * à importer.<br/>
	 * 
	 * @return : SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; : 
	 * Map triée encapsulant le fichier.<br/>
	 * 
	 * @throws FichierNullException : si pFile est null.<br/>
	 * @throws FichierVideException  : si pFile est vide.<br/>
	 * @throws FichierInexistantException  : si pFile n'existe pas.<br/>
	 * @throws FichierPasNormalException : si pFile est un répertoire.<br/>
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws MapNullException lorsque : la Map 'specificationChampsMap' 
	 * est null dans l'ImportateurDescription.<br/>
	 * @throws MapVideException lorsque : la Map 'specificationChampsMap' 
	 * est vide dans l'ImportateurDescription.<br/>
	 * @throws Exception
	 */
	SortedMap<Integer, SortedMap<Integer, String>> 
			importerFichierEnAnsi(
				File pFile) 
							throws FichierNullException
							, FichierVideException
							, FichierInexistantException
							, FichierPasNormalException
							, IOException
							, MapNullException
							, MapVideException
							, Exception;
	
	
	
	/**
	 * <ul>
	 * <li>Importe un fichier de données 
	 * (HIT, HISTO_F07, HISTO_F08, DARWIN_CSV, FEOR XML, ...) 
	 * <b>encodé en UTF-8</b> 
	 * en utilisant sa <b>description de fichier</b> et le retourne 
	 * sous forme de 
	 * SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; 
	 * <code><b>this.fichierImporteMap</b></code> avec :
	 * <ul>
	 * <li>Integer : le numéro d'ordre de la ligne dans le fichier.</li>
	 * <li>SortedMap&lt;Integer, String&gt; avec :</li>
	 * <ul>
	 * <li>Integer : le numéro d'un champ dans une ligne.</li>
	 * <li>String : la valeur du champ sous forme de String encodée 
	 * avec l'encodage par défaut de la plateforme (UTF-8).</li>
	 * </ul>
	 * </ul>
	 * </li>
	 * <li>utilise <code>Files.readAllLines(
	 * this.fichierAImporter.toPath(), charset);</code> 
	 * pour lire les lignes du fichier.<br/>
	 * jette donc une Exception de décodage <code>malformedInputException</code> 
	 * si le charset ne correspond pas à celui du fichier de données.</li>
	 * <li>Alimente <code><b>this.fichierImporteMap</b></code>.</li>
	 * <li>Retire un éventuel caractère BOM-UTF-8 
	 * en début de chaque ligne.</li>
	 * <li>saute une éventuelle ligne vide dans le fichier à importer.</li>
	 * <li>Génère un rapport dans <code><b>this.rapportImportStb</b></code> 
	 * si souci d'import 
	 * (fichier null, inexistant, répertoire, vide ou I/O Exception) 
	 * et si <code><b>this.logImport</b></code> vaut true.</li>
	 * <li>Génère un rapport de contrôle de validité
	 * dans <code><b>this.rapportControleStb</b></code> et 
	 * </ul>
	 * <br/>
	 *
	 * @param pFile : File : le fichier (HIT, DARWIN_CSV, ...) à importer.<br/>
	 * 
	 * @return : SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; : 
	 * Map triée encapsulant le fichier.<br/>
	 * 
	 * @throws FichierNullException : si pFile est null.<br/>
	 * @throws FichierVideException  : si pFile est vide.<br/>
	 * @throws FichierInexistantException  : si pFile n'existe pas.<br/>
	 * @throws FichierPasNormalException : si pFile est un répertoire.<br/>
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws MapNullException lorsque : la Map 'specificationChampsMap' 
	 * est null dans l'ImportateurDescription.<br/>
	 * @throws MapVideException lorsque : la Map 'specificationChampsMap' 
	 * est vide dans l'ImportateurDescription.<br/>
	 * @throws Exception
	 */
	SortedMap<Integer, SortedMap<Integer, String>> 
			importerFichierEnUTf8(
				File pFile) 
							throws FichierNullException
							, FichierVideException
							, FichierInexistantException
							, FichierPasNormalException
							, IOException
							, MapNullException
							, MapVideException
							, Exception;
	

	
	/**
	 * <ul>
	 * <li>Importe un fichier de données 
	 * (HIT, HISTO_F07, HISTO_F08, DARWIN_CSV, FEOR XML, ...) 
	 * <b>encodé en pCharset</b> 
	 * en utilisant sa <b>description de fichier</b> et le retourne 
	 * sous forme de 
	 * SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; 
	 * <code><b>this.fichierImporteMap</b></code> avec :
	 * <ul>
	 * <li>Integer : le numéro d'ordre de la ligne dans le fichier.</li>
	 * <li>SortedMap&lt;Integer, String&gt; avec :</li>
	 * <ul>
	 * <li>Integer : le numéro d'un champ dans une ligne.</li>
	 * <li>String : la valeur du champ sous forme de String encodée 
	 * avec l'encodage par défaut de la plateforme (UTF-8).</li>
	 * </ul>
	 * </ul>
	 * </li>
	 * <li>utilise <code>Files.readAllLines(
	 * this.fichierAImporter.toPath(), charset);</code> 
	 * pour lire les lignes du fichier.<br/>
	 * jette donc une Exception de décodage <code>malformedInputException</code> 
	 * si le charset ne correspond pas à celui du fichier de données.</li>
	 * <li>Alimente <code><b>this.fichierImporteMap</b></code>.</li>
	 * <li>Choisit automatiquement le Charset UTF-8 
	 * si pCharset == null.</li>
	 * <li>Retire un éventuel caractère BOM-UTF-8 
	 * en début de chaque ligne.</li>
	 * <li>saute une éventuelle ligne vide dans le fichier à importer.</li>
	 * <li>Génère un rapport dans <code><b>this.rapportImportStb</b></code> 
	 * si souci d'import 
	 * (fichier null, inexistant, répertoire, vide ou I/O Exception) 
	 * et si <code><b>this.logImport</b></code> vaut true.</li>
	 * </ul>
	 * <br/>
	 *
	 * @param pFile : File : le fichier (HIT, DARWIN_CSV, ...) 
	 * à importer.<br/>
	 * @param pCharset : Charset : Jeu de caractères dans lequel 
	 * le fichier à importer a été encodé.<br/>
	 * 
	 * @return : SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; : 
	 * Map triée encapsulant le fichier.<br/>
	 * 
	 * @throws FichierNullException : si pFile est null.<br/>
	 * @throws FichierVideException  : si pFile est vide.<br/>
	 * @throws FichierInexistantException  : si pFile n'existe pas.<br/>
	 * @throws FichierPasNormalException : si pFile est un répertoire.<br/>
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws MapNullException lorsque : la Map 'specificationChampsMap' 
	 * est null dans l'ImportateurDescription.<br/>
	 * @throws MapVideException lorsque : la Map 'specificationChampsMap' 
	 * est vide dans l'ImportateurDescription.<br/>
	 * @throws Exception
	 */
	SortedMap<Integer, SortedMap<Integer, String>> 
								importer(
									File pFile
										, Charset pCharset) 
												throws FichierNullException
												, FichierVideException
												, FichierInexistantException
												, FichierPasNormalException
												, IOException
												, MapNullException
												, MapVideException
												, Exception;
	
	

	/**
	 * Affiche la map importée.<br/>
	 * <br/>
	 * - retourne null 
	 * si <code><b>this.fichierImporteMap</b></code> est null.<br/>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 *
	 * @return : String : Affichage.<br/>
	 */
	String afficherFichierImporteMap();
	
	
	
	/**
	 * Compte le nombre de lignes dans un fichier.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * @return : int : Nombres de lignes.<br/>
	 * 
	 * @throws FichierNullException : si pFile est null.<br/>
	 * @throws FichierVideException  : si pFile est vide.<br/>
	 * @throws FichierInexistantException  : si pFile n'existe pas.<br/>
	 * @throws FichierPasNormalException : si pFile est un répertoire.<br/>
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws Exception 
	 */
	int compterNombreLignes(
					File pFile) 
						throws FichierNullException
							, FichierVideException
								, FichierInexistantException
									, FichierPasNormalException
										, IOException, Exception;
	

	
	/**
	 * Getter de l'Encapsulation permettant de stocker toutes les valeurs 
	 * décrivant un champ dans une description de fichier.<br/>
	 * Par exemple :<br/>
	 * <code>[ordreChamps, colonnes, longueur, intitule, nomenclature
	 * , champJava, typeJava, aNomenclature, aLexique
	 * , colonneDebut, colonneFin, longueurCalculee]</code> 
	 * pour une description de HISTO_F07.<br/>
	 * <code>[ordreChamps, intitule, nomenclature, champJava, 
	 * typeJava, aNomenclature, aLexique]</code> 
	 * pour une description de DARWIN_CSV.<br/>
	 * <br/>
	 *
	 * @return descriptionChamp : IDescriptionChamp.<br/>
	 */
	IDescriptionChamp getDescriptionChamp();



	/**
	 * Setter de l'Encapsulation permettant de stocker toutes les valeurs 
	 * décrivant un champ dans une description de fichier.<br/>
	 * Par exemple :<br/>
	 * <code>[ordreChamps, colonnes, longueur, intitule, nomenclature
	 * , champJava, typeJava, aNomenclature, aLexique
	 * , colonneDebut, colonneFin, longueurCalculee]</code> 
	 * pour une description de HISTO_F07.<br/>
	 * <code>[ordreChamps, intitule, nomenclature, champJava, 
	 * typeJava, aNomenclature, aLexique]</code> 
	 * pour une description de DARWIN_CSV.<br/>
	 * <br/>
	 *
	 * @param pDescriptionChamp : IDescriptionChamp : 
	 * valeur à passer à descriptionChamp.<br/>
	 */
	void setDescriptionChamp(
			IDescriptionChamp pDescriptionChamp);


	
	/**
	 * Getter de l'Importateur de description.<br/>
	 * Les ImportateurDescription sont chargés 
	 * de mettre à disposition de l'application les description 
	 * des fichiers (HIT, HISTO_F07, DARWIN_CSV, FEOR_XML, ...).<br/>
	 * Ces descriptions sont fournies par les ImportateursDescription 
	 * sous forme de SortedMap&lt;Integer, IDescriptionChamp&gt;.<br/>
	 * <br/>
	 *
	 * @return importateurDescription : IImportateurDescription.<br/>
	 */
	IImportateurDescription getImportateurDescription();



	/**
	 * Setter de l'Importateur de description.<br/>
	 * Les ImportateurDescription sont chargés 
	 * de mettre à disposition de l'application les description 
	 * des fichiers (HIT, HISTO_F07, DARWIN_CSV, FEOR_XML, ...).<br/>
	 * Ces descriptions sont fournies par les ImportateursDescription 
	 * sous forme de SortedMap&lt;Integer, IDescriptionChamp&gt;.<br/>
	 * <br/>
	 *
	 * @param pImportateurDescription : IImportateurDescription : 
	 * valeur à passer à importateurDescription.<br/>
	 */
	void setImportateurDescription(
			IImportateurDescription pImportateurDescription);


		
	/**
	 * Getter du fichier (HIT, DARWIN_CSV, ...) à importer.<br/>
	 *
	 * @return fichierAImporter : File.<br/>
	 */
	File getFichierAImporter();



	/**
	 * Setter du fichier (HIT, DARWIN_CSV, ...) à importer.<br/>
	 *
	 * @param pFichierAImporter : File : 
	 * valeur à passer à fichierAImporter.<br/>
	 */
	void setFichierAImporter(
			File pFichierAImporter);



	/**
	 * Getter de la SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; 
	 * <code><b>this.fichierImporteMap</b></code> avec :
	 * <ul>
	 * <li>Integer : le numéro d'ordre de la ligne dans le fichier.</li>
	 * <li>SortedMap&lt;Integer, String&gt; avec :</li>
	 * <ul>
	 * <li>Integer : le numéro d'un champ dans une ligne.</li>
	 * <li>String : la valeur du champ sous forme de String encodée 
	 * avec l'encodage par défaut de la plateforme (UTF-8).</li>
	 * </ul>
	 * </ul>
	 *
	 * @return this.fichierImporteMap : 
	 * SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; : 
	 * Map encapsulant le fichier de données importé.<br/>
	 */
	SortedMap<Integer, SortedMap<Integer, String>> 
											getFichierImporteMap();

	
	
	/**
	 * Setter de la SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; 
	 * <code><b>this.fichierImporteMap</b></code> avec :
	 * <ul>
	 * <li>Integer : le numéro d'ordre de la ligne dans le fichier.</li>
	 * <li>SortedMap&lt;Integer, String&gt; avec :</li>
	 * <ul>
	 * <li>Integer : le numéro d'un champ dans une ligne.</li>
	 * <li>String : la valeur du champ sous forme de String encodée 
	 * avec l'encodage par défaut de la plateforme (UTF-8).</li>
	 * </ul>
	 * </ul>
	 *
	 * @param pFichierImporteMap : SortedMap<Integer,SortedMap<Integer,String>> : 
	 * valeur à passer à this.fichierImporteMap.<br/>
	 */
	void setFichierImporteMap(
			SortedMap<Integer, SortedMap<Integer, String>> 
													pFichierImporteMap);


	
} // FIN DE L'INTERFACE IImporteur.------------------------------------------
