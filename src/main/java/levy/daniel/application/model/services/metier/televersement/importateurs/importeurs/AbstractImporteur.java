package levy.daniel.application.model.services.metier.televersement.importateurs.importeurs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;
import levy.daniel.application.apptechnic.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierPasNormalException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideException;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.IImportateurDescription;

/**
 * class AbstractImporteur :<br/>
 * Classe abstraite modélisant tous les importeurs de fichiers 
 * (HIT, HISTO_F07, FEOR XML, DARWIN_CSV.csv...).<br/>
 * <br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Créer fichier sur disque, creer fichier sur disque,<br/>
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
	 * "Classe AbstractImporteur".<br/>
	 */
	public static final String CLASSE_ABSTRACTIMPORTEUR 
		= "Classe AbstractImporteur";
	
	/**
	 * "Méthode Importer(File pFile, Charset pCharset)".<br/>
	 */
	public static final String METHODE_IMPORTER 
		= "Méthode Importer(File pFile, Charset pCharset)";
	
	
	//*****************************************************************/
	//**************************** SEPARATEURS ************************/
	//*****************************************************************/
	/**
	 * Séparateur point virgule pour les CSV.<br/>
	 * ";"
	 */
	public static final String SEP_PV = ";";
    
	/**
	 * " - ".<br/>
	 */
	public static final String SEPARATEUR_MOINS_AERE = " - ";
		
	/**
	 * "_".<br/>
	 */
	public static final String UNDERSCORE = "_";
	
	/**
	 * "."
	 */
	public static final String POINT = ".";
	
	/**
	 * Tabulation "\t".<br/>
	 */
	public static final String TAB = "\t";
	
	
	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/

	/**
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");
	
	//*****************************************************************/
	//**************************** BOM_UTF-8 **************************/
	//*****************************************************************/
	/**
	 * '\uFEFF'<br/>
	 * BOM UTF-8 pour forcer Excel 2010 à lire en UTF-8.<br/>
	 */
	public static final char BOM_UTF_8 = '\uFEFF';
	
	/**
	 * Encapsulation permettant de stocker toutes les valeurs 
	 * décrivant un champ dans une description de fichier.<br/>
	 * Par exemple :<br/>
	 * <code>[ordreChamps, colonnes, longueur, intitule, nomenclature
	 * , champJava, typeJava, aNomenclature, aLexique
	 * , colonneDebut, colonneFin, longueurCalculee]</code> 
	 * pour une description de HISTO_F07.<br/>
	 * <code>[ordreChamps, intitule, nomenclature, champJava, typeJava
	 * , aNomenclature, aLexique]</code> 
	 * pour une description de DARWIN_CSV.<br/>
	 */
	protected IDescriptionChamp descriptionChamp;
	
	/**
	 * Importateur de description.<br/>
	 * Les ImportateurDescription sont chargés 
	 * de mettre à disposition de l'application les description 
	 * des fichiers (HIT, HISTO_F07, DARWIN_CSV, Feor XML, ...).<br/>
	 * Ces descriptions sont fournies par les ImportateursDescription 
	 * sous forme de 
	 * SortedMap&lt;Integer, IDescriptionChamp&gt;.<br/>
	 */
	protected IImportateurDescription importateurDescription;
	
	/**
	 * .<br/>
	 */
	protected SortedMap<Integer, IDescriptionChamp> specificationChampsMap;
	
	/**
	 * boolean qui stipule si les Importeur
	 * doivent rapporter ou pas 
	 * (rapport d'erreur lors de la lecture de fichiers).<br/>
	 * Ce rapport n'est null que si this.logImport vaut false. 
	 * ATTENTION : Tester si il est vide.<br/>
	 */
	protected boolean logImport;
		
	/**
	 * StringBuffer chargé de contenir le rapport de
	 * l'import du fichier.<br/>
	 * Ce rapport n'est null que si this.logImport vaut false. 
	 * Tester si il est vide.<br/>
	 */
	protected StringBuffer rapportImportStb;
	
	/**
	 * Le fichier (HIT, DARWIN_CSV, ...) à importer.<br/>
	 */
	protected File fichierAImporter;
		
	/**
	 * SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; 
	 * encapsulant le fichier (HIT, DARWIN_CSV, ...) importé avec :<br/>
	 * - Integer : le numéro d'ordre de la ligne dans le fichier.<br/>
	 * - Integer : le numéro d'un champ dans une ligne.<br/>
	 * - String : la valeur du champ sous forme de String UTF-8.<br/>
	 */
	protected SortedMap<Integer, SortedMap<Integer, String>> fichierImporteMap;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(AbstractImporteur.class);

	
	// *************************METHODES************************************/
	

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 * <ul>
	 * <li>va chercher dans ressources_externes/messagestechnique.properties 
	 * la valeur de <code><b>this.logImport</b></code>.</li>
	 * </ul>
	 * @throws Exception 
	 */
	public AbstractImporteur() throws Exception {
		
		super();
		
		/* va chercher dans ressources_externes/messagestechnique.properties 
		 * la valeur de this.logImport*/
		/* Détermination de la valeur du boolean qui
		 * stipule si il faut logger les erreurs d'import du fichier
		 * ou pas. */
		this.determinerSiLogErreurs();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, SortedMap<Integer, String>> 
														importer(
															final File pFile) 
					throws Exception {
		
		return this.importer(pFile, StandardCharsets.UTF_8);
		
	} // Fin de importer(
	// File pFile).________________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, SortedMap<Integer, String>> 
			importerFichierEnLatin9(
				final File pFile) 
							throws Exception {
		
		return this.importer(pFile, Charset.forName("ISO-8859-15"));
		
	} // Fin de importerFichierEnLatin9(
	 // File pFile)._______________________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, SortedMap<Integer, String>> 
			importerFichierEnAnsi(
				final File pFile) 
							throws Exception {
		
		return this.importer(pFile, Charset.forName("Windows-1252"));
		
	} // Fin de importerFichierEnAnsi(
	 // File pFile)._______________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, SortedMap<Integer, String>> 
			importerFichierEnUTf8(
				final File pFile) 
							throws Exception {
		
		return this.importer(pFile, StandardCharsets.UTF_8);
		
	} // Fin de importerFichierEnUtf8(
	 // File pFile)._______________________________________________________
	

	
	/**
	 * {@inheritDoc}
	 * @throws Exception 
	 */
	@Override
	public final SortedMap<Integer, SortedMap<Integer, String>> 
								importer(
									final File pFile
										, final Charset pCharset) 
												throws Exception {

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
			charset = StandardCharsets.UTF_8;
		}
		else {
			charset = pCharset;
		}

		int numeroLigne = 0;
		List<String> listeLignes = null;
		
		// LECTURE DES LIGNES.**********************************
		try {
			
			listeLignes = 
					Files.readAllLines(this.fichierAImporter.toPath(), charset);
			
		} catch (Exception e) {
			
			final String message 
				= CLASSE_ABSTRACTIMPORTEUR 
				+ SEPARATEUR_MOINS_AERE 
				+ METHODE_IMPORTER 
				+ SEPARATEUR_MOINS_AERE 
				+ "Il est impossible de lire le fichier : " 
				+ this.fichierAImporter.getAbsolutePath() 
				+ " avec le Charset : " 
				+ charset.displayName(LocaleManager.getLocaleApplication());
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message, e);
			}
			
			throw new ExceptionImport(message, e);
			
		}

		
		if (listeLignes == null) {
			
			final String message 
				= CLASSE_ABSTRACTIMPORTEUR 
				+ SEPARATEUR_MOINS_AERE 
				+ METHODE_IMPORTER 
				+ SEPARATEUR_MOINS_AERE 
				+ "Files.readAllLines(this.fichierAImporter.toPath(), charset) "
				+ "a retourné NULL";
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			throw new ExceptionImport(message);
		}

		
		// DECOMPOSITION DES LIGNES
		for (final String ligneLue : listeLignes) {
			
			/* saute une éventuelle ligne d'en-tête. */
			if (numeroLigne == 0 && estLigneEntete(ligneLue)) {
				continue;
			}

			/* Incrémentation du numéro de ligne. */
			numeroLigne++;
			
			/* saute une éventuelle ligne vide dans le fichier à importer. */
			if (ligneLue == null) {
				continue;
			}
			
			String ligneADecomposer = null;
			
			/* Retire un éventuel caractère BOM-UTF-8 
			 * en début de chaque ligne. */
			if (ligneLue.charAt(0) == BOM_UTF_8) {
				ligneADecomposer = StringUtils.substring(ligneLue, 1);
			}
			else {
				ligneADecomposer = ligneLue;
			}
			

			/* DECOMPOSITION DE CHAQUE LIGNE. *******/
			final SortedMap<Integer, String> ligneMap = this
					.decomposerLigne(ligneADecomposer);

			
			// AJOUT DE LA LIGNE DECOMPOSEE DANS LA MAP RESULTAT._____
			if (ligneMap != null) {
				this.fichierImporteMap.put(numeroLigne, ligneMap);
			}

		} // FIN DECOMPOSITION DES LIGNES.******************************
		
		return this.fichierImporteMap;

	} // Fin de importer(...)._____________________________________________
	

	
	/**
	 * détermine si pLigne est une ligne d'en-tête d'un fichier de données.<br/>
	 * retourne true si pLigne est une ligne d'en-tête<br/>
	 *
	 * @param pLigne : String : ligne d'un fichier de données.
	 * 
	 * @return : boolean : true si pLigne est une ligne d'en-tête.<br/>
	 */
	protected abstract boolean estLigneEntete(String pLigne);
	
	
	
	/**
	 * Décompose chaque ligne lue dans le fichier 
	 * en fonction de la description et retourne une Map triée 
	 * SortedMap&lt;Integer, String&gt; avec :
	 * <ul>
	 * <li>Integer : l'ordre du champ.</li>
	 * <li>String : la valeur du champ sous forme de String 
	 * encodée dans le Charset par défaut de la plateforme (UTF-8).</li>
	 * </ul>
	 * - Retire un éventuel caractère BOM-UTF-8 
	 * en début de chaque ligne.<br/>
	 * - retourne null si pString est blank.<br/>
	 * - Implémenté au niveau du dessous dans AbstractImporteurAscii 
	 * ou AbstractImporteurNonAscii.<br/>
	 * <br/>
	 *
	 * @param pString
	 * @return : SortedMap&lt;Integer,String&gt;.<br/>
	 */
	protected abstract SortedMap<Integer, String> decomposerLigne(String pString);
	
	
	
	/**
	 * Traitement des fichiers null pour la méthode importer(pFile).<br/>
	 * LOG.fatal, rapporte dans this.rapportImportStb 
	 * et jette une FichierNullException si pFile est null.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void traiterFichierNull(
								final File pFile) 
											throws Exception {
		
		this.traiterFichierNull(pFile, METHODE_IMPORTER);
		
	} // Fin de traiterFichierNull(
	 // File pFile)._______________________________________________________
	
	
	
	/**
	 * Traitement des fichiers vide pour la méthode importer(pFile).<br/>
	 * LOG.fatal, rapporte dans this.rapportImportStb 
	 * et jette une FichierVideException si pFile est vide.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void traiterFichierVide(
								final File pFile) 
									throws Exception {
		
		this.traiterFichierVide(pFile, METHODE_IMPORTER);

	} // Fin de traiterFichierVide(
	 // File pFile)._______________________________________________________
	
	
	
	/**
	 * Traitement des fichiers inexistants 
	 * pour la méthode importer(pFile).<br/>
	 * LOG.fatal, rapporte dans this.rapportImportStb 
	 * et jette une FichierInexistantException 
	 * si pFile est inexistant.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void traiterFichierInexistant(
								final File pFile) 
									throws Exception {
		
		this.traiterFichierInexistant(pFile, METHODE_IMPORTER);

	} // Fin de traiterFichierInexistant(
	 // File pFile)._______________________________________________________
	
	

	/**
	 * Traitement des répertoires (fichier non 'normaux') 
	 * pour la méthode importer(pFile).<br/>
	 * LOG.fatal, rapporte dans this.rapportImportStb 
	 * et jette une FichierPasNormalException 
	 * si pFile est un répertoire.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void traiterFichierPasNormal(
							final File pFile) 
								throws Exception {
		
		this.traiterFichierPasNormal(pFile, METHODE_IMPORTER);

	} // Fin de traiterFichierPasNormal(
	 // File pFile)._______________________________________________________
	

	
	/**
	 * LOG.fatal, rapporte dans this.rapportImportStb 
	 * et jette une FichierNullException si pFile est null.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void traiterFichierNull(
								final File pFile
									, final String pMethode) 
											throws Exception {
		
		if (pFile == null) {
			
			/* Constitution du message. */
			final String message 
			= this.recupererNomClasse()
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ ConfigurationApplicationManager.getBundleMessagesTechnique()
			.getString(this.recupererCleImporterFileNull());
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logImport) {
				this.rapportImportStb.append(message);
				this.rapportImportStb.append(NEWLINE);
			}
			
			/* Exception circonstanciée. */
			throw new FichierNullException(message);
			
		} // Fin de pFile null._____________________________
		
	} // Fin de traiterFichierNull(
	 // File pFile
	// , String pMethode)._________________________________________________
	
	
	
	/**
	 * LOG.fatal, rapporte dans this.rapportImportStb 
	 * et jette une FichierVideException si pFile est vide.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void traiterFichierVide(
				final File pFile
					, final String pMethode) 
								throws Exception {
		
		if (pFile.length() == 0) {
			
			/* Constitution du message. */
			final String message 
			= this.recupererNomClasse()
			+ SEPARATEUR_MOINS_AERE
			+ pMethode 
			+ SEPARATEUR_MOINS_AERE
			+ ConfigurationApplicationManager.getBundleMessagesTechnique()
			.getString(this.recupererCleImporterFileVide()) 
			+ pFile.getAbsolutePath();
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logImport) {
				this.rapportImportStb.append(message);
				this.rapportImportStb.append(NEWLINE);
			}
			
			/* Exception circonstanciée. */
			throw new FichierVideException(message);
			
		} // Fin de pFile vide.______________________________

	} // Fin de traiterFichierVide(
	 // File pFile
	// , String pMethode)._________________________________________________
	
	
	
	/**
	 * LOG.fatal, rapporte dans this.rapportImportStb 
	 * et jette une FichierInexistantException 
	 * si pFile est inexistant.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void traiterFichierInexistant(
			final File pFile
				, final String pMethode) 
						throws Exception {
		
		if (!pFile.exists()) {
			
			/* Constitution du message. */
			final String message 
			= this.recupererNomClasse()
			+ SEPARATEUR_MOINS_AERE
			+ pMethode 
			+ SEPARATEUR_MOINS_AERE
			+ ConfigurationApplicationManager.getBundleMessagesTechnique()
			.getString(this.recupererCleImporterFileInexistant()) 
			+ pFile.getAbsolutePath();
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logImport) {
				this.rapportImportStb.append(message);
				this.rapportImportStb.append(NEWLINE);
			}
						
			/* Exception circonstanciée. */
			throw new FichierInexistantException(message);
			
		} // Fin de fichier inexistant._______________________

	} // Fin de traiterFichierInexistant(
	 // File pFile
	// , String pMethode)._________________________________________________
	
	

	/**
	 * LOG.fatal, rapporte dans this.rapportImportStb 
	 * et jette une FichierPasNormalException 
	 * si pFile est un répertoire.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void traiterFichierPasNormal(
			final File pFile
				, final String pMethode) 
						throws Exception {
		
		if (!pFile.isFile()) {
			
			/* Constitution du message. */
			final String message 
			= this.recupererNomClasse()
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ ConfigurationApplicationManager.getBundleMessagesTechnique()
			.getString(this.recupererCleImporterFilePasNormal()) 
			+ pFile.getAbsolutePath();
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logImport) {
				this.rapportImportStb.append(message);
				this.rapportImportStb.append(NEWLINE);
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
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(champsMap.toString());
			stb.append(NEWLINE);
		}
		
		return stb.toString();
		
	} // Fin de afficherFichierImporteMap()._______________________________
	


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compterNombreLignes(
					final File pFile) 
						throws Exception {
		
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
	 * <ul>
	 * <li>Va chercher dans ressource_externes/messages_techniques.properties 
	 * si il faut créer des rapports d'erreur d'import des fichiers.</li>
	 * <li>Instancie le cas échéant le rapport d'erreur d'import 
	 * <code><b>this.rapportImportStb</b></code>.</li>
	 * </ul>
	 * @throws Exception 
	 */
	protected final void determinerSiLogErreurs() throws Exception {
		
		final String cleLogImport = this.recupererCleLogErreur();

		final String logImportString 
		= ConfigurationApplicationManager.getBundleMessagesTechnique()
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
	 * Génère automatiquement le fichier de sortie 
	 * (VIDE ET NON ECRIT SUR DISQUE)
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
	public final String fournirEnteteparColonne(
			final int pI) 
						throws Exception {
		
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
			stb.append(SEP_PV);
			
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
			stb.append(SEP_PV);
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
			stb.append(NEWLINE);
		}
		
		final int nombreLignes = this.fichierImporteMap.size();
		
		for (int i = 1; i <= nombreLignes; i++) {
			
			stb.append(this.fournirLigneValeursCsv(i));
			
			if (i < nombreLignes) {
				stb.append(NEWLINE);
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
		
		return this.genererCsvFile(true, null, Charset.forName("ISO-8859-15"));
		
	} // Fin de genererCsvFileLatin9().____________________________________


	
	/**
	 * Génère un fichier csv encodé en ANSI avec séparateur ';' contenant 
	 * le fichier importé this.fichierAImporter.<br/>
	 * Ce fichier csv généré est accessible à pFile.<br/>
	 * contient la ligne d'en-tête.<br/>
	 * Génère automatiquement le fichier de sortie dans le 
	 * même répertoire que this.fichierAImporter 
	 * avec l'extension _Windows-1252.csv.<br/>
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
	public final File genererCsvFileAnsi() throws IOException {
		
		return this.genererCsvFile(true, null, Charset.forName("Windows-1252"));
		
	} // Fin de genererCsvFileAnsi().______________________________________

	
	
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
		
		return this.genererCsvFile(true, null, StandardCharsets.UTF_8);
		
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
			= StringUtils.substringBeforeLast(nom, POINT);
				
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
	 * Génère automatiquement le fichier de sortie 
	 * dans le même répertoire que <code><b>this.fichierAImporter</b></code> 
	 * avec l'extension <b>_charset.csv</b>.<br/>
	 * <ul>
	 * <li>Choisit automatiquement le Charset UTF-8 si pCharset == null.</li>
	 * </ul>
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
		
		Charset charset = null;
		
		/* Choisit automatiquement le Charset UTF-8 si pCharset == null. */
		if (pCharset == null) {
			charset = StandardCharsets.UTF_8;
		}
		else {
			charset = pCharset;
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
			+ charset.displayName() 
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
			SortedMap<Integer, SortedMap<Integer, String>> pFichierImporteMap
				, int pI);
	
	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File reconstituerFichierLatin9() throws IOException {
		
		return this.reconstituerFichier(
				this.fichierImporteMap
					, Charset.forName("ISO-8859-15"), false, null);
		
	} // Fin de reconstituerFichierLatin9()._______________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File reconstituerFichierAnsi() throws IOException {
		
		return this.reconstituerFichier(
				this.fichierImporteMap
					, Charset.forName("Windows-1252"), false, null);
		
	} // Fin de reconstituerFichierAnsi()._________________________________
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File reconstituerFichierUtf8() throws IOException {
		
		return this.reconstituerFichier(
				this.fichierImporteMap
					, StandardCharsets.UTF_8, false, null);
		
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
			charset = StandardCharsets.UTF_8;
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
			
		}
		else {
			
			fileGenere = pFile;
		}
		
		
		/* retourne null si fileGenere est null. */
		if (fileGenere == null) {
			return null;
		}
		
		/* crée un fichier vide sur disque et son arborescence 
		 * si il n'existe pas. */
		if (!fileGenere.exists()) {
			this.creerFichierVideEtArborescenceSurDisque(fileGenere);
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
	 * Génère automatiquement le fichier de sortie 
	 * dans le même répertoire que this.file 
	 * avec l'extension _charset.extension.<br/>
	 * <ul>
	 * <li>Choisit automatiquement le Charset UTF-8 si pCharset == null.</li>
	 * </ul>
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
				
		Charset charset = null;
		
		/* Choisit automatiquement le Charset UTF-8 si pCharset == null. */
		if (pCharset == null) {
			charset = StandardCharsets.UTF_8;
		}
		else {
			charset = pCharset;
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
			= StringUtils.substringBeforeLast(nom, POINT);
		
		final String extension 
		= StringUtils.substringAfterLast(nom, POINT);
		
		String cheminGenere = null;
		
		/* Création du nom complet du fichier généré. */
		if (!StringUtils.isBlank(extension)) {
			
			cheminGenere 
			= pathSansNom 
			+ nomSansExtension
			+ UNDERSCORE 
			+ charset.displayName()
			+ POINT
			+ extension;
			
		} else {
			
			cheminGenere 
			= pathSansNom 
			+ nomSansExtension
			+ UNDERSCORE 
			+ charset.displayName();
			
		}
				
		/* Création du fichier généré. */
		final File fileGenere = new File(cheminGenere);
		
		return fileGenere;
		
	} // Fin de genererAutomatiquementFileReconstitue(
	// Charset pCharset).__________________________________________________


	
	/**
	 * <b>Crée sur disque le File pFile <i>vide</i></b> 
	 * si il n'existe pas déjà.<br/>
	 * <ul>
	 * <li>retourne le fichier vide créé.</li>
	 * <li>crée sur disque l'<b>arborescence au dessus de pFile</b> 
	 * si elle n'existe pas.</li>
	 * <li>crée sur disque le fichier vide pFile 
	 * si il n'existe pas.</li>
	 * </ul>
	 * - retourne null si pFile == null.<br/>
	 * - retourne null si pFile existe.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier inexistant sur disque à créer.
	 * 
	 * @return File : le fichier VIDE créé sur disque.<br/>
	 * 
	 * @throws IOException
	 */
	private File creerFichierVideEtArborescenceSurDisque(
							final File pFile) throws IOException {
		
		/* retourne null si pFile == null. */
		if (pFile == null) {
			return null;
		}
		
		/* retourne null si pFile existe. */
		if (pFile.exists()) {
			return null;
		}
		
		final Path pFilePath = pFile.toPath();
		final Path pFileParentPath = pFilePath.getParent();
		
		/* crée l'arborescence au dessus de pFile si elle n'existe pas. */
		if (pFileParentPath != null) {
			
			if (!pFileParentPath.toFile().exists()) {
				Files.createDirectories(pFileParentPath);
			}
			
			/* crée le fichier VIDE pFile si il n'existe pas. */
			Files.createFile(pFilePath);

		}
		
		return pFile;
		
	} // Fin de creerFichierVideEtArborescenceSurDisque(...).______________
	

	
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

		
	
} // FIN DE LA CLASSE AbstractImporteur.-------------------------------------
