package levy.daniel.application.metier.importateurs.importeurs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.IConstantes;
import levy.daniel.application.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.exceptions.technical.impl.FichierPasNormalException;
import levy.daniel.application.exceptions.technical.impl.FichierVideException;
import levy.daniel.application.exceptions.technical.impl.MapNullException;
import levy.daniel.application.exceptions.technical.impl.MapVideException;
import levy.daniel.application.metier.importateurs.controleursimport.IControleurImport;
import levy.daniel.application.metier.importateurs.controleursimport.messagescontrolesimport.IMessageControleImport;
import levy.daniel.application.metier.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;
import levy.daniel.application.metier.importateurs.descripteursfichiers.importateursdescription.IImportateurDescription;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class AbstractImporteur :<br/>
 * Classe abstraite modélisant tous les importeurs de fichiers 
 * (HIT, HistonatF07, FEOR XML, Darwin.csv...).<br/>
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
 * @since 7 juil. 2014
 *
 */
public abstract class AbstractImporteur implements IImporteur {

	// ************************ATTRIBUTS************************************/

	
	/**
	 * descriptionChamp : IDescriptionChamp :<br/>
	 * Encapsulation permettant de stocker toutes les valeurs 
	 * décrivant un champ dans une description de fichier.<br/>
	 * Par exemple :<br/>
	 * [ordreChamps, colonnes, longueur, intitule, nomenclature
	 * , champJava, typeJava, aNomenclature
	 * , colonneDebut, colonneFin, longueurCalculee] 
	 * pour une description de HistonatF07.<br/>
	 * [ordreChamps, intitule, nomenclature, champJava, typeJava, aNomenclature] 
	 * pour une description de Darwin csv.<br/>
	 */
	protected IDescriptionChamp descriptionChamp;

	
	/**
	 * importateurDescription : IImportateurDescription :<br/>
	 * Importateur de description.<br/>
	 * Les ImportateurDescription sont chargés 
	 * de mettre à disposition de l'application les description 
	 * des fichiers (HIT, HISTONATF07, DarwinCsv, Feor XML, ...).<br/>
	 * Ces descriptions sont fournies par les ImportateursDescription sous forme de 
	 * SortedMap&lt;Integer, IDescriptionChamp&gt;.<br/>
	 */
	protected IImportateurDescription importateurDescription;

	
	/**
	 * logImport : boolean : 
	 * boolean qui stipule si les Importeur
	 * doivent rapporter ou pas 
	 * (rapport d'erreur lors de la lecture de fichiers).<br/>
	 * Ce rapport n'est null que si this.logImport vaut false. 
	 * ATTENTION : Tester si il est vide.<br/>
	 */
	protected boolean logImport;
	

	
	/**
	 * rapportImportStb : StringBuffer : <br/>
	 * StringBuffer chargé de contenir le rapport de
	 * l'import du fichier.<br/>
	 * Ce rapport n'est null que si this.logImport vaut false. 
	 * Tester si il est vide.<br/>
	 */
	protected StringBuffer rapportImportStb;

	
	/**
	 * fichierAImporter : File :<br/>
	 * Le fichier (HIT, Darwin, ...) à importer.<br/>
	 */
	protected File fichierAImporter;
	
	
	/**
	 * fichierImporteMap : SortedMap<Integer,SortedMap<Integer,String>> :<br/>
	 * SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; 
	 * encapsulant le fichier (HIT, Darwin, ...) importé avec :<br/>
	 * - Integer : le numéro d'ordre de la ligne dans le fichier.<br/>
	 * - Integer : le numéro d'un champ dans une ligne.<br/>
	 * - String : la valeur du champ sous forme de String UTF-8.<br/>
	 */
	protected SortedMap<Integer, SortedMap<Integer, String>> fichierImporteMap;
	
	
	
	/**
	 * logControle : boolean :<br/>
	 * boolean qui stipule si les Importeur
	 * doivent rapporter ou pas les contrôles de validité 
	 * (rapport de champ non renseigné, 
	 * d'erreurs de format ou de nomenclature 
	 * dans un champ lors de l'import d'un fichier).<br/>
	 * Ce rapport n'est null que si this.logControle vaut false. 
	 * ATTENTION : Tester si il est vide.<br/>
	 */
	protected boolean logControle;
	
	
	
	/**
	 * rapportControleStb : StringBuffer : <br/>
	 * StringBuffer chargé de contenir le rapport des
	 * contrôles de validité des champs 
	 * lors de l'import du fichier (format csv, en-tête inclus).<br/>
	 * Ce rapport n'est null que si this.logControle vaut false. 
	 * Tester si il est vide.<br/>
	 */
	protected StringBuffer rapportControleStb;


	
	/**
	 * rapportControleMap : <br/>
	 * SortedMap&lt;Integer, 
	 * SortedMap&lt;Integer, Set&lt;IMessageControleImport&gt;&gt;&gt; 
	 * contenant les rapports de contrôle de validité des champs 
	 * pour l'ensemble du fichier importé avec :<br/>
	 * <ul>
	 * <li>Integer : numéro de la ligne (1-based) 
	 * dans le fichier importé.</li><br/>
	 * <li>SortedMap&lt;Integer, Set&lt;IMessageControleImport&gt;&gt; 
	 * avec :<br/>
	 * <ul>
	 * <li>Integer : le numéro d'ordre du champ (1-based) 
	 * dans la ligne.</li><br/>
	 * <li>Set&lt;IMessageControleImport&gt;&gt; : l'ensemble 
	 * des messages de contrôle générés pour le champ.</li>
	 * </ul> 
	 * </li>
	 * </ul>
	 * Ce rapport n'est null que si this.logControle vaut false.<br/> 
	 * ATTENTION : Tester si il est vide.<br/>
	 */
	protected SortedMap<Integer, SortedMap<Integer, Set<IMessageControleImport>>> 
													rapportControleMap;

	
	
	/**
	 * controleurImport : IControleurImport :<br/>
	 * Controleur de validité des champs.<br/>
	 */
	protected IControleurImport controleurImport;
	
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(AbstractImporteur.class);

	
	// *************************METHODES************************************/
	

	
	 /**
	 * method CONSTRUCTEUR AbstractImporteur() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractImporteur() {
		
		super();
		
		/* Détermination de la valeur du boolean qui
		 * stipule si il faut logger l'import du fichier
		 * ou pas. */
		this.determinerSiLogErreurs();
		
