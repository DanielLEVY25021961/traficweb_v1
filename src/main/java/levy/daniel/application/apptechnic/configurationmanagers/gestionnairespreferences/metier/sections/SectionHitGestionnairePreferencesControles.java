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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesbundles.BundleConfigurationProjetManager;

/**
 * CLASSE SectionHitGestionnairePreferencesControles :<br/>
 * .<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 13 août 2019
 *
 */
public final class SectionHitGestionnairePreferencesControles {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe SectionHitGestionnairePreferencesControles".<br/>
	 */
	public static final String CLASSE_SECTIONHIT_GESTIONNAIRE_PREFS_CONTROLES 
		= "Classe SectionHitGestionnairePreferencesControles";

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
	 * '='.<br/>
	 */
	public static final char EGAL = '=';
	
	/**
	 * "méthode lireStringsDansFile(File pFile, Charset pCharset)".<br/>
	 */
	public static final String METHODE_LIRE_STRINGS_DANS_FILE 
		= "méthode lireStringsDansFile(File pFile, Charset pCharset)";

	// CONTROLES ***************************************************
	/* 1 - numDepartement. */
	/**
	 * clé de messageSectionHitNumDepartementRenseigne01 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.numdepartement.renseigne"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01 
		= "message.SectionHit.numdepartement.renseigne";
	
	/**
	 * messageSectionHitNumDepartementRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitNumDepartementRenseigne01 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le numéro de département de la section HIT doit être renseigné (obligatoire)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01_EN_DUR 
		= "le numéro de département de la section HIT doit être renseigné (obligatoire)";
		
	/**
	 * clé de messageSectionHitNumDepartementRegex02 dans 
	 * SectionHit_CONTROLES.properties<br/>
	 * "message.SectionHit.numdepartement.regex"<br/>
	 */
	public static final String KEY_MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02 
		= "message.SectionHit.numdepartement.regex";
	
	/**
	 * messageSectionHitNumDepartementRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * messageSectionHitNumDepartementLitteral02 
	 * indiqué dans SectionHit_CONTROLES.properties.<br/>
	 * "le numéro de département doit comporter exactement 3 chiffres 
	 * ('030' pour 03, '300' pour 30, '972' pour 972)".<br/>
	 */
	public static final String MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR 
		= "le numéro de département doit comporter exactement 3 chiffres ('030' pour 03, '300' pour 30, '972' pour 972)";
		
	/**
	* java.util.Properties encapsulant les préférences.<br/>
	*/
	private static Properties preferences = new Properties();
	
	/**
	* Path absolu vers le fichier properties contenant les preferences
	* <code>SectionHit_CONTROLES.properties</code>.<br/>
	*/
	private static Path pathAbsoluPreferencesProperties;
	
	/**
	* Chemin relatif (par rapport à ressources_externes) 
	* du fichier properties contenant les preferences
	* <code>SectionHit_CONTROLES.properties</code>.<br/>
	* "preferences/metier/sections/SectionHit_CONTROLES.properties"
	*/
	private static final String CHEMIN_RELATIF_PREFERENCES_PROPERTIES_STRING 
	= "preferences/metier/sections/SectionHit_CONTROLES.properties";
	
	/**
	* Modélisation Java du fichier properties contenant les preferences
	* <code>SectionHit_CONTROLES.properties</code>.<br/>
	*/
	private static File filePreferencesProperties;
	
	/**
	* commentaire à ajouter en haut du fichier properties 
	* contenant les preferences 
	* <code>SectionHit_CONTROLES.properties</code>.<br/>
	*/
	private static String commentaire;
	
	/**
	* Chemin relatif (par rapport à src/main/resources) 
	* du template contenant le commentataire à ajouter 
	* en haut du fichier properties contenant les preferences 
	* <code>SectionHit_CONTROLES.properties</code>.<br/>
	* "commentaires_properties/metier/sections/SectionHit_CONTROLES_properties_commentaires.txt"
	*/
	private static final String CHEMIN_RELATIF_TEMPLATE_COMMENTAIRE 
	= "commentaires_properties/metier/sections/SectionHit_CONTROLES_properties_commentaires.txt";

	// MESSAGES ************************************************
	/* 1 - numDepartement. */
	/**
	* message émis par la RG-SectionHit-NumDepartement-01 : 
	* "le numéro de département doit obligatoirement être renseigné".<br/>
	*/
	private static String messageSectionHitNumDepartementRenseigne01;
	
