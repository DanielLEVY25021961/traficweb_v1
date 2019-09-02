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
 * Classe UTILITAIRE (<i>final avec toutes les méthodes static</i>) chargée de :
 * <ul>
 * <li><b>gérer les <i>Booleans</i> de validation des RG comme des "préférences"</b> 
 * (paramétrées 1 fois par l'Administrateur puis stockées 
 * dans un fichier .properties) qui conservent la même valeur 
 * tant qu'elles sont inchangées.</li>
 * <li>permettre à l'ADMINISTRATEUR DE DONNEES de la MOA 
 * de <b>modifier ces Booleans</b>.</li>
 * <li><b>gérer les préférences relatives aux REGLES DE GESTION (RG) applicables 
 * à un OBJET METIER : {@link SectionHit}</b>.</li>
 * <li>fournir à toute l'application des <b>SINGLETONS</b> des 
 * <b>Booleans de validation par défaut 
 * (actuellement stockés dans un .properties) des Règles de Gestion (RG)</b>
 *  d'un OBJET METIER pour chaque attribut et chaque RG de l'attribut</li>
 * <li>gérer comme des préférences les <b>booleens 
 * qui activent ou non les contrôles des RG</b>.</li>
 * <li><b>créer un fichier .properties initial</b> avec des valeurs (Booleans) 
 * codées en dur dans la classe en cas de défaut de livraison du 
 * <code><b>ressources_externes/preferences/metier/sections/
 * OBJETMETIER_RG.properties</b></code></li>
 * </ul>
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
	/* 1 - numDepartement. ***************/
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
	
	/* 2 - numSection. ************/
	/**
	 * clé de validerRGSectionHitNumSection dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numsection"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMSECTION 
		= "valider.SectionHit.numsection";
	
	/**
	 * validerRGSectionHitNumSection par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumSection 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMSECTION_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitNumSectionRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numsection.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMSECTION_RENSEIGNE_01 
		= "valider.SectionHit.numsection.renseigne";
	
	/**
	 * validerRGSectionHitNumSectionRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumSectionRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMSECTION_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitNumSectionRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numsection.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMSECTION_REGEX_02 
		= "valider.SectionHit.numsection.regex";
	
	/**
	 * validerRGSectionHitNumSectionRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumSectionRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMSECTION_REGEX_02_EN_DUR 
		= "true";
	
	/* 3 - sens. *******************/
	/**
	 * clé de validerRGSectionHitSens dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.sens"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_SENS 
		= "valider.SectionHit.sens";
	
	/**
	 * validerRGSectionHitSens par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitSens 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_SENS_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitSensRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.sens.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_SENS_RENSEIGNE_01 
		= "valider.SectionHit.sens.renseigne";
	
	/**
	 * validerRGSectionHitSensRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitSensRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_SENS_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitSensRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.sens.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_SENS_REGEX_02 
		= "valider.SectionHit.sens.regex";
	
	/**
	 * validerRGSectionHitSensRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitSensRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_SENS_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitSensNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.sens.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_SENS_NOMENCLATURE_03 
		= "valider.SectionHit.sens.nomenclature";
	
	/**
	 * validerRGSectionHitSensNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitSensNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_SENS_NOMENCLATURE_03_EN_DUR 
		= "true";
	
	/* 4 - nature. *******************/
	/**
	 * clé de validerRGSectionHitNature dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.nature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NATURE 
		= "valider.SectionHit.nature";
	
	/**
	 * validerRGSectionHitNature par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNature 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NATURE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitNatureRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.nature.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NATURE_RENSEIGNE_01 
		= "valider.SectionHit.nature.renseigne";
	
	/**
	 * validerRGSectionHitNatureRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNatureRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NATURE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitNatureRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.nature.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NATURE_REGEX_02 
		= "valider.SectionHit.nature.regex";
	
	/**
	 * validerRGSectionHitNatureRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNatureRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NATURE_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitNatureNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.nature.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NATURE_NOMENCLATURE_03 
		= "valider.SectionHit.nature.nomenclature";
	
	/**
	 * validerRGSectionHitNatureNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNatureNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NATURE_NOMENCLATURE_03_EN_DUR 
		= "true";
	
	/* 5 - classe. ****************/
	/**
	 * clé de validerRGSectionHitClasse dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classe"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSE 
		= "valider.SectionHit.classe";
	
	/**
	 * validerRGSectionHitClasse par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasse 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitClasseRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classe.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSE_RENSEIGNE_01 
		= "valider.SectionHit.classe.renseigne";
	
	/**
	 * validerRGSectionHitClasseRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitClasseRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classe.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSE_REGEX_02 
		= "valider.SectionHit.classe.regex";
	
	/**
	 * validerRGSectionHitClasseRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSE_REGEX_02_EN_DUR 
		= "true";
	
	/* 6 - anneeTraitement. *******/
	/**
	 * clé de validerRGSectionHitAnneeTraitement dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.anneeTraitement"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT 
		= "valider.SectionHit.anneeTraitement";
	
	/**
	 * validerRGSectionHitAnneeTraitement par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAnneeTraitement 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ANNEETRAITEMENT_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitAnneeTraitementRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.anneeTraitement.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01 
		= "valider.SectionHit.anneeTraitement.renseigne";
	
	/**
	 * validerRGSectionHitAnneeTraitementRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAnneeTraitementRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitAnneeTraitementRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.anneeTraitement.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT_REGEX_02 
		= "valider.SectionHit.anneeTraitement.regex";
	
	/**
	 * validerRGSectionHitAnneeTraitementRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAnneeTraitementRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ANNEETRAITEMENT_REGEX_02_EN_DUR 
		= "true";	
	
	/* 7 - zoneLibre1. *******/
	/**
	 * clé de validerRGSectionHitZoneLibre1 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.zoneLibre1"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ZONELIBRE1 
		= "valider.SectionHit.zoneLibre1";
	
	/**
	 * validerRGSectionHitZoneLibre1 par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitZoneLibre1 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ZONELIBRE1_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitZoneLibre1Renseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.zoneLibre1.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01 
		= "valider.SectionHit.zoneLibre1.renseigne";
	
	/**
	 * validerRGSectionHitZoneLibre1Renseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitZoneLibre1Renseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitZoneLibre1Regex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.zoneLibre1.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ZONELIBRE1_REGEX_02 
		= "valider.SectionHit.zoneLibre1.regex";
	
	/**
	 * validerRGSectionHitZoneLibre1Regex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitZoneLibre1Regex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ZONELIBRE1_REGEX_02_EN_DUR 
		= "true";
	
	/* 8 - numRoute. *******/
	/**
	 * clé de validerRGSectionHitNumRoute dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numRoute"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMROUTE 
		= "valider.SectionHit.numRoute";
	
	/**
	 * validerRGSectionHitNumRoute par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumRoute 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMROUTE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitNumRouteRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numRoute.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMROUTE_RENSEIGNE_01 
		= "valider.SectionHit.numRoute.renseigne";
	
	/**
	 * validerRGSectionHitNumRouteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumRouteRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMROUTE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitNumRouteRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numRoute.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMROUTE_REGEX_02 
		= "valider.SectionHit.numRoute.regex";
	
	/**
	 * validerRGSectionHitNumRouteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumRouteRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMROUTE_REGEX_02_EN_DUR 
		= "true";
	
	/* 9 - indiceNumRoute. *******/
	/**
	 * clé de validerRGSectionHitIndiceNumRoute dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.indiceNumRoute"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_INDICENUMROUTE 
		= "valider.SectionHit.indiceNumRoute";
	
	/**
	 * validerRGSectionHitIndiceNumRoute par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitIndiceNumRoute 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_INDICENUMROUTE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitIndiceNumRouteRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.indiceNumRoute.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01 
		= "valider.SectionHit.indiceNumRoute.renseigne";
	
	/**
	 * validerRGSectionHitIndiceNumRouteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitIndiceNumRouteRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitIndiceNumRouteRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.indiceNumRoute.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_INDICENUMROUTE_REGEX_02 
		= "valider.SectionHit.indiceNumRoute.regex";
	
	/**
	 * validerRGSectionHitIndiceNumRouteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitIndiceNumRouteRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_INDICENUMROUTE_REGEX_02_EN_DUR 
		= "true";
	
	/* 10 - indiceLettreRoute. *******/
	/**
	 * clé de validerRGSectionHitIndiceLettreRoute dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.indiceLettreRoute"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE 
		= "valider.SectionHit.indiceLettreRoute";
	
	/**
	 * validerRGSectionHitIndiceLettreRoute par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitIndiceLettreRoute 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_INDICELETTREROUTE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitIndiceLettreRouteRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.indiceLettreRoute.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01 
		= "valider.SectionHit.indiceLettreRoute.renseigne";
	
	/**
	 * validerRGSectionHitIndiceLettreRouteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitIndiceLettreRouteRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitIndiceLettreRouteRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.indiceLettreRoute.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE_REGEX_02 
		= "valider.SectionHit.indiceLettreRoute.regex";
	
	/**
	 * validerRGSectionHitIndiceLettreRouteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitIndiceLettreRouteRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_INDICELETTREROUTE_REGEX_02_EN_DUR 
		= "true";
	
	/* 11 - categorieAdminRoute. *******/
	/**
	 * clé de validerRGSectionHitCategorieAdminRoute dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.categorieAdminRoute"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE 
		= "valider.SectionHit.categorieAdminRoute";
	
	/**
	 * validerRGSectionHitCategorieAdminRoute par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitCategorieAdminRoute 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitCategorieAdminRouteRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.categorieAdminRoute.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01 
		= "valider.SectionHit.categorieAdminRoute.renseigne";
	
	/**
	 * validerRGSectionHitCategorieAdminRouteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitCategorieAdminRouteRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitCategorieAdminRouteRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.categorieAdminRoute.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02 
		= "valider.SectionHit.categorieAdminRoute.regex";
	
	/**
	 * validerRGSectionHitCategorieAdminRouteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitCategorieAdminRouteRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitCategorieAdminRouteNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.categorieAdminRoute.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03 
		= "valider.SectionHit.categorieAdminRoute.nomenclature";
	
	/**
	 * validerRGSectionHitCategorieAdminRouteNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitCategorieAdminRouteNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03_EN_DUR 
		= "true";
	
	/* 12 - typeComptage. *******/
	/**
	 * clé de validerRGSectionHitTypeComptage dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.typeComptage"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE 
		= "valider.SectionHit.typeComptage";
	
	/**
	 * validerRGSectionHitTypeComptage par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitTypeComptage 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitTypeComptageRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.typeComptage.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01 
		= "valider.SectionHit.typeComptage.renseigne";
	
	/**
	 * validerRGSectionHitTypeComptageRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitTypeComptageRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitTypeComptageRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.typeComptage.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_REGEX_02 
		= "valider.SectionHit.typeComptage.regex";
	
	/**
	 * validerRGSectionHitTypeComptageRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitTypeComptageRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitTypeComptageNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.typeComptage.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03 
		= "valider.SectionHit.typeComptage.nomenclature";
	
	/**
	 * validerRGSectionHitTypeComptageNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitTypeComptageNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03_EN_DUR 
		= "true";
	
	/* 13 - classementRoute. *******/
	/**
	 * clé de validerRGSectionHitClassementRoute dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classementRoute"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE 
		= "valider.SectionHit.classementRoute";
	
	/**
	 * validerRGSectionHitClassementRoute par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClassementRoute 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitClassementRouteRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classementRoute.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01 
		= "valider.SectionHit.classementRoute.renseigne";
	
	/**
	 * validerRGSectionHitClassementRouteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClassementRouteRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitClassementRouteRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classementRoute.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_REGEX_02 
		= "valider.SectionHit.classementRoute.regex";
	
	/**
	 * validerRGSectionHitClassementRouteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClassementRouteRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitClassementRouteNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classementRoute.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03 
		= "valider.SectionHit.classementRoute.nomenclature";
	
	/**
	 * validerRGSectionHitClassementRouteNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClassementRouteNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03_EN_DUR 
		= "true";
	
	/* 14 - classeLargeurChausseeU. *******/
	/**
	 * clé de validerRGSectionHitClasseLargeurChausseeU dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classeLargeurChausseeU"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU 
		= "valider.SectionHit.classeLargeurChausseeU";
	
	/**
	 * validerRGSectionHitClasseLargeurChausseeU par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseLargeurChausseeU 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitClasseLargeurChausseeURenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classeLargeurChausseeU.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01 
		= "valider.SectionHit.classeLargeurChausseeU.renseigne";
	
	/**
	 * validerRGSectionHitClasseLargeurChausseeURenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseLargeurChausseeURenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitClasseLargeurChausseeURegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classeLargeurChausseeU.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02 
		= "valider.SectionHit.classeLargeurChausseeU.regex";
	
	/**
	 * validerRGSectionHitClasseLargeurChausseeURegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseLargeurChausseeURegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitClasseLargeurChausseeUNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classeLargeurChausseeU.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03 
		= "valider.SectionHit.classeLargeurChausseeU.nomenclature";
	
	/**
	 * validerRGSectionHitClasseLargeurChausseeUNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseLargeurChausseeUNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03_EN_DUR 
		= "true";
	
	/* 15 - classeLargeurChausseesS. *******/
	/**
	 * clé de validerRGSectionHitClasseLargeurChausseesS dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classeLargeurChausseesS"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS 
		= "valider.SectionHit.classeLargeurChausseesS";
	
	/**
	 * validerRGSectionHitClasseLargeurChausseesS par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseLargeurChausseesS 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitClasseLargeurChausseesSRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classeLargeurChausseesS.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01 
		= "valider.SectionHit.classeLargeurChausseesS.renseigne";
	
	/**
	 * validerRGSectionHitClasseLargeurChausseesSRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseLargeurChausseesSRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitClasseLargeurChausseesSRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classeLargeurChausseesS.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02 
		= "valider.SectionHit.classeLargeurChausseesS.regex";
	
	/**
	 * validerRGSectionHitClasseLargeurChausseesSRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseLargeurChausseesSRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitClasseLargeurChausseesSNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.classeLargeurChausseesS.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03 
		= "valider.SectionHit.classeLargeurChausseesS.nomenclature";
	
	/**
	 * validerRGSectionHitClasseLargeurChausseesSNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitClasseLargeurChausseesSNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03_EN_DUR 
		= "true";
	
	/* 16 - typeReseau. *******/
	/**
	 * clé de validerRGSectionHitTypeReseau dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.typeReseau"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_TYPERESEAU 
		= "valider.SectionHit.typeReseau";
	
	/**
	 * validerRGSectionHitTypeReseau par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitTypeReseau 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_TYPERESEAU_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitTypeReseauRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.typeReseau.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_TYPERESEAU_RENSEIGNE_01 
		= "valider.SectionHit.typeReseau.renseigne";
	
	/**
	 * validerRGSectionHitTypeReseauRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitTypeReseauRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_TYPERESEAU_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitTypeReseauRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.typeReseau.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_TYPERESEAU_REGEX_02 
		= "valider.SectionHit.typeReseau.regex";
	
	/**
	 * validerRGSectionHitTypeReseauRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitTypeReseauRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_TYPERESEAU_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitTypeReseauNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.typeReseau.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03 
		= "valider.SectionHit.typeReseau.nomenclature";
	
	/**
	 * validerRGSectionHitTypeReseauNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitTypeReseauNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03_EN_DUR 
		= "true";
	
	/* 17 - pRoupK. *******/
	/**
	 * clé de validerRGSectionHitPRoupK dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pRoupK"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PROUPK 
		= "valider.SectionHit.pRoupK";
	
	/**
	 * validerRGSectionHitPRoupK par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPRoupK 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PROUPK_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitPRoupKRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pRoupK.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PROUPK_RENSEIGNE_01 
		= "valider.SectionHit.pRoupK.renseigne";
	
	/**
	 * validerRGSectionHitPRoupKRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPRoupKRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PROUPK_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPRoupKRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pRoupK.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PROUPK_REGEX_02 
		= "valider.SectionHit.pRoupK.regex";
	
	/**
	 * validerRGSectionHitPRoupKRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPRoupKRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PROUPK_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPRoupKNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pRoupK.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PROUPK_NOMENCLATURE_03 
		= "valider.SectionHit.pRoupK.nomenclature";
	
	/**
	 * validerRGSectionHitPRoupKNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPRoupKNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PROUPK_NOMENCLATURE_03_EN_DUR 
		= "true";				
	
	/* 18 - lieuDitOrigine. *******/
	/**
	 * clé de validerRGSectionHitLieuDitOrigine dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.lieuDitOrigine"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE 
		= "valider.SectionHit.lieuDitOrigine";
	
	/**
	 * validerRGSectionHitLieuDitOrigine par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLieuDitOrigine 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LIEUDITORIGINE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitLieuDitOrigineRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.lieuDitOrigine.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01 
		= "valider.SectionHit.lieuDitOrigine.renseigne";
	
	/**
	 * validerRGSectionHitLieuDitOrigineRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLieuDitOrigineRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitLieuDitOrigineRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.lieuDitOrigine.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE_REGEX_02 
		= "valider.SectionHit.lieuDitOrigine.regex";
	
	/**
	 * validerRGSectionHitLieuDitOrigineRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLieuDitOrigineRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LIEUDITORIGINE_REGEX_02_EN_DUR 
		= "true";
	
	/* 19 - prOrigine. *******/
	/**
	 * clé de validerRGSectionHitPrOrigine dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.prOrigine"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PRORIGINE 
		= "valider.SectionHit.prOrigine";
	
	/**
	 * validerRGSectionHitPrOrigine par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPrOrigine 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PRORIGINE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitPrOrigineRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.prOrigine.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PRORIGINE_RENSEIGNE_01 
		= "valider.SectionHit.prOrigine.renseigne";
	
	/**
	 * validerRGSectionHitPrOrigineRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPrOrigineRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PRORIGINE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPrOrigineRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.prOrigine.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PRORIGINE_REGEX_02 
		= "valider.SectionHit.prOrigine.regex";
	
	/**
	 * validerRGSectionHitPrOrigineRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPrOrigineRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PRORIGINE_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPrOrigineNumerique03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.prOrigine.numerique"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PRORIGINE_NUMERIQUE_03 
		= "valider.SectionHit.prOrigine.numerique";
	
	/**
	 * validerRGSectionHitPrOrigineNumerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPrOrigineNumerique03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PRORIGINE_NUMERIQUE_03_EN_DUR 
		= "true";
	
	/* 20 - absOrigine. *******/
	/**
	 * clé de validerRGSectionHitAbsOrigine dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.absOrigine"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ABSORIGINE 
		= "valider.SectionHit.absOrigine";
	
	/**
	 * validerRGSectionHitAbsOrigine par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAbsOrigine 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ABSORIGINE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitAbsOrigineRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.absOrigine.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ABSORIGINE_RENSEIGNE_01 
		= "valider.SectionHit.absOrigine.renseigne";
	
	/**
	 * validerRGSectionHitAbsOrigineRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAbsOrigineRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ABSORIGINE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitAbsOrigineRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.absOrigine.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ABSORIGINE_REGEX_02 
		= "valider.SectionHit.absOrigine.regex";
	
	/**
	 * validerRGSectionHitAbsOrigineRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAbsOrigineRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ABSORIGINE_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitAbsOrigineNumerique03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.absOrigine.numerique"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ABSORIGINE_NUMERIQUE_03 
		= "valider.SectionHit.absOrigine.numerique";
	
	/**
	 * validerRGSectionHitAbsOrigineNumerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAbsOrigineNumerique03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ABSORIGINE_NUMERIQUE_03_EN_DUR 
		= "true";
	
	/* 21 - lieuDitExtremite. *******/
	/**
	 * clé de validerRGSectionHitLieuDitExtremite dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.lieuDitExtremite"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LIEUDITEXTREMITE 
		= "valider.SectionHit.lieuDitExtremite";
	
	/**
	 * validerRGSectionHitLieuDitExtremite par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLieuDitExtremite 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LIEUDITEXTREMITE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitLieuDitExtremiteRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.lieuDitExtremite.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01 
		= "valider.SectionHit.lieuDitExtremite.renseigne";
	
	/**
	 * validerRGSectionHitLieuDitExtremiteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLieuDitExtremiteRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitLieuDitExtremiteRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.lieuDitExtremite.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02 
		= "valider.SectionHit.lieuDitExtremite.regex";
	
	/**
	 * validerRGSectionHitLieuDitExtremiteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLieuDitExtremiteRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02_EN_DUR 
		= "true";
	
	/* 22 - prExtremite. *******/
	/**
	 * clé de validerRGSectionHitPrExtremite dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.prExtremite"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PREXTREMITE 
		= "valider.SectionHit.prExtremite";
	
	/**
	 * validerRGSectionHitPrExtremite par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPrExtremite 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PREXTREMITE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitPrExtremiteRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.prExtremite.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PREXTREMITE_RENSEIGNE_01 
		= "valider.SectionHit.prExtremite.renseigne";
	
	/**
	 * validerRGSectionHitPrExtremiteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPrExtremiteRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PREXTREMITE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPrExtremiteRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.prExtremite.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PREXTREMITE_REGEX_02 
		= "valider.SectionHit.prExtremite.regex";
	
	/**
	 * validerRGSectionHitPrExtremiteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPrExtremiteRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PREXTREMITE_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPrExtremiteNumerique03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.prExtremite.numerique"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PREXTREMITE_NUMERIQUE_03 
		= "valider.SectionHit.prExtremite.numerique";
	
	/**
	 * validerRGSectionHitPrExtremiteNumerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPrExtremiteNumerique03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PREXTREMITE_NUMERIQUE_03_EN_DUR 
		= "true";
	
	/* 23 - absExtremite. *******/
	/**
	 * clé de validerRGSectionHitAbsExtremite dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.absExtremite"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ABSEXTREMITE 
		= "valider.SectionHit.absExtremite";
	
	/**
	 * validerRGSectionHitAbsExtremite par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAbsExtremite 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ABSEXTREMITE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitAbsExtremiteRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.absExtremite.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01 
		= "valider.SectionHit.absExtremite.renseigne";
	
	/**
	 * validerRGSectionHitAbsExtremiteRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAbsExtremiteRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitAbsExtremiteRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.absExtremite.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ABSEXTREMITE_REGEX_02 
		= "valider.SectionHit.absExtremite.regex";
	
	/**
	 * validerRGSectionHitAbsExtremiteRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAbsExtremiteRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ABSEXTREMITE_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitAbsExtremiteNumerique03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.absExtremite.numerique"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03 
		= "valider.SectionHit.absExtremite.numerique";
	
	/**
	 * validerRGSectionHitAbsExtremiteNumerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAbsExtremiteNumerique03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03_EN_DUR 
		= "true";
	
	/* 24 - lieuDitComptage. *******/
	/**
	 * clé de validerRGSectionHitLieuDitComptage dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.lieuDitComptage"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE 
		= "valider.SectionHit.lieuDitComptage";
	
	/**
	 * validerRGSectionHitLieuDitComptage par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLieuDitComptage 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitLieuDitComptageRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.lieuDitComptage.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01 
		= "valider.SectionHit.lieuDitComptage.renseigne";
	
	/**
	 * validerRGSectionHitLieuDitComptageRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLieuDitComptageRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitLieuDitComptageRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.lieuDitComptage.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02 
		= "valider.SectionHit.lieuDitComptage.regex";
	
	/**
	 * validerRGSectionHitLieuDitComptageRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLieuDitComptageRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02_EN_DUR 
		= "true";
	
	/* 25 - prComptage. *******/
	/**
	 * clé de validerRGSectionHitPrComptage dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.prComptage"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PRCOMPTAGE 
		= "valider.SectionHit.prComptage";
	
	/**
	 * validerRGSectionHitPrComptage par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPrComptage 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PRCOMPTAGE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitPrComptageRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.prComptage.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01 
		= "valider.SectionHit.prComptage.renseigne";
	
	/**
	 * validerRGSectionHitPrComptageRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPrComptageRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPrComptageRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.prComptage.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PRCOMPTAGE_REGEX_02 
		= "valider.SectionHit.prComptage.regex";
	
	/**
	 * validerRGSectionHitPrComptageRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPrComptageRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PRCOMPTAGE_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPrComptageNumerique03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.prComptage.numerique"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03 
		= "valider.SectionHit.prComptage.numerique";
	
	/**
	 * validerRGSectionHitPrComptageNumerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPrComptageNumerique03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03_EN_DUR 
		= "true";
	
	/* 26 - absComptage. *******/
	/**
	 * clé de validerRGSectionHitAbsComptage dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.absComptage"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ABSCOMPTAGE 
		= "valider.SectionHit.absComptage";
	
	/**
	 * validerRGSectionHitAbsComptage par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAbsComptage 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ABSCOMPTAGE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitAbsComptageRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.absComptage.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01 
		= "valider.SectionHit.absComptage.renseigne";
	
	/**
	 * validerRGSectionHitAbsComptageRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAbsComptageRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitAbsComptageRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.absComptage.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ABSCOMPTAGE_REGEX_02 
		= "valider.SectionHit.absComptage.regex";
	
	/**
	 * validerRGSectionHitAbsComptageRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAbsComptageRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ABSCOMPTAGE_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitAbsComptageNumerique03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.absComptage.numerique"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03 
		= "valider.SectionHit.absComptage.numerique";
	
	/**
	 * validerRGSectionHitAbsComptageNumerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAbsComptageNumerique03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03_EN_DUR 
		= "true";				
	
	/* 27 - longueurSection. *******/
	/**
	 * clé de validerRGSectionHitLongueurSection dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.longueurSection"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LONGUEURSECTION 
		= "valider.SectionHit.longueurSection";
	
	/**
	 * validerRGSectionHitLongueurSection par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLongueurSection 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LONGUEURSECTION_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitLongueurSectionRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.longueurSection.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LONGUEURSECTION_RENSEIGNE_01 
		= "valider.SectionHit.longueurSection.renseigne";
	
	/**
	 * validerRGSectionHitLongueurSectionRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLongueurSectionRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LONGUEURSECTION_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitLongueurSectionRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.longueurSection.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LONGUEURSECTION_REGEX_02 
		= "valider.SectionHit.longueurSection.regex";
	
	/**
	 * validerRGSectionHitLongueurSectionRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLongueurSectionRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LONGUEURSECTION_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitLongueurSectionNumerique03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.longueurSection.numerique"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LONGUEURSECTION_NUMERIQUE_03 
		= "valider.SectionHit.longueurSection.numerique";
	
	/**
	 * validerRGSectionHitLongueurSectionNumerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLongueurSectionNumerique03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LONGUEURSECTION_NUMERIQUE_03_EN_DUR 
		= "true";
	
	/* 28 - longueurRaseCampagne. *******/
	/**
	 * clé de validerRGSectionHitLongueurRaseCampagne dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.longueurRaseCampagne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE 
		= "valider.SectionHit.longueurRaseCampagne";
	
	/**
	 * validerRGSectionHitLongueurRaseCampagne par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLongueurRaseCampagne 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitLongueurRaseCampagneRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.longueurRaseCampagne.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_RENSEIGNE_01 
		= "valider.SectionHit.longueurRaseCampagne.renseigne";
	
	/**
	 * validerRGSectionHitLongueurRaseCampagneRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLongueurRaseCampagneRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitLongueurRaseCampagneRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.longueurRaseCampagne.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_REGEX_02 
		= "valider.SectionHit.longueurRaseCampagne.regex";
	
	/**
	 * validerRGSectionHitLongueurRaseCampagneRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLongueurRaseCampagneRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitLongueurRaseCampagneNumerique03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.longueurRaseCampagne.numerique"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_NUMERIQUE_03 
		= "valider.SectionHit.longueurRaseCampagne.numerique";
	
	/**
	 * validerRGSectionHitLongueurRaseCampagneNumerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitLongueurRaseCampagneNumerique03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_NUMERIQUE_03_EN_DUR 
		= "true";	
	
	/* 29 - numDepartementRattachement. *******/
	/**
	 * clé de validerRGSectionHitNumDepartementRattachement dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numDepartementRattachement"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT 
		= "valider.SectionHit.numDepartementRattachement";
	
	/**
	 * validerRGSectionHitNumDepartementRattachement par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumDepartementRattachement 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitNumDepartementRattachementRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numDepartementRattachement.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_RENSEIGNE_01 
		= "valider.SectionHit.numDepartementRattachement.renseigne";
	
	/**
	 * validerRGSectionHitNumDepartementRattachementRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumDepartementRattachementRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitNumDepartementRattachementRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numDepartementRattachement.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_REGEX_02 
		= "valider.SectionHit.numDepartementRattachement.regex";
	
	/**
	 * validerRGSectionHitNumDepartementRattachementRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumDepartementRattachementRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_REGEX_02_EN_DUR 
		= "true";
	
	/* 30 - numSectionRattachement. *******/
	/**
	 * clé de validerRGSectionHitNumSectionRattachement dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numSectionRattachement"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT 
		= "valider.SectionHit.numSectionRattachement";
	
	/**
	 * validerRGSectionHitNumSectionRattachement par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumSectionRattachement 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitNumSectionRattachementRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numSectionRattachement.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT_RENSEIGNE_01 
		= "valider.SectionHit.numSectionRattachement.renseigne";
	
	/**
	 * validerRGSectionHitNumSectionRattachementRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumSectionRattachementRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitNumSectionRattachementRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numSectionRattachement.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT_REGEX_02 
		= "valider.SectionHit.numSectionRattachement.regex";
	
	/**
	 * validerRGSectionHitNumSectionRattachementRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumSectionRattachementRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT_REGEX_02_EN_DUR 
		= "true";
	
	/* 31 - sensRattachement. *******/
	/**
	 * clé de validerRGSectionHitSensRattachement dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.sensRattachement"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_SENSRATTACHEMENT 
		= "valider.SectionHit.sensRattachement";
	
	/**
	 * validerRGSectionHitSensRattachement par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitSensRattachement 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_SENSRATTACHEMENT_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitSensRattachementRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.sensRattachement.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_SENSRATTACHEMENT_RENSEIGNE_01 
		= "valider.SectionHit.sensRattachement.renseigne";
	
	/**
	 * validerRGSectionHitSensRattachementRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitSensRattachementRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_SENSRATTACHEMENT_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitSensRattachementRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.sensRattachement.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_SENSRATTACHEMENT_REGEX_02 
		= "valider.SectionHit.sensRattachement.regex";
	
	/**
	 * validerRGSectionHitSensRattachementRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitSensRattachementRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_SENSRATTACHEMENT_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitSensRattachementNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.sensRattachement.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_SENSRATTACHEMENT_NOMENCLATURE_03 
		= "valider.SectionHit.sensRattachement.nomenclature";
	
	/**
	 * validerRGSectionHitSensRattachementNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitSensRattachementNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_SENSRATTACHEMENT_NOMENCLATURE_03_EN_DUR 
		= "true";
	
	/* 32 - numDepartementLimitrophe. *******/
	/**
	 * clé de validerRGSectionHitNumDepartementLimitrophe dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numDepartementLimitrophe"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE 
		= "valider.SectionHit.numDepartementLimitrophe";
	
	/**
	 * validerRGSectionHitNumDepartementLimitrophe par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumDepartementLimitrophe 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitNumDepartementLimitropheRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numDepartementLimitrophe.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_RENSEIGNE_01 
		= "valider.SectionHit.numDepartementLimitrophe.renseigne";
	
	/**
	 * validerRGSectionHitNumDepartementLimitropheRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumDepartementLimitropheRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitNumDepartementLimitropheRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numDepartementLimitrophe.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_REGEX_02 
		= "valider.SectionHit.numDepartementLimitrophe.regex";
	
	/**
	 * validerRGSectionHitNumDepartementLimitropheRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumDepartementLimitropheRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_REGEX_02_EN_DUR 
		= "true";
	
	/* 33 - numSectionLimitrophe. *******/
	/**
	 * clé de validerRGSectionHitNumSectionLimitrophe dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numSectionLimitrophe"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE 
		= "valider.SectionHit.numSectionLimitrophe";
	
	/**
	 * validerRGSectionHitNumSectionLimitrophe par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumSectionLimitrophe 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitNumSectionLimitropheRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numSectionLimitrophe.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE_RENSEIGNE_01 
		= "valider.SectionHit.numSectionLimitrophe.renseigne";
	
	/**
	 * validerRGSectionHitNumSectionLimitropheRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumSectionLimitropheRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitNumSectionLimitropheRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.numSectionLimitrophe.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE_REGEX_02 
		= "valider.SectionHit.numSectionLimitrophe.regex";
	
	/**
	 * validerRGSectionHitNumSectionLimitropheRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitNumSectionLimitropheRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE_REGEX_02_EN_DUR 
		= "true";
	
	/* 34 - sensLimitrophe. *******/
	/**
	 * clé de validerRGSectionHitSensLimitrophe dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.sensLimitrophe"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_SENSLIMITROPHE 
		= "valider.SectionHit.sensLimitrophe";
	
	/**
	 * validerRGSectionHitSensLimitrophe par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitSensLimitrophe 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_SENSLIMITROPHE_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitSensLimitropheRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.sensLimitrophe.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_SENSLIMITROPHE_RENSEIGNE_01 
		= "valider.SectionHit.sensLimitrophe.renseigne";
	
	/**
	 * validerRGSectionHitSensLimitropheRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitSensLimitropheRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_SENSLIMITROPHE_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitSensLimitropheRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.sensLimitrophe.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_SENSLIMITROPHE_REGEX_02 
		= "valider.SectionHit.sensLimitrophe.regex";
	
	/**
	 * validerRGSectionHitSensLimitropheRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitSensLimitropheRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_SENSLIMITROPHE_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitSensLimitropheNomenclature03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.sensLimitrophe.nomenclature"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_SENSLIMITROPHE_NOMENCLATURE_03 
		= "valider.SectionHit.sensLimitrophe.nomenclature";
	
	/**
	 * validerRGSectionHitSensLimitropheNomenclature03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitSensLimitropheNomenclature03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_SENSLIMITROPHE_NOMENCLATURE_03_EN_DUR 
		= "true";
	
	/* 35 - moisSectionnement. *******/
	/**
	 * clé de validerRGSectionHitMoisSectionnement dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.moisSectionnement"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MOISSECTIONNEMENT 
		= "valider.SectionHit.moisSectionnement";
	
	/**
	 * validerRGSectionHitMoisSectionnement par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMoisSectionnement 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MOISSECTIONNEMENT_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitMoisSectionnementRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.moisSectionnement.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MOISSECTIONNEMENT_RENSEIGNE_01 
		= "valider.SectionHit.moisSectionnement.renseigne";
	
	/**
	 * validerRGSectionHitMoisSectionnementRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMoisSectionnementRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MOISSECTIONNEMENT_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitMoisSectionnementRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.moisSectionnement.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MOISSECTIONNEMENT_REGEX_02 
		= "valider.SectionHit.moisSectionnement.regex";
	
	/**
	 * validerRGSectionHitMoisSectionnementRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMoisSectionnementRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MOISSECTIONNEMENT_REGEX_02_EN_DUR 
		= "true";
	
	/* 36 - anneeSectionnement. *******/
	/**
	 * clé de validerRGSectionHitAnneeSectionnement dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.anneeSectionnement"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT 
		= "valider.SectionHit.anneeSectionnement";
	
	/**
	 * validerRGSectionHitAnneeSectionnement par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAnneeSectionnement 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitAnneeSectionnementRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.anneeSectionnement.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT_RENSEIGNE_01 
		= "valider.SectionHit.anneeSectionnement.renseigne";
	
	/**
	 * validerRGSectionHitAnneeSectionnementRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAnneeSectionnementRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitAnneeSectionnementRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.anneeSectionnement.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT_REGEX_02 
		= "valider.SectionHit.anneeSectionnement.regex";
	
	/**
	 * validerRGSectionHitAnneeSectionnementRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitAnneeSectionnementRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT_REGEX_02_EN_DUR 
		= "true";
	
	/* 37 - zoneLibre2. *******/
	/**
	 * clé de validerRGSectionHitZoneLibre2 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.zoneLibre2"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ZONELIBRE2 
		= "valider.SectionHit.zoneLibre2";
	
	/**
	 * validerRGSectionHitZoneLibre2 par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitZoneLibre2 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ZONELIBRE2_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitZoneLibre2Renseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.zoneLibre2.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ZONELIBRE2_RENSEIGNE_01 
		= "valider.SectionHit.zoneLibre2.renseigne";
	
	/**
	 * validerRGSectionHitZoneLibre2Renseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitZoneLibre2Renseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ZONELIBRE2_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitZoneLibre2Regex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.zoneLibre2.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_ZONELIBRE2_REGEX_02 
		= "valider.SectionHit.zoneLibre2.regex";
	
	/**
	 * validerRGSectionHitZoneLibre2Regex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitZoneLibre2Regex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_ZONELIBRE2_REGEX_02_EN_DUR 
		= "true";
	
	/* 38 - mjaN. *******/
	/**
	 * clé de validerRGSectionHitMjaN dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.mjaN"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MJAN 
		= "valider.SectionHit.mjaN";
	
	/**
	 * validerRGSectionHitMjaN par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMjaN 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MJAN_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitMjaNRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.mjaN.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MJAN_RENSEIGNE_01 
		= "valider.SectionHit.mjaN.renseigne";
	
	/**
	 * validerRGSectionHitMjaNRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMjaNRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MJAN_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitMjaNRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.mjaN.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MJAN_REGEX_02 
		= "valider.SectionHit.mjaN.regex";
	
	/**
	 * validerRGSectionHitMjaNRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMjaNRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MJAN_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitMjaNNumerique03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.mjaN.numerique"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MJAN_NUMERIQUE_03 
		= "valider.SectionHit.mjaN.numerique";
	
	/**
	 * validerRGSectionHitMjaNNumerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMjaNNumerique03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MJAN_NUMERIQUE_03_EN_DUR 
		= "true";
	
	/* 39 - modeCalculN. *******/
	/**
	 * clé de validerRGSectionHitModeCalculN dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.modeCalculN"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MODECALCULN 
		= "valider.SectionHit.modeCalculN";
	
	/**
	 * validerRGSectionHitModeCalculN par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitModeCalculN 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MODECALCULN_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitModeCalculNRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.modeCalculN.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MODECALCULN_RENSEIGNE_01 
		= "valider.SectionHit.modeCalculN.renseigne";
	
	/**
	 * validerRGSectionHitModeCalculNRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitModeCalculNRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MODECALCULN_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitModeCalculNRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.modeCalculN.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MODECALCULN_REGEX_02 
		= "valider.SectionHit.modeCalculN.regex";
	
	/**
	 * validerRGSectionHitModeCalculNRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitModeCalculNRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MODECALCULN_REGEX_02_EN_DUR 
		= "true";
	
	/* 40 - pcPLN. *******/
	/**
	 * clé de validerRGSectionHitPcPLN dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcPLN"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCPLN 
		= "valider.SectionHit.pcPLN";
	
	/**
	 * validerRGSectionHitPcPLN par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcPLN 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCPLN_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitPcPLNRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcPLN.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCPLN_RENSEIGNE_01 
		= "valider.SectionHit.pcPLN.renseigne";
	
	/**
	 * validerRGSectionHitPcPLNRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcPLNRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCPLN_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPcPLNRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcPLN.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCPLN_REGEX_02 
		= "valider.SectionHit.pcPLN.regex";
	
	/**
	 * validerRGSectionHitPcPLNRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcPLNRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCPLN_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPcPLNNumerique03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcPLN.numerique"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCPLN_NUMERIQUE_03 
		= "valider.SectionHit.pcPLN.numerique";
	
	/**
	 * validerRGSectionHitPcPLNNumerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcPLNNumerique03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCPLN_NUMERIQUE_03_EN_DUR 
		= "true";
	
	/* 41 - evaluationPLN. *******/
	/**
	 * clé de validerRGSectionHitEvaluationPLN dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.evaluationPLN"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_EVALUATIONPLN 
		= "valider.SectionHit.evaluationPLN";
	
	/**
	 * validerRGSectionHitEvaluationPLN par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitEvaluationPLN 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_EVALUATIONPLN_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitEvaluationPLNRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.evaluationPLN.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_EVALUATIONPLN_RENSEIGNE_01 
		= "valider.SectionHit.evaluationPLN.renseigne";
	
	/**
	 * validerRGSectionHitEvaluationPLNRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitEvaluationPLNRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_EVALUATIONPLN_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitEvaluationPLNRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.evaluationPLN.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_EVALUATIONPLN_REGEX_02 
		= "valider.SectionHit.evaluationPLN.regex";
	
	/**
	 * validerRGSectionHitEvaluationPLNRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitEvaluationPLNRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_EVALUATIONPLN_REGEX_02_EN_DUR 
		= "true";
	
	/* 42 - pcNuitAnnuelN. *******/
	/**
	 * clé de validerRGSectionHitPcNuitAnnuelN dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcNuitAnnuelN"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCNUITANNUELN 
		= "valider.SectionHit.pcNuitAnnuelN";
	
	/**
	 * validerRGSectionHitPcNuitAnnuelN par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcNuitAnnuelN 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCNUITANNUELN_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitPcNuitAnnuelNRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcNuitAnnuelN.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCNUITANNUELN_RENSEIGNE_01 
		= "valider.SectionHit.pcNuitAnnuelN.renseigne";
	
	/**
	 * validerRGSectionHitPcNuitAnnuelNRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcNuitAnnuelNRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCNUITANNUELN_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPcNuitAnnuelNRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcNuitAnnuelN.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCNUITANNUELN_REGEX_02 
		= "valider.SectionHit.pcNuitAnnuelN.regex";
	
	/**
	 * validerRGSectionHitPcNuitAnnuelNRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcNuitAnnuelNRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCNUITANNUELN_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPcNuitAnnuelNNumerique03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcNuitAnnuelN.numerique"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCNUITANNUELN_NUMERIQUE_03 
		= "valider.SectionHit.pcNuitAnnuelN.numerique";
	
	/**
	 * validerRGSectionHitPcNuitAnnuelNNumerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcNuitAnnuelNNumerique03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCNUITANNUELN_NUMERIQUE_03_EN_DUR 
		= "true";
	
	/* 43 - indiceFiabiliteMjaN. *******/
	/**
	 * clé de validerRGSectionHitIndiceFiabiliteMjaN dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.indiceFiabiliteMjaN"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN 
		= "valider.SectionHit.indiceFiabiliteMjaN";
	
	/**
	 * validerRGSectionHitIndiceFiabiliteMjaN par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitIndiceFiabiliteMjaN 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitIndiceFiabiliteMjaNRenseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.indiceFiabiliteMjaN.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN_RENSEIGNE_01 
		= "valider.SectionHit.indiceFiabiliteMjaN.renseigne";
	
	/**
	 * validerRGSectionHitIndiceFiabiliteMjaNRenseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitIndiceFiabiliteMjaNRenseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitIndiceFiabiliteMjaNRegex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.indiceFiabiliteMjaN.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN_REGEX_02 
		= "valider.SectionHit.indiceFiabiliteMjaN.regex";
	
	/**
	 * validerRGSectionHitIndiceFiabiliteMjaNRegex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitIndiceFiabiliteMjaNRegex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN_REGEX_02_EN_DUR 
		= "true";
	
	/* 44 - mjmNmois01. *******/
	/**
	 * clé de validerRGSectionHitMjmNmois01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.mjmNmois01"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MJMNMOIS01 
		= "valider.SectionHit.mjmNmois01";
	
	/**
	 * validerRGSectionHitMjmNmois01 par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMjmNmois01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MJMNMOIS01_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitMjmNmois01Renseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.mjmNmois01.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MJMNMOIS01_RENSEIGNE_01 
		= "valider.SectionHit.mjmNmois01.renseigne";
	
	/**
	 * validerRGSectionHitMjmNmois01Renseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMjmNmois01Renseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MJMNMOIS01_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitMjmNmois01Regex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.mjmNmois01.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MJMNMOIS01_REGEX_02 
		= "valider.SectionHit.mjmNmois01.regex";
	
	/**
	 * validerRGSectionHitMjmNmois01Regex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMjmNmois01Regex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MJMNMOIS01_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitMjmNmois01Numerique03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.mjmNmois01.numerique"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MJMNMOIS01_NUMERIQUE_03 
		= "valider.SectionHit.mjmNmois01.numerique";
	
	/**
	 * validerRGSectionHitMjmNmois01Numerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMjmNmois01Numerique03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MJMNMOIS01_NUMERIQUE_03_EN_DUR 
		= "true";
	
	/* 45 - pcNuitNmois01. *******/
	/**
	 * clé de validerRGSectionHitPcNuitNmois01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcNuitNmois01"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCNUITNMOIS01 
		= "valider.SectionHit.pcNuitNmois01";
	
	/**
	 * validerRGSectionHitPcNuitNmois01 par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcNuitNmois01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCNUITNMOIS01_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitPcNuitNmois01Renseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcNuitNmois01.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCNUITNMOIS01_RENSEIGNE_01 
		= "valider.SectionHit.pcNuitNmois01.renseigne";
	
	/**
	 * validerRGSectionHitPcNuitNmois01Renseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcNuitNmois01Renseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCNUITNMOIS01_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPcNuitNmois01Regex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcNuitNmois01.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCNUITNMOIS01_REGEX_02 
		= "valider.SectionHit.pcNuitNmois01.regex";
	
	/**
	 * validerRGSectionHitPcNuitNmois01Regex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcNuitNmois01Regex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCNUITNMOIS01_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPcNuitNmois01Numerique03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcNuitNmois01.numerique"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCNUITNMOIS01_NUMERIQUE_03 
		= "valider.SectionHit.pcNuitNmois01.numerique";
	
	/**
	 * validerRGSectionHitPcNuitNmois01Numerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcNuitNmois01Numerique03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCNUITNMOIS01_NUMERIQUE_03_EN_DUR 
		= "true";
	
	/* 46 - mjmNmois02. *******/
	/**
	 * clé de validerRGSectionHitMjmNmois02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.mjmNmois02"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MJMNMOIS02 
		= "valider.SectionHit.mjmNmois02";
	
	/**
	 * validerRGSectionHitMjmNmois02 par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMjmNmois02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MJMNMOIS02_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitMjmNmois02Renseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.mjmNmois02.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MJMNMOIS02_RENSEIGNE_01 
		= "valider.SectionHit.mjmNmois02.renseigne";
	
	/**
	 * validerRGSectionHitMjmNmois02Renseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMjmNmois02Renseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MJMNMOIS02_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitMjmNmois02Regex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.mjmNmois02.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MJMNMOIS02_REGEX_02 
		= "valider.SectionHit.mjmNmois02.regex";
	
	/**
	 * validerRGSectionHitMjmNmois02Regex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMjmNmois02Regex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MJMNMOIS02_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitMjmNmois02Numerique03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.mjmNmois02.numerique"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MJMNMOIS02_NUMERIQUE_03 
		= "valider.SectionHit.mjmNmois02.numerique";
	
	/**
	 * validerRGSectionHitMjmNmois02Numerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMjmNmois02Numerique03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MJMNMOIS02_NUMERIQUE_03_EN_DUR 
		= "true";
	
	/* 47 - pcNuitNmois02. *******/
	/**
	 * clé de validerRGSectionHitPcNuitNmois02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcNuitNmois02"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCNUITNMOIS02 
		= "valider.SectionHit.pcNuitNmois02";
	
	/**
	 * validerRGSectionHitPcNuitNmois02 par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcNuitNmois02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCNUITNMOIS02_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitPcNuitNmois02Renseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcNuitNmois02.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCNUITNMOIS02_RENSEIGNE_01 
		= "valider.SectionHit.pcNuitNmois02.renseigne";
	
	/**
	 * validerRGSectionHitPcNuitNmois02Renseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcNuitNmois02Renseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCNUITNMOIS02_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPcNuitNmois02Regex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcNuitNmois02.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCNUITNMOIS02_REGEX_02 
		= "valider.SectionHit.pcNuitNmois02.regex";
	
	/**
	 * validerRGSectionHitPcNuitNmois02Regex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcNuitNmois02Regex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCNUITNMOIS02_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPcNuitNmois02Numerique03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcNuitNmois02.numerique"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCNUITNMOIS02_NUMERIQUE_03 
		= "valider.SectionHit.pcNuitNmois02.numerique";
	
	/**
	 * validerRGSectionHitPcNuitNmois02Numerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcNuitNmois02Numerique03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCNUITNMOIS02_NUMERIQUE_03_EN_DUR 
		= "true";
	
	/* 48 - mjmNmois03. *******/
	/**
	 * clé de validerRGSectionHitMjmNmois03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.mjmNmois03"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MJMNMOIS03 
		= "valider.SectionHit.mjmNmois03";
	
	/**
	 * validerRGSectionHitMjmNmois03 par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMjmNmois03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MJMNMOIS03_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitMjmNmois03Renseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.mjmNmois03.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MJMNMOIS03_RENSEIGNE_01 
		= "valider.SectionHit.mjmNmois03.renseigne";
	
	/**
	 * validerRGSectionHitMjmNmois03Renseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMjmNmois03Renseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MJMNMOIS03_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitMjmNmois03Regex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.mjmNmois03.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MJMNMOIS03_REGEX_02 
		= "valider.SectionHit.mjmNmois03.regex";
	
	/**
	 * validerRGSectionHitMjmNmois03Regex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMjmNmois03Regex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MJMNMOIS03_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitMjmNmois03Numerique03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.mjmNmois03.numerique"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_MJMNMOIS03_NUMERIQUE_03 
		= "valider.SectionHit.mjmNmois03.numerique";
	
	/**
	 * validerRGSectionHitMjmNmois03Numerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitMjmNmois03Numerique03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_MJMNMOIS03_NUMERIQUE_03_EN_DUR 
		= "true";
	
	/* 49 - pcNuitNmois03. *******/
	/**
	 * clé de validerRGSectionHitPcNuitNmois03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcNuitNmois03"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCNUITNMOIS03 
		= "valider.SectionHit.pcNuitNmois03";
	
	/**
	 * validerRGSectionHitPcNuitNmois03 par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcNuitNmois03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCNUITNMOIS03_EN_DUR 
		= "true";
	
	/**
	 * clé de validerRGSectionHitPcNuitNmois03Renseigne01 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcNuitNmois03.renseigne"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCNUITNMOIS03_RENSEIGNE_01 
		= "valider.SectionHit.pcNuitNmois03.renseigne";
	
	/**
	 * validerRGSectionHitPcNuitNmois03Renseigne01 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcNuitNmois03Renseigne01 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCNUITNMOIS03_RENSEIGNE_01_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPcNuitNmois03Regex02 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcNuitNmois03.regex"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCNUITNMOIS03_REGEX_02 
		= "valider.SectionHit.pcNuitNmois03.regex";
	
	/**
	 * validerRGSectionHitPcNuitNmois03Regex02 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcNuitNmois03Regex02 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCNUITNMOIS03_REGEX_02_EN_DUR 
		= "true";
		
	/**
	 * clé de validerRGSectionHitPcNuitNmois03Numerique03 dans 
	 * SectionHit_RG.properties<br/>
	 * "valider.SectionHit.pcNuitNmois03.numerique"<br/>
	 */
	public static final String KEY_VALIDER_SECTIONHIT_PCNUITNMOIS03_NUMERIQUE_03 
		= "valider.SectionHit.pcNuitNmois03.numerique";
	
	/**
	 * validerRGSectionHitPcNuitNmois03Numerique03 par défaut 
	 * de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le 
	 * validerRGSectionHitPcNuitNmois03Numerique03 
	 * indiqué dans SectionHit_RG.properties.<br/>
	 * "true".<br/>
	 */
	public static final String STRING_VALIDER_SECTIONHIT_PCNUITNMOIS03_NUMERIQUE_03_EN_DUR 
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
	/* 1 - numDepartement. ***************/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur le <i>numDepartement</i> de la SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numDepartement</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitNumDepartement;
	
	/**
	 * Boolean activant la RG-SectionHit-NumDepartement-01 : 
	 * "le numéro de département de la section HIT (colonnes [1-3] du HIT) doit être renseigné (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitNumDepartementRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-NumDepartement-02 : 
	 * "le numéro de département doit comporter exactement 3 chiffres 
	 * dans les colonnes [1-3] du HIT 
	 * ('030' pour 03, '300' pour 30, '972' pour 972)".<br/>
	 */
	private static Boolean validerRGSectionHitNumDepartementRegex02;
	
	/* 2 - numSection. *****************/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur le <i>numSection</i> de la SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numSection</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitNumSection;
	
	/**
	 * Boolean activant la RG-SectionHit-NumSection-01 : 
	 * "le numéro de section de la section HIT (colonnes [4-9] du HIT) 
	 * doit être renseigné (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitNumSectionRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-NumSection-02 : 
	 * "le numéro de section doit comporter exactement 6 chiffres 
	 * (Numéro de section sur 4 chiffres significatifs maxi complété 
	 * éventuellement avec des 0 à gauche et indice sur 2 chiffres 
	 * significatifs maxi complété éventuellement avec des 0 à gauche. 
	 * Exemples : section 26 indice 4 = 002604, section 1 indice 0 = 000100
	 * , section 162 indice 65 = 016265) - (colonnes [4-9] du HIT)".<br/>
	 */
	private static Boolean validerRGSectionHitNumSectionRegex02;
	
	/* 3 - sens. ******************/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur le <i>sens</i> de la SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>sens</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitSens;
	
	/**
	 * Boolean activant la RG-SectionHit-Sens-01 : 
	 * "le sens de la section HIT (colonne [10] du HIT) doit être renseigné (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitSensRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-Sens-02 : 
	 * "le sens de la section HIT (colonne [10] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitSensRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-Sens-03 :
	 * "le sens de la section HIT (colonne [10] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]".<br/>
	 */
	private static Boolean validerRGSectionHitSensNomenclature03;
	
	/* 4 - nature. *****************/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur le <i>nature</i> de la SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>nature</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitNature;
	
	/**
	 * Boolean activant la RG-SectionHit-Nature-01 : 
	 * "la nature de la section HIT (colonne [11] du HIT) doit être renseignée (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitNatureRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-Nature-02 : 
	 * "la nature de la section HIT (colonne [11] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitNatureRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-Nature-03 :
	 * "la nature de la section HIT (colonne [11] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]".<br/>
	 */
	private static Boolean validerRGSectionHitNatureNomenclature03;
	
	/* 5 - classe. *************/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur le <i>classe</i> de la SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>classe</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitClasse;
	
	/**
	 * Boolean activant la RG-SectionHit-Classe-01 : 
	 * "la classe de la section HIT (colonnes [12-13] du HIT) doit être renseignée".<br/>
	 */
	private static Boolean validerRGSectionHitClasseRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-Classe-02 : 
	 * "la classe de la section HIT (colonnes [12-13] du HIT) doit comporter exactement 2 chiffres ('00')".<br/>
	 */
	private static Boolean validerRGSectionHitClasseRegex02;
	
	/* 6 - anneeTraitement. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>anneeTraitement</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>anneeTraitement</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitAnneeTraitement;
	
	/**
	 * Boolean activant la RG-SectionHit-AnneeTraitement-01 : 
	 * "l'année de traitement de la section HIT (colonnes [14-15] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitAnneeTraitementRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-AnneeTraitement-02 : 
	 * "l'année de traitement de la section HIT (colonnes [14-15] du HIT) doit comporter exactement 2 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitAnneeTraitementRegex02;
	
	/* 7 - zoneLibre1. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>zoneLibre1</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>zoneLibre1</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitZoneLibre1;
	
	/**
	 * Boolean activant la RG-SectionHit-ZoneLibre1-01 : 
	 * "la zone libre de la section HIT (colonne [16] du HIT) doit être renseignée (1 espace)".<br/>
	 */
	private static Boolean validerRGSectionHitZoneLibre1Renseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-ZoneLibre1-02 : 
	 * "la zone libre de la section HIT (colonne [16] du HIT) doit comporter exactement 1 espace".<br/>
	 */
	private static Boolean validerRGSectionHitZoneLibre1Regex02;
	
	/* 8 - numRoute. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>numRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numRoute</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitNumRoute;
	
	/**
	 * Boolean activant la RG-SectionHit-NumRoute-01 : 
	 * "le numéro de la route de la section HIT (colonnes [17-20] du HIT) doit être renseigné (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitNumRouteRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-NumRoute-02 : 
	 * "le numéro de la route de la section HIT (colonnes [17-20] du HIT) doit comporter exactement 4 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitNumRouteRegex02;
	
	/* 9 - indiceNumRoute. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>indiceNumRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>indiceNumRoute</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitIndiceNumRoute;
	
	/**
	 * Boolean activant la RG-SectionHit-IndiceNumRoute-01 : 
	 * "l'indice numérique de la route de la section HIT (colonnes [21] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitIndiceNumRouteRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-IndiceNumRoute-02 : 
	 * "l'indice numérique de la route de la section HIT (colonnes [21] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitIndiceNumRouteRegex02;
	
	/* 10 - indiceLettreRoute. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>indiceLettreRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>indiceLettreRoute</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitIndiceLettreRoute;
	
	/**
	 * Boolean activant la RG-SectionHit-IndiceLettreRoute-01 : 
	 * "l'indice lettre de la route de la section HIT (colonnes [22] du HIT) doit être renseigné (ou espace si pas d'indice)".<br/>
	 */
	private static Boolean validerRGSectionHitIndiceLettreRouteRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-IndiceLettreRoute-02 : 
	 * "l'indice lettre de la route de la section HIT (colonnes [22] du HIT) doit comporter exactement 1 lettre (ou espace si pas d'indice)".<br/>
	 */
	private static Boolean validerRGSectionHitIndiceLettreRouteRegex02;
	
	/* 11 - categorieAdminRoute. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>categorieAdminRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>categorieAdminRoute</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitCategorieAdminRoute;
	
	/**
	 * Boolean activant la RG-SectionHit-CategorieAdminRoute-01 : 
	 * "la catégorie administrative de la route de la section HIT (colonnes [23] du HIT) doit être renseignée (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitCategorieAdminRouteRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-CategorieAdminRoute-02 : 
	 * "la catégorie administrative de la route de la section HIT (colonnes [23] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitCategorieAdminRouteRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-CategorieAdminRoute-03 :
	 * "la catégorie administrative de la route de la section HIT (colonne [23] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]".<br/>
	 */
	private static Boolean validerRGSectionHitCategorieAdminRouteNomenclature03;
	
	/* 12 - typeComptage. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>typeComptage</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>typeComptage</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitTypeComptage;
	
	/**
	 * Boolean activant la RG-SectionHit-TypeComptage-01 : 
	 * "le type de comptage de la section HIT (colonne [24] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitTypeComptageRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-TypeComptage-02 : 
	 * "le type de comptage de la section HIT (colonne [24] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitTypeComptageRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-TypeComptage-03 :
	 * "le type de comptage de la section HIT (colonne [24] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5, 6, 7, 8, 9]".<br/>
	 */
	private static Boolean validerRGSectionHitTypeComptageNomenclature03;
	
	/* 13 - classementRoute. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>classementRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>classementRoute</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitClassementRoute;
	
	/**
	 * Boolean activant la RG-SectionHit-ClassementRoute-01 : 
	 * "le classement de la route de la section HIT (colonne [25] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitClassementRouteRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-ClassementRoute-02 : 
	 * "le classement de la route de la section HIT (colonne [25] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitClassementRouteRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-ClassementRoute-03 :
	 * "le classement de la route de la section HIT (colonne [25] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]".<br/>
	 */
	private static Boolean validerRGSectionHitClassementRouteNomenclature03;	
	
	/* 14 - classeLargeurChausseeU. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>classeLargeurChausseeU</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>classeLargeurChausseeU</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitClasseLargeurChausseeU;
	
	/**
	 * Boolean activant la RG-SectionHit-ClasseLargeurChausseeU-01 : 
	 * "la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit être renseignée (0 si sans objet)".<br/>
	 */
	private static Boolean validerRGSectionHitClasseLargeurChausseeURenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-ClasseLargeurChausseeU-02 : 
	 * "la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitClasseLargeurChausseeURegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-ClasseLargeurChausseeU-03 :
	 * "la classe de largeur de chaussée unique de la section HIT (colonne [26] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4, 5, 6, 7, 8]".<br/>
	 */
	private static Boolean validerRGSectionHitClasseLargeurChausseeUNomenclature03;
	
	/* 15 - classeLargeurChausseesS. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>classeLargeurChausseesS</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>classeLargeurChausseesS</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitClasseLargeurChausseesS;
	
	/**
	 * Boolean activant la RG-SectionHit-ClasseLargeurChausseesS-01 : 
	 * "la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit être renseignée (0 si sans objet)".<br/>
	 */
	private static Boolean validerRGSectionHitClasseLargeurChausseesSRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-ClasseLargeurChausseesS-02 : 
	 * "la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitClasseLargeurChausseesSRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-ClasseLargeurChausseesS-03 :
	 * "la classe de largeur de chaussées séparées de la section HIT (colonne [27] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4, 5, 6, 7]".<br/>
	 */
	private static Boolean validerRGSectionHitClasseLargeurChausseesSNomenclature03;
	
	/* 16 - typeReseau. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>typeReseau</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>typeReseau</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitTypeReseau;
	
	/**
	 * Boolean activant la RG-SectionHit-TypeReseau-01 : 
	 * "le type de réseau de la section HIT (colonne [28] du HIT) doit être renseigné (0 si autre que autoroute)".<br/>
	 */
	private static Boolean validerRGSectionHitTypeReseauRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-TypeReseau-02 : 
	 * "le type de réseau de la section HIT (colonne [28] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitTypeReseauRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-TypeReseau-03 :
	 * "le type de réseau de la section HIT (colonne [28] du HIT) doit respecter une nomenclature [0, 1, 2, 3, 4]".<br/>
	 */
	private static Boolean validerRGSectionHitTypeReseauNomenclature03;
	
	/* 17 - pRoupK. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>pRoupK</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>pRoupK</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitPRoupK;
	
	/**
	 * Boolean activant la RG-SectionHit-PRoupK-01 : 
	 * "PR ou PK de la section HIT (colonne [29] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitPRoupKRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-PRoupK-02 : 
	 * "PR ou PK de la section HIT (colonne [29] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitPRoupKRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-PRoupK-03 :
	 * "PR ou PK de la section HIT (colonne [29] du HIT) doit respecter une nomenclature [1, 2]".<br/>
	 */
	private static Boolean validerRGSectionHitPRoupKNomenclature03;
	
	/* 18 - lieuDitOrigine. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>lieuDitOrigine</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>lieuDitOrigine</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitLieuDitOrigine;
	
	/**
	 * Boolean activant la RG-SectionHit-LieuDitOrigine-01 : 
	 * "le lieu-dit origine de la section HIT (colonnes [30-49] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitLieuDitOrigineRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-LieuDitOrigine-02 : 
	 * "le lieu-dit origine de la section HIT (colonnes [30-49] du HIT) doit comporter exactement 20 caractères".<br/>
	 */
	private static Boolean validerRGSectionHitLieuDitOrigineRegex02;	
	
	/* 19 - prOrigine. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>prOrigine</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>prOrigine</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitPrOrigine;
	
	/**
	 * Boolean activant la RG-SectionHit-PrOrigine-01 : 
	 * "le PR origine de la section HIT (colonnes [50-52] du HIT) doit être renseigné (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitPrOrigineRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-PrOrigine-02 : 
	 * "le PR origine de la section HIT (colonnes [50-52] du HIT) doit comporter exactement 3 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitPrOrigineRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-PrOrigine-03 : 
	 * "le PR origine de la section HIT (colonnes [50-52] du HIT) doit être homogène à un entier".<br/>
	 */
	private static Boolean validerRGSectionHitPrOrigineNumerique03;
	
	/* 20 - absOrigine. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>absOrigine</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>absOrigine</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitAbsOrigine;
	
	/**
	 * Boolean activant la RG-SectionHit-AbsOrigine-01 : 
	 * "l'abscisse origine de la section HIT (colonnes [53-56] du HIT) doit être renseignée (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitAbsOrigineRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-AbsOrigine-02 : 
	 * "l'abscisse origine de la section HIT (colonnes [53-56] du HIT) doit comporter exactement 4 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitAbsOrigineRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-AbsOrigine-03 : 
	 * "l'abscisse origine de la section HIT (colonnes [53-56] du HIT) doit être homogène à un entier".<br/>
	 */
	private static Boolean validerRGSectionHitAbsOrigineNumerique03;
	
	/* 21 - lieuDitExtremite. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>lieuDitExtremite</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>lieuDitExtremite</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitLieuDitExtremite;
	
	/**
	 * Boolean activant la RG-SectionHit-LieuDitExtremite-01 : 
	 * "le lieu-dit extremité de la section HIT (colonnes [57-76] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitLieuDitExtremiteRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-LieuDitExtremite-02 : 
	 * "le lieu-dit extremité de la section HIT (colonnes [57-76] du HIT) doit comporter exactement 20 caractères".<br/>
	 */
	private static Boolean validerRGSectionHitLieuDitExtremiteRegex02;	
	
	/* 22 - prExtremite. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>prExtremite</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>prExtremite</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitPrExtremite;
	
	/**
	 * Boolean activant la RG-SectionHit-PrExtremite-01 : 
	 * "le PR extremité de la section HIT (colonnes [77-79] du HIT) doit être renseigné (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitPrExtremiteRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-PrExtremite-02 : 
	 * "le PR extremité de la section HIT (colonnes [77-79] du HIT) doit comporter exactement 3 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitPrExtremiteRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-PrExtremite-03 : 
	 * "le PR extremité de la section HIT (colonnes [77-79] du HIT) doit être homogène à un entier".<br/>
	 */
	private static Boolean validerRGSectionHitPrExtremiteNumerique03;
	
	/* 23 - absExtremite. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>absExtremite</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>absExtremite</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitAbsExtremite;
	
	/**
	 * Boolean activant la RG-SectionHit-AbsExtremite-01 : 
	 * "l'abscisse extremité de la section HIT (colonnes [80-83] du HIT) doit être renseignée (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitAbsExtremiteRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-AbsExtremite-02 : 
	 * "l'abscisse extremité de la section HIT (colonnes [80-83] du HIT) doit comporter exactement 4 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitAbsExtremiteRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-AbsExtremite-03 : 
	 * "l'abscisse extremité de la section HIT (colonnes [80-83] du HIT) doit être homogène à un entier".<br/>
	 */
	private static Boolean validerRGSectionHitAbsExtremiteNumerique03;
	
	/* 24 - lieuDitComptage. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>lieuDitComptage</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>lieuDitComptage</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitLieuDitComptage;
	
	/**
	 * Boolean activant la RG-SectionHit-LieuDitComptage-01 : 
	 * "le lieu-dit de comptage de la section HIT (colonnes [84-103] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitLieuDitComptageRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-LieuDitComptage-02 : 
	 * "le lieu-dit de comptage de la section HIT (colonnes [84-103] du HIT) doit comporter exactement 20 caractères".<br/>
	 */
	private static Boolean validerRGSectionHitLieuDitComptageRegex02;	
	
	/* 25 - prComptage. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>prComptage</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>prComptage</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitPrComptage;
	
	/**
	 * Boolean activant la RG-SectionHit-PrComptage-01 : 
	 * "le PR du comptage de la section HIT (colonnes [104-106] du HIT) doit être renseigné (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitPrComptageRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-PrComptage-02 : 
	 * "le PR du comptage de la section HIT (colonnes [104-106] du HIT) doit comporter exactement 3 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitPrComptageRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-PrComptage-03 : 
	 * "le PR du comptage de la section HIT (colonnes [104-106] du HIT) doit être homogène à un entier".<br/>
	 */
	private static Boolean validerRGSectionHitPrComptageNumerique03;
	
	/* 26 - absComptage. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>absComptage</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>absComptage</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitAbsComptage;
	
	/**
	 * Boolean activant la RG-SectionHit-AbsComptage-01 : 
	 * "l'abscisse du comptage de la section HIT (colonnes [107-110] du HIT) doit être renseignée (obligatoire)".<br/>
	 */
	private static Boolean validerRGSectionHitAbsComptageRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-AbsComptage-02 : 
	 * "l'abscisse du comptage de la section HIT (colonnes [107-110] du HIT) doit comporter exactement 4 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitAbsComptageRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-AbsComptage-03 : 
	 * "l'abscisse du comptage de la section HIT (colonnes [107-110] du HIT) doit être homogène à un entier".<br/>
	 */
	private static Boolean validerRGSectionHitAbsComptageNumerique03;
	
	/* 27 - longueurSection. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>longueurSection</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>longueurSection</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitLongueurSection;
	
	/**
	 * Boolean activant la RG-SectionHit-LongueurSection-01 : 
	 * "la longueur de la section HIT (colonnes [111-116] du HIT) doit être renseignée".<br/>
	 */
	private static Boolean validerRGSectionHitLongueurSectionRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-LongueurSection-02 : 
	 * "la longueur de la section HIT (colonnes [111-116] du HIT) doit comporter exactement 6 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitLongueurSectionRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-LongueurSection-03 : 
	 * "la longueur de la section HIT (colonnes [111-116] du HIT) doit être homogène à un entier".<br/>
	 */
	private static Boolean validerRGSectionHitLongueurSectionNumerique03;
	
	/* 28 - longueurRaseCampagne. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>longueurRaseCampagne</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>longueurRaseCampagne</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitLongueurRaseCampagne;
	
	/**
	 * Boolean activant la RG-SectionHit-LongueurRaseCampagne-01 : 
	 * "la longueur en rase campagne de la section HIT (colonnes [117-122] du HIT) doit être renseignée".<br/>
	 */
	private static Boolean validerRGSectionHitLongueurRaseCampagneRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-LongueurRaseCampagne-02 : 
	 * "la longueur en rase campagne de la section HIT (colonnes [117-122] du HIT) doit comporter exactement 6 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitLongueurRaseCampagneRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-LongueurRaseCampagne-03 : 
	 * "la longueur en rase campagne de la section HIT (colonnes [117-122] du HIT) doit être homogène à un entier".<br/>
	 */
	private static Boolean validerRGSectionHitLongueurRaseCampagneNumerique03;
	
	/* 29 - numDepartementRattachement. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>numDepartementRattachement</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numDepartementRattachement</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitNumDepartementRattachement;
	
	/**
	 * Boolean activant la RG-SectionHit-NumDepartementRattachement-01 : 
	 * "le numéro de département de rattachement de la section HIT (colonnes [123-125] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitNumDepartementRattachementRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-NumDepartementRattachement-02 : 
	 * "le numéro de département de rattachement de la section HIT (colonnes [123-125] du HIT) doit comporter exactement 3 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitNumDepartementRattachementRegex02;	
	
	/* 30 - numSectionRattachement. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>numSectionRattachement</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numSectionRattachement</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitNumSectionRattachement;
	
	/**
	 * Boolean activant la RG-SectionHit-NumSectionRattachement-01 : 
	 * "le numéro de section de rattachement de la section HIT (colonnes [126-131] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitNumSectionRattachementRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-NumSectionRattachement-02 : 
	 * "le numéro de section de rattachement de la section HIT (colonnes [126-131] du HIT) doit comporter exactement 6 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitNumSectionRattachementRegex02;
	
	/* 31 - sensRattachement. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>sensRattachement</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>sensRattachement</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitSensRattachement;
	
	/**
	 * Boolean activant la RG-SectionHit-SensRattachement-01 : 
	 * "le sens de la section de rattachement de la section HIT (colonne [132] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitSensRattachementRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-SensRattachement-02 : 
	 * "le sens de la section de rattachement de la section HIT (colonne [132] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitSensRattachementRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-SensRattachement-03 :
	 * "le sens de la section de rattachement de la section HIT (colonne [132] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]".<br/>
	 */
	private static Boolean validerRGSectionHitSensRattachementNomenclature03;
	
	/* 32 - numDepartementLimitrophe. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>numDepartementLimitrophe</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numDepartementLimitrophe</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitNumDepartementLimitrophe;
	
	/**
	 * Boolean activant la RG-SectionHit-NumDepartementLimitrophe-01 : 
	 * "le numéro de département limitrophe de la section HIT (colonnes [133-135] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitNumDepartementLimitropheRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-NumDepartementLimitrophe-02 : 
	 * "le numéro de département limitrophe de la section HIT (colonnes [133-135] du HIT) doit comporter exactement 3 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitNumDepartementLimitropheRegex02;	
	
	/* 33 - numSectionLimitrophe. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>numSectionLimitrophe</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numSectionLimitrophe</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitNumSectionLimitrophe;
	
	/**
	 * Boolean activant la RG-SectionHit-NumSectionLimitrophe-01 : 
	 * "le numéro de section limitrophe de la section HIT (colonnes [136-141] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitNumSectionLimitropheRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-NumSectionLimitrophe-02 : 
	 * "le numéro de section limitrophe de la section HIT (colonnes [136-141] du HIT) doit comporter exactement 6 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitNumSectionLimitropheRegex02;
	
	/* 34 - sensLimitrophe. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>sensLimitrophe</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>sensLimitrophe</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitSensLimitrophe;
	
	/**
	 * Boolean activant la RG-SectionHit-SensLimitrophe-01 : 
	 * "le sens de la section limitrophe de la section HIT (colonne [142] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitSensLimitropheRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-SensLimitrophe-02 : 
	 * "le sens de la section limitrophe de la section HIT (colonne [142] du HIT) doit comporter exactement 1 chiffre".<br/>
	 */
	private static Boolean validerRGSectionHitSensLimitropheRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-SensLimitrophe-03 :
	 * "le sens de la section limitrophe de la section HIT (colonne [142] du HIT) doit respecter une nomenclature [1, 2, 3, 4, 5]".<br/>
	 */
	private static Boolean validerRGSectionHitSensLimitropheNomenclature03;
	
	/* 35 - moisSectionnement. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>moisSectionnement</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>moisSectionnement</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitMoisSectionnement;
	
	/**
	 * Boolean activant la RG-SectionHit-MoisSectionnement-01 : 
	 * "le mois de sectionnement de la section HIT (colonnes [143-144] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitMoisSectionnementRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-MoisSectionnement-02 : 
	 * "le mois de sectionnement de la section HIT (colonnes [143-144] du HIT) doit comporter exactement 2 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitMoisSectionnementRegex02;
	
	/* 36 - anneeSectionnement. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>anneeSectionnement</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>anneeSectionnement</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitAnneeSectionnement;
	
	/**
	 * Boolean activant la RG-SectionHit-AnneeSectionnement-01 : 
	 * "l'année de sectionnement de la section HIT (colonnes [145-146] du HIT) doit être renseignée".<br/>
	 */
	private static Boolean validerRGSectionHitAnneeSectionnementRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-AnneeSectionnement-02 : 
	 * "l'année de sectionnement de la section HIT (colonnes [145-146] du HIT) doit comporter exactement 2 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitAnneeSectionnementRegex02;
	
	/* 37 - zoneLibre2. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>zoneLibre2</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>zoneLibre2</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitZoneLibre2;
	
	/**
	 * Boolean activant la RG-SectionHit-ZoneLibre2-01 : 
	 * "la zone libre de la section HIT (colonnes [147-152] du HIT) doit être renseignée (avec des espaces)".<br/>
	 */
	private static Boolean validerRGSectionHitZoneLibre2Renseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-ZoneLibre2-02 : 
	 * "la zone libre de la section HIT (colonnes [147-152] du HIT) doit comporter exactement 6 espaces".<br/>
	 */
	private static Boolean validerRGSectionHitZoneLibre2Regex02;
	
	/* 38 - mjaN. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>mjaN</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>mjaN</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitMjaN;
	
	/**
	 * Boolean activant la RG-SectionHit-MjaN-01 : 
	 * "le trafic moyen journalier annuel de l'année n de la section HIT (colonnes [153-158] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitMjaNRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-MjaN-02 : 
	 * "le trafic moyen journalier annuel de l'année n de la section HIT (colonnes [153-158] du HIT) doit comporter exactement 6 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitMjaNRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-MjaN-03 : 
	 * "le trafic moyen journalier annuel de l'année n de la section HIT (colonnes [153-158] du HIT) doit être homogène à un entier".<br/>
	 */
	private static Boolean validerRGSectionHitMjaNNumerique03;
	
	/* 39 - modeCalculN. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>modeCalculN</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>modeCalculN</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitModeCalculN;
	
	/**
	 * Boolean activant la RG-SectionHit-ModeCalculN-01 : 
	 * "le mode de calcul des trafics de l'année n de la section HIT (colonne [159] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitModeCalculNRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-ModeCalculN-02 : 
	 * "le mode de calcul des trafics de l'année n de la section HIT (colonne [159] du HIT) doit comporter exactement 1 caractère (ou espace)".<br/>
	 */
	private static Boolean validerRGSectionHitModeCalculNRegex02;
	
	/* 40 - pcPLN. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>pcPLN</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>pcPLN</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitPcPLN;
	
	/**
	 * Boolean activant la RG-SectionHit-PcPLN-01 : 
	 * "le pourcentage de trafic poids lourds annuel de l'année n de la section HIT (colonnes [160-162] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitPcPLNRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-PcPLN-02 : 
	 * "le pourcentage de trafic poids lourds annuel de l'année n de la section HIT (colonnes [160-162] du HIT) doit comporter exactement 3 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitPcPLNRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-PcPLN-03 : 
	 * "le pourcentage de trafic poids lourds annuel de l'année n de la section HIT (colonnes [160-162] du HIT) doit être homogène à un entier".<br/>
	 */
	private static Boolean validerRGSectionHitPcPLNNumerique03;
	
	/* 41 - evaluationPLN. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>evaluationPLN</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>evaluationPLN</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitEvaluationPLN;
	
	/**
	 * Boolean activant la RG-SectionHit-EvaluationPLN-01 : 
	 * "l'évaluation des pourcentages de trafic poids lourds de l'année n de la section HIT (colonne [163] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitEvaluationPLNRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-EvaluationPLN-02 : 
	 * "l'évaluation des pourcentages de trafic poids lourds de l'année n de la section HIT (colonne [163] du HIT) doit comporter exactement 1 caractère (ou espace)".<br/>
	 */
	private static Boolean validerRGSectionHitEvaluationPLNRegex02;
	
	/* 42 - pcNuitAnnuelN. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>pcNuitAnnuelN</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>pcNuitAnnuelN</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitPcNuitAnnuelN;
	
	/**
	 * Boolean activant la RG-SectionHit-PcNuitAnnuelN-01 : 
	 * "le pourcentage de trafic de nuit tous véhicules annuel de l'année n de la section HIT (colonnes [164-166] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitPcNuitAnnuelNRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-PcNuitAnnuelN-02 : 
	 * "le pourcentage de trafic de nuit tous véhicules annuel de l'année n de la section HIT (colonnes [164-166] du HIT) doit comporter exactement 3 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitPcNuitAnnuelNRegex02;
	
	/**
	 * Boolean activant la RG-SectionHit-PcNuitAnnuelN-03 : 
	 * "le pourcentage de trafic de nuit tous véhicules annuel de l'année n de la section HIT (colonnes [164-166] du HIT) doit être homogène à un entier".<br/>
	 */
	private static Boolean validerRGSectionHitPcNuitAnnuelNNumerique03;
	
	/* 43 - indiceFiabiliteMjaN. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>indiceFiabiliteMjaN</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>indiceFiabiliteMjaN</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitIndiceFiabiliteMjaN;
	
	/**
	 * Boolean activant la RG-SectionHit-IndiceFiabiliteMjaN-01 : 
	 * "l'indice de fiabilité de la TMJA de l'année n de la section HIT (colonne [167] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitIndiceFiabiliteMjaNRenseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-IndiceFiabiliteMjaN-02 : 
	 * "l'indice de fiabilité de la TMJA de l'année n de la section HIT (colonne [167] du HIT) doit comporter exactement 1 caractère (ou espace)".<br/>
	 */
	private static Boolean validerRGSectionHitIndiceFiabiliteMjaNRegex02;
	
	/* 44 - mjmNmois01. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>mjmNmois01</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>mjmNmois01</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitMjmNmois01;
	
	/**
	 * Boolean activant la RG-SectionHit-MjmNmois01-01 : 
	 * "la moyenne journalière mensuelle du mois de janvier (01) de l'année n en véhicules/jour de la section HIT (colonnes [168-173] du HIT) doit être renseignée".<br/>
	 */
	private static Boolean validerRGSectionHitMjmNmois01Renseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-MjmNmois01-02 : 
	 * "la moyenne journalière mensuelle du mois de janvier (01) de l'année n en véhicules/jour de la section HIT (colonnes [168-173] du HIT) doit comporter exactement 6 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitMjmNmois01Regex02;
	
	/**
	 * Boolean activant la RG-SectionHit-MjmNmois01-03 : 
	 * "la moyenne journalière mensuelle du mois de janvier (01) de l'année n en véhicules/jour de la section HIT (colonnes [168-173] du HIT) doit être homogène à un entier".<br/>
	 */
	private static Boolean validerRGSectionHitMjmNmois01Numerique03;
	
	/* 45 - pcNuitNmois01. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>pcNuitNmois01</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>pcNuitNmois01</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitPcNuitNmois01;
	
	/**
	 * Boolean activant la RG-SectionHit-PcNuitNmois01-01 : 
	 * "le pourcentage de trafic de nuit tous véhicules mensuel du mois de janvier (01) de l'année n de la section HIT (colonnes [174-176] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitPcNuitNmois01Renseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-PcNuitNmois01-02 : 
	 * "le pourcentage de trafic de nuit tous véhicules mensuel du mois de janvier (01) de l'année n de la section HIT (colonnes [174-176] du HIT) doit comporter exactement 3 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitPcNuitNmois01Regex02;
	
	/**
	 * Boolean activant la RG-SectionHit-PcNuitNmois01-03 : 
	 * "le pourcentage de trafic de nuit tous véhicules mensuel du mois de janvier (01) de l'année n de la section HIT (colonnes [174-176] du HIT) doit être homogène à un entier".<br/>
	 */
	private static Boolean validerRGSectionHitPcNuitNmois01Numerique03;
	
	/* 46 - mjmNmois02. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>mjmNmois02</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>mjmNmois02</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitMjmNmois02;
	
	/**
	 * Boolean activant la RG-SectionHit-MjmNmois02-01 : 
	 * "la moyenne journalière mensuelle du mois de février (02) de l'année n en véhicules/jour de la section HIT (colonnes [177-182] du HIT) doit être renseignée".<br/>
	 */
	private static Boolean validerRGSectionHitMjmNmois02Renseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-MjmNmois02-02 : 
	 * "la moyenne journalière mensuelle du mois de février (02) de l'année n en véhicules/jour de la section HIT (colonnes [177-182] du HIT) doit comporter exactement 6 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitMjmNmois02Regex02;
	
	/**
	 * Boolean activant la RG-SectionHit-MjmNmois02-03 : 
	 * "la moyenne journalière mensuelle du mois de février (02) de l'année n en véhicules/jour de la section HIT (colonnes [177-182] du HIT) doit être homogène à un entier".<br/>
	 */
	private static Boolean validerRGSectionHitMjmNmois02Numerique03;
	
	/* 47 - pcNuitNmois02. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>pcNuitNmois02</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>pcNuitNmois02</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitPcNuitNmois02;
	
	/**
	 * Boolean activant la RG-SectionHit-PcNuitNmois02-01 : 
	 * "le pourcentage de trafic de nuit tous véhicules mensuel du mois de février (02) de l'année n de la section HIT (colonnes [183-185] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitPcNuitNmois02Renseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-PcNuitNmois02-02 : 
	 * "le pourcentage de trafic de nuit tous véhicules mensuel du mois de février (02) de l'année n de la section HIT (colonnes [183-185] du HIT) doit comporter exactement 3 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitPcNuitNmois02Regex02;
	
	/**
	 * Boolean activant la RG-SectionHit-PcNuitNmois02-03 : 
	 * "le pourcentage de trafic de nuit tous véhicules mensuel du mois de février (02) de l'année n de la section HIT (colonnes [183-185] du HIT) doit être homogène à un entier".<br/>
	 */
	private static Boolean validerRGSectionHitPcNuitNmois02Numerique03;
	
	/* 48 - mjmNmois03. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>mjmNmois03</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>mjmNmois03</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitMjmNmois03;
	
	/**
	 * Boolean activant la RG-SectionHit-MjmNmois03-01 : 
	 * "la moyenne journalière mensuelle du mois de mars (03) de l'année n en véhicules/jour de la section HIT (colonnes [186-191] du HIT) doit être renseignée".<br/>
	 */
	private static Boolean validerRGSectionHitMjmNmois03Renseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-MjmNmois03-02 : 
	 * "la moyenne journalière mensuelle du mois de mars (03) de l'année n en véhicules/jour de la section HIT (colonnes [186-191] du HIT) doit comporter exactement 6 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitMjmNmois03Regex02;
	
	/**
	 * Boolean activant la RG-SectionHit-MjmNmois03-03 : 
	 * "la moyenne journalière mensuelle du mois de mars (03) de l'année n en véhicules/jour de la section HIT (colonnes [186-191] du HIT) doit être homogène à un entier".<br/>
	 */
	private static Boolean validerRGSectionHitMjmNmois03Numerique03;
	
	/* 49 - pcNuitNmois03. *******/
	/**
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>pcNuitNmois03</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>pcNuitNmois03</i> 
	 * de SectionHit.<br/>
	 */
	private static Boolean validerRGSectionHitPcNuitNmois03;
	
	/**
	 * Boolean activant la RG-SectionHit-PcNuitNmois03-01 : 
	 * "le pourcentage de trafic de nuit tous véhicules mensuel du mois de mars (03) de l'année n de la section HIT (colonnes [192-194] du HIT) doit être renseigné".<br/>
	 */
	private static Boolean validerRGSectionHitPcNuitNmois03Renseigne01;
	
	/**
	 * Boolean activant la RG-SectionHit-PcNuitNmois03-02 : 
	 * "le pourcentage de trafic de nuit tous véhicules mensuel du mois de mars (03) de l'année n de la section HIT (colonnes [192-194] du HIT) doit comporter exactement 3 chiffres".<br/>
	 */
	private static Boolean validerRGSectionHitPcNuitNmois03Regex02;
	
	/**
	 * Boolean activant la RG-SectionHit-PcNuitNmois03-03 : 
	 * "le pourcentage de trafic de nuit tous véhicules mensuel du mois de mars (03) de l'année n de la section HIT (colonnes [192-194] du HIT) doit être homogène à un entier".<br/>
	 */
	private static Boolean validerRGSectionHitPcNuitNmois03Numerique03;
	
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
	 */
	private static void ajouterPropertiesEnDur() {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			/* 1 - numDepartement. **********/
			/* ajoute le validerRGSectionHitNumDepartement 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT
						, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumDepartementRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumDepartementRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENT_REGEX_02
						, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENT_REGEX_02_EN_DUR);
			
			/* 2 - numSection. ***********/
			/* ajoute le validerRGSectionHitNumSection 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMSECTION
						, STRING_VALIDER_SECTIONHIT_NUMSECTION_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumSectionRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMSECTION_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_NUMSECTION_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumSectionRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMSECTION_REGEX_02
						, STRING_VALIDER_SECTIONHIT_NUMSECTION_REGEX_02_EN_DUR);
			
			/* 3 - sens. ***********/
			/* ajoute le validerRGSectionHitSens 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_SENS
						, STRING_VALIDER_SECTIONHIT_SENS_EN_DUR);
			
			/* ajoute le validerRGSectionHitSensRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_SENS_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_SENS_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitSensRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_SENS_REGEX_02
						, STRING_VALIDER_SECTIONHIT_SENS_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitSensNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_SENS_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_SENS_NOMENCLATURE_03_EN_DUR);
			
			/* 4 - nature. ***********/
			/* ajoute le validerRGSectionHitNature 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NATURE
						, STRING_VALIDER_SECTIONHIT_NATURE_EN_DUR);
			
			/* ajoute le validerRGSectionHitNatureRenseigne01 
			 * par défaut stockée en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NATURE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_NATURE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitNatureRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NATURE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_NATURE_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitNatureNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NATURE_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_NATURE_NOMENCLATURE_03_EN_DUR);
			
			/* 5 - classe. ***********/
			/* ajoute le validerRGSectionHitClasse 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSE
						, STRING_VALIDER_SECTIONHIT_CLASSE_EN_DUR);
			
			/* ajoute le validerRGSectionHitClasseRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_CLASSE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitClasseRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_CLASSE_REGEX_02_EN_DUR);
			
			/* 6 - anneeTraitement. *******/
			/* ajoute le validerRGSectionHitAnneeTraitement 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT
						, STRING_VALIDER_SECTIONHIT_ANNEETRAITEMENT_EN_DUR);
			
			/* ajoute le validerRGSectionHitAnneeTraitementRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitAnneeTraitementRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT_REGEX_02
						, STRING_VALIDER_SECTIONHIT_ANNEETRAITEMENT_REGEX_02_EN_DUR);
			
			/* 7 - zoneLibre1. *******/
			/* ajoute le validerRGSectionHitZoneLibre1 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ZONELIBRE1
						, STRING_VALIDER_SECTIONHIT_ZONELIBRE1_EN_DUR);
			
			/* ajoute le validerRGSectionHitZoneLibre1Renseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitZoneLibre1Regex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ZONELIBRE1_REGEX_02
						, STRING_VALIDER_SECTIONHIT_ZONELIBRE1_REGEX_02_EN_DUR);
			
			/* 8 - numRoute. *******/
			/* ajoute le validerRGSectionHitNumRoute 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMROUTE
						, STRING_VALIDER_SECTIONHIT_NUMROUTE_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumRouteRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMROUTE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_NUMROUTE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumRouteRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMROUTE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_NUMROUTE_REGEX_02_EN_DUR);
			
			/* 9 - indiceNumRoute. *******/
			/* ajoute le validerRGSectionHitIndiceNumRoute 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_INDICENUMROUTE
						, STRING_VALIDER_SECTIONHIT_INDICENUMROUTE_EN_DUR);
			
			/* ajoute le validerRGSectionHitIndiceNumRouteRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitIndiceNumRouteRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_INDICENUMROUTE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_INDICENUMROUTE_REGEX_02_EN_DUR);
			
			/* 10 - indiceLettreRoute. *******/
			/* ajoute le validerRGSectionHitIndiceLettreRoute 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE
						, STRING_VALIDER_SECTIONHIT_INDICELETTREROUTE_EN_DUR);
			
			/* ajoute le validerRGSectionHitIndiceLettreRouteRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitIndiceLettreRouteRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_INDICELETTREROUTE_REGEX_02_EN_DUR);
			
			/* 11 - categorieAdminRoute. *******/
			/* ajoute le validerRGSectionHitCategorieAdminRoute 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE
						, STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_EN_DUR);
			
			/* ajoute le validerRGSectionHitCategorieAdminRouteRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitCategorieAdminRouteRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitCategorieAdminRouteNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03_EN_DUR);
			
			/* 12 - typeComptage. *******/
			/* ajoute le validerRGSectionHitTypeComptage 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE
						, STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_EN_DUR);
			
			/* ajoute le validerRGSectionHitTypeComptageRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitTypeComptageRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitTypeComptageNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03_EN_DUR);
			
			/* 13 - classementRoute. *******/
			/* ajoute le validerRGSectionHitClassementRoute 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE
						, STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_EN_DUR);
			
			/* ajoute le validerRGSectionHitClassementRouteRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitClassementRouteRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitClassementRouteNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03_EN_DUR);
			
			/* 14 - classeLargeurChausseeU. *******/
			/* ajoute le validerRGSectionHitClasseLargeurChausseeU 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU
						, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_EN_DUR);
			
			/* ajoute le validerRGSectionHitClasseLargeurChausseeURenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitClasseLargeurChausseeURegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02
						, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitClasseLargeurChausseeUNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03_EN_DUR);
			
			/* 15 - classeLargeurChausseesS. *******/
			/* ajoute le validerRGSectionHitClasseLargeurChausseesS 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS
						, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_EN_DUR);
			
			/* ajoute le validerRGSectionHitClasseLargeurChausseesSRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitClasseLargeurChausseesSRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02
						, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitClasseLargeurChausseesSNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03_EN_DUR);
			
			/* 16 - typeReseau. *******/
			/* ajoute le validerRGSectionHitTypeReseau 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_TYPERESEAU
						, STRING_VALIDER_SECTIONHIT_TYPERESEAU_EN_DUR);
			
			/* ajoute le validerRGSectionHitTypeReseauRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_TYPERESEAU_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_TYPERESEAU_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitTypeReseauRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_TYPERESEAU_REGEX_02
						, STRING_VALIDER_SECTIONHIT_TYPERESEAU_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitTypeReseauNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03_EN_DUR);
			
			/* 17 - pRoupK. *******/
			/* ajoute le validerRGSectionHitPRoupK 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PROUPK
						, STRING_VALIDER_SECTIONHIT_PROUPK_EN_DUR);
			
			/* ajoute le validerRGSectionHitPRoupKRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PROUPK_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_PROUPK_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitPRoupKRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PROUPK_REGEX_02
						, STRING_VALIDER_SECTIONHIT_PROUPK_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitPRoupKNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PROUPK_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_PROUPK_NOMENCLATURE_03_EN_DUR);
			
			/* 18 - lieuDitOrigine. *******/
			/* ajoute le validerRGSectionHitLieuDitOrigine 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE
						, STRING_VALIDER_SECTIONHIT_LIEUDITORIGINE_EN_DUR);
			
			/* ajoute le validerRGSectionHitLieuDitOrigineRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitLieuDitOrigineRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_LIEUDITORIGINE_REGEX_02_EN_DUR);
			
			/* 19 - prOrigine. *******/
			/* ajoute le validerRGSectionHitPrOrigine 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PRORIGINE
						, STRING_VALIDER_SECTIONHIT_PRORIGINE_EN_DUR);
			
			/* ajoute le validerRGSectionHitPrOrigineRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PRORIGINE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_PRORIGINE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitPrOrigineRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PRORIGINE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_PRORIGINE_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitPrOrigineNumerique03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PRORIGINE_NUMERIQUE_03
						, STRING_VALIDER_SECTIONHIT_PRORIGINE_NUMERIQUE_03_EN_DUR);
			
			/* 20 - absOrigine. *******/
			/* ajoute le validerRGSectionHitAbsOrigine 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ABSORIGINE
						, STRING_VALIDER_SECTIONHIT_ABSORIGINE_EN_DUR);
			
			/* ajoute le validerRGSectionHitAbsOrigineRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ABSORIGINE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_ABSORIGINE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitAbsOrigineRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ABSORIGINE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_ABSORIGINE_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitAbsOrigineNumerique03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ABSORIGINE_NUMERIQUE_03
						, STRING_VALIDER_SECTIONHIT_ABSORIGINE_NUMERIQUE_03_EN_DUR);
			
			/* 21 - lieuDitExtremite. *******/
			/* ajoute le validerRGSectionHitLieuDitExtremite 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LIEUDITEXTREMITE
						, STRING_VALIDER_SECTIONHIT_LIEUDITEXTREMITE_EN_DUR);
			
			/* ajoute le validerRGSectionHitLieuDitExtremiteRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitLieuDitExtremiteRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02_EN_DUR);
			
			/* 22 - prExtremite. *******/
			/* ajoute le validerRGSectionHitPrExtremite 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PREXTREMITE
						, STRING_VALIDER_SECTIONHIT_PREXTREMITE_EN_DUR);
			
			/* ajoute le validerRGSectionHitPrExtremiteRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PREXTREMITE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_PREXTREMITE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitPrExtremiteRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PREXTREMITE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_PREXTREMITE_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitPrExtremiteNumerique03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PREXTREMITE_NUMERIQUE_03
						, STRING_VALIDER_SECTIONHIT_PREXTREMITE_NUMERIQUE_03_EN_DUR);
			
			/* 23 - absExtremite. *******/
			/* ajoute le validerRGSectionHitAbsExtremite 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ABSEXTREMITE
						, STRING_VALIDER_SECTIONHIT_ABSEXTREMITE_EN_DUR);
			
			/* ajoute le validerRGSectionHitAbsExtremiteRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitAbsExtremiteRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ABSEXTREMITE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_ABSEXTREMITE_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitAbsExtremiteNumerique03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03
						, STRING_VALIDER_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03_EN_DUR);
			
			/* 24 - lieuDitComptage. *******/
			/* ajoute le validerRGSectionHitLieuDitComptage 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE
						, STRING_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE_EN_DUR);
			
			/* ajoute le validerRGSectionHitLieuDitComptageRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitLieuDitComptageRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02_EN_DUR);
			
			/* 25 - prComptage. *******/
			/* ajoute le validerRGSectionHitPrComptage 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PRCOMPTAGE
						, STRING_VALIDER_SECTIONHIT_PRCOMPTAGE_EN_DUR);
			
			/* ajoute le validerRGSectionHitPrComptageRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitPrComptageRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PRCOMPTAGE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_PRCOMPTAGE_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitPrComptageNumerique03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03
						, STRING_VALIDER_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03_EN_DUR);
			
			/* 26 - absComptage. *******/
			/* ajoute le validerRGSectionHitAbsComptage 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ABSCOMPTAGE
						, STRING_VALIDER_SECTIONHIT_ABSCOMPTAGE_EN_DUR);
			
			/* ajoute le validerRGSectionHitAbsComptageRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitAbsComptageRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ABSCOMPTAGE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_ABSCOMPTAGE_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitAbsComptageNumerique03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03
						, STRING_VALIDER_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03_EN_DUR);
			
			/* 27 - longueurSection. *******/
			/* ajoute le validerRGSectionHitLongueurSection 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LONGUEURSECTION
						, STRING_VALIDER_SECTIONHIT_LONGUEURSECTION_EN_DUR);
			
			/* ajoute le validerRGSectionHitLongueurSectionRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LONGUEURSECTION_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_LONGUEURSECTION_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitLongueurSectionRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LONGUEURSECTION_REGEX_02
						, STRING_VALIDER_SECTIONHIT_LONGUEURSECTION_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitLongueurSectionNumerique03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LONGUEURSECTION_NUMERIQUE_03
						, STRING_VALIDER_SECTIONHIT_LONGUEURSECTION_NUMERIQUE_03_EN_DUR);
			
			/* 28 - longueurRaseCampagne. *******/
			/* ajoute le validerRGSectionHitLongueurRaseCampagne 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE
						, STRING_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_EN_DUR);
			
			/* ajoute le validerRGSectionHitLongueurRaseCampagneRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitLongueurRaseCampagneRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitLongueurRaseCampagneNumerique03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_NUMERIQUE_03
						, STRING_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_NUMERIQUE_03_EN_DUR);
			
			/* 29 - numDepartementRattachement. *******/
			/* ajoute le validerRGSectionHitNumDepartementRattachement 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT
						, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumDepartementRattachementRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumDepartementRattachementRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_REGEX_02
						, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_REGEX_02_EN_DUR);
			
			/* 30 - numSectionRattachement. *******/
			/* ajoute le validerRGSectionHitNumSectionRattachement 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT
						, STRING_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumSectionRattachementRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumSectionRattachementRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT_REGEX_02
						, STRING_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT_REGEX_02_EN_DUR);
			
			/* 31 - sensRattachement. *******/
			/* ajoute le validerRGSectionHitSensRattachement 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_SENSRATTACHEMENT
						, STRING_VALIDER_SECTIONHIT_SENSRATTACHEMENT_EN_DUR);
			
			/* ajoute le validerRGSectionHitSensRattachementRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_SENSRATTACHEMENT_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_SENSRATTACHEMENT_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitSensRattachementRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_SENSRATTACHEMENT_REGEX_02
						, STRING_VALIDER_SECTIONHIT_SENSRATTACHEMENT_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitSensRattachementNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_SENSRATTACHEMENT_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_SENSRATTACHEMENT_NOMENCLATURE_03_EN_DUR);
			
			/* 29 - numDepartementLimitrophe. *******/
			/* ajoute le validerRGSectionHitNumDepartementLimitrophe 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE
						, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumDepartementLimitropheRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumDepartementLimitropheRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_REGEX_02_EN_DUR);
			
			/* 30 - numSectionLimitrophe. *******/
			/* ajoute le validerRGSectionHitNumSectionLimitrophe 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE
						, STRING_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumSectionLimitropheRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitNumSectionLimitropheRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE_REGEX_02_EN_DUR);
			
			/* 31 - sensLimitrophe. *******/
			/* ajoute le validerRGSectionHitSensLimitrophe 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_SENSLIMITROPHE
						, STRING_VALIDER_SECTIONHIT_SENSLIMITROPHE_EN_DUR);
			
			/* ajoute le validerRGSectionHitSensLimitropheRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_SENSLIMITROPHE_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_SENSLIMITROPHE_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitSensLimitropheRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_SENSLIMITROPHE_REGEX_02
						, STRING_VALIDER_SECTIONHIT_SENSLIMITROPHE_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitSensLimitropheNomenclature03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_SENSLIMITROPHE_NOMENCLATURE_03
						, STRING_VALIDER_SECTIONHIT_SENSLIMITROPHE_NOMENCLATURE_03_EN_DUR);
			
			/* 35 - moisSectionnement. *******/
			/* ajoute le validerRGSectionHitMoisSectionnement 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MOISSECTIONNEMENT
						, STRING_VALIDER_SECTIONHIT_MOISSECTIONNEMENT_EN_DUR);
			
			/* ajoute le validerRGSectionHitMoisSectionnementRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MOISSECTIONNEMENT_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_MOISSECTIONNEMENT_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitMoisSectionnementRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MOISSECTIONNEMENT_REGEX_02
						, STRING_VALIDER_SECTIONHIT_MOISSECTIONNEMENT_REGEX_02_EN_DUR);
			
			/* 36 - anneeSectionnement. *******/
			/* ajoute le validerRGSectionHitAnneeSectionnement 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT
						, STRING_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT_EN_DUR);
			
			/* ajoute le validerRGSectionHitAnneeSectionnementRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitAnneeSectionnementRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT_REGEX_02
						, STRING_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT_REGEX_02_EN_DUR);
			
			/* 37 - zoneLibre2. *******/
			/* ajoute le validerRGSectionHitZoneLibre2 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ZONELIBRE2
						, STRING_VALIDER_SECTIONHIT_ZONELIBRE2_EN_DUR);
			
			/* ajoute le validerRGSectionHitZoneLibre2Renseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ZONELIBRE2_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_ZONELIBRE2_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitZoneLibre2Regex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_ZONELIBRE2_REGEX_02
						, STRING_VALIDER_SECTIONHIT_ZONELIBRE2_REGEX_02_EN_DUR);
			
			/* 38 - mjaN. *******/
			/* ajoute le validerRGSectionHitMjaN 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MJAN
						, STRING_VALIDER_SECTIONHIT_MJAN_EN_DUR);
			
			/* ajoute le validerRGSectionHitMjaNRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MJAN_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_MJAN_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitMjaNRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MJAN_REGEX_02
						, STRING_VALIDER_SECTIONHIT_MJAN_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitMjaNNumerique03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MJAN_NUMERIQUE_03
						, STRING_VALIDER_SECTIONHIT_MJAN_NUMERIQUE_03_EN_DUR);
			
			/* 39 - modeCalculN. *******/
			/* ajoute le validerRGSectionHitModeCalculN 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MODECALCULN
						, STRING_VALIDER_SECTIONHIT_MODECALCULN_EN_DUR);
			
			/* ajoute le validerRGSectionHitModeCalculNRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MODECALCULN_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_MODECALCULN_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitModeCalculNRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MODECALCULN_REGEX_02
						, STRING_VALIDER_SECTIONHIT_MODECALCULN_REGEX_02_EN_DUR);
			
			/* 40 - pcPLN. *******/
			/* ajoute le validerRGSectionHitPcPLN 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCPLN
						, STRING_VALIDER_SECTIONHIT_PCPLN_EN_DUR);
			
			/* ajoute le validerRGSectionHitPcPLNRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCPLN_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_PCPLN_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitPcPLNRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCPLN_REGEX_02
						, STRING_VALIDER_SECTIONHIT_PCPLN_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitPcPLNNumerique03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCPLN_NUMERIQUE_03
						, STRING_VALIDER_SECTIONHIT_PCPLN_NUMERIQUE_03_EN_DUR);
			
			/* 41 - evaluationPLN. *******/
			/* ajoute le validerRGSectionHitEvaluationPLN 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_EVALUATIONPLN
						, STRING_VALIDER_SECTIONHIT_EVALUATIONPLN_EN_DUR);
			
			/* ajoute le validerRGSectionHitEvaluationPLNRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_EVALUATIONPLN_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_EVALUATIONPLN_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitEvaluationPLNRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_EVALUATIONPLN_REGEX_02
						, STRING_VALIDER_SECTIONHIT_EVALUATIONPLN_REGEX_02_EN_DUR);
			
			/* 42 - pcNuitAnnuelN. *******/
			/* ajoute le validerRGSectionHitPcNuitAnnuelN 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCNUITANNUELN
						, STRING_VALIDER_SECTIONHIT_PCNUITANNUELN_EN_DUR);
			
			/* ajoute le validerRGSectionHitPcNuitAnnuelNRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCNUITANNUELN_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_PCNUITANNUELN_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitPcNuitAnnuelNRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCNUITANNUELN_REGEX_02
						, STRING_VALIDER_SECTIONHIT_PCNUITANNUELN_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitPcNuitAnnuelNNumerique03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCNUITANNUELN_NUMERIQUE_03
						, STRING_VALIDER_SECTIONHIT_PCNUITANNUELN_NUMERIQUE_03_EN_DUR);
			
			/* 43 - indiceFiabiliteMjaN. *******/
			/* ajoute le validerRGSectionHitIndiceFiabiliteMjaN 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN
						, STRING_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN_EN_DUR);
			
			/* ajoute le validerRGSectionHitIndiceFiabiliteMjaNRenseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitIndiceFiabiliteMjaNRegex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN_REGEX_02
						, STRING_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN_REGEX_02_EN_DUR);
			
			/* 44 - mjmNmois01. *******/
			/* ajoute le validerRGSectionHitMjmNmois01 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MJMNMOIS01
						, STRING_VALIDER_SECTIONHIT_MJMNMOIS01_EN_DUR);
			
			/* ajoute le validerRGSectionHitMjmNmois01Renseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MJMNMOIS01_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_MJMNMOIS01_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitMjmNmois01Regex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MJMNMOIS01_REGEX_02
						, STRING_VALIDER_SECTIONHIT_MJMNMOIS01_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitMjmNmois01Numerique03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MJMNMOIS01_NUMERIQUE_03
						, STRING_VALIDER_SECTIONHIT_MJMNMOIS01_NUMERIQUE_03_EN_DUR);
			
			/* 45 - pcNuitNmois01. *******/
			/* ajoute le validerRGSectionHitPcNuitNmois01 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCNUITNMOIS01
						, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS01_EN_DUR);
			
			/* ajoute le validerRGSectionHitPcNuitNmois01Renseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCNUITNMOIS01_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS01_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitPcNuitNmois01Regex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCNUITNMOIS01_REGEX_02
						, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS01_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitPcNuitNmois01Numerique03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCNUITNMOIS01_NUMERIQUE_03
						, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS01_NUMERIQUE_03_EN_DUR);
			
			/* 46 - mjmNmois02. *******/
			/* ajoute le validerRGSectionHitMjmNmois02 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MJMNMOIS02
						, STRING_VALIDER_SECTIONHIT_MJMNMOIS02_EN_DUR);
			
			/* ajoute le validerRGSectionHitMjmNmois02Renseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MJMNMOIS02_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_MJMNMOIS02_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitMjmNmois02Regex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MJMNMOIS02_REGEX_02
						, STRING_VALIDER_SECTIONHIT_MJMNMOIS02_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitMjmNmois02Numerique03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MJMNMOIS02_NUMERIQUE_03
						, STRING_VALIDER_SECTIONHIT_MJMNMOIS02_NUMERIQUE_03_EN_DUR);
			
			/* 47 - pcNuitNmois02. *******/
			/* ajoute le validerRGSectionHitPcNuitNmois02 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCNUITNMOIS02
						, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS02_EN_DUR);
			
			/* ajoute le validerRGSectionHitPcNuitNmois02Renseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCNUITNMOIS02_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS02_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitPcNuitNmois02Regex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCNUITNMOIS02_REGEX_02
						, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS02_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitPcNuitNmois02Numerique03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCNUITNMOIS02_NUMERIQUE_03
						, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS02_NUMERIQUE_03_EN_DUR);
			
			/* 48 - mjmNmois03. *******/
			/* ajoute le validerRGSectionHitMjmNmois03 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MJMNMOIS03
						, STRING_VALIDER_SECTIONHIT_MJMNMOIS03_EN_DUR);
			
			/* ajoute le validerRGSectionHitMjmNmois03Renseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MJMNMOIS03_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_MJMNMOIS03_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitMjmNmois03Regex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MJMNMOIS03_REGEX_02
						, STRING_VALIDER_SECTIONHIT_MJMNMOIS03_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitMjmNmois03Numerique03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_MJMNMOIS03_NUMERIQUE_03
						, STRING_VALIDER_SECTIONHIT_MJMNMOIS03_NUMERIQUE_03_EN_DUR);
			
			/* 49 - pcNuitNmois03. *******/
			/* ajoute le validerRGSectionHitPcNuitNmois03 
			 * par défaut stocké en dur. */
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCNUITNMOIS03
						, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS03_EN_DUR);
			
			/* ajoute le validerRGSectionHitPcNuitNmois03Renseigne01 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCNUITNMOIS03_RENSEIGNE_01
						, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS03_RENSEIGNE_01_EN_DUR);
			
			/* ajoute le validerRGSectionHitPcNuitNmois03Regex02 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCNUITNMOIS03_REGEX_02
						, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS03_REGEX_02_EN_DUR);
			
			/* ajoute le validerRGSectionHitPcNuitNmois03Numerique03 
			 * par défaut stocké en dur.*/
			preferences.setProperty(
					KEY_VALIDER_SECTIONHIT_PCNUITNMOIS03_NUMERIQUE_03
						, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS03_NUMERIQUE_03_EN_DUR);
			
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
	
	
	
	/* 1 - numDepartement. **********/
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
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
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
	
	
	
	/* 2 - numSection. ****************/
	/**
	 * retourne le <code>validerRGSectionHitNumSection</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>numSection</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numSection</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSection stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumSection 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumSection() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitNumSection
					, fournirKeyValiderRGSectionHitNumSection()
					, STRING_VALIDER_SECTIONHIT_NUMSECTION_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumSection().________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumSection 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numsection".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMSECTION : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumSection() {
		return KEY_VALIDER_SECTIONHIT_NUMSECTION;
	} // Fin de fournirKeyValiderRGSectionHitNumSection()._____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumSection par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitNumSection 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumSection stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumSection : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumSection() 
													throws Exception {
		return fournirValiderRGSectionHitNumSection();
	} // Fin de getValiderRGSectionHitNumSection().____________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumSection par défaut 
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
	* valeur à passer à validerRGSectionHitNumSection.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumSection(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitNumSection
							, fournirKeyValiderRGSectionHitNumSection());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumSection(...)._________________



	/**
	 * retourne le validerRGSectionHitNumSectionRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSectionRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumSectionRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumSectionRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumSectionRenseigne01
					, fournirKeyValiderRGSectionHitNumSectionRenseigne01()
					, STRING_VALIDER_SECTIONHIT_NUMSECTION_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumSectionRenseigne01()._______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumSectionRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numsection.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMSECTION_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumSectionRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_NUMSECTION_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitNumSectionRenseigne01().__



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumSectionRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSectionRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumSectionRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumSectionRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumSectionRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitNumSectionRenseigne01();
	} // Fin de getValiderRGSectionHitNumSectionRenseigne01()._________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumSectionRenseigne01 
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
	* valeur à passer à validerRGSectionHitNumSectionRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumSectionRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumSectionRenseigne01
							, fournirKeyValiderRGSectionHitNumSectionRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumSectionRenseigne01(...).______



	/**
	 * retourne le validerRGSectionHitNumSectionRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSectionRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumSectionRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumSectionRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumSectionRegex02
					, fournirKeyValiderRGSectionHitNumSectionRegex02()
					, STRING_VALIDER_SECTIONHIT_NUMSECTION_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumSectionRegex02()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumSectionRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numsection.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMSECTION_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumSectionRegex02() {
		return KEY_VALIDER_SECTIONHIT_NUMSECTION_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitNumSectionRegex02().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumSectionRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSectionRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumSectionRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumSectionRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumSectionRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitNumSectionRegex02();
	} // Fin de getValiderRGSectionHitNumSectionRegex02()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumSectionRegex02 
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
	* valeur à passer à validerRGSectionHitNumSectionRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumSectionRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumSectionRegex02
							, fournirKeyValiderRGSectionHitNumSectionRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumSectionRegex02(...).__________
	
	
	
	/* 3 - sens. */
	/**
	 * retourne le <code>validerRGSectionHitSens</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>sens</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>sens</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitSens stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitSens 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitSens() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitSens
					, fournirKeyValiderRGSectionHitSens()
					, STRING_VALIDER_SECTIONHIT_SENS_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitSens().________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitSens 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.sens".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_SENS : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitSens() {
		return KEY_VALIDER_SECTIONHIT_SENS;
	} // Fin de fournirKeyValiderRGSectionHitSens()._____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitSens par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitSens 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitSens stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitSens : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitSens() 
													throws Exception {
		return fournirValiderRGSectionHitSens();
	} // Fin de getValiderRGSectionHitSens().____________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitSens par défaut 
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
	* valeur à passer à validerRGSectionHitSens.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitSens(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitSens
							, fournirKeyValiderRGSectionHitSens());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitSens(...)._________________



	/**
	 * retourne le validerRGSectionHitSensRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitSensRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitSensRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitSensRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitSensRenseigne01
					, fournirKeyValiderRGSectionHitSensRenseigne01()
					, STRING_VALIDER_SECTIONHIT_SENS_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitSensRenseigne01()._______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitSensRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.sens.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_SENS_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitSensRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_SENS_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitSensRenseigne01().__



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitSensRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitSensRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitSensRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitSensRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitSensRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitSensRenseigne01();
	} // Fin de getValiderRGSectionHitSensRenseigne01()._________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitSensRenseigne01 
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
	* valeur à passer à validerRGSectionHitSensRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitSensRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitSensRenseigne01
							, fournirKeyValiderRGSectionHitSensRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitSensRenseigne01(...).______



	/**
	 * retourne le validerRGSectionHitSensRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitSensRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitSensRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitSensRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitSensRegex02
					, fournirKeyValiderRGSectionHitSensRegex02()
					, STRING_VALIDER_SECTIONHIT_SENS_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitSensRegex02()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitSensRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.sens.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_SENS_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitSensRegex02() {
		return KEY_VALIDER_SECTIONHIT_SENS_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitSensRegex02().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitSensRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitSensRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitSensRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitSensRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitSensRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitSensRegex02();
	} // Fin de getValiderRGSectionHitSensRegex02()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitSensRegex02 
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
	* valeur à passer à validerRGSectionHitSensRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitSensRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitSensRegex02
							, fournirKeyValiderRGSectionHitSensRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitSensRegex02(...).__________



	/**
	 * retourne le validerRGSectionHitSensNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitSensNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitSensNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitSensNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitSensNomenclature03
					, fournirKeyValiderRGSectionHitSensNomenclature03()
					, STRING_VALIDER_SECTIONHIT_SENS_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitSensNomenclature03().____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitSensNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.sens.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_SENS_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitSensNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_SENS_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitSensNomenclature03()._________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitSensNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitSensNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitSensNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitSensNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitSensNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitSensNomenclature03();
	} // Fin de getValiderRGSectionHitSensNomenclature03().________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitSensNomenclature03 
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
	* valeur à passer à validerRGSectionHitSensNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitSensNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitSensNomenclature03
							, fournirKeyValiderRGSectionHitSensNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitSensNomenclature03(...)._____________
	
	
	
	/* 3 - nature. */
	/**
	 * retourne le <code>validerRGSectionHitNature</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>nature</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>nature</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNature stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNature 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNature() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitNature
					, fournirKeyValiderRGSectionHitNature()
					, STRING_VALIDER_SECTIONHIT_NATURE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNature().________________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNature 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.nature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NATURE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNature() {
		return KEY_VALIDER_SECTIONHIT_NATURE;
	} // Fin de fournirKeyValiderRGSectionHitNature()._____________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNature par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitNature 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNature stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNature : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNature() 
													throws Exception {
		return fournirValiderRGSectionHitNature();
	} // Fin de getValiderRGSectionHitNature().____________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNature par défaut 
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
	* valeur à passer à validerRGSectionHitNature.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNature(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitNature
							, fournirKeyValiderRGSectionHitNature());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNature(...)._________________________



	/**
	 * retourne le validerRGSectionHitNatureRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNatureRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNatureRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNatureRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNatureRenseigne01
					, fournirKeyValiderRGSectionHitNatureRenseigne01()
					, STRING_VALIDER_SECTIONHIT_NATURE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNatureRenseigne01()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNatureRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.nature.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NATURE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNatureRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_NATURE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitNatureRenseigne01().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNatureRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNatureRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNatureRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNatureRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNatureRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitNatureRenseigne01();
	} // Fin de getValiderRGSectionHitNatureRenseigne01()._________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNatureRenseigne01 
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
	* valeur à passer à validerRGSectionHitNatureRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNatureRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNatureRenseigne01
							, fournirKeyValiderRGSectionHitNatureRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNatureRenseigne01(...).______________



	/**
	 * retourne le validerRGSectionHitNatureRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNatureRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNatureRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNatureRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNatureRegex02
					, fournirKeyValiderRGSectionHitNatureRegex02()
					, STRING_VALIDER_SECTIONHIT_NATURE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNatureRegex02()._________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNatureRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.nature.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NATURE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNatureRegex02() {
		return KEY_VALIDER_SECTIONHIT_NATURE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitNatureRegex02().______________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNatureRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNatureRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNatureRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNatureRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNatureRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitNatureRegex02();
	} // Fin de getValiderRGSectionHitNatureRegex02()._____________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNatureRegex02 
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
	* valeur à passer à validerRGSectionHitNatureRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNatureRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNatureRegex02
							, fournirKeyValiderRGSectionHitNatureRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNatureRegex02(...).__________________



	/**
	 * retourne le validerRGSectionHitNatureNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNatureNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNatureNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNatureNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNatureNomenclature03
					, fournirKeyValiderRGSectionHitNatureNomenclature03()
					, STRING_VALIDER_SECTIONHIT_NATURE_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNatureNomenclature03().__________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNatureNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.nature.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NATURE_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNatureNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_NATURE_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitNatureNomenclature03()._______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNatureNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNatureNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNatureNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNatureNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNatureNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitNatureNomenclature03();
	} // Fin de getValiderRGSectionHitNatureNomenclature03().______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNatureNomenclature03 
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
	* valeur à passer à validerRGSectionHitNatureNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNatureNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNatureNomenclature03
							, fournirKeyValiderRGSectionHitNatureNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNatureNomenclature03(...).___________
	
	
	
	/* 5 - classe. ****************/
	/**
	 * retourne le <code>validerRGSectionHitClasse</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>classe</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>classe</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasse stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasse 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasse() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitClasse
					, fournirKeyValiderRGSectionHitClasse()
					, STRING_VALIDER_SECTIONHIT_CLASSE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasse().________________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasse 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classe".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasse() {
		return KEY_VALIDER_SECTIONHIT_CLASSE;
	} // Fin de fournirKeyValiderRGSectionHitClasse()._____________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasse par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitClasse 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasse stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasse : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasse() 
													throws Exception {
		return fournirValiderRGSectionHitClasse();
	} // Fin de getValiderRGSectionHitClasse().____________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasse par défaut 
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
	* valeur à passer à validerRGSectionHitClasse.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasse(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitClasse
							, fournirKeyValiderRGSectionHitClasse());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasse(...)._________________________



	/**
	 * retourne le validerRGSectionHitClasseRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClasseRenseigne01
					, fournirKeyValiderRGSectionHitClasseRenseigne01()
					, STRING_VALIDER_SECTIONHIT_CLASSE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseRenseigne01()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classe.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_CLASSE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitClasseRenseigne01().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitClasseRenseigne01();
	} // Fin de getValiderRGSectionHitClasseRenseigne01()._________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseRenseigne01 
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
	* valeur à passer à validerRGSectionHitClasseRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClasseRenseigne01
							, fournirKeyValiderRGSectionHitClasseRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseRenseigne01(...).______________



	/**
	 * retourne le validerRGSectionHitClasseRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClasseRegex02
					, fournirKeyValiderRGSectionHitClasseRegex02()
					, STRING_VALIDER_SECTIONHIT_CLASSE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseRegex02()._________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classe.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseRegex02() {
		return KEY_VALIDER_SECTIONHIT_CLASSE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitClasseRegex02().______________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitClasseRegex02();
	} // Fin de getValiderRGSectionHitClasseRegex02()._____________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseRegex02 
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
	* valeur à passer à validerRGSectionHitClasseRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClasseRegex02
							, fournirKeyValiderRGSectionHitClasseRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseRegex02(...).__________________
	
	
	
	/* 6 - anneeTraitement. **************/
	/**
	 * retourne le <code>validerRGSectionHitAnneeTraitement</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>anneeTraitement</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>anneeTraitement</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAnneeTraitement stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAnneeTraitement 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAnneeTraitement() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitAnneeTraitement
					, fournirKeyValiderRGSectionHitAnneeTraitement()
					, STRING_VALIDER_SECTIONHIT_ANNEETRAITEMENT_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAnneeTraitement().________________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAnneeTraitement 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.anneeTraitement".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAnneeTraitement() {
		return KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT;
	} // Fin de fournirKeyValiderRGSectionHitAnneeTraitement()._____________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAnneeTraitement par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitAnneeTraitement 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAnneeTraitement stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAnneeTraitement : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAnneeTraitement() 
													throws Exception {
		return fournirValiderRGSectionHitAnneeTraitement();
	} // Fin de getValiderRGSectionHitAnneeTraitement().____________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAnneeTraitement par défaut 
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
	* valeur à passer à validerRGSectionHitAnneeTraitement.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAnneeTraitement(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitAnneeTraitement
							, fournirKeyValiderRGSectionHitAnneeTraitement());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAnneeTraitement(...)._________________________



	/**
	 * retourne le validerRGSectionHitAnneeTraitementRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAnneeTraitementRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAnneeTraitementRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAnneeTraitementRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitAnneeTraitementRenseigne01
					, fournirKeyValiderRGSectionHitAnneeTraitementRenseigne01()
					, STRING_VALIDER_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAnneeTraitementRenseigne01()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAnneeTraitementRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.anneeTraitement.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAnneeTraitementRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitAnneeTraitementRenseigne01().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAnneeTraitementRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitAnneeTraitementRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAnneeTraitementRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAnneeTraitementRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAnneeTraitementRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitAnneeTraitementRenseigne01();
	} // Fin de getValiderRGSectionHitAnneeTraitementRenseigne01()._________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAnneeTraitementRenseigne01 
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
	* valeur à passer à validerRGSectionHitAnneeTraitementRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAnneeTraitementRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitAnneeTraitementRenseigne01
							, fournirKeyValiderRGSectionHitAnneeTraitementRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAnneeTraitementRenseigne01(...).______________



	/**
	 * retourne le validerRGSectionHitAnneeTraitementRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAnneeTraitementRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAnneeTraitementRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAnneeTraitementRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitAnneeTraitementRegex02
					, fournirKeyValiderRGSectionHitAnneeTraitementRegex02()
					, STRING_VALIDER_SECTIONHIT_ANNEETRAITEMENT_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAnneeTraitementRegex02()._________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAnneeTraitementRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.anneeTraitement.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAnneeTraitementRegex02() {
		return KEY_VALIDER_SECTIONHIT_ANNEETRAITEMENT_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitAnneeTraitementRegex02().______________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAnneeTraitementRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitAnneeTraitementRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAnneeTraitementRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAnneeTraitementRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAnneeTraitementRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitAnneeTraitementRegex02();
	} // Fin de getValiderRGSectionHitAnneeTraitementRegex02()._____________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAnneeTraitementRegex02 
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
	* valeur à passer à validerRGSectionHitAnneeTraitementRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAnneeTraitementRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitAnneeTraitementRegex02
							, fournirKeyValiderRGSectionHitAnneeTraitementRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAnneeTraitementRegex02(...).__________________
	
	
	
	/* 7 - zoneLibre1. **************/
	/**
	 * retourne le <code>validerRGSectionHitZoneLibre1</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>zoneLibre1</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>zoneLibre1</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitZoneLibre1 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitZoneLibre1 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitZoneLibre1() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitZoneLibre1
					, fournirKeyValiderRGSectionHitZoneLibre1()
					, STRING_VALIDER_SECTIONHIT_ZONELIBRE1_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitZoneLibre1().____________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitZoneLibre1 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.zoneLibre1".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ZONELIBRE1 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitZoneLibre1() {
		return KEY_VALIDER_SECTIONHIT_ZONELIBRE1;
	} // Fin de fournirKeyValiderRGSectionHitZoneLibre1()._________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitZoneLibre1 par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitZoneLibre1 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitZoneLibre1 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitZoneLibre1 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitZoneLibre1() 
													throws Exception {
		return fournirValiderRGSectionHitZoneLibre1();
	} // Fin de getValiderRGSectionHitZoneLibre1().________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitZoneLibre1 par défaut 
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
	* valeur à passer à validerRGSectionHitZoneLibre1.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitZoneLibre1(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitZoneLibre1
							, fournirKeyValiderRGSectionHitZoneLibre1());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitZoneLibre1(...)._____________________



	/**
	 * retourne le validerRGSectionHitZoneLibre1Renseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitZoneLibre1Renseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitZoneLibre1Renseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitZoneLibre1Renseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitZoneLibre1Renseigne01
					, fournirKeyValiderRGSectionHitZoneLibre1Renseigne01()
					, STRING_VALIDER_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitZoneLibre1Renseigne01()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitZoneLibre1Renseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.zoneLibre1.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitZoneLibre1Renseigne01() {
		return KEY_VALIDER_SECTIONHIT_ZONELIBRE1_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitZoneLibre1Renseigne01().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitZoneLibre1Renseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitZoneLibre1Renseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitZoneLibre1Renseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitZoneLibre1Renseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitZoneLibre1Renseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitZoneLibre1Renseigne01();
	} // Fin de getValiderRGSectionHitZoneLibre1Renseigne01()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitZoneLibre1Renseigne01 
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
	* valeur à passer à validerRGSectionHitZoneLibre1Renseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitZoneLibre1Renseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitZoneLibre1Renseigne01
							, fournirKeyValiderRGSectionHitZoneLibre1Renseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitZoneLibre1Renseigne01(...).__________



	/**
	 * retourne le validerRGSectionHitZoneLibre1Regex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitZoneLibre1Regex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitZoneLibre1Regex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitZoneLibre1Regex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitZoneLibre1Regex02
					, fournirKeyValiderRGSectionHitZoneLibre1Regex02()
					, STRING_VALIDER_SECTIONHIT_ZONELIBRE1_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitZoneLibre1Regex02()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitZoneLibre1Regex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.zoneLibre1.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ZONELIBRE1_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitZoneLibre1Regex02() {
		return KEY_VALIDER_SECTIONHIT_ZONELIBRE1_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitZoneLibre1Regex02().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitZoneLibre1Regex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitZoneLibre1Regex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitZoneLibre1Regex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitZoneLibre1Regex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitZoneLibre1Regex02() 
													throws Exception {
		return fournirValiderRGSectionHitZoneLibre1Regex02();
	} // Fin de getValiderRGSectionHitZoneLibre1Regex02()._________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitZoneLibre1Regex02 
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
	* valeur à passer à validerRGSectionHitZoneLibre1Regex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitZoneLibre1Regex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitZoneLibre1Regex02
							, fournirKeyValiderRGSectionHitZoneLibre1Regex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitZoneLibre1Regex02(...).______________
	
	
	
	/* 8 - numRoute. **************/
	/**
	 * retourne le <code>validerRGSectionHitNumRoute</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>numRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numRoute</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumRoute stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumRoute 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumRoute() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitNumRoute
					, fournirKeyValiderRGSectionHitNumRoute()
					, STRING_VALIDER_SECTIONHIT_NUMROUTE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumRoute().______________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumRoute 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numRoute".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMROUTE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumRoute() {
		return KEY_VALIDER_SECTIONHIT_NUMROUTE;
	} // Fin de fournirKeyValiderRGSectionHitNumRoute().___________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumRoute par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitNumRoute 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumRoute stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumRoute : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumRoute() 
													throws Exception {
		return fournirValiderRGSectionHitNumRoute();
	} // Fin de getValiderRGSectionHitNumRoute().__________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumRoute par défaut 
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
	* valeur à passer à validerRGSectionHitNumRoute.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumRoute(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitNumRoute
							, fournirKeyValiderRGSectionHitNumRoute());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumRoute(...)._______________________



	/**
	 * retourne le validerRGSectionHitNumRouteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumRouteRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumRouteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumRouteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumRouteRenseigne01
					, fournirKeyValiderRGSectionHitNumRouteRenseigne01()
					, STRING_VALIDER_SECTIONHIT_NUMROUTE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumRouteRenseigne01().___________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumRouteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numRoute.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMROUTE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumRouteRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_NUMROUTE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitNumRouteRenseigne01().________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumRouteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumRouteRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumRouteRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumRouteRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumRouteRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitNumRouteRenseigne01();
	} // Fin de getValiderRGSectionHitNumRouteRenseigne01()._______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumRouteRenseigne01 
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
	* valeur à passer à validerRGSectionHitNumRouteRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumRouteRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumRouteRenseigne01
							, fournirKeyValiderRGSectionHitNumRouteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumRouteRenseigne01(...).____________



	/**
	 * retourne le validerRGSectionHitNumRouteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumRouteRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumRouteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumRouteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumRouteRegex02
					, fournirKeyValiderRGSectionHitNumRouteRegex02()
					, STRING_VALIDER_SECTIONHIT_NUMROUTE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumRouteRegex02()._______________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumRouteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numRoute.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMROUTE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumRouteRegex02() {
		return KEY_VALIDER_SECTIONHIT_NUMROUTE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitNumRouteRegex02().____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumRouteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumRouteRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumRouteRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumRouteRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumRouteRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitNumRouteRegex02();
	} // Fin de getValiderRGSectionHitNumRouteRegex02().___________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumRouteRegex02 
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
	* valeur à passer à validerRGSectionHitNumRouteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumRouteRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumRouteRegex02
							, fournirKeyValiderRGSectionHitNumRouteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumRouteRegex02(...).________________
	
	
	
	/* 9 - indiceNumRoute. **************/
	/**
	 * retourne le <code>validerRGSectionHitIndiceNumRoute</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>indiceNumRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>indiceNumRoute</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceNumRoute stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitIndiceNumRoute 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitIndiceNumRoute() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitIndiceNumRoute
					, fournirKeyValiderRGSectionHitIndiceNumRoute()
					, STRING_VALIDER_SECTIONHIT_INDICENUMROUTE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitIndiceNumRoute().________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitIndiceNumRoute 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.indiceNumRoute".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_INDICENUMROUTE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitIndiceNumRoute() {
		return KEY_VALIDER_SECTIONHIT_INDICENUMROUTE;
	} // Fin de fournirKeyValiderRGSectionHitIndiceNumRoute()._____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitIndiceNumRoute par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitIndiceNumRoute 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitIndiceNumRoute stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitIndiceNumRoute : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitIndiceNumRoute() 
													throws Exception {
		return fournirValiderRGSectionHitIndiceNumRoute();
	} // Fin de getValiderRGSectionHitIndiceNumRoute().____________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitIndiceNumRoute par défaut 
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
	* valeur à passer à validerRGSectionHitIndiceNumRoute.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitIndiceNumRoute(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitIndiceNumRoute
							, fournirKeyValiderRGSectionHitIndiceNumRoute());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitIndiceNumRoute(...)._________________



	/**
	 * retourne le validerRGSectionHitIndiceNumRouteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceNumRouteRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitIndiceNumRouteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitIndiceNumRouteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitIndiceNumRouteRenseigne01
					, fournirKeyValiderRGSectionHitIndiceNumRouteRenseigne01()
					, STRING_VALIDER_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitIndiceNumRouteRenseigne01()._____
	

	
	/**
	 * Getter de la clé du validerRGSectionHitIndiceNumRouteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.indiceNumRoute.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitIndiceNumRouteRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_INDICENUMROUTE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitIndiceNumRouteRenseigne01().__



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitIndiceNumRouteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceNumRouteRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitIndiceNumRouteRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitIndiceNumRouteRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitIndiceNumRouteRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitIndiceNumRouteRenseigne01();
	} // Fin de getValiderRGSectionHitIndiceNumRouteRenseigne01()._________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitIndiceNumRouteRenseigne01 
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
	* valeur à passer à validerRGSectionHitIndiceNumRouteRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitIndiceNumRouteRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitIndiceNumRouteRenseigne01
							, fournirKeyValiderRGSectionHitIndiceNumRouteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitIndiceNumRouteRenseigne01(...).______



	/**
	 * retourne le validerRGSectionHitIndiceNumRouteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceNumRouteRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitIndiceNumRouteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitIndiceNumRouteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitIndiceNumRouteRegex02
					, fournirKeyValiderRGSectionHitIndiceNumRouteRegex02()
					, STRING_VALIDER_SECTIONHIT_INDICENUMROUTE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitIndiceNumRouteRegex02()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitIndiceNumRouteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.indiceNumRoute.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_INDICENUMROUTE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitIndiceNumRouteRegex02() {
		return KEY_VALIDER_SECTIONHIT_INDICENUMROUTE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitIndiceNumRouteRegex02().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitIndiceNumRouteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceNumRouteRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitIndiceNumRouteRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitIndiceNumRouteRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitIndiceNumRouteRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitIndiceNumRouteRegex02();
	} // Fin de getValiderRGSectionHitIndiceNumRouteRegex02()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitIndiceNumRouteRegex02 
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
	* valeur à passer à validerRGSectionHitIndiceNumRouteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitIndiceNumRouteRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitIndiceNumRouteRegex02
							, fournirKeyValiderRGSectionHitIndiceNumRouteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitIndiceNumRouteRegex02(...).__________
	
	
	
	/* 10 - indiceLettreRoute. **************/
	/**
	 * retourne le <code>validerRGSectionHitIndiceLettreRoute</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>indiceLettreRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>indiceLettreRoute</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceLettreRoute stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitIndiceLettreRoute 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitIndiceLettreRoute() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitIndiceLettreRoute
					, fournirKeyValiderRGSectionHitIndiceLettreRoute()
					, STRING_VALIDER_SECTIONHIT_INDICELETTREROUTE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitIndiceLettreRoute()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitIndiceLettreRoute 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.indiceLettreRoute".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitIndiceLettreRoute() {
		return KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE;
	} // Fin de fournirKeyValiderRGSectionHitIndiceLettreRoute().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitIndiceLettreRoute par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitIndiceLettreRoute 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitIndiceLettreRoute stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitIndiceLettreRoute : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitIndiceLettreRoute() 
													throws Exception {
		return fournirValiderRGSectionHitIndiceLettreRoute();
	} // Fin de getValiderRGSectionHitIndiceLettreRoute()._________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitIndiceLettreRoute par défaut 
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
	* valeur à passer à validerRGSectionHitIndiceLettreRoute.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitIndiceLettreRoute(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitIndiceLettreRoute
							, fournirKeyValiderRGSectionHitIndiceLettreRoute());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitIndiceLettreRoute(...).______________



	/**
	 * retourne le validerRGSectionHitIndiceLettreRouteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceLettreRouteRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitIndiceLettreRouteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitIndiceLettreRouteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitIndiceLettreRouteRenseigne01
					, fournirKeyValiderRGSectionHitIndiceLettreRouteRenseigne01()
					, STRING_VALIDER_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitIndiceLettreRouteRenseigne01().__
	

	
	/**
	 * Getter de la clé du validerRGSectionHitIndiceLettreRouteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.indiceLettreRoute.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitIndiceLettreRouteRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitIndiceLettreRouteRenseigne01().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitIndiceLettreRouteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceLettreRouteRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitIndiceLettreRouteRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitIndiceLettreRouteRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitIndiceLettreRouteRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitIndiceLettreRouteRenseigne01();
	} // Fin de getValiderRGSectionHitIndiceLettreRouteRenseigne01().______
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitIndiceLettreRouteRenseigne01 
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
	* valeur à passer à validerRGSectionHitIndiceLettreRouteRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitIndiceLettreRouteRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitIndiceLettreRouteRenseigne01
							, fournirKeyValiderRGSectionHitIndiceLettreRouteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitIndiceLettreRouteRenseigne01(...).___



	/**
	 * retourne le validerRGSectionHitIndiceLettreRouteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceLettreRouteRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitIndiceLettreRouteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitIndiceLettreRouteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitIndiceLettreRouteRegex02
					, fournirKeyValiderRGSectionHitIndiceLettreRouteRegex02()
					, STRING_VALIDER_SECTIONHIT_INDICELETTREROUTE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitIndiceLettreRouteRegex02().______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitIndiceLettreRouteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.indiceLettreRoute.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitIndiceLettreRouteRegex02() {
		return KEY_VALIDER_SECTIONHIT_INDICELETTREROUTE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitIndiceLettreRouteRegex02().___



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitIndiceLettreRouteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceLettreRouteRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitIndiceLettreRouteRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitIndiceLettreRouteRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitIndiceLettreRouteRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitIndiceLettreRouteRegex02();
	} // Fin de getValiderRGSectionHitIndiceLettreRouteRegex02().__________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitIndiceLettreRouteRegex02 
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
	* valeur à passer à validerRGSectionHitIndiceLettreRouteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitIndiceLettreRouteRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitIndiceLettreRouteRegex02
							, fournirKeyValiderRGSectionHitIndiceLettreRouteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitIndiceLettreRouteRegex02(...)._______
	
	
	
	/* 11 - categorieAdminRoute. **************/
	/**
	 * retourne le <code>validerRGSectionHitCategorieAdminRoute</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>categorieAdminRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>categorieAdminRoute</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitCategorieAdminRoute stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitCategorieAdminRoute 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitCategorieAdminRoute() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitCategorieAdminRoute
					, fournirKeyValiderRGSectionHitCategorieAdminRoute()
					, STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitCategorieAdminRoute().___________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitCategorieAdminRoute 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.categorieAdminRoute".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitCategorieAdminRoute() {
		return KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE;
	} // Fin de fournirKeyValiderRGSectionHitCategorieAdminRoute().________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitCategorieAdminRoute par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitCategorieAdminRoute 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitCategorieAdminRoute stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitCategorieAdminRoute : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitCategorieAdminRoute() 
													throws Exception {
		return fournirValiderRGSectionHitCategorieAdminRoute();
	} // Fin de getValiderRGSectionHitCategorieAdminRoute()._______________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitCategorieAdminRoute par défaut 
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
	* valeur à passer à validerRGSectionHitCategorieAdminRoute.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitCategorieAdminRoute(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitCategorieAdminRoute
							, fournirKeyValiderRGSectionHitCategorieAdminRoute());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitCategorieAdminRoute(...).____________



	/**
	 * retourne le validerRGSectionHitCategorieAdminRouteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitCategorieAdminRouteRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitCategorieAdminRouteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitCategorieAdminRouteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitCategorieAdminRouteRenseigne01
					, fournirKeyValiderRGSectionHitCategorieAdminRouteRenseigne01()
					, STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitCategorieAdminRouteRenseigne01().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitCategorieAdminRouteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.categorieAdminRoute.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitCategorieAdminRouteRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitCategorieAdminRouteRenseigne01().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitCategorieAdminRouteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitCategorieAdminRouteRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitCategorieAdminRouteRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitCategorieAdminRouteRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitCategorieAdminRouteRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitCategorieAdminRouteRenseigne01();
	} // Fin de getValiderRGSectionHitCategorieAdminRouteRenseigne01().____
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitCategorieAdminRouteRenseigne01 
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
	* valeur à passer à validerRGSectionHitCategorieAdminRouteRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitCategorieAdminRouteRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitCategorieAdminRouteRenseigne01
							, fournirKeyValiderRGSectionHitCategorieAdminRouteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitCategorieAdminRouteRenseigne01(...)._



	/**
	 * retourne le validerRGSectionHitCategorieAdminRouteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitCategorieAdminRouteRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitCategorieAdminRouteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitCategorieAdminRouteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitCategorieAdminRouteRegex02
					, fournirKeyValiderRGSectionHitCategorieAdminRouteRegex02()
					, STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitCategorieAdminRouteRegex02().____
	

	
	/**
	 * Getter de la clé du validerRGSectionHitCategorieAdminRouteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.categorieAdminRoute.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitCategorieAdminRouteRegex02() {
		return KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitCategorieAdminRouteRegex02()._



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitCategorieAdminRouteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitCategorieAdminRouteRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitCategorieAdminRouteRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitCategorieAdminRouteRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitCategorieAdminRouteRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitCategorieAdminRouteRegex02();
	} // Fin de getValiderRGSectionHitCategorieAdminRouteRegex02().________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitCategorieAdminRouteRegex02 
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
	* valeur à passer à validerRGSectionHitCategorieAdminRouteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitCategorieAdminRouteRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitCategorieAdminRouteRegex02
							, fournirKeyValiderRGSectionHitCategorieAdminRouteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitCategorieAdminRouteRegex02(...)._____



	/**
	 * retourne le validerRGSectionHitCategorieAdminRouteNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitCategorieAdminRouteNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitCategorieAdminRouteNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitCategorieAdminRouteNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitCategorieAdminRouteNomenclature03
					, fournirKeyValiderRGSectionHitCategorieAdminRouteNomenclature03()
					, STRING_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitCategorieAdminRouteNomenclature03().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitCategorieAdminRouteNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.categorieAdminRoute.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitCategorieAdminRouteNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_CATEGORIEADMINROUTE_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitCategorieAdminRouteNomenclature03().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitCategorieAdminRouteNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitCategorieAdminRouteNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitCategorieAdminRouteNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitCategorieAdminRouteNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitCategorieAdminRouteNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitCategorieAdminRouteNomenclature03();
	} // Fin de getValiderRGSectionHitCategorieAdminRouteNomenclature03()._
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitCategorieAdminRouteNomenclature03 
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
	* valeur à passer à validerRGSectionHitCategorieAdminRouteNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitCategorieAdminRouteNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitCategorieAdminRouteNomenclature03
							, fournirKeyValiderRGSectionHitCategorieAdminRouteNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitCategorieAdminRouteNomenclature03(...).
	
	
	
	/* 12 - typeComptage. **************/
	/**
	 * retourne le <code>validerRGSectionHitTypeComptage</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>typeComptage</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>typeComptage</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeComptage stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitTypeComptage 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitTypeComptage() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitTypeComptage
					, fournirKeyValiderRGSectionHitTypeComptage()
					, STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitTypeComptage().__________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitTypeComptage 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.typeComptage".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitTypeComptage() {
		return KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE;
	} // Fin de fournirKeyValiderRGSectionHitTypeComptage()._______________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitTypeComptage par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitTypeComptage 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitTypeComptage stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitTypeComptage : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitTypeComptage() 
													throws Exception {
		return fournirValiderRGSectionHitTypeComptage();
	} // Fin de getValiderRGSectionHitTypeComptage().______________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitTypeComptage par défaut 
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
	* valeur à passer à validerRGSectionHitTypeComptage.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitTypeComptage(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitTypeComptage
							, fournirKeyValiderRGSectionHitTypeComptage());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitTypeComptage(...).___________________



	/**
	 * retourne le validerRGSectionHitTypeComptageRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeComptageRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitTypeComptageRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitTypeComptageRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitTypeComptageRenseigne01
					, fournirKeyValiderRGSectionHitTypeComptageRenseigne01()
					, STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitTypeComptageRenseigne01()._______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitTypeComptageRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.typeComptage.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitTypeComptageRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitTypeComptageRenseigne01().____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitTypeComptageRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeComptageRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitTypeComptageRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitTypeComptageRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitTypeComptageRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitTypeComptageRenseigne01();
	} // Fin de getValiderRGSectionHitTypeComptageRenseigne01().___________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitTypeComptageRenseigne01 
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
	* valeur à passer à validerRGSectionHitTypeComptageRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitTypeComptageRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitTypeComptageRenseigne01
							, fournirKeyValiderRGSectionHitTypeComptageRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitTypeComptageRenseigne01(...).________



	/**
	 * retourne le validerRGSectionHitTypeComptageRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeComptageRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitTypeComptageRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitTypeComptageRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitTypeComptageRegex02
					, fournirKeyValiderRGSectionHitTypeComptageRegex02()
					, STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitTypeComptageRegex02().___________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitTypeComptageRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.typeComptage.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitTypeComptageRegex02() {
		return KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitTypeComptageRegex02().________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitTypeComptageRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeComptageRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitTypeComptageRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitTypeComptageRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitTypeComptageRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitTypeComptageRegex02();
	} // Fin de getValiderRGSectionHitTypeComptageRegex02()._______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitTypeComptageRegex02 
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
	* valeur à passer à validerRGSectionHitTypeComptageRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitTypeComptageRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitTypeComptageRegex02
							, fournirKeyValiderRGSectionHitTypeComptageRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitTypeComptageRegex02(...).____________



	/**
	 * retourne le validerRGSectionHitTypeComptageNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeComptageNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitTypeComptageNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitTypeComptageNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitTypeComptageNomenclature03
					, fournirKeyValiderRGSectionHitTypeComptageNomenclature03()
					, STRING_VALIDER_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitTypeComptageNomenclature03().____
	

	
	/**
	 * Getter de la clé du validerRGSectionHitTypeComptageNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.typeComptage.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitTypeComptageNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_TYPECOMPTAGE_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitTypeComptageNomenclature03()._



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitTypeComptageNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeComptageNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitTypeComptageNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitTypeComptageNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitTypeComptageNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitTypeComptageNomenclature03();
	} // Fin de getValiderRGSectionHitTypeComptageNomenclature03().________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitTypeComptageNomenclature03 
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
	* valeur à passer à validerRGSectionHitTypeComptageNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitTypeComptageNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitTypeComptageNomenclature03
							, fournirKeyValiderRGSectionHitTypeComptageNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitTypeComptageNomenclature03(...)._____
	
	
	
	/* 13 - classementRoute. **************/
	/**
	 * retourne le <code>validerRGSectionHitClassementRoute</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>classementRoute</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>classementRoute</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClassementRoute stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClassementRoute 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClassementRoute() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitClassementRoute
					, fournirKeyValiderRGSectionHitClassementRoute()
					, STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClassementRoute()._______________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClassementRoute 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classementRoute".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClassementRoute() {
		return KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE;
	} // Fin de fournirKeyValiderRGSectionHitClassementRoute().____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClassementRoute par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitClassementRoute 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClassementRoute stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClassementRoute : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClassementRoute() 
													throws Exception {
		return fournirValiderRGSectionHitClassementRoute();
	} // Fin de getValiderRGSectionHitClassementRoute().___________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClassementRoute par défaut 
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
	* valeur à passer à validerRGSectionHitClassementRoute.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClassementRoute(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitClassementRoute
							, fournirKeyValiderRGSectionHitClassementRoute());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClassementRoute(...).__________________



	/**
	 * retourne le validerRGSectionHitClassementRouteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClassementRouteRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClassementRouteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClassementRouteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClassementRouteRenseigne01
					, fournirKeyValiderRGSectionHitClassementRouteRenseigne01()
					, STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClassementRouteRenseigne01().____
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClassementRouteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classementRoute.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClassementRouteRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitClassementRouteRenseigne01()._



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClassementRouteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClassementRouteRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClassementRouteRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClassementRouteRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClassementRouteRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitClassementRouteRenseigne01();
	} // Fin de getValiderRGSectionHitClassementRouteRenseigne01().________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClassementRouteRenseigne01 
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
	* valeur à passer à validerRGSectionHitClassementRouteRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClassementRouteRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClassementRouteRenseigne01
							, fournirKeyValiderRGSectionHitClassementRouteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClassementRouteRenseigne01(...)._____



	/**
	 * retourne le validerRGSectionHitClassementRouteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClassementRouteRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClassementRouteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClassementRouteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClassementRouteRegex02
					, fournirKeyValiderRGSectionHitClassementRouteRegex02()
					, STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClassementRouteRegex02().________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClassementRouteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classementRoute.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClassementRouteRegex02() {
		return KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitClassementRouteRegex02()._____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClassementRouteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClassementRouteRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClassementRouteRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClassementRouteRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClassementRouteRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitClassementRouteRegex02();
	} // Fin de getValiderRGSectionHitClassementRouteRegex02().____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClassementRouteRegex02 
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
	* valeur à passer à validerRGSectionHitClassementRouteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClassementRouteRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClassementRouteRegex02
							, fournirKeyValiderRGSectionHitClassementRouteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClassementRouteRegex02(...)._________



	/**
	 * retourne le validerRGSectionHitClassementRouteNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClassementRouteNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClassementRouteNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClassementRouteNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClassementRouteNomenclature03
					, fournirKeyValiderRGSectionHitClassementRouteNomenclature03()
					, STRING_VALIDER_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClassementRouteNomenclature03()._
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClassementRouteNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classementRoute.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClassementRouteNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_CLASSEMENTROUTE_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitClassementRouteNomenclature03().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClassementRouteNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClassementRouteNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClassementRouteNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClassementRouteNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClassementRouteNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitClassementRouteNomenclature03();
	} // Fin de getValiderRGSectionHitClassementRouteNomenclature03()._____
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClassementRouteNomenclature03 
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
	* valeur à passer à validerRGSectionHitClassementRouteNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClassementRouteNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClassementRouteNomenclature03
							, fournirKeyValiderRGSectionHitClassementRouteNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClassementRouteNomenclature03(...).__
	
	
	
	/* 14 - classeLargeurChausseeU. **************/
	/**
	 * retourne le <code>validerRGSectionHitClasseLargeurChausseeU</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>classeLargeurChausseeU</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>classeLargeurChausseeU</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseeU stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseLargeurChausseeU 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseLargeurChausseeU() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitClasseLargeurChausseeU
					, fournirKeyValiderRGSectionHitClasseLargeurChausseeU()
					, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseLargeurChausseeU().________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseLargeurChausseeU 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classeLargeurChausseeU".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseLargeurChausseeU() {
		return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU;
	} // Fin de fournirKeyValiderRGSectionHitClasseLargeurChausseeU()._____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseeU par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseeU 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseLargeurChausseeU stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseLargeurChausseeU : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseLargeurChausseeU() 
													throws Exception {
		return fournirValiderRGSectionHitClasseLargeurChausseeU();
	} // Fin de getValiderRGSectionHitClasseLargeurChausseeU().____________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseeU par défaut 
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
	* valeur à passer à validerRGSectionHitClasseLargeurChausseeU.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseLargeurChausseeU(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitClasseLargeurChausseeU
							, fournirKeyValiderRGSectionHitClasseLargeurChausseeU());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseLargeurChausseeU(...)._________



	/**
	 * retourne le validerRGSectionHitClasseLargeurChausseeURenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseeURenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseLargeurChausseeURenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseLargeurChausseeURenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClasseLargeurChausseeURenseigne01
					, fournirKeyValiderRGSectionHitClasseLargeurChausseeURenseigne01()
					, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseLargeurChausseeURenseigne01().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseLargeurChausseeURenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classeLargeurChausseeU.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseLargeurChausseeURenseigne01() {
		return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitClasseLargeurChausseeURenseigne01().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseeURenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseeURenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseLargeurChausseeURenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseLargeurChausseeURenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseLargeurChausseeURenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitClasseLargeurChausseeURenseigne01();
	} // Fin de getValiderRGSectionHitClasseLargeurChausseeURenseigne01()._
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseeURenseigne01 
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
	* valeur à passer à validerRGSectionHitClasseLargeurChausseeURenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseLargeurChausseeURenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClasseLargeurChausseeURenseigne01
							, fournirKeyValiderRGSectionHitClasseLargeurChausseeURenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseLargeurChausseeURenseigne01(...).



	/**
	 * retourne le validerRGSectionHitClasseLargeurChausseeURegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseeURegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseLargeurChausseeURegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseLargeurChausseeURegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClasseLargeurChausseeURegex02
					, fournirKeyValiderRGSectionHitClasseLargeurChausseeURegex02()
					, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseLargeurChausseeURegex02()._
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseLargeurChausseeURegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classeLargeurChausseeU.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseLargeurChausseeURegex02() {
		return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitClasseLargeurChausseeURegex02().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseeURegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseeURegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseLargeurChausseeURegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseLargeurChausseeURegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseLargeurChausseeURegex02() 
													throws Exception {
		return fournirValiderRGSectionHitClasseLargeurChausseeURegex02();
	} // Fin de getValiderRGSectionHitClasseLargeurChausseeURegex02()._____
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseeURegex02 
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
	* valeur à passer à validerRGSectionHitClasseLargeurChausseeURegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseLargeurChausseeURegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClasseLargeurChausseeURegex02
							, fournirKeyValiderRGSectionHitClasseLargeurChausseeURegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseLargeurChausseeURegex02(...).__



	/**
	 * retourne le validerRGSectionHitClasseLargeurChausseeUNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseeUNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseLargeurChausseeUNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseLargeurChausseeUNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClasseLargeurChausseeUNomenclature03
					, fournirKeyValiderRGSectionHitClasseLargeurChausseeUNomenclature03()
					, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseLargeurChausseeUNomenclature03().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseLargeurChausseeUNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classeLargeurChausseeU.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseLargeurChausseeUNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEEU_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitClasseLargeurChausseeUNomenclature03().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseeUNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseeUNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseLargeurChausseeUNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseLargeurChausseeUNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseLargeurChausseeUNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitClasseLargeurChausseeUNomenclature03();
	} // Fin de getValiderRGSectionHitClasseLargeurChausseeUNomenclature03().
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseeUNomenclature03 
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
	* valeur à passer à validerRGSectionHitClasseLargeurChausseeUNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseLargeurChausseeUNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClasseLargeurChausseeUNomenclature03
							, fournirKeyValiderRGSectionHitClasseLargeurChausseeUNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseLargeurChausseeUNomenclature03(...).
	
	
	
	/* 15 - classeLargeurChausseesS. **************/
	/**
	 * retourne le <code>validerRGSectionHitClasseLargeurChausseesS</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>classeLargeurChausseesS</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>classeLargeurChausseesS</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseesS stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseLargeurChausseesS 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseLargeurChausseesS() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitClasseLargeurChausseesS
					, fournirKeyValiderRGSectionHitClasseLargeurChausseesS()
					, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseLargeurChausseesS()._______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseLargeurChausseesS 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classeLargeurChausseesS".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseLargeurChausseesS() {
		return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS;
	} // Fin de fournirKeyValiderRGSectionHitClasseLargeurChausseesS().____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseesS par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseesS 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseLargeurChausseesS stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseLargeurChausseesS : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseLargeurChausseesS() 
													throws Exception {
		return fournirValiderRGSectionHitClasseLargeurChausseesS();
	} // Fin de getValiderRGSectionHitClasseLargeurChausseesS().___________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseesS par défaut 
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
	* valeur à passer à validerRGSectionHitClasseLargeurChausseesS.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseLargeurChausseesS(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitClasseLargeurChausseesS
							, fournirKeyValiderRGSectionHitClasseLargeurChausseesS());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseLargeurChausseesS(...).________



	/**
	 * retourne le validerRGSectionHitClasseLargeurChausseesSRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseesSRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseLargeurChausseesSRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseLargeurChausseesSRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClasseLargeurChausseesSRenseigne01
					, fournirKeyValiderRGSectionHitClasseLargeurChausseesSRenseigne01()
					, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseLargeurChausseesSRenseigne01().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseLargeurChausseesSRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classeLargeurChausseesS.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseLargeurChausseesSRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitClasseLargeurChausseesSRenseigne01().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseesSRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseesSRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseLargeurChausseesSRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseLargeurChausseesSRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseLargeurChausseesSRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitClasseLargeurChausseesSRenseigne01();
	} // Fin de getValiderRGSectionHitClasseLargeurChausseesSRenseigne01().
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseesSRenseigne01 
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
	* valeur à passer à validerRGSectionHitClasseLargeurChausseesSRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseLargeurChausseesSRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClasseLargeurChausseesSRenseigne01
							, fournirKeyValiderRGSectionHitClasseLargeurChausseesSRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseLargeurChausseesSRenseigne01(...).



	/**
	 * retourne le validerRGSectionHitClasseLargeurChausseesSRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseesSRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseLargeurChausseesSRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseLargeurChausseesSRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClasseLargeurChausseesSRegex02
					, fournirKeyValiderRGSectionHitClasseLargeurChausseesSRegex02()
					, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseLargeurChausseesSRegex02().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseLargeurChausseesSRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classeLargeurChausseesS.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseLargeurChausseesSRegex02() {
		return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitClasseLargeurChausseesSRegex02().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseesSRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseesSRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseLargeurChausseesSRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseLargeurChausseesSRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseLargeurChausseesSRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitClasseLargeurChausseesSRegex02();
	} // Fin de getValiderRGSectionHitClasseLargeurChausseesSRegex02().____
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseesSRegex02 
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
	* valeur à passer à validerRGSectionHitClasseLargeurChausseesSRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseLargeurChausseesSRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClasseLargeurChausseesSRegex02
							, fournirKeyValiderRGSectionHitClasseLargeurChausseesSRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseLargeurChausseesSRegex02(...)._



	/**
	 * retourne le validerRGSectionHitClasseLargeurChausseesSNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseesSNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitClasseLargeurChausseesSNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitClasseLargeurChausseesSNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitClasseLargeurChausseesSNomenclature03
					, fournirKeyValiderRGSectionHitClasseLargeurChausseesSNomenclature03()
					, STRING_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitClasseLargeurChausseesSNomenclature03().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitClasseLargeurChausseesSNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.classeLargeurChausseesS.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitClasseLargeurChausseesSNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_CLASSELARGEURCHAUSSEESS_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitClasseLargeurChausseesSNomenclature03().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseesSNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitClasseLargeurChausseesSNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitClasseLargeurChausseesSNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitClasseLargeurChausseesSNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitClasseLargeurChausseesSNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitClasseLargeurChausseesSNomenclature03();
	} // Fin de getValiderRGSectionHitClasseLargeurChausseesSNomenclature03().
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitClasseLargeurChausseesSNomenclature03 
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
	* valeur à passer à validerRGSectionHitClasseLargeurChausseesSNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitClasseLargeurChausseesSNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitClasseLargeurChausseesSNomenclature03
							, fournirKeyValiderRGSectionHitClasseLargeurChausseesSNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitClasseLargeurChausseesSNomenclature03(...).
	
	
	
	/* 16 - typeReseau. **************/
	/**
	 * retourne le <code>validerRGSectionHitTypeReseau</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>typeReseau</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>typeReseau</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeReseau stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitTypeReseau 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitTypeReseau() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitTypeReseau
					, fournirKeyValiderRGSectionHitTypeReseau()
					, STRING_VALIDER_SECTIONHIT_TYPERESEAU_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitTypeReseau().____________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitTypeReseau 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.typeReseau".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_TYPERESEAU : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitTypeReseau() {
		return KEY_VALIDER_SECTIONHIT_TYPERESEAU;
	} // Fin de fournirKeyValiderRGSectionHitTypeReseau()._________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitTypeReseau par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitTypeReseau 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitTypeReseau stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitTypeReseau : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitTypeReseau() 
													throws Exception {
		return fournirValiderRGSectionHitTypeReseau();
	} // Fin de getValiderRGSectionHitTypeReseau().________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitTypeReseau par défaut 
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
	* valeur à passer à validerRGSectionHitTypeReseau.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitTypeReseau(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitTypeReseau
							, fournirKeyValiderRGSectionHitTypeReseau());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitTypeReseau(...)._____________________



	/**
	 * retourne le validerRGSectionHitTypeReseauRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeReseauRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitTypeReseauRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitTypeReseauRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitTypeReseauRenseigne01
					, fournirKeyValiderRGSectionHitTypeReseauRenseigne01()
					, STRING_VALIDER_SECTIONHIT_TYPERESEAU_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitTypeReseauRenseigne01()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitTypeReseauRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.typeReseau.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_TYPERESEAU_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitTypeReseauRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_TYPERESEAU_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitTypeReseauRenseigne01().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitTypeReseauRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeReseauRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitTypeReseauRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitTypeReseauRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitTypeReseauRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitTypeReseauRenseigne01();
	} // Fin de getValiderRGSectionHitTypeReseauRenseigne01()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitTypeReseauRenseigne01 
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
	* valeur à passer à validerRGSectionHitTypeReseauRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitTypeReseauRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitTypeReseauRenseigne01
							, fournirKeyValiderRGSectionHitTypeReseauRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitTypeReseauRenseigne01(...).__________



	/**
	 * retourne le validerRGSectionHitTypeReseauRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeReseauRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitTypeReseauRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitTypeReseauRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitTypeReseauRegex02
					, fournirKeyValiderRGSectionHitTypeReseauRegex02()
					, STRING_VALIDER_SECTIONHIT_TYPERESEAU_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitTypeReseauRegex02()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitTypeReseauRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.typeReseau.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_TYPERESEAU_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitTypeReseauRegex02() {
		return KEY_VALIDER_SECTIONHIT_TYPERESEAU_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitTypeReseauRegex02().___________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitTypeReseauRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeReseauRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitTypeReseauRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitTypeReseauRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitTypeReseauRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitTypeReseauRegex02();
	} // Fin de getValiderRGSectionHitTypeReseauRegex02()._________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitTypeReseauRegex02 
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
	* valeur à passer à validerRGSectionHitTypeReseauRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitTypeReseauRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitTypeReseauRegex02
							, fournirKeyValiderRGSectionHitTypeReseauRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitTypeReseauRegex02(...).______________



	/**
	 * retourne le validerRGSectionHitTypeReseauNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeReseauNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitTypeReseauNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitTypeReseauNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitTypeReseauNomenclature03
					, fournirKeyValiderRGSectionHitTypeReseauNomenclature03()
					, STRING_VALIDER_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitTypeReseauNomenclature03().______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitTypeReseauNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.typeReseau.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitTypeReseauNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_TYPERESEAU_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitTypeReseauNomenclature03().___



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitTypeReseauNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitTypeReseauNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitTypeReseauNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitTypeReseauNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitTypeReseauNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitTypeReseauNomenclature03();
	} // Fin de getValiderRGSectionHitTypeReseauNomenclature03().__________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitTypeReseauNomenclature03 
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
	* valeur à passer à validerRGSectionHitTypeReseauNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitTypeReseauNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitTypeReseauNomenclature03
							, fournirKeyValiderRGSectionHitTypeReseauNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitTypeReseauNomenclature03(...)._______
	
	
	
	/* 17 - pRoupK. **************/
	/**
	 * retourne le <code>validerRGSectionHitPRoupK</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>pRoupK</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>pRoupK</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPRoupK stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPRoupK 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPRoupK() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitPRoupK
					, fournirKeyValiderRGSectionHitPRoupK()
					, STRING_VALIDER_SECTIONHIT_PROUPK_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPRoupK().________________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPRoupK 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pRoupK".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PROUPK : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPRoupK() {
		return KEY_VALIDER_SECTIONHIT_PROUPK;
	} // Fin de fournirKeyValiderRGSectionHitPRoupK()._____________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPRoupK par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitPRoupK 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPRoupK stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPRoupK : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPRoupK() 
													throws Exception {
		return fournirValiderRGSectionHitPRoupK();
	} // Fin de getValiderRGSectionHitPRoupK().____________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPRoupK par défaut 
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
	* valeur à passer à validerRGSectionHitPRoupK.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPRoupK(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitPRoupK
							, fournirKeyValiderRGSectionHitPRoupK());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPRoupK(...)._________________________



	/**
	 * retourne le validerRGSectionHitPRoupKRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPRoupKRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPRoupKRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPRoupKRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPRoupKRenseigne01
					, fournirKeyValiderRGSectionHitPRoupKRenseigne01()
					, STRING_VALIDER_SECTIONHIT_PROUPK_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPRoupKRenseigne01()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPRoupKRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pRoupK.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PROUPK_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPRoupKRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_PROUPK_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitPRoupKRenseigne01().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPRoupKRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPRoupKRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPRoupKRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPRoupKRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPRoupKRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitPRoupKRenseigne01();
	} // Fin de getValiderRGSectionHitPRoupKRenseigne01()._________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPRoupKRenseigne01 
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
	* valeur à passer à validerRGSectionHitPRoupKRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPRoupKRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPRoupKRenseigne01
							, fournirKeyValiderRGSectionHitPRoupKRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPRoupKRenseigne01(...).______________



	/**
	 * retourne le validerRGSectionHitPRoupKRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPRoupKRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPRoupKRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPRoupKRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPRoupKRegex02
					, fournirKeyValiderRGSectionHitPRoupKRegex02()
					, STRING_VALIDER_SECTIONHIT_PROUPK_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPRoupKRegex02()._________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPRoupKRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pRoupK.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PROUPK_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPRoupKRegex02() {
		return KEY_VALIDER_SECTIONHIT_PROUPK_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitPRoupKRegex02().______________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPRoupKRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPRoupKRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPRoupKRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPRoupKRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPRoupKRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitPRoupKRegex02();
	} // Fin de getValiderRGSectionHitPRoupKRegex02()._____________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPRoupKRegex02 
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
	* valeur à passer à validerRGSectionHitPRoupKRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPRoupKRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPRoupKRegex02
							, fournirKeyValiderRGSectionHitPRoupKRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPRoupKRegex02(...).__________________



	/**
	 * retourne le validerRGSectionHitPRoupKNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPRoupKNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPRoupKNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPRoupKNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPRoupKNomenclature03
					, fournirKeyValiderRGSectionHitPRoupKNomenclature03()
					, STRING_VALIDER_SECTIONHIT_PROUPK_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPRoupKNomenclature03().__________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPRoupKNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pRoupK.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PROUPK_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPRoupKNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_PROUPK_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitPRoupKNomenclature03()._______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPRoupKNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPRoupKNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPRoupKNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPRoupKNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPRoupKNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitPRoupKNomenclature03();
	} // Fin de getValiderRGSectionHitPRoupKNomenclature03().______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPRoupKNomenclature03 
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
	* valeur à passer à validerRGSectionHitPRoupKNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPRoupKNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPRoupKNomenclature03
							, fournirKeyValiderRGSectionHitPRoupKNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPRoupKNomenclature03(...).___________
	
	
	
	/* 18 - lieuDitOrigine. **************/
	/**
	 * retourne le <code>validerRGSectionHitLieuDitOrigine</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>lieuDitOrigine</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>lieuDitOrigine</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitOrigine stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLieuDitOrigine 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLieuDitOrigine() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitLieuDitOrigine
					, fournirKeyValiderRGSectionHitLieuDitOrigine()
					, STRING_VALIDER_SECTIONHIT_LIEUDITORIGINE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLieuDitOrigine().________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLieuDitOrigine 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.lieuDitOrigine".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLieuDitOrigine() {
		return KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE;
	} // Fin de fournirKeyValiderRGSectionHitLieuDitOrigine()._____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLieuDitOrigine par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitLieuDitOrigine 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLieuDitOrigine stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLieuDitOrigine : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLieuDitOrigine() 
													throws Exception {
		return fournirValiderRGSectionHitLieuDitOrigine();
	} // Fin de getValiderRGSectionHitLieuDitOrigine().____________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLieuDitOrigine par défaut 
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
	* valeur à passer à validerRGSectionHitLieuDitOrigine.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLieuDitOrigine(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitLieuDitOrigine
							, fournirKeyValiderRGSectionHitLieuDitOrigine());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLieuDitOrigine(...)._________________



	/**
	 * retourne le validerRGSectionHitLieuDitOrigineRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitOrigineRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLieuDitOrigineRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLieuDitOrigineRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitLieuDitOrigineRenseigne01
					, fournirKeyValiderRGSectionHitLieuDitOrigineRenseigne01()
					, STRING_VALIDER_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLieuDitOrigineRenseigne01()._____
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLieuDitOrigineRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.lieuDitOrigine.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLieuDitOrigineRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitLieuDitOrigineRenseigne01().__



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLieuDitOrigineRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitOrigineRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLieuDitOrigineRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLieuDitOrigineRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLieuDitOrigineRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitLieuDitOrigineRenseigne01();
	} // Fin de getValiderRGSectionHitLieuDitOrigineRenseigne01()._________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLieuDitOrigineRenseigne01 
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
	* valeur à passer à validerRGSectionHitLieuDitOrigineRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLieuDitOrigineRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitLieuDitOrigineRenseigne01
							, fournirKeyValiderRGSectionHitLieuDitOrigineRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLieuDitOrigineRenseigne01(...).______



	/**
	 * retourne le validerRGSectionHitLieuDitOrigineRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitOrigineRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLieuDitOrigineRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLieuDitOrigineRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitLieuDitOrigineRegex02
					, fournirKeyValiderRGSectionHitLieuDitOrigineRegex02()
					, STRING_VALIDER_SECTIONHIT_LIEUDITORIGINE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLieuDitOrigineRegex02()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLieuDitOrigineRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.lieuDitOrigine.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLieuDitOrigineRegex02() {
		return KEY_VALIDER_SECTIONHIT_LIEUDITORIGINE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitLieuDitOrigineRegex02().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLieuDitOrigineRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitOrigineRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLieuDitOrigineRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLieuDitOrigineRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLieuDitOrigineRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitLieuDitOrigineRegex02();
	} // Fin de getValiderRGSectionHitLieuDitOrigineRegex02()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLieuDitOrigineRegex02 
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
	* valeur à passer à validerRGSectionHitLieuDitOrigineRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLieuDitOrigineRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitLieuDitOrigineRegex02
							, fournirKeyValiderRGSectionHitLieuDitOrigineRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLieuDitOrigineRegex02(...).__________
	
	
	
	/* 19 - prOrigine. **************/
	/**
	 * retourne le <code>validerRGSectionHitPrOrigine</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>prOrigine</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>prOrigine</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPrOrigine stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPrOrigine 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPrOrigine() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitPrOrigine
					, fournirKeyValiderRGSectionHitPrOrigine()
					, STRING_VALIDER_SECTIONHIT_PRORIGINE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPrOrigine()._____________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPrOrigine 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.prOrigine".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PRORIGINE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPrOrigine() {
		return KEY_VALIDER_SECTIONHIT_PRORIGINE;
	} // Fin de fournirKeyValiderRGSectionHitPrOrigine().__________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPrOrigine par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitPrOrigine 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPrOrigine stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPrOrigine : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPrOrigine() 
													throws Exception {
		return fournirValiderRGSectionHitPrOrigine();
	} // Fin de getValiderRGSectionHitPrOrigine()._________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPrOrigine par défaut 
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
	* valeur à passer à validerRGSectionHitPrOrigine.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPrOrigine(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitPrOrigine
							, fournirKeyValiderRGSectionHitPrOrigine());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPrOrigine(...).______________________



	/**
	 * retourne le validerRGSectionHitPrOrigineRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPrOrigineRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPrOrigineRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPrOrigineRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPrOrigineRenseigne01
					, fournirKeyValiderRGSectionHitPrOrigineRenseigne01()
					, STRING_VALIDER_SECTIONHIT_PRORIGINE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPrOrigineRenseigne01().__________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPrOrigineRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.prOrigine.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PRORIGINE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPrOrigineRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_PRORIGINE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitPrOrigineRenseigne01()._______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPrOrigineRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPrOrigineRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPrOrigineRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPrOrigineRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPrOrigineRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitPrOrigineRenseigne01();
	} // Fin de getValiderRGSectionHitPrOrigineRenseigne01().______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPrOrigineRenseigne01 
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
	* valeur à passer à validerRGSectionHitPrOrigineRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPrOrigineRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPrOrigineRenseigne01
							, fournirKeyValiderRGSectionHitPrOrigineRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPrOrigineRenseigne01(...).___________



	/**
	 * retourne le validerRGSectionHitPrOrigineRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPrOrigineRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPrOrigineRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPrOrigineRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPrOrigineRegex02
					, fournirKeyValiderRGSectionHitPrOrigineRegex02()
					, STRING_VALIDER_SECTIONHIT_PRORIGINE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPrOrigineRegex02().______________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPrOrigineRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.prOrigine.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PRORIGINE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPrOrigineRegex02() {
		return KEY_VALIDER_SECTIONHIT_PRORIGINE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitPrOrigineRegex02().___________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPrOrigineRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPrOrigineRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPrOrigineRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPrOrigineRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPrOrigineRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitPrOrigineRegex02();
	} // Fin de getValiderRGSectionHitPrOrigineRegex02().__________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPrOrigineRegex02 
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
	* valeur à passer à validerRGSectionHitPrOrigineRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPrOrigineRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPrOrigineRegex02
							, fournirKeyValiderRGSectionHitPrOrigineRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPrOrigineRegex02(...)._______________



	/**
	 * retourne le validerRGSectionHitPrOrigineNumerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPrOrigineNumerique03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPrOrigineNumerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPrOrigineNumerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPrOrigineNumerique03
					, fournirKeyValiderRGSectionHitPrOrigineNumerique03()
					, STRING_VALIDER_SECTIONHIT_PRORIGINE_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPrOrigineNumerique03().__________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPrOrigineNumerique03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.prOrigine.numerique".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PRORIGINE_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPrOrigineNumerique03() {
		return KEY_VALIDER_SECTIONHIT_PRORIGINE_NUMERIQUE_03;
	} // Fin de fournirKeyValiderRGSectionHitPrOrigineNumerique03()._______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPrOrigineNumerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPrOrigineNumerique03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPrOrigineNumerique03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPrOrigineNumerique03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPrOrigineNumerique03() 
													throws Exception {
		return fournirValiderRGSectionHitPrOrigineNumerique03();
	} // Fin de getValiderRGSectionHitPrOrigineNumerique03().______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPrOrigineNumerique03 
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
	* valeur à passer à validerRGSectionHitPrOrigineNumerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPrOrigineNumerique03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPrOrigineNumerique03
							, fournirKeyValiderRGSectionHitPrOrigineNumerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPrOrigineNumerique03(...).___________
	
	
	
	/* 20 - absOrigine. **************/
	/**
	 * retourne le <code>validerRGSectionHitAbsOrigine</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>absOrigine</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>absOrigine</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsOrigine stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAbsOrigine 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAbsOrigine() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitAbsOrigine
					, fournirKeyValiderRGSectionHitAbsOrigine()
					, STRING_VALIDER_SECTIONHIT_ABSORIGINE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAbsOrigine().____________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAbsOrigine 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.absOrigine".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ABSORIGINE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAbsOrigine() {
		return KEY_VALIDER_SECTIONHIT_ABSORIGINE;
	} // Fin de fournirKeyValiderRGSectionHitAbsOrigine()._________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAbsOrigine par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitAbsOrigine 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAbsOrigine stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAbsOrigine : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAbsOrigine() 
													throws Exception {
		return fournirValiderRGSectionHitAbsOrigine();
	} // Fin de getValiderRGSectionHitAbsOrigine().________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAbsOrigine par défaut 
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
	* valeur à passer à validerRGSectionHitAbsOrigine.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAbsOrigine(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitAbsOrigine
							, fournirKeyValiderRGSectionHitAbsOrigine());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAbsOrigine(...)._____________________



	/**
	 * retourne le validerRGSectionHitAbsOrigineRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsOrigineRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAbsOrigineRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAbsOrigineRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitAbsOrigineRenseigne01
					, fournirKeyValiderRGSectionHitAbsOrigineRenseigne01()
					, STRING_VALIDER_SECTIONHIT_ABSORIGINE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAbsOrigineRenseigne01()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAbsOrigineRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.absOrigine.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ABSORIGINE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAbsOrigineRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_ABSORIGINE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitAbsOrigineRenseigne01().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAbsOrigineRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsOrigineRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAbsOrigineRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAbsOrigineRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAbsOrigineRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitAbsOrigineRenseigne01();
	} // Fin de getValiderRGSectionHitAbsOrigineRenseigne01()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAbsOrigineRenseigne01 
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
	* valeur à passer à validerRGSectionHitAbsOrigineRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAbsOrigineRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitAbsOrigineRenseigne01
							, fournirKeyValiderRGSectionHitAbsOrigineRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAbsOrigineRenseigne01(...).__________



	/**
	 * retourne le validerRGSectionHitAbsOrigineRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsOrigineRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAbsOrigineRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAbsOrigineRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitAbsOrigineRegex02
					, fournirKeyValiderRGSectionHitAbsOrigineRegex02()
					, STRING_VALIDER_SECTIONHIT_ABSORIGINE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAbsOrigineRegex02()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAbsOrigineRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.absOrigine.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ABSORIGINE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAbsOrigineRegex02() {
		return KEY_VALIDER_SECTIONHIT_ABSORIGINE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitAbsOrigineRegex02().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAbsOrigineRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsOrigineRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAbsOrigineRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAbsOrigineRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAbsOrigineRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitAbsOrigineRegex02();
	} // Fin de getValiderRGSectionHitAbsOrigineRegex02()._________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAbsOrigineRegex02 
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
	* valeur à passer à validerRGSectionHitAbsOrigineRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAbsOrigineRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitAbsOrigineRegex02
							, fournirKeyValiderRGSectionHitAbsOrigineRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAbsOrigineRegex02(...).______________



	/**
	 * retourne le validerRGSectionHitAbsOrigineNumerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsOrigineNumerique03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAbsOrigineNumerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAbsOrigineNumerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitAbsOrigineNumerique03
					, fournirKeyValiderRGSectionHitAbsOrigineNumerique03()
					, STRING_VALIDER_SECTIONHIT_ABSORIGINE_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAbsOrigineNumerique03()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAbsOrigineNumerique03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.absOrigine.numerique".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ABSORIGINE_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAbsOrigineNumerique03() {
		return KEY_VALIDER_SECTIONHIT_ABSORIGINE_NUMERIQUE_03;
	} // Fin de fournirKeyValiderRGSectionHitAbsOrigineNumerique03().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAbsOrigineNumerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsOrigineNumerique03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAbsOrigineNumerique03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAbsOrigineNumerique03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAbsOrigineNumerique03() 
													throws Exception {
		return fournirValiderRGSectionHitAbsOrigineNumerique03();
	} // Fin de getValiderRGSectionHitAbsOrigineNumerique03()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAbsOrigineNumerique03 
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
	* valeur à passer à validerRGSectionHitAbsOrigineNumerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAbsOrigineNumerique03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitAbsOrigineNumerique03
							, fournirKeyValiderRGSectionHitAbsOrigineNumerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAbsOrigineNumerique03(...).__________
	
	
	
	/* 21 - lieuDitExtremite. **************/
	/**
	 * retourne le <code>validerRGSectionHitLieuDitExtremite</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>lieuDitExtremite</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>lieuDitExtremite</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitExtremite stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLieuDitExtremite 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLieuDitExtremite() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitLieuDitExtremite
					, fournirKeyValiderRGSectionHitLieuDitExtremite()
					, STRING_VALIDER_SECTIONHIT_LIEUDITEXTREMITE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLieuDitExtremite().______________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLieuDitExtremite 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.lieuDitExtremite".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LIEUDITEXTREMITE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLieuDitExtremite() {
		return KEY_VALIDER_SECTIONHIT_LIEUDITEXTREMITE;
	} // Fin de fournirKeyValiderRGSectionHitLieuDitExtremite().___________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLieuDitExtremite par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitLieuDitExtremite 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLieuDitExtremite stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLieuDitExtremite : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLieuDitExtremite() 
													throws Exception {
		return fournirValiderRGSectionHitLieuDitExtremite();
	} // Fin de getValiderRGSectionHitLieuDitExtremite().__________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLieuDitExtremite par défaut 
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
	* valeur à passer à validerRGSectionHitLieuDitExtremite.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLieuDitExtremite(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitLieuDitExtremite
							, fournirKeyValiderRGSectionHitLieuDitExtremite());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLieuDitExtremite(...)._______________



	/**
	 * retourne le validerRGSectionHitLieuDitExtremiteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitExtremiteRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLieuDitExtremiteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLieuDitExtremiteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitLieuDitExtremiteRenseigne01
					, fournirKeyValiderRGSectionHitLieuDitExtremiteRenseigne01()
					, STRING_VALIDER_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLieuDitExtremiteRenseigne01().___
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLieuDitExtremiteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.lieuDitExtremite.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLieuDitExtremiteRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_LIEUDITEXTREMITE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitLieuDitExtremiteRenseigne01().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLieuDitExtremiteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitExtremiteRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLieuDitExtremiteRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLieuDitExtremiteRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLieuDitExtremiteRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitLieuDitExtremiteRenseigne01();
	} // Fin de getValiderRGSectionHitLieuDitExtremiteRenseigne01()._______
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLieuDitExtremiteRenseigne01 
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
	* valeur à passer à validerRGSectionHitLieuDitExtremiteRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLieuDitExtremiteRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitLieuDitExtremiteRenseigne01
							, fournirKeyValiderRGSectionHitLieuDitExtremiteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLieuDitExtremiteRenseigne01(...).____



	/**
	 * retourne le validerRGSectionHitLieuDitExtremiteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitExtremiteRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLieuDitExtremiteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLieuDitExtremiteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitLieuDitExtremiteRegex02
					, fournirKeyValiderRGSectionHitLieuDitExtremiteRegex02()
					, STRING_VALIDER_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLieuDitExtremiteRegex02()._______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLieuDitExtremiteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.lieuDitExtremite.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLieuDitExtremiteRegex02() {
		return KEY_VALIDER_SECTIONHIT_LIEUDITEXTREMITE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitLieuDitExtremiteRegex02().____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLieuDitExtremiteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitExtremiteRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLieuDitExtremiteRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLieuDitExtremiteRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLieuDitExtremiteRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitLieuDitExtremiteRegex02();
	} // Fin de getValiderRGSectionHitLieuDitExtremiteRegex02().___________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLieuDitExtremiteRegex02 
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
	* valeur à passer à validerRGSectionHitLieuDitExtremiteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLieuDitExtremiteRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitLieuDitExtremiteRegex02
							, fournirKeyValiderRGSectionHitLieuDitExtremiteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLieuDitExtremiteRegex02(...).________
	
	
	
	/* 22 - prExtremite. **************/
	/**
	 * retourne le <code>validerRGSectionHitPrExtremite</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>prExtremite</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>prExtremite</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPrExtremite stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPrExtremite 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPrExtremite() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitPrExtremite
					, fournirKeyValiderRGSectionHitPrExtremite()
					, STRING_VALIDER_SECTIONHIT_PREXTREMITE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPrExtremite().___________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPrExtremite 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.prExtremite".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PREXTREMITE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPrExtremite() {
		return KEY_VALIDER_SECTIONHIT_PREXTREMITE;
	} // Fin de fournirKeyValiderRGSectionHitPrExtremite().________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPrExtremite par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitPrExtremite 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPrExtremite stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPrExtremite : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPrExtremite() 
													throws Exception {
		return fournirValiderRGSectionHitPrExtremite();
	} // Fin de getValiderRGSectionHitPrExtremite()._______________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPrExtremite par défaut 
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
	* valeur à passer à validerRGSectionHitPrExtremite.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPrExtremite(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitPrExtremite
							, fournirKeyValiderRGSectionHitPrExtremite());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPrExtremite(...).____________________



	/**
	 * retourne le validerRGSectionHitPrExtremiteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPrExtremiteRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPrExtremiteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPrExtremiteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPrExtremiteRenseigne01
					, fournirKeyValiderRGSectionHitPrExtremiteRenseigne01()
					, STRING_VALIDER_SECTIONHIT_PREXTREMITE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPrExtremiteRenseigne01().________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPrExtremiteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.prExtremite.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PREXTREMITE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPrExtremiteRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_PREXTREMITE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitPrExtremiteRenseigne01()._____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPrExtremiteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPrExtremiteRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPrExtremiteRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPrExtremiteRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPrExtremiteRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitPrExtremiteRenseigne01();
	} // Fin de getValiderRGSectionHitPrExtremiteRenseigne01().____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPrExtremiteRenseigne01 
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
	* valeur à passer à validerRGSectionHitPrExtremiteRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPrExtremiteRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPrExtremiteRenseigne01
							, fournirKeyValiderRGSectionHitPrExtremiteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPrExtremiteRenseigne01(...)._________



	/**
	 * retourne le validerRGSectionHitPrExtremiteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPrExtremiteRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPrExtremiteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPrExtremiteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPrExtremiteRegex02
					, fournirKeyValiderRGSectionHitPrExtremiteRegex02()
					, STRING_VALIDER_SECTIONHIT_PREXTREMITE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPrExtremiteRegex02().____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPrExtremiteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.prExtremite.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PREXTREMITE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPrExtremiteRegex02() {
		return KEY_VALIDER_SECTIONHIT_PREXTREMITE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitPrExtremiteRegex02()._________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPrExtremiteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPrExtremiteRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPrExtremiteRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPrExtremiteRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPrExtremiteRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitPrExtremiteRegex02();
	} // Fin de getValiderRGSectionHitPrExtremiteRegex02().________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPrExtremiteRegex02 
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
	* valeur à passer à validerRGSectionHitPrExtremiteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPrExtremiteRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPrExtremiteRegex02
							, fournirKeyValiderRGSectionHitPrExtremiteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPrExtremiteRegex02(...)._____________



	/**
	 * retourne le validerRGSectionHitPrExtremiteNumerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPrExtremiteNumerique03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPrExtremiteNumerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPrExtremiteNumerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPrExtremiteNumerique03
					, fournirKeyValiderRGSectionHitPrExtremiteNumerique03()
					, STRING_VALIDER_SECTIONHIT_PREXTREMITE_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPrExtremiteNumerique03().________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPrExtremiteNumerique03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.prExtremite.numerique".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PREXTREMITE_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPrExtremiteNumerique03() {
		return KEY_VALIDER_SECTIONHIT_PREXTREMITE_NUMERIQUE_03;
	} // Fin de fournirKeyValiderRGSectionHitPrExtremiteNumerique03()._____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPrExtremiteNumerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPrExtremiteNumerique03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPrExtremiteNumerique03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPrExtremiteNumerique03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPrExtremiteNumerique03() 
													throws Exception {
		return fournirValiderRGSectionHitPrExtremiteNumerique03();
	} // Fin de getValiderRGSectionHitPrExtremiteNumerique03().____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPrExtremiteNumerique03 
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
	* valeur à passer à validerRGSectionHitPrExtremiteNumerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPrExtremiteNumerique03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPrExtremiteNumerique03
							, fournirKeyValiderRGSectionHitPrExtremiteNumerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPrExtremiteNumerique03(...)._________
	
	
	
	/* 23 - absExtremite. **************/
	/**
	 * retourne le <code>validerRGSectionHitAbsExtremite</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>absExtremite</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>absExtremite</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsExtremite stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAbsExtremite 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAbsExtremite() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitAbsExtremite
					, fournirKeyValiderRGSectionHitAbsExtremite()
					, STRING_VALIDER_SECTIONHIT_ABSEXTREMITE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAbsExtremite().__________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAbsExtremite 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.absExtremite".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ABSEXTREMITE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAbsExtremite() {
		return KEY_VALIDER_SECTIONHIT_ABSEXTREMITE;
	} // Fin de fournirKeyValiderRGSectionHitAbsExtremite()._______________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAbsExtremite par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitAbsExtremite 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAbsExtremite stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAbsExtremite : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAbsExtremite() 
													throws Exception {
		return fournirValiderRGSectionHitAbsExtremite();
	} // Fin de getValiderRGSectionHitAbsExtremite().______________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAbsExtremite par défaut 
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
	* valeur à passer à validerRGSectionHitAbsExtremite.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAbsExtremite(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitAbsExtremite
							, fournirKeyValiderRGSectionHitAbsExtremite());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAbsExtremite(...).___________________



	/**
	 * retourne le validerRGSectionHitAbsExtremiteRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsExtremiteRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAbsExtremiteRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAbsExtremiteRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitAbsExtremiteRenseigne01
					, fournirKeyValiderRGSectionHitAbsExtremiteRenseigne01()
					, STRING_VALIDER_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAbsExtremiteRenseigne01()._______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAbsExtremiteRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.absExtremite.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAbsExtremiteRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_ABSEXTREMITE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitAbsExtremiteRenseigne01().____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAbsExtremiteRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsExtremiteRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAbsExtremiteRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAbsExtremiteRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAbsExtremiteRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitAbsExtremiteRenseigne01();
	} // Fin de getValiderRGSectionHitAbsExtremiteRenseigne01().___________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAbsExtremiteRenseigne01 
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
	* valeur à passer à validerRGSectionHitAbsExtremiteRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAbsExtremiteRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitAbsExtremiteRenseigne01
							, fournirKeyValiderRGSectionHitAbsExtremiteRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAbsExtremiteRenseigne01(...).________



	/**
	 * retourne le validerRGSectionHitAbsExtremiteRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsExtremiteRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAbsExtremiteRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAbsExtremiteRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitAbsExtremiteRegex02
					, fournirKeyValiderRGSectionHitAbsExtremiteRegex02()
					, STRING_VALIDER_SECTIONHIT_ABSEXTREMITE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAbsExtremiteRegex02().___________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAbsExtremiteRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.absExtremite.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ABSEXTREMITE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAbsExtremiteRegex02() {
		return KEY_VALIDER_SECTIONHIT_ABSEXTREMITE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitAbsExtremiteRegex02().________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAbsExtremiteRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsExtremiteRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAbsExtremiteRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAbsExtremiteRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAbsExtremiteRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitAbsExtremiteRegex02();
	} // Fin de getValiderRGSectionHitAbsExtremiteRegex02()._______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAbsExtremiteRegex02 
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
	* valeur à passer à validerRGSectionHitAbsExtremiteRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAbsExtremiteRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitAbsExtremiteRegex02
							, fournirKeyValiderRGSectionHitAbsExtremiteRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAbsExtremiteRegex02(...).____________



	/**
	 * retourne le validerRGSectionHitAbsExtremiteNumerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsExtremiteNumerique03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAbsExtremiteNumerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAbsExtremiteNumerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitAbsExtremiteNumerique03
					, fournirKeyValiderRGSectionHitAbsExtremiteNumerique03()
					, STRING_VALIDER_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAbsExtremiteNumerique03()._______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAbsExtremiteNumerique03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.absExtremite.numerique".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAbsExtremiteNumerique03() {
		return KEY_VALIDER_SECTIONHIT_ABSEXTREMITE_NUMERIQUE_03;
	} // Fin de fournirKeyValiderRGSectionHitAbsExtremiteNumerique03().____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAbsExtremiteNumerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsExtremiteNumerique03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAbsExtremiteNumerique03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAbsExtremiteNumerique03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAbsExtremiteNumerique03() 
													throws Exception {
		return fournirValiderRGSectionHitAbsExtremiteNumerique03();
	} // Fin de getValiderRGSectionHitAbsExtremiteNumerique03().___________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAbsExtremiteNumerique03 
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
	* valeur à passer à validerRGSectionHitAbsExtremiteNumerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAbsExtremiteNumerique03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitAbsExtremiteNumerique03
							, fournirKeyValiderRGSectionHitAbsExtremiteNumerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAbsExtremiteNumerique03(...).________
	
	
	
	/* 24 - lieuDitComptage. **************/
	/**
	 * retourne le <code>validerRGSectionHitLieuDitComptage</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>lieuDitComptage</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>lieuDitComptage</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitComptage stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLieuDitComptage 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLieuDitComptage() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitLieuDitComptage
					, fournirKeyValiderRGSectionHitLieuDitComptage()
					, STRING_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLieuDitComptage()._______________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLieuDitComptage 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.lieuDitComptage".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLieuDitComptage() {
		return KEY_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE;
	} // Fin de fournirKeyValiderRGSectionHitLieuDitComptage().____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLieuDitComptage par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitLieuDitComptage 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLieuDitComptage stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLieuDitComptage : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLieuDitComptage() 
													throws Exception {
		return fournirValiderRGSectionHitLieuDitComptage();
	} // Fin de getValiderRGSectionHitLieuDitComptage().___________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLieuDitComptage par défaut 
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
	* valeur à passer à validerRGSectionHitLieuDitComptage.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLieuDitComptage(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitLieuDitComptage
							, fournirKeyValiderRGSectionHitLieuDitComptage());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLieuDitComptage(...).________________



	/**
	 * retourne le validerRGSectionHitLieuDitComptageRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitComptageRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLieuDitComptageRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLieuDitComptageRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitLieuDitComptageRenseigne01
					, fournirKeyValiderRGSectionHitLieuDitComptageRenseigne01()
					, STRING_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLieuDitComptageRenseigne01().____
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLieuDitComptageRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.lieuDitComptage.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLieuDitComptageRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitLieuDitComptageRenseigne01()._



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLieuDitComptageRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitComptageRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLieuDitComptageRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLieuDitComptageRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLieuDitComptageRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitLieuDitComptageRenseigne01();
	} // Fin de getValiderRGSectionHitLieuDitComptageRenseigne01().________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLieuDitComptageRenseigne01 
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
	* valeur à passer à validerRGSectionHitLieuDitComptageRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLieuDitComptageRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitLieuDitComptageRenseigne01
							, fournirKeyValiderRGSectionHitLieuDitComptageRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLieuDitComptageRenseigne01(...)._____



	/**
	 * retourne le validerRGSectionHitLieuDitComptageRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitComptageRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLieuDitComptageRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLieuDitComptageRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitLieuDitComptageRegex02
					, fournirKeyValiderRGSectionHitLieuDitComptageRegex02()
					, STRING_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLieuDitComptageRegex02().________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLieuDitComptageRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.lieuDitComptage.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLieuDitComptageRegex02() {
		return KEY_VALIDER_SECTIONHIT_LIEUDITCOMPTAGE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitLieuDitComptageRegex02()._____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLieuDitComptageRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitLieuDitComptageRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLieuDitComptageRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLieuDitComptageRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLieuDitComptageRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitLieuDitComptageRegex02();
	} // Fin de getValiderRGSectionHitLieuDitComptageRegex02().____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLieuDitComptageRegex02 
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
	* valeur à passer à validerRGSectionHitLieuDitComptageRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLieuDitComptageRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitLieuDitComptageRegex02
							, fournirKeyValiderRGSectionHitLieuDitComptageRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLieuDitComptageRegex02(...)._________
	
	
	
	/* 25 - prComptage. **************/
	/**
	 * retourne le <code>validerRGSectionHitPrComptage</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>prComptage</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>prComptage</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPrComptage stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPrComptage 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPrComptage() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitPrComptage
					, fournirKeyValiderRGSectionHitPrComptage()
					, STRING_VALIDER_SECTIONHIT_PRCOMPTAGE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPrComptage().____________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPrComptage 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.prComptage".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PRCOMPTAGE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPrComptage() {
		return KEY_VALIDER_SECTIONHIT_PRCOMPTAGE;
	} // Fin de fournirKeyValiderRGSectionHitPrComptage()._________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPrComptage par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitPrComptage 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPrComptage stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPrComptage : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPrComptage() 
													throws Exception {
		return fournirValiderRGSectionHitPrComptage();
	} // Fin de getValiderRGSectionHitPrComptage().________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPrComptage par défaut 
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
	* valeur à passer à validerRGSectionHitPrComptage.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPrComptage(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitPrComptage
							, fournirKeyValiderRGSectionHitPrComptage());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPrComptage(...)._____________________



	/**
	 * retourne le validerRGSectionHitPrComptageRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPrComptageRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPrComptageRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPrComptageRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPrComptageRenseigne01
					, fournirKeyValiderRGSectionHitPrComptageRenseigne01()
					, STRING_VALIDER_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPrComptageRenseigne01()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPrComptageRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.prComptage.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPrComptageRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_PRCOMPTAGE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitPrComptageRenseigne01().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPrComptageRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPrComptageRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPrComptageRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPrComptageRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPrComptageRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitPrComptageRenseigne01();
	} // Fin de getValiderRGSectionHitPrComptageRenseigne01()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPrComptageRenseigne01 
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
	* valeur à passer à validerRGSectionHitPrComptageRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPrComptageRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPrComptageRenseigne01
							, fournirKeyValiderRGSectionHitPrComptageRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPrComptageRenseigne01(...).__________



	/**
	 * retourne le validerRGSectionHitPrComptageRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPrComptageRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPrComptageRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPrComptageRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPrComptageRegex02
					, fournirKeyValiderRGSectionHitPrComptageRegex02()
					, STRING_VALIDER_SECTIONHIT_PRCOMPTAGE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPrComptageRegex02()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPrComptageRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.prComptage.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PRCOMPTAGE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPrComptageRegex02() {
		return KEY_VALIDER_SECTIONHIT_PRCOMPTAGE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitPrComptageRegex02().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPrComptageRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPrComptageRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPrComptageRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPrComptageRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPrComptageRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitPrComptageRegex02();
	} // Fin de getValiderRGSectionHitPrComptageRegex02()._________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPrComptageRegex02 
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
	* valeur à passer à validerRGSectionHitPrComptageRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPrComptageRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPrComptageRegex02
							, fournirKeyValiderRGSectionHitPrComptageRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPrComptageRegex02(...).______________



	/**
	 * retourne le validerRGSectionHitPrComptageNumerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPrComptageNumerique03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPrComptageNumerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPrComptageNumerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPrComptageNumerique03
					, fournirKeyValiderRGSectionHitPrComptageNumerique03()
					, STRING_VALIDER_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPrComptageNumerique03()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPrComptageNumerique03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.prComptage.numerique".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPrComptageNumerique03() {
		return KEY_VALIDER_SECTIONHIT_PRCOMPTAGE_NUMERIQUE_03;
	} // Fin de fournirKeyValiderRGSectionHitPrComptageNumerique03().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPrComptageNumerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPrComptageNumerique03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPrComptageNumerique03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPrComptageNumerique03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPrComptageNumerique03() 
													throws Exception {
		return fournirValiderRGSectionHitPrComptageNumerique03();
	} // Fin de getValiderRGSectionHitPrComptageNumerique03()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPrComptageNumerique03 
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
	* valeur à passer à validerRGSectionHitPrComptageNumerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPrComptageNumerique03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPrComptageNumerique03
							, fournirKeyValiderRGSectionHitPrComptageNumerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPrComptageNumerique03(...).__________
	
	
	
	/* 26 - absComptage. **************/
	/**
	 * retourne le <code>validerRGSectionHitAbsComptage</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>absComptage</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>absComptage</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsComptage stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAbsComptage 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAbsComptage() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitAbsComptage
					, fournirKeyValiderRGSectionHitAbsComptage()
					, STRING_VALIDER_SECTIONHIT_ABSCOMPTAGE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAbsComptage().___________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAbsComptage 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.absComptage".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ABSCOMPTAGE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAbsComptage() {
		return KEY_VALIDER_SECTIONHIT_ABSCOMPTAGE;
	} // Fin de fournirKeyValiderRGSectionHitAbsComptage().________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAbsComptage par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitAbsComptage 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAbsComptage stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAbsComptage : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAbsComptage() 
													throws Exception {
		return fournirValiderRGSectionHitAbsComptage();
	} // Fin de getValiderRGSectionHitAbsComptage()._______________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAbsComptage par défaut 
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
	* valeur à passer à validerRGSectionHitAbsComptage.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAbsComptage(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitAbsComptage
							, fournirKeyValiderRGSectionHitAbsComptage());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAbsComptage(...).____________________



	/**
	 * retourne le validerRGSectionHitAbsComptageRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsComptageRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAbsComptageRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAbsComptageRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitAbsComptageRenseigne01
					, fournirKeyValiderRGSectionHitAbsComptageRenseigne01()
					, STRING_VALIDER_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAbsComptageRenseigne01().________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAbsComptageRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.absComptage.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAbsComptageRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_ABSCOMPTAGE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitAbsComptageRenseigne01()._____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAbsComptageRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsComptageRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAbsComptageRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAbsComptageRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAbsComptageRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitAbsComptageRenseigne01();
	} // Fin de getValiderRGSectionHitAbsComptageRenseigne01().____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAbsComptageRenseigne01 
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
	* valeur à passer à validerRGSectionHitAbsComptageRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAbsComptageRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitAbsComptageRenseigne01
							, fournirKeyValiderRGSectionHitAbsComptageRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAbsComptageRenseigne01(...)._________



	/**
	 * retourne le validerRGSectionHitAbsComptageRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsComptageRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAbsComptageRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAbsComptageRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitAbsComptageRegex02
					, fournirKeyValiderRGSectionHitAbsComptageRegex02()
					, STRING_VALIDER_SECTIONHIT_ABSCOMPTAGE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAbsComptageRegex02().____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAbsComptageRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.absComptage.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ABSCOMPTAGE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAbsComptageRegex02() {
		return KEY_VALIDER_SECTIONHIT_ABSCOMPTAGE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitAbsComptageRegex02()._________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAbsComptageRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsComptageRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAbsComptageRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAbsComptageRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAbsComptageRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitAbsComptageRegex02();
	} // Fin de getValiderRGSectionHitAbsComptageRegex02().________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAbsComptageRegex02 
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
	* valeur à passer à validerRGSectionHitAbsComptageRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAbsComptageRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitAbsComptageRegex02
							, fournirKeyValiderRGSectionHitAbsComptageRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAbsComptageRegex02(...)._____________



	/**
	 * retourne le validerRGSectionHitAbsComptageNumerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsComptageNumerique03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAbsComptageNumerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAbsComptageNumerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitAbsComptageNumerique03
					, fournirKeyValiderRGSectionHitAbsComptageNumerique03()
					, STRING_VALIDER_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAbsComptageNumerique03().________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAbsComptageNumerique03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.absComptage.numerique".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAbsComptageNumerique03() {
		return KEY_VALIDER_SECTIONHIT_ABSCOMPTAGE_NUMERIQUE_03;
	} // Fin de fournirKeyValiderRGSectionHitAbsComptageNumerique03()._____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAbsComptageNumerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitAbsComptageNumerique03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAbsComptageNumerique03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAbsComptageNumerique03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAbsComptageNumerique03() 
													throws Exception {
		return fournirValiderRGSectionHitAbsComptageNumerique03();
	} // Fin de getValiderRGSectionHitAbsComptageNumerique03().____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAbsComptageNumerique03 
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
	* valeur à passer à validerRGSectionHitAbsComptageNumerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAbsComptageNumerique03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitAbsComptageNumerique03
							, fournirKeyValiderRGSectionHitAbsComptageNumerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAbsComptageNumerique03(...)._________
	
	
	
	/* 27 - longueurSection. **************/
	/**
	 * retourne le <code>validerRGSectionHitLongueurSection</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>longueurSection</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>longueurSection</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLongueurSection stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLongueurSection 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLongueurSection() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitLongueurSection
					, fournirKeyValiderRGSectionHitLongueurSection()
					, STRING_VALIDER_SECTIONHIT_LONGUEURSECTION_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLongueurSection()._______________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLongueurSection 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.longueurSection".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LONGUEURSECTION : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLongueurSection() {
		return KEY_VALIDER_SECTIONHIT_LONGUEURSECTION;
	} // Fin de fournirKeyValiderRGSectionHitLongueurSection().____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLongueurSection par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitLongueurSection 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLongueurSection stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLongueurSection : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLongueurSection() 
													throws Exception {
		return fournirValiderRGSectionHitLongueurSection();
	} // Fin de getValiderRGSectionHitLongueurSection().___________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLongueurSection par défaut 
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
	* valeur à passer à validerRGSectionHitLongueurSection.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLongueurSection(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitLongueurSection
							, fournirKeyValiderRGSectionHitLongueurSection());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLongueurSection(...).________________



	/**
	 * retourne le validerRGSectionHitLongueurSectionRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLongueurSectionRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLongueurSectionRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLongueurSectionRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitLongueurSectionRenseigne01
					, fournirKeyValiderRGSectionHitLongueurSectionRenseigne01()
					, STRING_VALIDER_SECTIONHIT_LONGUEURSECTION_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLongueurSectionRenseigne01().____
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLongueurSectionRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.longueurSection.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LONGUEURSECTION_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLongueurSectionRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_LONGUEURSECTION_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitLongueurSectionRenseigne01()._



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLongueurSectionRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitLongueurSectionRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLongueurSectionRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLongueurSectionRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLongueurSectionRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitLongueurSectionRenseigne01();
	} // Fin de getValiderRGSectionHitLongueurSectionRenseigne01().________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLongueurSectionRenseigne01 
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
	* valeur à passer à validerRGSectionHitLongueurSectionRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLongueurSectionRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitLongueurSectionRenseigne01
							, fournirKeyValiderRGSectionHitLongueurSectionRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLongueurSectionRenseigne01(...)._____



	/**
	 * retourne le validerRGSectionHitLongueurSectionRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLongueurSectionRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLongueurSectionRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLongueurSectionRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitLongueurSectionRegex02
					, fournirKeyValiderRGSectionHitLongueurSectionRegex02()
					, STRING_VALIDER_SECTIONHIT_LONGUEURSECTION_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLongueurSectionRegex02().________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLongueurSectionRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.longueurSection.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LONGUEURSECTION_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLongueurSectionRegex02() {
		return KEY_VALIDER_SECTIONHIT_LONGUEURSECTION_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitLongueurSectionRegex02()._____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLongueurSectionRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitLongueurSectionRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLongueurSectionRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLongueurSectionRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLongueurSectionRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitLongueurSectionRegex02();
	} // Fin de getValiderRGSectionHitLongueurSectionRegex02().____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLongueurSectionRegex02 
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
	* valeur à passer à validerRGSectionHitLongueurSectionRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLongueurSectionRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitLongueurSectionRegex02
							, fournirKeyValiderRGSectionHitLongueurSectionRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLongueurSectionRegex02(...)._________



	/**
	 * retourne le validerRGSectionHitLongueurSectionNumerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLongueurSectionNumerique03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLongueurSectionNumerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLongueurSectionNumerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitLongueurSectionNumerique03
					, fournirKeyValiderRGSectionHitLongueurSectionNumerique03()
					, STRING_VALIDER_SECTIONHIT_LONGUEURSECTION_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLongueurSectionNumerique03().____
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLongueurSectionNumerique03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.longueurSection.numerique".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LONGUEURSECTION_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLongueurSectionNumerique03() {
		return KEY_VALIDER_SECTIONHIT_LONGUEURSECTION_NUMERIQUE_03;
	} // Fin de fournirKeyValiderRGSectionHitLongueurSectionNumerique03()._



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLongueurSectionNumerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitLongueurSectionNumerique03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLongueurSectionNumerique03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLongueurSectionNumerique03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLongueurSectionNumerique03() 
													throws Exception {
		return fournirValiderRGSectionHitLongueurSectionNumerique03();
	} // Fin de getValiderRGSectionHitLongueurSectionNumerique03().________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLongueurSectionNumerique03 
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
	* valeur à passer à validerRGSectionHitLongueurSectionNumerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLongueurSectionNumerique03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitLongueurSectionNumerique03
							, fournirKeyValiderRGSectionHitLongueurSectionNumerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLongueurSectionNumerique03(...)._____
	
	
	
	/* 28 - longueurRaseCampagne. **************/
	/**
	 * retourne le <code>validerRGSectionHitLongueurRaseCampagne</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>longueurRaseCampagne</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>longueurRaseCampagne</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLongueurRaseCampagne stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLongueurRaseCampagne 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLongueurRaseCampagne() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitLongueurRaseCampagne
					, fournirKeyValiderRGSectionHitLongueurRaseCampagne()
					, STRING_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLongueurRaseCampagne().__________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLongueurRaseCampagne 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.longueurRaseCampagne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLongueurRaseCampagne() {
		return KEY_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE;
	} // Fin de fournirKeyValiderRGSectionHitLongueurRaseCampagne()._______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLongueurRaseCampagne par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitLongueurRaseCampagne 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLongueurRaseCampagne stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLongueurRaseCampagne : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLongueurRaseCampagne() 
													throws Exception {
		return fournirValiderRGSectionHitLongueurRaseCampagne();
	} // Fin de getValiderRGSectionHitLongueurRaseCampagne().______________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLongueurRaseCampagne par défaut 
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
	* valeur à passer à validerRGSectionHitLongueurRaseCampagne.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLongueurRaseCampagne(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitLongueurRaseCampagne
							, fournirKeyValiderRGSectionHitLongueurRaseCampagne());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLongueurRaseCampagne(...).___________



	/**
	 * retourne le validerRGSectionHitLongueurRaseCampagneRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLongueurRaseCampagneRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLongueurRaseCampagneRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLongueurRaseCampagneRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitLongueurRaseCampagneRenseigne01
					, fournirKeyValiderRGSectionHitLongueurRaseCampagneRenseigne01()
					, STRING_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLongueurRaseCampagneRenseigne01().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLongueurRaseCampagneRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.longueurRaseCampagne.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLongueurRaseCampagneRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitLongueurRaseCampagneRenseigne01().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLongueurRaseCampagneRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitLongueurRaseCampagneRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLongueurRaseCampagneRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLongueurRaseCampagneRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLongueurRaseCampagneRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitLongueurRaseCampagneRenseigne01();
	} // Fin de getValiderRGSectionHitLongueurRaseCampagneRenseigne01().___
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLongueurRaseCampagneRenseigne01 
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
	* valeur à passer à validerRGSectionHitLongueurRaseCampagneRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLongueurRaseCampagneRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitLongueurRaseCampagneRenseigne01
							, fournirKeyValiderRGSectionHitLongueurRaseCampagneRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLongueurRaseCampagneRenseigne01(...).



	/**
	 * retourne le validerRGSectionHitLongueurRaseCampagneRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLongueurRaseCampagneRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLongueurRaseCampagneRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLongueurRaseCampagneRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitLongueurRaseCampagneRegex02
					, fournirKeyValiderRGSectionHitLongueurRaseCampagneRegex02()
					, STRING_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLongueurRaseCampagneRegex02().___
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLongueurRaseCampagneRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.longueurRaseCampagne.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLongueurRaseCampagneRegex02() {
		return KEY_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitLongueurRaseCampagneRegex02().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLongueurRaseCampagneRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitLongueurRaseCampagneRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLongueurRaseCampagneRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLongueurRaseCampagneRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLongueurRaseCampagneRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitLongueurRaseCampagneRegex02();
	} // Fin de getValiderRGSectionHitLongueurRaseCampagneRegex02()._______
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLongueurRaseCampagneRegex02 
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
	* valeur à passer à validerRGSectionHitLongueurRaseCampagneRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLongueurRaseCampagneRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitLongueurRaseCampagneRegex02
							, fournirKeyValiderRGSectionHitLongueurRaseCampagneRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLongueurRaseCampagneRegex02(...).____



	/**
	 * retourne le validerRGSectionHitLongueurRaseCampagneNumerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitLongueurRaseCampagneNumerique03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitLongueurRaseCampagneNumerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitLongueurRaseCampagneNumerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitLongueurRaseCampagneNumerique03
					, fournirKeyValiderRGSectionHitLongueurRaseCampagneNumerique03()
					, STRING_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitLongueurRaseCampagneNumerique03().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitLongueurRaseCampagneNumerique03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.longueurRaseCampagne.numerique".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitLongueurRaseCampagneNumerique03() {
		return KEY_VALIDER_SECTIONHIT_LONGUEURRASECAMPAGNE_NUMERIQUE_03;
	} // Fin de fournirKeyValiderRGSectionHitLongueurRaseCampagneNumerique03().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitLongueurRaseCampagneNumerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitLongueurRaseCampagneNumerique03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitLongueurRaseCampagneNumerique03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitLongueurRaseCampagneNumerique03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitLongueurRaseCampagneNumerique03() 
													throws Exception {
		return fournirValiderRGSectionHitLongueurRaseCampagneNumerique03();
	} // Fin de getValiderRGSectionHitLongueurRaseCampagneNumerique03().___
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitLongueurRaseCampagneNumerique03 
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
	* valeur à passer à validerRGSectionHitLongueurRaseCampagneNumerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitLongueurRaseCampagneNumerique03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitLongueurRaseCampagneNumerique03
							, fournirKeyValiderRGSectionHitLongueurRaseCampagneNumerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitLongueurRaseCampagneNumerique03(...).
	
	
	
	/* 29 - numDepartementRattachement. **************/
	/**
	 * retourne le <code>validerRGSectionHitNumDepartementRattachement</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>numDepartementRattachement</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numDepartementRattachement</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartementRattachement stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumDepartementRattachement 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumDepartementRattachement() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitNumDepartementRattachement
					, fournirKeyValiderRGSectionHitNumDepartementRattachement()
					, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumDepartementRattachement().____
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumDepartementRattachement 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numDepartementRattachement".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumDepartementRattachement() {
		return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT;
	} // Fin de fournirKeyValiderRGSectionHitNumDepartementRattachement()._



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumDepartementRattachement par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitNumDepartementRattachement 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumDepartementRattachement stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumDepartementRattachement : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumDepartementRattachement() 
													throws Exception {
		return fournirValiderRGSectionHitNumDepartementRattachement();
	} // Fin de getValiderRGSectionHitNumDepartementRattachement().________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumDepartementRattachement par défaut 
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
	* valeur à passer à validerRGSectionHitNumDepartementRattachement.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumDepartementRattachement(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitNumDepartementRattachement
							, fournirKeyValiderRGSectionHitNumDepartementRattachement());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumDepartementRattachement(...)._____



	/**
	 * retourne le validerRGSectionHitNumDepartementRattachementRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartementRattachementRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumDepartementRattachementRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumDepartementRattachementRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumDepartementRattachementRenseigne01
					, fournirKeyValiderRGSectionHitNumDepartementRattachementRenseigne01()
					, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumDepartementRattachementRenseigne01().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumDepartementRattachementRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numDepartementRattachement.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumDepartementRattachementRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitNumDepartementRattachementRenseigne01().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumDepartementRattachementRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartementRattachementRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumDepartementRattachementRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumDepartementRattachementRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumDepartementRattachementRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitNumDepartementRattachementRenseigne01();
	} // Fin de getValiderRGSectionHitNumDepartementRattachementRenseigne01().
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumDepartementRattachementRenseigne01 
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
	* valeur à passer à validerRGSectionHitNumDepartementRattachementRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumDepartementRattachementRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumDepartementRattachementRenseigne01
							, fournirKeyValiderRGSectionHitNumDepartementRattachementRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumDepartementRattachementRenseigne01(...).



	/**
	 * retourne le validerRGSectionHitNumDepartementRattachementRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartementRattachementRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumDepartementRattachementRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumDepartementRattachementRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumDepartementRattachementRegex02
					, fournirKeyValiderRGSectionHitNumDepartementRattachementRegex02()
					, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumDepartementRattachementRegex02().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumDepartementRattachementRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numDepartementRattachement.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumDepartementRattachementRegex02() {
		return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTRATTACHEMENT_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitNumDepartementRattachementRegex02().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumDepartementRattachementRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartementRattachementRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumDepartementRattachementRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumDepartementRattachementRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumDepartementRattachementRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitNumDepartementRattachementRegex02();
	} // Fin de getValiderRGSectionHitNumDepartementRattachementRegex02()._
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumDepartementRattachementRegex02 
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
	* valeur à passer à validerRGSectionHitNumDepartementRattachementRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumDepartementRattachementRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumDepartementRattachementRegex02
							, fournirKeyValiderRGSectionHitNumDepartementRattachementRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumDepartementRattachementRegex02(...).
	
	
	
	/* 30 - numSectionRattachement. **************/
	/**
	 * retourne le <code>validerRGSectionHitNumSectionRattachement</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>numSectionRattachement</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numSectionRattachement</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSectionRattachement stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumSectionRattachement 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumSectionRattachement() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitNumSectionRattachement
					, fournirKeyValiderRGSectionHitNumSectionRattachement()
					, STRING_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumSectionRattachement().________________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumSectionRattachement 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numSectionRattachement".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumSectionRattachement() {
		return KEY_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT;
	} // Fin de fournirKeyValiderRGSectionHitNumSectionRattachement()._____________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumSectionRattachement par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitNumSectionRattachement 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumSectionRattachement stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumSectionRattachement : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumSectionRattachement() 
													throws Exception {
		return fournirValiderRGSectionHitNumSectionRattachement();
	} // Fin de getValiderRGSectionHitNumSectionRattachement().____________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumSectionRattachement par défaut 
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
	* valeur à passer à validerRGSectionHitNumSectionRattachement.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumSectionRattachement(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitNumSectionRattachement
							, fournirKeyValiderRGSectionHitNumSectionRattachement());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumSectionRattachement(...)._________________________



	/**
	 * retourne le validerRGSectionHitNumSectionRattachementRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSectionRattachementRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumSectionRattachementRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumSectionRattachementRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumSectionRattachementRenseigne01
					, fournirKeyValiderRGSectionHitNumSectionRattachementRenseigne01()
					, STRING_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumSectionRattachementRenseigne01().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumSectionRattachementRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numSectionRattachement.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumSectionRattachementRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitNumSectionRattachementRenseigne01().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumSectionRattachementRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSectionRattachementRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumSectionRattachementRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumSectionRattachementRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumSectionRattachementRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitNumSectionRattachementRenseigne01();
	} // Fin de getValiderRGSectionHitNumSectionRattachementRenseigne01()._
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumSectionRattachementRenseigne01 
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
	* valeur à passer à validerRGSectionHitNumSectionRattachementRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumSectionRattachementRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumSectionRattachementRenseigne01
							, fournirKeyValiderRGSectionHitNumSectionRattachementRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumSectionRattachementRenseigne01(...).



	/**
	 * retourne le validerRGSectionHitNumSectionRattachementRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSectionRattachementRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumSectionRattachementRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumSectionRattachementRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumSectionRattachementRegex02
					, fournirKeyValiderRGSectionHitNumSectionRattachementRegex02()
					, STRING_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumSectionRattachementRegex02()._
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumSectionRattachementRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numSectionRattachement.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumSectionRattachementRegex02() {
		return KEY_VALIDER_SECTIONHIT_NUMSECTIONRATTACHEMENT_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitNumSectionRattachementRegex02().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumSectionRattachementRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSectionRattachementRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumSectionRattachementRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumSectionRattachementRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumSectionRattachementRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitNumSectionRattachementRegex02();
	} // Fin de getValiderRGSectionHitNumSectionRattachementRegex02()._____
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumSectionRattachementRegex02 
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
	* valeur à passer à validerRGSectionHitNumSectionRattachementRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumSectionRattachementRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumSectionRattachementRegex02
							, fournirKeyValiderRGSectionHitNumSectionRattachementRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumSectionRattachementRegex02(...).__
	
	
	
	/* 31 - sensRattachement. **************/
	/**
	 * retourne le <code>validerRGSectionHitSensRattachement</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>sensRattachement</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>sensRattachement</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitSensRattachement stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitSensRattachement 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitSensRattachement() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitSensRattachement
					, fournirKeyValiderRGSectionHitSensRattachement()
					, STRING_VALIDER_SECTIONHIT_SENSRATTACHEMENT_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitSensRattachement().______________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitSensRattachement 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.sensRattachement".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_SENSRATTACHEMENT : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitSensRattachement() {
		return KEY_VALIDER_SECTIONHIT_SENSRATTACHEMENT;
	} // Fin de fournirKeyValiderRGSectionHitSensRattachement().___________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitSensRattachement par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitSensRattachement 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitSensRattachement stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitSensRattachement : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitSensRattachement() 
													throws Exception {
		return fournirValiderRGSectionHitSensRattachement();
	} // Fin de getValiderRGSectionHitSensRattachement().__________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitSensRattachement par défaut 
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
	* valeur à passer à validerRGSectionHitSensRattachement.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitSensRattachement(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitSensRattachement
							, fournirKeyValiderRGSectionHitSensRattachement());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitSensRattachement(...)._______________



	/**
	 * retourne le validerRGSectionHitSensRattachementRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitSensRattachementRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitSensRattachementRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitSensRattachementRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitSensRattachementRenseigne01
					, fournirKeyValiderRGSectionHitSensRattachementRenseigne01()
					, STRING_VALIDER_SECTIONHIT_SENSRATTACHEMENT_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitSensRattachementRenseigne01().___
	

	
	/**
	 * Getter de la clé du validerRGSectionHitSensRattachementRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.sensRattachement.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_SENSRATTACHEMENT_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitSensRattachementRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_SENSRATTACHEMENT_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitSensRattachementRenseigne01().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitSensRattachementRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitSensRattachementRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitSensRattachementRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitSensRattachementRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitSensRattachementRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitSensRattachementRenseigne01();
	} // Fin de getValiderRGSectionHitSensRattachementRenseigne01()._______
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitSensRattachementRenseigne01 
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
	* valeur à passer à validerRGSectionHitSensRattachementRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitSensRattachementRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitSensRattachementRenseigne01
							, fournirKeyValiderRGSectionHitSensRattachementRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitSensRattachementRenseigne01(...).____



	/**
	 * retourne le validerRGSectionHitSensRattachementRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitSensRattachementRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitSensRattachementRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitSensRattachementRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitSensRattachementRegex02
					, fournirKeyValiderRGSectionHitSensRattachementRegex02()
					, STRING_VALIDER_SECTIONHIT_SENSRATTACHEMENT_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitSensRattachementRegex02()._______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitSensRattachementRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.sensRattachement.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_SENSRATTACHEMENT_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitSensRattachementRegex02() {
		return KEY_VALIDER_SECTIONHIT_SENSRATTACHEMENT_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitSensRattachementRegex02().____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitSensRattachementRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitSensRattachementRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitSensRattachementRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitSensRattachementRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitSensRattachementRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitSensRattachementRegex02();
	} // Fin de getValiderRGSectionHitSensRattachementRegex02().___________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitSensRattachementRegex02 
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
	* valeur à passer à validerRGSectionHitSensRattachementRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitSensRattachementRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitSensRattachementRegex02
							, fournirKeyValiderRGSectionHitSensRattachementRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitSensRattachementRegex02(...).________



	/**
	 * retourne le validerRGSectionHitSensRattachementNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitSensRattachementNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitSensRattachementNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitSensRattachementNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitSensRattachementNomenclature03
					, fournirKeyValiderRGSectionHitSensRattachementNomenclature03()
					, STRING_VALIDER_SECTIONHIT_SENSRATTACHEMENT_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitSensRattachementNomenclature03().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitSensRattachementNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.sensRattachement.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_SENSRATTACHEMENT_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitSensRattachementNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_SENSRATTACHEMENT_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitSensRattachementNomenclature03().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitSensRattachementNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitSensRattachementNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitSensRattachementNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitSensRattachementNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitSensRattachementNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitSensRattachementNomenclature03();
	} // Fin de getValiderRGSectionHitSensRattachementNomenclature03().____
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitSensRattachementNomenclature03 
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
	* valeur à passer à validerRGSectionHitSensRattachementNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitSensRattachementNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitSensRattachementNomenclature03
							, fournirKeyValiderRGSectionHitSensRattachementNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitSensRattachementNomenclature03(...)._
	
	
	
	/* 32 - numDepartementLimitrophe. **************/
	/**
	 * retourne le <code>validerRGSectionHitNumDepartementLimitrophe</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>numDepartementLimitrophe</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numDepartementLimitrophe</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartementLimitrophe stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumDepartementLimitrophe 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumDepartementLimitrophe() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitNumDepartementLimitrophe
					, fournirKeyValiderRGSectionHitNumDepartementLimitrophe()
					, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumDepartementLimitrophe().______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumDepartementLimitrophe 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numDepartementLimitrophe".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumDepartementLimitrophe() {
		return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE;
	} // Fin de fournirKeyValiderRGSectionHitNumDepartementLimitrophe().___



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumDepartementLimitrophe par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitNumDepartementLimitrophe 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumDepartementLimitrophe stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumDepartementLimitrophe : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumDepartementLimitrophe() 
													throws Exception {
		return fournirValiderRGSectionHitNumDepartementLimitrophe();
	} // Fin de getValiderRGSectionHitNumDepartementLimitrophe().__________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumDepartementLimitrophe par défaut 
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
	* valeur à passer à validerRGSectionHitNumDepartementLimitrophe.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumDepartementLimitrophe(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitNumDepartementLimitrophe
							, fournirKeyValiderRGSectionHitNumDepartementLimitrophe());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumDepartementLimitrophe(...)._______



	/**
	 * retourne le validerRGSectionHitNumDepartementLimitropheRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartementLimitropheRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumDepartementLimitropheRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumDepartementLimitropheRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumDepartementLimitropheRenseigne01
					, fournirKeyValiderRGSectionHitNumDepartementLimitropheRenseigne01()
					, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumDepartementLimitropheRenseigne01().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumDepartementLimitropheRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numDepartementLimitrophe.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumDepartementLimitropheRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitNumDepartementLimitropheRenseigne01().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumDepartementLimitropheRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartementLimitropheRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumDepartementLimitropheRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumDepartementLimitropheRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumDepartementLimitropheRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitNumDepartementLimitropheRenseigne01();
	} // Fin de getValiderRGSectionHitNumDepartementLimitropheRenseigne01().
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumDepartementLimitropheRenseigne01 
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
	* valeur à passer à validerRGSectionHitNumDepartementLimitropheRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumDepartementLimitropheRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumDepartementLimitropheRenseigne01
							, fournirKeyValiderRGSectionHitNumDepartementLimitropheRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumDepartementLimitropheRenseigne01(...).



	/**
	 * retourne le validerRGSectionHitNumDepartementLimitropheRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartementLimitropheRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumDepartementLimitropheRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumDepartementLimitropheRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumDepartementLimitropheRegex02
					, fournirKeyValiderRGSectionHitNumDepartementLimitropheRegex02()
					, STRING_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumDepartementLimitropheRegex02().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumDepartementLimitropheRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numDepartementLimitrophe.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumDepartementLimitropheRegex02() {
		return KEY_VALIDER_SECTIONHIT_NUMDEPARTEMENTLIMITROPHE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitNumDepartementLimitropheRegex02().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumDepartementLimitropheRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumDepartementLimitropheRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumDepartementLimitropheRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumDepartementLimitropheRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumDepartementLimitropheRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitNumDepartementLimitropheRegex02();
	} // Fin de getValiderRGSectionHitNumDepartementLimitropheRegex02().___
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumDepartementLimitropheRegex02 
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
	* valeur à passer à validerRGSectionHitNumDepartementLimitropheRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumDepartementLimitropheRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumDepartementLimitropheRegex02
							, fournirKeyValiderRGSectionHitNumDepartementLimitropheRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumDepartementLimitropheRegex02(...).
	
	
	
	/* 33 - numSectionLimitrophe. **************/
	/**
	 * retourne le <code>validerRGSectionHitNumSectionLimitrophe</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>numSectionLimitrophe</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>numSectionLimitrophe</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSectionLimitrophe stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumSectionLimitrophe 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumSectionLimitrophe() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitNumSectionLimitrophe
					, fournirKeyValiderRGSectionHitNumSectionLimitrophe()
					, STRING_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumSectionLimitrophe().__________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumSectionLimitrophe 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numSectionLimitrophe".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumSectionLimitrophe() {
		return KEY_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE;
	} // Fin de fournirKeyValiderRGSectionHitNumSectionLimitrophe()._______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumSectionLimitrophe par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitNumSectionLimitrophe 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumSectionLimitrophe stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumSectionLimitrophe : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumSectionLimitrophe() 
													throws Exception {
		return fournirValiderRGSectionHitNumSectionLimitrophe();
	} // Fin de getValiderRGSectionHitNumSectionLimitrophe().______________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumSectionLimitrophe par défaut 
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
	* valeur à passer à validerRGSectionHitNumSectionLimitrophe.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumSectionLimitrophe(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitNumSectionLimitrophe
							, fournirKeyValiderRGSectionHitNumSectionLimitrophe());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumSectionLimitrophe(...).___________



	/**
	 * retourne le validerRGSectionHitNumSectionLimitropheRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSectionLimitropheRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumSectionLimitropheRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumSectionLimitropheRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumSectionLimitropheRenseigne01
					, fournirKeyValiderRGSectionHitNumSectionLimitropheRenseigne01()
					, STRING_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumSectionLimitropheRenseigne01().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumSectionLimitropheRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numSectionLimitrophe.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumSectionLimitropheRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitNumSectionLimitropheRenseigne01().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumSectionLimitropheRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSectionLimitropheRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumSectionLimitropheRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumSectionLimitropheRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumSectionLimitropheRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitNumSectionLimitropheRenseigne01();
	} // Fin de getValiderRGSectionHitNumSectionLimitropheRenseigne01().___
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumSectionLimitropheRenseigne01 
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
	* valeur à passer à validerRGSectionHitNumSectionLimitropheRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumSectionLimitropheRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumSectionLimitropheRenseigne01
							, fournirKeyValiderRGSectionHitNumSectionLimitropheRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumSectionLimitropheRenseigne01(...).



	/**
	 * retourne le validerRGSectionHitNumSectionLimitropheRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSectionLimitropheRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitNumSectionLimitropheRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitNumSectionLimitropheRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitNumSectionLimitropheRegex02
					, fournirKeyValiderRGSectionHitNumSectionLimitropheRegex02()
					, STRING_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitNumSectionLimitropheRegex02().___
	

	
	/**
	 * Getter de la clé du validerRGSectionHitNumSectionLimitropheRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.numSectionLimitrophe.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitNumSectionLimitropheRegex02() {
		return KEY_VALIDER_SECTIONHIT_NUMSECTIONLIMITROPHE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitNumSectionLimitropheRegex02().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitNumSectionLimitropheRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitNumSectionLimitropheRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitNumSectionLimitropheRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitNumSectionLimitropheRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitNumSectionLimitropheRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitNumSectionLimitropheRegex02();
	} // Fin de getValiderRGSectionHitNumSectionLimitropheRegex02()._______
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitNumSectionLimitropheRegex02 
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
	* valeur à passer à validerRGSectionHitNumSectionLimitropheRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitNumSectionLimitropheRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitNumSectionLimitropheRegex02
							, fournirKeyValiderRGSectionHitNumSectionLimitropheRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitNumSectionLimitropheRegex02(...).____
	
	
	
	/* 34 - sensLimitrophe. **************/
	/**
	 * retourne le <code>validerRGSectionHitSensLimitrophe</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>sensLimitrophe</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>sensLimitrophe</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitSensLimitrophe stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitSensLimitrophe 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitSensLimitrophe() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitSensLimitrophe
					, fournirKeyValiderRGSectionHitSensLimitrophe()
					, STRING_VALIDER_SECTIONHIT_SENSLIMITROPHE_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitSensLimitrophe().________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitSensLimitrophe 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.sensLimitrophe".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_SENSLIMITROPHE : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitSensLimitrophe() {
		return KEY_VALIDER_SECTIONHIT_SENSLIMITROPHE;
	} // Fin de fournirKeyValiderRGSectionHitSensLimitrophe()._____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitSensLimitrophe par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitSensLimitrophe 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitSensLimitrophe stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitSensLimitrophe : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitSensLimitrophe() 
													throws Exception {
		return fournirValiderRGSectionHitSensLimitrophe();
	} // Fin de getValiderRGSectionHitSensLimitrophe().____________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitSensLimitrophe par défaut 
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
	* valeur à passer à validerRGSectionHitSensLimitrophe.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitSensLimitrophe(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitSensLimitrophe
							, fournirKeyValiderRGSectionHitSensLimitrophe());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitSensLimitrophe(...)._________________



	/**
	 * retourne le validerRGSectionHitSensLimitropheRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitSensLimitropheRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitSensLimitropheRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitSensLimitropheRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitSensLimitropheRenseigne01
					, fournirKeyValiderRGSectionHitSensLimitropheRenseigne01()
					, STRING_VALIDER_SECTIONHIT_SENSLIMITROPHE_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitSensLimitropheRenseigne01()._____
	

	
	/**
	 * Getter de la clé du validerRGSectionHitSensLimitropheRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.sensLimitrophe.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_SENSLIMITROPHE_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitSensLimitropheRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_SENSLIMITROPHE_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitSensLimitropheRenseigne01().__



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitSensLimitropheRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitSensLimitropheRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitSensLimitropheRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitSensLimitropheRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitSensLimitropheRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitSensLimitropheRenseigne01();
	} // Fin de getValiderRGSectionHitSensLimitropheRenseigne01()._________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitSensLimitropheRenseigne01 
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
	* valeur à passer à validerRGSectionHitSensLimitropheRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitSensLimitropheRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitSensLimitropheRenseigne01
							, fournirKeyValiderRGSectionHitSensLimitropheRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitSensLimitropheRenseigne01(...).______



	/**
	 * retourne le validerRGSectionHitSensLimitropheRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitSensLimitropheRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitSensLimitropheRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitSensLimitropheRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitSensLimitropheRegex02
					, fournirKeyValiderRGSectionHitSensLimitropheRegex02()
					, STRING_VALIDER_SECTIONHIT_SENSLIMITROPHE_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitSensLimitropheRegex02()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitSensLimitropheRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.sensLimitrophe.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_SENSLIMITROPHE_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitSensLimitropheRegex02() {
		return KEY_VALIDER_SECTIONHIT_SENSLIMITROPHE_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitSensLimitropheRegex02().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitSensLimitropheRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitSensLimitropheRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitSensLimitropheRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitSensLimitropheRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitSensLimitropheRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitSensLimitropheRegex02();
	} // Fin de getValiderRGSectionHitSensLimitropheRegex02()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitSensLimitropheRegex02 
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
	* valeur à passer à validerRGSectionHitSensLimitropheRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitSensLimitropheRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitSensLimitropheRegex02
							, fournirKeyValiderRGSectionHitSensLimitropheRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitSensLimitropheRegex02(...).__________



	/**
	 * retourne le validerRGSectionHitSensLimitropheNomenclature03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitSensLimitropheNomenclature03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitSensLimitropheNomenclature03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitSensLimitropheNomenclature03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitSensLimitropheNomenclature03
					, fournirKeyValiderRGSectionHitSensLimitropheNomenclature03()
					, STRING_VALIDER_SECTIONHIT_SENSLIMITROPHE_NOMENCLATURE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitSensLimitropheNomenclature03().__
	

	
	/**
	 * Getter de la clé du validerRGSectionHitSensLimitropheNomenclature03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.sensLimitrophe.nomenclature".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_SENSLIMITROPHE_NOMENCLATURE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitSensLimitropheNomenclature03() {
		return KEY_VALIDER_SECTIONHIT_SENSLIMITROPHE_NOMENCLATURE_03;
	} // Fin de fournirKeyValiderRGSectionHitSensLimitropheNomenclature03().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitSensLimitropheNomenclature03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitSensLimitropheNomenclature03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitSensLimitropheNomenclature03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitSensLimitropheNomenclature03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitSensLimitropheNomenclature03() 
													throws Exception {
		return fournirValiderRGSectionHitSensLimitropheNomenclature03();
	} // Fin de getValiderRGSectionHitSensLimitropheNomenclature03().______
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitSensLimitropheNomenclature03 
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
	* valeur à passer à validerRGSectionHitSensLimitropheNomenclature03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitSensLimitropheNomenclature03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitSensLimitropheNomenclature03
							, fournirKeyValiderRGSectionHitSensLimitropheNomenclature03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitSensLimitropheNomenclature03(...).___
	
	
	
	/* 35 - moisSectionnement. **************/
	/**
	 * retourne le <code>validerRGSectionHitMoisSectionnement</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>moisSectionnement</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>moisSectionnement</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMoisSectionnement stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMoisSectionnement 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMoisSectionnement() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitMoisSectionnement
					, fournirKeyValiderRGSectionHitMoisSectionnement()
					, STRING_VALIDER_SECTIONHIT_MOISSECTIONNEMENT_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMoisSectionnement()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMoisSectionnement 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.moisSectionnement".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MOISSECTIONNEMENT : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMoisSectionnement() {
		return KEY_VALIDER_SECTIONHIT_MOISSECTIONNEMENT;
	} // Fin de fournirKeyValiderRGSectionHitMoisSectionnement().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMoisSectionnement par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitMoisSectionnement 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMoisSectionnement stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMoisSectionnement : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMoisSectionnement() 
													throws Exception {
		return fournirValiderRGSectionHitMoisSectionnement();
	} // Fin de getValiderRGSectionHitMoisSectionnement()._________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMoisSectionnement par défaut 
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
	* valeur à passer à validerRGSectionHitMoisSectionnement.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMoisSectionnement(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitMoisSectionnement
							, fournirKeyValiderRGSectionHitMoisSectionnement());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMoisSectionnement(...).______________



	/**
	 * retourne le validerRGSectionHitMoisSectionnementRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMoisSectionnementRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMoisSectionnementRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMoisSectionnementRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitMoisSectionnementRenseigne01
					, fournirKeyValiderRGSectionHitMoisSectionnementRenseigne01()
					, STRING_VALIDER_SECTIONHIT_MOISSECTIONNEMENT_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMoisSectionnementRenseigne01().__
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMoisSectionnementRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.moisSectionnement.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MOISSECTIONNEMENT_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMoisSectionnementRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_MOISSECTIONNEMENT_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitMoisSectionnementRenseigne01().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMoisSectionnementRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitMoisSectionnementRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMoisSectionnementRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMoisSectionnementRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMoisSectionnementRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitMoisSectionnementRenseigne01();
	} // Fin de getValiderRGSectionHitMoisSectionnementRenseigne01().______
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMoisSectionnementRenseigne01 
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
	* valeur à passer à validerRGSectionHitMoisSectionnementRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMoisSectionnementRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitMoisSectionnementRenseigne01
							, fournirKeyValiderRGSectionHitMoisSectionnementRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMoisSectionnementRenseigne01(...).___



	/**
	 * retourne le validerRGSectionHitMoisSectionnementRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMoisSectionnementRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMoisSectionnementRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMoisSectionnementRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitMoisSectionnementRegex02
					, fournirKeyValiderRGSectionHitMoisSectionnementRegex02()
					, STRING_VALIDER_SECTIONHIT_MOISSECTIONNEMENT_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMoisSectionnementRegex02().______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMoisSectionnementRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.moisSectionnement.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MOISSECTIONNEMENT_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMoisSectionnementRegex02() {
		return KEY_VALIDER_SECTIONHIT_MOISSECTIONNEMENT_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitMoisSectionnementRegex02().___



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMoisSectionnementRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitMoisSectionnementRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMoisSectionnementRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMoisSectionnementRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMoisSectionnementRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitMoisSectionnementRegex02();
	} // Fin de getValiderRGSectionHitMoisSectionnementRegex02().__________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMoisSectionnementRegex02 
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
	* valeur à passer à validerRGSectionHitMoisSectionnementRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMoisSectionnementRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitMoisSectionnementRegex02
							, fournirKeyValiderRGSectionHitMoisSectionnementRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMoisSectionnementRegex02(...)._______
	
	
	
	/* 36 - anneeSectionnement. **************/
	/**
	 * retourne le <code>validerRGSectionHitAnneeSectionnement</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>anneeSectionnement</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>anneeSectionnement</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAnneeSectionnement stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAnneeSectionnement 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAnneeSectionnement() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitAnneeSectionnement
					, fournirKeyValiderRGSectionHitAnneeSectionnement()
					, STRING_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAnneeSectionnement().____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAnneeSectionnement 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.anneeSectionnement".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAnneeSectionnement() {
		return KEY_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT;
	} // Fin de fournirKeyValiderRGSectionHitAnneeSectionnement()._________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAnneeSectionnement par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitAnneeSectionnement 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAnneeSectionnement stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAnneeSectionnement : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAnneeSectionnement() 
													throws Exception {
		return fournirValiderRGSectionHitAnneeSectionnement();
	} // Fin de getValiderRGSectionHitAnneeSectionnement().________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAnneeSectionnement par défaut 
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
	* valeur à passer à validerRGSectionHitAnneeSectionnement.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAnneeSectionnement(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitAnneeSectionnement
							, fournirKeyValiderRGSectionHitAnneeSectionnement());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAnneeSectionnement(...)._____________



	/**
	 * retourne le validerRGSectionHitAnneeSectionnementRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAnneeSectionnementRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAnneeSectionnementRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAnneeSectionnementRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitAnneeSectionnementRenseigne01
					, fournirKeyValiderRGSectionHitAnneeSectionnementRenseigne01()
					, STRING_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAnneeSectionnementRenseigne01()._
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAnneeSectionnementRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.anneeSectionnement.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAnneeSectionnementRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitAnneeSectionnementRenseigne01().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAnneeSectionnementRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitAnneeSectionnementRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAnneeSectionnementRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAnneeSectionnementRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAnneeSectionnementRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitAnneeSectionnementRenseigne01();
	} // Fin de getValiderRGSectionHitAnneeSectionnementRenseigne01()._____
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAnneeSectionnementRenseigne01 
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
	* valeur à passer à validerRGSectionHitAnneeSectionnementRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAnneeSectionnementRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitAnneeSectionnementRenseigne01
							, fournirKeyValiderRGSectionHitAnneeSectionnementRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAnneeSectionnementRenseigne01(...).__



	/**
	 * retourne le validerRGSectionHitAnneeSectionnementRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitAnneeSectionnementRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitAnneeSectionnementRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitAnneeSectionnementRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitAnneeSectionnementRegex02
					, fournirKeyValiderRGSectionHitAnneeSectionnementRegex02()
					, STRING_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitAnneeSectionnementRegex02()._____
	

	
	/**
	 * Getter de la clé du validerRGSectionHitAnneeSectionnementRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.anneeSectionnement.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitAnneeSectionnementRegex02() {
		return KEY_VALIDER_SECTIONHIT_ANNEESECTIONNEMENT_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitAnneeSectionnementRegex02().__



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitAnneeSectionnementRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitAnneeSectionnementRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitAnneeSectionnementRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitAnneeSectionnementRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitAnneeSectionnementRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitAnneeSectionnementRegex02();
	} // Fin de getValiderRGSectionHitAnneeSectionnementRegex02()._________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitAnneeSectionnementRegex02 
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
	* valeur à passer à validerRGSectionHitAnneeSectionnementRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitAnneeSectionnementRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitAnneeSectionnementRegex02
							, fournirKeyValiderRGSectionHitAnneeSectionnementRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitAnneeSectionnementRegex02(...).______
	
	
	
	/* 37 - zoneLibre2. **************/
	/**
	 * retourne le <code>validerRGSectionHitZoneLibre2</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>zoneLibre2</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>zoneLibre2</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitZoneLibre2 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitZoneLibre2 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitZoneLibre2() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitZoneLibre2
					, fournirKeyValiderRGSectionHitZoneLibre2()
					, STRING_VALIDER_SECTIONHIT_ZONELIBRE2_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitZoneLibre2().________________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitZoneLibre2 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.zoneLibre2".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ZONELIBRE2 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitZoneLibre2() {
		return KEY_VALIDER_SECTIONHIT_ZONELIBRE2;
	} // Fin de fournirKeyValiderRGSectionHitZoneLibre2()._____________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitZoneLibre2 par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitZoneLibre2 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitZoneLibre2 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitZoneLibre2 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitZoneLibre2() 
													throws Exception {
		return fournirValiderRGSectionHitZoneLibre2();
	} // Fin de getValiderRGSectionHitZoneLibre2().____________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitZoneLibre2 par défaut 
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
	* valeur à passer à validerRGSectionHitZoneLibre2.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitZoneLibre2(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitZoneLibre2
							, fournirKeyValiderRGSectionHitZoneLibre2());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitZoneLibre2(...)._________________________



	/**
	 * retourne le validerRGSectionHitZoneLibre2Renseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitZoneLibre2Renseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitZoneLibre2Renseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitZoneLibre2Renseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitZoneLibre2Renseigne01
					, fournirKeyValiderRGSectionHitZoneLibre2Renseigne01()
					, STRING_VALIDER_SECTIONHIT_ZONELIBRE2_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitZoneLibre2Renseigne01()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitZoneLibre2Renseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.zoneLibre2.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ZONELIBRE2_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitZoneLibre2Renseigne01() {
		return KEY_VALIDER_SECTIONHIT_ZONELIBRE2_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitZoneLibre2Renseigne01().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitZoneLibre2Renseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitZoneLibre2Renseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitZoneLibre2Renseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitZoneLibre2Renseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitZoneLibre2Renseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitZoneLibre2Renseigne01();
	} // Fin de getValiderRGSectionHitZoneLibre2Renseigne01()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitZoneLibre2Renseigne01 
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
	* valeur à passer à validerRGSectionHitZoneLibre2Renseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitZoneLibre2Renseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitZoneLibre2Renseigne01
							, fournirKeyValiderRGSectionHitZoneLibre2Renseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitZoneLibre2Renseigne01(...).__________



	/**
	 * retourne le validerRGSectionHitZoneLibre2Regex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitZoneLibre2Regex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitZoneLibre2Regex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitZoneLibre2Regex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitZoneLibre2Regex02
					, fournirKeyValiderRGSectionHitZoneLibre2Regex02()
					, STRING_VALIDER_SECTIONHIT_ZONELIBRE2_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitZoneLibre2Regex02()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitZoneLibre2Regex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.zoneLibre2.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_ZONELIBRE2_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitZoneLibre2Regex02() {
		return KEY_VALIDER_SECTIONHIT_ZONELIBRE2_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitZoneLibre2Regex02().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitZoneLibre2Regex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitZoneLibre2Regex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitZoneLibre2Regex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitZoneLibre2Regex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitZoneLibre2Regex02() 
													throws Exception {
		return fournirValiderRGSectionHitZoneLibre2Regex02();
	} // Fin de getValiderRGSectionHitZoneLibre2Regex02()._________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitZoneLibre2Regex02 
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
	* valeur à passer à validerRGSectionHitZoneLibre2Regex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitZoneLibre2Regex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitZoneLibre2Regex02
							, fournirKeyValiderRGSectionHitZoneLibre2Regex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitZoneLibre2Regex02(...).______________
	
	
	
	/* 38 - mjaN. **************/
	/**
	 * retourne le <code>validerRGSectionHitMjaN</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>mjaN</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>mjaN</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMjaN stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMjaN 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMjaN() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitMjaN
					, fournirKeyValiderRGSectionHitMjaN()
					, STRING_VALIDER_SECTIONHIT_MJAN_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMjaN().__________________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMjaN 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.mjaN".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MJAN : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMjaN() {
		return KEY_VALIDER_SECTIONHIT_MJAN;
	} // Fin de fournirKeyValiderRGSectionHitMjaN()._______________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMjaN par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitMjaN 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMjaN stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMjaN : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMjaN() 
													throws Exception {
		return fournirValiderRGSectionHitMjaN();
	} // Fin de getValiderRGSectionHitMjaN().______________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMjaN par défaut 
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
	* valeur à passer à validerRGSectionHitMjaN.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMjaN(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitMjaN
							, fournirKeyValiderRGSectionHitMjaN());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMjaN(...).___________________________



	/**
	 * retourne le validerRGSectionHitMjaNRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMjaNRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMjaNRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMjaNRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitMjaNRenseigne01
					, fournirKeyValiderRGSectionHitMjaNRenseigne01()
					, STRING_VALIDER_SECTIONHIT_MJAN_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMjaNRenseigne01()._______________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMjaNRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.mjaN.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MJAN_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMjaNRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_MJAN_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitMjaNRenseigne01().____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMjaNRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitMjaNRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMjaNRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMjaNRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMjaNRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitMjaNRenseigne01();
	} // Fin de getValiderRGSectionHitMjaNRenseigne01().___________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMjaNRenseigne01 
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
	* valeur à passer à validerRGSectionHitMjaNRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMjaNRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitMjaNRenseigne01
							, fournirKeyValiderRGSectionHitMjaNRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMjaNRenseigne01(...).________________



	/**
	 * retourne le validerRGSectionHitMjaNRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMjaNRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMjaNRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMjaNRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitMjaNRegex02
					, fournirKeyValiderRGSectionHitMjaNRegex02()
					, STRING_VALIDER_SECTIONHIT_MJAN_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMjaNRegex02().___________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMjaNRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.mjaN.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MJAN_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMjaNRegex02() {
		return KEY_VALIDER_SECTIONHIT_MJAN_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitMjaNRegex02().________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMjaNRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitMjaNRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMjaNRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMjaNRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMjaNRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitMjaNRegex02();
	} // Fin de getValiderRGSectionHitMjaNRegex02()._______________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMjaNRegex02 
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
	* valeur à passer à validerRGSectionHitMjaNRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMjaNRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitMjaNRegex02
							, fournirKeyValiderRGSectionHitMjaNRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMjaNRegex02(...).____________________



	/**
	 * retourne le validerRGSectionHitMjaNNumerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMjaNNumerique03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMjaNNumerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMjaNNumerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitMjaNNumerique03
					, fournirKeyValiderRGSectionHitMjaNNumerique03()
					, STRING_VALIDER_SECTIONHIT_MJAN_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMjaNNumerique03()._______________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMjaNNumerique03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.mjaN.numerique".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MJAN_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMjaNNumerique03() {
		return KEY_VALIDER_SECTIONHIT_MJAN_NUMERIQUE_03;
	} // Fin de fournirKeyValiderRGSectionHitMjaNNumerique03().____________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMjaNNumerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitMjaNNumerique03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMjaNNumerique03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMjaNNumerique03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMjaNNumerique03() 
													throws Exception {
		return fournirValiderRGSectionHitMjaNNumerique03();
	} // Fin de getValiderRGSectionHitMjaNNumerique03().___________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMjaNNumerique03 
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
	* valeur à passer à validerRGSectionHitMjaNNumerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMjaNNumerique03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitMjaNNumerique03
							, fournirKeyValiderRGSectionHitMjaNNumerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMjaNNumerique03(...).________________
	
	
	
	/* 39 - modeCalculN. **************/
	/**
	 * retourne le <code>validerRGSectionHitModeCalculN</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>modeCalculN</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>modeCalculN</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitModeCalculN stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitModeCalculN 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitModeCalculN() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitModeCalculN
					, fournirKeyValiderRGSectionHitModeCalculN()
					, STRING_VALIDER_SECTIONHIT_MODECALCULN_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitModeCalculN().___________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitModeCalculN 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.modeCalculN".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MODECALCULN : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitModeCalculN() {
		return KEY_VALIDER_SECTIONHIT_MODECALCULN;
	} // Fin de fournirKeyValiderRGSectionHitModeCalculN().________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitModeCalculN par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitModeCalculN 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitModeCalculN stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitModeCalculN : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitModeCalculN() 
													throws Exception {
		return fournirValiderRGSectionHitModeCalculN();
	} // Fin de getValiderRGSectionHitModeCalculN()._______________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitModeCalculN par défaut 
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
	* valeur à passer à validerRGSectionHitModeCalculN.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitModeCalculN(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitModeCalculN
							, fournirKeyValiderRGSectionHitModeCalculN());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitModeCalculN(...).____________________



	/**
	 * retourne le validerRGSectionHitModeCalculNRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitModeCalculNRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitModeCalculNRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitModeCalculNRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitModeCalculNRenseigne01
					, fournirKeyValiderRGSectionHitModeCalculNRenseigne01()
					, STRING_VALIDER_SECTIONHIT_MODECALCULN_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitModeCalculNRenseigne01().________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitModeCalculNRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.modeCalculN.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MODECALCULN_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitModeCalculNRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_MODECALCULN_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitModeCalculNRenseigne01()._____



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitModeCalculNRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitModeCalculNRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitModeCalculNRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitModeCalculNRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitModeCalculNRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitModeCalculNRenseigne01();
	} // Fin de getValiderRGSectionHitModeCalculNRenseigne01().____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitModeCalculNRenseigne01 
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
	* valeur à passer à validerRGSectionHitModeCalculNRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitModeCalculNRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitModeCalculNRenseigne01
							, fournirKeyValiderRGSectionHitModeCalculNRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitModeCalculNRenseigne01(...)._________



	/**
	 * retourne le validerRGSectionHitModeCalculNRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitModeCalculNRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitModeCalculNRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitModeCalculNRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitModeCalculNRegex02
					, fournirKeyValiderRGSectionHitModeCalculNRegex02()
					, STRING_VALIDER_SECTIONHIT_MODECALCULN_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitModeCalculNRegex02().____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitModeCalculNRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.modeCalculN.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MODECALCULN_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitModeCalculNRegex02() {
		return KEY_VALIDER_SECTIONHIT_MODECALCULN_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitModeCalculNRegex02()._________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitModeCalculNRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitModeCalculNRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitModeCalculNRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitModeCalculNRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitModeCalculNRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitModeCalculNRegex02();
	} // Fin de getValiderRGSectionHitModeCalculNRegex02().________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitModeCalculNRegex02 
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
	* valeur à passer à validerRGSectionHitModeCalculNRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitModeCalculNRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitModeCalculNRegex02
							, fournirKeyValiderRGSectionHitModeCalculNRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitModeCalculNRegex02(...)._____________
	
	
	
	/* 40 - pcPLN. **************/
	/**
	 * retourne le <code>validerRGSectionHitPcPLN</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>pcPLN</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>pcPLN</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcPLN stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcPLN 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcPLN() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitPcPLN
					, fournirKeyValiderRGSectionHitPcPLN()
					, STRING_VALIDER_SECTIONHIT_PCPLN_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcPLN()._________________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcPLN 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcPLN".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCPLN : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcPLN() {
		return KEY_VALIDER_SECTIONHIT_PCPLN;
	} // Fin de fournirKeyValiderRGSectionHitPcPLN().______________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcPLN par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitPcPLN 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcPLN stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcPLN : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcPLN() 
													throws Exception {
		return fournirValiderRGSectionHitPcPLN();
	} // Fin de getValiderRGSectionHitPcPLN()._____________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcPLN par défaut 
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
	* valeur à passer à validerRGSectionHitPcPLN.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcPLN(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitPcPLN
							, fournirKeyValiderRGSectionHitPcPLN());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcPLN(...).__________________________



	/**
	 * retourne le validerRGSectionHitPcPLNRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcPLNRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcPLNRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcPLNRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPcPLNRenseigne01
					, fournirKeyValiderRGSectionHitPcPLNRenseigne01()
					, STRING_VALIDER_SECTIONHIT_PCPLN_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcPLNRenseigne01().______________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcPLNRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcPLN.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCPLN_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcPLNRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_PCPLN_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitPcPLNRenseigne01().___________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcPLNRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPcPLNRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcPLNRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcPLNRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcPLNRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitPcPLNRenseigne01();
	} // Fin de getValiderRGSectionHitPcPLNRenseigne01().__________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcPLNRenseigne01 
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
	* valeur à passer à validerRGSectionHitPcPLNRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcPLNRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPcPLNRenseigne01
							, fournirKeyValiderRGSectionHitPcPLNRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcPLNRenseigne01(...)._______________



	/**
	 * retourne le validerRGSectionHitPcPLNRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcPLNRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcPLNRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcPLNRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPcPLNRegex02
					, fournirKeyValiderRGSectionHitPcPLNRegex02()
					, STRING_VALIDER_SECTIONHIT_PCPLN_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcPLNRegex02().__________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcPLNRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcPLN.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCPLN_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcPLNRegex02() {
		return KEY_VALIDER_SECTIONHIT_PCPLN_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitPcPLNRegex02()._______________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcPLNRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPcPLNRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcPLNRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcPLNRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcPLNRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitPcPLNRegex02();
	} // Fin de getValiderRGSectionHitPcPLNRegex02().______________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcPLNRegex02 
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
	* valeur à passer à validerRGSectionHitPcPLNRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcPLNRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPcPLNRegex02
							, fournirKeyValiderRGSectionHitPcPLNRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcPLNRegex02(...).___________________



	/**
	 * retourne le validerRGSectionHitPcPLNNumerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcPLNNumerique03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcPLNNumerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcPLNNumerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPcPLNNumerique03
					, fournirKeyValiderRGSectionHitPcPLNNumerique03()
					, STRING_VALIDER_SECTIONHIT_PCPLN_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcPLNNumerique03().______________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcPLNNumerique03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcPLN.numerique".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCPLN_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcPLNNumerique03() {
		return KEY_VALIDER_SECTIONHIT_PCPLN_NUMERIQUE_03;
	} // Fin de fournirKeyValiderRGSectionHitPcPLNNumerique03().___________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcPLNNumerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPcPLNNumerique03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcPLNNumerique03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcPLNNumerique03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcPLNNumerique03() 
													throws Exception {
		return fournirValiderRGSectionHitPcPLNNumerique03();
	} // Fin de getValiderRGSectionHitPcPLNNumerique03().__________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcPLNNumerique03 
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
	* valeur à passer à validerRGSectionHitPcPLNNumerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcPLNNumerique03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPcPLNNumerique03
							, fournirKeyValiderRGSectionHitPcPLNNumerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcPLNNumerique03(...)._______________
	
	
	
	/* 41 - evaluationPLN. **************/
	/**
	 * retourne le <code>validerRGSectionHitEvaluationPLN</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>evaluationPLN</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>evaluationPLN</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitEvaluationPLN stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitEvaluationPLN 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitEvaluationPLN() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitEvaluationPLN
					, fournirKeyValiderRGSectionHitEvaluationPLN()
					, STRING_VALIDER_SECTIONHIT_EVALUATIONPLN_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitEvaluationPLN()._________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitEvaluationPLN 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.evaluationPLN".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_EVALUATIONPLN : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitEvaluationPLN() {
		return KEY_VALIDER_SECTIONHIT_EVALUATIONPLN;
	} // Fin de fournirKeyValiderRGSectionHitEvaluationPLN().______________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitEvaluationPLN par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitEvaluationPLN 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitEvaluationPLN stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitEvaluationPLN : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitEvaluationPLN() 
													throws Exception {
		return fournirValiderRGSectionHitEvaluationPLN();
	} // Fin de getValiderRGSectionHitEvaluationPLN()._____________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitEvaluationPLN par défaut 
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
	* valeur à passer à validerRGSectionHitEvaluationPLN.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitEvaluationPLN(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitEvaluationPLN
							, fournirKeyValiderRGSectionHitEvaluationPLN());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitEvaluationPLN(...).__________________



	/**
	 * retourne le validerRGSectionHitEvaluationPLNRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitEvaluationPLNRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitEvaluationPLNRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitEvaluationPLNRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitEvaluationPLNRenseigne01
					, fournirKeyValiderRGSectionHitEvaluationPLNRenseigne01()
					, STRING_VALIDER_SECTIONHIT_EVALUATIONPLN_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitEvaluationPLNRenseigne01().______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitEvaluationPLNRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.evaluationPLN.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_EVALUATIONPLN_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitEvaluationPLNRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_EVALUATIONPLN_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitEvaluationPLNRenseigne01().___



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitEvaluationPLNRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitEvaluationPLNRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitEvaluationPLNRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitEvaluationPLNRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitEvaluationPLNRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitEvaluationPLNRenseigne01();
	} // Fin de getValiderRGSectionHitEvaluationPLNRenseigne01().__________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitEvaluationPLNRenseigne01 
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
	* valeur à passer à validerRGSectionHitEvaluationPLNRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitEvaluationPLNRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitEvaluationPLNRenseigne01
							, fournirKeyValiderRGSectionHitEvaluationPLNRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitEvaluationPLNRenseigne01(...)._______



	/**
	 * retourne le validerRGSectionHitEvaluationPLNRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitEvaluationPLNRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitEvaluationPLNRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitEvaluationPLNRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitEvaluationPLNRegex02
					, fournirKeyValiderRGSectionHitEvaluationPLNRegex02()
					, STRING_VALIDER_SECTIONHIT_EVALUATIONPLN_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitEvaluationPLNRegex02().__________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitEvaluationPLNRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.evaluationPLN.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_EVALUATIONPLN_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitEvaluationPLNRegex02() {
		return KEY_VALIDER_SECTIONHIT_EVALUATIONPLN_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitEvaluationPLNRegex02()._______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitEvaluationPLNRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitEvaluationPLNRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitEvaluationPLNRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitEvaluationPLNRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitEvaluationPLNRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitEvaluationPLNRegex02();
	} // Fin de getValiderRGSectionHitEvaluationPLNRegex02().______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitEvaluationPLNRegex02 
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
	* valeur à passer à validerRGSectionHitEvaluationPLNRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitEvaluationPLNRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitEvaluationPLNRegex02
							, fournirKeyValiderRGSectionHitEvaluationPLNRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitEvaluationPLNRegex02(...).___________
	
	
	
	/* 42 - pcNuitAnnuelN. **************/
	/**
	 * retourne le <code>validerRGSectionHitPcNuitAnnuelN</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>pcNuitAnnuelN</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>pcNuitAnnuelN</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitAnnuelN stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcNuitAnnuelN 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcNuitAnnuelN() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitPcNuitAnnuelN
					, fournirKeyValiderRGSectionHitPcNuitAnnuelN()
					, STRING_VALIDER_SECTIONHIT_PCNUITANNUELN_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcNuitAnnuelN()._________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcNuitAnnuelN 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcNuitAnnuelN".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCNUITANNUELN : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcNuitAnnuelN() {
		return KEY_VALIDER_SECTIONHIT_PCNUITANNUELN;
	} // Fin de fournirKeyValiderRGSectionHitPcNuitAnnuelN().______________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcNuitAnnuelN par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitPcNuitAnnuelN 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcNuitAnnuelN stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcNuitAnnuelN : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcNuitAnnuelN() 
													throws Exception {
		return fournirValiderRGSectionHitPcNuitAnnuelN();
	} // Fin de getValiderRGSectionHitPcNuitAnnuelN()._____________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcNuitAnnuelN par défaut 
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
	* valeur à passer à validerRGSectionHitPcNuitAnnuelN.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcNuitAnnuelN(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitPcNuitAnnuelN
							, fournirKeyValiderRGSectionHitPcNuitAnnuelN());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcNuitAnnuelN(...).__________________



	/**
	 * retourne le validerRGSectionHitPcNuitAnnuelNRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitAnnuelNRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcNuitAnnuelNRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcNuitAnnuelNRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPcNuitAnnuelNRenseigne01
					, fournirKeyValiderRGSectionHitPcNuitAnnuelNRenseigne01()
					, STRING_VALIDER_SECTIONHIT_PCNUITANNUELN_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcNuitAnnuelNRenseigne01().______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcNuitAnnuelNRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcNuitAnnuelN.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCNUITANNUELN_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcNuitAnnuelNRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_PCNUITANNUELN_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitPcNuitAnnuelNRenseigne01().___



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcNuitAnnuelNRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitAnnuelNRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcNuitAnnuelNRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcNuitAnnuelNRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcNuitAnnuelNRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitPcNuitAnnuelNRenseigne01();
	} // Fin de getValiderRGSectionHitPcNuitAnnuelNRenseigne01().__________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcNuitAnnuelNRenseigne01 
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
	* valeur à passer à validerRGSectionHitPcNuitAnnuelNRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcNuitAnnuelNRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPcNuitAnnuelNRenseigne01
							, fournirKeyValiderRGSectionHitPcNuitAnnuelNRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcNuitAnnuelNRenseigne01(...)._______



	/**
	 * retourne le validerRGSectionHitPcNuitAnnuelNRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitAnnuelNRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcNuitAnnuelNRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcNuitAnnuelNRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPcNuitAnnuelNRegex02
					, fournirKeyValiderRGSectionHitPcNuitAnnuelNRegex02()
					, STRING_VALIDER_SECTIONHIT_PCNUITANNUELN_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcNuitAnnuelNRegex02().__________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcNuitAnnuelNRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcNuitAnnuelN.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCNUITANNUELN_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcNuitAnnuelNRegex02() {
		return KEY_VALIDER_SECTIONHIT_PCNUITANNUELN_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitPcNuitAnnuelNRegex02()._______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcNuitAnnuelNRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitAnnuelNRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcNuitAnnuelNRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcNuitAnnuelNRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcNuitAnnuelNRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitPcNuitAnnuelNRegex02();
	} // Fin de getValiderRGSectionHitPcNuitAnnuelNRegex02().______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcNuitAnnuelNRegex02 
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
	* valeur à passer à validerRGSectionHitPcNuitAnnuelNRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcNuitAnnuelNRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPcNuitAnnuelNRegex02
							, fournirKeyValiderRGSectionHitPcNuitAnnuelNRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcNuitAnnuelNRegex02(...).___________



	/**
	 * retourne le validerRGSectionHitPcNuitAnnuelNNumerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitAnnuelNNumerique03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcNuitAnnuelNNumerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcNuitAnnuelNNumerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPcNuitAnnuelNNumerique03
					, fournirKeyValiderRGSectionHitPcNuitAnnuelNNumerique03()
					, STRING_VALIDER_SECTIONHIT_PCNUITANNUELN_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcNuitAnnuelNNumerique03().______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcNuitAnnuelNNumerique03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcNuitAnnuelN.numerique".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCNUITANNUELN_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcNuitAnnuelNNumerique03() {
		return KEY_VALIDER_SECTIONHIT_PCNUITANNUELN_NUMERIQUE_03;
	} // Fin de fournirKeyValiderRGSectionHitPcNuitAnnuelNNumerique03().___



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcNuitAnnuelNNumerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitAnnuelNNumerique03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcNuitAnnuelNNumerique03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcNuitAnnuelNNumerique03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcNuitAnnuelNNumerique03() 
													throws Exception {
		return fournirValiderRGSectionHitPcNuitAnnuelNNumerique03();
	} // Fin de getValiderRGSectionHitPcNuitAnnuelNNumerique03().__________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcNuitAnnuelNNumerique03 
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
	* valeur à passer à validerRGSectionHitPcNuitAnnuelNNumerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcNuitAnnuelNNumerique03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPcNuitAnnuelNNumerique03
							, fournirKeyValiderRGSectionHitPcNuitAnnuelNNumerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcNuitAnnuelNNumerique03(...)._______
	
	
	
	/* 43 - indiceFiabiliteMjaN. **************/
	/**
	 * retourne le <code>validerRGSectionHitIndiceFiabiliteMjaN</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>indiceFiabiliteMjaN</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>indiceFiabiliteMjaN</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceFiabiliteMjaN stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitIndiceFiabiliteMjaN 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitIndiceFiabiliteMjaN() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitIndiceFiabiliteMjaN
					, fournirKeyValiderRGSectionHitIndiceFiabiliteMjaN()
					, STRING_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitIndiceFiabiliteMjaN().___________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitIndiceFiabiliteMjaN 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.indiceFiabiliteMjaN".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitIndiceFiabiliteMjaN() {
		return KEY_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN;
	} // Fin de fournirKeyValiderRGSectionHitIndiceFiabiliteMjaN().________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitIndiceFiabiliteMjaN par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitIndiceFiabiliteMjaN 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitIndiceFiabiliteMjaN stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitIndiceFiabiliteMjaN : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitIndiceFiabiliteMjaN() 
													throws Exception {
		return fournirValiderRGSectionHitIndiceFiabiliteMjaN();
	} // Fin de getValiderRGSectionHitIndiceFiabiliteMjaN()._______________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitIndiceFiabiliteMjaN par défaut 
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
	* valeur à passer à validerRGSectionHitIndiceFiabiliteMjaN.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitIndiceFiabiliteMjaN(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitIndiceFiabiliteMjaN
							, fournirKeyValiderRGSectionHitIndiceFiabiliteMjaN());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitIndiceFiabiliteMjaN(...).____________



	/**
	 * retourne le validerRGSectionHitIndiceFiabiliteMjaNRenseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceFiabiliteMjaNRenseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitIndiceFiabiliteMjaNRenseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitIndiceFiabiliteMjaNRenseigne01
					, fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01()
					, STRING_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01().
	

	
	/**
	 * Getter de la clé du validerRGSectionHitIndiceFiabiliteMjaNRenseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.indiceFiabiliteMjaN.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01() {
		return KEY_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01().



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitIndiceFiabiliteMjaNRenseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceFiabiliteMjaNRenseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitIndiceFiabiliteMjaNRenseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitIndiceFiabiliteMjaNRenseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01();
	} // Fin de getValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01().____
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitIndiceFiabiliteMjaNRenseigne01 
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
	* valeur à passer à validerRGSectionHitIndiceFiabiliteMjaNRenseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitIndiceFiabiliteMjaNRenseigne01
							, fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitIndiceFiabiliteMjaNRenseigne01(...)._



	/**
	 * retourne le validerRGSectionHitIndiceFiabiliteMjaNRegex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceFiabiliteMjaNRegex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitIndiceFiabiliteMjaNRegex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitIndiceFiabiliteMjaNRegex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitIndiceFiabiliteMjaNRegex02
					, fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNRegex02()
					, STRING_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitIndiceFiabiliteMjaNRegex02().____
	

	
	/**
	 * Getter de la clé du validerRGSectionHitIndiceFiabiliteMjaNRegex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.indiceFiabiliteMjaN.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNRegex02() {
		return KEY_VALIDER_SECTIONHIT_INDICEFIABLITEMJAN_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNRegex02()._



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitIndiceFiabiliteMjaNRegex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitIndiceFiabiliteMjaNRegex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitIndiceFiabiliteMjaNRegex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitIndiceFiabiliteMjaNRegex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitIndiceFiabiliteMjaNRegex02() 
													throws Exception {
		return fournirValiderRGSectionHitIndiceFiabiliteMjaNRegex02();
	} // Fin de getValiderRGSectionHitIndiceFiabiliteMjaNRegex02().________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitIndiceFiabiliteMjaNRegex02 
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
	* valeur à passer à validerRGSectionHitIndiceFiabiliteMjaNRegex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitIndiceFiabiliteMjaNRegex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitIndiceFiabiliteMjaNRegex02
							, fournirKeyValiderRGSectionHitIndiceFiabiliteMjaNRegex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitIndiceFiabiliteMjaNRegex02(...)._____
	
	
	
	/* 44 - mjmNmois01. **************/
	/**
	 * retourne le <code>validerRGSectionHitMjmNmois01</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>mjmNmois01</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>mjmNmois01</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMjmNmois01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMjmNmois01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitMjmNmois01
					, fournirKeyValiderRGSectionHitMjmNmois01()
					, STRING_VALIDER_SECTIONHIT_MJMNMOIS01_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMjmNmois01().____________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMjmNmois01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.mjmNmois01".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MJMNMOIS01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMjmNmois01() {
		return KEY_VALIDER_SECTIONHIT_MJMNMOIS01;
	} // Fin de fournirKeyValiderRGSectionHitMjmNmois01()._________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMjmNmois01 par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitMjmNmois01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMjmNmois01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMjmNmois01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMjmNmois01() 
													throws Exception {
		return fournirValiderRGSectionHitMjmNmois01();
	} // Fin de getValiderRGSectionHitMjmNmois01().________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMjmNmois01 par défaut 
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
	* valeur à passer à validerRGSectionHitMjmNmois01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMjmNmois01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitMjmNmois01
							, fournirKeyValiderRGSectionHitMjmNmois01());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMjmNmois01(...)._____________________



	/**
	 * retourne le validerRGSectionHitMjmNmois01Renseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois01Renseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMjmNmois01Renseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMjmNmois01Renseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitMjmNmois01Renseigne01
					, fournirKeyValiderRGSectionHitMjmNmois01Renseigne01()
					, STRING_VALIDER_SECTIONHIT_MJMNMOIS01_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMjmNmois01Renseigne01()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMjmNmois01Renseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.mjmNmois01.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MJMNMOIS01_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMjmNmois01Renseigne01() {
		return KEY_VALIDER_SECTIONHIT_MJMNMOIS01_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitMjmNmois01Renseigne01().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMjmNmois01Renseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois01Renseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMjmNmois01Renseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMjmNmois01Renseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMjmNmois01Renseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitMjmNmois01Renseigne01();
	} // Fin de getValiderRGSectionHitMjmNmois01Renseigne01()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMjmNmois01Renseigne01 
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
	* valeur à passer à validerRGSectionHitMjmNmois01Renseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMjmNmois01Renseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitMjmNmois01Renseigne01
							, fournirKeyValiderRGSectionHitMjmNmois01Renseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMjmNmois01Renseigne01(...).__________



	/**
	 * retourne le validerRGSectionHitMjmNmois01Regex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois01Regex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMjmNmois01Regex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMjmNmois01Regex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitMjmNmois01Regex02
					, fournirKeyValiderRGSectionHitMjmNmois01Regex02()
					, STRING_VALIDER_SECTIONHIT_MJMNMOIS01_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMjmNmois01Regex02()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMjmNmois01Regex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.mjmNmois01.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MJMNMOIS01_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMjmNmois01Regex02() {
		return KEY_VALIDER_SECTIONHIT_MJMNMOIS01_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitMjmNmois01Regex02().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMjmNmois01Regex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois01Regex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMjmNmois01Regex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMjmNmois01Regex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMjmNmois01Regex02() 
													throws Exception {
		return fournirValiderRGSectionHitMjmNmois01Regex02();
	} // Fin de getValiderRGSectionHitMjmNmois01Regex02()._________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMjmNmois01Regex02 
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
	* valeur à passer à validerRGSectionHitMjmNmois01Regex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMjmNmois01Regex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitMjmNmois01Regex02
							, fournirKeyValiderRGSectionHitMjmNmois01Regex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMjmNmois01Regex02(...).______________



	/**
	 * retourne le validerRGSectionHitMjmNmois01Numerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois01Numerique03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMjmNmois01Numerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMjmNmois01Numerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitMjmNmois01Numerique03
					, fournirKeyValiderRGSectionHitMjmNmois01Numerique03()
					, STRING_VALIDER_SECTIONHIT_MJMNMOIS01_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMjmNmois01Numerique03()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMjmNmois01Numerique03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.mjmNmois01.numerique".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MJMNMOIS01_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMjmNmois01Numerique03() {
		return KEY_VALIDER_SECTIONHIT_MJMNMOIS01_NUMERIQUE_03;
	} // Fin de fournirKeyValiderRGSectionHitMjmNmois01Numerique03().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMjmNmois01Numerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois01Numerique03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMjmNmois01Numerique03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMjmNmois01Numerique03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMjmNmois01Numerique03() 
													throws Exception {
		return fournirValiderRGSectionHitMjmNmois01Numerique03();
	} // Fin de getValiderRGSectionHitMjmNmois01Numerique03()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMjmNmois01Numerique03 
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
	* valeur à passer à validerRGSectionHitMjmNmois01Numerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMjmNmois01Numerique03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitMjmNmois01Numerique03
							, fournirKeyValiderRGSectionHitMjmNmois01Numerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMjmNmois01Numerique03(...).__________
	
	
	
	/* 45 - pcNuitNmois01. **************/
	/**
	 * retourne le <code>validerRGSectionHitPcNuitNmois01</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>pcNuitNmois01</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>pcNuitNmois01</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcNuitNmois01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcNuitNmois01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitPcNuitNmois01
					, fournirKeyValiderRGSectionHitPcNuitNmois01()
					, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS01_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcNuitNmois01()._________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcNuitNmois01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcNuitNmois01".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcNuitNmois01() {
		return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS01;
	} // Fin de fournirKeyValiderRGSectionHitPcNuitNmois01().______________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcNuitNmois01 par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitPcNuitNmois01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcNuitNmois01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcNuitNmois01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcNuitNmois01() 
													throws Exception {
		return fournirValiderRGSectionHitPcNuitNmois01();
	} // Fin de getValiderRGSectionHitPcNuitNmois01()._____________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcNuitNmois01 par défaut 
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
	* valeur à passer à validerRGSectionHitPcNuitNmois01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcNuitNmois01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitPcNuitNmois01
							, fournirKeyValiderRGSectionHitPcNuitNmois01());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcNuitNmois01(...).__________________



	/**
	 * retourne le validerRGSectionHitPcNuitNmois01Renseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois01Renseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcNuitNmois01Renseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcNuitNmois01Renseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPcNuitNmois01Renseigne01
					, fournirKeyValiderRGSectionHitPcNuitNmois01Renseigne01()
					, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS01_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcNuitNmois01Renseigne01().______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcNuitNmois01Renseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcNuitNmois01.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS01_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcNuitNmois01Renseigne01() {
		return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS01_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitPcNuitNmois01Renseigne01().___



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcNuitNmois01Renseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois01Renseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcNuitNmois01Renseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcNuitNmois01Renseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcNuitNmois01Renseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitPcNuitNmois01Renseigne01();
	} // Fin de getValiderRGSectionHitPcNuitNmois01Renseigne01().__________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcNuitNmois01Renseigne01 
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
	* valeur à passer à validerRGSectionHitPcNuitNmois01Renseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcNuitNmois01Renseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPcNuitNmois01Renseigne01
							, fournirKeyValiderRGSectionHitPcNuitNmois01Renseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcNuitNmois01Renseigne01(...)._______



	/**
	 * retourne le validerRGSectionHitPcNuitNmois01Regex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois01Regex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcNuitNmois01Regex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcNuitNmois01Regex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPcNuitNmois01Regex02
					, fournirKeyValiderRGSectionHitPcNuitNmois01Regex02()
					, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS01_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcNuitNmois01Regex02().__________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcNuitNmois01Regex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcNuitNmois01.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS01_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcNuitNmois01Regex02() {
		return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS01_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitPcNuitNmois01Regex02()._______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcNuitNmois01Regex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois01Regex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcNuitNmois01Regex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcNuitNmois01Regex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcNuitNmois01Regex02() 
													throws Exception {
		return fournirValiderRGSectionHitPcNuitNmois01Regex02();
	} // Fin de getValiderRGSectionHitPcNuitNmois01Regex02().______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcNuitNmois01Regex02 
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
	* valeur à passer à validerRGSectionHitPcNuitNmois01Regex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcNuitNmois01Regex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPcNuitNmois01Regex02
							, fournirKeyValiderRGSectionHitPcNuitNmois01Regex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcNuitNmois01Regex02(...).___________



	/**
	 * retourne le validerRGSectionHitPcNuitNmois01Numerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois01Numerique03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcNuitNmois01Numerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcNuitNmois01Numerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPcNuitNmois01Numerique03
					, fournirKeyValiderRGSectionHitPcNuitNmois01Numerique03()
					, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS01_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcNuitNmois01Numerique03().______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcNuitNmois01Numerique03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcNuitNmois01.numerique".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS01_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcNuitNmois01Numerique03() {
		return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS01_NUMERIQUE_03;
	} // Fin de fournirKeyValiderRGSectionHitPcNuitNmois01Numerique03().___



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcNuitNmois01Numerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois01Numerique03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcNuitNmois01Numerique03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcNuitNmois01Numerique03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcNuitNmois01Numerique03() 
													throws Exception {
		return fournirValiderRGSectionHitPcNuitNmois01Numerique03();
	} // Fin de getValiderRGSectionHitPcNuitNmois01Numerique03().__________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcNuitNmois01Numerique03 
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
	* valeur à passer à validerRGSectionHitPcNuitNmois01Numerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcNuitNmois01Numerique03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPcNuitNmois01Numerique03
							, fournirKeyValiderRGSectionHitPcNuitNmois01Numerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcNuitNmois01Numerique03(...)._______
	
	
	
	/* 46 - mjmNmois02. **************/
	/**
	 * retourne le <code>validerRGSectionHitMjmNmois02</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>mjmNmois02</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>mjmNmois02</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMjmNmois02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMjmNmois02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitMjmNmois02
					, fournirKeyValiderRGSectionHitMjmNmois02()
					, STRING_VALIDER_SECTIONHIT_MJMNMOIS02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMjmNmois02().____________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMjmNmois02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.mjmNmois02".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MJMNMOIS02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMjmNmois02() {
		return KEY_VALIDER_SECTIONHIT_MJMNMOIS02;
	} // Fin de fournirKeyValiderRGSectionHitMjmNmois02()._________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMjmNmois02 par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitMjmNmois02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMjmNmois02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMjmNmois02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMjmNmois02() 
													throws Exception {
		return fournirValiderRGSectionHitMjmNmois02();
	} // Fin de getValiderRGSectionHitMjmNmois02().________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMjmNmois02 par défaut 
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
	* valeur à passer à validerRGSectionHitMjmNmois02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMjmNmois02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitMjmNmois02
							, fournirKeyValiderRGSectionHitMjmNmois02());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMjmNmois02(...)._____________________



	/**
	 * retourne le validerRGSectionHitMjmNmois02Renseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois02Renseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMjmNmois02Renseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMjmNmois02Renseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitMjmNmois02Renseigne01
					, fournirKeyValiderRGSectionHitMjmNmois02Renseigne01()
					, STRING_VALIDER_SECTIONHIT_MJMNMOIS02_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMjmNmois02Renseigne01()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMjmNmois02Renseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.mjmNmois02.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MJMNMOIS02_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMjmNmois02Renseigne01() {
		return KEY_VALIDER_SECTIONHIT_MJMNMOIS02_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitMjmNmois02Renseigne01().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMjmNmois02Renseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois02Renseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMjmNmois02Renseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMjmNmois02Renseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMjmNmois02Renseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitMjmNmois02Renseigne01();
	} // Fin de getValiderRGSectionHitMjmNmois02Renseigne01()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMjmNmois02Renseigne01 
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
	* valeur à passer à validerRGSectionHitMjmNmois02Renseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMjmNmois02Renseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitMjmNmois02Renseigne01
							, fournirKeyValiderRGSectionHitMjmNmois02Renseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMjmNmois02Renseigne01(...).__________



	/**
	 * retourne le validerRGSectionHitMjmNmois02Regex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois02Regex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMjmNmois02Regex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMjmNmois02Regex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitMjmNmois02Regex02
					, fournirKeyValiderRGSectionHitMjmNmois02Regex02()
					, STRING_VALIDER_SECTIONHIT_MJMNMOIS02_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMjmNmois02Regex02()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMjmNmois02Regex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.mjmNmois02.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MJMNMOIS02_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMjmNmois02Regex02() {
		return KEY_VALIDER_SECTIONHIT_MJMNMOIS02_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitMjmNmois02Regex02().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMjmNmois02Regex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois02Regex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMjmNmois02Regex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMjmNmois02Regex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMjmNmois02Regex02() 
													throws Exception {
		return fournirValiderRGSectionHitMjmNmois02Regex02();
	} // Fin de getValiderRGSectionHitMjmNmois02Regex02()._________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMjmNmois02Regex02 
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
	* valeur à passer à validerRGSectionHitMjmNmois02Regex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMjmNmois02Regex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitMjmNmois02Regex02
							, fournirKeyValiderRGSectionHitMjmNmois02Regex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMjmNmois02Regex02(...).______________



	/**
	 * retourne le validerRGSectionHitMjmNmois02Numerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois02Numerique03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMjmNmois02Numerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMjmNmois02Numerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitMjmNmois02Numerique03
					, fournirKeyValiderRGSectionHitMjmNmois02Numerique03()
					, STRING_VALIDER_SECTIONHIT_MJMNMOIS02_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMjmNmois02Numerique03()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMjmNmois02Numerique03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.mjmNmois02.numerique".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MJMNMOIS02_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMjmNmois02Numerique03() {
		return KEY_VALIDER_SECTIONHIT_MJMNMOIS02_NUMERIQUE_03;
	} // Fin de fournirKeyValiderRGSectionHitMjmNmois02Numerique03().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMjmNmois02Numerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois02Numerique03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMjmNmois02Numerique03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMjmNmois02Numerique03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMjmNmois02Numerique03() 
													throws Exception {
		return fournirValiderRGSectionHitMjmNmois02Numerique03();
	} // Fin de getValiderRGSectionHitMjmNmois02Numerique03()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMjmNmois02Numerique03 
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
	* valeur à passer à validerRGSectionHitMjmNmois02Numerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMjmNmois02Numerique03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitMjmNmois02Numerique03
							, fournirKeyValiderRGSectionHitMjmNmois02Numerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMjmNmois02Numerique03(...).__________
	
	
	
	/* 47 - pcNuitNmois02. **************/
	/**
	 * retourne le <code>validerRGSectionHitPcNuitNmois02</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>pcNuitNmois02</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>pcNuitNmois02</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcNuitNmois02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcNuitNmois02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitPcNuitNmois02
					, fournirKeyValiderRGSectionHitPcNuitNmois02()
					, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcNuitNmois02()._________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcNuitNmois02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcNuitNmois02".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcNuitNmois02() {
		return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS02;
	} // Fin de fournirKeyValiderRGSectionHitPcNuitNmois02().______________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcNuitNmois02 par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitPcNuitNmois02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcNuitNmois02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcNuitNmois02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcNuitNmois02() 
													throws Exception {
		return fournirValiderRGSectionHitPcNuitNmois02();
	} // Fin de getValiderRGSectionHitPcNuitNmois02()._____________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcNuitNmois02 par défaut 
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
	* valeur à passer à validerRGSectionHitPcNuitNmois02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcNuitNmois02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitPcNuitNmois02
							, fournirKeyValiderRGSectionHitPcNuitNmois02());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcNuitNmois02(...).__________________



	/**
	 * retourne le validerRGSectionHitPcNuitNmois02Renseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois02Renseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcNuitNmois02Renseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcNuitNmois02Renseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPcNuitNmois02Renseigne01
					, fournirKeyValiderRGSectionHitPcNuitNmois02Renseigne01()
					, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS02_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcNuitNmois02Renseigne01().______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcNuitNmois02Renseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcNuitNmois02.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS02_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcNuitNmois02Renseigne01() {
		return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS02_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitPcNuitNmois02Renseigne01().___



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcNuitNmois02Renseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois02Renseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcNuitNmois02Renseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcNuitNmois02Renseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcNuitNmois02Renseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitPcNuitNmois02Renseigne01();
	} // Fin de getValiderRGSectionHitPcNuitNmois02Renseigne01().__________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcNuitNmois02Renseigne01 
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
	* valeur à passer à validerRGSectionHitPcNuitNmois02Renseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcNuitNmois02Renseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPcNuitNmois02Renseigne01
							, fournirKeyValiderRGSectionHitPcNuitNmois02Renseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcNuitNmois02Renseigne01(...)._______



	/**
	 * retourne le validerRGSectionHitPcNuitNmois02Regex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois02Regex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcNuitNmois02Regex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcNuitNmois02Regex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPcNuitNmois02Regex02
					, fournirKeyValiderRGSectionHitPcNuitNmois02Regex02()
					, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS02_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcNuitNmois02Regex02().__________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcNuitNmois02Regex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcNuitNmois02.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS02_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcNuitNmois02Regex02() {
		return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS02_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitPcNuitNmois02Regex02()._______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcNuitNmois02Regex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois02Regex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcNuitNmois02Regex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcNuitNmois02Regex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcNuitNmois02Regex02() 
													throws Exception {
		return fournirValiderRGSectionHitPcNuitNmois02Regex02();
	} // Fin de getValiderRGSectionHitPcNuitNmois02Regex02().______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcNuitNmois02Regex02 
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
	* valeur à passer à validerRGSectionHitPcNuitNmois02Regex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcNuitNmois02Regex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPcNuitNmois02Regex02
							, fournirKeyValiderRGSectionHitPcNuitNmois02Regex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcNuitNmois02Regex02(...).___________



	/**
	 * retourne le validerRGSectionHitPcNuitNmois02Numerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois02Numerique03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcNuitNmois02Numerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcNuitNmois02Numerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPcNuitNmois02Numerique03
					, fournirKeyValiderRGSectionHitPcNuitNmois02Numerique03()
					, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS02_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcNuitNmois02Numerique03().______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcNuitNmois02Numerique03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcNuitNmois02.numerique".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS02_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcNuitNmois02Numerique03() {
		return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS02_NUMERIQUE_03;
	} // Fin de fournirKeyValiderRGSectionHitPcNuitNmois02Numerique03().___



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcNuitNmois02Numerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois02Numerique03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcNuitNmois02Numerique03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcNuitNmois02Numerique03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcNuitNmois02Numerique03() 
													throws Exception {
		return fournirValiderRGSectionHitPcNuitNmois02Numerique03();
	} // Fin de getValiderRGSectionHitPcNuitNmois02Numerique03().__________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcNuitNmois02Numerique03 
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
	* valeur à passer à validerRGSectionHitPcNuitNmois02Numerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcNuitNmois02Numerique03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPcNuitNmois02Numerique03
							, fournirKeyValiderRGSectionHitPcNuitNmois02Numerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcNuitNmois02Numerique03(...)._______
	
	
	
	/* 48 - mjmNmois03. **************/
	/**
	 * retourne le <code>validerRGSectionHitMjmNmois03</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>mjmNmois03</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>mjmNmois03</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMjmNmois03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMjmNmois03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitMjmNmois03
					, fournirKeyValiderRGSectionHitMjmNmois03()
					, STRING_VALIDER_SECTIONHIT_MJMNMOIS03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMjmNmois03().____________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMjmNmois03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.mjmNmois03".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MJMNMOIS03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMjmNmois03() {
		return KEY_VALIDER_SECTIONHIT_MJMNMOIS03;
	} // Fin de fournirKeyValiderRGSectionHitMjmNmois03()._________________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMjmNmois03 par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitMjmNmois03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMjmNmois03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMjmNmois03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMjmNmois03() 
													throws Exception {
		return fournirValiderRGSectionHitMjmNmois03();
	} // Fin de getValiderRGSectionHitMjmNmois03().________________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMjmNmois03 par défaut 
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
	* valeur à passer à validerRGSectionHitMjmNmois03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMjmNmois03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitMjmNmois03
							, fournirKeyValiderRGSectionHitMjmNmois03());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMjmNmois03(...)._____________________



	/**
	 * retourne le validerRGSectionHitMjmNmois03Renseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois03Renseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMjmNmois03Renseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMjmNmois03Renseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitMjmNmois03Renseigne01
					, fournirKeyValiderRGSectionHitMjmNmois03Renseigne01()
					, STRING_VALIDER_SECTIONHIT_MJMNMOIS03_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMjmNmois03Renseigne01()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMjmNmois03Renseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.mjmNmois03.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MJMNMOIS03_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMjmNmois03Renseigne01() {
		return KEY_VALIDER_SECTIONHIT_MJMNMOIS03_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitMjmNmois03Renseigne01().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMjmNmois03Renseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois03Renseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMjmNmois03Renseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMjmNmois03Renseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMjmNmois03Renseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitMjmNmois03Renseigne01();
	} // Fin de getValiderRGSectionHitMjmNmois03Renseigne01()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMjmNmois03Renseigne01 
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
	* valeur à passer à validerRGSectionHitMjmNmois03Renseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMjmNmois03Renseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitMjmNmois03Renseigne01
							, fournirKeyValiderRGSectionHitMjmNmois03Renseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMjmNmois03Renseigne01(...).__________



	/**
	 * retourne le validerRGSectionHitMjmNmois03Regex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois03Regex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMjmNmois03Regex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMjmNmois03Regex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitMjmNmois03Regex02
					, fournirKeyValiderRGSectionHitMjmNmois03Regex02()
					, STRING_VALIDER_SECTIONHIT_MJMNMOIS03_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMjmNmois03Regex02()._____________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMjmNmois03Regex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.mjmNmois03.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MJMNMOIS03_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMjmNmois03Regex02() {
		return KEY_VALIDER_SECTIONHIT_MJMNMOIS03_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitMjmNmois03Regex02().__________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMjmNmois03Regex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois03Regex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMjmNmois03Regex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMjmNmois03Regex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMjmNmois03Regex02() 
													throws Exception {
		return fournirValiderRGSectionHitMjmNmois03Regex02();
	} // Fin de getValiderRGSectionHitMjmNmois03Regex02()._________________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMjmNmois03Regex02 
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
	* valeur à passer à validerRGSectionHitMjmNmois03Regex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMjmNmois03Regex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitMjmNmois03Regex02
							, fournirKeyValiderRGSectionHitMjmNmois03Regex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMjmNmois03Regex02(...).______________



	/**
	 * retourne le validerRGSectionHitMjmNmois03Numerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois03Numerique03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitMjmNmois03Numerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitMjmNmois03Numerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitMjmNmois03Numerique03
					, fournirKeyValiderRGSectionHitMjmNmois03Numerique03()
					, STRING_VALIDER_SECTIONHIT_MJMNMOIS03_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitMjmNmois03Numerique03()._________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitMjmNmois03Numerique03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.mjmNmois03.numerique".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_MJMNMOIS03_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitMjmNmois03Numerique03() {
		return KEY_VALIDER_SECTIONHIT_MJMNMOIS03_NUMERIQUE_03;
	} // Fin de fournirKeyValiderRGSectionHitMjmNmois03Numerique03().______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitMjmNmois03Numerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitMjmNmois03Numerique03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitMjmNmois03Numerique03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitMjmNmois03Numerique03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitMjmNmois03Numerique03() 
													throws Exception {
		return fournirValiderRGSectionHitMjmNmois03Numerique03();
	} // Fin de getValiderRGSectionHitMjmNmois03Numerique03()._____________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitMjmNmois03Numerique03 
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
	* valeur à passer à validerRGSectionHitMjmNmois03Numerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitMjmNmois03Numerique03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitMjmNmois03Numerique03
							, fournirKeyValiderRGSectionHitMjmNmois03Numerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitMjmNmois03Numerique03(...).__________
	
	
	
	/* 49 - pcNuitNmois03. **************/
	/**
	 * retourne le <code>validerRGSectionHitPcNuitNmois03</code> 
	 * par défaut de l'application.<br/>
	 * Boolean activant <b>globalement</b> les contrôles 
	 * sur <i>pcNuitNmois03</i> de SectionHit.<br/>
	 * <b>interrupteur GENERAL</b> sur les contrôles de <i>pcNuitNmois03</i> 
	 * de SectionHit.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcNuitNmois03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcNuitNmois03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			return fournirAttribut(
					validerRGSectionHitPcNuitNmois03
					, fournirKeyValiderRGSectionHitPcNuitNmois03()
					, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcNuitNmois03()._________________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcNuitNmois03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcNuitNmois03".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcNuitNmois03() {
		return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS03;
	} // Fin de fournirKeyValiderRGSectionHitPcNuitNmois03().______________



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcNuitNmois03 par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li><b>interrupteur GENERAL de la validation de l'attribut.</b></li>
	 * <li>lit le validerRGSectionHitPcNuitNmois03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcNuitNmois03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcNuitNmois03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcNuitNmois03() 
													throws Exception {
		return fournirValiderRGSectionHitPcNuitNmois03();
	} // Fin de getValiderRGSectionHitPcNuitNmois03()._____________________
	
	
	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcNuitNmois03 par défaut 
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
	* valeur à passer à validerRGSectionHitPcNuitNmois03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcNuitNmois03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
						
			setterAttribut(
					pValue
						, validerRGSectionHitPcNuitNmois03
							, fournirKeyValiderRGSectionHitPcNuitNmois03());

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcNuitNmois03(...).__________________



	/**
	 * retourne le validerRGSectionHitPcNuitNmois03Renseigne01 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois03Renseigne01 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcNuitNmois03Renseigne01 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcNuitNmois03Renseigne01() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPcNuitNmois03Renseigne01
					, fournirKeyValiderRGSectionHitPcNuitNmois03Renseigne01()
					, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS03_RENSEIGNE_01_EN_DUR);

		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcNuitNmois03Renseigne01().______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcNuitNmois03Renseigne01 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcNuitNmois03.renseigne".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS03_RENSEIGNE_01 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcNuitNmois03Renseigne01() {
		return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS03_RENSEIGNE_01;
	} // Fin de fournirKeyValiderRGSectionHitPcNuitNmois03Renseigne01().___



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcNuitNmois03Renseigne01 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois03Renseigne01 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcNuitNmois03Renseigne01 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcNuitNmois03Renseigne01 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcNuitNmois03Renseigne01() 
													throws Exception {
		return fournirValiderRGSectionHitPcNuitNmois03Renseigne01();
	} // Fin de getValiderRGSectionHitPcNuitNmois03Renseigne01().__________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcNuitNmois03Renseigne01 
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
	* valeur à passer à validerRGSectionHitPcNuitNmois03Renseigne01.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcNuitNmois03Renseigne01(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPcNuitNmois03Renseigne01
							, fournirKeyValiderRGSectionHitPcNuitNmois03Renseigne01());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcNuitNmois03Renseigne01(...)._______



	/**
	 * retourne le validerRGSectionHitPcNuitNmois03Regex02 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois03Regex02 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcNuitNmois03Regex02 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcNuitNmois03Regex02() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPcNuitNmois03Regex02
					, fournirKeyValiderRGSectionHitPcNuitNmois03Regex02()
					, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS03_REGEX_02_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcNuitNmois03Regex02().__________
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcNuitNmois03Regex02 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcNuitNmois03.regex".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS03_REGEX_02 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcNuitNmois03Regex02() {
		return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS03_REGEX_02;
	} // Fin de fournirKeyValiderRGSectionHitPcNuitNmois03Regex02()._______



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcNuitNmois03Regex02 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois03Regex02 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcNuitNmois03Regex02 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcNuitNmois03Regex02 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcNuitNmois03Regex02() 
													throws Exception {
		return fournirValiderRGSectionHitPcNuitNmois03Regex02();
	} // Fin de getValiderRGSectionHitPcNuitNmois03Regex02().______________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcNuitNmois03Regex02 
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
	* valeur à passer à validerRGSectionHitPcNuitNmois03Regex02.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcNuitNmois03Regex02(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPcNuitNmois03Regex02
							, fournirKeyValiderRGSectionHitPcNuitNmois03Regex02());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcNuitNmois03Regex02(...).___________



	/**
	 * retourne le validerRGSectionHitPcNuitNmois03Numerique03 
	 * par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois03Numerique03 stocké 
	 * dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne la valeur stockée en dur dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Boolean : validerRGSectionHitPcNuitNmois03Numerique03 
	 * dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Boolean fournirValiderRGSectionHitPcNuitNmois03Numerique03() 
			throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			return fournirAttribut(
					validerRGSectionHitPcNuitNmois03Numerique03
					, fournirKeyValiderRGSectionHitPcNuitNmois03Numerique03()
					, STRING_VALIDER_SECTIONHIT_PCNUITNMOIS03_NUMERIQUE_03_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirValiderRGSectionHitPcNuitNmois03Numerique03().______
	

	
	/**
	 * Getter de la clé du validerRGSectionHitPcNuitNmois03Numerique03 
	 * par défaut de l'application 
	 * dans SectionHit_RG.properties.<br/>
	 * "valider.SectionHit.pcNuitNmois03.numerique".<br/>
	 *
	 * @return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS03_NUMERIQUE_03 : String.<br/>
	 */
	public static String fournirKeyValiderRGSectionHitPcNuitNmois03Numerique03() {
		return KEY_VALIDER_SECTIONHIT_PCNUITNMOIS03_NUMERIQUE_03;
	} // Fin de fournirKeyValiderRGSectionHitPcNuitNmois03Numerique03().___



	/**
	 * Getter du <b>SINGLETON de validerRGSectionHitPcNuitNmois03Numerique03 
	 * par défaut dans l'application</b>.
	 * <ul>
	 * <li>lit le validerRGSectionHitPcNuitNmois03Numerique03 
	 * stocké dans SectionHit_RG.properties 
	 * si il n'est pas null.</li>
	 * <li>true sinon (stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le validerRGSectionHitPcNuitNmois03Numerique03 stocké en dur 
	 * dans la classe (true) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return validerRGSectionHitPcNuitNmois03Numerique03 : Boolean.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Boolean getValiderRGSectionHitPcNuitNmois03Numerique03() 
													throws Exception {
		return fournirValiderRGSectionHitPcNuitNmois03Numerique03();
	} // Fin de getValiderRGSectionHitPcNuitNmois03Numerique03().__________
	

	
	/**
	* Setter du <b>SINGLETON de validerRGSectionHitPcNuitNmois03Numerique03 
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
	* valeur à passer à validerRGSectionHitPcNuitNmois03Numerique03.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setValiderRGSectionHitPcNuitNmois03Numerique03(
			final Boolean pValue) throws Exception {
		
		synchronized (SectionHitGestionnairePreferencesRG.class) {
			
			setterAttribut(
					pValue
						, validerRGSectionHitPcNuitNmois03Numerique03
							, fournirKeyValiderRGSectionHitPcNuitNmois03Numerique03());
			
		} // Fin du bloc synchronized.__________________
						
	} // Fin de setValiderRGSectionHitPcNuitNmois03Numerique03(...)._______
	
					
	
} // FIN DE LA CLASSE SectionHitGestionnairePreferencesRG.-------------------