		/* Détermination de la valeur du boolean qui 
		 * stipule si il faut contrôler la validité des champs*/
		this.determinerSiLogControle();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, SortedMap<Integer, String>> 
														importer(
															final File pFile) 
					throws FichierNullException
						, FichierVideException
							, FichierInexistantException
								, FichierPasNormalException
									, IOException
										, MapNullException
											, MapVideException {
		
		return this.importer(pFile, IConstantes.CHARSET_UTF8);
		
	} // Fin de importer(
	// File pFile).________________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, SortedMap<Integer, String>> 
			importerFichierEnLatin9(
				final File pFile) 
							throws FichierNullException
							, FichierVideException
							, FichierInexistantException
							, FichierPasNormalException
							, IOException
							, MapNullException
							, MapVideException {
		
		return this.importer(pFile, IConstantes.CHARSET_LATIN9);
		
	} // Fin de importerFichierEnLatin9(
	 // File pFile)._______________________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, SortedMap<Integer, String>> 
			importerFichierEnAnsi(
				final File pFile) 
							throws FichierNullException
							, FichierVideException
							, FichierInexistantException
							, FichierPasNormalException
							, IOException
							, MapNullException
							, MapVideException {
		
		return this.importer(pFile, IConstantes.CHARSET_ANSI);
		
	} // Fin de importerFichierEnAnsi(
	 // File pFile)._______________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, SortedMap<Integer, String>> 
			importerFichierEnUTf8(
				final File pFile) 
							throws FichierNullException
							, FichierVideException
							, FichierInexistantException
							, FichierPasNormalException
							, IOException
							, MapNullException
							, MapVideException {
		
		return this.importer(pFile, IConstantes.CHARSET_UTF8);
		
	} // Fin de importerFichierEnUtf8(
	 // File pFile)._______________________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, SortedMap<Integer, String>> 
								importer(
									final File pFile
										, final Charset pCharset) 
												throws FichierNullException
												, FichierVideException
												, FichierInexistantException
												, FichierPasNormalException
												, IOException
												, MapNullException
												, MapVideException {

		// ********** PARAMETRES INVALIDES *******************************/
		/* Fichier null. */
		this.traiterFichierNull(pFile);

		/* Fichier vide. */
		this.traiterFichierVide(pFile);

		/* Fichier inexistant. */
		this.traiterFichierInexistant(pFile);

		/* File directory. */
		this.traiterFichierPasNormal(pFile);

		// ************ PARAMETRES VALIDES *******************************/

		/* Passage des paramètres aux attributs. */
		this.fichierAImporter = pFile;

		/* Map résultat. */
		this.fichierImporteMap 
		= new TreeMap<Integer, SortedMap<Integer, String>>();
		
		Charset charset = null;
		
		/* Choisit automatiquement le Charset UTF-8 si pCharset == null. */
		if (pCharset == null) {
			charset = IConstantes.CHARSET_UTF8;
		}
		else {
			charset = pCharset;
		}

		/* Ouverture des flux. */
		final FileInputStream fis = new FileInputStream(pFile);
		final InputStreamReader isr = new InputStreamReader(fis, charset);
		final BufferedReader bfr = new BufferedReader(isr);

		String ligneLue = "";
		int numeroLigne = 0;

		// LECTURE DES LIGNES.**********************************
		while (true) {

			/* Incrémentation du numéro de ligne. */
			numeroLigne++;
			
			/* Lecture de la ligne. */
			ligneLue = bfr.readLine();

			if (ligneLue == null) {
				break;
			}
			
			String ligneADecomposer = null;
			
			/* Retire un éventuel caractère BOM-UTF-8 
			 * en début de chaque ligne. */
			if (ligneLue.charAt(0) == IConstantes.BOM_UTF_8) {
				ligneADecomposer = StringUtils.substring(ligneLue, 1);
			}
			else {
				ligneADecomposer = ligneLue;
			}
			

			/* DECOMPOSITION DE CHAQUE LIGNE. *******/
			final SortedMap<Integer, String> ligneMap = this
					.decomposerLigne(ligneADecomposer);

			
			// CONTROLE DE VALIDITE DES CHAMPS POUR CHAQUE LIGNE.____
			if (this.logControle) {

				/* EXECUTION DU CONTROLE. ****/
				this.controlerValiditeChamps(numeroLigne, ligneMap, ligneLue);
				
			} // FIN DE CONTROLE DE VALIDITE DES CHAMPS POUR CHAQUE LIGNE.____

			
			// AJOUT DE LA LIGNE DECOMPOSEE DANS LA MAP RESULTAT._____
			if (ligneMap != null) {
				this.fichierImporteMap.put(numeroLigne, ligneMap);
			}

		} // FIN LECTURE DES LIGNES.******************************

		/* FERMETURE DES FLUX. */
		fis.close();
		isr.close();
		bfr.close();

		return this.fichierImporteMap;

	} // Fin de importer(
	// File pFile
	// , Charset pCharset).________________________________________________
	

	
	/**
	 * method controlerValiditeChamps(
	 * int pNumeroLigne
	 * , SortedMap&lt;Integer, String&gt; pLigneMap
	 * , String pLigneLue) :<br/>
	 * <ul>
	 * <li>Exécute les contrôles de validité des champs de la ligne 
	 * pNumeroLigne (1-based) du fichier importé.<br/>
	 * La ligne du fichier à contrôler est fournie sous forme de 
	 * SortedMap&lt;Integer, String&gt; avec :<br/>
	 * <ul>
	 * <li>Integer : le numéro d'ordre du champ dans la ligne.</li><br/>
	 * <li>String : la valeur prise par le champ.</li><br/>
	 * </ul>
	 * </li>
	 * <li>Délègue à this.controleurImport le contrôle 
	 * de validité des champs de la ligne.<br/>
	 * this.controleurImport génère une 
	 * SortedMap&lt;Integer, Set&lt;IMessageControleImport&gt;&gt; avec :<br/>
	 * <ul>
	 * <li>Integer : le numéro d'ordre du champ (1-based) 
	 * dans la ligne.</li><br/>
	 * <li>Set&lt;IMessageControleImport&gt;&gt; : l'ensemble 
	 * des messages de contrôle générés pour le champ.</li>
	 * </ul> 
	 * </li>
	 * <li>Ajoute la Map des contrôles de validité des champs de la ligne 
	 * au rapport du fichier this.rapportControleMap.<br/>
	 * this.rapportControleMap est une 
	 * SortedMap&lt;Integer, 
	 * SortedMap&lt;Integer, Set&lt;IMessageControleImport&gt;&gt;&gt; 
	 * avec :<br/>
	 * <ul>
	 * <li>Integer : numéro de la ligne (1-based) 
	 * dans le fichier importé.</li><br/>
	 * <li>SortedMap&lt;Integer, Set&lt;IMessageControleImport&gt;&gt; 
	 * avec :<br/>
	 * <ul>
	 * <li>Integer : le numéro d'ordre du champ (1-based) 
	 * dans la ligne.</li><br/>
	 * <li>Set&lt;IMessageControleImport&gt;&gt; : l'ensemble 
	 * des messages de contrôle générés pour le champ.</li>
	 * </ul></li></ul></li>
	 * <li>Ajoute chaque message de controle de validité 
	 * des champs de la ligne au rapport en csv 
	 * this.rapportControleStb.</li><br/>
	 *</ul>
	 * 
	 * - Ne fait rien si pLigneMap == null.<br/>
	 * <br/>
	 *
	 * @param pNumeroLigne : int : le numéro de ligne (1-based) 
	 * dans le fichier importé.<br/>
	 * @param pLigneMap : SortedMap&lt;Integer, String&gt; 
	 * décrivant la ligne pNumeroLigne du fichier avec :<br/>
	 * <ul>
	 * <li>Integer : le numéro d'ordre (1-based) du champ dans la ligne.</li><br/>
	 * <li>String : la valeur prise par le champ.</li><br/>
	 * </ul>
	 * @param pLigneLue : String : la ligne pNumeroLigne (1-based) 
	 * du fichier importé.<br/>
	 * <br/>
	 * 
	 * @throws MapNullException lorsque : la Map 'specificationChampsMap' 
	 * est null dans l'ImportateurDescription.<br/>
	 * @throws MapVideException lorsque : la Map 'specificationChampsMap' 
	 * est vide dans l'ImportateurDescription.<br/>
	 */
	private void controlerValiditeChamps(
			final int pNumeroLigne
				, final SortedMap<Integer, String> pLigneMap
					, final String pLigneLue) 
							throws MapNullException, MapVideException {
		
		/* Ne fait rien si pLigneMap == null. */
		if (pLigneMap == null) {
			return;
		}
		
		/* EXECUTION DU CONTROLE. ****/
		/* Délègue à this.controleurImport 
		 * le contrôle de validité des champs de la ligne. */
		final SortedMap<Integer, Set<IMessageControleImport>> mapLigne 
		= this.controleurImport
				.controlerImport(pNumeroLigne, pLigneMap, pLigneLue);

		/* Ajoute la Map des contrôles de validité des champs 
		 * de la ligne au rapport du fichier this.rapportControleMap. */
		this.rapportControleMap.put(pNumeroLigne, mapLigne);

		/* Ajoute chaque message de controle de validité des champs de la ligne au rapport en csv this.rapportControleStb. */
		this.ajouterAuRapportStb(mapLigne);
		
	} // Fin de controlerValiditeChamps(
	 // int pNumeroLigne
	 // , SortedMap<Integer, String> pLigneMap
	 // , String pLigneLue)._______________________________________________
	
	
	
	/**
	 * method decomposerLigne(
	 * String pString) :<br/>
	 * Décompose chaque ligne lue dans le fichier 
	 * en fonction de la description et retourne une Map triée 
	 * SortedMap&lt;Integer, String&gt; avec :<br/>
	 * <ul>
	 * <li>Integer : l'ordre du champ.</li><br/>
	 * <li>String : la valeur du champ sous forme de String 
	 * encodée dans le Charset par défaut de la plateforme (UTF-8).</li><br/>
	 * </ul>
	 * <br/>
	 * - Retire un éventuel caractère BOM-UTF-8 
	 * en début de chaque ligne.<br/>
	 * <br/>
	 * - retourne null si pString est blank.<br/>
	 * <br/>
	 * Implémenté au niveau du dessous dans AbstractImporteurAscii 
	 * ou AbstractImporteurNonAscii.<br/>
	 * <br/>
	 *
	 * @param pString
	 * @return : SortedMap&lt;Integer,String&gt;.<br/>
	 */
	protected abstract SortedMap<Integer, String> decomposerLigne(
			final String pString);
	
	
	
	/**
	 * method traiterFichierNull(
	 * File pFile) :<br/>
	 * Traitement des fichiers null pour la méthode importer(pFile).<br/>
	 * LOG.fatal, rapporte dans this.rapportImportStb 
	 * et jette une FichierNullException si pFile est null.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws FichierNullException : si pFile est null.<br/> 
	 */
	protected final void traiterFichierNull(
								final File pFile) 
											throws FichierNullException {
		
		this.traiterFichierNull(pFile, METHODE_IMPORTER);
		
	} // Fin de traiterFichierNull(
	 // File pFile)._______________________________________________________
	
	
	
	/**
	 * method traiterFichierVide(
	 * File pFile) :<br/>
	 * Traitement des fichiers vide pour la méthode importer(pFile).<br/>
	 * LOG.fatal, rapporte dans this.rapportImportStb 
	 * et jette une FichierVideException si pFile est vide.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws FichierVideException : si pFile est vide.<br/> 
	 */
	protected final void traiterFichierVide(
								final File pFile) 
									throws FichierVideException {
		
		this.traiterFichierVide(pFile, METHODE_IMPORTER);

	} // Fin de traiterFichierVide(
	 // File pFile)._______________________________________________________
	
	
	
	/**
	 * method traiterFichierInexistant(
	 * File pFile) :<br/>
	 * Traitement des fichiers inexistants 
	 * pour la méthode importer(pFile).<br/>
	 * LOG.fatal, rapporte dans this.rapportImportStb 
	 * et jette une FichierInexistantException 
	 * si pFile est inexistant.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws FichierInexistantException : 
	 * si le fichier est inexistant.<br/> 
	 */
	protected final void traiterFichierInexistant(
								final File pFile) 
									throws FichierInexistantException {
		
		this.traiterFichierInexistant(pFile, METHODE_IMPORTER);

	} // Fin de traiterFichierInexistant(
	 // File pFile)._______________________________________________________
	
	

