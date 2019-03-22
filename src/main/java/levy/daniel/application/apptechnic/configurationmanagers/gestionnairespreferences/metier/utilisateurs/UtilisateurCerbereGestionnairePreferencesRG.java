package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.utilisateurs;

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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesbundles.BundleConfigurationProjetManager;
import levy.daniel.application.model.metier.utilisateur.impl.UtilisateurCerbere;


/**
 * CLASSE <b>UtilisateurCerbereGestionnairePreferencesRG</b> :<br/>
 * Classe Utilitaire chargée de gérer les 
 * <b>préférences relatives aux REGLES DE GESTION (RG) applicables 
 * à un {@link UtilisateurCerbere}</b>.<br/>
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
 * @author dan Lévy
 * @version 1.0
 * @since 24 juil. 2018
 *
 */
public final class UtilisateurCerbereGestionnairePreferencesRG {
	
	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe UtilisateurCerbereGestionnairePreferencesRG".<br/>
	 */
	public static final String CLASSE_UTILISATEURCERBERE_GESTIONNAIRE_PREFS_RG 
		= "Classe UtilisateurCerbereGestionnairePreferencesRG";

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
		
	/**
	 * clé de validerRGUtilisateurCivilite dans 
	 * UtilisateurCerbere_RG.properties<br/>
	 * "valider.UtilisateurCerbere.civilite"<br/>
	 */
	public static final String KEY_VALIDER_UTILISATEUR_CIVILITE 
		= "valider.UtilisateurCerbere.civilite";
	
	/**
	 * validerRGUtilisateurCivilite par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGUtilisateurCivilite 
	 * indiqué dans UtilisateurCerbere_RG.properties.<br/>
	 * "false".<br/>
	 */
	public static final String STRING_VALIDER_UTILISATEUR_CIVILITE_EN_DUR 
		= "false";
	
	/**
	 * clé de validerRGUtilisateurCiviliteRenseigne01 dans 
	 * UtilisateurCerbere_RG.properties<br/>
	 * "valider.UtilisateurCerbere.civilite.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_UTILISATEUR_CIVILITE_RENSEIGNE_01 
		= "valider.UtilisateurCerbere.civilite.renseigne";
	
	/**
	 * validerRGUtilisateurCiviliteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGUtilisateurCiviliteRenseigne01 
	 * indiqué dans UtilisateurCerbere_RG.properties.<br/>
	 * "false".<br/>
	 */
	public static final String STRING_VALIDER_UTILISATEUR_CIVILITE_RENSEIGNE_01_EN_DUR 
		= "false";
		
	/**
	 * clé de validerRGUtilisateurCiviliteLitteral02 dans 
	 * UtilisateurCerbere_RG.properties<br/>
	 * "valider.UtilisateurCerbere.civilite.litteral"<br/>
	 */
	public static final String KEY_VALIDER_UTILISATEUR_CIVILITE_LITTERAL_02 
		= "valider.UtilisateurCerbere.civilite.litteral";
	
	/**
	 * validerRGUtilisateurCiviliteLitteral02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGUtilisateurCiviliteLitteral02 
	 * indiqué dans UtilisateurCerbere_RG.properties.<br/>
	 * "false".<br/>
	 */
	public static final String STRING_VALIDER_UTILISATEUR_CIVILITE_LITTERAL_02_EN_DUR 
		= "false";
		
	/**
	 * clé de validerRGUtilisateurCiviliteLongueur03 dans 
	 * UtilisateurCerbere_RG.properties<br/>
	 * "valider.UtilisateurCerbere.civilite.longueur"<br/>
	 */
	public static final String KEY_VALIDER_UTILISATEUR_CIVILITE_LONGUEUR_03 
		= "valider.UtilisateurCerbere.civilite.longueur";
	
	/**
	 * validerRGUtilisateurCiviliteLongueur03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGUtilisateurCiviliteLongueur03 
	 * indiqué dans UtilisateurCerbere_RG.properties.<br/>
	 * "false".<br/>
	 */
	public static final String STRING_VALIDER_UTILISATEUR_CIVILITE_LONGUEUR_03_EN_DUR 
		= "false";
		
	/**
	 * clé de validerRGUtilisateurCiviliteNomenclature04 dans 
	 * UtilisateurCerbere_RG.properties<br/>
	 * "valider.UtilisateurCerbere.civilite.longueur"<br/>
	 */
	public static final String KEY_VALIDER_UTILISATEUR_CIVILITE_NOMENCLATURE_04 
		= "valider.UtilisateurCerbere.civilite.longueur";
	
	/**
	 * validerRGUtilisateurCiviliteNomenclature04 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGUtilisateurCiviliteNomenclature04 
	 * indiqué dans UtilisateurCerbere_RG.properties.<br/>
	 * "false".<br/>
	 */
	public static final String STRING_VALIDER_UTILISATEUR_CIVILITE_NOMENCLATURE_04_EN_DUR 
		= "false";
	
		
	/**
	 * Properties encapsulant les préférences.<br/>
	 */
	private static Properties preferences = new Properties();
	
	/**
	 * Path absolu vers UtilisateurCerbere_RG.properties.<br/>
	 */
	private static Path pathAbsoluPreferencesProperties;
	
	/**
	 * commentaire à ajouter en haut du fichier properties.<br/>
	 */
	private static String commentaire;
	
	/**
	 * Chemin relatif (par rapport à src/main/resources) 
	 * du template contenant le commentataire à ajouter 
	 * au dessus de UtilisateurCerbere_RG.properties.<br/>
	 * "commentaires_properties/metier/utilisateurs/UtilisateurCerbere_RG_properties_commentaires.txt"
	 */
	private static String cheminRelatifTemplateCommentaire 
		= "commentaires_properties/metier/utilisateurs/UtilisateurCerbere_RG_properties_commentaires.txt";
	
