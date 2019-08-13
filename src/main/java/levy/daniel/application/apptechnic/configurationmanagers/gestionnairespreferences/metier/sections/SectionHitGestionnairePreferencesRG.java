package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.sections;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesbundles.BundleConfigurationProjetManager;
import levy.daniel.application.model.metier.sections.impl.SectionHit;

/**
 * CLASSE SectionHitGestionnairePreferencesRG :<br/>
 * Classe Utilitaire chargée de gérer les 
 * <b>préférences relatives aux REGLES DE GESTION (RG) applicables 
 * à un {@link SectionHit}</b>.<br/>
 * <ul>
 * <li>gère comme des préférences les <b>booleens 
 * qui activent ou non les contrôles des RG</b>.</li>
 * </ul>
 * <br/>
 * 
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * répertoire du projet, System.getProperty("user.dir"),<br/>
 * Properties, préférences, Preferences, <br/>
 * Template, template, lire dans fichier,<br/>
 * enregistrer des préférences dans un Properties, fichier properties, <br/>
 * commentaire dans un fichier properties, écrire commentaire, <br/>
 * lire dans un fichier properties, écrire dans un fichier properties, <br/>
 * créer une String à partir d'une liste de lignes, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 13 août 2019
 *
 */
public final class SectionHitGestionnairePreferencesRG {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe SectionHitGestionnairePreferencesRG".
	 */
	public static final String CLASSE_SECTIONHIT_GESTIONNAIRE_PREFS_RG 
		= "Classe SectionHitGestionnairePreferencesRG";

	//*****************************************************************/
	//**************************** SEPARATEURS ************************/
	//*****************************************************************/

	/**
	 * System.getProperty("file.separator")
	 */
	public static final String SEPARATEUR_FICHIER 
		= System.getProperty("file.separator");
		
	/**
	 * " - ".<br/>
	 */
	public static final String SEPARATEUR_MOINS_AERE = " - ";

	
	//*****************************************************************/
	//**************************** CHARSETS ***************************/
	//*****************************************************************/
	/**
	 * Charset.forName("UTF-8").<br/>
	 * Eight-bit Unicode (or UCS) Transformation Format.<br/> 
	 */
	public static final Charset CHARSET_UTF8 
		= Charset.forName("UTF-8");
	
	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/	
	/**
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE 
		= System.getProperty("line.separator");

	/**
	 * Saut de ligne JAVA
	 * '\n'.<br/>
	 */
	public static final char SAUT_LIGNE_JAVA = '\n';

	/**
	 * "&lt;br/&gt;".<br/>
	 */
	public static final String SAUT_LIGNE_HTML = "<br/>";
	
	/**
	 * "méthode lireStringsDansFile(File pFile, Charset pCharset)".<br/>
	 */
	public static final String METHODE_LIRE_STRINGS_DANS_FILE 
		= "méthode lireStringsDansFile(File pFile, Charset pCharset)";
		
	/**
	 * '='.<br/>
	 */
	public static final char EGAL = '=';
	
	// RGs ***************************************************
	/* 1 - numDepartement. */
	/**
	 * clé de validerRGSectionHitNumDepartement dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numdepartement"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT 
		= "valider.SectionHit.numdepartement";
	
	/**
	 * validerRGSectionHitNumDepartement par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumDepartement 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitNumDepartementRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numdepartement.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01 
		= "valider.SectionHit.numdepartement.renseigne";
	
	/**
	 * validerRGSectionHitNumDepartementRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumDepartementRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitNumDepartementRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numdepartement.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT_REGEX_02 
		= "valider.SectionHit.numdepartement.regex";
	
	/**
	 * validerRGSectionHitNumDepartementRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumDepartementRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR 
		= "true";
		
	/**
	* java.util.Properties encapsulant les préférences.<br/>
	*/
	private static Properties preferences = new Properties();
	
	/**
	* Path absolu vers le fichier properties contenant les preferences
	* <code><b>ressources_externes/preferences/metier/sections/
	* SectionHit_RG.properties</b></code>.<br/>
	*/
	private static Path pathAbsoluPreferencesProperties;
	
	/**
	* Chemin relatif (par rapport à ressources_externes) 
	* du fichier properties contenant les preferences
	* <code>SectionHit_RG.properties</code>.<br/>
	* "preferences/metier/sections/SectionHit_RG.properties"
	*/
	private static final String CHEMIN_RELATIF_PREFERENCES_PROPERTIES_STRING 
	= "preferences/metier/sections/SectionHit_RG.properties";
	
	/**
	* Modélisation Java du fichier properties contenant les preferences
	* <code>SectionHit_RG.properties</code>.<br/>
	*/
	private static File filePreferencesProperties;
	
	/**
	* commentaire à ajouter en haut du fichier properties 
	* contenant les preferences 
	* <code>SectionHit_RG.properties</code>.<br/>
	*/
	private static String commentaire;
	
	/**
	* Chemin relatif (par rapport à src/main/resources) 
	* du template contenant le commentataire à ajouter 
	* en haut du fichier properties contenant les preferences 
	* <code>SectionHit_RG.properties</code>.<br/>
	* "commentaires_properties/metier/sections/SectionHit_RG_properties_commentaires.txt"
	*/
	private static final String CHEMIN_RELATIF_TEMPLATE_COMMENTAIRE 
	= "commentaires_properties/metier/sections/SectionHit_RG_properties_commentaires.txt";

	// BOOLEANS *************************************
	/* 1 - numDepartement. */
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur le <i>numDepartement</i> de la SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numDepartement</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitNumDepartement;
	