	/**
	 * method traiterFichierPasNormal(
	 * File pFile) :<br/>
	 * Traitement des répertoires (fichier non 'normaux') 
	 * pour la méthode importer(pFile).<br/>
	 * LOG.fatal, rapporte dans this.rapportImportStb 
	 * et jette une FichierPasNormalException 
	 * si pFile est un répertoire.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws FichierPasNormalException : si pFile est un répertoire.<br/>
	 */
	protected final void traiterFichierPasNormal(
							final File pFile) 
								throws FichierPasNormalException {
		
		this.traiterFichierPasNormal(pFile, METHODE_IMPORTER);

	} // Fin de traiterFichierPasNormal(
	 // File pFile)._______________________________________________________
	

	
	/**
	 * method traiterFichierNull(
	 * File pFile
	 * , String pMethode) :<br/>
	 * LOG.fatal, rapporte dans this.rapportImportStb 
	 * et jette une FichierNullException si pFile est null.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws FichierNullException : si pFile est null.<br/> 
	 */
	protected final void traiterFichierNull(
								final File pFile
									, final String pMethode) 
											throws FichierNullException {
		
		if (pFile == null) {
			
			/* Constitution du message. */
			final String message 
			= this.recupererNomClasse()
			+ IConstantes.SEPARATEUR_MOINS_AERE
			+ pMethode
			+ IConstantes.SEPARATEUR_MOINS_AERE
			+ ConfigurationApplicationManager.getBundleMessagesTechniques()
			.getString(this.recupererCleImporterFileNull());
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logImport) {
				this.rapportImportStb.append(message);
				this.rapportImportStb.append(IConstantes.SAUT_LIGNE);
			}
			
			/* Exception circonstanciée. */
			throw new FichierNullException(message);
			
		} // Fin de pFile null._____________________________
		
	} // Fin de traiterFichierNull(
	 // File pFile
	// , String pMethode)._________________________________________________
	
	
	
	/**
	 * method traiterFichierVide(
	 * File pFile
	 * , String pMethode) :<br/>
	 * LOG.fatal, rapporte dans this.rapportImportStb 
	 * et jette une FichierVideException si pFile est vide.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws FichierVideException : si pFile est vide.<br/> 
	 */
	protected final void traiterFichierVide(
				final File pFile
					, final String pMethode) 
								throws FichierVideException {
		
		if (pFile.length() == 0) {
			
			/* Constitution du message. */
			final String message 
			= this.recupererNomClasse()
			+ IConstantes.SEPARATEUR_MOINS_AERE
			+ pMethode 
			+ IConstantes.SEPARATEUR_MOINS_AERE
			+ ConfigurationApplicationManager.getBundleMessagesTechniques()
			.getString(this.recupererCleImporterFileVide()) 
			+ pFile.getAbsolutePath();
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logImport) {
				this.rapportImportStb.append(message);
				this.rapportImportStb.append(IConstantes.SAUT_LIGNE);
			}
			
			/* Exception circonstanciée. */
			throw new FichierVideException(message);
			
		} // Fin de pFile vide.______________________________

	} // Fin de traiterFichierVide(
	 // File pFile
	// , String pMethode)._________________________________________________
	
	
	
	/**
	 * method traiterFichierInexistant(
	 * File pFile
	 * , String pMethode) :<br/>
	 * LOG.fatal, rapporte dans this.rapportImportStb 
	 * et jette une FichierInexistantException 
	 * si pFile est inexistant.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws FichierInexistantException : si le fichier est inexistant.<br/> 
	 */
	protected final void traiterFichierInexistant(
			final File pFile
				, final String pMethode) 
						throws FichierInexistantException {
		
		if (!pFile.exists()) {
			
			/* Constitution du message. */
			final String message 
			= this.recupererNomClasse()
			+ IConstantes.SEPARATEUR_MOINS_AERE
			+ pMethode 
			+ IConstantes.SEPARATEUR_MOINS_AERE
			+ ConfigurationApplicationManager.getBundleMessagesTechniques()
			.getString(this.recupererCleImporterFileInexistant()) 
			+ pFile.getAbsolutePath();
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logImport) {
				this.rapportImportStb.append(message);
				this.rapportImportStb.append(IConstantes.SAUT_LIGNE);
			}
						
			/* Exception circonstanciée. */
			throw new FichierInexistantException(message);
			
		} // Fin de fichier inexistant._______________________

	} // Fin de traiterFichierInexistant(
	 // File pFile
	// , String pMethode)._________________________________________________
	
	

	/**
	 * method traiterFichierPasNormal(
	 * File pFile
	 * , String pMethode) :<br/>
	 * LOG.fatal, rapporte dans this.rapportImportStb 
	 * et jette une FichierPasNormalException 
	 * si pFile est un répertoire.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws FichierPasNormalException : si pFile est un répertoire.<br/>
	 */
	protected final void traiterFichierPasNormal(
			final File pFile
				, final String pMethode) 
						throws FichierPasNormalException {
		
		if (!pFile.isFile()) {
			
			/* Constitution du message. */
			final String message 
			= this.recupererNomClasse()
			+ IConstantes.SEPARATEUR_MOINS_AERE
			+ pMethode
			+ IConstantes.SEPARATEUR_MOINS_AERE
			+ ConfigurationApplicationManager.getBundleMessagesTechniques()
			.getString(this.recupererCleImporterFilePasNormal()) 
			+ pFile.getAbsolutePath();
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logImport) {
				this.rapportImportStb.append(message);
				this.rapportImportStb.append(IConstantes.SAUT_LIGNE);
			}
			
			/* Exception circonstanciée. */
			throw new FichierPasNormalException(message);

		} // Fin de File directory.____________________________

	} // Fin de traiterFichierPasNormal(
	 // File pFile
	// , String pMethode)._________________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherFichierImporteMap() {
		
		/* retourne null si this.fichierImporteMap est null. */
		if (this.fichierImporteMap == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		final Set<Entry<Integer, SortedMap<Integer, String>>> set 
			= this.fichierImporteMap.entrySet();
		
		final Iterator<Entry<Integer, SortedMap<Integer, String>>> ite 
			= set.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<Integer, SortedMap<Integer, String>> entry 
				= ite.next();
			
			final Integer numeroLigne = entry.getKey();
			
			final SortedMap<Integer, String> champsMap = entry.getValue();
			
			stb.append("Ligne ");
			stb.append(numeroLigne);
			stb.append(IConstantes.SEPARATEUR_MOINS_AERE);
			stb.append(champsMap.toString());
			stb.append(IConstantes.SAUT_LIGNE);
		}
		
		return stb.toString();
		
	} // Fin de afficherFichierImporteMap()._______________________________
	


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compterNombreLignes(
					final File pFile) 
						throws FichierNullException
							, FichierVideException
								, FichierInexistantException
									, FichierPasNormalException
										, IOException {
		
		//************PARAMETRES INVALIDES. *****************************/
		/* Fichier null. */
		this.traiterFichierNull(pFile, METHODE_COMPTERNOMBRELIGNES);
		
		/* Fichier inexistant. */
		this.traiterFichierInexistant(pFile, METHODE_COMPTERNOMBRELIGNES);

		/* Fichier vide. */
		this.traiterFichierVide(pFile, METHODE_COMPTERNOMBRELIGNES);
				
		/* File directory. */
		this.traiterFichierPasNormal(pFile, METHODE_COMPTERNOMBRELIGNES);
		
		//************ PARAMETRES VALIDES *******************************/
				
		int compteur = 0;
		
		final FileReader fr = new FileReader(pFile);
		final BufferedReader bfr= new BufferedReader(fr);
				
		while (bfr.readLine() != null) {
			compteur++;
		}
		
		/* Fermeture des flux. */
		bfr.close();
		fr.close();
		
		return compteur;
		
	} // Fin de compterNombreLignes(
	 // File pFile)._______________________________________________________
	

	
	
	/**
	 * method determinerSiLogErreurs() :<br/>
	 * - Va chercher dans messages_techniques.properties 
	 * si il faut créer des rapports d'erreur d'import des fichiers.<br/>
	 * - Instancie le cas échéant le rapport d'erreur.<br/>
	 * <br/>
	 */
	protected final void determinerSiLogErreurs() {
		
		final String cleLogImport = this.recupererCleLogErreur();

		final String logImportString 
		= ConfigurationApplicationManager.getBundleMessagesTechniques()
				.getString(cleLogImport);
		
		if (StringUtils.containsIgnoreCase(logImportString, "true")) {
			this.logImport = true;
		}
		else {
			this.logImport = false;
		}
		
		/* Instanciation du rapport d'import
		 * si logImport == true. */
		if (this.logImport) {
			this.rapportImportStb = new StringBuffer();
		}
		
	} // Fin de determinerSiLogErreurs().__________________________________
	
	
	
	/**
	 * method determinerSiLogControle() :<br/>
	 * - Va chercher dans messagescontroles.properties 
	 * si il faut créer des rapports de contrôle de validité 
	 * lors de l'import des fichiers.<br/>
	 * - Instancie le cas échéant le rapport de contrôle de validité.<br/>
	 * <br/>
	 */
	protected final void determinerSiLogControle() {
		
		final String cleLogControle = this.recupererCleLogControle();

		final String logControleString 
		= ConfigurationApplicationManager.getBundleMessagesControles()
				.getString(cleLogControle);
		
		if (StringUtils.containsIgnoreCase(logControleString, "true")) {
			this.logControle = true;
		}
		else {
			this.logControle = false;
		}
		
		/* Instanciation du rapport de controle
		 * si logImportDescription == true. */
		if (this.logControle) {
			this.rapportControleStb = new StringBuffer();
			this.rapportControleMap 
			= new TreeMap<Integer, SortedMap<Integer, Set<IMessageControleImport>>>();
		}
		
	} // Fin de determinerSiLogControle().__________________________________
	

	
	/**
	 * method ajouterAuRapportStb(
	 * SortedMap&lt;Integer, Set&lt;IMessageControleImport&gt;&gt; pMapLigne) :<br/>
	 * Ajoute les lignes de messages de contrôle de validité 
	 * à this.rapportControleStb.<br/>
	 * Ajoute automatiquement un en-tête au rapportControleStb.<br/>
	 * <br/>
	 * - ne fait rien si this.rapportControleStb est null.<br/>
	 * - ne fait rien si pMapLigne est null.<br/>
	 * <br/>
	 *
	 * @param pMapLigne : 
	 * SortedMap&lt;Integer, Set&lt;IMessageControleImport&gt;&gt;.<br/>
	 */
	private void ajouterAuRapportStb(
			final SortedMap<Integer, Set<IMessageControleImport>> pMapLigne) {
		
		/* ne fait rien si this.rapportControleStb est null. */
		if (this.rapportControleStb == null) {
			return;
		}
		
		/* ne fait rien si pMapLigne est null. */
		if (pMapLigne == null) {
			return;
		}
				
		// SI LA LIGNE N'EST PAS VIDE._________
		if (!pMapLigne.isEmpty()) {
			
			final Set<Entry<Integer, Set<IMessageControleImport>>> set 
			= pMapLigne.entrySet();
			
			final Iterator<Entry<Integer, Set<IMessageControleImport>>> ite 
			= set.iterator();
			
			// BOUCLE SUR CHAQUE CHAMP D'UNE LIGNE.___________
			while (ite.hasNext()) {
				
				final Entry<Integer, Set<IMessageControleImport>> entry 
				= ite.next();
				
				final Set<IMessageControleImport> setMessages 
				= entry.getValue();
				
				// BOUCLE SUR LES MESSAGES DE CHAQUE CHAMP.___
				for (final IMessageControleImport message : setMessages) {
					
					if (this.rapportControleStb.length() == 0) {
						this.rapportControleStb.append(
								message.fournirLigneEnTetesCsv());
						this.rapportControleStb
							.append(IConstantes.SAUT_LIGNE);
					}
					
					this.rapportControleStb.append(
							message.fournirLigneValeursCsv());
					this.rapportControleStb
						.append(IConstantes.SAUT_LIGNE);
					
				} // FIN DE BOUCLE SUR LES MESSAGES DE CHAQUE CHAMP.___
				
			} // FIN DE BOUCLE SUR CHAQUE CHAMP D'UNE LIGNE.___________
			
		} // FIN DE SI LA LIGNE N'EST PAS VIDE._________________________
		
	} // Fin de ajouterAuRapportStb(...).__________________________________
	
	
		
	/**
	 * method genererAutomatiquementFile(
	 * String pSuffixe 
	 * , Charset pCharset
	 * , String pExtension) :<br/>
	 * Génère automatiquement le fichier de sortie 
	 * dans le même répertoire que this.fichierAImporter 
	 * avec l'extension _ pSuffixe_pCharsetName.pExtension.<br/>
	 * <br/>
	 * Exemple : HIT_DIRA_2013_RapportControle_UTF-8.csv.<br/>
	 * <br/>
	 * - retourne null si this.fichierAImporter est null.<br/>
	 * - retourne null si this.fichierAImporter n'existe pas.<br/>
	 * <br/>
	 *
	 * @param pSuffixe : String : suffixe à ajouter au nom de fichier 
	 * comme 'RapportControle'.<br/>
	 * @param pCharset : Charset.<br/>
	 * @param pExtension : String : Extension à donner au fichier généré.<br/>
	 * 
	 * @return fileGenere : File : le File généré automatiquement.<br/>
	 */
	protected File genererAutomatiquementFile(
			final String pSuffixe
				, final Charset pCharset
					, final String pExtension) {
		
		/* retourne null si this.fichierAImporter est null. */
		if (this.fichierAImporter == null) {
			return null;
		}
		
		/* retourne null si this.fichierAImporter n'existe pas. */
		if (!this.fichierAImporter.exists()) {
			return null;
		}
		
		
		/* Récupération du chemin complet de this.fichierAImporter. */
		final String path = this.fichierAImporter.getAbsolutePath();
		
		/* Récupération du nom de this.fichierAImporter. */
		final String nom = this.fichierAImporter.getName();
		
		/* Récupération du chemin de this.fichierAImporter sans le nom. */
		final String pathSansNom 
			= StringUtils.substringBeforeLast(path, nom);
		
		/* Récupération du nom sans extension. */
		final String nomSansExtension 
			= StringUtils.substringBeforeLast(nom, ".");
				
		/* Création du nom complet du fichier généré. */
		final String cheminGenere 
			= pathSansNom 
			+ nomSansExtension
			+ "_"
			+ pSuffixe
			+ "_" 
			+ pCharset.displayName() 
			+ "." 
			+ pExtension;
		
		/* Création du fichier généré. */
		final File fileGenere = new File(cheminGenere);
		
		return fileGenere;
		
	} // Fin de genererAutomatiquementFile(
	 // String pSuffixe
	// , Charset pCharset
	 // , String pExtension))._____________________________________________
	


		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererCsvRapportControleLatin9() throws IOException {
		
		return this.genererCsvRapportControle(
				null, IConstantes.CHARSET_LATIN9);
		
	} // Fin de genererCsvRapportControleLatin9()._________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererCsvRapportControleANSI() throws IOException {
		
		return this.genererCsvRapportControle(
				null, IConstantes.CHARSET_ANSI);
		
	} // Fin de genererCsvRapportControleANSI().___________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererCsvRapportControleUtf8() throws IOException {
		
		return this.genererCsvRapportControle(
				null, IConstantes.CHARSET_UTF8);
		
	} // Fin de genererCsvRapportControleUtf8()._____________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererCsvRapportControleLatin9(
			final File pFile) throws IOException {
		
		return this.genererCsvRapportControle(
				pFile, IConstantes.CHARSET_LATIN9);
		
	} // Fin de genererCsvRapportControleLatin9(
	 // File pFile)._______________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererCsvRapportControleANSI(
			final File pFile) throws IOException {
		
		return this.genererCsvRapportControle(
				pFile, IConstantes.CHARSET_ANSI);
		
	} // Fin de genererCsvRapportControleANSI(
	 // File pFile)._______________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererCsvRapportControleUtf8(
			final File pFile) throws IOException {
		
		return this.genererCsvRapportControle(
				pFile, IConstantes.CHARSET_UTF8);
		
	} // Fin de genererCsvRapportControleUtf8(
	 // File pFile)._______________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererCsvRapportControle(
			final File pFile
					, final Charset pCharset) throws IOException {
		
		/* retourne null si this.rapportControleStb est null. */
		if (this.rapportControleStb == null) {
			return null;
		}
		
		File fileGenere = null;
		
		/* Génère automatiquement le fichier de sortie dans le 
		 * même répertoire que this.fichierAImporter 
		 * avec l'extension _RapportControle_charset.csv 
		 * si pFile est null. */
		if (pFile == null) {
			
			fileGenere = this.genererAutomatiquementFile(
					"RapportControle", pCharset, "csv");
			
			/* retourne null si pFile est null et 
			 * this.fichierAImporter est introuvable. */
			if (fileGenere == null) {
				return null;
			}
			
		}
		else {
			
			fileGenere = pFile;
		}
	
		/* OUVERTURE DES FLUX EN ECRITURE VERS LE FICHIER A GENERER. */
		/* ECRITURE EN pCharset. */
		final FileOutputStream fos = new FileOutputStream(fileGenere);
		final OutputStreamWriter osw = new OutputStreamWriter(fos, pCharset);
		final BufferedWriter bfw = new BufferedWriter(osw);
				
		
		/* Ajout des lignes de données. */
		/*String to File. */
		/* OUVERTURE DES FLUX EN LECTURE VERS LE RAPPORT. */
		final StringReader sr 
			= new StringReader(this.rapportControleStb.toString());
		final BufferedReader bfr = new BufferedReader(sr);
		
		String ligneLue = null;
		int numeroLigneLue = 0;
		
		while ((ligneLue = bfr.readLine()) != null) {
			
			numeroLigneLue++;
			
			/* Rajoute un BOM-UTF-8 à la première ligne si 
			 * pCharset == CHARSET_UTF8 pour forcer 
			 * Excel à détecter l'UTF-8. */
			if (numeroLigneLue == 1) {
				if (pCharset.equals(IConstantes.CHARSET_UTF8)) {
					ligneLue = IConstantes.BOM_UTF_8 + ligneLue;
				}
			}
			
			
			/* Ecriture de la ligne dans le flux (en UTF-8)*/
			bfw.write(ligneLue);
			
			/* Insertion d'un saut de ligne. */
			bfw.newLine();
			
		}
		
		/* ECRITURE SUR DISQUE. */
		bfw.flush();
		
		/* FERMETURE DES FLUX. */
		bfw.close();
		osw.close();
		fos.close();
		
		return fileGenere;
	
	} // Fin de genererCsvRapportControle(
	 // File pFile
	 // , Charset pCharset)._______________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherRapportControleMap() {
		
		/* retourne null si this.rapportControleMap est null. */
		if (this.rapportControleMap == null) {
			return null;
		}
		
		/* retourne vide si this.rapportControleMap est vide. */
		if (this.rapportControleMap.isEmpty()) {
			return "";
		}
		
		final StringBuffer stb =  new StringBuffer();
		
		/* Obtention d'un Set<Entry> auprès de la Map contenant 
		 * le rapport de contrôle de validité des champs. */
		final Set<Entry<Integer
			, SortedMap<Integer, Set<IMessageControleImport>>>> set 
				= this.rapportControleMap.entrySet();
		
		/* retourne null si le set obtenu auprès de la Map est null; */
		if (set == null) {
			return null;
		}
		
		/* Obtention d'un Iterator auprès du Set<Entry>. */
		final Iterator<Entry<Integer
			, SortedMap<Integer, Set<IMessageControleImport>>>> ite 
				= set.iterator();
		
		/* retourne null si l'Iterator obtenu auprès du Set est null. */
		if (ite == null) {
			return null;
		}
		
		// BOUCLE SUR LES LIGNES.____________________________________________
		while(ite.hasNext()) {
			
			final Entry<Integer
				, SortedMap<Integer, Set<IMessageControleImport>>> entry 
					= ite.next();
			
			/* retourne null si l'Entry obtenu auprès de l'Iterator est null; */
			if (entry == null) {
				return null;
			}
			
			/* Récupération des valeurs dans l'Entry. */
			final Integer numeroLigne = entry.getKey();
			final SortedMap<Integer, Set<IMessageControleImport>> map 
				= entry.getValue();
			
			/* Si la Map des rapports de contrôle de validité 
			 * sur le champ est null ou vide
			 * , reprend la boucle. */
			if (map == null || map.isEmpty()) {
				continue;
			}
	
			/* Sinon, construit le message pour le 
			 * rapport de contrôle de validité des champs. */
			stb.append("Ligne numéro : ");
			stb.append(numeroLigne);
			stb.append(IConstantes.SAUT_LIGNE);
						
			/* Récupération de toutes les lignes 
			 * du rapport de contrôle de validité des champs 
			 * relatives au champ considéré auprès de setChamps. */
			final Set<Entry<Integer, Set<IMessageControleImport>>> setChamps 
				= map.entrySet();
			
			// SI LE SET DES MESSAGES DU CHAMP N'EST PAS NULL.__________
			if (setChamps != null) {
				
				// SI LE SET DES MESSAGES DU CHAMP N'EST PAS VIDE.__________
				if (!setChamps.isEmpty()) {
					
					final Iterator<Entry<Integer, Set<IMessageControleImport>>> iteChamps 
					= setChamps.iterator();
					
					// BOUCLE SUR CHAQUE CHAMP._________
					while(iteChamps.hasNext()) {
						
						final Entry<Integer, Set<IMessageControleImport>> entryChamp 
						= iteChamps.next();
						
						/* Poursuit la boucle si entryChamp == null. */
						if (entryChamp == null) {
							continue;
						}
						
						/* Sinon, récupère l'ensemble des 
						 * messages de contrôle de validité des champs 
						 * relatifs au champ considéré. */
						final Set<IMessageControleImport> setMessagesChamp 
						= entryChamp.getValue();
						
						// BOUCLE SUR CHAQUE MESSAGE DU CHAMP._____
						for (final IMessageControleImport  message : setMessagesChamp) {
							
							/* Ajoute chaque message relatif au champ 
							 * considéré si le message n'est pas null. */
							if (message != null) {								
								stb.append(message.toString());
								stb.append(IConstantes.SAUT_LIGNE);								
							}
														
						} // FIN DE BOUCLE SUR CHAQUE MESSAGE DU CHAMP._____
						
					} // FIN DE BOUCLE SUR CHAQUE CHAMP._________
					
				} // FIN DE SI LE SET DES MESSAGES DU CHAMP N'EST PAS VIDE.____
				
			} // FIN DE SI LE SET DES MESSAGES DU CHAMP N'EST PAS NULL.________
			
		} // FIN DE BOUCLE SUR LES LIGNES.____________________________________
		
		return stb.toString();
		
	} // Fin de afficherRapportControleMap().______________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherRapportControleMapLigne(
			final int pNumeroLigne) {
		
		/* retourne null si this.rapportControleMap est null. */
		if (this.rapportControleMap == null) {
			return null;
		}
		
		/* retourne vide si this.rapportControleMap est vide. */
		if (this.rapportControleMap.isEmpty()) {
			return "";
		}
		
		final StringBuffer stb =  new StringBuffer();
		
		final SortedMap<Integer, Set<IMessageControleImport>> map 
		= this.rapportControleMap.get(pNumeroLigne);
		
		/* retourne null si aucun contrôle n' a matché pour cette ligne. */
		if (map == null || map.isEmpty()) {
			return null;
		}
		
		stb.append("Ligne numéro : ");
		stb.append(pNumeroLigne);
		stb.append(IConstantes.SAUT_LIGNE);
					
		
		final Set<Entry<Integer, Set<IMessageControleImport>>> setChamps 
			= map.entrySet();
		
		// SI LE SET DES MESSAGES DU CHAMP N'EST PAS NULL.__________
		if (setChamps != null) {
			
			// SI LE SET DES MESSAGES DU CHAMP N'EST PAS VIDE.__________
			if (!setChamps.isEmpty()) {
				
				final Iterator<Entry<Integer, Set<IMessageControleImport>>> iteChamps 
				= setChamps.iterator();
				
				// BOUCLE SUR CHAQUE CHAMP._________
				while(iteChamps.hasNext()) {
					
					final Entry<Integer, Set<IMessageControleImport>> entryChamp 
					= iteChamps.next();
					
					final Set<IMessageControleImport> setMessagesChamp 
					= entryChamp.getValue();
					
					// BOUCLE SUR CHAQUE MESSAGE DU CHAMP._____
					for (final IMessageControleImport  message : setMessagesChamp) {
						
						stb.append(message.toString());
						stb.append(IConstantes.SAUT_LIGNE);
						
					} // FIN DE BOUCLE SUR CHAQUE MESSAGE DU CHAMP._____
					
				} // FIN DE BOUCLE SUR CHAQUE CHAMP._________
				
			} // FIN DE SI LE SET DES MESSAGES DU CHAMP N'EST PAS VIDE.____
			
		} // FIN DE SI LE SET DES MESSAGES DU CHAMP N'EST PAS NULL.________
	
		return stb.toString();
		
	} // Fin de afficherRapportControleMapLigne(
	 // int pNumeroLigne)._________________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnteteparColonne(
			final int pI) 
			throws MapNullException, MapVideException {
		
		/* retourne null si this.importateurDescription est null. */
		if (this.importateurDescription == null) {
			return null;
		}
		
		/* retourne null si this.importateurDescription.getDescriptionChamp(pI)
		 *  est null. */
		if (this.importateurDescription
				.getDescriptionChamp(pI) == null) {
			return null;
		}
		
		return this.importateurDescription
				.getDescriptionChamp(pI).getIntitule();
		
	} // Fin de getEnteteparColonne(
	// int pI).____________________________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirValeurparLigneColonne(
			final int pL
			, final int pI) {
		
		/* retourne null si this.fichierImporteMap est null. */
		if (this.fichierImporteMap == null) {
			return null;
		}
		
		/* Extraction d'une ligne. */
		final SortedMap<Integer, String> mapLigne = 
				this.fichierImporteMap.get(pL);
		
		/* retourne null si la ligne pL n'existe pas.  */
		if (mapLigne == null) {
			return null;
		}
		
		return mapLigne.get(pI);
		
	} // Fin de getValeurparLigneColonne(
	// int pL
	// , int pI).__________________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirLigneEnTetesCsv() {
				
		final SortedMap<Integer, IDescriptionChamp> descriptionMap 
			= this.importateurDescription.getSpecificationChampsMap();
		
		final Set<Entry<Integer, IDescriptionChamp>> set 
		= descriptionMap.entrySet();
		
		final StringBuffer stb = new StringBuffer();
		
		// BOUCLE SUR LES CHAMPS DE LA DESCRIPTION.-----
		for (final Entry<Integer, IDescriptionChamp> entry : set) {
			
			final IDescriptionChamp desc = entry.getValue();
			
			stb.append(desc.getIntitule());
			stb.append(IConstantes.SEP_PV);
			
		} // FIN BOUCLE SUR LES CHAMPS DE LA DESCRIPTION.-----			
		// FIN de DECOMPOSITION D'UNE LIGNE.---------------------------
			
		return stb.toString();
		
	} // Fin de fournirLigneEnTetesCsv().____________________________________

	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirLigneValeursCsv(final int pL) {
		
		/* retourne null si this.fichierImporteMap est null. */
		if (this.fichierImporteMap == null) {
			return null;
		}
		
		final SortedMap<Integer, String> ligne = this.fichierImporteMap.get(pL);
		
		if (ligne == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		final Set<Entry<Integer, String>> set = ligne.entrySet();
		
		for (final Entry<Integer, String> entry : set) {
			
			final String valeur = entry.getValue();
			
			stb.append(valeur);
			stb.append(IConstantes.SEP_PV);
		}
		
		return stb.toString();
		
	} // Fin de fournirLigneValeursCsv(
	// int pL).____________________________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String genererCsvString() {
		
		return this.genererCsvString(true);
		
	} // Fin de genererCsvString().________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String genererCsvString(
			final boolean pAvecLigneEntetes) {
		
		/* retourne null si this.fichierImporteMap est null. */
		if (this.fichierImporteMap == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		/* Ajout de la ligne d'en-tête si pAvecLigneEntetes. */
		if (pAvecLigneEntetes) {
			stb.append(this.fournirLigneEnTetesCsv());
			stb.append(IConstantes.SAUT_LIGNE);
		}
		
		final int nombreLignes = this.fichierImporteMap.size();
		
		for (int i = 1; i <= nombreLignes; i++) {
			
			stb.append(this.fournirLigneValeursCsv(i));
			
			if (i < nombreLignes) {
				stb.append(IConstantes.SAUT_LIGNE);
			}
		}
		
		return stb.toString();
		
	} // Fin de genererCsvString(
	// boolean pAvecLigneEntetes)._________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererCsvFile() throws IOException {
		
		return this.genererCsvFile(true, null);
		
	} // Fin de genererCsvFile().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererCsvFile(
			final File pFile) throws IOException {
		
		return this.genererCsvFile(true, pFile);
		
	} // Fin de genererCsvFile(
	 // File pFile)._______________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File genererCsvFile(
			final boolean pAvecLigneEntetes
				, final File pFile) throws IOException {
		
		/* retourne null si this.fichierImporteMap est null. */
		if (this.fichierImporteMap == null) {
			return null;
		}
		
		File fileGenere = null;
		
		/* Génère automatiquement le fichier de sortie dans le 
		 * même répertoire que this.fichierAImporter 
		 * avec l'extension _utf8.csv 
		 * si pFile est null. */
		if (pFile == null) {
			
			fileGenere = this.genererAutomatiquementFile();
			
			/* retourne null si pFile est null et 
			 * this.fichierAImporter est introuvable. */
			if (fileGenere == null) {
				return null;
			}
			
		}
		else {
			
			fileGenere = pFile;
		}

		/* OUVERTURE DES FLUX EN ECRITURE VERS LE FICHIER A GENERER. */
		/* ECRITURE EN UTF-8. */
		final FileOutputStream fos = new FileOutputStream(fileGenere);
		final OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		final BufferedWriter bfw = new BufferedWriter(osw);
				
		/* Ajout éventuel de la ligne d'en-têtes. */
		if (pAvecLigneEntetes) {
			
			bfw.write(this.fournirLigneEnTetesCsv());
			
			/* Saut de ligne*/
			bfw.newLine();
		}
		
		/* Ajout des lignes de données. */
		final int nombreLignes = this.fichierImporteMap.size();
		
		for (int i = 1; i <= nombreLignes; i++) {
			
			/* Récupération de la ligne csv. */
			final String ligneCsv = this.fournirLigneValeursCsv(i);
			
			if (ligneCsv != null) {
				
				bfw.write(ligneCsv);
				
				if (i < nombreLignes) {
					
					/* Saut de ligne*/
					bfw.newLine();
				}
			}			
		}
		
		
		/* ECRITURE SUR DISQUE. */
		bfw.flush();
		
		/* FERMETURE DES FLUX. */
		bfw.close();
		osw.close();
		fos.close();
		
		return fileGenere;
		
	} // Fin de genererCsvFile(
	 // boolean pAvecLigneEntetes
	 // , File pFile)._____________________________________________________


	
	/**
	 * method genererCsvFileLatin9() :<br/>
	 * Génère un fichier csv encodé en Latin9 avec séparateur ';' contenant 
	 * le fichier importé this.fichierAImporter.<br/>
	 * Ce fichier csv généré est accessible à pFile.<br/>
	 * contient la ligne d'en-tête.<br/>
	 * Génère automatiquement le fichier de sortie dans le 
	 * même répertoire que this.fichierAImporter 
	 * avec l'extension _ISO-8859-15.csv.<br/>
	 * <br/>
	 * FAIRE IMPORTER pour remplir this.fichierImporteMap 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si this.fichierImporteMap est null.<br/>
	 * - retourne null si pFile est null et 
	 * this.fichierAImporter est introuvable.<br/>
	 * <br/>
	 *
	 * @return : File : le fichier csv généré.<br/>
	 * 
	 * @throws IOException<br/>
	 */
	@Override
	public final File genererCsvFileLatin9() throws IOException {
		
		return this.genererCsvFile(true, null, IConstantes.CHARSET_LATIN9);
		
	} // Fin de genererCsvFileLatin9().____________________________________

	
	
	/**
	 * method genererCsvFileUtf8() :<br/>
	 * Génère un fichier csv encodé en UTF-8 avec séparateur ';' contenant 
	 * le fichier importé this.fichierAImporter.<br/>
	 * Ce fichier csv généré est accessible à pFile.<br/>
	 * contient la ligne d'en-tête.<br/>
	 * Génère automatiquement le fichier de sortie dans le 
	 * même répertoire que this.fichierAImporter 
	 * avec l'extension _UTF-8.csv.<br/>
	 * <br/>
	 * FAIRE IMPORTER pour remplir this.fichierImporteMap 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si this.fichierImporteMap est null.<br/>
	 * - retourne null si pFile est null et 
	 * this.fichierAImporter est introuvable.<br/>
	 * <br/>
	 *
	 * @return : File : le fichier csv généré.<br/>
	 * 
	 * @throws IOException<br/>
	 */
	@Override
	public final File genererCsvFileUtf8() throws IOException {
		
		return this.genererCsvFile(true, null, IConstantes.CHARSET_UTF8);
		
	} // Fin de genererCsvFileUtf8().______________________________________
	
	
	
	/**
	 * method genererCsvFile(
	 * boolean pAvecLigneEntetes
	 * , File pFile
	 * , Charset pCharset) :<br/>
	 * Génère un fichier csv encodé en pCharset avec séparateur ';' contenant 
	 * le fichier importé this.fichierAImporter.<br/>
	 * Ce fichier csv généré est accessible à pFile.<br/>
	 * pAvecLigneEntetes à true permet de générer la ligne d'en-tête.<br/>
	 * Génère automatiquement le fichier de sortie dans le 
	 * même répertoire que this.fichierAImporter 
	 * avec l'extension _charset.csv 
	 * si pFile est null.<br/>
	 * FAIRE IMPORTER pour remplir this.fichierImporteMap 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si this.fichierImporteMap est null.<br/>
	 * - retourne null si pFile est null et 
	 * this.fichierAImporter est introuvable.<br/>
	 * <br/>
	 *
	 * @param pAvecLigneEntetes : boolean :boolean qui stipule 
	 * si il faut rajouter la ligne d'en-têtes au fichier csv généré.<br/>
	 * @param pFile : File : le fichier csv à générer.<br/>
	 * @param pCharset : Charset : le Charset dans lequel 
	 * on veut encoder le fichier généré.<br/>
	 * 
	 * @return : File : le fichier csv généré.<br/>
	 * 
	 * @throws IOException<br/>
	 */
	@Override
	public final File genererCsvFile(
			final boolean pAvecLigneEntetes
				, final File pFile
					, final Charset pCharset) throws IOException {
		
		/* retourne null si this.fichierImporteMap est null. */
		if (this.fichierImporteMap == null) {
			return null;
		}
		
		File fileGenere = null;
		
		/* Génère automatiquement le fichier de sortie dans le 
		 * même répertoire que this.fichierAImporter 
		 * avec l'extension _charset.csv 
		 * si pFile est null. */
		if (pFile == null) {
			
			fileGenere = this.genererAutomatiquementFile(pCharset);
			
			/* retourne null si pFile est null et 
			 * this.fichierAImporter est introuvable. */
			if (fileGenere == null) {
				return null;
			}
			
		}
		else {
			
			fileGenere = pFile;
		}

		/* OUVERTURE DES FLUX EN ECRITURE VERS LE FICHIER A GENERER. */
		/* ECRITURE EN pCharset. */
		final FileOutputStream fos = new FileOutputStream(fileGenere);
		final OutputStreamWriter osw = new OutputStreamWriter(fos, pCharset);
		final BufferedWriter bfw = new BufferedWriter(osw);
				
		/* Ajout éventuel de la ligne d'en-têtes. */
		if (pAvecLigneEntetes) {
			
			bfw.write(this.fournirLigneEnTetesCsv());
			
			/* Saut de ligne*/
			bfw.newLine();
		}
		
		/* Ajout des lignes de données. */
		final int nombreLignes = this.fichierImporteMap.size();
		
		// ECRITURE DES LIGNES._____________________________
		for (int i = 1; i <= nombreLignes; i++) {
			
			/* Récupération de la ligne csv. */
			final String ligneCsv = this.fournirLigneValeursCsv(i);
			
			if (ligneCsv != null) {
				
				bfw.write(ligneCsv);
				
				if (i < nombreLignes) {
					
					/* Saut de ligne*/
					bfw.newLine();
				}
			}
			
		} // FIN DE ECRITURE DES LIGNES.__________________________
		
		
		/* ECRITURE SUR DISQUE. */
		bfw.flush();
		
		/* FERMETURE DES FLUX. */
		bfw.close();
		osw.close();
		fos.close();
		
		return fileGenere;
		
	} // Fin de genererCsvFile(
	 // boolean pAvecLigneEntetes
	 // , File pFile
	// , Charset pCharset).________________________________________________
	


	/**
	 * method genererAutomatiquementFile() :<br/>
	 * Génère automatiquement le fichier de sortie 
	 * dans le même répertoire que this.fichierAImporter 
	 * avec l'extension _utf8.csv.<br/>
	 * <br/>
	 * - retourne null si this.fichierAImporter est null.<br/>
	 * - retourne null si this.fichierAImporter n'existe pas.<br/>
	 * <br/>
	 *
	 * @return fileGenere : File : le File généré automatiquement.<br/>
	 */
	private File genererAutomatiquementFile() {
		
		/* retourne null si this.fichierAImporter est null. */
		if (this.fichierAImporter == null) {
			return null;
		}
		
		/* retourne null si this.fichierAImporter n'existe pas. */
		if (!this.fichierAImporter.exists()) {
			return null;
		}
		
		
		/* Récupération du chemin complet de this.fichierAImporter. */
		final String path = this.fichierAImporter.getAbsolutePath();
		
		/* Récupération du nom de this.fichierAImporter. */
		final String nom = this.fichierAImporter.getName();
		
		/* Récupération du chemin de this.fichierAImporter sans le nom. */
		final String pathSansNom 
			= StringUtils.substringBeforeLast(path, nom);
		
		/* Récupération du nom sans extension. */
		final String nomSansExtension 
			= StringUtils.substringBeforeLast(nom, ".");
				
		/* Création du nom complet du fichier généré. */
		final String cheminGenere 
			= pathSansNom 
			+ nomSansExtension
			+ "_utf8.csv";
		
		/* Création du fichier généré. */
		final File fileGenere = new File(cheminGenere);
		
		return fileGenere;
		
	} // Fin de genererAutomatiquementFile().______________________________
	



	/**
	 * method genererAutomatiquementFile(
	 * Charset pCharset) :<br/>
	 * Génère automatiquement le fichier de sortie 
	 * dans le même répertoire que this.fichierAImporter 
	 * avec l'extension _charset.csv.<br/>
	 * <br/>
	 * - retourne null si this.fichierAImporter est null.<br/>
	 * - retourne null si this.fichierAImporter n'existe pas.<br/>
	 * <br/>
	 * 
	 * @param pCharset : Charset.<br/>
	 *
	 * @return fileGenere : File : le File généré automatiquement.<br/>
	 */
	private File genererAutomatiquementFile(
			final Charset pCharset) {
		
		/* retourne null si this.fichierAImporter est null. */
		if (this.fichierAImporter == null) {
			return null;
		}
		
		/* retourne null si this.fichierAImporter n'existe pas. */
		if (!this.fichierAImporter.exists()) {
			return null;
		}
		
		
		/* Récupération du chemin complet de this.fichierAImporter. */
		final String path = this.fichierAImporter.getAbsolutePath();
		
		/* Récupération du nom de this.fichierAImporter. */
		final String nom = this.fichierAImporter.getName();
		
		/* Récupération du chemin de this.fichierAImporter sans le nom. */
		final String pathSansNom 
			= StringUtils.substringBeforeLast(path, nom);
		
		/* Récupération du nom sans extension. */
		final String nomSansExtension 
			= StringUtils.substringBeforeLast(nom, ".");
				
		/* Création du nom complet du fichier généré. */
		final String cheminGenere 
			= pathSansNom 
			+ nomSansExtension
			+ "_" 
			+ pCharset.displayName() 
			+ ".csv";
		
		/* Création du fichier généré. */
		final File fileGenere = new File(cheminGenere);
		
		return fileGenere;
		
	} // Fin de genererAutomatiquementFile(
	// Charset pCharset).__________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String reconstituerLigne(
			final int pI) {
		
		return this.reconstituerLigne(this.fichierImporteMap, pI);
		
	} // Fin de reconstituerLigne(
	 // int pI).___________________________________________________________
	
	

	/**
	 * method reconstituerLigne(
	 * SortedMap<Integer, SortedMap<Integer, String>> pFichierImporteMap
	 * , int pI) :<br/>
	 * Reconstitue la pI-ème ligne du fichier 
	 * (telle qu'elle est stockée dans this.fichierImporteMap, 
	 * donc avec l'encodage par défaut de la plateforme UTF-8).<br/>
	 * <br/>
	 * - retourne null si pFichierImporteMap est null.<br/>
	 * <br/>
	 *
	 * @param pFichierImporteMap : 
	 * SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt;.<br/>
	 * @param pI : int : numéro (1-based) de la ligne du fichier.<br/>
	 * 
	 * @return : String : pI-ème ligne du fichier.<br/>
	 */
	protected abstract String reconstituerLigne(
			final SortedMap<Integer, SortedMap<Integer, String>> pFichierImporteMap
				, final int pI);
	
	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File reconstituerFichierLatin9() throws IOException {
		
		return this.reconstituerFichier(
				this.fichierImporteMap
					, IConstantes.CHARSET_LATIN9, false, null);
		
	} // Fin de reconstituerFichierLatin9()._______________________________
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File reconstituerFichierUtf8() throws IOException {
		
		return this.reconstituerFichier(
				this.fichierImporteMap
					, IConstantes.CHARSET_UTF8, false, null);
		
	} // Fin de reconstituerFichierUtf8()._________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File reconstituerFichier(
			final SortedMap<Integer, SortedMap<Integer, String>> pFichierImporteMap
				, final Charset pCharset
					, final boolean pLigneEntete
						, final File pFile) throws IOException {
		
		/* retourne null si pFichierImporteMap est null. */
		if (pFichierImporteMap == null) {
			return null;
		}
				
		// DETERMINATION DU CHARSET._______
		Charset charset = null;
		
		if (pCharset == null) {
			charset = IConstantes.CHARSET_UTF8;
		}
		else {
			charset = pCharset;
		}
		
		// DETERMINATION DU FILE GENERE.______________
		File fileGenere = null;
		
		/* Génère automatiquement le fichier de sortie dans le 
		 * même répertoire que this.fichierAImporter 
		 * avec l'extension _charset.extension 
		 * si pFile est null. */
		if (pFile == null) {
			
			fileGenere = this.genererAutomatiquementFileReconstitue(pCharset);
			
			/* retourne null si pFile est null et 
			 * this.fichierAImporter est introuvable. */
			if (fileGenere == null) {
				return null;
			}
			
		}
		else {
			
			fileGenere = pFile;
		}
		
		
		/* OUVERTURE DES FLUX EN ECRITURE VERS LE FICHIER A GENERER. */
		/* ECRITURE EN pCharset. */
		final FileOutputStream fos = new FileOutputStream(fileGenere);
		final OutputStreamWriter osw = new OutputStreamWriter(fos, charset);
		final BufferedWriter bfw = new BufferedWriter(osw);

		
		/* Ajout éventuel de la ligne d'en-têtes. */
		if (pLigneEntete) {
			
			bfw.write(this.fournirLigneEnTetesCsv());
			
			/* Saut de ligne*/
			bfw.newLine();
		}
		
		/* Ajout des lignes de données. */
		final int nombreLignes = pFichierImporteMap.size();
		
		// ECRITURE DES LIGNES._____________________________
		for (int i = 1; i <= nombreLignes; i++) {
			
			/* Récupération de la ligne reconstituée. */
			final String ligneReconstituee 
				= this.reconstituerLigne(
						pFichierImporteMap, i);
			
			if (ligneReconstituee != null) {
				
				bfw.write(ligneReconstituee);
				
				if (i < nombreLignes) {
					
					/* Saut de ligne*/
					bfw.newLine();
				}
			}
			
		} // FIN DE ECRITURE DES LIGNES.__________________________
		
		
		/* ECRITURE SUR DISQUE. */
		bfw.flush();
		
		/* FERMETURE DES FLUX. */
		bfw.close();
		osw.close();
		fos.close();
		
		return fileGenere;
	
	} // Fin de reconstituerFichier(
	 // SortedMap<Integer, SortedMap<Integer, String>> pFichierImporteMap
	 // , Charset pCharset
	 // , boolean pLigneEntete
	 // , File pFile)._____________________________________________________
	

	
	/**
	 * method genererAutomatiquementFileReconstitue(
	 * Charset pCharset) :<br/>
	 * Génère automatiquement le fichier de sortie 
	 * dans le même répertoire que this.file 
	 * avec l'extension _charset.extension.<br/>
	 * <br/>
	 * - retourne null si this.fichierAImporter est null.<br/>
	 * - retourne null si this.fichierAImporter n'existe pas.<br/>
	 * <br/>
	 * 
	 * @param pCharset : Charset.<br/>
	 *
	 * @return fileGenere : File : le File généré automatiquement.<br/>
	 */
	private File genererAutomatiquementFileReconstitue(
			final Charset pCharset) {
		
		/* retourne null si this.fichierAImporter est null. */
		if (this.fichierAImporter == null) {
			return null;
		}
		
		/* retourne null si this.fichierAImporter n'existe pas. */
		if (!this.fichierAImporter.exists()) {
			return null;
		}
		
		
		/* Récupération du chemin complet de this.fichierAImporter. */
		final String path = this.fichierAImporter.getAbsolutePath();
		
		/* Récupération du nom de this.fichierAImporter. */
		final String nom = this.fichierAImporter.getName();
		
		/* Récupération du chemin de this.fichierAImporter sans le nom. */
		final String pathSansNom 
			= StringUtils.substringBeforeLast(path, nom);
		
		/* Récupération du nom sans extension. */
		final String nomSansExtension 
			= StringUtils.substringBeforeLast(nom, ".");
		
		final String extension 
		= StringUtils.substringAfterLast(nom, ".");
				
		/* Création du nom complet du fichier généré. */
		final String cheminGenere 
			= pathSansNom 
			+ nomSansExtension
			+ "_" 
			+ pCharset.displayName() 
			+ extension;
		
		/* Création du fichier généré. */
		final File fileGenere = new File(cheminGenere);
		
		return fileGenere;
		
	} // Fin de genererAutomatiquementFileReconstitue(
	// Charset pCharset).__________________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IDescriptionChamp getDescriptionChamp() {
		return this.descriptionChamp;
	} // Fin de getDescriptionChamp()._____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setDescriptionChamp(
			final IDescriptionChamp pDescriptionChamp) {
		this.descriptionChamp = pDescriptionChamp;
	} // Fin de setDescriptionChamp(
	 // IDescriptionChamp pDescriptionChamp).______________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IImportateurDescription getImportateurDescription() {
		return this.importateurDescription;
	} // Fin de getImportateurDescription()._______________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setImportateurDescription(
			final IImportateurDescription pImportateurDescription) {
		this.importateurDescription = pImportateurDescription;
	} // Fin de setImportateurDescription(
	 // IImportateurDescription pImportateurDescription).__________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isLogImport() {
		return this.logImport;
	} // Fin de isLogImport()._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setLogImport(
			final boolean pLogImport) {
		this.logImport = pLogImport;
	} // Fin de setLogImport(
	 // boolean pLogImport)._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final StringBuffer getRapportImportStb() {
		return this.rapportImportStb;
	} // Fin de getRapportImportStb()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setRapportImportStb(
			final StringBuffer pRapportImportStb) {
		this.rapportImportStb = pRapportImportStb;
	} // Fin de setRapportImportStb(
	 // StringBuffer pRapportImportStb).___________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getFichierAImporter() {
		return this.fichierAImporter;
	} // Fin de getFichierAImporter()._____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setFichierAImporter(
			final File pFichierAImporter) {
		this.fichierAImporter = pFichierAImporter;
	} // Fin de setFichierAImporter(
	 // File pFichierAImporter).___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, SortedMap<Integer, String>> 
											getFichierImporteMap() {
		return this.fichierImporteMap;
	} // Fin de getFichierImporteMap().____________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setFichierImporteMap(
			final SortedMap<Integer, SortedMap<Integer, String>> 
													pFichierImporteMap) {
		this.fichierImporteMap = pFichierImporteMap;
	} // Fin de setFichierImporteMap(
	 // SortedMap<Integer,SortedMap<Integer,String>> pFichierImporteMap).__



	/**
	 * method isLogControle() :<br/>
	 * Getter du boolean qui stipule si les Importeur
	 * doivent rapporter ou pas les contrôles de validité 
	 * (rapport d'erreurs de format lors de la lecture de fichiers).<br/>
	 * <br/>
	 *
	 * @return logControle : boolean.<br/>
	 */
	@Override
	public final boolean isLogControle() {
		return this.logControle;
	} // Fin de isLogControle().___________________________________________



	/**
	 * method setLogControle(
	 * boolean pLogControle) :<br/>
	 * Setter du boolean qui stipule si les Importeur
	 * doivent rapporter ou pas les contrôles de validité 
	 * (rapport d'erreurs de format lors de la lecture de fichiers).<br/>
	 * <br/>
	 *
	 * @param pLogControle : boolean : 
	 * valeur à passer à logControle.<br/>
	 */
	@Override
	public final void setLogControle(
			final boolean pLogControle) {
		this.logControle = pLogControle;
	} // Fin de setLogControle(
	 // boolean pLogControle)._____________________________________________



	/**
	 * method getRapportControleStb() :<br/>
	 * Getter du StringBuffer chargé de contenir le rapport des
	 * contrôles de validité des champs 
	 * lors de l'import du fichier.<br/>
	 * <br/>
	 * - Le rapport ne peut être null que si this.logControle == false.<br/>
	 * - Sinon, le rapport peut être VIDE si aucun contrôle n'a matché.<br/>
	 * <br/>
	 *
	 * @return rapportControleStb : StringBuffer.<br/>
	 */
	@Override
	public final StringBuffer getRapportControleStb() {
		return this.rapportControleStb;
	} // Fin de getRapportControleStb().___________________________________



	/**
	 * method setRapportControleStb(
	 * StringBuffer pRapportControleStb) :<br/>
	 * Setter du StringBuffer chargé de contenir le rapport des
	 * contrôles de validité des champs 
	 * lors de l'import du fichier.<br/>
	 * <br/>
	 *
	 * @param pRapportControleStb : StringBuffer : 
	 * valeur à passer à rapportControleStb.<br/>
	 */
	@Override
	public final void setRapportControleStb(
			final StringBuffer pRapportControleStb) {
		this.rapportControleStb = pRapportControleStb;
	} // Fin de setRapportControleStb(
	 // StringBuffer pRapportControleStb)._________________________________



	/**
	 * method getRapportControleMap() :<br/>
	 * Getter de la 
	 * SortedMap&lt;Integer, SortedMap&lt;Integer
	 * , Set&lt;IMessageControleImport&gt;&gt;&gt; :<br/>
	 * Rapport de contrôle de validité sous forme de map triée avec :<br/>
	 * - Integer : le numéro de la ligne.<br/>
	 * - SortedMap&lt;Integer, Set&lt;IMessageControleImport&gt;&gt; 
	 * map triée avec :<br/>
	 * - Integer : le numéro d'ordre du champ dans la ligne.<br/>
	 * - Set&lt;IMessageControleImport&gt; : 
	 * ensemble des messages de contrôle pour ce champ.<br/>
	 * <br/>
	 *
	 * @return rapportControleMap : 
	 * SortedMap<Integer,SortedMap<Integer,Set<IMessageControleImport>>>.<br/>
	 */
	@Override
	public final SortedMap<Integer, SortedMap<Integer, Set<IMessageControleImport>>> 
													getRapportControleMap() {
		return this.rapportControleMap;
	} // Fin de getRapportControleMap().___________________________________



	/**
	 * method setRapportControleMap(
	 * SortedMap<Integer,SortedMap<Integer,Set<IMessageControleImport>>> 
	 * pRapportControleMap) :<br/>
	 * Setter de la 
	 * SortedMap&lt;Integer, SortedMap&lt;Integer
	 * , Set&lt;IMessageControleImport&gt;&gt;&gt; :<br/>
	 * Rapport de contrôle de validité sous forme de map triée avec :<br/>
	 * - Integer : le numéro de la ligne.<br/>
	 * - SortedMap&lt;Integer, Set&lt;IMessageControleImport&gt;&gt; 
	 * map triée avec :<br/>
	 * - Integer : le numéro d'ordre du champ dans la ligne.<br/>
	 * - Set&lt;IMessageControleImport&gt; : 
	 * ensemble des messages de contrôle pour ce champ.<br/>
	 * <br/>
	 *
	 * @param pRapportControleMap : 
	 * SortedMap<Integer,SortedMap<Integer,Set<IMessageControleImport>>> : 
	 * valeur à passer à rapportControleMap.<br/>
	 */
	@Override
	public final void setRapportControleMap(
			final SortedMap<Integer, SortedMap<Integer, Set<IMessageControleImport>>> 
			pRapportControleMap) {
		this.rapportControleMap = pRapportControleMap;
	} // Fin de setRapportControleMap(
	 // SortedMap<Integer,SortedMap<Integer,Set<IMessageControleImport>>> 
	 // pRapportControleMap).______________________________________________



	/**
	 * method getControleurImport() :<br/>
	 * Getter du Controleur de validité des champs.
	 * <br/>
	 *
	 * @return controleurImport : IControleurImport.<br/>
	 */
	@Override
	public final IControleurImport getControleurImport() {
		return this.controleurImport;
	} // Fin de getControleurImport()._____________________________________



	/**
	 * method setControleurImport(
	 * IControleurImport pControleurImport) :<br/>
	 * Setter du Controleur de validité des champs.
	 * <br/>
	 *
	 * @param pControleurImport : IControleurImport : 
	 * valeur à passer à controleurImport.<br/>
	 */
	@Override
	public final void setControleurImport(
			final IControleurImport pControleurImport) {
		this.controleurImport = pControleurImport;
	} // Fin de setControleurImport(
	 // IControleurImport pControleurImport).______________________________

		
	
} // FIN DE LA CLASSE AbstractImporteur.-------------------------------------