	/**
	* message émis par la RG-SectionHit-NumDepartement-02 : 
	* "le numéro de département doit comporter exactement 3 chiffres 
	* dans les colonnes [1-3] du HIT 
	* ('030' pour 03, '300' pour 30, '972' pour 972)".<br/>
	*/
	private static String messageSectionHitNumDepartementRegex02;


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(SectionHitGestionnairePreferencesControles.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation<br/>
	 */
	private SectionHitGestionnairePreferencesControles() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * <b>sauvegarde sur disque un fichier
	 * <code>ressources_externes/preferences/metier/sections/ 
	 * SectionHit_CONTROLES.properties</code> initial</b> alimenté par des 
	 * propriétés [clé-valeur] <b>écrites en dur</b> 
	 * dans la présente classe.<br/>
	 * <ul>
	 * <li>remplit le java.util.Properties <code>preferences</code> 
	 * avec des [clé-valeur] stockées en dur dans la classe.</li>
	 * <li>crée le fichier properties contenant les préférences 
	 * <code>filePreferencesProperties</code> VIDE 
	 * sur le disque si il n'existe pas.</li>
	 * <li>remplit le fichier <code>filePreferencesProperties</code> 
	 * (SectionHit_CONTROLES.properties) 
	 * avec le contenu de <code>preferences</code> 
	 * ([clé-valeur] stockées en dur dans la classe).</li>
	 * <li>Ecrit en UTF8 le Properties <code>preferences</code> dans 
	 * le File <code>filePreferencesProperties</code> 
	 * modélisant le fichier SectionHit_CONTROLES.properties en positionnant 
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
	 * 1 - numDepartement
	 * <li>ajoute le messageSectionHitNumDepartementRenseigne01 
	 * par défaut stocké en dur.</li>
	 * <li>ajoute le messageSectionHitNumDepartementRegex02 
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			/* 1 - numDepartement. */		
			/* ajoute le messageSectionHitNumDepartementRenseigne01 
			 * par défaut stockée en dur.*/
			preferences.setProperty(
					KEY_MESSAGE_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01
						, MESSAGE_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le messageSectionHitNumDepartementLitteral02 
			 * par défaut stockée en dur.*/
			preferences.setProperty(
					KEY_MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02
						, MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR);
			

			
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
	 * <li>calcule le path du SectionHit_CONTROLES.properties 
	 * via un resolve par rapport au path des ressources externes.</li>
	 * <li>alimente <code>pathAbsoluPreferencesProperties</code></li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void instancierPathAbsoluPreferencesProperties() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			/* ne fait rien si pathAbsoluPreferencesProperties 
			 * n'est pas null. */
			if (pathAbsoluPreferencesProperties == null) {
				
				/* obtient le path du properties dans les 
				 * ressources externes auprès du 
				 * ConfigurationApplicationManager. */
				final Path pathRessourcesExternes 
				= Paths.get(ConfigurationApplicationManager
						.getPathRessourcesExternes());
				
				/* calcule le path du SectionHit_CONTROLES.properties 
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			/* ne fait rien si commentaire n'est pas null. */
			if (commentaire == null) {
				
				/* lit dans un template le commentaire à ajouter au début du 
				 * SectionHit_CONTROLES.properties et le stocke 
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
	 * <code>SectionHit_CONTROLES.properties</code> <b>VIDE</b> 
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
	 * SectionHit_CONTROLES.properties</b></code> 
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

		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
	 * SectionHit_CONTROLES.properties</b></code>.<br/>
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
				loggerError(CLASSE_SECTIONHIT_GESTIONNAIRE_PREFS_CONTROLES
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
				loggerError(CLASSE_SECTIONHIT_GESTIONNAIRE_PREFS_CONTROLES
						, METHODE_LIRE_STRINGS_DANS_FILE
						, ioe);
				
				final String message 
				= CLASSE_SECTIONHIT_GESTIONNAIRE_PREFS_CONTROLES 
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
	 * retourne la valeur du String pAttribut 
	 * dans le fichier properties.<br/>
	 * <ul>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * <li><b>alimente l'attribut pAttribut avec sa valeur 
	 * dans le java.util.Properties <code>preferences</code>.</b></li>
	 * <ul>
	 * <li>nettoie la valeur lue dans le properties avec un trim().</li>
	 * <li>affecte la valeur nettoyée lue dans le properties à pAttribut 
	 * si le properties est accessible.</li>
	 * <li>affecte la valeur en dur à pAttribut si problème.</li>
	 * </ul>
	 * <li><b>retourne la valeur de l'attribut 
	 * dans le fichier properties</b>.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * un attribut de la classe (SINGLETON) comme 
	 * <code>messageSectionHitNumDepartementRenseigne01</code>
	 * @param pFournirKey : String : 
	 * clé de l'attribut String pAttribut dans le fichier properties.
	 * @param pValeurEnDur : String : 
	 * valeur initiale stockée en dur dans la classe pour pAttribut.
	 * 
	 * @return String : 
	 * l'attribut String passé en paramètre tel qu'il est stocké 
	 * dans le fichier properties.<br/>
	 * 
	 * @throws Exception
	 */
	private static String fournirAttribut(
			String pAttribut
				, final String pFournirKey
					, final String pValeurEnDur) 
									throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			/*  alimente le java.util.Properties preferences. */
			alimenterPreferences();
			
			/* alimente l'attribut pAttribut avec sa valeur 
			 * dans le fichier properties. */
			if (pAttribut == null) {
				
				/* lecture dans le properties. */
				final String valeurStringSale 
					= preferences
						.getProperty(pFournirKey);
				
				if (!StringUtils.isBlank(valeurStringSale)) {
					
					/* nettoie la valeur lue dans le properties 
					 * avec un trim(). */
					pAttribut 
						= valeurStringSale.trim();
					
				}
				else {
					
					/* prend valeur en dur si problème 
					 * et l'affecte à pAttribut. */
					pAttribut 
						= pValeurEnDur.trim();
				}
			}
			
			/* retourne la valeur de l'attribut dans 
			 * le fichier properties. */
			return pAttribut;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirAttribut(...).______________________________________

	
	
	/**
	 * Méthod générique permettant de factoriser 
	 * les Getters des attributs possédant une valeur numérique à changer.<br/>
	 * retourne la valeur du String pAttribut 
	 * dans le fichier properties.<br/>
	 * Par exemple :<br/>
	 * si la valeur dans pAttribut vaut "la civilite de l'SectionHit 
	 * ne doit pas excéder 15 caractères", 
	 * et  pValeurAInjecter vaut "127", la méthode retourne 
	 * "la civilite de l'SectionHit ne doit pas excéder 127 caractères".<br/>
	 * <ul>
	 * <li>alimente le java.util.Properties <code>preferences</code>.</li>
	 * <li><b>alimente l'attribut pAttribut avec sa valeur 
	 * dans le java.util.Properties <code>preferences</code>.</b></li>
	 * <ul>
	 * <li>nettoie la valeur lue dans le properties avec un trim().</li>
	 * <li>remplace le nombre dans la valeur lue dans le properties 
	 * par la valeur à injecter.</li>
	 * <li>affecte la valeur nettoyée lue dans le properties à pAttribut 
	 * si le properties est accessible.</li>
	 * <li>affecte la valeur en dur à pAttribut si problème.</li>
	 * </ul>
	 * <li><b>retourne la valeur de l'attribut 
	 * dans le fichier properties</b>.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : 
	 * un attribut de la classe (SINGLETON) comme 
	 * <code>messageSectionHitNumDepartementLongueur03</code>
	 * @param pFournirKey : String : 
	 * clé de l'attribut String pAttribut dans le fichier properties.
	 * @param pValeurAInjecter : String : 
	 * valeur numérique à substituer dans pAttribut.
	 * @param pValeurEnDur : String : 
	 * valeur initiale stockée en dur dans la classe pour pAttribut.
	 * 
	 * @return String : 
	 * l'attribut String passé en paramètre tel qu'il est stocké 
	 * dans le fichier properties.<br/>
	 * 
	 * @throws Exception
	 */
	private static String fournirAttributSubstitue(
			String pAttribut
				, final String pFournirKey
					, final String pValeurAInjecter
						, final String pValeurEnDur) 
										throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			/*  alimente le java.util.Properties preferences. */
			alimenterPreferences();
			
			/* alimente l'attribut pAttribut avec sa valeur 
			 * dans le fichier properties. */
			if (pAttribut == null) {
				
				/* lecture dans le properties. */
				final String valeurStringSale 
					= preferences
						.getProperty(pFournirKey);
				
				if (!StringUtils.isBlank(valeurStringSale)) {
					
					/* nettoie la valeur lue dans le properties 
					 * avec un trim(). */
					pAttribut 
						= valeurStringSale.trim();
					
					/* remplace le nombre par la valeur à injecter. */
					pAttribut = remplacerNombreParValeur(
							pAttribut, pValeurAInjecter);
				}
				else {
					
					/* prend valeur en dur si problème 
					 * et l'affecte à pAttribut. */
					pAttribut 
						= pValeurEnDur.trim();
				}
			}
			
			/* retourne la valeur de l'attribut dans 
			 * le fichier properties. */
			return pAttribut;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirAttributSubstitue(...)._____________________________


	
	/**
	 * remplace le premier nombre rencontré dans une String pString 
	 * par la valeur pValeurAInjecter.<br/>
	 * Par exemple :<br/>
	 * <code>remplacerNombreParValeur("la civilite de l'SectionHit 
	 * ne doit pas excéder 15 caractères", "127")</code> retourne 
	 * "la civilite de l'SectionHit ne doit pas excéder 127 caractères".<br/>
	 * <ul>
	 * <li>utilise une REGEX avec le motif "(\\d+)" 
	 * pour trouver le nombre à substituer.</li>
	 * <li>utilise <code>matcher.replaceFirst(pValeurAInjecter)</code> 
	 * pour substituer la valeur à injecter au nombre trouvé.</li>
	 * </ul>
	 *
	 * @param pString : String : 
	 * chaine de caractères comportant un nombre à substituer.
	 * @param pValeurAInjecter : String : valeur de substitution.
	 * 
	 * @return : String : chaine substituée.<br/>
	 */
	private static String remplacerNombreParValeur(
			final String pString
				, final String pValeurAInjecter) {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			String resultat = null;
			
			final String motif = "(\\d+)";
			
			final Pattern pattern = Pattern.compile(motif);
			
			final Matcher matcher = pattern.matcher(pString);
			
			if (matcher.find()) {
								
				resultat = matcher.replaceFirst(pValeurAInjecter);
				
			}
			
			return resultat;

		} // Fin du bloc synchronized.__________________
		
	} // Fin de remplacerNombreParValeur(...)._____________________________
	

	
	/**
	 * Retourne un boolean à true si pString 
	 * est de la forme "[1 Nombre]" comme 
	 * <i>"15"</i>.<br/>
	 * <ul>
	 * <li>utilise la Regex "^(\\d+)$" qui matche si pString 
	 * est <b>un nombre</b>.</li>
	 * </ul>
	 *
	 * @param pString : String
	 * 
	 * @return : boolean : 
	 * true si la chaine est un nombre.<br/>
	 */
	private static boolean est1Nombre(
			final String pString) {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			boolean resultat = false;
			
			/* motif : chaine de caractères est un nombre. */
			final String motif = "^(\\d+)$";
			
			final Pattern pattern = Pattern.compile(motif);
			
			final Matcher matcher = pattern.matcher(pString);
			
			if (matcher.matches()) {
				resultat = true;
			} 
			
			return resultat;
			
		} // Fin du bloc synchronized.__________________
				
	} // Fin de est1Nombre(...).___________________________________________

	
	
	/**
	 * Retourne un boolean à true si pString 
	 * est de la forme "....[1 Nombre] ..." comme 
	 * <i>"la civilite de l'SectionHit ne doit pas 
	 * excéder 15 caractères"</i>.<br/>
	 * <ul>
	 * <li>utilise la Regex "^(\\D*)(\\d+)(\\D*)$" qui matche si pString 
	 * ne comporte <b>qu'un et un seul nombre</b>.</li>
	 * </ul>
	 *
	 * @param pString : String
	 * 
	 * @return : boolean : 
	 * true si la chaine de caractères ne comporte 
	 * qu'un et un seul nombre.<br/>
	 */
	private static boolean respecteFormat1Nombre(
			final String pString) {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			boolean resultat = false;
			
			/* motif : chaine de caractères ne comporte 
			 * qu'un et un seul nombre*/
			final String motif = "^(\\D*)(\\d+)(\\D*)$";
			
			final Pattern pattern = Pattern.compile(motif);
			
			final Matcher matcher = pattern.matcher(pString);
			
			if (matcher.matches()) {
				resultat = true;
			} 
			
			return resultat;
			
		} // Fin du bloc synchronized.__________________
				
	} // Fin de respecteFormat1Nombre(...).________________________________


	
	/**
	 * Extrait et Retourne un nombre contenu dans pString si pString 
	 * est de la forme "....[1 Nombre] ..." comme 
	 * <i>"la civilite de l'SectionHit ne doit pas 
	 * excéder 15 caractères"</i>.<br/>
	 * <ul>
	 * <li>utilise la Regex "^(\\D*)(\\d+)(\\D*)$" qui matche si pString 
	 * ne comporte <b>qu'un et un seul nombre</b>.</li>
	 * <li><code>extraire1Nombre("ne doit pas excéder 15 caractères")</code> 
	 * retourne 15.</li>
	 * </ul>
	 *
	 * @param pString : String
	 * 
	 * @return : String : le nombre unique contenu dans pString.<br/>
	 */
	private static String extraire1Nombre(
			final String pString) {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			String resultat = null;
			
			/* motif : chaine de caractères ne comporte 
			 * qu'un et un seul nombre*/
			final String motif = "^(\\D*)(\\d+)(\\D*)$";
			
			final Pattern pattern = Pattern.compile(motif);
			
			final Matcher matcher = pattern.matcher(pString);
			
			if (matcher.matches()) {
				resultat = matcher.group(2);				
			}
			
			return resultat;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de extraire1Nombre(...).______________________________________
		
	
	
	/**
	 * Méthod générique permettant de factoriser 
	 * les Setters des attributs.<br/>
	 * change la valeur du String pAttribut en pValue
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
	 * @param pValue : String : 
	 * nouvelle valeur à passer à pAttribut et à stocker 
	 * dans le fichier properties de preferences.
	 * @param pAttribut : String : 
	 * un attribut de la classe (SINGLETON) comme 
	 * <code>messageRGSectionHitNumDepartement</code> 
	 * @param pFournirKey : String : 
	 * clé de l'attribut String pAttribut dans le fichier properties.
	 * 
	 * @throws Exception
	 */
	private static void setterAttribut(
			final String pValue
				, String pAttribut
					, final String pFournirKey) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			/* ne fait rien si le paramètre pValue est null
			 * ou ne modifie pas la valeur existante de pAttribut. */
			if (pValue != null 
					&& !pValue
						.equals(pAttribut)) {
				
				/* affecte la nouvelle valeur pValue à 
				 * l'attribut pAttribut. */
				pAttribut = pValue;
				
				/* alimente le java.util.Properties preferences. */
				alimenterPreferences();
				
				/* modifie le java.util.Properties preferences 
				 * avec la nouvelle valeur pValue passée en paramètre. */
				creerOuModifierProperty(
						pFournirKey
							, pAttribut);
				
				/* ré-écrit entièrement le fichier properties mis à jour 
				 * avec les nouvelles valeurs dans le 
				 * java.util.Properties preferences. */
				enregistrerPreferencesDansFichierProperties();

			}

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setterAttribut(...)._______________________________________
	

	
	/**
	* Méthod générique permettant de factoriser 
	* les Setters des attributs valeur liés à un unique attribut message.<br/>
	* Par exemple, l'attribut message 
	* <code>messageSectionHitNumDepartementLongueur03</code> 
	* susceptible de contenir 
	* "la civilite de l'SectionHit ne doit pas excéder 15 caractères" 
	* est lié à l'attribut valeur 
	* <code>valeurSectionHitNumDepartementLongueur03</code> 
	* qui doit alors contenir "15".<br/>
	* Setter du <b>SINGLETON de pAttributValeur comme 
	* <code>valeurSectionHitNumDepartementLongueur03</code> 
	* par défaut dans l'application</b>.<br/>
	* pAttributValeur est la valeur d'un attribut message 
	* <i>à une seule valeur</i> lié comme 
	* <code>messageSectionHitNumDepartementLongueur03</code>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* de l'attribut lié au présent.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si pValue n'est pas un nombre.<br/>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à pAttributValeur.<br/>
	* @param pAttributValeur : String : 
	* un attribut de la classe (SINGLETON) lié à 
	* un unique attribut message comme 
	* <code>valeurSectionHitNumDepartementLongueur03</code> 
	* @param pKeyAttributValeur : String : 
	* clé de l'attribut String pAttributValeur dans le fichier properties.
	* @param pAttributMessageLie : String : 
	* un attribut de la classe (SINGLETON) lié à 
	* un unique attribut valeur comme 
	* <code>messageSectionHitNumDepartementLongueur03</code> 
	* @param pKeyAttributMessageLie : String : 
	* clé de l'attribut String pAttributMessageLie dans le fichier properties.
	* 
	 * @throws Exception 
	*/
	private static void setterValeurAttributLieAUnAttribut(
			final String pValue
				, String pAttributValeur
					, final String pKeyAttributValeur
						, String pAttributMessageLie
							, final String pKeyAttributMessageLie) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			/* ne fait rien si pValue n'est pas un nombre. */
			if (!est1Nombre(pValue)) {
				return;
			}
			
			/* ne fait rien si le paramètre pValue est null
			 * ou ne modifie pas la valeur existante de pAttribut. */
			if (pValue != null 
					&& !pValue
						.equals(pAttributValeur)) {
				
				/* affecte la nouvelle valeur pValue à 
				 * l'attribut pAttribut. */
				pAttributValeur = pValue;
				
				/* affecte la nouvelle valeur à l'attribut lié 
				 * au présent pAttribut. */
				pAttributMessageLie 
				= remplacerNombreParValeur(
						pAttributMessageLie
							, pAttributValeur);
				
				/* alimente le java.util.Properties preferences. */
				alimenterPreferences();
				
				/* modifie le java.util.Properties preferences 
				 * avec la nouvelle valeur pValue passée en paramètre. */
				creerOuModifierProperty(
						pKeyAttributValeur
							, pAttributValeur);
				
				/* ré-écrit entièrement le fichier properties mis à jour 
				 * avec les nouvelles valeurs dans le 
				 * java.util.Properties preferences. */
				enregistrerPreferencesDansFichierProperties();
				
				/* modifie le java.util.Properties preferences 
				 * avec la nouvelle valeur de l'attribut lié 
				 * au présent pAttribut. */
				creerOuModifierProperty(
						pKeyAttributMessageLie
							, pAttributMessageLie);
				
				/* ré-écrit entièrement le fichier properties mis à jour 
				 * avec les nouvelles valeurs dans le 
				 * java.util.Properties preferences. */
				enregistrerPreferencesDansFichierProperties();
				
			}
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setterValeurAttributLieAUnAttribut(...).___________________
	

	
	/**
	* Méthod générique permettant de factoriser 
	* les Setters des attributs message liés à un unique attribut valeur.<br/>
	* Par exemple, l'attribut message 
	* <code>messageSectionHitNumDepartementLongueur03</code> 
	* susceptible de contenir 
	* "la civilite de l'SectionHit ne doit pas excéder 15 caractères" 
	* est lié à l'attribut valeur 
	* <code>valeurSectionHitNumDepartementLongueur03</code> 
	* qui doit alors contenir "15".<br/>
	* Setter du <b>SINGLETON de pAttributMessage comme 
	* <code>messageSectionHitNumDepartementLongueur03</code> 
	* par défaut dans l'application</b>.<br/>
	* pAttributMessage est un attribut message 
	* <i>à une seule valeur</i> liée comme 
	* <code>valeurSectionHitNumDepartementLongueur03</code>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>affecte la nouvelle valeur à l'attribut 
	* lié au présent pAttribut.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si la nouvelle valeur pValue 
	* ne respecte pas le bon format.<br/>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à pAttributMessage.<br/>
	*  @param pAttributValeurLiee : String : 
	* un attribut de la classe (SINGLETON) lié à 
	* un unique attribut message comme 
	* <code>valeurSectionHitNumDepartementLongueur03</code> 
	* @param pKeyAttributValeurLiee : String : 
	* clé de l'attribut String pAttributValeurLiee dans le fichier properties.
	* @param pAttributMessage : String : 
	* un attribut de la classe (SINGLETON) lié à 
	* un unique attribut valeur comme 
	* <code>messageSectionHitNumDepartementLongueur03</code> 
	* @param pKeyAttributMessage : String : 
	* clé de l'attribut String pAttributMessageLie dans le fichier properties.
	* 
	* @throws Exception 
	*/
	private static void setMessageAttributLieAUnAttribut(
			final String pValue
				, String pAttributValeurLiee
					, final String pKeyAttributValeurLiee
				, String pAttributMessage
					, final String pKeyAttributMessage) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			/* ne fait rien si la nouvelle valeur pValue 
			 * ne respecte pas le bon format. */
			if (!respecteFormat1Nombre(pValue)) {
				return;
			}
			
			/* ne fait rien si le paramètre pValue est null
			 * ou ne modifie pas la valeur existante de pAttribut. */
			if (pValue != null 
					&& !pValue
						.equals(pAttributMessage)) {
				
				/* affecte la nouvelle valeur pValue à 
				 * l'attribut pAttribut. */
				pAttributMessage = pValue;
				
				/* affecte la nouvelle valeur à l'attribut lié 
				 * au présent pAttribut. */
				pAttributValeurLiee 
				= extraire1Nombre(
						pAttributMessage);
				
				/* alimente le java.util.Properties preferences. */
				alimenterPreferences();
				
				/* modifie le java.util.Properties preferences 
				 * avec la nouvelle valeur pValue passée en paramètre. */
				creerOuModifierProperty(
						pKeyAttributValeurLiee
							, pAttributValeurLiee);
				
				/* ré-écrit entièrement le fichier properties mis à jour 
				 * avec les nouvelles valeurs dans le 
				 * java.util.Properties preferences. */
				enregistrerPreferencesDansFichierProperties();
				
				/* modifie le java.util.Properties preferences 
				 * avec la nouvelle valeur de l'attribut lié 
				 * au présent pAttribut. */
				creerOuModifierProperty(
						pKeyAttributMessage
							, pAttributMessage);
				
				/* ré-écrit entièrement le fichier properties mis à jour 
				 * avec les nouvelles valeurs dans le 
				 * java.util.Properties preferences. */
				enregistrerPreferencesDansFichierProperties();
				
			}
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageAttributLieAUnAttribut(...)._____________________


	
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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

		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
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
	 * <code>SectionHit_CONTROLES.properties</code>.<br/>
	 * SINGLETON.<br/>
	 *
	 * @return pathAbsoluPreferencesProperties : Path.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Path getPathAbsoluPreferencesProperties() 
											throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			if (pathAbsoluPreferencesProperties == null) {
				instancierPathAbsoluPreferencesProperties();
			}
			
			return pathAbsoluPreferencesProperties;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de getPathAbsoluPreferencesProperties().______________________


		
	/**
	 * Getter du Chemin relatif (par rapport à ressources_externes) 
	 * du fichier properties contenant les preferences
	 * <code>SectionHit_CONTROLES.properties</code>.<br/>
	 * "preferences/metier/utilisateurs/SectionHit_CONTROLES.properties"
	 *
	 * @return CHEMIN_RELATIF_PREFERENCES_PROPERTIES_STRING : String.<br/>
	 */
	public static String getCheminRelatifPreferencesPropertiesString() {
		return CHEMIN_RELATIF_PREFERENCES_PROPERTIES_STRING;
	} // Fin de getCheminRelatifPreferencesPropertiesString()._____________



	/**
	 * Getter de la  Modélisation Java du fichier properties 
	 * contenant les preferences 
	 * <code>SectionHit_CONTROLES.properties</code>.<br/>
	 * SINGLETON.<br/>
	 *
	 * @return filePreferencesProperties : File.<br/>
	 * 
	 * @throws Exception 
	 */
	public static File getFilePreferencesProperties() throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			if (filePreferencesProperties == null) {				
				instancierFilePreferencesProperties();
			}
			
			return filePreferencesProperties;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de getFilePreferencesProperties().____________________________
	
	
	
	/**
	 * Getter du commentaire à ajouter en haut du fichier properties 
	 * contenant les preferences 
	 * <code>SectionHit_CONTROLES.properties</code>.<br/>
	 * SINGLETON.<br/>
	 *
	 * @return commentaire : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getCommentaire() throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			if (commentaire == null) {
				instancierCommentaire();
			}
			
			return commentaire;
			
		} // Fin du bloc synchronized.__________________
				
	} // Fin de getCommentaire().__________________________________________
	
	
	
	/**
	* Setter du commentaire à ajouter en haut du fichier properties 
	* contenant les preferences 
	* <code>SectionHit_CONTROLES.properties</code>.<br/>
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
	 * au dessus de SectionHit_CONTROLES.properties.<br/>
	 * "commentaires_properties/metier/utilisateurs/SectionHit_RG_properties_commentaires.txt"
	 * <br/>
	 *
	 * @return CHEMIN_RELATIF_TEMPLATE_COMMENTAIRE : String.<br/>
	 */
	public static String getCheminRelatifTemplateCommentaire() {
		return CHEMIN_RELATIF_TEMPLATE_COMMENTAIRE;
	} // Fin de getCheminRelatifTemplateCommentaire()._____________________



	/**
	 * retourne le messageSectionHitNumDepartementRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitNumDepartementRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitNumDepartementRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitNumDepartementRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitNumDepartementRenseigne01
					, fournirKeyMessageSectionHitNumDepartementRenseigne01()
					, MESSAGE_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitNumDepartementRenseigne01()._______
	

	
	/**
	 * Getter de la clé du messageSectionHitNumDepartementRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.numdepartement.renseigne".<br/>
	 *
	 * @return KEY_MESSAGE_UTILISATEUR_CIVILITE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitNumDepartementRenseigne01() {
		return KEY_MESSAGE_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01;
	} // Fin de fournirKeyMessageSectionHitNumDepartementRenseigne01().____



	/**
	 * Getter du <b>SINGLETON de messageSectionHitNumDepartementRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitNumDepartementRenseigne01 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitNumDepartementRenseigne01 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitNumDepartementRenseigne01() 
													throws Exception {
		return fournirMessageSectionHitNumDepartementRenseigne01();
	} // Fin de getMessageSectionHitNumDepartementRenseigne01().___________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitNumDepartementRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitNumDepartementRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitNumDepartementRenseigne01(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitNumDepartementRenseigne01
							, fournirKeyMessageSectionHitNumDepartementRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitNumDepartementRenseigne01(...).________



	/**
	 * retourne le messageSectionHitNumDepartementRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le messageSectionHitNumDepartementRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : String : messageSectionHitNumDepartementRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String fournirMessageSectionHitNumDepartementRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			return fournirAttribut(
					messageSectionHitNumDepartementRegex02
					, fournirKeyMessageSectionHitNumDepartementRegex02()
					, MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirMessageSectionHitNumDepartementRegex02().___________
	

	
	/**
	 * Getter de la clé du messageSectionHitNumDepartementRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_CONTROLES.properties.<br/>
	 * "message.SectionHit.numdepartement.regex".<br/>
	 *
	 * @return KEY_MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyMessageSectionHitNumDepartementRegex02() {
		return KEY_MESSAGE_SECTIONHIT_NUMDEPARTEMENT_REGEX_02;
	} // Fin de fournirKeyMessageSectionHitNumDepartementRegex02().________



	/**
	 * Getter du <b>SINGLETON de messageSectionHitNumDepartementRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le messageSectionHitNumDepartementRegex02 stocké 
	 * dans SectionHit_CONTROLES.properties 
	 * si il n'est pas null.</li>
	 * <li>valeur stockée en dur dans la classe sinon.</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return messageSectionHitNumDepartementRegex02 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getMessageSectionHitNumDepartementRegex02() 
													throws Exception {
		return fournirMessageSectionHitNumDepartementRegex02();
	} // Fin de getMessageSectionHitNumDepartementRegex02()._______________
	

	
	/**
	* Setter du <b>SINGLETON de messageSectionHitNumDepartementRegex02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* SectionHit_CONTROLES.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier SectionHit_CONTROLES.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : String : 
	* valeur à passer à messageSectionHitNumDepartementRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setMessageSectionHitNumDepartementRegex02(
			final String pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesControles.class) {
			
			setterAttribut(
					pValue
						, messageSectionHitNumDepartementRegex02
							, fournirKeyMessageSectionHitNumDepartementRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setMessageSectionHitNumDepartementRegex02(...).____________
	
	
	
} // FIN DE LA CLASSE SectionHitGestionnairePreferencesControles.------------