	/**
	 * Boolean activant la RG-SectionHit-NumDepartement-01 : 
	 * ""le numéro de département doit obligatoirement être renseigné"".<br/>
	 */
	private static Boolean validerRGSectionHitNumDepartementRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-NumDepartement-02 : 
	 * "le numéro de département doit comporter exactement 3 chiffres 
	 * dans les colonnes [1-3] du HIT 
	 * ('030' pour 03, '300' pour 30, '972' pour 972)".<br/>
	 */
	private static Boolean validerRGSectionHitNumDepartementRegex02;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(SectionHitGestionnairePreferencesRG.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation<br/>
	 */
	private SectionHitGestionnairePreferencesRG() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * <b>sauvegarde sur disque un fichier 
	 * SectionHit_RG.properties initial</b> alimenté par des 
	 * propriétés [clé-valeur] <b>écrites en dur</b> 
	 * dans la présente classe.<br/>
	 * <ul>
	 * <li>remplit le java.util.Properties <code>preferences</code> 
	 * avec des [clé-valeur] stockées en dur dans la classe.</li>
	 * <li>crée le fichier properties contenant les préférences 
	 * <code>filePreferencesProperties</code> VIDE 
	 * sur le disque si il n'existe pas.</li>
	 * <li>remplit le fichier <code>filePreferencesProperties</code> 
	 * (SectionHit_RG.properties) 
	 * avec le contenu de <code>preferences</code> 
	 * ([clé-valeur] stockées en dur dans la classe).</li>
	 * <li>Ecrit en UTF8 le Properties <code>preferences</code> dans 
	 * le File <code>filePreferencesProperties</code> 
	 * modélisant le fichier SectionHit_RG.properties en positionnant 
	 * le <code>commentaire</code> au dessus.</li>
	 * <li>Utilise <code>preferences.store(writer, commentaire);</code> 
	 * avec un try-with-resource.</li>
	 * <li>ré-écrit (écrase) tout le fichier à chaque appel.</li>
	 * <li>trace EX_TEC_INITIALISATION_07.</li>
	 * </ul>
	 * <p>
	 * <img src="../../../../../../../../../../../javadoc/images/apptechnic/preferences/methode_creerFichierPropertiesInitial_activites.png" 
	 * alt="diagramme d'activités de la méthode creerFichierPropertiesInitial()" />
	 * </p>
	 * 
	 * @throws Exception 
	 */
	private static void creerFichierPropertiesInitial() 
											throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* remplit le Properties Java <code>preferences</code> 
			 * avec des [clé-valeur] stockées en dur dans la classe. */
			ajouterPropertiesEnDur();
			
			/* crée le fichier properties contenant les préférences 
			 * filePreferencesProperties VIDE sur le disque 
			 * si il n'existe pas. */
			creerFichierPreferencesPropertiesVide();
			
			/* remplit le fichier filePreferencesProperties avec 
			 * preferences. */
			enregistrerPreferencesDansFichierProperties();
						
		} // Fin du bloc synchronized.__________________
		
	} // Fin de creerFichierPropertiesInitial().___________________________

	

	/**
	 * <b>Ajoute des propriétés initiales stockées en dur</b> 
	 * dans la classe au java.util.Properties <b>preferences</b>.<br/>
	 * <ul>
	 * numdepartement
	 * <li>ajoute le validerRGSectionHitNumDepartement 
	 * par défaut stocké en dur.</li>
	 * <li>ajoute le validerRGSectionHitNumDepartementRenseigne01 
	 * par défaut stockée en dur.</li>
	 * <li>ajoute le validerRGSectionHitNumDepartementRegex02 
	 * par défaut stockée en dur.</li>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private static void ajouterPropertiesEnDur() {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* NUMDEPARTEMENT. */
			/* ajoute le validerRGSectionHitNumDepartement 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT
						, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumDepartementRenseigne01 
			 * par défaut stockée en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumDepartementRegex02 
			 * par défaut stockée en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT_REGEX_02
						, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR);
						
		} // Fin du bloc synchronized.__________________
		
	} // Fin de ajouterProperties()._______________________________________
	

	
	/**
	 * <b>Instancie tous les attributs</b> relatifs 
	 * au fichier de preferences <b>si ils sont null</b>.<br/>
	 * <ul>
	 * <li>instancie <code>pathAbsoluPreferencesProperties</code> 
	 * si nécessaire.</li>
	 * <li>instancie <code>filePreferencesProperties</code> 
	 * si nécessaire.</li>
	 * <li>instancie <code>commentaire</code> si nécessaire.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void instancierAttributsStatiques() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* instancie pathAbsoluPreferencesProperties si nécessaire. */
			instancierPathAbsoluPreferencesProperties();
			
			/* instancie filePreferencesProperties si nécessaire. */
			instancierFilePreferencesProperties();
			
			/* instancie commentaire si nécesaire. */
			instancierCommentaire();
						
		} // Fin du bloc synchronized.__________________
		
	} // Fin de instancierAttributsFichierProperties().____________________
	

	
	/**
	 * instancie <code>pathAbsoluPreferencesProperties</code>.<br/>
	 * <ul>
	 * <li>ne fait rien si pathAbsoluPreferencesProperties 
	 * n'est pas null.</li>
	 * <li>obtient le path des ressources externes auprès 
	 * du ConfigurationApplicationManager.</li>
	 * <li>calcule le path du SectionHit_RG.properties 
	 * via un resolve par rapport au path des ressources externes.</li>
	 * <li>alimente <code>pathAbsoluPreferencesProperties</code></li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void instancierPathAbsoluPreferencesProperties() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* ne fait rien si pathAbsoluPreferencesProperties 
			 * n'est pas null. */
			if (pathAbsoluPreferencesProperties == null) {
				
				/* obtient le path du properties dans les 
				 * ressources externes auprès du 
				 * ConfigurationApplicationManager. */
				final Path pathRessourcesExternes 
				= Paths.get(ConfigurationApplicationManager
						.getPathRessourcesExternes());
				
				/* calcule le path du SectionHit_RG.properties 
				 * via un resolve par rapport au path 
				 * des ressources externes. */
				pathAbsoluPreferencesProperties 
				= pathRessourcesExternes
					.resolve(CHEMIN_RELATIF_PREFERENCES_PROPERTIES_STRING)
						.toAbsolutePath()
							.normalize();
			}

		} // Fin du bloc synchronized.__________________
			
	} // Fin de instancierPathAbsoluPreferencesProperties()._______________
	

	
	/**
	 * instancie <code>filePreferencesProperties</code>.<br/>
	 * <ul>
	 * <li>ne fait rien si filePreferencesProperties n'est pas null.</li>
	 * <li>instancie <code>pathAbsoluPreferencesProperties</code> 
	 * si nécessaire.</li>
	 * <li>alimente <code>filePreferencesProperties</code>.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void instancierFilePreferencesProperties() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* ne fait rien si filePreferencesProperties 
			 * n'est pas null. */
			if (filePreferencesProperties == null) {
				
				/* instancie pathAbsoluPreferencesProperties 
				 * si nécessaire. */
				instancierPathAbsoluPreferencesProperties();
				
				/* alimente filePreferencesProperties. */
				filePreferencesProperties 
					= pathAbsoluPreferencesProperties.toFile();
				
			}
						
		} // Fin du bloc synchronized.__________________		
		
	} // Fin de instancierFilePreferencesProperties()._____________________
	

	
	/**
	 * instancie <code>commentaire</code>.<br/>
	 * <ul>
	 * <li>ne fait rien si commentaire n'est pas null.</li>
	 * <li>lit dans un template le commentaire à ajouter 
	 * au début du fichier properties contenant les preferences.</li>
	 * <li>alimente <code>commentaire</code>.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void instancierCommentaire() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* ne fait rien si commentaire n'est pas null. */
			if (commentaire == null) {
				
				/* lit dans un template le commentaire à ajouter au début du 
				 * SectionHit_RG.properties et le stocke 
				 * dans commentaire.*/
				commentaire 
					= lireTemplateString(CHEMIN_RELATIF_TEMPLATE_COMMENTAIRE);
			}
		} // Fin du bloc synchronized.__________________	
		
	} // Fin de instancierCommentaire().___________________________________
	
	
	
	/**
	 * Crée sur disque dur l'arborescence des répertoires 
	 * parent de pFile si elle n'existe pas déjà.<br/>
	 * <ul>
	 * <li><code>Files.createDirectories(pathParent);</code></li>
	 * </ul>
	 * - ne fait rien si pFile == null.<br/>
	 * - ne fait rien si pFile est une racine (pas de parent).<br/>
	 * </br/>
	 *
	 * @param pFile : File : 
	 * fichier dont on veut créer l'arborescence sur disque dur.<br/>
	 * 
	 * @throws IOException
	 */
	private static void creerRepertoiresArbo(
			final File pFile) throws IOException {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* ne fait rien si pFile == null. */
			if (pFile == null) {
				return;
			}
			
			final Path pathFile = pFile.toPath();
			final Path pathParent = pathFile.getParent();
			
			/* ne fait rien si pFile est une racine (pas de parent). */
			if (pathParent != null) {
				Files.createDirectories(pathParent);
			}

		} // Fin du bloc synchronized.__________________
						
	} // Fin de creerRepertoiresArbo(...)._________________________________
	

	
	/**
	 * crée sur disque le fichier properties contenant les preferences
	 * <code>SectionHit_RG.properties</code> <b>VIDE</b> 
	 * <i>si il n'existe pas déjà</i>.<br/>
	 * <ul>
	 * <li>crée l'arboresence parente du fichier properties 
	 * si elle n'existe pas déjà.</li>
	 * <li>crée le fichier properties VIDE 
	 * (<code>Files.createFile(Path ...)</code>).</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void creerFichierPreferencesPropertiesVide() 
													throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* Crée sur le disque dur l'arborescence et le fichier 
			 * filePreferencesProperties VIDE si nécessaire.*/
			if (!filePreferencesProperties.exists()) {
				
				/* crée l'arboresence parente du fichier properties 
				 * si elle n'existe pas déjà. */
				creerRepertoiresArbo(filePreferencesProperties);
				
				/* crée le fichier properties VIDE. */
				Files.createFile(pathAbsoluPreferencesProperties);
			}
			
		} // Fin du bloc synchronized.__________________
				
	} // Fin de creerFichierPreferencesPropertiesVide().___________________
	
	
	
	/**
	 * <b>lit en UTF-8 le fichier properties contenant les preferences 
	 * <code>ressources_externes/preferences/metier/utilisateurs/
	 * SectionHit_RG.properties</b></code> 
	 * et alimente le <i>java.util.Properties</i> <b>preferences</b>.<br/>
	 * <ul>
	 * <li>décode le fichier .properties en UTF8 et le charge 
	 * dans le java.util.Properties <code>preferences</code>.</li>
	 * <li><code>preferences.load(BufferedReader);</code></li>
	 * <li>trace EX_TEC_PARAMETRAGE_02.</li>
	 * </ul>
	 * @throws Exception 
	 */
	private static void lireFichierPreferencesProperties() 
												throws Exception {

		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* try-with-resource qui se charge du close(). */
			try (Reader reader = Files.newBufferedReader(
					pathAbsoluPreferencesProperties, CHARSET_UTF8)) {
				
				/* décode le fichier .properties en UTF8 
				 * et le charge dans le Properties preferences. */
				preferences.load(reader);
		
			}

		} // Fin du bloc synchronized.__________________
				
	} // Fin de lireFichierPreferences().__________________________________


	
	/**
	 * <b>Enregistre en UTF8</b> le <i>java.util.Properties</i> 
	 * preferences dans le <i>fichier</i> properties 
	 * contenant les preferences
	 * <code><b>ressources_externes/preferences/metier/utilisateurs/
	 * SectionHit_RG.properties</b></code>.<br/>
	 * <ul>
	 * <li>enregistre le <i>java.util.Properties</i> <b>preferences</b> 
	 * sur disque dur dans le <i>fichier</i> 
	 * .properties correspondant.</li>
	 * <li>ajoute le commentaire au début de preferences.properties.</li>
	 * <li>Prise en compte (stockage) 
	 * d'une modification d'une Property.</li>
	 * <li><code>preferences.store(writer, null);</code></li>
	 * <li>trace EX_FONCT_MEMORISATION_05.</li>
	 * <li>trace EX_TEC_MEMORISATION_06.</li>
	 * </ul>
	 * - ne fait rien si le fichier properties n'existe pas.<br/>
	 * - ne fait rien si preferences est vide.<br/>
	 * <br/>
	 * 
	 * @throws Exception 
	 */
	private static void enregistrerPreferencesDansFichierProperties() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* ne fait rien si le fichier properties n'existe pas. */
			if (filePreferencesProperties == null 
					|| !filePreferencesProperties.exists()) {
				return;
			}
			
			/* ne fait rien si preferences est vide. */
			if (preferences.isEmpty()) {
				return;
			}
			
			/* try-with-resource qui se charge du close(). */
			try (Writer writer = Files.newBufferedWriter(
					pathAbsoluPreferencesProperties, CHARSET_UTF8)) {
				
				/* enregistre le Properties preferences sur disque dur 
				 * dans le fichier .properties correspondant. */
				preferences.store(writer, commentaire);
				
			}

		} // Fin du bloc synchronized.__________________
		
	} // Fin de enregistrerFichierPreferences().___________________________
	

	
	/**
	 * <b>Lit un template situé à 
	 * <code>cheminAbsoluMainResources/pCheminRelatifTemplate</code> 
	 * et retourne une String unique 
	 * incorporant les sauts de lignes</b>.
	 * <ul>
	 * <li>lit le fichier template avec le Charset UTF8.</li>
	 * <li>utilise la méthode lireStringsDansFile(
	 * templateFile, CHARSET_UTF8).</li>
	 * <li><b>Ne fait aucune substitution de variables</b>. 
	 * Lit le template et le retourne sous forme 
	 * de String.</li>
	 * <li>incorpore dans la String résultat les 
	 * sauts de ligne de la plateforme.</li>
	 * </ul>
	 * - retourne null si pCheminRelatifTemplate est blank.<br/>
	 * - retourne null si le fichier template n'existe pas.<br/>
	 * <br/>
	 *
	 * @param pCheminRelatifTemplate : String : 
	 * chemin relatif du template à lire par rapport à 
	 * cheminAbsoluMainResources (src/main/resources).<br/>
	 * 
	 * @return String : 
	 * template sous forme de String unique 
	 * incorporant les sauts de lignes.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String lireTemplateString(
			final String pCheminRelatifTemplate) 
									throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* retourne null si pCheminRelatifTemplate est blank. */
			if (StringUtils.isBlank(pCheminRelatifTemplate)) {
				return null;
			}
			
			final List<String> templateListe 
				= lireTemplate(pCheminRelatifTemplate);
			
			final String resultat 
				= creerStringAPartirDeListe(templateListe);
			
			return resultat;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de lireTemplateString(...).___________________________________

	
	
	/**
	 * <b>Lit un template situé à 
	 * <code>cheminAbsoluMainResources/pCheminRelatifTemplate</code> 
	 * et retourne une liste de lignes</b>.
	 * <ul>
	 * <li>lit le fichier template avec le Charset UTF8.</li>
	 * <li>utilise la méthode lireStringsDansFile(
	 * templateFile, CHARSET_UTF8).</li>
	 * <li><b>Ne fait aucune substitution de variables</b>. 
	 * Lit le template et le retourne sous forme 
	 * de List&lt;String&gt;.</li>
	 * </ul>
	 * - retourne null si pCheminRelatifTemplate est blank.<br/>
	 * - retourne null si le fichier template n'existe pas.<br/>
	 * <br/>
	 *
	 * @param pCheminRelatifTemplate : String : 
	 * chemin relatif du template à lire par rapport à 
	 * cheminAbsoluMainResources (src/main/resources).<br/>
	 * 
	 * @return List&lt;String&gt; : 
	 * template sous forme de liste de lignes.<br/>
	 * 
	 * @throws Exception 
	 */
	private static List<String> lireTemplate(
			final String pCheminRelatifTemplate) 
									throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* retourne null si pCheminRelatifTemplate est blank. */
			if (StringUtils.isBlank(pCheminRelatifTemplate)) {
				return null;
			}
			
			final String cheminAbsoluTemplate 
				= BundleConfigurationProjetManager.getRacineMainResources() 
					+ SEPARATEUR_FICHIER 
						+ pCheminRelatifTemplate;
			
			final File templateFile = new File(cheminAbsoluTemplate);
			
			/* retourne null si le fichier template n'existe pas. */
			if (!templateFile.exists()) {
				return null;
			}
			
			/* utilise la méthode 
			 * lireStringsDansFile(templateFile, CHARSET_UTF8). */
			final List<String> templateListe 
				= lireStringsDansFile(templateFile, CHARSET_UTF8);
			
			return templateListe;

		} // Fin du bloc synchronized.__________________
		
	} // Fin de lireTemplate(...)._________________________________________
	
	
	
	/**
	 * <b>Lit le contenu d'un fichier texte avec pCharset 
	 * et retourne une Liste de lignes</b>. 
	 * <ul>
	 * <li><b>Lit le contenu</b> d'un fichier texte 
	 * (fichier simple contenant du texte) pFile.</li>
	 * <li>Décode le contenu d'un fichier texte 
	 * (fichier simple contenant du texte) pFile 
	 * avec le Charset pCharset</li>
	 * <li><b>Retourne la liste des lignes</b> 
	 * du fichier simple texte pFile 
	 * lues avec le Charset pCharset.</li>
	 * <ul>
	 * <li>Utilise automatiquement le CHARSET_UTF8 
	 * si pCharset est null.</li>
	 * </ul>
	 * </ul>
	 * - Retourne null si pFile est null.<br/>
	 * - Retourne null si pFile n'existe pas.<br/>
	 * - Retourne null si pFile est un répertoire.<br/>
	 * - Retourne null en cas d'Exception loggée 
	 * (MalformedInputException, ...).<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier simple textuel à lire.<br/>
	 * @param pCharset : Charset : le Charset à utiliser pour 
	 * lire le fichier pFile.<br/>
	 * 
	 * @return : List&lt;String&gt; : Liste des lignes lues.<br/>
	 * 
	 * @throws Exception en cas d'Exception loggée 
	 * (IOException, MalformedInputException, ...).<br/>
	 */
	private static List<String> lireStringsDansFile(
			final File pFile
				, final Charset pCharset) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* Retourne null si pFile est null. */
			if (pFile == null) {
				return null;
			}
			
			/* Retourne null si pFile n'existe pas. */
			if (!pFile.exists()) {
				return null;
			}
			
			/* Retourne null si pFile est un répertoire. */
			if (pFile.isDirectory()) {
				return null;
			}
			
			/* Utilise automatiquement le CHARSET_UTF8 si pCharset est null. */
			Charset charset = null;
			
			if (pCharset == null) {
				charset = CHARSET_UTF8;
			}
			else {
				charset = pCharset;
			}
			
			/* Récupère le Path de pFile. */
			final Path pathFichier = pFile.toPath();
			
			try {
				
				// *****************************************************
				/* Retourne la liste des lignes lues avec le charset. */
				return Files.readAllLines(pathFichier, charset);
				
			} 
			
			catch (MalformedInputException malformedInputException) {
				
				final String message 
				=  "Impossible de lire le contenu du fichier '" 
				+ pFile.getName() 
				+ "' avec le Charset " 
				+ charset.displayName(Locale.getDefault()) 
				+ " à cause d'un caractère qui ne peut être "
				+ "écrit dans ce Charset (MalformedInputException)";
				
				/* LOG de niveau Error. */
				loggerError(CLASSE_SECTIONHIT_GESTIONNAIRE_PREFS_RG
						, METHODE_LIRE_STRINGS_DANS_FILE 
						+ SEPARATEUR_MOINS_AERE 
						+ message
						, malformedInputException);
				
				/* retourne null en cas d'Exception loggée 
				 * (IOException, MalformedInputException, ...). */
				return null;
		
			}
			
			catch (IOException ioe) {
				
				/* LOG de niveau Error. */
				loggerError(CLASSE_SECTIONHIT_GESTIONNAIRE_PREFS_RG
						, METHODE_LIRE_STRINGS_DANS_FILE
						, ioe);
				
				final String message 
				= CLASSE_SECTIONHIT_GESTIONNAIRE_PREFS_RG 
				+ SEPARATEUR_MOINS_AERE 
				+ METHODE_LIRE_STRINGS_DANS_FILE 
				+ SEPARATEUR_MOINS_AERE 
				+ "Impossible de lire le contenu du fichier '" 
				+ pFile.getName() 
				+ "' avec le Charset " 
				+ charset.displayName(Locale.getDefault());
				
				/* jette une Exception en cas d'Exception loggée 
				 * (IOException, MalformedInputException, ...). */
				throw new Exception(message, ioe);
			
			}
			
		} // Fin du bloc synchronized.__________________
			
	} // Fin de lireStringsDansFile(...).__________________________________
	

	
	/**
	 * <b>Crée une String à partir d'une liste de Strings</b>.
	 * <ul>
	 * <li>ajoute un saut de ligne de la plateforme 
	 * NEWLINE à chaque ligne.</li>
	 * </ul>
	 * - retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;String&gt;.<br/>
	 * 
	 * @return : String.<br/>
	 */
	private static String creerStringAPartirDeListe(
			final List<String> pList) {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* retourne null si pList == null. */
			if (pList == null) {
				return null;
			}
			
			final StringBuilder stb = new StringBuilder();
			
			for (final String ligne : pList) {
				
				stb.append(ligne);
				
				/* ajoute un saut de ligne de la plateforme 
				 * NEWLINE à chaque ligne. */
				stb.append(NEWLINE);
			}
			
			return stb.toString();

		} // Fin du bloc synchronized.__________________
		
	} // Fin de creerStringAPartirDeListe(...).____________________________
	
	
	
	/**
	 * method loggerInfo(
	 * String pClasse
	 * , String pMethode
	 * , String pMessage) :<br/>
	 * <ul>
	 * <li>Crée un message de niveau INFO dans le LOG.</li>
	 * </ul>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pMessage : String : Message particulier de la méthode.<br/>
	 */
	private static void loggerInfo(
			final String pClasse
				, final String pMethode
					, final String pMessage) {
		
		/* Ne fait rien si un des paramètres est null. */
		if (pClasse == null || pMethode == null || pMessage == null) {
			return;
		}
		
		/* LOG de niveau INFO. */			
		if (LOG.isInfoEnabled()) {
			
			final String message 
			= pClasse 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ pMessage;
			
			LOG.info(message);
		}
		
	} // Fin de la classe loggerInfo(...)._________________________________
	
	
	
	/**
	 * method loggerInfo(
	 * String pClasse
	 * , String pMethode
	 * , String pMessage
	 * , String pComplement) :<br/>
	 * <ul>
	 * <li>Créée un message de niveau INFO dans le LOG.</li>
	 * </ul>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pMessage : String : Message particulier de la méthode.<br/>
	 * @param pComplement : String : Complément au message de la méthode.<br/>
	 */
	private static void loggerInfo(
			final String pClasse
				, final String pMethode
					, final String pMessage
						, final String pComplement) {
		
		/* Ne fait rien si un des paramètres est null. */
		if (pClasse == null || pMethode == null 
				|| pMessage == null || pComplement == null) {
			return;
		}
		
		/* LOG de niveau INFO. */			
		if (LOG.isInfoEnabled()) {
			
			final String message 
			= pClasse 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ pMessage
			+ pComplement;
			
			LOG.info(message);
		}
		
	} // Fin de loggerInfo(...).___________________________________________
	
	
	
	/**
	 * method loggerError(
	 * String pClasse
	 * , String pMethode
	 * , Exception pException) :<br/>
	 * <ul>
	 * <li>Crée un message de niveau ERROR dans le LOG.</li>
	 * </ul>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pException : Exception : Exception transmise 
	 * par la méthode appelante.<br/>
	 */
	private static void loggerError(
			final String pClasse
				, final String pMethode
					, final Exception pException) {
		
		/* Ne fait rien si un des paramètres est null. */
		if (pClasse == null || pMethode == null || pException == null) {
			return;
		}
		
		/* LOG de niveau ERROR. */			
		if (LOG.isErrorEnabled()) {
			
			final String message 
			= pClasse 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE 
			+ pException.getMessage();
			
			LOG.error(message, pException);
			
		}
		
	} // Fin de loggerError(...).__________________________________________


	
	/**
	 * Méthod générique permettant de factoriser 
	 * les Getters des attributs.<br/>
	 * retourne la valeur du Boolean pAttribut 
	 * dans le fichier properties.<br/>
	 * <ul>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * <li><b>alimente l'attribut pAttribut avec sa valeur 
	 * dans le java.util.Properties <code>preferences</code>.</b></li>
	 * <ul>
	 * <li>nettoie la valeur lue dans le properties avec un trim().</li>
	 * <li>parse la valeur nettoyée lue dans le properties 
	 * et l'affecte à pAttribut.</li>
	 * <li>parse la valeur en dur si problème 
	 * et l'affecte à pAttribut.</li>
	 * </ul>
	 * <li><b>retourne la valeur de l'attribut 
	 * dans le fichier properties</b>.</li>
	 * </ul>
	 *
	 * @param pAttribut : Boolean : 
	 * un attribut de la classe (SINGLETON) comme 
	 * <code>validerRGSectionHitNumDepartement</code>
	 * @param pFournirKey : String : 
	 * clé de l'attribut Boolean pAttribut dans le fichier properties.
	 * @param pValeurEnDur : String : 
	 * valeur initiale stockée en dur dans la classe pour pAttribut.
	 * 
	 * @return Boolean : 
	 * l'attribut Boolean passé en paramètre tel qu'il est stocké 
	 * dans le fichier properties.<br/>
	 * 
	 * @throws Exception
	 */
	private static Boolean fournirAttribut(
			Boolean pAttribut
				, final String pFournirKey
					, final String pValeurEnDur) 
									throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/*  alimente le java.util.Properties preferences. */
			alimenterPreferences();
			
			/* alimente l'attribut pAttribut avec sa valeur 
			 * dans le fichier properties. */
			if (pAttribut == null) {
				
				/* lecture dans le properties. */
				final String valeurStringSale 
					= preferences
						.getProperty(pFournirKey);
				
				String valeurString = null;
				
				if (!StringUtils.isBlank(valeurStringSale)) {
					
					/* nettoie la valeur lue dans le properties 
					 * avec un trim(). */
					valeurString 
						= valeurStringSale.trim();
					
					try {
						
						/* parse la valeur nettoyée lue dans 
						 * le properties et l'affecte à pAttribut. */
						pAttribut 
							= Boolean.parseBoolean(valeurString);
						
					} catch (Exception e) {
						
						/* parse la valeur en dur si problème 
						 * et l'affecte à pAttribut. */
						pAttribut 
							= Boolean.parseBoolean(pValeurEnDur);
						
					}
					
				}
				else {
					
					/* parse la valeur en dur si problème 
					 * et l'affecte à pAttribut. */
					pAttribut 
						= Boolean.parseBoolean(pValeurEnDur);
				}
			}
			
			/* retourne la valeur de l'attribut dans 
			 * le fichier properties. */
			return pAttribut;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirAttribut(...).______________________________________
	

	
	/**
	 * Méthod générique permettant de factoriser 
	 * les Setters des attributs.<br/>
	 * change la valeur du Boolean pAttribut en pValue
	 * et l'écrit sur disque dans le fichier properties.<br/>
	 * <ul>
	 * <li>ne fait rien si le paramètre pValue est null
	 * ou ne modifie pas la valeur existante de pAttribut.</li>
	 * <li>affecte la nouvelle valeur pValue à l'attribut pAttribut.</li>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * <li>modifie le java.util.Properties <code>preferences</code></b> 
	 * avec la nouvelle valeur pValue passée en paramètre</li>
	 * <li>ré-écrit entièrement le fichier properties mis à jour 
	 * avec les nouvelles valeurs dans le java.util.Properties 
	 * <code>preferences</code>.</li>
	 * </ul>
	 *
	 * @param pValue : Boolean : 
	 * nouvelle valeur à passer à pAttribut et à stocker 
	 * dans le fichier properties de preferences.
	 * @param pAttribut : Boolean : 
	 * un attribut de la classe (SINGLETON) comme 
	 * <code>validerRGSectionHitNumDepartement</code> 
	 * @param pFournirKey : String : 
	 * clé de l'attribut Boolean pAttribut dans le fichier properties.
	 * 
	 * @throws Exception
	 */
	private static void setterAttribut(
			final Boolean pValue
				, Boolean pAttribut
					, final String pFournirKey) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* ne fait rien si le paramètre pValue est null
			 * ou ne modifie pas la valeur existante de pAttribut. */
			if (pValue != null 
					&& !pValue
						.equals(pAttribut)) {
				
				/* affecte la nouvelle valeur pValue à 
				 * l'attribut pAttribut. */
				pAttribut = pValue;
				
				final String valeurString 
					= pAttribut.toString();
				
				/* alimente le java.util.Properties preferences. */
				alimenterPreferences();
				
				/* modifie le java.util.Properties preferences 
				 * avec la nouvelle valeur pValue passée en paramètre. */
				creerOuModifierProperty(
						pFournirKey
							, valeurString);
				
				/* ré-écrit entièrement le fichier properties mis à jour 
				 * avec les nouvelles valeurs dans le 
				 * java.util.Properties preferences. */
				enregistrerPreferencesDansFichierProperties();

			}

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setterAttribut(...)._______________________________________


	
	/**
	 * alimente le java.util.Properties <code>preferences</code>.<br/>
	 * <ul>
	 * <li>instancie tous les attributs statiques si nécessaire.</li>
	 * <li>crée le fichier properties si il n'existe pas 
	 * (la première fois).</li>
	 * <li>lit le contenu du fichier properties si il existe.</li>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * </ul>
	 * <p>
	 * <img src="../../../../../../../../../../../javadoc/images/apptechnic/preferences/methode_alimenterPreferences_activites.png" 
	 * alt="diagramme d'activités de la méthode alimenterPreferences()" />
	 * </p>
	 * 
	 * @throws Exception
	 */
	private static void alimenterPreferences() throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* instancie tous les attributs statiques si nécessaire. */
			instancierAttributsStatiques();
			
			/* crée le fichier properties si il n'existe pas 
			 * (la première fois). */
			if (filePreferencesProperties == null 
					|| !filePreferencesProperties.exists()) {
				creerFichierPropertiesInitial();
			} else {
				
				/* lit le contenu du fichier properties si il existe. */
				lireFichierPreferencesProperties();
			}
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de alimenterPreferences().____________________________________
	

	
	/**
	 * <b>Crée ou met à jour une Property</b> dans 
	 * le <i>Properties</i> <b>preferences</b>.<br/>
	 * <ul>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * <li>Crée ou maj dans le java.util.Properties <b>preferences</b> 
	 * <i>sans enregistrer la modification sur le disque dur</i>.</li>
	 * <li><code>preferences.setProperty(pKey, pValue);</code></li>
	 * <li>trace EX_FONCT_PARAMETRAGE_03.</li>
	 * </ul>
	 * - retourne false si pKey == null.<br/>
	 * - retourne false si pValue == null.<br/>
	 * <br/>
	 *
	 * @param pKey : String : Clé.<br/>
	 * @param pValue : String : Valeur.<br/>
	 * 
	 * @return : boolean : true si la property a été créée.<br/>
	 * 
	 * @throws Exception 
	 */
	private static boolean creerOuModifierProperty(
			final String pKey
				, final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/*  alimente le java.util.Properties preferences. */
			alimenterPreferences();
			
			/* retourne false si pKey == null. */
			if (pKey == null) {
				return false;
			}
			
			/* retourne false si pValue == null. */
			if (pValue == null) {
				return false;
			}
			
			/* crée ou met à jour la Property dans preferences. */
			preferences.setProperty(pKey, pValue);
			
			return true;
			
		} // Fin du bloc synchronized.__________________
				
	} // Fin de creerOuModifierProperty(...).______________________________

	
	
	/**
	 * <b>Retire une Property</b> dans 
	 * le <i>Properties</i> <b>preferences</b>.<br/>
	 * <ul>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * <li>retire dans l'objet Properties <b>preferences</b> 
	 * <i>sans enregistrer la modification sur le disque dur 
	 * (.properties)</i>.</li>
	 * <li><code>preferences.remove(pKey);</code>.</li>
	 * </ul>
	 * - retourne false si pKey == null.<br/>
	 * <br/>
	 *
	 * @param pKey : String : Clé.<br/>
	 * 
	 * @return : boolean : true si la property a été retirée.<br/>
	 * 
	 * @throws Exception 
	 */
	private static boolean retirerProperty(
			final String pKey) 
					throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/*  alimente le java.util.Properties preferences. */
			alimenterPreferences();
			
			/* retourne false si pKey == null. */
			if (pKey == null) {
				return false;
			}
			
			/* retire la Property de preferences. */
			preferences.remove(pKey);
			
			return true;
			
		} // Fin du bloc synchronized.__________________
				
	} // Fin de retirerProperty(...).______________________________________
	

	
	/**
	 * vide le <i>Properties</i> <b>preferences</b>.<br/>
	 * <ul>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * <li>vide l'objet <i>Properties</i> <b>preferences</b> 
	 * sans vider le <i>fichier</i> .properties correspondant 
	 * sur le disque dur.</li>
	 * <li><code>preferences.remove(cle);</code>.</li>
	 * </ul>
	 * - retourne false si l'ensemble des clés du 
	 * Properties preferences est null.<br/>
	 * <br/>
	 *
	 * @return : boolean : true si preferences a été vidée.<br/>
	 * 
	 * @throws Exception 
	 */
	private static boolean viderPreferences() throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/*  alimente le java.util.Properties preferences. */
			alimenterPreferences();
				
			final Set<String> clesSet 
				= preferences.stringPropertyNames();
			
			/* retourne false si l'ensemble des clés 
			 * du Properties preferences est null. */
			if (clesSet == null) {
				return false;
			}
			
			/* vidage du Properties preferences. */
			for (final String cle : clesSet) {
				preferences.remove(cle);
			}
			
			return true;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de viderPreferences().________________________________________



	/**
	 * Getter du java.util.Properties encapsulant les préférences.<br/>
	 * SINGLETON.<br/>
	 * <ul>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * <li>retourne le java.util.Properties <code>preferences</code>.</li>
	 * <li>trace EX_FONCT_PARAMETRAGE_01</li>
	 * </ul>
	 *
	 * @return preferences : Properties.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Properties getPreferences() throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/*  alimente le java.util.Properties preferences. */
			alimenterPreferences();
			
			return preferences;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de getPreferences().__________________________________________
	
	
	
	/**
	 * fournit une String pour l'affichage de preferences.properties.<br/>
	 * <ul>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * <li>affiche le contenu de preferences</li>
	 * <li>trace EX_FONCT_PARAMETRAGE_01</li>
	 * <li>trie un Set&lt;String&gt;.</li>
	 * </ul>
	 *
	 * @return : String.<br/>
	 * 
	 * @throws Exception
	 */
	public static String afficherPreferences() throws Exception {

		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/*  alimente le java.util.Properties preferences. */
			alimenterPreferences();
			
			final StringBuffer stb = new StringBuffer();
			
			/* trie un Set<String>. */
			final TreeSet<String> setTrie 
				= new TreeSet<String>(preferences.stringPropertyNames());
			
			for (final String key : setTrie) {
				stb.append(key);
				stb.append(EGAL);
				stb.append(preferences.getProperty(key));
				stb.append(SAUT_LIGNE_JAVA);
			}
			
			return stb.toString();

		} // Fin du bloc synchronized.__________________
		
	} // Fin de afficherPreferences()._____________________________________


	
	/**
	 * Getter du Path absolu vers le fichier properties 
	 * contenant les preferences 
	 * <code>SectionHit_RG.properties</code>.<br/>
	 * SINGLETON.<br/>
	 *
	 * @return pathAbsoluPreferencesProperties : Path.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Path getPathAbsoluPreferencesProperties() 
											throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			if (pathAbsoluPreferencesProperties == null) {
				instancierPathAbsoluPreferencesProperties();
			}
			
			return pathAbsoluPreferencesProperties;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de getPathAbsoluPreferencesProperties().______________________


		
	/**
	 * Getter du Chemin relatif (par rapport à ressources_externes) 
	 * du fichier properties contenant les preferences
	 * <code>SectionHit_RG.properties</code>.<br/>
	 * "preferences/metier/utilisateurs/SectionHit_RG.properties"
	 *
	 * @return CHEMIN_RELATIF_PREFERENCES_PROPERTIES_STRING : String.<br/>
	 */
	public static String getCheminRelatifPreferencesPropertiesString() {
		return CHEMIN_RELATIF_PREFERENCES_PROPERTIES_STRING;
	} // Fin de getCheminRelatifPreferencesPropertiesString()._____________



	/**
	 * Getter de la  Modélisation Java du fichier properties 
	 * contenant les preferences 
	 * <code>SectionHit_RG.properties</code>.<br/>
	 * SINGLETON.<br/>
	 *
	 * @return filePreferencesProperties : File.<br/>
	 * 
	 * @throws Exception 
	 */
	public static File getFilePreferencesProperties() throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			if (filePreferencesProperties == null) {				
				instancierFilePreferencesProperties();
			}
			
			return filePreferencesProperties;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de getFilePreferencesProperties().____________________________
	
	
	
	/**
	 * Getter du commentaire à ajouter en haut du fichier properties 
	 * contenant les preferences 
	 * <code>SectionHit_RG.properties</code>.<br/>
	 * SINGLETON.<br/>
	 *
	 * @return commentaire : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getCommentaire() throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			if (commentaire == null) {
				instancierCommentaire();
			}
			
			return commentaire;
			
		} // Fin du bloc synchronized.__________________
				
	} // Fin de getCommentaire().__________________________________________
	
	
	
	/**
	* Setter du commentaire à ajouter en haut du fichier properties 
	* contenant les preferences 
	* <code>SectionHit_RG.properties</code>.<br/>
	*
	* @param pCommentaire : String : 
	* valeur à passer à commentaire.<br/>
	*/
	public static void setCommentaire(
			final String pCommentaire) {
		commentaire = pCommentaire;
	} // Fin de setCommentaire(...)._______________________________________
	

			
	/**
	 * Getter du Chemin relatif (par rapport à src/main/resources) 
	 * du template contenant le commentaire à ajouter 
	 * au dessus de SectionHit_RG.properties.<br/>
	 * "commentaires_properties/commentaires_preferences_properties.txt"
	 * <br/>
	 *
	 * @return CHEMIN_RELATIF_TEMPLATE_COMMENTAIRE : String.<br/>
	 */
	public static String getCheminRelatifTemplateCommentaire() {
		return CHEMIN_RELATIF_TEMPLATE_COMMENTAIRE;
	} // Fin de getCheminRelatifTemplateCommentaire()._____________________
	
	
	
	/**
	 * retourne le <code>validerRGSectionHitNumDepartement</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>numDepartement</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numDepartement</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartement stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumDepartement 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumDepartement() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitNumDepartement
					, fournirKeyValiderRGSectionHitNumDepartement()
					, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumDepartement().________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumDepartement 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numdepartement".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumDepartement() {
		return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT;
	} // Fin de fournirKeyValiderRGSectionHitNumDepartement()._____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumDepartement par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartement 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumDepartement stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumDepartement : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumDepartement() 
													throws Exception {
		return fournirValiderRGSectionHitNumDepartement();
	} // Fin de getValiderRGSectionHitNumDepartement().____________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumDepartement par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitNumDepartement.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumDepartement(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitNumDepartement
							, fournirKeyValiderRGSectionHitNumDepartement());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumDepartement(...)._________________



	/**
	 * retourne le validerRGSectionHitNumDepartementRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartementRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumDepartementRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumDepartementRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumDepartementRenseigne01
					, fournirKeyValiderRGSectionHitNumDepartementRenseigne01()
					, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumDepartementRenseigne01()._______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumDepartementRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numdepartement.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumDepartementRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitNumDepartementRenseigne01().__



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumDepartementRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartementRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumDepartementRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumDepartementRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumDepartementRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitNumDepartementRenseigne01();
	} // Fin de getValiderRGSectionHitNumDepartementRenseigne01()._________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumDepartementRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitNumDepartementRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumDepartementRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumDepartementRenseigne01
							, fournirKeyValiderRGSectionHitNumDepartementRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumDepartementRenseigne01(...).______



	/**
	 * retourne le validerRGSectionHitNumDepartementRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartementRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumDepartementRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumDepartementRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumDepartementRegex02
					, fournirKeyValiderRGSectionHitNumDepartementRegex02()
					, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumDepartementRegex02()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumDepartementRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numdepartement.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumDepartementRegex02() {
		return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitNumDepartementRegex02().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumDepartementRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartementRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumDepartementRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumDepartementRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumDepartementRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitNumDepartementRegex02();
	} // Fin de getValiderRGSectionHitNumDepartementRegex02()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumDepartementRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGSectionHitNumDepartementRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumDepartementRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumDepartementRegex02
							, fournirKeyValiderRGSectionHitNumDepartementRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumDepartementRegex02(...).__________
	
	
	
} // FIN DE LA CLASSE SectionHitGestionnairePreferencesRG.-------------------