	/**
	 * Modélisation Java du fichier UtilisateurCerbere_RG.properties.<br/>
	 */
	private static File filePreferencesProperties;

	
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur la <i>civilite</i> de l'utilisateur.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de la <i>civilité</i> 
	 * de l'Utilisateur.<br/>
	 */
	private static Boolean validerRGUtilisateurCivilite;
	
	/**
	 * Boolean activant la RG-Utilisateur-Civilite-01 : 
	 * "la civilite de l'Utilisateur 
	 * doit être renseignée".<br/>
	 */
	private static Boolean validerRGUtilisateurCiviliteRenseigne01;
	
	/**
	 * Boolean activant la RG-Utilisateur-Civilite-02 : 
	 * "la civilite de l'Utilisateur ne doit comporter que des 
	 * lettres de l'alphabet et des caractères spéciaux (-, _, ...)
	 * (pas de chiffres)".<br/>
	 */
	private static Boolean validerRGUtilisateurCiviliteLitteral02;
	
	/**
	 * Boolean activant la RG-Utilisateur-Civilite-03 : 
	 * "la civilite de l'Utilisateur ne doit pas excéder 15 caractères".<br/>
	 */
	private static Boolean validerRGUtilisateurCiviliteLongueur03;
	
	/**
	 * Boolean activant la RG-Utilisateur-Civilite-04 : 
	 * "la civilite de l'Utilisateur 
	 * doit se conformer à une nomenclature".<br/>
	 */
	private static Boolean validerRGUtilisateurCiviliteNomenclature04;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereGestionnairePreferencesRG.class);
	
	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation<br/>
	 */
	private UtilisateurCerbereGestionnairePreferencesRG() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * <b>sauvegarde sur disque un fichier 
	 * UtilisateurCerbere_RG.properties initial</b> alimenté par des 
	 * propriétés [clé-valeur] écrites en dur 
	 * dans la présente classe.<br/>
	 * <ul>
	 * <li>remplit le Properties Java <code>preferences</code> 
	 * avec des [clé-valeur] stockées en dur dans la classe.</li>
	 * <li>instancie tous les attributs de la classe.</li>
	 * <ul>
	 * <li>instancie pathAbsoluPreferencesProperties si nécessaire.</li>
	 * <li>instancie filePreferencesProperties si nécessaire.</li>
	 * <li>Crée sur le disque dur l'arborescence et le fichier 
	 * filePreferencesProperties VIDE si nécessaire.</li>
	 * <li>lit dans un template le commentaire à ajouter au début du 
	 * UtilisateurCerbere_RG.properties et le stocke dans commentaire.</li>
	 * </ul>
	 * <li>remplit le fichier <code>filePreferencesProperties</code> 
	 * (UtilisateurCerbere_RG.properties) 
	 * avec le contenu de <code>preferences</code> 
	 * ([clé-valeur] stockées en dur dans la classe).</li>
	 * <li>Ecrit en UTF8 le Properties <code>preferences</code> dans 
	 * le File <code>filePreferencesProperties</code> 
	 * modélisant le fichier UtilisateurCerbere_RG.properties en positionnant 
	 * le <code>commentaire</code> au dessus.</li>
	 * <li>Utilise <code>preferences.store(writer, commentaire);</code> 
	 * avec un try-with-resource.</li>
	 * <li>ré-écrit (écrase) tout le fichier à chaque appel.</li>
	 * <li>trace EX_TEC_INITIALISATION_07.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	private static void creerFichierPropertiesInitial() 
											throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
			/* remplit le Properties Java <code>preferences</code> 
			 * avec des [clé-valeur] stockées en dur dans la classe. */
			ajouterPropertiesEnDur();
			
			/* instancie pathAbsoluPreferencesProperties si nécessaire. */
			/* instancie filePreferencesProperties si nécessaire. */
			/* Crée sur le disque dur l'arborescence et le fichier 
			 * filePreferencesProperties VIDE si nécessaire.*/
			/* lit dans un template le commentaire à ajouter au début du 
			 * UtilisateurCerbere_RG.properties et le stocke 
			 * dans commentaire.*/
			instancierAttributsFichierProperties();
			
			/* ECRITURE SUR DISQUE. */
			/* try-with-resource qui se charge du close(). */
			try (Writer writer = Files.newBufferedWriter(
					pathAbsoluPreferencesProperties, CHARSET_UTF8)) {
				
				/* enregistre le Properties preferences sur disque dur 
				 * dans le fichier preferences.properties correspondant. */
				preferences.store(writer, commentaire);
				
			}
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de creerFichierPropertiesInitial().___________________________

	

	/**
	 * <b>Ajoute des propriétés initiales stockées en dur</b> 
	 * dans la classe au Properties <b>preferences</b>.<br/>
	 * <ul>
	 * civilite
	 * <li>ajoute le validerRGUtilisateurCivilite 
	 * par défaut stocké en dur.</li>
	 * <li>ajoute le validerRGUtilisateurCiviliteRenseigne01 
	 * par défaut stockée en dur.</li>
	 * <li>ajoute le validerRGUtilisateurCiviliteLitteral02 
	 * par défaut stockée en dur.</li>
	 * <li>ajoute le validerRGUtilisateurCiviliteLongueur03 
	 * par défaut stockée en dur.</li>
	 * <li>ajoute le validerRGUtilisateurCiviliteNomenclature04 
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
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
			/* ajoute le validerRGUtilisateurCivilite 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_UTILISATEUR_CIVILITE
						, STRING_VALIDER_UTILISATEUR_CIVILITE_EN_DUR);
			
			/* ajoute le validerRGUtilisateurCiviliteRenseigne01 
			 * par défaut stockée en dur.*/
			preferences.setProperty(
					KEY_VALIDER_UTILISATEUR_CIVILITE_RENSEIGNE_01
						, STRING_VALIDER_UTILISATEUR_CIVILITE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGUtilisateurCiviliteLitteral02 
			 * par défaut stockée en dur.*/
			preferences.setProperty(
					KEY_VALIDER_UTILISATEUR_CIVILITE_LITTERAL_02
						, STRING_VALIDER_UTILISATEUR_CIVILITE_LITTERAL_02_EN_DUR);
			
			/* ajoute le validerRGUtilisateurCiviliteLongueur03 
			 * par défaut stockée en dur.*/
			preferences.setProperty(
					KEY_VALIDER_UTILISATEUR_CIVILITE_LONGUEUR_03
						, STRING_VALIDER_UTILISATEUR_CIVILITE_LONGUEUR_03_EN_DUR);
			
			/* ajoute le validerRGUtilisateurCiviliteNomenclature04 
			 * par défaut stockée en dur.*/
			preferences.setProperty(
					KEY_VALIDER_UTILISATEUR_CIVILITE_NOMENCLATURE_04
						, STRING_VALIDER_UTILISATEUR_CIVILITE_NOMENCLATURE_04_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de ajouterProperties()._______________________________________
	

	
	/**
	 * <b>Instancie tous les attributs</b> relatifs 
	 * au fichier de preferences <b>si ils sont null</b>.<br/>
	 * <b>Crée le fichier UtilisateurCerbere_RG.properties VIDE</b> 
	 * (et son arborescence) 
	 * sur HDD <b>si il n'existe pas déjà</b>.<br/>
	 * <ul>
	 * <li>instancie pathAbsoluPreferencesProperties si nécessaire.</li>
	 * <li>instancie filePreferencesProperties si nécessaire.</li>
	 * <li>Crée sur le disque dur l'arborescence et le fichier 
	 * filePreferencesProperties VIDE si nécessaire.</li>
	 * <li>lit dans un template le commentaire à ajouter au début du 
	 * UtilisateurCerbere_RG.properties et le stocke dans commentaire.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void instancierAttributsFichierProperties() 
			throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
			/* instancie pathAbsoluPreferencesProperties si nécessaire. */
			instancierPathAbsoluPreferencesProperties();
			
			/* instancie filePreferencesProperties si nécessaire. */
			if (filePreferencesProperties == null) {
				
				filePreferencesProperties 
					= pathAbsoluPreferencesProperties.toFile();
				
				/* Crée sur le disque dur l'arborescence et le fichier 
				 * filePreferencesProperties VIDE si nécessaire.*/
				if (!filePreferencesProperties.exists()) {
					creerRepertoiresArbo(filePreferencesProperties);
					Files.createFile(pathAbsoluPreferencesProperties);
				}				
			}
			
			/* lit dans un template le commentaire à ajouter au début du 
			 * UtilisateurCerbere_RG.properties et le stocke 
			 * dans commentaire.*/
			if (commentaire == null) {
				commentaire 
					= lireTemplateString(cheminRelatifTemplateCommentaire);
			}
						
		} // Fin du bloc synchronized.__________________
		
	} // Fin de instancierAttributsFichierProperties().____________________
	

	
	/**
	 * instancie pathAbsoluPreferencesProperties.<br/>
	 * <ul>
	 * <li>ne fait rien si pathAbsoluPreferencesProperties 
	 * n'est pas null.</li>
	 * <li>obtient le path des ressources externes auprès 
	 * du ConfigurationApplicationManager.</li>
	 * <li>calcule le path du UtilisateurCerbere_RG.properties 
	 * via un resolve par rapport au path des ressources externes.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void instancierPathAbsoluPreferencesProperties() 
			throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
			/* ne fait rien si pathAbsoluPreferencesProperties 
			 * n'est pas null. */
			if (pathAbsoluPreferencesProperties == null) {
				
				/* obtient le path du properties dans les 
				 * ressources externes auprès du 
				 * ConfigurationApplicationManager. */
				final Path pathRessourcesExternes 
				= Paths.get(ConfigurationApplicationManager
						.getPathRessourcesExternes());
				
				/* calcule le path du UtilisateurCerbere_RG.properties 
				 * via un resolve par rapport au path 
				 * des ressources externes. */
				pathAbsoluPreferencesProperties 
				= pathRessourcesExternes
					.resolve("preferences/metier/utilisateurs/UtilisateurCerbere_RG.properties")
						.toAbsolutePath()
							.normalize();
			}

		} // Fin du bloc synchronized.__________________
			
	} // Fin de instancierPathAbsoluPreferencesProperties()._______________
	
	
	
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
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
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
	 * <b>lit le fichier <code>
	 * ressources_externes/preferences/metier/utilisateurs/
	 * UtilisateurCerbere_RG.properties</b></code> 
	 * et alimente le <i>Properties</i> <b>preferences</b> 
	 * en le décodant en UTF8.<br/>
	 * <ul>
	 * <li>initialise les attributs relatifs 
	 * aux fichiers preferences.</li>
	 * <li>décode le fichier .properties en UTF8 et le charge 
	 * dans le Properties preferences.</li>
	 * <li><code>preferences.load(inputStream);</code></li>
	 * <li>trace EX_TEC_PARAMETRAGE_02.</li>
	 * </ul>
	 * @throws Exception 
	 */
	private static void lireFichierPreferencesProperties() 
												throws Exception {

		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
			/* initialise les attributs relatifs aux fichiers preferences. */
			instancierAttributsFichierProperties();
					
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
	 * <b>Enregistre en UTF8</b> le <i>Properties</i> preferences dans 
	 * le <i>fichier</i> 
	 * <code><b>ressources_externes/preferences/metier/utilisateurs/
	 * UtilisateurCerbere_RG.properties</b></code>.<br/>
	 * <ul>
	 * <li>initialise le <i>Properties</i> <b>preferences</b> 
	 * et remplit le <i>fichier</i> .properties si nécessaire.</li>
	 * <li>enregistre le <i>Properties</i> <b>preferences</b> 
	 * sur disque dur dans le <i>fichier</i> 
	 * .properties correspondant.</li>
	 * <li>ajoute le commentaire au début de preferences.properties.</li>
	 * <li>Prise en compte (stockage) 
	 * d'une modification d'une Property.</li>
	 * <li><code>preferences.store(writer, null);</code></li>
	 * <li>trace EX_FONCT_MEMORISATION_05.</li>
	 * <li>trace EX_TEC_MEMORISATION_06.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	private static void enregistrerFichierPreferencesProperties() 
			throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
			/* crée le Properties preferences et 
			 * le remplit avec des valeurs en dur si nécessaire. */
			if (filePreferencesProperties == null 
					|| !filePreferencesProperties.exists()) {
				creerFichierPropertiesInitial();
			}
			
			/* initialise les fichiers preferences si nécessaire. */
			instancierAttributsFichierProperties();
			
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
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
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
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
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
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
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
				loggerError(CLASSE_UTILISATEURCERBERE_GESTIONNAIRE_PREFS_RG
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
				loggerError(CLASSE_UTILISATEURCERBERE_GESTIONNAIRE_PREFS_RG
						, METHODE_LIRE_STRINGS_DANS_FILE
						, ioe);
				
				final String message 
				= CLASSE_UTILISATEURCERBERE_GESTIONNAIRE_PREFS_RG 
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
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
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
	 * retourne la valeur du Boolean pAttribut dans le fichier properties.<br/>
	 *
	 * @param pAttribut
	 * @param pFournirKey
	 * @param pValeurEnDur
	 * @return Boolean
	 * 
	 * @throws Exception
	 */
	private static Boolean fournirAttribut(
			Boolean pAttribut
				, final String pFournirKey
					, final String pValeurEnDur) 
			throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
			instancierAttributsFichierProperties();
			
			/* crée le Properties preferences et 
			 * le remplit avec des valeurs en dur si nécessaire. */
			if (filePreferencesProperties == null 
					|| !filePreferencesProperties.exists() 
						|| filePreferencesProperties.length() == 0) {
				creerFichierPropertiesInitial();
			}
						
			/* lit le fichier properties et alimente preferences. */
			lireFichierPreferencesProperties();
						
			if (pAttribut == null) {
				
				/* lecture dans le properties. */
				final String valeurStringSale 
					= preferences
						.getProperty(pFournirKey);
				
				String valeurString = null;
				
				if (!StringUtils.isBlank(valeurStringSale)) {
					
					/* nettoyage de la valeur lue dans le properties. */
					valeurString 
						= valeurStringSale.trim();
					
					try {
						
						pAttribut 
							= Boolean.parseBoolean(valeurString);
						
					} catch (Exception e) {
						
						pAttribut 
							= Boolean.parseBoolean(pValeurEnDur);
						
					}
					
				}
				else {
					
					pAttribut 
						= Boolean.parseBoolean(pValeurEnDur);
				}
			}
			
			return pAttribut;
			
		}
		
	}
	

	
	/**
	 * .<br/>
	 *
	 * @param pValue
	 * @param pAttribut
	 * @param pFournirKey
	 * 
	 * @throws Exception
	 */
	private static void setterAttribut(
			final Boolean pValue
				, Boolean pAttribut
					, final String pFournirKey) throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
			/* ne fait rien si le paramètre est null
			 * ou ne modifie pas la valeur existante. */
			if (pValue != null 
					&& !pValue
						.equals(pAttribut)) {
				
				pAttribut = pValue;
				
				final String valeurString 
					= pAttribut.toString();
				
				/* crée le Properties preferences et le fichier 
				 * UtilisateurCerbere_RG.properties
				 * et les remplit avec des valeurs en dur si nécessaire. */
				if (filePreferencesProperties == null 
						|| !filePreferencesProperties.exists()) {
					creerFichierPropertiesInitial();
				}
				
				/* modifie preferences avec la nouvelle valeur 
				 * passée dans le setter. */
				creerOuModifierProperty(
						pFournirKey
							, valeurString);
				
				/* ré-écrit entièrement le fichier 
				 * UtilisateurCerbere_RG.properties mis à jour. */
				enregistrerFichierPreferencesProperties();

			}

		} // Fin du bloc synchronized.__________________
						
	}


	
	/**
	 * fournit une String pour l'affichage de preferences.properties.<br/>
	 * <ul>
	 * <li>crée le fichier preferences.properties et alimente 
	 * le Properties preferences avec des valeurs en dur 
	 * si preferences est vide.</li>
	 * <li>trace EX_FONCT_PARAMETRAGE_01</li>
	 * </ul>
	 *
	 * @return : String.<br/>
	 * @throws Exception 
	 */
	public static String afficherPreferences() throws Exception {

		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
			/* crée le fichier preferences.properties et alimente 
			 * le Properties preferences avec des valeurs en dur 
			 * si preferences est vide. */
			if (preferences.isEmpty()) {
				creerFichierPropertiesInitial();
			}
						
			final StringBuffer stb = new StringBuffer();
			
			for (final String key : preferences.stringPropertyNames()) {
				stb.append(key);
				stb.append(EGAL);
				stb.append(preferences.getProperty(key));
				stb.append(SAUT_LIGNE_JAVA);
			}
			
			return stb.toString();

		} // Fin du bloc synchronized.__________________
		
	} // Fin de afficherPreferences()._____________________________________
	

	
	/**
	 * <b>Crée ou met à jour une Property</b> dans 
	 * le <i>Properties</i> <b>preferences</b>.<br/>
	 * <ul>
	 * <li>initialise le <i>Properties</i> <b>preferences</b> 
	 * et remplit le <i>fichier</i> .properties si nécessaire.</li>
	 * <li>Crée ou maj dans l'objet Properties <b>preferences</b> 
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
	public static boolean creerOuModifierProperty(
			final String pKey
				, final String pValue) throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
			/* crée le Properties preferences et 
			 * le remplit avec des valeurs en dur si nécessaire. */
			if (filePreferencesProperties == null 
					|| !filePreferencesProperties.exists()) {
				creerFichierPropertiesInitial();
			}
			
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
	 * <li>initialise le <i>Properties</i> <b>preferences</b> 
	 * et remplit le <i>fichier</i> .properties si nécessaire.</li>
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
	public static boolean retirerProperty(
			final String pKey) 
					throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
			/* crée le Properties preferences et 
			 * le remplit avec des valeurs en dur si nécessaire. */
			if (filePreferencesProperties == null 
					|| !filePreferencesProperties.exists()) {
				creerFichierPropertiesInitial();
			}
			
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
	 * <li>initialise le <i>Properties</i> <b>preferences</b> 
	 * et remplit le <i>fichier</i> .properties si nécessaire.</li>
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
	public static boolean viderPreferences() throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
			/* crée le Properties preferences et 
			 * le remplit avec des valeurs en dur si nécessaire. */
			if (filePreferencesProperties == null 
					|| !filePreferencesProperties.exists()) {
				creerFichierPropertiesInitial();
			}
				
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
	 * Getter du commentaire à ajouter en haut du fichier properties.<br/>
	 * <br/>
	 *
	 * @return commentaire : String.<br/>
	 */
	public static String getCommentaire() {
		return commentaire;
	} // Fin de getCommentaire().__________________________________________
	
	
	
	/**
	* Setter du commentaire à ajouter en haut du fichier properties.<br/>
	* <br/>
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
	 * au dessus de UtilisateurCerbere_RG.properties.<br/>
	 * "commentaires_properties/commentaires_preferences_properties.txt"
	 * <br/>
	 *
	 * @return cheminRelatifTemplateCommentaire : String.<br/>
	 */
	public static String getCheminRelatifTemplateCommentaire() {
		return cheminRelatifTemplateCommentaire;
	} // Fin de getCheminRelatifTemplateCommentaire()._____________________



	/**
	 * Getter du Properties encapsulant les préférences.<br/>
	 * SINGLETON.<br/>
	 * <ul>
	 * <li>crée le fichier UtilisateurCerbere_RG.properties et alimente 
	 * le Properties preferences avec des valeurs en dur 
	 * si preferences est vide.</li>
	 * <li>trace EX_FONCT_PARAMETRAGE_01</li>
	 * </ul>
	 *
	 * @return preferences : Properties.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Properties getPreferences() throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
			/* crée le fichier UtilisateurCerbere_RG.properties et alimente 
			 * le Properties preferences avec des valeurs en dur 
			 * si preferences est vide. */
			if (preferences.isEmpty()) {
				creerFichierPropertiesInitial();
			}
			
			return preferences;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de getPreferences().__________________________________________


		
	/**
	 * Getter du Path absolu vers UtilisateurCerbere_RG.properties.<br/>
	 * SINGLETON.<br/>
	 *
	 * @return pathAbsoluPreferencesProperties : Path.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Path getPathAbsoluPreferencesProperties() 
											throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
			if (pathAbsoluPreferencesProperties == null) {
				instancierAttributsFichierProperties();
			}
			
			return pathAbsoluPreferencesProperties;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de getPathAbsoluPreferencesProperties().______________________


		
	/**
	 * Getter de la  Modélisation Java du fichier 
	 * UtilisateurCerbere_RG.properties.<br/>
	 * SINGLETON.<br/>
	 *
	 * @return filePreferencesProperties : File.<br/>
	 * 
	 * @throws Exception 
	 */
	public static File getFilePreferencesProperties() throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
			if (filePreferencesProperties == null) {
				creerFichierPropertiesInitial();
			}
			
			return filePreferencesProperties;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de getFilePreferencesProperties().____________________________
	
	
	
	/**
	 * retourne le <code>validerRGUtilisateurCivilite</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur la <i>civilite</i> de l'utilisateur.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de la <i>civilité</i> 
	 * de l'Utilisateur.<br/>
	 * <ul>
	 * <li>lit le validerRGUtilisateurCivilite stocké 
	 * dans UtilisateurCerbere_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>false sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (false) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGUtilisateurCivilite 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGUtilisateurCivilite() 
			throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
//			/* instancie les attributs de fichier si nécessaire. */
//			/* alimente Properties avec le contenu 
//			 * du fichier properties. */
//			lireFichierPreferencesProperties();
//			
//			/* crée le Properties preferences et 
//			 * le remplit avec des valeurs en dur si nécessaire. */
//			if (filePreferencesProperties == null 
//					|| !filePreferencesProperties.exists()) {
//				creerFichierPropertiesInitial();
//			}
//			
//			if (validerRGUtilisateurCivilite == null) {
//				
//				/* lecture dans le properties. */
//				final String valeurString 
//					= preferences
//						.getProperty(
//								fournirKeyValiderRGUtilisateurCivilite())
//									.trim();
//				
//				if (!StringUtils.isBlank(valeurString)) {
//					
//					try {
//						
//						validerRGUtilisateurCivilite 
//							= Boolean.parseBoolean(valeurString);
//						
//					} catch (Exception e) {
//						
//						validerRGUtilisateurCivilite 
//							= Boolean.parseBoolean(
//								STRING_VALIDER_UTILISATEUR_CIVILITE_EN_DUR);
//						
//					}
//					
//				}
//				else {
//					
//					validerRGUtilisateurCivilite 
//						= Boolean.parseBoolean(
//						STRING_VALIDER_UTILISATEUR_CIVILITE_EN_DUR);
//				}
//			}
//			
//			return validerRGUtilisateurCivilite;
			
			return fournirAttribut(
					validerRGUtilisateurCivilite
					, fournirKeyValiderRGUtilisateurCivilite()
					, STRING_VALIDER_UTILISATEUR_CIVILITE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGUtilisateurCivilite()._____________________
	

	
	/**
	 * Getter de la clé du validerRGUtilisateurCivilite 
	 * par défaut de l'application 
	 * dans UtilisateurCerbere_RG.properties.<br/>
	 * "valider.UtilisateurCerbere.civilite".<br/>
	 *
	 * @return KEY_VALIDER_UTILISATEUR_CIVILITE : String.<br/>
	 */
	public static String fournirKeyValiderRGUtilisateurCivilite() {
		return KEY_VALIDER_UTILISATEUR_CIVILITE;
	} // Fin de fournirKeyValiderRGUtilisateurCivilite().__________________



	/**
	 * Getter du <b>SINGLETON de validerRGUtilisateurCivilite par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGUtilisateurCivilite 
	 * stocké dans UtilisateurCerbere_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>false sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGUtilisateurCivilite stocké en dur 
	 * dans la classe (false) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGUtilisateurCivilite : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGUtilisateurCivilite() 
													throws Exception {
		return fournirValiderRGUtilisateurCivilite();
	} // Fin de getValiderRGUtilisateurCivilite()._________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGUtilisateurCivilite par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* UtilisateurCerbere_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier UtilisateurCerbere_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGUtilisateurCivilite.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGUtilisateurCivilite(
			final Boolean pValue) throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
//			/* ne fait rien si le paramètre est null
//			 * ou ne modifie pas la valeur existante. */
//			if (pValue != null 
//					&& !pValue
//						.equals(validerRGUtilisateurCivilite)) {
//				
//				validerRGUtilisateurCivilite = pValue;
//				
//				final String valeurString 
//					= validerRGUtilisateurCivilite.toString();
//				
//				/* crée le Properties preferences et le fichier 
//				 * UtilisateurCerbere_RG.properties
//				 * et les remplit avec des valeurs en dur si nécessaire. */
//				if (filePreferencesProperties == null 
//						|| !filePreferencesProperties.exists()) {
//					creerFichierPropertiesInitial();
//				}
//				
//				/* modifie preferences avec la nouvelle valeur 
//				 * passée dans le setter. */
//				creerOuModifierProperty(
//						fournirKeyValiderRGUtilisateurCivilite()
//							, valeurString);
//				
//				/* ré-écrit entièrement le fichier 
//				 * UtilisateurCerbere_RG.properties mis à jour. */
//				enregistrerFichierPreferencesProperties();
//
//			}
			
			setterAttribut(
					pValue
						, validerRGUtilisateurCivilite
							, fournirKeyValiderRGUtilisateurCivilite());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderCiviliteUtilisateur(...).________________________



	/**
	 * retourne le validerRGUtilisateurCiviliteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGUtilisateurCiviliteRenseigne01 stocké 
	 * dans UtilisateurCerbere_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>false sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (false) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGUtilisateurCiviliteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGUtilisateurCiviliteRenseigne01() 
			throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
//			/* instancie les attributs de fichier si nécessaire. */
//			/* alimente Properties avec le contenu 
//			 * du fichier properties. */
//			lireFichierPreferencesProperties();
//			
//			/* crée le Properties preferences et 
//			 * le remplit avec des valeurs en dur si nécessaire. */
//			if (filePreferencesProperties == null 
//					|| !filePreferencesProperties.exists()) {
//				creerFichierPropertiesInitial();
//			}
//			
//			if (validerRGUtilisateurCiviliteRenseigne01 == null) {
//				
//				/* lecture dans le properties. */
//				final String valeurString 
//					= preferences
//						.getProperty(
//								fournirKeyValiderRGUtilisateurCiviliteRenseigne01())
//									.trim();
//				
//				if (!StringUtils.isBlank(
//						valeurString)) {
//					
//					try {
//						
//						validerRGUtilisateurCiviliteRenseigne01 
//							= Boolean.parseBoolean(
//									valeurString);
//						
//					} catch (Exception e) {
//						
//						validerRGUtilisateurCiviliteRenseigne01 
//							= Boolean.parseBoolean(
//								STRING_VALIDER_UTILISATEUR_CIVILITE_RENSEIGNE_01_EN_DUR);
//						
//					}
//					
//				}
//				else {
//					
//					validerRGUtilisateurCiviliteRenseigne01 
//						= Boolean.parseBoolean(
//								STRING_VALIDER_UTILISATEUR_CIVILITE_RENSEIGNE_01_EN_DUR);
//				}
//			}
//			
//			return validerRGUtilisateurCiviliteRenseigne01;
			
			return fournirAttribut(
					validerRGUtilisateurCiviliteRenseigne01
					, fournirKeyValiderRGUtilisateurCiviliteRenseigne01()
					, STRING_VALIDER_UTILISATEUR_CIVILITE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGUtilisateurCiviliteRenseigne01().__________
	

	
	/**
	 * Getter de la clé du validerRGUtilisateurCiviliteRenseigne01 
	 * par défaut de l'application 
	 * dans UtilisateurCerbere_RG.properties.<br/>
	 * "valider.UtilisateurCerbere.civilite.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_UTILISATEUR_CIVILITE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGUtilisateurCiviliteRenseigne01() {
		return KEY_VALIDER_UTILISATEUR_CIVILITE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGUtilisateurCiviliteRenseigne01()._______



	/**
	 * Getter du <b>SINGLETON de validerRGUtilisateurCiviliteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGUtilisateurCiviliteRenseigne01 
	 * stocké dans UtilisateurCerbere_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>false sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGUtilisateurCiviliteRenseigne01 stocké en dur 
	 * dans la classe (false) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGUtilisateurCiviliteRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGUtilisateurCiviliteRenseigne01() 
													throws Exception {
		return fournirValiderRGUtilisateurCiviliteRenseigne01();
	} // Fin de getValiderRGUtilisateurCiviliteRenseigne01().______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGUtilisateurCiviliteRenseigne01 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* UtilisateurCerbere_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier UtilisateurCerbere_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGUtilisateurCiviliteRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGUtilisateurCiviliteRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
//			/* ne fait rien si le paramètre est null
//			 * ou ne modifie pas la valeur existante. */
//			if (pValue != null 
//					&& !pValue
//						.equals(validerRGUtilisateurCiviliteRenseigne01)) {
//				
//				validerRGUtilisateurCiviliteRenseigne01 = pValue;
//				
//				final String valeurString 
//					= validerRGUtilisateurCiviliteRenseigne01.toString();
//				
//				/* crée le Properties preferences et le fichier 
//				 * UtilisateurCerbere_RG.properties
//				 * et les remplit avec des valeurs en dur si nécessaire. */
//				if (filePreferencesProperties == null 
//						|| !filePreferencesProperties.exists()) {
//					creerFichierPropertiesInitial();
//				}
//				
//				/* modifie preferences avec la nouvelle valeur 
//				 * passée dans le setter. */
//				creerOuModifierProperty(
//						fournirKeyValiderRGUtilisateurCiviliteRenseigne01()
//							, valeurString);
//				
//				/* ré-écrit entièrement le fichier 
//				 * UtilisateurCerbere_RG.properties mis à jour. */
//				enregistrerFichierPreferencesProperties();
//
//			}

			setterAttribut(
					pValue
						, validerRGUtilisateurCiviliteRenseigne01
							, fournirKeyValiderRGUtilisateurCiviliteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGUtilisateurCiviliteRenseigne01(...).___________



	/**
	 * retourne le validerRGUtilisateurCiviliteLitteral02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGUtilisateurCiviliteLitteral02 stocké 
	 * dans UtilisateurCerbere_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>false sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (false) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGUtilisateurCiviliteLitteral02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGUtilisateurCiviliteLitteral02() 
			throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
//			/* instancie les attributs de fichier si nécessaire. */
//			/* alimente Properties avec le contenu 
//			 * du fichier properties. */
//			lireFichierPreferencesProperties();
//			
//			/* crée le Properties preferences et 
//			 * le remplit avec des valeurs en dur si nécessaire. */
//			if (filePreferencesProperties == null 
//					|| !filePreferencesProperties.exists()) {
//				creerFichierPropertiesInitial();
//			}
//			
//			if (validerRGUtilisateurCiviliteLitteral02 == null) {
//				
//				/* lecture dans le properties. */
//				final String valeurString 
//					= preferences
//						.getProperty(
//								fournirKeyValiderRGUtilisateurCiviliteLitteral02())
//									.trim();
//				
//				if (!StringUtils.isBlank(
//						valeurString)) {
//					
//					try {
//						
//						validerRGUtilisateurCiviliteLitteral02 
//							= Boolean.parseBoolean(
//									valeurString);
//						
//					} catch (Exception e) {
//						
//						validerRGUtilisateurCiviliteLitteral02 
//							= Boolean.parseBoolean(
//								STRING_VALIDER_UTILISATEUR_CIVILITE_LITTERAL_02_EN_DUR);
//						
//					}
//					
//				}
//				else {
//					
//					validerRGUtilisateurCiviliteLitteral02 
//						= Boolean.parseBoolean(
//								STRING_VALIDER_UTILISATEUR_CIVILITE_LITTERAL_02_EN_DUR);
//				}
//			}
//			
//			return validerRGUtilisateurCiviliteLitteral02;
			
			return fournirAttribut(
					validerRGUtilisateurCiviliteLitteral02
					, fournirKeyValiderRGUtilisateurCiviliteLitteral02()
					, STRING_VALIDER_UTILISATEUR_CIVILITE_LITTERAL_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGUtilisateurCiviliteLitteral02().___________
	

	
	/**
	 * Getter de la clé du validerRGUtilisateurCiviliteLitteral02 
	 * par défaut de l'application 
	 * dans UtilisateurCerbere_RG.properties.<br/>
	 * "valider.UtilisateurCerbere.civilite.litteral".<br/>
	 *
	 * @return KEY_VALIDER_UTILISATEUR_CIVILITE_LITTERAL_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGUtilisateurCiviliteLitteral02() {
		return KEY_VALIDER_UTILISATEUR_CIVILITE_LITTERAL_02;
	} // Fin de fournirKeyValiderRGUtilisateurCiviliteLitteral02().________



	/**
	 * Getter du <b>SINGLETON de validerRGUtilisateurCiviliteLitteral02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGUtilisateurCiviliteLitteral02 
	 * stocké dans UtilisateurCerbere_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>false sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGUtilisateurCiviliteLitteral02 stocké en dur 
	 * dans la classe (false) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGUtilisateurCiviliteLitteral02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGUtilisateurCiviliteLitteral02() 
													throws Exception {
		return fournirValiderRGUtilisateurCiviliteLitteral02();
	} // Fin de getValiderRGUtilisateurCiviliteLitteral02()._______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGUtilisateurCiviliteLitteral02 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* UtilisateurCerbere_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier UtilisateurCerbere_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGUtilisateurCiviliteLitteral02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGUtilisateurCiviliteLitteral02(
			final Boolean pValue) throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
//			/* ne fait rien si le paramètre est null
//			 * ou ne modifie pas la valeur existante. */
//			if (pValue != null 
//					&& !pValue
//						.equals(validerRGUtilisateurCiviliteLitteral02)) {
//				
//				validerRGUtilisateurCiviliteLitteral02 = pValue;
//				
//				final String valeurString 
//					= validerRGUtilisateurCiviliteLitteral02.toString();
//				
//				/* crée le Properties preferences et le fichier 
//				 * UtilisateurCerbere_RG.properties
//				 * et les remplit avec des valeurs en dur si nécessaire. */
//				if (filePreferencesProperties == null 
//						|| !filePreferencesProperties.exists()) {
//					creerFichierPropertiesInitial();
//				}
//				
//				/* modifie preferences avec la nouvelle valeur 
//				 * passée dans le setter. */
//				creerOuModifierProperty(
//						fournirKeyValiderRGUtilisateurCiviliteLitteral02()
//							, valeurString);
//				
//				/* ré-écrit entièrement le fichier 
//				 * UtilisateurCerbere_RG.properties mis à jour. */
//				enregistrerFichierPreferencesProperties();
//
//			}

			setterAttribut(
					pValue
						, validerRGUtilisateurCiviliteLitteral02
							, fournirKeyValiderRGUtilisateurCiviliteLitteral02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGUtilisateurCiviliteLitteral02(...).____________



	/**
	 * retourne le validerRGUtilisateurCiviliteLongueur03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGUtilisateurCiviliteLongueur03 stocké 
	 * dans UtilisateurCerbere_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>false sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (false) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGUtilisateurCiviliteLongueur03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGUtilisateurCiviliteLongueur03() 
			throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
//			/* instancie les attributs de fichier si nécessaire. */
//			/* alimente Properties avec le contenu 
//			 * du fichier properties. */
//			lireFichierPreferencesProperties();
//			
//			/* crée le Properties preferences et 
//			 * le remplit avec des valeurs en dur si nécessaire. */
//			if (filePreferencesProperties == null 
//					|| !filePreferencesProperties.exists()) {
//				creerFichierPropertiesInitial();
//			}
//			
//			if (validerRGUtilisateurCiviliteLongueur03 == null) {
//				
//				/* lecture dans le properties. */
//				final String valeurString 
//					= preferences
//						.getProperty(
//								fournirKeyValiderRGUtilisateurCiviliteLongueur03())
//									.trim();
//				
//				if (!StringUtils.isBlank(
//						valeurString)) {
//					
//					try {
//						
//						validerRGUtilisateurCiviliteLongueur03 
//							= Boolean.parseBoolean(
//									valeurString);
//						
//					} catch (Exception e) {
//						
//						validerRGUtilisateurCiviliteLongueur03 
//							= Boolean.parseBoolean(
//								STRING_VALIDER_UTILISATEUR_CIVILITE_LONGUEUR_03_EN_DUR);
//						
//					}
//					
//				}
//				else {
//					
//					validerRGUtilisateurCiviliteLongueur03 
//						= Boolean.parseBoolean(
//								STRING_VALIDER_UTILISATEUR_CIVILITE_LONGUEUR_03_EN_DUR);
//				}
//			}
//			
//			return validerRGUtilisateurCiviliteLongueur03;
			
			return fournirAttribut(
					validerRGUtilisateurCiviliteLongueur03
					, fournirKeyValiderRGUtilisateurCiviliteLongueur03()
					, STRING_VALIDER_UTILISATEUR_CIVILITE_LONGUEUR_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirvaliderRGUtilisateurCiviliteLongueur03().___________
	

	
	/**
	 * Getter de la clé du validerRGUtilisateurCiviliteLongueur03 
	 * par défaut de l'application 
	 * dans UtilisateurCerbere_RG.properties.<br/>
	 * "valider.UtilisateurCerbere.civilite.longueur".<br/>
	 *
	 * @return KEY_VALIDER_UTILISATEUR_CIVILITE_LONGUEUR_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGUtilisateurCiviliteLongueur03() {
		return KEY_VALIDER_UTILISATEUR_CIVILITE_LONGUEUR_03;
	} // Fin de fournirKeyValiderRGUtilisateurCiviliteLongueur03().________



	/**
	 * Getter du <b>SINGLETON de validerRGUtilisateurCiviliteLongueur03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGUtilisateurCiviliteLongueur03 
	 * stocké dans UtilisateurCerbere_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>false sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGUtilisateurCiviliteLongueur03 stocké en dur 
	 * dans la classe (false) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGUtilisateurCiviliteLongueur03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGUtilisateurCiviliteLongueur03() 
													throws Exception {
		return fournirValiderRGUtilisateurCiviliteLongueur03();
	} // Fin de getValiderRGUtilisateurCiviliteLongueur03()._______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGUtilisateurCiviliteLongueur03 
	* par défaut dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* UtilisateurCerbere_RG.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier UtilisateurCerbere_RG.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si le paramètre est null 
	* ou ne modifie pas la valeur existante.<br/>
	* <br/>
	*
	* @param pValue : Boolean : 
	* valeur à passer à validerRGUtilisateurCiviliteLongueur03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGUtilisateurCiviliteLongueur03(
			final Boolean pValue) throws Exception {
		
		synchronized (UtilisateurCerbereGestionnairePreferencesRG.class) {
			
//			/* ne fait rien si le paramètre est null
//			 * ou ne modifie pas la valeur existante. */
//			if (pValue != null 
//					&& !pValue
//						.equals(validerRGUtilisateurCiviliteLongueur03)) {
//				
//				validerRGUtilisateurCiviliteLongueur03 = pValue;
//				
//				final String valeurString 
//					= validerRGUtilisateurCiviliteLongueur03.toString();
//				
//				/* crée le Properties preferences et le fichier 
//				 * UtilisateurCerbere_RG.properties
//				 * et les remplit avec des valeurs en dur si nécessaire. */
//				if (filePreferencesProperties == null 
//						|| !filePreferencesProperties.exists()) {
//					creerFichierPropertiesInitial();
//				}
//				
//				/* modifie preferences avec la nouvelle valeur 
//				 * passée dans le setter. */
//				creerOuModifierProperty(
//						fournirKeyValiderRGUtilisateurCiviliteLongueur03()
//							, valeurString);
//				
//				/* ré-écrit entièrement le fichier 
//				 * UtilisateurCerbere_RG.properties mis à jour. */
//				enregistrerFichierPreferencesProperties();
//
//			}

			setterAttribut(
					pValue
						, validerRGUtilisateurCiviliteLongueur03
							, fournirKeyValiderRGUtilisateurCiviliteLongueur03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGUtilisateurCiviliteLitteral02(...).____________

			
	
} // FIN DE LA CLASSE UtilisateurCerbereGestionnairePreferencesRG.-----------
